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

@Entity( name = "com.jbernate.cm.domain.table.CmMenuMgr" )
@Table( name = "CM_MENU_MGR" )
@SequenceGenerator( name = "CM_MENU_MGR_S", sequenceName = "CM_MENU_MGR_S",  initialValue = 1, allocationSize = 1 )
public class CmMenuMgr implements Serializable{
	private static final long serialVersionUID = 1L;

	public CmMenuMgr(){};
	public CmMenuMgr( BigDecimal seq ){	this.seq = seq;	}

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
	@Column( name = "INS_OBJ", length = 128, nullable = true ) 
	private String insObj;
	public String getInsObj() {	return insObj;	}
	public void setInsObj(String insObj) {	this.insObj = insObj;	}

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
	@Column( name = "UPD_OBJ", length = 128, nullable = true ) 
	private String updObj;
	public String getUpdObj() {	return updObj;	}
	public void setUpdObj(String updObj) {	this.updObj = updObj;	}

	//
	@Column( name = "UPD_IP", length = 64, nullable = true ) 
	private String updIp;
	public String getUpdIp() {	return updIp;	}
	public void setUpdIp(String updIp) {	this.updIp = updIp;	}

	//
	@Column( name = "MENU_NM", length = 128, nullable = true ) 
	private String menuNm;
	public String getMenuNm() {	return menuNm;	}
	public void setMenuNm(String menuNm) {	this.menuNm = menuNm;	}

	//
	@Column( name = "REMK", length = 256, nullable = true ) 
	private String remk;
	public String getRemk() {	return remk;	}
	public void setRemk(String remk) {	this.remk = remk;	}

	//
	@Column( name = "USE_YN", length = 1, nullable = false ) 
	private String useYn;
	public String getUseYn() {	return useYn;	}
	public void setUseYn(String useYn) {	this.useYn = useYn;	}

	//
	@ManyToOne
	@JoinColumn( name = "PGM_SEQ", referencedColumnName = "SEQ", columnDefinition = "NUMBER(16) NOT NULL UNIQUE" )
	private CmPgmMgr pgmSeq;
	public CmPgmMgr getPgmSeq() {	return pgmSeq;	}
	public void setPgmSeq(CmPgmMgr pgmSeq) {	this.pgmSeq = pgmSeq;	}

	//Sequence
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "CM_MENU_MGR_S" )
	@Column( name = "SEQ", columnDefinition = "NUMBER(16) NOT NULL UNIQUE", precision = 16, scale = 0 )
	private BigDecimal seq;
	public BigDecimal getSeq() {	return seq;	}
	public void setSeq(BigDecimal seq) {	this.seq = seq;	}

	//
	@ManyToOne
	@JoinColumn( name = "UP_SEQ", referencedColumnName = "SEQ", columnDefinition = "NUMBER(16) NOT NULL UNIQUE" )
	private CmMenuMgr upSeq;
	public CmMenuMgr getUpSeq() {	return upSeq;	}
	public void setUpSeq(CmMenuMgr upSeq) {	this.upSeq = upSeq;	}

	//
	@Column( name = "DSP_SEQ", precision = 16, scale = 0, nullable = true ) 
	private BigDecimal dspSeq;
	public BigDecimal getDspSeq() {	return dspSeq;	}
	public void setDspSeq(BigDecimal dspSeq) {	this.dspSeq = dspSeq;	}

	//
	@Column( name = "CO_CD", length = 30, nullable = false ) 
	private String coCd;
	public String getCoCd() {	return coCd;	}
	public void setCoCd(String coCd) {	this.coCd = coCd;	}

}
