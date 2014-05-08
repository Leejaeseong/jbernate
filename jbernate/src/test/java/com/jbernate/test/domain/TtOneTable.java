package com.jbernate.test.domain;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.jbernate.util.ConstantUtil;

@Entity                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
@Table( name = "TT_ONE_TABLE" )                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
@SequenceGenerator( name = "TT_ONE_TABLE_S", sequenceName = "TT_ONE_TABLE_S", initialValue = 1, allocationSize = 1 )
public class TtOneTable implements Serializable{

	public TtOneTable(){};
	
	public TtOneTable( long seq ){
		this.seq = seq;
	}
	
	// Sequence
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "TT_ONE_TABLE_S" )
	@Column( name = "SEQ", precision = 16, scale = 0 )
	private long seq;
	
	// 문자형
	@Column( name = "T_VARCHAR")
	@Size( min = 0, max = 200 )
	private String tVarchar;
	
	// 날짜
	@Column( name = "T_DATE" )                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
	@Temporal( TemporalType.TIMESTAMP )                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
	@DateTimeFormat( pattern=ConstantUtil.FORMAT_DATE )
	private Date tDate;
	
	// CLOB형
	@Column( name = "T_CLOB")
	@Lob
	private String tClob;
	
	// BLOB형
	@Column( name = "T_BLOB")
	@Lob
	private byte[] tBlob;

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String gettVarchar() {
		return tVarchar;
	}

	public void settVarchar(String tVarchar) {
		this.tVarchar = tVarchar;
	}

	public Date gettDate() {
		return tDate;
	}

	public void settDate(Date tDate) {
		this.tDate = tDate;
	}

	public String gettClob() {
		return tClob;
	}

	public void settClob(String tClob) {
		this.tClob = tClob;
	}

	public byte[] gettBlob() {
		return tBlob;
	}

	public void settBlob(byte[] tBlob) {
		this.tBlob = tBlob;
	}
}