package com.jbernate.cm.util;

/**
 * 상수 정의
 */
public class ConstantUtil {
	
	// ▣ 컨트롤러 PATH ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣
	/** 컨트롤러 PATH > 테스트 */
	public static final String PATH_CONTROLLER_TEST	= "/tt";
	
	// ▣ 식별자 ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣
	/** 식별자 > DB > Jbernate 본인 객체 */
	public static final String ID_DB_OBJECTOWN			= "jbernate";
	
	/** 식별자 > 페이지 > TEST */
	public static final String ID_PAGE_TEST			= "T";
	
	/** 식별자 > 패키지 prefix */
	public static final String ID_PACKAGE_PREFIX	= "com.jbernate";

	// ▣ 형식 ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣
	/** 형식 > 날짜 */
	public static final String FORMAT_DATE			= "yyyy-MM-dd";
	
	// ▣ 제약 ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣
	/** 제약 > 자바스크립트 Alert 함수 출력 시긴 글 자르기 크기 */
	public static final int LIMIT_JS_ALERT_STRING_CNT	= 1000;
}