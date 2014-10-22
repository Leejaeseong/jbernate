package com.jbernate.ut.mkhiberclzz;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.jbernate.cm.util.DbUtil;
import com.jbernate.cm.util.StrUtil;

public class MkManyToOne {

	public static BufferedWriter mkCont( BufferedWriter bw, Object[] ent, String dbNm, String tPrefix, String tPostfix, String tDbName ) throws IOException {
		
		String rTab = ent[ 13 ].toString().split( "," )[ 0 ];
		String rCol = ent[ 13 ].toString().split( "," )[ 1 ];
		
		bw.write( "\t//" + ent[ 4 ] );	bw.newLine();
		bw.write( "\t@ManyToOne" );	bw.newLine();
		bw.write( "\t@JoinColumn( name = \"" + ent[ 3 ] + "\", referencedColumnName = \"" + rCol + "\" columnDefinition = \"NUMBER(16) NOT NULL UNIQUE\", precision = 16, scale = 0 )" );	bw.newLine();
		bw = MkVarGetSet.mkCont(bw, ent, dbNm, tPrefix, tPostfix, tDbName);
		bw.newLine();
		
		return bw;
	}
}