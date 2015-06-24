package com.jbernate.mp.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
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
import com.jbernate.cm.dao.CmDao;
import com.jbernate.cm.service.CmService;
import com.jbernate.cm.util.NumUtil;
import com.jbernate.cm.util.StrUtil;
import com.jbernate.mp.dao.MpPrcdDao;
import com.jbernate.mp.service.P00017Service;

/**
 * 먼디파마 > 실적 조회
 */
@Service
@Transactional
public class P00017ServiceImpl implements P00017Service{

	@Autowired CmDao dao;
	@Autowired CmService cmService;
	@Autowired MpPrcdDao mpPrcdDao;	
	
	/** 데이터 로드 */
	@Override
	@SuppressWarnings("rawtypes")	
	public Model load( HttpSession sess, HttpServletRequest req, HttpServletResponse res, Model model, String postPayload ) {
		
		Gson gson = new Gson();
		
		LinkedTreeMap map = new LinkedTreeMap();
		map = (LinkedTreeMap) gson.fromJson(postPayload, map.getClass());
		
		/*
		ArrayList wbList = new ArrayList<WhereBean>();
		wbList.add( new WhereBean( "yyyymm"		, map.get( "searchYyyymm" ).toString().replaceAll( "-", "" ) , Clause.EQ ) );
		wbList.add( new WhereBean( "teamSeq"	, map.get( "searchYyyymm" ).toString().replaceAll( "-", "" ) , Clause.EQ ) );
		if( StrUtil.chkBlank( map.get( "searchHosptSeq" ) ) ) {
			wbList.add( new WhereBean( "hosptSeq.seq", new BigDecimal( map.get( "searchHosptSeq" ).toString() ), Clause.EQ ) );
		}
		*/
		
		HashMap<String, List> hm = mpPrcdDao.getResultCol(
					map.get( "searchYyyy" ).toString()
				,	new BigDecimal( map.get( "searchTeamSeq" ).toString() )
				,	( map.get( "searchHosptSeq" ) == null ? "" : StrUtil.trimDot( map.get( "searchHosptSeq" ).toString() ) )
				,	( map.get( "isAdmin" ).toString().equals( "true" ) ? "" : StrUtil.trimDot( map.get( "searchUserSeq" ).toString() ) ) 
		);
		
		model.addAttribute( "cData"	, hm.get( "column" ) );	// 컬럼 헤더 정보
		model.addAttribute( "data"	, hm.get( "data" ) );	// 데이터 정보
		
		return model;
	}
	
}