package com.jbernate.cm.util;

import javax.management.ReflectionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logger 관련 필요 Util
 */
public class LogUtil {

	private static final String preMsg 	= "▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷▷";
	
	public static void trace( String msg ) {	LogUtil util = new LogUtil();	Logger logger = util.getLogger();	if( logger != null ) logger.trace( LogUtil.concatMsg( msg ) );	}
	public static void debug( String msg ) {	LogUtil util = new LogUtil();	Logger logger = util.getLogger();	if( logger != null ) logger.debug( LogUtil.concatMsg( msg ) );	}
	public static void info( String msg ) {		LogUtil util = new LogUtil();	Logger logger = util.getLogger();	if( logger != null ) logger.info( LogUtil.concatMsg( msg ) );	}
	public static void warn( String msg ) {		LogUtil util = new LogUtil();	Logger logger = util.getLogger();	if( logger != null ) logger.warn( LogUtil.concatMsg( msg ) );	}
	public static void error( String msg ) {	LogUtil util = new LogUtil();	Logger logger = util.getLogger();	if( logger != null ) logger.error( LogUtil.concatMsg( msg ) );	}
	public static void print( Object msg ) {	LogUtil util = new LogUtil();	Logger logger = util.getLogger();	if( logger != null ) System.out.print( preMsg + msg );			}
	public static void println( Object msg ) {	LogUtil util = new LogUtil();	Logger logger = util.getLogger();	if( logger != null ) System.out.println( preMsg + msg );		}
	
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