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
import com.jbernate.cm.util.DateUtil;
import com.jbernate.cm.util.SecuUtil;
import com.jbernate.cm.util.StrUtil;
import com.jbernate.mp.service.P00010Service;
import com.jbernate.mp.util.MpConstUtil;
import com.jbernate.mundi.domain.table.TeamMgr;
import com.jbernate.mundi.domain.table.UserMgr;

/**
 * 먼디파마 > 사용자관리
 */
@Service
@Transactional
public class P00010ServiceImpl implements P00010Service{

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
		wbList.add( new WhereBean( "userNm"	, map.get( "searchUserNm" )	, Clause.LIKEANY ) );
		wbList.add( new WhereBean( "loginId", map.get( "searchLoginId" ), Clause.LIKEANY ) );
		if( StrUtil.chkBlank( map.get( "searchTeam" ) ) ) {
			wbList.add( new WhereBean( "teamSeq.seq", new BigDecimal( map.get( "searchTeam" ).toString() ), Clause.EQ ) );
		}
		List rList = dao.list( req, new UserMgr(), wbList );
		
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
		
		UserMgr user;
		for( int i = 0; i < list.size(); i++ ) {
			LinkedTreeMap map = (LinkedTreeMap)list.get( i );
			if( map.get( "CRUD" ).equals( "I" ) ) {
				user = new UserMgr();
				
				user.setTeamSeq( new TeamMgr( new BigDecimal( map.get( "teamSeq" ).toString() ) ) );
				user.setRoleCd( MpConstUtil.MP_ROLE_EMPLOYEE );	// 사용자 권한
				user.setLoginId( map.get( "loginId" ).toString() );
				user.setLoginPwd( SecuUtil.getSha256( map.get( "loginPwd" ).toString() ) );
				user.setUserNm( map.get( "userNm" ).toString() );
				user.setWrkStDt( DateUtil.strToDt( map.get( "wrkStDt" ).toString(), "yyyy-MM-dd" ) );
				user.setWrkRegion( StrUtil.nvlNull( map.get( "wrkRegion" ) ) );
				user.setContact( StrUtil.nvlNull( map.get( "contact" ) ) );
				user.setRemk( StrUtil.nvlNull( map.get( "remk" ) ) );
				user.setUseYn( "Y" );
				
				cmService.create( req,user );
			} else if( map.get( "CRUD" ).equals( "U" ) ) {
				user = new UserMgr( new BigDecimal( map.get( "seq").toString() ) );
				user = (UserMgr)cmService.get( req, user );
				
				user.setTeamSeq( new TeamMgr( new BigDecimal( map.get( "teamSeq" ).toString() ) ) );
				if( map.get( "loginPwd" ) != null ) { user.setLoginPwd( SecuUtil.getSha256( map.get( "loginPwd" ).toString() ) ); }
				user.setUserNm( map.get( "userNm" ).toString() );
				user.setWrkStDt( DateUtil.strToDt( map.get( "wrkStDt" ).toString(), "yyyy-MM-dd" ) );
				user.setWrkRegion( StrUtil.nvlNull( map.get( "wrkRegion" ) ) );
				user.setContact( StrUtil.nvlNull( map.get( "contact" ) ) );
				user.setRemk( StrUtil.nvlNull( map.get( "remk" ) ) );
				
				cmService.update( req, user );
			} else if( map.get( "CRUD" ).equals( "D" ) && map.get( "seq" ).toString().indexOf( "-" ) == -1 ) {	// 신규추가( seq가 음수 )는 삭제할 필요도 없음
				user = new UserMgr( new BigDecimal( map.get( "seq").toString() ) );
				user = (UserMgr)cmService.get( req, user );
				
				cmService.delete( req, user );
			}
		}
		
		return model;
	}
}