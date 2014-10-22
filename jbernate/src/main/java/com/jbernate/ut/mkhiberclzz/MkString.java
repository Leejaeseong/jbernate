package com.jbernate.ut.mkhiberclzz;

import java.io.BufferedWriter;
import java.io.IOException;

import com.jbernate.cm.util.StrUtil;

public class MkString {

	public static BufferedWriter mkCont( BufferedWriter bw, Object[] ent, String dbNm, String tPrefix, String tPostfix, String tDbName ) throws IOException {
		
		bw.write( "\t//" + ent[ 4 ] );	bw.newLine();
		bw.write( "\t@Column( name = \"" + ent[ 3 ] + "\", length = " + ent[ 6 ] + ", nullable = " + ( ent[ 9 ].toString().equals( "Y" ) ? "true" : "false" ) + " ) " );	bw.newLine();
		bw = MkVarGetSet.mkCont(bw, ent, dbNm, tPrefix, tPostfix, tDbName);
		bw.newLine();
		
		return bw;
	}
}