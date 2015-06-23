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

@Entity( name = "com.jbernate.mundi.domain.table.PerBrandMgr" )
@Table( name = "PER_BRAND_MGR" )
@SequenceGenerator( name = "PER_BRAND_MGR_S", sequenceName = "PER_BRAND_MGR_S",  initialValue = 1, allocationSize = 1 )
public class PerBrandMgr implements Serializable{
	private static final long serialVersionUID = 1L;

	public PerBrandMgr(){};
	public PerBrandMgr( BigDecimal seq ){	this.seq = seq;	}

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

	//Sequence
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "PER_BRAND_MGR_S" )
	@Column( name = "SEQ", columnDefinition = "NUMBER(16) NOT NULL UNIQUE", precision = 16, scale = 0 )
	private BigDecimal seq;
	public BigDecimal getSeq() {	return seq;	}
	public void setSeq(BigDecimal seq) {	this.seq = seq;	}

	//
	@Column( name = "YYYY", length = 4, nullable = true ) 
	private String yyyy;
	public String getYyyy() {	return yyyy;	}
	public void setYyyy(String yyyy) {	this.yyyy = yyyy;	}

	//
	@ManyToOne
	@JoinColumn( name = "TEAM_SEQ", referencedColumnName = "SEQ", columnDefinition = "NUMBER(16) NOT NULL UNIQUE" )
	private TeamMgr teamSeq;
	public TeamMgr getTeamSeq() {	return teamSeq;	}
	public void setTeamSeq(TeamMgr teamSeq) {	this.teamSeq = teamSeq;	}

	//
	@ManyToOne
	@JoinColumn( name = "PRDGRP_SEQ", referencedColumnName = "SEQ", columnDefinition = "NUMBER(16) NOT NULL UNIQUE" )
	private PrdgrpMgr prdgrpSeq;
	public PrdgrpMgr getPrdgrpSeq() {	return prdgrpSeq;	}
	public void setPrdgrpSeq(PrdgrpMgr prdgrpSeq) {	this.prdgrpSeq = prdgrpSeq;	}

	//
	@Column( name = "CYCLE", length = 30, nullable = true ) 
	private String cycle;
	public String getCycle() {	return cycle;	}
	public void setCycle(String cycle) {	this.cycle = cycle;	}
	
	//
	@Column( name = "PERCENTAGE", precision = 22, scale = 3, nullable = true ) 
	private BigDecimal percentage;
	public BigDecimal getPercentage() {	return percentage;	}
	public void setPercentage(BigDecimal percentage) {	this.percentage = percentage;	}
	
}