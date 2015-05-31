package com.jbernate.cm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date 관련 공통 유틸
 */
public class DateUtil {

	/**
	 * 문자와 날짜 형식을 받아 Date로 변환
	 * @param val	날짜 문자열
	 * @param form	날짜 형식( ex. yyyy-MM-dd HH:mm:ss )
	 * @return Date, 입력 오류인 경우 현재값을 반환
	 */
	public static Date strToDt( String val, String form ) {
		Date rDate = new Date();
		
		try {
			SimpleDateFormat transFormat = new SimpleDateFormat( form );
			rDate = transFormat.parse( val );
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return rDate;
	}
	
	/**
	 * 날짜와 형식을 받아 문자열로 변환
	 * @param val	Date
	 * @param form	날짜 형식( ex. yyyy-MM-dd HH:mm:ss )
	 * @return String, 입력 오류인 경우 공백을 반환
	 */
	public static String dtToStr( Date val, String form ) {
		String rStr = "";
		
		SimpleDateFormat transFormat = new SimpleDateFormat( form );
		rStr = transFormat.format( val );
		
		return rStr;
	}
}