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
 * URL /mp 로 시작하고, BasicController 기능을 상속받는 테스트 공통 컨트롤러
 */
@Controller
@RequestMapping( value = ConstUtil.PATH_CONTROLLER_MP )
public class MpController extends BasicController{
	@RequestMapping( "/{pgmId}/test" )
	public String list( 
			@PathVariable( "pgmId" ) String pgmId
			, HttpSession session
			, @RequestParam( value = "viewNm", required = false ) 	String viewNm
			, Model model
			, HttpServletRequest request 
			, HttpServletResponse response
		) throws Exception{
		
		LogUtil.info( pgmId + " test1234" );
		
		List list = new ArrayList();
		CmPgmMgr mgr = new CmPgmMgr( new BigDecimal( 1 ) );
		mgr.setRemk( "테스트 입니다1" );
		mgr.setPgmNm( "테스트프로그램1" );
		list.add( mgr );
		mgr = new CmPgmMgr( new BigDecimal( 2 ) );
		mgr.setRemk( "테스트 입니다2" );
		mgr.setPgmNm( "테스트프로그램2" );
		list.add( mgr );
		mgr = new CmPgmMgr( new BigDecimal( 3 ) );
		mgr.setRemk( "테스트 입니다3" );
		mgr.setPgmNm( "테스트프로그램3" );
		list.add( mgr );
		model.addAttribute( "viewData", list );
		
		//@SuppressWarnings("rawtypes")
		//List list = cmService.list( request, StrUtil.chkBlank( viewNm ) ? DbUtil.getEntityName( viewNm ) : pgmId );
		
		//model.addAttribute( "viewData", list );
		
		//LogUtil.trace( pgmId + " data count = " + list.size() );
		
		return ControllerUtil.getViewName( request );
	}
	
	@RequestMapping( "/{pgmId}/testSave" )
	public String save( 
			@PathVariable( "pgmId" ) String pgmId
			, HttpSession session
			, @RequestParam( value = "viewNm", required = false ) 	String viewNm
			, Model model
			, HttpServletRequest request 
			, HttpServletResponse response
			, @RequestBody String postPayload
		) throws Exception{
		Gson gson = new Gson();
		
		List list = new ArrayList();
		list=(List) gson.fromJson(postPayload, list.getClass());
		
		for( int i = 0; i < list.size(); i++ ) {
			LinkedTreeMap map = (LinkedTreeMap)list.get( i );
			System.out.println( "seq = " + map.get( "seq" ) + ", pgmNm = " + map.get( "pgmNm" ) + ", remk = " + map.get( "remk" ) + ", CRUD = " + map.get( "CRUD" ) );
		}
		
		LogUtil.info( pgmId + " testSave" );
		
		return ControllerUtil.getViewName( request );
	}
}