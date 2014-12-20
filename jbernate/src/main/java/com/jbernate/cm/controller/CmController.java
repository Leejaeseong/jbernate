package com.jbernate.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jbernate.cm.service.SessionService;
import com.jbernate.cm.util.ConstUtil;

/**
 * CM 도메인 컨트롤러
 */
@Controller
@RequestMapping( value = ConstUtil.PATH_CONTROLLER_CM )
public class CmController extends BasicController{
}