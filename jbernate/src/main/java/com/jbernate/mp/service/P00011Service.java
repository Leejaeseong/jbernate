package com.jbernate.mp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

/**
 * 먼디파마 > 제품그룹 관리
 */
public interface P00011Service {

	/** 데이터 로드 */
	@SuppressWarnings("rawtypes")
	Model load( HttpSession sess, HttpServletRequest req, HttpServletResponse res, Model model, String postPayload );
	
	/** 데이터 저장 */
	@SuppressWarnings("rawtypes")
	Model submit( HttpSession sess, HttpServletRequest req, HttpServletResponse res, Model model, String postPayload, String submitType );
}