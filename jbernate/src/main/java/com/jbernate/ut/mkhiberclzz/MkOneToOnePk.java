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

	public static BufferedWriter mkCont( BufferedWriter bw, Object[] ent, String dbNm, String tPrefix, String tPostfix, String tDbName ) throws IOException {
		
		String rTab = ent[ 13 ].toString().split( "," )[ 0 ];
		String rCol = ent[ 13 ].toString().split( "," )[ 1 ];
		
		bw.write( "\t//1:1 Slave" );	bw.newLine();
		bw.write( "\t@OneToOne" );	bw.newLine();
		bw.write( "\t@JoinColumn( name = \"" + ent[ 3 ] + "\", referencedColumnName = \"" + rCol + "\", insertable = false, updatable = false )" );	bw.newLine();
		bw = MkVarGetSet.mkOneToOneCont(bw, ent, dbNm, tPrefix, tPostfix, tDbName, rTab, rCol);
		
		bw.newLine();
		
		return bw;
	}
}