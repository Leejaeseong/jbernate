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
import com.jbernate.cm.util.NumUtil;
import com.jbernate.cm.util.StrUtil;
import com.jbernate.mp.service.P00015Service;
import com.jbernate.mundi.domain.table.GoalMgr;
import com.jbernate.mundi.domain.table.HosptMgr;
import com.jbernate.mundi.domain.table.PrdgrpMgr;
import com.jbernate.mundi.domain.table.TeamMgr;
import com.jbernate.mundi.domain.table.UserMgr;

/**
 * 먼디파마 > 목표 관리
 */
@Service
@Transactional
public class P00015ServiceImpl implements P00015Service{

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
		if( StrUtil.chkBlank( map.get( "searchTeamSeq" ) ) ) {
			wbList.add( new WhereBean( "teamSeq.seq", new BigDecimal( map.get( "searchTeamSeq" ).toString() ), Clause.EQ ) );
		}		
		if( StrUtil.chkBlank( map.get( "searchPrdgrpSeq" ) ) ) {
			wbList.add( new WhereBean( "prdgrpSeq.seq", new BigDecimal( map.get( "searchPrdgrpSeq" ).toString() ), Clause.EQ ) );
		}		
		if( StrUtil.chkBlank( map.get( "searchHosptSeq" ) ) ) {
			wbList.add( new WhereBean( "hosptSeq.seq", new BigDecimal( map.get( "searchHosptSeq" ).toString() ), Clause.EQ ) );
		}
		wbList.add( new WhereBean( "userSeq.userNm"	, map.get( "searchUserNm" ), Clause.LIKEANY ) );
		
		List rList = dao.list( req, new GoalMgr(), wbList );
		
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
		
		GoalMgr goal;
		for( int i = 0; i < list.size(); i++ ) {
			LinkedTreeMap map = (LinkedTreeMap)list.get( i );
			if( map == null ) continue;	// null 값이 넘어오면 Pass
			if( map.get( "CRUD" ).equals( "I" ) ) {
				goal = new GoalMgr();
				
				goal.setYyyy( ltMap.get( "yyyy" ).toString().replaceAll( "-", "" ) );
				
				goal.setTeamSeq( new TeamMgr( new BigDecimal( map.get( "teamSeq" ).toString() ) ) );
				goal.setPrdgrpSeq( 	new PrdgrpMgr( new BigDecimal( map.get( "prdgrpSeq" ).toString() ) ) );
				goal.setHosptSeq( 	new HosptMgr( new BigDecimal( map.get( "hosptSeq" ).toString() ) ) );
				goal.setUserSeq( 	new UserMgr( new BigDecimal( map.get( "userSeq" ).toString() ) ) );
				goal.setMon1( 		new BigDecimal( NumUtil.intNumVal( map.get( "mon1" 	) ) ) );
				goal.setMon2( 		new BigDecimal( NumUtil.intNumVal( map.get( "mon2" 	) ) ) );
				goal.setMon3( 		new BigDecimal( NumUtil.intNumVal( map.get( "mon3" 	) ) ) );
				goal.setMon4( 		new BigDecimal( NumUtil.intNumVal( map.get( "mon4" 	) ) ) );
				goal.setMon5( 		new BigDecimal( NumUtil.intNumVal( map.get( "mon5" 	) ) ) );
				goal.setMon6( 		new BigDecimal( NumUtil.intNumVal( map.get( "mon6" 	) ) ) );
				goal.setMon7( 		new BigDecimal( NumUtil.intNumVal( map.get( "mon7" 	) ) ) );
				goal.setMon8( 		new BigDecimal( NumUtil.intNumVal( map.get( "mon8" 	) ) ) );
				goal.setMon9( 		new BigDecimal( NumUtil.intNumVal( map.get( "mon9" 	) ) ) );
				goal.setMon10( 		new BigDecimal( NumUtil.intNumVal( map.get( "mon10" ) ) ) );
				goal.setMon11( 		new BigDecimal( NumUtil.intNumVal( map.get( "mon11" ) ) ) );
				goal.setMon12(		new BigDecimal( NumUtil.intNumVal( map.get( "mon12" ) ) ) );
				goal.setRemk( StrUtil.nvlNull( map.get( "remk" ) ) );
				goal.setUseYn( "Y" );
				
				cmService.create( req, goal );
			} else if( map.get( "CRUD" ).equals( "U" ) ) {
				goal = new GoalMgr( new BigDecimal( map.get( "seq" ).toString() ) );
				goal = (GoalMgr)cmService.get( req, goal );
				
				goal.setTeamSeq( new TeamMgr( new BigDecimal( map.get( "teamSeq" ).toString() ) ) );
				goal.setPrdgrpSeq( 	new PrdgrpMgr( new BigDecimal( map.get( "prdgrpSeq" ).toString() ) ) );
				goal.setHosptSeq( 	new HosptMgr( new BigDecimal( map.get( "hosptSeq" ).toString() ) ) );
				goal.setUserSeq( 	new UserMgr( new BigDecimal( map.get( "userSeq" ).toString() ) ) );
				goal.setMon1( 		new BigDecimal( NumUtil.intNumVal( map.get( "mon1" 	) ) ) );
				goal.setMon2( 		new BigDecimal( NumUtil.intNumVal( map.get( "mon2" 	) ) ) );
				goal.setMon3( 		new BigDecimal( NumUtil.intNumVal( map.get( "mon3" 	) ) ) );
				goal.setMon4( 		new BigDecimal( NumUtil.intNumVal( map.get( "mon4" 	) ) ) );
				goal.setMon5( 		new BigDecimal( NumUtil.intNumVal( map.get( "mon5" 	) ) ) );
				goal.setMon6( 		new BigDecimal( NumUtil.intNumVal( map.get( "mon6" 	) ) ) );
				goal.setMon7( 		new BigDecimal( NumUtil.intNumVal( map.get( "mon7" 	) ) ) );
				goal.setMon8( 		new BigDecimal( NumUtil.intNumVal( map.get( "mon8" 	) ) ) );
				goal.setMon9( 		new BigDecimal( NumUtil.intNumVal( map.get( "mon9" 	) ) ) );
				goal.setMon10( 		new BigDecimal( NumUtil.intNumVal( map.get( "mon10" ) ) ) );
				goal.setMon11( 		new BigDecimal( NumUtil.intNumVal( map.get( "mon11" ) ) ) );
				goal.setMon12(		new BigDecimal( NumUtil.intNumVal( map.get( "mon12" ) ) ) );
				goal.setRemk( StrUtil.nvlNull( map.get( "remk" ) ) );
				
				cmService.update( req, goal );
			} else if( map.get( "CRUD" ).equals( "D" ) && map.get( "seq" ).toString().indexOf( "-" ) == -1 ) {	// 신규추가( seq가 음수 )는 삭제할 필요도 없음
				goal = new GoalMgr( new BigDecimal( map.get( "seq" ).toString() ) );
				goal = (GoalMgr)cmService.get( req, goal );
				
				cmService.delete( req, goal );
			}
		}
		
		return model;
	}
}