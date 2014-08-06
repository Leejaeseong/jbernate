package com.jbernate.cm.util;

import java.util.List;

public class ArrUtil {
	
	/**
	 * null이거나 size가 0인지 확인
	 * @param list	확인할 목록
	 * @return		true 	: 내용이 있음
	 * 				false 	: null이거나 size가 0임
	 */
	public static boolean chkBlank( List<?> list ) {
		if( list == null || list.size() == 0 )	return false;
		else 		return true;
	}
}