package com.jbernate.ut.mkhiberclzz;

import java.io.BufferedWriter;
import java.io.IOException;

import com.jbernate.cm.util.StrUtil;

public class MkSinglePk {

	public static BufferedWriter mkCont( BufferedWriter bw, Object[] ent, String dbNm, String tPrefix, String tPostfix ) throws IOException {
		
		bw.write( "\t//Sequence" );	bw.newLine();
		bw.write( "\t@Id" );	bw.newLine();
		bw.write( "\t@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = \"" + ent[ 1 ] + "_S\" )" );	bw.newLine();
		bw.write( "\t@Column( name = \"" + ent[ 3 ] + "\", columnDefinition = \"NUMBER(16) NOT NULL UNIQUE\", precision = 16, scale = 0 )" );	bw.newLine();
		bw.write( "\tprivate BigDecimal " + StrUtil.makeJavaNameRule( ent[ 3 ].toString().toLowerCase() ) + ";" );	bw.newLine();
		bw.write( "\tpublic BigDecimal " + StrUtil.makeJavaNameRule( "get_" + ent[ 3 ].toString().toLowerCase() ) + "() {	return " + StrUtil.makeJavaNameRule( ent[ 3 ].toString().toLowerCase() ) + ";	}" );	bw.newLine();
		bw.write( "\tpublic void " + StrUtil.makeJavaNameRule( "set_" + ent[ 3 ].toString().toLowerCase() ) + "(BigDecimal " + StrUtil.makeJavaNameRule( ent[ 3 ].toString().toLowerCase() ) + ") {	this." + StrUtil.makeJavaNameRule( ent[ 3 ].toString().toLowerCase() ) + " = " + StrUtil.makeJavaNameRule( ent[ 3 ].toString().toLowerCase() ) + ";	}" );	bw.newLine();
		bw.newLine();
		
		return bw;
	}
}