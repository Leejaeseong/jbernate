package com.jbernate.cm.util;

import java.util.List;

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
	
}