package com.jbernate.cm.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller에서 필요한 Util
 */
public class ControllerUtil {
	
	/** Deprecated : getViewName( HttpServletRequest request ) 로 통일, request로 하면 해당 클래스에서 직접호출하지 않고 다른 클래스에서 해당 클래스를 호출하는 경우에도 정합성이 보장되므로 이것보다 나음
	 * Controller에서 return 하는 view 이름 얻기 
	 * @param clazz		Class 객체
	 * @return			ex) com.jbernate.tt.controller.T00002Controller => tt/T0002 반환
	public static String getViewName( Class<?> clazz ) {
		String[] arr = clazz.getName().split( "\\." );
		return arr[ 2 ] + "/" + arr[ 4 ].substring( 0, arr[ 4 ].indexOf( "Controller" ) );
	}
	 */
	
	/**
	 * URL full path중 return 하는 view 이름 얻기
	 * 예를들어 /tt/00003/load 처럼 /domain/{pageID}/{명령어} 로 들어오는 방식일 때 처리
	 * @param request	HttpSerbletRequest	
	 * @return String	ex) /aaa/bbb/ccc/load => bbb/ccc
	 */
	public static String getViewName( HttpServletRequest request ) {
		String[] arr = request.getRequestURI().split( "/" );
		return arr[ arr.length - 3 ] + "/" + arr[ arr.length - 2 ];
	}
	
	/**
	 * URL full path를 사용하여 full class path 추출 
	 * @param request	HttpServletRequest
	 * @param domain	영역에 해당하는 패키지명 아래 예시에서는 service 라고 가정
	 * @return String	ex) /tt/P00005/load => com.jbernate.tt.service.P00005
	 */
	public static String getClassPathByUrl( HttpServletRequest request, String domain ) {
		String[] arr = request.getRequestURI().split( "/" );
		return ConstUtil.ID_PACKAGE_PREFIX + "." + arr[ arr.length - 3 ] + "." + domain + "." + arr[ arr.length - 2 ];
	}
}