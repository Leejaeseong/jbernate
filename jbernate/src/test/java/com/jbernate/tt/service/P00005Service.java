package com.jbernate.tt.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public interface P00005Service {
	public Model load( HttpSession session, HttpServletRequest request, Model model );
}