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
import com.google.gson.internal.LinkedTreeMap;
import com.jbernate.cm.bean.WhereBean;
import com.jbernate.cm.bean.WhereBean.Clause;
import com.jbernate.cm.dao.CmDao;
import com.jbernate.cm.service.CmService;
import com.jbernate.cm.util.DateUtil;
import com.jbernate.cm.util.NumUtil;
import com.jbernate.cm.util.StrUtil;
import com.jbernate.mp.service.P00016Service;
import com.jbernate.mp.util.MpConstUtil;
import com.jbernate.mp.util.MpSearchUtil;
import com.jbernate.mundi.domain.table.ActualMgr;
import com.jbernate.mundi.domain.table.ConfirmMm;
import com.jbernate.mundi.domain.table.LogModHistory;
import com.jbernate.mundi.domain.table.UserMgr;

/**
 * 먼디파마 > 실적 관리
 */
@Service
@Transactional
public class P00016ServiceImpl implements P00016Service{

	@Autowired CmDao dao;
	@Autowired CmService cmService;
	
	private String modFalseQuery = "UPDATE ACTUAL_MGR SET MOD_YN = 'N' WHERE MOD_YN = 'Y'";
	
	/** 데이터 로드 */
	@Override
	@SuppressWarnings("rawtypes")	
	public Model load( HttpSession sess, HttpServletRequest req, HttpServletResponse res, Model model, String postPayload ) {
		
		Gson gson = new Gson();
		
		LinkedTreeMap map = new LinkedTreeMap();
		map = (LinkedTreeMap) gson.fromJson(postPayload, map.getClass());
		
		ArrayList wbList = new ArrayList<WhereBean>();
		wbList.add( new WhereBean( "yyyymm"	, map.get( "searchYyyymm" ).toString().replaceAll( "-", "" ) , Clause.EQ ) );
		wbList.add( new WhereBean( "prdSeq.prdgrpSeq.seq", new BigDecimal( map.get( "searchPrdgrpSeq" ).toString() ), Clause.EQ ) );
		if( StrUtil.chkBlank( map.get( "searchPrdSeq" ) ) ) {
			wbList.add( new WhereBean( "prdSeq.seq", new BigDecimal( map.get( "searchPrdSeq" ).toString() ), Clause.EQ ) );
		}		
		if( StrUtil.chkBlank( map.get( "searchHosptSeq" ) ) ) {
			wbList.add( new WhereBean( "hosptSeq.seq", new BigDecimal( map.get( "searchHosptSeq" ).toString() ), Clause.EQ ) );
		}
		wbList.add( new WhereBean( "userSeq.userNm"	, map.get( "searchUserNm" )		, Clause.LIKEANY ) );
		wbList.add( new WhereBean( "wholesalNm"		, map.get( "searchWholesalNm" )	, Clause.LIKEANY ) );
		wbList.add( new WhereBean( "salLocNm"		, map.get( "searchSalLocNm" )	, Clause.LIKEANY ) );
		wbList.add( new WhereBean( "branchNm"		, map.get( "searchBranchNm" )	, Clause.LIKEANY ) );
		
		List rList = dao.list( req, new ActualMgr(), wbList );
		
		model.addAttribute( "viewData", rList );
		
		// 최종 년월 조회
		String lastYyyymm = DateUtil.dtToStr( new Date(), "yyyyMM" );
		List l = cmService.queryList( null, "SELECT MAX( YYYYMM ) FROM ACTUAL_MGR WITH(NOLOCK) WHERE USE_YN = 'Y'" );
		if( l != null && l.size() == 1 ) {
			lastYyyymm = l.get( 0 ).toString();
		}
		model.addAttribute( "lastYyyymm", DateUtil.dtToStr( DateUtil.strToDt( lastYyyymm, "yyyyMM" ), "yyyy-MM" ) );
		
		wbList = new ArrayList<WhereBean>();
		wbList.add( new WhereBean( "yyyymm", map.get( "searchYyyymm" ).toString().replaceAll( "-", "" ), Clause.EQ ) );
		// 확정 여부 조회
		model.addAttribute( "confirmMm", dao.list( req, new ConfirmMm(), wbList ) );
		
		return model;
	}
	
	/** Submit */
	@Override
	@SuppressWarnings("rawtypes")	
	public Model submit( HttpSession sess, HttpServletRequest req, HttpServletResponse res, Model model, String postPayload, String submitType ) {
		
		Gson gson = new Gson();
		LinkedTreeMap ltMap = (LinkedTreeMap)gson.fromJson(postPayload, new Object().getClass());
		
		if( ltMap.get( "submitType" ).equals( "save" ) ) {				// 저장
			this.save(sess, req, res, model, postPayload, submitType );
		} else if( ltMap.get( "submitType" ).equals( "pause" ) ) {		// PAUSE 처리
			this.pause(sess, req, res, model, postPayload, submitType );
		} else if( ltMap.get( "submitType" ).equals( "confirm" ) ) {	// 확정 처리
			this.confirm(sess, req, res, model, postPayload, submitType );
		}
		
		return model;
	}
	
	/** 데이터 저장 */
	public Model save( HttpSession sess, HttpServletRequest req, HttpServletResponse res, Model model, String postPayload, String submitType ) {
		Gson gson = new Gson();
		LinkedTreeMap ltMap = (LinkedTreeMap)gson.fromJson(postPayload, new Object().getClass());
		
		List list = new ArrayList();
		list=(List)gson.fromJson(ltMap.get( "saveData" ).toString(), list.getClass());
		
		ActualMgr actual;
		LogModHistory history;
		for( int i = 0; i < list.size(); i++ ) {
			LinkedTreeMap map = (LinkedTreeMap)list.get( i );
			if( map == null ) continue;	// null 값이 넘어오면 Pass
			if( map.get( "CRUD" ).equals( "I" ) ) {
				actual = new ActualMgr();
				
				actual.setWholesalNm( StrUtil.nvlNull( map.get( "remk" ) ) );
				actual.setYyyymm( ltMap.get( "yyyymm" ).toString().replaceAll( "-", "" ) );
				//actual.setPrdSeq( 	new PrdMgr( new BigDecimal( map.get( "prdSeq" ).toString() ) ) );
				actual.setPrdSeq( MpSearchUtil.getOnePrdByCd( req, cmService, map.get( "prdSeq" ).toString() ) );
				//actual.setHosptSeq( 	new HosptMgr( new BigDecimal( map.get( "hosptSeq" ).toString() ) ) );
				actual.setHosptSeq( MpSearchUtil.getOneHosptByCd( req, cmService, map.get( "hosptSeq" ).toString() ) );
				//actual.setUserSeq( 	new UserMgr( new BigDecimal( map.get( "userSeq" ).toString() ) ) );
				actual.setUserSeq( MpSearchUtil.getOneUserByCd( req, cmService, map.get( "userSeq" ).toString() ) );
				actual.setSalLocNm( StrUtil.nvlNull( map.get( "salLocNm" ) ) );
				actual.setAddr( StrUtil.nvlNull( map.get( "addr" ) ) );
				actual.setZipCd( StrUtil.nvlNull( map.get( "zipCd" ) ) );
				actual.setSalCnt( new BigDecimal( NumUtil.intNumVal( map.get( "salCnt" ) ) ) );
				actual.setUnitPrc( new BigDecimal( NumUtil.intNumVal( map.get( "unitPrc" ) ) ) );
				actual.setSalAmt( new BigDecimal( NumUtil.intNumVal( map.get( "salAmt" ) ) ) );
				actual.setBranchNm( StrUtil.nvlNull( map.get( "branchNm" ) ) );
				actual.setModYn( "Y" );
				actual.setRemk( StrUtil.nvlNull( map.get( "remk" ) ) );
				actual.setUseYn( "Y" );
				
				cmService.create( req, actual );
			} else if( map.get( "CRUD" ).equals( "U" ) ) {
				actual = new ActualMgr( new BigDecimal( map.get( "seq" ).toString() ) );
				actual = (ActualMgr)cmService.get( req, actual );
				
				actual.setWholesalNm( StrUtil.nvlNull( map.get( "wholesalNm" ) ) );
				actual.setYyyymm( ltMap.get( "yyyymm" ).toString().replaceAll( "-", "" ) );
				//actual.setPrdSeq( 	new PrdMgr( new BigDecimal( map.get( "prdSeq" ).toString() ) ) );
				actual.setPrdSeq( MpSearchUtil.getOnePrdByCd( req, cmService, map.get( "prdSeq" ).toString() ) );
				//actual.setHosptSeq( 	new HosptMgr( new BigDecimal( map.get( "hosptSeq" ).toString() ) ) );
				actual.setHosptSeq( MpSearchUtil.getOneHosptByCd( req, cmService, map.get( "hosptSeq" ).toString() ) );
				//actual.setUserSeq( 	new UserMgr( new BigDecimal( map.get( "userSeq" ).toString() ) ) );
				actual.setSalLocNm( StrUtil.nvlNull( map.get( "salLocNm" ) ) );
				actual.setAddr( StrUtil.nvlNull( map.get( "addr" ) ) );
				actual.setZipCd( StrUtil.nvlNull( map.get( "zipCd" ) ) );
				actual.setSalCnt( new BigDecimal( NumUtil.intNumVal( map.get( "salCnt" ) ) ) );
				actual.setUnitPrc( new BigDecimal( NumUtil.intNumVal( map.get( "unitPrc" ) ) ) );
				actual.setSalAmt( new BigDecimal( NumUtil.intNumVal( map.get( "salAmt" ) ) ) );
				actual.setBranchNm( StrUtil.nvlNull( map.get( "branchNm" ) ) );
				actual.setModYn( StrUtil.nvlNull( map.get( "modYn" ) ) );
				actual.setRemk( StrUtil.nvlNull( map.get( "remk" ) ) );
				cmService.update( req, actual );
				
				// 변경 로그 기록
				history = new LogModHistory();
				history.setActualSeq( new ActualMgr( new BigDecimal( map.get( "seq" ).toString() ) ) );
				history.setUserSeq( 	new UserMgr( new BigDecimal( map.get( "userSeq" ).toString() ) ) );
				history.setUseYn( "Y" );
				if( StrUtil.chkStrIn( sess.getAttribute( "roleCd" ).toString()
						, MpConstUtil.MP_ROLE_ADMIN		// 관리자 계정	 
						, MpConstUtil.MP_ROLE_DEVELOP	// 개발 계정	 
					) ) {
					history.setRemk( "관리자" );
				}
				cmService.create( req, history );
			} else if( map.get( "CRUD" ).equals( "D" ) && map.get( "seq" ).toString().indexOf( "-" ) == -1 ) {	// 신규추가( seq가 음수 )는 삭제할 필요도 없음
				actual = new ActualMgr( new BigDecimal( map.get( "seq" ).toString() ) );
				actual = (ActualMgr)cmService.get( req, actual );
				
				cmService.delete( req, actual );
			}
		}
		
		return model;
	}
	
	/** PAUSE 처리 */
	public Model pause( HttpSession sess, HttpServletRequest req, HttpServletResponse res, Model model, String postPayload, String submitType ) {
		// 수정가능 여부 전부 FALSE
		cmService.execQuery( req, modFalseQuery );
		return model;
	}
	
	/** 확정 처리 */
	public Model confirm( HttpSession sess, HttpServletRequest req, HttpServletResponse res, Model model, String postPayload, String submitType ) {
		Gson gson = new Gson();
		LinkedTreeMap ltMap = (LinkedTreeMap)gson.fromJson(postPayload, new Object().getClass());
		
		// 수정가능 여부 전부 FALSE
		cmService.execQuery( req, modFalseQuery );
		
		// 확정 테이블 데이터 삽입
		ConfirmMm confirmMm = new ConfirmMm();
		confirmMm.setYyyymm( ltMap.get( "yyyymm" ).toString().replaceAll( "-", "" ) );
		confirmMm.setUseYn( "Y" );		
		cmService.create( req, confirmMm );
		
		// 확정 처리 프로시저 실행
		
		
		return model;
	}
	
}