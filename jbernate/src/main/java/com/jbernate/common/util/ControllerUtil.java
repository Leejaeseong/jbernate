package com.jbernate.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller에서 필요한 Util
 */
public class ControllerUtil {
	
	/**
	 * Controller에서 return 하는 view 이름 얻기 
	 * @param clazz		Class 객체
	 * @return			ex) com.jbernate.test.controller.T00002Controller => test/T0002 반환
	 */
	public static String getViewName( Class<?> clazz ) {
		String[] arr = clazz.getName().split( "\\." );
		return arr[ 2 ] + "/" + arr[ 4 ].substring( 0, arr[ 4 ].indexOf( "Controller" ) );
	}
	
	/**
	 * URL full path중 return 하는 view 이름 얻기
	 * 예를들어 /test/T00003/load 처럼 /domain/{pageID}/명령어 로 들어오는 방식일 때 처리
	 * @param request	HttpSerbletRequest	
	 * @return String	ex) /aaa/bbb/ccc/load => /bbb/ccc
	 */
	public static String getViewName( HttpServletRequest request ) {
		String[] arr = request.getRequestURI().split( "/" );
		return arr[ arr.length - 3 ] + "/" + arr[ arr.length - 2 ];
	}
}