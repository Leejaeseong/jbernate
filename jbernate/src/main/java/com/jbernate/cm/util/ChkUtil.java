package com.jbernate.cm.util;

import java.util.List;

import javax.servlet.http.HttpSession;

public class ChkUtil {
	
	/**
	 * 널이거나 유효하지 않은 변수, 또는 배열인지 판단
	 * 배열 : 널이거나 배열수가 0이면 false
	 * 문자열 : 널이거나 길이기 0이면 false
	 * @param obj
	 * @return
	 */
	public static boolean chkBlank( Object obj ) {
		if( obj == null ) return false;	// 널이면 유효하지 않음
		
		if( obj instanceof List ) {
			return ArrUtil.chkBlank( (List<?>)obj );
		} else if( obj instanceof String ) {
			return StrUtil.chkBlank( obj );
		} else {
			System.out.println( "정의되지 않은 타입" );
			return false;
		}		
	}
	
	/**
	 * 세션으로 로그인 상태인지 체크
	 * @param session
	 * @return	true 	: 로그인 된 상태
	 * 			fasle 	: 로그인 되지 않은 상태
	 */
	public static boolean chkLogin( HttpSession session ) {
		if( session != null && session.getAttribute( "loginId" ) != null && !session.getAttribute( "loginId" ).equals( "" ) ) return true;
		else return false;
	}
	
}