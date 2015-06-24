package com.jbernate.mp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

/**
 * 먼디파마 > 실적 조회
 */
public interface P00017Service {

	/** 데이터 로드 */
	@SuppressWarnings("rawtypes")
	Model load( HttpSession sess, HttpServletRequest req, HttpServletResponse res, Model model, String postPayload );

}