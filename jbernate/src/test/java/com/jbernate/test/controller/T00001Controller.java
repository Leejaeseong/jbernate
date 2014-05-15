package com.jbernate.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jbernate.common.util.ConstantUtil;
import com.jbernate.common.util.ControllerUtil;
import com.jbernate.common.util.LoggerUtil;

/**
 * 컨트롤러 테스트
 */
@Controller
@RequestMapping( value = ConstantUtil.PATH_CONTROLLER_TEST + "/" + ConstantUtil.ID_PAGE_TEST + "00001" )
public class T00001Controller {
	@RequestMapping( method = RequestMethod.GET )
	public String root(	HttpSession session
						, Model model
						, HttpServletRequest request
	) {
		LoggerUtil.trace( "logger.trace111" );

		return ControllerUtil.getViewName( getClass() );
	}
}