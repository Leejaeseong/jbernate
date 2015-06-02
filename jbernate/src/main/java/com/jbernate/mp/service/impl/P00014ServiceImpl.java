package com.jbernate.mp.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.jbernate.cm.bean.WhereBean;
import com.jbernate.cm.bean.WhereBean.Clause;
import com.jbernate.cm.dao.CmDao;
import com.jbernate.cm.service.CmService;
import com.jbernate.cm.util.DateUtil;
import com.jbernate.cm.util.StrUtil;
import com.jbernate.mp.service.P00014Service;
import com.jbernate.mundi.domain.table.RateMgr;

/**
 * 먼디파마 > RATE관리
 */
@Service
@Transactional
public class P00014ServiceImpl implements P00014Service{

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
		wbList.add( new WhereBean( "yyyymm"	, map.get( "searchYyyymm" ).toString().replaceAll( "-", "" ) , Clause.EQ ) );
		if( StrUtil.chkBlank( map.get( "searchRateCd" ) ) ) {
			wbList.add( new WhereBean( "rateCd", map.get( "searchRateCd" ).toString(), Clause.EQ ) );
		}		
		List rList = dao.list( req, new RateMgr(), wbList );
		
		model.addAttribute( "viewData", rList );
		
		// 최종 년월 조회
		String lastYyyymm = DateUtil.dtToStr( new Date(), "yyyyMM" );
		//Date lastYyyymm = new Date();
		List l = cmService.queryList( null, "SELECT MAX( YYYYMM ) FROM RATE_MGR WITH(NOLOCK) WHERE USE_YN = 'Y'" );
		if( l != null && l.size() == 1 ) {
			lastYyyymm = l.get( 0 ).toString();
		}
		//model.addAttribute( "lastYyyymm", DateUtil.dtToStr( DateUtil.strToDt( lastYyyymm, "yyyyMM" ), "yyyy-MM" ) );
		model.addAttribute( "lastYyyymm", DateUtil.dtToStr( DateUtil.strToDt( lastYyyymm, "yyyyMM" ), "yyyy-MM" ) );
		
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
		
		RateMgr rate;
		for( int i = 0; i < list.size(); i++ ) {
			LinkedTreeMap map = (LinkedTreeMap)list.get( i );
			if( map == null ) continue;	// null 값이 넘어오면 Pass
			if( map.get( "CRUD" ).equals( "I" ) ) {
				rate = new RateMgr();
				
				rate.setRateCd( ltMap.get( "rateCd" ).toString() );
				rate.setYyyymm( ltMap.get( "yyyymm" ).toString().replaceAll( "-", "" ) );
				rate.setValue( new BigDecimal( map.get( "value" ).toString() ) );
				rate.setAchiev( new BigDecimal( map.get( "achiev" ).toString() ) );
				rate.setRemk( StrUtil.nvlNull( map.get( "remk" ) ) );
				rate.setUseYn( "Y" );
				
				cmService.create( req, rate );
			} else if( map.get( "CRUD" ).equals( "U" ) ) {
				rate = new RateMgr( new BigDecimal( map.get( "seq" ).toString() ) );
				rate = (RateMgr)cmService.get( req, rate );
				
				rate.setRateCd( map.get( "rateCd" ).toString() );
				rate.setValue( new BigDecimal( map.get( "value" ).toString() ) );
				rate.setAchiev( new BigDecimal( map.get( "achiev" ).toString() ) );
				rate.setRemk( StrUtil.nvlNull( map.get( "remk" ) ) );
				
				cmService.update( req, rate );
			} else if( map.get( "CRUD" ).equals( "D" ) && map.get( "seq" ).toString().indexOf( "-" ) == -1 ) {	// 신규추가( seq가 음수 )는 삭제할 필요도 없음
				rate = new RateMgr( new BigDecimal( map.get( "seq" ).toString() ) );
				rate = (RateMgr)cmService.get( req, rate );
				
				cmService.delete( req, rate );
			}
		}
		
		return model;
	}
}