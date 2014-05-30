package com.jbernate.cm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jbernate.cm.bean.Sb;
import com.jbernate.cm.service.CmCrudService;
import com.jbernate.cm.util.BeanUtil;
import com.jbernate.cm.util.ConstUtil;
import com.jbernate.cm.util.ControllerUtil;
import com.jbernate.cm.util.DbUtil;
import com.jbernate.cm.util.LogUtil;
import com.jbernate.cm.util.StrUtil;

/**
 * 공통으로 사용되는 컨트롤러 기능
 * 각 도메인에서 extends하여 사용한다( 이 클래스에서 RequestMapping value를 "/"로 사용하면 resource파일도 Servlet으로 실행되어 버림 )
 */
@Controller
public class BasicController {

	@Autowired CmCrudService cService;
	
	/**
	 * 페이지 로딩
	 * @param pgmId		프로그램 ID
	 * @param model		Model
	 * @param request	HttpServletRequest
	 * @return			/domain/pageId 페이지
	 */
	@RequestMapping( value = "/{pgmId}/" + ConstUtil.FORMAT_CONTROLLER_COMMAND_LOAD, method = RequestMethod.GET )
	public String list( 
			@PathVariable( "pgmId" ) String pgmId
			, HttpSession session
			, Model model, HttpServletRequest request 
		) throws Exception {
		
		LogUtil.trace( pgmId + " program : loaded( By BasicController > load method" );
		
		Sb sb = new Sb();
		
		// 모델 공통부분 설정
		model = BeanUtil.getCommonModel( this, session, model, request, sb );
		
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
		) throws Exception{
		
		LogUtil.trace( pgmId + " program : loaded( By BasicController > list method" );
		
		@SuppressWarnings("rawtypes")
		List list = cService.list( request, StrUtil.chkBlank( viewNm ) ? DbUtil.getEntityName( viewNm ) : pgmId );
		
		model.addAttribute( "viewData", list );
		
		LogUtil.trace( pgmId + " data count = " + list.size() );
		
		return ControllerUtil.getViewName( request );

	}
}