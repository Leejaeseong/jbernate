package com.jbernate.ut.mkhiberclzz;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.jbernate.cm.util.DbUtil;
import com.jbernate.cm.util.StrUtil;

public class MkOneToOnePk {

	public static BufferedWriter mkCont( BufferedWriter bw, Object[] ent, String dbNm, String tPrefix, String tPostfix ) throws IOException {
		
		String rTab = ent[ 13 ].toString().split( "," )[ 0 ];
		String rCol = ent[ 13 ].toString().split( "," )[ 1 ];
		
		bw.write( "\t//Sequence, 1:1 Slave" );	bw.newLine();
		bw.write( "\t@Id" );	bw.newLine();
		bw.write( "\t@OneToOne" );	bw.newLine();
		bw.write( "\t@JoinColumn( name = \"" + ent[ 3 ] + "\", referencedColumnName = \"" + rCol + "\" columnDefinition = \"NUMBER(16) NOT NULL UNIQUE\", precision = 16, scale = 0 )" );	bw.newLine();
		bw.write( "\tprivate BigDecimal " + StrUtil.makeJavaNameRule( ent[ 3 ].toString().toLowerCase() ) + ";" );	bw.newLine();
		bw.write( "\tpublic BigDecimal " + StrUtil.makeJavaNameRule( "get_" + ent[ 3 ].toString().toLowerCase() ) + "() {	return " + StrUtil.makeJavaNameRule( ent[ 3 ].toString().toLowerCase() ) + ";	}" );	bw.newLine();
		bw.write( "\tpublic void " + StrUtil.makeJavaNameRule( "set_" + ent[ 3 ].toString().toLowerCase() ) + "(BigDecimal " + StrUtil.makeJavaNameRule( ent[ 3 ].toString().toLowerCase() ) + ") {	this." + StrUtil.makeJavaNameRule( ent[ 3 ].toString().toLowerCase() ) + " = " + StrUtil.makeJavaNameRule( ent[ 3 ].toString().toLowerCase() ) + ";	}" );	bw.newLine();
		bw.newLine();
		
		return bw;
	}
}