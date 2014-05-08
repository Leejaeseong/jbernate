package com.jbernate.util;

import javax.management.ReflectionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {

	public static void trace( String msg ) {	LoggerUtil util = new LoggerUtil();	Logger logger = util.getLogger();	if( logger != null ) logger.trace( msg );	}
	public static void debug( String msg ) {	LoggerUtil util = new LoggerUtil();	Logger logger = util.getLogger();	if( logger != null ) logger.debug( msg );	}
	public static void info( String msg ) {		LoggerUtil util = new LoggerUtil();	Logger logger = util.getLogger();	if( logger != null ) logger.info( msg );	}
	public static void warn( String msg ) {		LoggerUtil util = new LoggerUtil();	Logger logger = util.getLogger();	if( logger != null ) logger.warn( msg );	}
	public static void error( String msg ) {	LoggerUtil util = new LoggerUtil();	Logger logger = util.getLogger();	if( logger != null ) logger.error( msg );	}
	
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
}