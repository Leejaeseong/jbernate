package com.jbernate.cm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.jbernate.cm.bean.WhereBean;
import com.jbernate.cm.bean.WhereBean.Clause;
import com.jbernate.cm.dao.CmDao;
import com.jbernate.cm.service.LoginService;
import com.jbernate.cm.util.ArrUtil;
import com.jbernate.cm.util.ConstUtil;
import com.jbernate.cm.util.SecuUtil;
import com.jbernate.mundi.domain.table.UserMgr;

/**
 * 로그인/아웃 및 세션 관리
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService{

	@Autowired CmDao dao;
	
	/**
	 * 세션 체크 및 성공 시 세션 저장
	 * @param request	HttpServletRequest
	 * @return			String : 0 = 실패, 1 = 성공
	 */
	@Override
	@SuppressWarnings("rawtypes")	
	public Model submit( HttpSession sess, HttpServletRequest req, HttpServletResponse res, Model model, String postPayload, String submitType ) {
		String loginId 	= req.getParameter( "loginId" 	);
		String pwd 		= req.getParameter( "pwd" 		);
		String chkRemId	= req.getParameter( "cbRemId" 	);
		
		ArrayList wbList = new ArrayList<WhereBean>();
		wbList.add( new WhereBean( "loginId", loginId, Clause.EQ ) );
		wbList.add( new WhereBean( "loginPwd", SecuUtil.getSha256( pwd ), Clause.EQ ) );
		List rList = dao.list( req, new UserMgr(), wbList );
		
		if( ArrUtil.chkBlank( rList ) ) {	// 로그인 성공
			UserMgr user = (UserMgr)rList.get( 0 );
			
			// 세션에 정보 저장
			sess.setAttribute( "roleCd"	, user.getRoleCd() );
			sess.setAttribute( "teamNm"	, user.getTeamSeq().getTeamNm() );
			sess.setAttribute( "loginId", user.getLoginId() );
			sess.setAttribute( "userSeq", user.getSeq() 	);
			sess.setAttribute( "userNm"	, user.getUserNm() 	);
			
			// cookie 정보
			Cookie cookie;
			if( chkRemId != null ) {	// 아이디 저장 설정
				cookie = new Cookie("loginSaveId", loginId );
				res.addCookie( cookie );
			}else {						// 아이디 저장 해제
				cookie = new Cookie("loginSaveId", "" );
			}
			cookie.setMaxAge( 60 * 60 * 24 * 365 );
			res.addCookie( cookie );
			model.addAttribute( ConstUtil.FORMAT_MODEL_SUCCESS, true );
		}else {
			model.addAttribute( ConstUtil.FORMAT_MODEL_SUCCESS, false );
		}
		
		return model;
	}
}