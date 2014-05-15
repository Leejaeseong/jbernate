package com.jbernate.common.util;

/**
 * String 관련 공통 유틸
 */
public class StringUtil {

	/**
	 * 단어의 첫문자만 소문자로 치환
	 * @param str
	 * @return
	 */
	public static String firstLowStr( String str ) {
		if( str == null || str.length() < 1 ) 	return "";
		else									return str.substring( 0, 1 ).toLowerCase() + str.substring( 1 );
	}
	
}