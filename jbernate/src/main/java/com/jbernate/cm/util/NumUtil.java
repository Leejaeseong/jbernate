package com.jbernate.cm.util;

/**
 * 숫자 관련 공통 유틸
 */
public class NumUtil {

	/**
	 * 공백이나 널이면 0
	 * @param str	대상 문자 객체
	 * @return		null or "" = 0
	 * 				else	: 원래 값, 소수점 아래는 자름 
	 */
	public static int intNumVal( Object str ) {
		if( str == null || str.toString().equals( "" ) ) 	return 0;
		else {
			if( str.toString().indexOf( "." ) != -1 ) {
					return Integer.parseInt( str.toString().substring( 0, str.toString().indexOf( "." ) ) );
			}else	return Integer.parseInt( str.toString() );
		}
	}

}