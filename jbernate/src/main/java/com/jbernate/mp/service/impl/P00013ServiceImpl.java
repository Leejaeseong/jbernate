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
import com.jbernate.mp.service.P00013Service;
import com.jbernate.mundi.domain.table.HosptMgr;
import com.jbernate.mundi.domain.table.UserMgr;

/**
 * 먼디파마 > 병원관리
 */
@Service
@Transactional
public class P00013ServiceImpl implements P00013Service{

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
		
		// 리스트박스 목록조회
		if( StrUtil.chkStrEqual( map.get( "searchType" ), "hosptSelectBox" ) ) {
			
		}
		// 병원관리 화면 조회
		else {
			wbList.add( new WhereBean( "hosptNm"	, map.get( "searchHosptNm" )	, Clause.LIKEANY ) );
		}
		
		List rList = dao.list( req, new HosptMgr(), wbList );
		
		model.addAttribute( "viewData", rList );
		
		return model;
	}
	
	/** 데이터 저장 */
	@Override
	@SuppressWarnings("rawtypes")	
	public Model submit( HttpSession sess, HttpServletRequest req, HttpServletResponse res, Model model, String postPayload, String submitType ) {
		
		Gson gson = new Gson();
		
		List list = new ArrayList();
		list=(List) gson.fromJson(postPayload, list.getClass());
		
		HosptMgr hospt;
		for( int i = 0; i < list.size(); i++ ) {
			LinkedTreeMap map = (LinkedTreeMap)list.get( i );
			if( map == null ) continue;	// null 값이 넘어오면 Pass
			if( map.get( "CRUD" ).equals( "I" ) ) {
				hospt = new HosptMgr();
				
				hospt.setHosptCd( map.get( "hosptCd" ).toString() );
				hospt.setHosptNm( map.get( "hosptNm" ).toString() );
				hospt.setAddr( map.get( "addr" ).toString() );
				hospt.setRemk( StrUtil.nvlNull( map.get( "remk" ) ) );
				hospt.setUseYn( "Y" );
				
				cmService.create( req, hospt );
			} else if( map.get( "CRUD" ).equals( "U" ) ) {
				hospt = new HosptMgr( new BigDecimal( map.get( "seq" ).toString() ) );
				hospt = (HosptMgr)cmService.get( req, hospt );
				
				hospt.setHosptCd( map.get( "hosptCd" ).toString() );
				hospt.setHosptNm( map.get( "hosptNm" ).toString() );
				hospt.setAddr( map.get( "addr" ).toString() );
				hospt.setRemk( StrUtil.nvlNull( map.get( "remk" ) ) );
				
				cmService.update( req, hospt );
			} else if( map.get( "CRUD" ).equals( "D" ) && map.get( "seq" ).toString().indexOf( "-" ) == -1 ) {	// 신규추가( seq가 음수 )는 삭제할 필요도 없음
				hospt = new HosptMgr( new BigDecimal( map.get( "seq").toString() ) );
				hospt = (HosptMgr)cmService.get( req, hospt );
				
				cmService.delete( req, hospt );
			}
		}
		
		return model;
	}
}