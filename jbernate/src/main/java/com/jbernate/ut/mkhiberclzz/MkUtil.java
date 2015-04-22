package com.jbernate.ut.mkhiberclzz;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.jbernate.cm.util.DbUtil;

public class MkUtil {

	/**
	 * 컬럼 타입을 자바 타입으로 변환
	 * @param tDbName
	 * @param Type
	 */
	public static String ConvertDataType( String tDbName, String type ) {
		if( type.equals( "NUMBER" ) || type.equals( "NUMERIC" ) ) return "BigDecimal";
		else if( type.equals( "BLOB" ) ) return "byte[]";
		else if( type.equals( "DATE" ) ) return "Date";
		else return "String";
		
	}
	
}