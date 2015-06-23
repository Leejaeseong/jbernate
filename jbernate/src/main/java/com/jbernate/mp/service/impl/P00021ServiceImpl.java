package com.jbernate.mp.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.jbernate.cm.bean.WhereBean;
import com.jbernate.cm.bean.WhereBean.Clause;
import com.jbernate.cm.dao.CmDao;
import com.jbernate.cm.service.CmService;
import com.jbernate.cm.util.StrUtil;
import com.jbernate.mp.service.P00021Service;
import com.jbernate.mp.util.MpSearchUtil;
import com.jbernate.mundi.domain.table.PerBrandMgr;
import com.jbernate.mundi.domain.table.PerYearMgr;
import com.jbernate.mundi.domain.table.PrdgrpMgr;

/**
 * 먼디파마 > 년별 요율 관리
 */
@Service
@Transactional
public class P00021ServiceImpl implements P00021Service{

	@Autowired CmDao dao;
	@Autowired CmService cmService;
	
	/** 데이터 로드 */
	@Override
	@SuppressWarnings("rawtypes")	
	public Model load( HttpSession sess, HttpServletRequest req, HttpServletResponse res, Model model, String postPayload ) {
		
		Gson gson = new Gson();
		
		LinkedTreeMap map = new LinkedTreeMap();
		map = (LinkedTreeMap) gson.fromJson(postPayload, map.getClass());
		
		ArrayList wbList = new ArrayList<WhereBean>();
		wbList.add( new WhereBean( "yyyy"	, map.get( "searchYyyy" ).toString().replaceAll( "-", "" ) , Clause.EQ ) );
		
		List rList = dao.list( req, new PerYearMgr(), wbList );
		
		model.addAttribute( "viewData", rList );
		
		return model;
	}
	
	/** 데이터 저장 */
	@Override
	@SuppressWarnings("rawtypes")	
	public Model submit( HttpSession sess, HttpServletRequest req, HttpServletResponse res, Model model, String postPayload, String submitType ) {
		
		Gson gson = new Gson();
		
		LinkedTreeMap ltMap = (LinkedTreeMap)gson.fromJson(postPayload, new Object().getClass());
		
		List list = new ArrayList();
		list=(List)gson.fromJson(ltMap.get( "saveData" ).toString(), list.getClass());
		
		PerYearMgr year;
		for( int i = 0; i < list.size(); i++ ) {
			LinkedTreeMap map = (LinkedTreeMap)list.get( i );
			if( map == null ) continue;	// null 값이 넘어오면 Pass
			if( map.get( "CRUD" ).equals( "I" ) ) {
				year = new PerYearMgr();
				
				year.setYyyy( ltMap.get( "yyyy" ).toString().replaceAll( "-", "" ) );
				
				year.setCycle( map.get( "cycle" ).toString() );
				year.setPercentage( 	new BigDecimal( map.get( "percentage" ).toString() ) );
				year.setRemk( StrUtil.nvlNull( map.get( "remk" ) ) );
				year.setUseYn( "Y" );
				
				cmService.create( req, year );
			} else if( map.get( "CRUD" ).equals( "U" ) ) {
				year = new PerYearMgr( new BigDecimal( map.get( "seq" ).toString() ) );
				year = (PerYearMgr)cmService.get( req, year );
				year.setCycle( map.get( "cycle" ).toString() );
				year.setPercentage( 	new BigDecimal( map.get( "percentage" ).toString() ) );
				year.setRemk( StrUtil.nvlNull( map.get( "remk" ) ) );
				
				cmService.update( req, year );
			} else if( map.get( "CRUD" ).equals( "D" ) && map.get( "seq" ).toString().indexOf( "-" ) == -1 ) {	// 신규추가( seq가 음수 )는 삭제할 필요도 없음
				year = new PerYearMgr( new BigDecimal( map.get( "seq" ).toString() ) );
				year = (PerYearMgr)cmService.get( req, year );
				
				cmService.delete( req, year );
			}
		}
		
		return model;
	}
}