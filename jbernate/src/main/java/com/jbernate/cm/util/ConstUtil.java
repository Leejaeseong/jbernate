package com.jbernate.cm.util;

/**
 * 상수 정의
 */
public class ConstUtil {
	
	// ▣ 컨트롤러 PATH ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣
	/** 컨트롤러 PATH > 테스트 */
	public static final String PATH_CONTROLLER_TEST	= "/tt";
	
	/** 컨트롤러 PATH > 공통 */
	public static final String PATH_CONTROLLER_CM	= "/cm";
	
	// ▣ 식별자 ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣
	/** 식별자 > DB > Jbernate 본인 객체 */
	public static final String ID_DB_OBJECTOWN			= "jbernate";
	
	/** 식별자 > 페이지 > prefix */
	public static final String ID_PAGE_PREFIX			= "P";
	
	/** 식별자 > 패키지 > prefix */
	public static final String ID_PACKAGE_PREFIX		= "com.jbernate";

	// ▣ 형식 ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣
	/** 형식 > 날짜 */
	public static final String FORMAT_DATE						= "yyyy-MM-dd";
	
	/** 형식 > 페이지 길이 */
	public static final int FORMAT_PAGE_LEN						= 6; // ex) P12345
	
	/** 형식 > 컨트롤러 명령어 > 목록 */
	public static final String FORMAT_CONTROLLER_COMMAND_LIST	= "list";
	
	/** 형식 > 컨트롤러 명령어 > 페이지 로딩 */
	public static final String FORMAT_CONTROLLER_COMMAND_LOAD	= "load";
	
	/** 형식 > 컨트롤러 명령어 > 전송( submit ) */
	public static final String FORMAT_CONTROLLER_COMMAND_SUBMIT	= "submit";
	
	/** 형식 > Model > return type */
	public static final String FORMAT_MODEL_SUCCESS = "SUCCESS";
	
	// ▣ 제약 ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣
	/** 제약 > 자바스크립트 Alert 함수 출력 시 긴 글 자르기 크기 */
	public static final int LIMIT_JS_ALERT_STRING_CNT	= 1000;
	
	/** 제약 > Loop 횟수 Max값 limit */
	public static final int LIMIT_LOOP_CNT	= 100;
}