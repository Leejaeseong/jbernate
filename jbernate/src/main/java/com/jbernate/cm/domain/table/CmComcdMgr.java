package com.jbernate.cm.domain.table;
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

@Entity( name = "com.jbernate.cm.domain.table.CmComcdMgr" )
@Table( name = "CM_COMCD_MGR" )
@SequenceGenerator( name = "CM_COMCD_MGR_S", initialValue = 1, allocationSize = 1 )
public class CmComcdMgr implements Serializable{
	private static final long serialVersionUID = 1L;

	public CmComcdMgr(){};
	public CmComcdMgr( BigDecimal seq ){	this.seq = seq;	}

	//null
	@Column( name = "INS_DT" )
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern=ConstUtil.FORMAT_DATE)
	private Date insDt;
	public Date getInsDt() {	return insDt;	}
	public void setInsDt(Date insDt) {	this.insDt = insDt;	}

	//Sequence
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "CM_COMCD_MGR_S" )
	@Column( name = "SEQ", columnDefinition = "NUMBER(16) NOT NULL UNIQUE", precision = 16, scale = 0 )
	private BigDecimal seq;
	public BigDecimal getSeq() {	return seq;	}
	public void setSeq(BigDecimal seq) {	this.seq = seq;	}

	//null
	@Column( name = "INS_ID", length = 30, nullable = true ) 
	private String insId;
	public String getInsId() {	return insId;	}
	public void setInsId(String insId) {	this.insId = insId;	}

	//null
	@Column( name = "INS_OBJ", length = 256, nullable = true ) 
	private String insObj;
	public String getInsObj() {	return insObj;	}
	public void setInsObj(String insObj) {	this.insObj = insObj;	}

	//null
	@Column( name = "INS_IP", length = 64, nullable = true ) 
	private String insIp;
	public String getInsIp() {	return insIp;	}
	public void setInsIp(String insIp) {	this.insIp = insIp;	}

	//null
	@Column( name = "UPD_DT" )
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern=ConstUtil.FORMAT_DATE)
	private Date updDt;
	public Date getUpdDt() {	return updDt;	}
	public void setUpdDt(Date updDt) {	this.updDt = updDt;	}

	//null
	@Column( name = "UPD_ID", length = 30, nullable = true ) 
	private String updId;
	public String getUpdId() {	return updId;	}
	public void setUpdId(String updId) {	this.updId = updId;	}

	//null
	@Column( name = "UPD_OBJ", length = 256, nullable = true ) 
	private String updObj;
	public String getUpdObj() {	return updObj;	}
	public void setUpdObj(String updObj) {	this.updObj = updObj;	}

	//null
	@Column( name = "UPD_IP", length = 64, nullable = true ) 
	private String updIp;
	public String getUpdIp() {	return updIp;	}
	public void setUpdIp(String updIp) {	this.updIp = updIp;	}

	//null
	@Column( name = "COM_GRP", length = 4, nullable = true ) 
	private String comGrp;
	public String getComGrp() {	return comGrp;	}
	public void setComGrp(String comGrp) {	this.comGrp = comGrp;	}

	//null
	@Column( name = "ETC01", length = 256, nullable = true ) 
	private String etc01;
	public String getEtc01() {	return etc01;	}
	public void setEtc01(String etc01) {	this.etc01 = etc01;	}

	//null
	@Column( name = "ETC02", length = 256, nullable = true ) 
	private String etc02;
	public String getEtc02() {	return etc02;	}
	public void setEtc02(String etc02) {	this.etc02 = etc02;	}

	//null
	@Column( name = "ETC03", length = 256, nullable = true ) 
	private String etc03;
	public String getEtc03() {	return etc03;	}
	public void setEtc03(String etc03) {	this.etc03 = etc03;	}

	//null
	@Column( name = "ETC04", length = 256, nullable = true ) 
	private String etc04;
	public String getEtc04() {	return etc04;	}
	public void setEtc04(String etc04) {	this.etc04 = etc04;	}

	//null
	@Column( name = "ETC05", length = 256, nullable = true ) 
	private String etc05;
	public String getEtc05() {	return etc05;	}
	public void setEtc05(String etc05) {	this.etc05 = etc05;	}

	//null
	@Column( name = "ETC06", length = 256, nullable = true ) 
	private String etc06;
	public String getEtc06() {	return etc06;	}
	public void setEtc06(String etc06) {	this.etc06 = etc06;	}

	//null
	@Column( name = "ETC07", length = 256, nullable = true ) 
	private String etc07;
	public String getEtc07() {	return etc07;	}
	public void setEtc07(String etc07) {	this.etc07 = etc07;	}

	//null
	@Column( name = "ETC08", length = 256, nullable = true ) 
	private String etc08;
	public String getEtc08() {	return etc08;	}
	public void setEtc08(String etc08) {	this.etc08 = etc08;	}

	//null
	@Column( name = "ETC09", length = 256, nullable = true ) 
	private String etc09;
	public String getEtc09() {	return etc09;	}
	public void setEtc09(String etc09) {	this.etc09 = etc09;	}

	//null
	@Column( name = "ETC10", length = 256, nullable = true ) 
	private String etc10;
	public String getEtc10() {	return etc10;	}
	public void setEtc10(String etc10) {	this.etc10 = etc10;	}

	//null
	@Column( name = "COM_CD", length = 30, nullable = true ) 
	private String comCd;
	public String getComCd() {	return comCd;	}
	public void setComCd(String comCd) {	this.comCd = comCd;	}

	//null
	@Column( name = "GRP_NM", length = 256, nullable = true ) 
	private String grpNm;
	public String getGrpNm() {	return grpNm;	}
	public void setGrpNm(String grpNm) {	this.grpNm = grpNm;	}

	//null
	@Column( name = "CD_NM", length = 256, nullable = true ) 
	private String cdNm;
	public String getCdNm() {	return cdNm;	}
	public void setCdNm(String cdNm) {	this.cdNm = cdNm;	}

}
