package com.jbernate.util;

public class ControllerUtil {
	
	/**
	 * Controller에서 return 하는 view 이름 얻기 
	 * @param clazz		Class 객체
	 * @return			ex) com.jbernate.controller.test.T00002Controller => test/T0002 반환
	 */
	public static String getViewName( Class<?> clazz ) {
		return clazz.getName().substring( clazz.getName().lastIndexOf( ".", clazz.getName().lastIndexOf( "." ) - 1 ) + 1, clazz.getName().lastIndexOf( "." ) ) 
		+ "/" 
		+ clazz.getName().substring( clazz.getName().lastIndexOf( "." ) + 1, clazz.getName().indexOf( "Controller" ) );
	}
}