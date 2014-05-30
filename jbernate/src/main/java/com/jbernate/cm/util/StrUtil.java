package com.jbernate.cm.util;

/**
 * String 관련 공통 유틸
 */
public class StrUtil {

	/**
	 * 공백이나 널인지 여부
	 * @param str	대상 문자 객체
	 * @return		true	: 문자 존재
	 * 				false	: 공백이나 null 
	 */
	public static boolean chkBlank( Object str ) {
		if( str == null || str.toString().equals( "" ) ) 	return false;
		else												return true;
	}
	
	/**
	 * 널이면 "" 으로 치환
	 * @param str	대상 문자
	 * @return		null 일 경우 "" 으로 치환
	 */
	public static String nvl( String str ) {
		if( str == null ) 	return "";
		else				return str;
	}
	
	/**
	 * 단어의 첫문자만 소문자로 치환
	 * @param str	대상 문자
	 * @return		단어의 첫문자만 소문자로 치환
	 */
	public static String firstLowStr( String str ) {
		if( str == null || str.length() < 1 ) 	return "";
		else									return str.substring( 0, 1 ).toLowerCase() + str.substring( 1 );
	}
	/**
	 * 단어의 첫문자만 대문자로 치환
	 * @param str	대상 문자
	 * @return		단어의 첫문자만 대문자로 치환
	 */
	public static String firstUpperStr( String str ) {
		if( str == null || str.length() < 1 ) 	return "";
		else									return str.substring( 0, 1 ).toUpperCase() + str.substring( 1 );
	}

	/**
	 * 자바 변수명 규칙으로 변환
	 * @param str	변환할 대상 문자열
	 * @param delim	구분자
	 * @return		ex1 : delim = "_" ) "aa_bb_cc" 	=> "aaBbCc"
	 * 				ex2 : delim = "_" ) "aa_bb_"	=> "aaBb"
	 * 				ex3 : delim = "_" ) "_"			=> ""
	 */
	public static String makeJavaNameRule( String str, String delim ) {
		if( !chkBlank( str ) ) return str;
		// 끝에 delim으로 종료되면 delim 부분 제거
		if( str.length() > delim.length() && str.endsWith( delim ) )	str = str.substring( 0, str.lastIndexOf( delim ) );
		
		String rVal = "";
		int tInt	= 0;
		int arrLen 	= str.split( delim ).length;
		for( int i = 0; i < arrLen; i++ ) {
			int tBeforeTInt = tInt;
			tInt = str.indexOf( delim, tBeforeTInt + 1 );
			if( str.length() > tInt + 1 ) {	// 문자열 마지막이 구분자로 끝나지 않는다면
				if( i + 1 == arrLen ) {	// 마지막 부분이라면
					if( i == 0 )	rVal += str;
					else			rVal += firstUpperStr( str.substring( tBeforeTInt + 1) );
				}else {
					if( i == 0 )	rVal += str.substring( tBeforeTInt, tInt );
					else			rVal += firstUpperStr( str.substring( tBeforeTInt + 1, tInt ) );
				}
			}
		}
		return rVal;		
	}
	/**
	 * 자바 변수명 규칙으로 변환
	 * @param str	대상 문자열
	 * @return		ex1 : delim = "_" ) "aa_bb_cc" 	=> "aaBbCc"
	 * 				ex2 : delim = "_" ) "aa_bb_"	=> "aaBb"
	 * 				ex3 : delim = "_" ) "_"			=> ""
	 */
	public static String makeJavaNameRule( String str ) {
		return makeJavaNameRule( str, "_" );
	}
	
	/**
	 * 긴 글 자르기
	 * @param str	대상 문자열
	 * @param size	글자 제한 수
	 * @return		잘려진 글자
	 */
	public static String cutString( String str, int size ) {
		if( !chkBlank( str ) || str.length() <= size ) 	return str;
		else											return str.substring( 0, size );
	}
}