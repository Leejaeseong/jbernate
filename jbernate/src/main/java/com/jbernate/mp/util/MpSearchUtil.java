package com.jbernate.mp.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jbernate.cm.bean.WhereBean;
import com.jbernate.cm.service.CmService;
import com.jbernate.mundi.domain.table.HosptMgr;
import com.jbernate.mundi.domain.table.PrdMgr;
import com.jbernate.mundi.domain.table.TeamMgr;
import com.jbernate.mundi.domain.table.UserMgr;

/**
 * 상수 정의
 */
public class MpSearchUtil {
	
	/** 팀코드로 팀객체 얻기 */
	public static TeamMgr getOneTeamByCd( HttpServletRequest req, CmService service, String teamCd ) {
		List wbList = new ArrayList();
		wbList.add( new WhereBean( "teamCd", teamCd, WhereBean.Clause.EQ ) );
		List list = service.list( req, new TeamMgr(), wbList );
		return (TeamMgr)list.get( 0 );
	}
	
	/** 병원코드로 병원객체 얻기 */
	public static HosptMgr getOneHosptByCd( HttpServletRequest req, CmService service, String hosptCd ) {
		List wbList = new ArrayList();
		wbList.add( new WhereBean( "hosptCd", hosptCd, WhereBean.Clause.EQ ) );
		List list = service.list( req, new HosptMgr(), wbList );
		return (HosptMgr)list.get( 0 );
	}
	
	/** 제품코드로 제품객체 얻기 */
	public static PrdMgr getOnePrdByCd( HttpServletRequest req, CmService service, String prdCd ) {
		List wbList = new ArrayList();
		wbList.add( new WhereBean( "prdCd", prdCd, WhereBean.Clause.EQ ) );
		List list = service.list( req, new PrdMgr(), wbList );
		return (PrdMgr)list.get( 0 );
	}
	
	/** 사번코드로 사용자객체 얻기 */
	public static UserMgr getOneUserByCd( HttpServletRequest req, CmService service, String empCd ) {
		List wbList = new ArrayList();
		wbList.add( new WhereBean( "empCd", empCd, WhereBean.Clause.EQ ) );
		List list = service.list( req, new UserMgr(), wbList );
		return (UserMgr)list.get( 0 );
	}
	
}