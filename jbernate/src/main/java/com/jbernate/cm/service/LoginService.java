package com.jbernate.cm.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

/**
 * 로그인/아웃 및 세션 관리
 */
public interface LoginService {

	/**
	 * 세션 체크 및 성공 시 세션 저장
	 * @param request	HttpServletRequest
	 * @return			String : 0 = 실패, 1 = 성공
	 */
	@SuppressWarnings("rawtypes")
	Model submit( HttpSession sess, HttpServletRequest req, HttpServletResponse res, Model model, String submitType );
}