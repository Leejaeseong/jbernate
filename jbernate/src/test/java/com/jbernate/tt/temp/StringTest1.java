package com.jbernate.tt.temp;

import java.io.UnsupportedEncodingException;

import org.springframework.util.StringUtils;

import com.jbernate.cm.util.StrUtil;


public class StringTest1 {

	public static void main(String[] args) {
		//String str = "Thòt ñuøi-test";
		String str = "ñ";
		try {
			System.out.println( str.getBytes().length + ", " + str.getBytes( "UTF-16LE" ).length);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		/*
		//System.out.println( StringUtils.countOccurrencesOf( a, "." ) );
		
		while( alias.indexOf( "." ) > -1 ) {	// alias 설정( subclass의 키가 아닌 다른 값 조회 시 필요 )
			//System.out.println( a + ", " + a.substring( 0, a.indexOf( "." ) ) + ", " + a.indexOf( "." ) + ", " + a.length() );
			//System.out.println( a + ", " + a.substring( 0, a.lastIndexOf( "." ) ) + ", " + a.indexOf( "." ) + ", " + a.length() );
			//if( a.length() > a.indexOf( "." ) ) a = a.substring( a.indexOf( "." ) + 1 );
			
		}
		*/
	}

}