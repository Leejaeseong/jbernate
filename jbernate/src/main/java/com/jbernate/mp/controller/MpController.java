package com.jbernate.mp.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.jbernate.cm.controller.BasicController;
import com.jbernate.cm.domain.table.CmPgmMgr;
import com.jbernate.cm.util.ConstUtil;
import com.jbernate.cm.util.ControllerUtil;
import com.jbernate.cm.util.LogUtil;

/**
 * 먼디파마 컨트롤러
 * URL /mp 로 시작하고, BasicController 기능을 상속받는 공통 컨트롤러
 */
@Controller
@RequestMapping( value = ConstUtil.PATH_CONTROLLER_MP )
public class MpController extends BasicController{
}