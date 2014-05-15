package com.jbernate.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jbernate.common.util.ControllerUtil;
import com.jbernate.common.util.LoggerUtil;

/**
 * 공통으로 사용되는 컨트롤러 기능
 * 각 도메인에서 extends하여 사용한다( 이 클래스에서 RequestMapping value를 "/"로 사용하면 resource파일도 Servlet으로 실행되어 버림 )
 */
@Controller
public class BasicController {

	/**
	 * 페이지만 단순 로딩
	 * @param domain	contenxt domain
	 * @param pageId	페이지 ID
	 * @param model		Model
	 * @param request	HttpServletRequest
	 * @return			/domain/pageId 페이지
	 */
	@RequestMapping( value = "/{pageId}/load", method = RequestMethod.GET )
	public String list( @PathVariable( "pageId" ) String pageId
			, Model model, HttpServletRequest request 
		) {
		
		LoggerUtil.trace( pageId + " page : loaded( By BasicController > listView method" );
		
		return ControllerUtil.getViewName( request );
	}
	
	@RequestMapping( "/{pageId}/list" )
	public String list( @PathVariable( "pageId" ) String pageId
			, @RequestParam( value = "isLoadView", required = false ) boolean isLoadView	// 뷰 로딩 여부( default = true )
			, Model model, HttpServletRequest request 
		) {
		
		return ControllerUtil.getViewName( request );
	}
}