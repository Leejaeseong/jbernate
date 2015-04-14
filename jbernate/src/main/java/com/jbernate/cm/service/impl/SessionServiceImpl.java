package com.jbernate.cm.service.impl;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.jbernate.cm.bean.WhereBean;
import com.jbernate.cm.bean.WhereBean.Clause;
import com.jbernate.cm.dao.CmDao;
import com.jbernate.cm.service.SessionService;
import com.jbernate.cm.util.SecuUtil;

/**
 * 로그인/아웃 및 세션 관리
 */
@Service
@Transactional
public class SessionServiceImpl implements SessionService{

	@Autowired CmDao dao;
	
	/**
	 * 세션 체크 및 성공 시 세션 저장
	 * @param request	HttpServletRequest
	 * @return			String : 0 = 실패, 1 = 성공
	 */
	@Override
	@SuppressWarnings("rawtypes")	
	public Model submit( HttpSession sess, HttpServletRequest req, Model model, String submitType ) {
		String id = req.getParameter( "id" );
		String pw = req.getParameter( "pw" );
		
		ArrayList wbList = new ArrayList<WhereBean>();
		wbList.add( new WhereBean( "loginId", id, Clause.EQ ) );
		wbList.add( new WhereBean( "pwd", SecuUtil.getSha256( pw ), Clause.EQ ) );
		wbList.add( new WhereBean( "accStDt", new Date(), Clause.LE ) );
		wbList.add( new WhereBean( "accEdDt", new Date(), Clause.GE ) );
		wbList.add( new WhereBean( "useYn", "1", Clause.EQ ) );
				
		/*
		List rList = dao.list( req, new CmUserMgr(), wbList );
		if( ArrUtil.chkBlank( rList ) ) {	// 로그인 성공
			CmUserMgr user = (CmUserMgr)rList.get( 0 );
			
			// 세션에 정보 저장
			sess.setAttribute( "roleSeq", user.getRoleSeq() );
			sess.setAttribute( "coCd"	, user.getCoCd() 	);
			sess.setAttribute( "loginId", user.getLoginId() );
			sess.setAttribute( "pwdExDt", user.getPwdExDt() );
			sess.setAttribute( "userNm"	, user.getUserNm() 	);
			sess.setAttribute( "accStDt", user.getAccStDt() );
			sess.setAttribute( "accEdDt", user.getAccEdDt() );
			sess.setAttribute( "useYn"	, user.getUseYn() 	);
			
			model.addAttribute( ConstUtil.FORMAT_MODEL_SUCCESS, true );
		}else {
			model.addAttribute( ConstUtil.FORMAT_MODEL_SUCCESS, false );
		}
		*/
		
		return model;
	}
}