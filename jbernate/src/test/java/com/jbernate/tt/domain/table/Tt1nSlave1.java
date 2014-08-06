package com.jbernate.tt.domain.table;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.jbernate.cm.util.ConstUtil;

@Entity( name = "com.jbernate.tt.domain.table.Tt1nSlave" )                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
@Table( name = "TT_1N_SLAVE1" )                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
@SequenceGenerator( name = "TT_1N_SLAVE1_S", sequenceName = "TT_1N_SLAVE1_S", initialValue = 1, allocationSize = 1 )
public class Tt1nSlave1 implements Serializable{

	private static final long serialVersionUID = 1L;

	public Tt1nSlave1(){};
	
	public Tt1nSlave1( Long seq ){
		this.seq = seq;
	}
	
	// Sequence
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "TT_1N_SLAVE1_S" )
	@Column( name = "SEQ", columnDefinition = "NUMBER(16) NOT NULL UNIQUE", precision = 16, scale = 0 )
	private Long seq;
	
	@ManyToOne                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
	@JoinColumn( name = "FK_MASTER", referencedColumnName = "SEQ", columnDefinition = "NUMBER(16) NOT NULL" )                                                                                                                                                                                                                                                                                                                                                                                                                                             
	private Tt1nMaster fkMaster;
	
	// 문자형
	@Column( name = "T_VARCHAR", columnDefinition = "NVARCHAR2(200) NULL", length = 200 )
	private String tVarchar;
	
	// 날짜
	@Column( name = "T_DATE", columnDefinition = "DATE NULL" )                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
	@Temporal( TemporalType.TIMESTAMP )                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
	@DateTimeFormat( pattern=ConstUtil.FORMAT_DATE )
	private Date tDate;
	
	// CLOB형
	@Column( name = "T_CLOB", columnDefinition = "CLOB NULL" )
	@Lob
	private String tClob;
	
	// BLOB형
	@Column( name = "T_BLOB", columnDefinition = "BLOB NULL" )
	@Lob
	private byte[] tBlob;
	
	// 삭제여부
	@Column( name = "DEL_FLAG", columnDefinition = "VARCHAR2(1) NULL", length = 1 )
	private String delFlag;
	
	// 생성일
	@Column( name = "CRE_DATE", columnDefinition = "DATE NULL" )                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
	@Temporal( TemporalType.TIMESTAMP )                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
	@DateTimeFormat( pattern=ConstUtil.FORMAT_DATE )
	private Date creDate;

	// 생성자
	@Column( name = "CRE_ID", columnDefinition = "NUMBER(16) NULL", precision = 16, scale = 0 )
	private Long creId;
	
	// 생성객체
	@Column( name = "CRE_OBJ", columnDefinition = "NVARCHAR2(100) NULL", length = 100 )
	private String creObj;
	
	// 생성자IP
	@Column( name = "CRE_IP", columnDefinition = "VARCHAR2(64) NULL", length = 64 )
	private String creIp;
	
	// 수정일
	@Column( name = "MOD_DATE", columnDefinition = "DATE NULL" )                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
	@Temporal( TemporalType.TIMESTAMP )                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
	@DateTimeFormat( pattern=ConstUtil.FORMAT_DATE )
	private Date modDate;

	// 수정자
	@Column( name = "MOD_ID", columnDefinition = "NUMBER(16) NULL", precision = 16, scale = 0 )
	private Long modId;
	
	// 수정객체
	@Column( name = "MOD_OBJ", columnDefinition = "NVARCHAR2(100) NULL", length = 100 )
	private String modObj;
	
	// 수정자IP
	@Column( name = "MOD_IP", columnDefinition = "VARCHAR2(64) NULL", length = 64 )
	private String modIp;

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public Tt1nMaster getFkMaster() {
		return fkMaster;
	}

	public void setFkMaster(Tt1nMaster fkMaster) {
		this.fkMaster = fkMaster;
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