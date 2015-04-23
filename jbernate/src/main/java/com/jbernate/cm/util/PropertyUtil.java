package com.jbernate.cm.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Database관련 필요 Util
 */
public class PropertyUtil {
	
	/**
	 * 프라퍼티 값 얻기
	 * @param pName	프라퍼티 파일명( 경로와 ".properties"생략 )
	 * @param key	얻고자 하는 프라퍼티 key
	 * @return		값
	 * @throws IOException
	 */
	public static Object get( String pName, String key ) throws IOException{
		Properties properties = new Properties();
		properties.load( PropertyUtil.class.getClassLoader().getResourceAsStream( "property/" + pName + ".properties" ) );
        return properties.get(key); 
    }
	
}