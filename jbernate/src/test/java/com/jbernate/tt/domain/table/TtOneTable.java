package com.jbernate.tt.domain.table;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity( name = "com.jbernate.tt.domain.table.TtOneTable" )                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
@Table( name = "TT_ONE_TABLE" )                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
@SequenceGenerator( name = "TT_ONE_TABLE_S", sequenceName = "TT_ONE_TABLE_S", initialValue = 1, allocationSize = 1 )
public class TtOneTable implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public TtOneTable(){};
	
	public TtOneTable( Long seq ){
		this.seq = seq;
	}
	
	// Sequence
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "TT_ONE_TABLE_S" )
	@Column( name = "SEQ", precision = 16, scale = 0 )
	private Long seq;
	
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

	// 삭제여부
	@Column( name = "DEL_FLAG")
	@Size( min = 0, max = 1 )
	private String delFlag;
		
	// 생성일
	@Column( name = "CRE_DATE" )                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
	@Temporal( TemporalType.TIMESTAMP )                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
	@DateTimeFormat( pattern=ConstantUtil.FORMAT_DATE )
	private Date creDate;

	// 생성자
	@Column( name = "CRE_ID", precision = 16, scale = 0 )
	private Long creId;
	
	// 생성객체
	@Column( name = "CRE_OBJ")
	@Size( min = 0, max = 100 )
	private String creObj;
	
	// 생성자IP
	@Column( name = "CRE_IP")
	@Size( min = 0, max = 64 )
	private String creIp;
	
	// 수정일
	@Column( name = "MOD_DATE" )                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
	@Temporal( TemporalType.TIMESTAMP )                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
	@DateTimeFormat( pattern=ConstantUtil.FORMAT_DATE )
	private Date modDate;

	// 수정자
	@Column( name = "MOD_ID", precision = 16, scale = 0 )
	private Long modId;
	
	// 수정객체
	@Column( name = "MOD_OBJ")
	@Size( min = 0, max = 100 )
	private String modObj;
	
	// 수정자IP
	@Column( name = "MOD_IP")
	@Size( min = 0, max = 64 )
	private String modIp;

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
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

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public Date getCreDate() {
		return creDate;
	}

	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	public Long getCreId() {
		return creId;
	}

	public void setCreId(Long creId) {
		this.creId = creId;
	}

	public String getCreObj() {
		return creObj;
	}

	public void setCreObj(String creObj) {
		this.creObj = creObj;
	}

	public String getCreIp() {
		return creIp;
	}

	public void setCreIp(String creIp) {
		this.creIp = creIp;
	}

	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	public Long getModId() {
		return modId;
	}

	public void setModId(Long modId) {
		this.modId = modId;
	}

	public String getModObj() {
		return modObj;
	}

	public void setModObj(String modObj) {
		this.modObj = modObj;
	}

	public String getModIp() {
		return modIp;
	}

	public void setModIp(String modIp) {
		this.modIp = modIp;
	}
		
}