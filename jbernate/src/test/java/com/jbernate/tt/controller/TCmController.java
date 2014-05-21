package com.jbernate.tt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jbernate.cm.controller.BasicController;
import com.jbernate.cm.util.ConstantUtil;

/**
 * 테스트 공통 컨트롤러
 * URL /tt 로 시작하고, BasicController 기능을 상속받는 테스트 공통 컨트롤러
 */
@Controller
@RequestMapping( value = ConstantUtil.PATH_CONTROLLER_TEST )
public class TCmController extends BasicController{	
}