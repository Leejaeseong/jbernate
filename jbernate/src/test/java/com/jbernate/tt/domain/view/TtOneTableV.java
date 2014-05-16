package com.jbernate.tt.domain.view;

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

import com.jbernate.cm.util.ConstantUtil;

@Entity( name = "com.jbernate.tt.domain.view.TtOneTableV" )                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
@Table( name = "TT_ONE_TABLE_V" )                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
public class TtOneTableV implements Serializable{

	public TtOneTableV(){};
	
	public TtOneTableV( long seq ){
		this.seq = seq;
	}
	
	// Sequence
	@Id
	@Column( name = "SEQ", precision = 16, scale = 0 )
	private long seq;
	
	// 문자형
	@Column( name = "V_VARCHAR")
	@Size( min = 0, max = 200 )
	private String vVarchar;
	
	// 날짜
	@Column( name = "V_DATE" )                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
	@Temporal( TemporalType.TIMESTAMP )                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
	@DateTimeFormat( pattern=ConstantUtil.FORMAT_DATE )
	private Date vDate;
	
	// CLOB형
	@Column( name = "V_CLOB")
	@Lob
	private String vClob;
	
	// BLOB형
	@Column( name = "V_BLOB")
	@Lob
	private byte[] vBlob;

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getvVarchar() {
		return vVarchar;
	}

	public void setvVarchar(String vVarchar) {
		this.vVarchar = vVarchar;
	}

	public Date getvDate() {
		return vDate;
	}

	public void setvDate(Date vDate) {
		this.vDate = vDate;
	}

	public String getvClob() {
		return vClob;
	}

	public void setvClob(String vClob) {
		this.vClob = vClob;
	}

	public byte[] getvBlob() {
		return vBlob;
	}

	public void setvBlob(byte[] vBlob) {
		this.vBlob = vBlob;
	}

}