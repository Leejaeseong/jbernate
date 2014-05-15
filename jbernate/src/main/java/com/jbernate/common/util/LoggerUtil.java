package com.jbernate.common.util;

import javax.management.ReflectionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logger 관련 필요 Util
 */
public class LoggerUtil {

	private static final String preMsg 	= "▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷";
	
	public static void trace( String msg ) {	LoggerUtil util = new LoggerUtil();	Logger logger = util.getLogger();	if( logger != null ) logger.trace( LoggerUtil.concatMsg( msg ) );	}
	public static void debug( String msg ) {	LoggerUtil util = new LoggerUtil();	Logger logger = util.getLogger();	if( logger != null ) logger.debug( LoggerUtil.concatMsg( msg ) );	}
	public static void info( String msg ) {		LoggerUtil util = new LoggerUtil();	Logger logger = util.getLogger();	if( logger != null ) logger.info( LoggerUtil.concatMsg( msg ) );	}
	public static void warn( String msg ) {		LoggerUtil util = new LoggerUtil();	Logger logger = util.getLogger();	if( logger != null ) logger.warn( LoggerUtil.concatMsg( msg ) );	}
	public static void error( String msg ) {	LoggerUtil util = new LoggerUtil();	Logger logger = util.getLogger();	if( logger != null ) logger.error( LoggerUtil.concatMsg( msg ) );	}
	
	/**
	 * Caller Class이름으로 Logger 얻기
	 * @return Logger
	 */
	public Logger getLogger() {
		Logger logger = null;
		StackTraceElement[] stackTrace 	= Thread.currentThread().getStackTrace();
		
		if( stackTrace.length >= 3 ) {
			StackTraceElement e 			= stackTrace[ 3 ];			
			logger = LoggerFactory.getLogger( e.getClassName() );
		}
		
		return logger;
	}
	
	/**
	 * 로그 콘솔 창이 잘 보이도록 메시지 앞에 특수 문자를 넣음 
	 * @param msg	출력할 메시지 내용
	 * @return		특수 문자가 앞에 붙은 메시지 내용
	 */
	private static String concatMsg( String msg ) {
		return preMsg + msg;
	}
}