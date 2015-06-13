package com.jbernate.mp.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
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
import com.jbernate.cm.util.DateUtil;
import com.jbernate.cm.util.StrUtil;
import com.jbernate.mp.service.P00019Service;
import com.jbernate.mundi.domain.table.LogModHistory;

/**
 * 먼디파마 > 변경이력관리
 */
@Service
@Transactional
public class P00019ServiceImpl implements P00019Service{

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
		
		if( StrUtil.chkBlank( map.get( "searchSeq" ) ) ) {
			wbList.add( new WhereBean( "seq", new BigDecimal( map.get( "searchSeq" ).toString() ), Clause.EQ ) );
		}
		
		if( StrUtil.chkBlank( map.get( "searchStYyyymm" ) ) ) {
			List wl = new LinkedList();
			wl.add( DateUtil.strToDt( map.get( "searchStYyyymm" ).toString() + " 00:00:00", "yyyy-MM-dd HH:mm:ss" ) );
			wl.add( DateUtil.strToDt( map.get( "searchEdYyyymm" ).toString() + " 23:59:59", "yyyy-MM-dd HH:mm:ss" ) );
			wbList.add( new WhereBean( "insDt", wl, Clause.BETWEEN ) );
		}
		
		//wbList.add( new WhereBean( "yyyymm"	, map.get( "searchYyyymm" ).toString().replaceAll( "-", "" ) , Clause.EQ ) );
		/*
		wbList.add( new WhereBean( "actualSeq.prdSeq.prdgrpSeq.seq", new BigDecimal( map.get( "searchPrdgrpSeq" ).toString() ), Clause.EQ ) );
		if( StrUtil.chkBlank( map.get( "searchPrdSeq" ) ) ) {
			wbList.add( new WhereBean( "actualSeq.prdSeq.seq", new BigDecimal( map.get( "searchPrdSeq" ).toString() ), Clause.EQ ) );
		}		
		if( StrUtil.chkBlank( map.get( "searchHosptSeq" ) ) ) {
			wbList.add( new WhereBean( "actualSeq.hosptSeq.seq", new BigDecimal( map.get( "searchHosptSeq" ).toString() ), Clause.EQ ) );
		}
		wbList.add( new WhereBean( "userSeq.userNm"			, map.get( "searchUserNm" )		, Clause.LIKEANY ) );
		wbList.add( new WhereBean( "actualSeq.wholesalNm"	, map.get( "searchWholesalNm" )	, Clause.LIKEANY ) );
		wbList.add( new WhereBean( "actualSeq.salLocNm"		, map.get( "searchSalLocNm" )	, Clause.LIKEANY ) );
		wbList.add( new WhereBean( "actualSeq.branchNm"		, map.get( "searchBrnchNm" )	, Clause.LIKEANY ) );
		*/
		
		List rList = dao.list( req, new LogModHistory(), wbList );
		
		model.addAttribute( "viewData", rList );
		
		return model;
	}
	
}