package com.jbernate.cm.util.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectUtil {

	/**
	 * 모든 getter 목록  반환
	 * 
	 * @param clazz
	 * @return
	 */
	public static List<Method> getGetterMethods( Class<?> clazz ) {
		return getGetterMethods( clazz, clazz.getDeclaredFields() );
	}

	/**
	 * Field 목록에 해당하는 모든 getter 목록 반환
	 * 
	 * @param clazz
	 * @param fields
	 * @return
	 */
	public static List<Method> getGetterMethods( Class<?> clazz, Field[] fields ) {

		List<Method> getterMethods = new ArrayList<Method>();

		for ( Field field : fields ) {
			
			Method getter = getGetterMethod( clazz, field.getName(), null );

			if ( getter != null ) {
				getterMethods.add( getter );
			}

		}

		return getterMethods;
	}

	/**
	 * 필드 이름 목록에 해당하는 getter 목록 반환
	 * 
	 * @param clazz
	 * @param fieldNameList
	 * @return
	 */
	public static List<Method> getGetterMethods( Class<?> clazz, List<String> fieldNameList ) {

		List<Method> getterMethods = new ArrayList<Method>();

		for ( String fieldName : fieldNameList ) {
			
			Method getter = getGetterMethod( clazz, fieldName, null );

			if ( getter != null ) {
				getterMethods.add( getter );
			}
		}

		return getterMethods;
	}

	/**
	 * 필드 이름 목록에 해당하는 getter 목록 반환
	 * 
	 * @param clazz
	 * @param fieldNames
	 * @return
	 */
	public static List<Method> getGetterMethods( Class<?> clazz, String[] fieldNames ) {

		List<Method> getterMethods = new ArrayList<Method>();

		for ( String fieldName : fieldNames ) {
			
			Method getter = getGetterMethod( clazz, fieldName, null );

			if ( getter != null ) {
				getterMethods.add( getter );
			}
		}

		return getterMethods;
	}

	/**
	 * Field 에 해당하는 getter 반환
	 * 
	 * @param clazz
	 * @param field
	 * @return
	 */
	public static Method getGetterMethod( Class<?> clazz, Field field ) {
		return getGetterMethod( clazz, field.getName(), null );
	}

	/**
	 * 필드명에 해당하는 getter 반환
	 * 
	 * @param clazz
	 * @param fieldName
	 * @return
	 */
	public static Method getGetterMethod( Class<?> clazz, String fieldName ) {
		return getGetterMethod( clazz, fieldName, null );
	}

	/**
	 * 필드명에 해당하는 getter 반환
	 * 
	 * @param clazz
	 * @param fieldName
	 * @param parameterTypes
	 * @return
	 */
	public static Method getGetterMethod( Class<?> clazz, String fieldName, Class<?>[] parameterTypes ) {

		Method getter = null;

		String methodFieldName = fieldName.substring(0,1).toUpperCase() + fieldName.substring( 1 );

		try {
			getter = clazz.getMethod( "get" + methodFieldName, parameterTypes );
		} catch ( Exception e ) {
			try {
				getter = clazz.getMethod( "is" + methodFieldName, parameterTypes );
			} catch ( Exception e2 ) {}
		}

		return getter;
	}
}
