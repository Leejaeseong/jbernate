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
import com.jbernate.mp.service.P00009Service;
import com.jbernate.mundi.domain.table.TeamMgr;

/**
 * 로그인/아웃 및 세션 관리
 */
@Service
@Transactional
public class P00009ServiceImpl implements P00009Service{

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
		wbList.add( new WhereBean( "teamNm", map.get( "searchTeamNm" ), Clause.LIKEANY ) );
		List rList = dao.list( req, new TeamMgr(), wbList );
		
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
		
		TeamMgr tm;
		for( int i = 0; i < list.size(); i++ ) {
			LinkedTreeMap map = (LinkedTreeMap)list.get( i );
			if( map.get( "CRUD" ).equals( "I" ) ) {
				tm = new TeamMgr();
				tm.setTeamCd( map.get( "teamCd").toString() );
				tm.setTeamNm( StrUtil.nvlNull( map.get( "teamNm") ) );
				tm.setRemk( StrUtil.nvlNull( map.get( "remk") ) );
				tm.setUseYn( "Y" );
				cmService.create( req, tm );
			} else if( map.get( "CRUD" ).equals( "U" ) ) {
				tm = new TeamMgr( new BigDecimal( map.get( "seq").toString() ) );
				tm = (TeamMgr)cmService.get( req, tm );
				tm.setTeamCd( map.get( "teamCd").toString() );
				tm.setTeamNm( StrUtil.nvlNull( map.get( "teamNm") ) );
				tm.setRemk( StrUtil.nvlNull( map.get( "remk") ) );
				cmService.update( req, tm );
			} else if( map.get( "CRUD" ).equals( "D" ) && map.get( "seq" ).toString().indexOf( "-" ) == -1 ) {	// 신규추가( seq가 음수 )는 삭제할 필요도 없음
				tm = new TeamMgr( new BigDecimal( map.get( "seq" ).toString() ) );
				tm.setTeamCd( map.get( "teamCd").toString() );
				tm.setTeamNm( StrUtil.nvlNull( map.get( "teamNm") ) );
				tm.setRemk( StrUtil.nvlNull( map.get( "remk") ) );
				cmService.delete( req, tm );
			}
			System.out.println( "seq = " + map.get( "seq" ) + ", teamCd = " + map.get( "teamCd" ) + ", teamNm = " + map.get( "teamNm" ) + ", CRUD = " + map.get( "CRUD" ) );
		}
		
		return model;
	}
}