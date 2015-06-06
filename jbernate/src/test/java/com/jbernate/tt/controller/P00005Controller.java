package com.jbernate.tt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.jbernate.cm.bean.Sb;
import com.jbernate.cm.service.CmService;
import com.jbernate.cm.util.ArrUtil;
import com.jbernate.cm.util.BeanUtil;
import com.jbernate.cm.util.ConstUtil;
import com.jbernate.cm.util.ControllerUtil;
import com.jbernate.cm.util.LogUtil;
import com.jbernate.cm.validator.SbValidator;

/**
 * Form 테스트
 */
@Controller
@RequestMapping( value = ConstUtil.PATH_CONTROLLER_TEST + "/" + ConstUtil.ID_PAGE_PREFIX + "00005" )
@SessionAttributes( value = "sb" )
public class P00005Controller {
	
	@Autowired CmService cmService;
	@Autowired SbValidator sbValidator;
	
	/* ▣ CmController에 합병하기 위해 선행 테스트 한 소스 입니다.
	@RequestMapping( value = "/" + ConstUtil.FORMAT_CONTROLLER_COMMAND_LOAD, method = RequestMethod.GET )
	public String load(	HttpSession session
						, Model model
						, HttpServletRequest request
	) {
		LogUtil.trace( "logger.trace!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" );
		
		TtOneTable ttOneTable = new TtOneTable();
		@SuppressWarnings("unchecked")
		List<TtOneTable> list = cmService.list( request, ttOneTable );
		if( ArrUtil.chkBlank( list ) ) {
			ttOneTable = list.get( 0 );
		}
		
		Sb sb = new Sb();
		sb.setTtOneTable( ttOneTable );
		
		// 모델 공통부분 설정
		model = BeanUtil.getCommonModel( session, model, request );
		
		return ControllerUtil.getViewName( getClass() );
	}
	*/
	
	/* ▣ CmController에 합병하기 위해 선행 테스트 한 소스 입니다.
	@RequestMapping( value = "/" + ConstUtil.FORMAT_CONTROLLER_COMMAND_SUBMIT, method = RequestMethod.POST )
	public String submit(
			@ModelAttribute  Sb sb
			, BindingResult result
			, HttpSession session
			, SessionStatus status
			, HttpServletRequest request
	) {
		sbValidator.validate( sb, result );
		
		// 오류 발생 시 load 부분으로 redirect
		if( result.hasErrors() ){
			return //"redirect:/" + ControllerUtil.getViewName( getClass() ) + "/" + ConstUtil.FORMAT_CONTROLLER_COMMAND_LOAD;
					"/" + ControllerUtil.getViewName( request );
		}
		
		status.setComplete();
		
		return ControllerUtil.getViewName( request );
	}
	*/
}