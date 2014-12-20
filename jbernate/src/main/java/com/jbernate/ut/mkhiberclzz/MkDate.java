package com.jbernate.ut.mkhiberclzz;

import java.io.BufferedWriter;
import java.io.IOException;

import com.jbernate.cm.util.StrUtil;

public class MkDate {

	public static BufferedWriter mkCont( BufferedWriter bw, Object[] ent, String dbNm, String tPrefix, String tPostfix, String tDbName ) throws IOException {
		
		bw.write( "\t//" + ent[ 4 ] );	bw.newLine();
		bw.write( "\t@Column( name = \"" + ent[ 3 ] + "\" )" );	bw.newLine();
		bw.write( "\t@Temporal(TemporalType.DATE)" );	bw.newLine();
		bw.write( "\t@DateTimeFormat(pattern=ConstUtil.FORMAT_DATE)" );	bw.newLine();
		bw = MkVarGetSet.mkCont(bw, ent, dbNm, tPrefix, tPostfix, tDbName);
		bw.newLine();
		
		return bw;
	}
}