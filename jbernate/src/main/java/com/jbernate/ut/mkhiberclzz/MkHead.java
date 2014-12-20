package com.jbernate.ut.mkhiberclzz;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.jbernate.cm.util.DbUtil;

public class MkHead {

	public static BufferedWriter mkCont( BufferedWriter bw, Object[] ent, String dbNm, String tPrefix, String tPostfix ) throws IOException {
		
		// 파일 상단부 생성
		bw.write( "package " + tPrefix + "." + dbNm.toLowerCase() + "." + tPostfix + ";" );	bw.newLine();					
		bw.write( "import java.io.Serializable;" );	bw.newLine();
		bw.write( "import java.util.Date;" );	bw.newLine();	bw.newLine();
		bw.write( "import java.math.BigDecimal;" );	bw.newLine();	bw.newLine();
		bw.write( "import javax.persistence.Column;" );	bw.newLine();
		bw.write( "import javax.persistence.Entity;" );	bw.newLine();
		bw.write( "import javax.persistence.GeneratedValue;" );	bw.newLine();
		bw.write( "import javax.persistence.GenerationType;" );	bw.newLine();
		bw.write( "import javax.persistence.Id;" );	bw.newLine();
		bw.write( "import javax.persistence.OneToOne;" );	bw.newLine();
		bw.write( "import javax.persistence.ManyToOne;" );	bw.newLine();
		bw.write( "import javax.persistence.JoinColumn;" );	bw.newLine();
		bw.write( "import javax.persistence.Lob;" );	bw.newLine();
		bw.write( "import javax.persistence.SequenceGenerator;" );	bw.newLine();
		bw.write( "import javax.persistence.Table;" );	bw.newLine();
		bw.write( "import javax.persistence.Temporal;" );	bw.newLine();
		bw.write( "import javax.persistence.TemporalType;" );	bw.newLine();	bw.newLine();
		bw.write( "import org.springframework.format.annotation.DateTimeFormat;" );	bw.newLine();	bw.newLine();
		bw.write( "import com.jbernate.cm.util.ConstUtil;" );	bw.newLine();	bw.newLine();
		bw.write( "@Entity( name = \"" + tPrefix + "." + dbNm.toLowerCase() + "." + tPostfix + "." + DbUtil.getEntityName( ent[ 1 ].toString() ) + "\" )" );	bw.newLine();
		bw.write( "@Table( name = \"" + ent[ 1 ] + "\" )" );	bw.newLine();
		bw.write( "@SequenceGenerator( name = \"" + ent[ 1 ] + "_S\", initialValue = 1, allocationSize = 1 )" );	bw.newLine();
		bw.write( "public class " + DbUtil.getEntityName( ent[ 1 ].toString() ) + " implements Serializable{" );	bw.newLine();
		bw.write( "\tprivate static final long serialVersionUID = 1L;" );	bw.newLine();	bw.newLine();
		bw.write( "\tpublic " + DbUtil.getEntityName( ent[ 1 ].toString() ) + "(){};" );	bw.newLine();
		bw.write( "\tpublic " + DbUtil.getEntityName( ent[ 1 ].toString() ) + "( BigDecimal seq ){	this.seq = seq;	}" );	bw.newLine();	
		bw.newLine();
		
		return bw;
	}
}