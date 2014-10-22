package com.jbernate.ut.mkhiberclzz;

import java.io.BufferedWriter;
import java.io.IOException;

import com.jbernate.cm.util.DbUtil;

public class MkTail {

	public static BufferedWriter mkCont( BufferedWriter bw, Object[] ent, String dbNm, String tPrefix, String tPostfix ) throws IOException {
		
		// 파일 하단부 생성
		bw.write( "}" );	bw.newLine();					
				
		return bw;
	}
}