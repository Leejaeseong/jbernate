package com.jbernate.cm.controller;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.jbernate.cm.bean.Sb;
import com.jbernate.cm.service.CmService;
import com.jbernate.cm.util.BeanUtil;
import com.jbernate.cm.util.ConstUtil;
import com.jbernate.cm.util.ControllerUtil;
import com.jbernate.cm.util.DbUtil;
import com.jbernate.cm.util.LogUtil;
import com.jbernate.cm.util.StrUtil;
import com.jbernate.cm.validator.SbValidator;

/**
 * 공통으로 사용되는 컨트롤러 기능
 * 각 도메인에서 extends하여 사용한다( 이 클래스에서 RequestMapping value를 "/"로 사용하면 resource파일도 Servlet으로 실행되어 버림 )
 */
@Controller
public class BasicController {

	@Autowired CmService cmService;
	@Autowired AutowireCapableBeanFactory factory;
	@Autowired ApplicationContext appContext;
	@Autowired SbValidator sbValidator;

	/**
	 * 페이지 로딩
	 * @param pgmId		프로그램 ID
	 * @param model		Model
	 * @param request	HttpServletRequest
	 * @return			/domain/pageId 페이지
	 */
	@RequestMapping( value = "/{pgmId}/" + ConstUtil.FORMAT_CONTROLLER_COMMAND_LOAD )
	public String list( 
			@PathVariable( "pgmId" ) String pgmId
			, HttpSession session
			, Model model
			, HttpServletRequest request 
			, HttpServletResponse response 
			, @RequestBody String postPayload	// Json 데이터를 받기 위함
		) throws Exception {
		
		LogUtil.trace( pgmId + " program : loaded( By BasicController > load method )" );	// Log
		
		// 해당 프로그램 Service의 load 함수 호출
		try{
			Object sBean = appContext.getBean( Class.forName( ControllerUtil.getClassPathByUrl( request, "service" ) + "Service" ) );
			Method m = sBean.getClass().getDeclaredMethod( "load", HttpSession.class, HttpServletRequest.class, HttpServletResponse.class, Model.class, String.class );
			model = (Model)m.invoke( sBean, session, request, response, model, postPayload );
		}catch( Exception e ) {
			LogUtil.trace( "Service( " + ControllerUtil.getClassPathByUrl( request, "service" ) + "Service" + ") has not method of load" );
		}
		
		// 모델 공통부분 설정
		model = BeanUtil.getCommonModel( session, model, request );
		
		return ControllerUtil.getViewName( request );
	}
	
	/**
	 * Form Submit
	 * @param sb		Sb( SessionBean ) : 테이블 및 뷰 정보를 담고 있는 상위 공통 빈
	 * @param result	Validation 결과 객체
	 * @param session	HttpSession
	 * @param status	SessionStatus : Form session
	 * @param request	HttpServlerRequest
	 * @return			/domain/pageId 페이지로 submit
	 */
	// TODO submit 서비스 호출 및 메시지 리턴 처리
	@RequestMapping( value = "/{pgmId}/" + ConstUtil.FORMAT_CONTROLLER_COMMAND_SUBMIT, method = RequestMethod.POST )
	public String submit(
			  @ModelAttribute  Sb sb
			, @PathVariable( "pgmId" ) String pgmId
			, @RequestParam( value = "submitType", required = false ) String submitType	// 타입 구분자를 통해 한 페이지에서 여러 형태의 submit 지원
			, BindingResult result
			, HttpSession session			
			, SessionStatus status
			, Model model
			, HttpServletRequest request
			, HttpServletResponse response
			, @RequestBody String postPayload	// Json 데이터를 받기 위함			
	) {
		sbValidator.validate( sb, result );
		
		// 오류 발생 시 load 부분으로 redirect
		if( result.hasErrors() ){	return "/" + ControllerUtil.getViewName( request );	}
		
		// 해당 프로그램 Service의 Submit 함수 호출
		try{
			Object sBean = appContext.getBean( Class.forName( ControllerUtil.getClassPathByUrl( request, "service" ) + "Service" ) );
			Method m = sBean.getClass().getDeclaredMethod( "submit", HttpSession.class, HttpServletRequest.class, HttpServletResponse.class, Model.class, String.class, String.class );
			model = (Model)m.invoke( sBean, session, request, response, model, postPayload, submitType );
		}catch( Exception e ) {
			e.printStackTrace();
			LogUtil.trace( "Service( " + ControllerUtil.getClassPathByUrl( request, "service" ) + "Service" + " ) has not method of submit" );
		}
	
		status.setComplete();	// Form session clear
		
		return ControllerUtil.getViewName( request );
	}
	
	/**
	 * 뷰 데이터 로딩
	 * @param pgmId		프로그램 ID
	 * @param viewNm	로딩할 뷰 이름( 테이블/뷰 이름을 DB 스키마 그대로 사용 )
	 * 					없을 경우 {pageId}_V 이름의 뷰를 호출
	 * @param model		Model
	 * @param request	HttpServletRequest
	 * @return			/domain/pageId( 주로 Json 형태로 데이터만 전달 )
	 */
	// TODO 검색 파라미터 공통 처리
	@RequestMapping( "/{pgmId}/" + ConstUtil.FORMAT_CONTROLLER_COMMAND_LIST )
	public String list( 
			@PathVariable( "pgmId" ) String pgmId
			, HttpSession session
			, @RequestParam( value = "viewNm", required = false ) 	String viewNm
			, Model model
			, HttpServletRequest request 
			, HttpServletResponse response
		) throws Exception{
		
		LogUtil.trace( pgmId + " program : loaded( By BasicController > list method" );
		
		@SuppressWarnings("rawtypes")
		List list = cmService.list( request, StrUtil.chkBlank( viewNm ) ? DbUtil.getEntityName( viewNm ) : pgmId );
		
		model.addAttribute( "viewData", list );
		
		LogUtil.trace( pgmId + " data count = " + list.size() );
		
		return ControllerUtil.getViewName( request );
	}
 
}