package com.jbernate.ut.mkhiberclzz;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.jbernate.cm.util.DbUtil;
import com.jbernate.cm.util.StrUtil;

public class MkVarGetSet {

	public static BufferedWriter mkCont( BufferedWriter bw, Object[] ent, String dbNm, String tPrefix, String tPostfix, String tDbName ) throws IOException {
	
		bw.write( "\tprivate " + MkUtil.ConvertDataType( tDbName, ent[ 5 ].toString() ) + " " + StrUtil.makeJavaNameRule( ent[ 3 ].toString().toLowerCase() ) + ";" );	bw.newLine();
		bw.write( "\tpublic " + MkUtil.ConvertDataType( tDbName, ent[ 5 ].toString() ) + " " + StrUtil.makeJavaNameRule( "get_" + ent[ 3 ].toString().toLowerCase() ) + "() {	return " + StrUtil.makeJavaNameRule( ent[ 3 ].toString().toLowerCase() ) + ";	}" );	bw.newLine();
		bw.write( "\tpublic void " + StrUtil.makeJavaNameRule( "set_" + ent[ 3 ].toString().toLowerCase() ) + "(" + MkUtil.ConvertDataType( tDbName, ent[ 5 ].toString() ) + " " + StrUtil.makeJavaNameRule( ent[ 3 ].toString().toLowerCase() ) + ") {	this." + StrUtil.makeJavaNameRule( ent[ 3 ].toString().toLowerCase() ) + " = " + StrUtil.makeJavaNameRule( ent[ 3 ].toString().toLowerCase() ) + ";	}" );	bw.newLine();
		
		return bw;
	}
}