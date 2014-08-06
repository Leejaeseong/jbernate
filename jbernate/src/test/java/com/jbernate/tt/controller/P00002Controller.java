package com.jbernate.tt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jbernate.cm.util.ConstUtil;
import com.jbernate.cm.util.ControllerUtil;
import com.jbernate.cm.util.LogUtil;

/**
 * 컨트롤러 테스트
 */
@Controller
@RequestMapping( value = ConstUtil.PATH_CONTROLLER_TEST + "/" + ConstUtil.ID_PAGE_PREFIX + "00002" )
public class P00002Controller {
	@RequestMapping( method = RequestMethod.GET )
	public String load(	HttpSession session
						, Model model
						, HttpServletRequest request
	) {
		LogUtil.trace( "logger.trace!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" );
		
		return ControllerUtil.getViewName( request );
	}
}