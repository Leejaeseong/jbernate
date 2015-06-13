package com.jbernate.mundi.domain.table;
import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.jbernate.cm.util.ConstUtil;

@Entity( name = "com.jbernate.mundi.domain.table.ActualMgr" )
@Table( name = "ACTUAL_MGR" )
@SequenceGenerator( name = "ACTUAL_MGR_S", sequenceName = "ACTUAL_MGR_S",  initialValue = 1, allocationSize = 1 )
public class ActualMgr implements Serializable{
	private static final long serialVersionUID = 1L;

	public ActualMgr(){};
	public ActualMgr( BigDecimal seq ){	this.seq = seq;	}

	//Sequence
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "ACTUAL_MGR_S" )
	@Column( name = "SEQ", columnDefinition = "NUMBER(16) NOT NULL UNIQUE", precision = 16, scale = 0 )
	private BigDecimal seq;
	public BigDecimal getSeq() {	return seq;	}
	public void setSeq(BigDecimal seq) {	this.seq = seq;	}

	//
	@Column( name = "REMK", length = 256, nullable = true ) 
	private String remk;
	public String getRemk() {	return remk;	}
	public void setRemk(String remk) {	this.remk = remk;	}

	//
	@Column( name = "INS_DT" )
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern=ConstUtil.FORMAT_DATE)
	private Date insDt;
	public Date getInsDt() {	return insDt;	}
	public void setInsDt(Date insDt) {	this.insDt = insDt;	}

	//
	@Column( name = "INS_ID", length = 30, nullable = true ) 
	private String insId;
	public String getInsId() {	return insId;	}
	public void setInsId(String insId) {	this.insId = insId;	}

	//
	@Column( name = "INS_IP", length = 64, nullable = true ) 
	private String insIp;
	public String getInsIp() {	return insIp;	}
	public void setInsIp(String insIp) {	this.insIp = insIp;	}

	//
	@Column( name = "UPD_DT" )
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern=ConstUtil.FORMAT_DATE)
	private Date updDt;
	public Date getUpdDt() {	return updDt;	}
	public void setUpdDt(Date updDt) {	this.updDt = updDt;	}

	//
	@Column( name = "UPD_ID", length = 30, nullable = true ) 
	private String updId;
	public String getUpdId() {	return updId;	}
	public void setUpdId(String updId) {	this.updId = updId;	}

	//
	@Column( name = "UPD_IP", length = 64, nullable = true ) 
	private String updIp;
	public String getUpdIp() {	return updIp;	}
	public void setUpdIp(String updIp) {	this.updIp = updIp;	}

	//
	@Column( name = "USE_YN", length = 1, nullable = false ) 
	private String useYn;
	public String getUseYn() {	return useYn;	}
	public void setUseYn(String useYn) {	this.useYn = useYn;	}

	//
	@ManyToOne
	@JoinColumn( name = "USER_SEQ", referencedColumnName = "SEQ", columnDefinition = "NUMBER(16) NOT NULL UNIQUE" )
	private UserMgr userSeq;
	public UserMgr getUserSeq() {	return userSeq;	}
	public void setUserSeq(UserMgr userSeq) {	this.userSeq = userSeq;	}
	
	//
	@Column( name = "WHOLESAL_NM", length = 128, nullable = true ) 
	private String wholesalNm;
	public String getWholesalNm() {	return wholesalNm;	}
	public void setWholesalNm(String wholesalNm) {	this.wholesalNm = wholesalNm;	}

	//
	@ManyToOne
	@JoinColumn( name = "PRD_SEQ", referencedColumnName = "SEQ", columnDefinition = "NUMBER(16) NOT NULL UNIQUE" )
	private PrdMgr prdSeq;
	public PrdMgr getPrdSeq() {	return prdSeq;	}
	public void setPrdSeq(PrdMgr prdSeq) {	this.prdSeq = prdSeq;	}

	//
	@Column( name = "SAL_LOC_NM", length = 128, nullable = true ) 
	private String salLocNm;
	public String getSalLocNm() {	return salLocNm;	}
	public void setSalLocNm(String salLocNm) {	this.salLocNm = salLocNm;	}

	//
	@Column( name = "ADDR", length = 256, nullable = true ) 
	private String addr;
	public String getAddr() {	return addr;	}
	public void setAddr(String addr) {	this.addr = addr;	}

	//
	@Column( name = "ZIP_CD", length = 30, nullable = true ) 
	private String zipCd;
	public String getZipCd() {	return zipCd;	}
	public void setZipCd(String zipCd) {	this.zipCd = zipCd;	}

	//
	@Column( name = "SAL_CNT", precision = 22, scale = 3, nullable = true ) 
	private BigDecimal salCnt;
	public BigDecimal getSalCnt() {	return salCnt;	}
	public void setSalCnt(BigDecimal salCnt) {	this.salCnt = salCnt;	}

	//
	@Column( name = "UNIT_PRC", precision = 22, scale = 3, nullable = true ) 
	private BigDecimal unitPrc;
	public BigDecimal getUnitPrc() {	return unitPrc;	}
	public void setUnitPrc(BigDecimal unitPrc) {	this.unitPrc = unitPrc;	}

	//
	@Column( name = "SAL_AMT", precision = 22, scale = 3, nullable = true ) 
	private BigDecimal salAmt;
	public BigDecimal getSalAmt() {	return salAmt;	}
	public void setSalAmt(BigDecimal salAmt) {	this.salAmt = salAmt;	}

	//
	@Column( name = "BRANCH_NM", length = 128, nullable = true ) 
	private String branchNm;
	public String getBranchNm() {	return branchNm;	}
	public void setBranchNm(String branchNm) {	this.branchNm = branchNm;	}

	//
	@Column( name = "YYYYMM", length = 6, nullable = true ) 
	private String yyyymm;
	public String getYyyymm() {	return yyyymm;	}
	public void setYyyymm(String yyyymm) {	this.yyyymm = yyyymm;	}

	//
	@Column( name = "MOD_YN", length = 1, nullable = false ) 
	private String modYn;
	public String getModYn() {	return modYn;	}
	public void setModYn(String modYn) {	this.modYn = modYn;	}

	//
	@ManyToOne
	@JoinColumn( name = "HOSPT_SEQ", referencedColumnName = "SEQ", columnDefinition = "NUMBER(16) NOT NULL UNIQUE" )
	private HosptMgr hosptSeq;
	public HosptMgr getHosptSeq() {	return hosptSeq;	}
	public void setHosptSeq(HosptMgr hosptSeq) {	this.hosptSeq = hosptSeq;	}

}
