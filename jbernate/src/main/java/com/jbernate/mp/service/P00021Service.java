package com.jbernate.mp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

/**
 * 먼디파마 > 년별 요율 관리
 */
public interface P00021Service {

	/** 데이터 로드 */
	@SuppressWarnings("rawtypes")
	Model load( HttpSession sess, HttpServletRequest req, HttpServletResponse res, Model model, String postPayload );
	
	/** 데이터 저장 */
	@SuppressWarnings("rawtypes")
	Model submit( HttpSession sess, HttpServletRequest req, HttpServletResponse res, Model model, String postPayload, String submitType );
}