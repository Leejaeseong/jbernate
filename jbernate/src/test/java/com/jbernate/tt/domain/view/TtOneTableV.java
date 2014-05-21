package com.jbernate.tt.domain.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.jbernate.cm.util.ConstantUtil;

@Entity( name = "com.jbernate.tt.domain.view.TtOneTableV" )                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
@Table( name = "TT_ONE_TABLE_V" )                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
public class TtOneTableV implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public TtOneTableV(){};
	
	public TtOneTableV( Long seq ){
		this.seq = seq;
	}
	
	// Sequence
	@Id
	@Column( name = "SEQ", precision = 16, scale = 0 )
	private Long seq;
	
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