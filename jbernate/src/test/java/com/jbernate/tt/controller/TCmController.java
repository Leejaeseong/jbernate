package com.jbernate.tt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jbernate.cm.controller.BasicController;
import com.jbernate.cm.util.ConstantUtil;
import com.jbernate.cm.util.ControllerUtil;
import com.jbernate.cm.util.LoggerUtil;

/**
 * 테스트 공통 컨트롤러
 * URL /tt 로 시작하고, BasicController 기능을 상속받는 테스트 공통 컨트롤러
 */
@Controller
@RequestMapping( value = ConstantUtil.PATH_CONTROLLER_TEST )
public class TCmController extends BasicController{	
}