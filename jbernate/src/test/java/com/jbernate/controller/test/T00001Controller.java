package com.jbernate.controller.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jbernate.util.ConstantUtil;
import com.jbernate.util.ControllerUtil;

/**
 * 컨트롤러 테스트
 */
@Controller
@RequestMapping( value = ConstantUtil.PATH_CONTROLLER_TEST + "/" + ConstantUtil.ID_PAGE_TEST + "00001" )
public class T00001Controller {
	
	private final Logger logger = LoggerFactory.getLogger( getClass().getName() );
	
	@RequestMapping( method = RequestMethod.GET )
	public String root(	HttpSession session
						, Model model
						, HttpServletRequest request
	) {
		logger.debug( "logger.debug!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" );
		logger.info( "logger.info!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" );
		
		return ControllerUtil.getViewName( getClass() );
	}
}