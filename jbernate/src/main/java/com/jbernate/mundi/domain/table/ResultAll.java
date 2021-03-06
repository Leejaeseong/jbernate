package com.jbernate.mundi.domain.table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.jbernate.cm.util.ConstUtil;

@Entity( name = "com.jbernate.mundi.domain.table.ResultAll" )
@Table( name = "RESULT_ALL" )
@SequenceGenerator( name = "RESULT_ALL_S", sequenceName = "RESULT_ALL_S",  initialValue = 1, allocationSize = 1 )
public class ResultAll implements Serializable{
	private static final long serialVersionUID = 1L;

	public ResultAll(){};
	public ResultAll( BigDecimal seq ){	this.seq = seq;	}

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
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "RESULT_ALL_S" )
	@Column( name = "SEQ", columnDefinition = "NUMBER(16) NOT NULL UNIQUE", precision = 16, scale = 0 )
	private BigDecimal seq;
	public BigDecimal getSeq() {	return seq;	}
	public void setSeq(BigDecimal seq) {	this.seq = seq;	}

	//
	@Column( name = "PRDGRP_SEQ", precision = 16, scale = 0, nullable = false ) 
	private BigDecimal prdgrpSeq;
	public BigDecimal getPrdgrpSeq() {	return prdgrpSeq;	}
	public void setPrdgrpSeq(BigDecimal prdgrpSeq) {	this.prdgrpSeq = prdgrpSeq;	}

	//
	@Column( name = "TEAM_SEQ", precision = 16, scale = 0, nullable = true ) 
	private BigDecimal teamSeq;
	public BigDecimal getTeamSeq() {	return teamSeq;	}
	public void setTeamSeq(BigDecimal teamSeq) {	this.teamSeq = teamSeq;	}

	//
	@Column( name = "USER_SEQ", precision = 16, scale = 0, nullable = true ) 
	private BigDecimal userSeq;
	public BigDecimal getUserSeq() {	return userSeq;	}
	public void setUserSeq(BigDecimal userSeq) {	this.userSeq = userSeq;	}

	//
	@Column( name = "YYYYMM", length = 6, nullable = true ) 
	private String yyyymm;
	public String getYyyymm() {	return yyyymm;	}
	public void setYyyymm(String yyyymm) {	this.yyyymm = yyyymm;	}

	//
	@Column( name = "GOAL", precision = 22, scale = 3, nullable = true ) 
	private BigDecimal goal;
	public BigDecimal getGoal() {	return goal;	}
	public void setGoal(BigDecimal goal) {	this.goal = goal;	}

	//
	@Column( name = "ACTUAL", precision = 22, scale = 3, nullable = true ) 
	private BigDecimal actual;
	public BigDecimal getActual() {	return actual;	}
	public void setActual(BigDecimal actual) {	this.actual = actual;	}

	//
	@Column( name = "HOSPT_SEQ", precision = 16, scale = 0, nullable = true ) 
	private BigDecimal hosptSeq;
	public BigDecimal getHosptSeq() {	return hosptSeq;	}
	public void setHosptSeq(BigDecimal hosptSeq) {	this.hosptSeq = hosptSeq;	}
	
	//
	@Column( name = "PER_RATE", precision = 22, scale = 3, nullable = true ) 
	private BigDecimal perRate;
	public BigDecimal getPerRate() {	return perRate;	}
	public void setPerRate(BigDecimal perRate) {	this.perRate = perRate;	}
	
	//
	@Column( name = "PER_WRK", precision = 22, scale = 3, nullable = true ) 
	private BigDecimal perWrk;
	public BigDecimal getPerWrk() {	return perWrk;	}
	public void setPerWrk(BigDecimal perWrk) {	this.perWrk = perWrk;	}
	
	//
	@Column( name = "PER_GRP", precision = 22, scale = 3, nullable = true ) 
	private BigDecimal perGrp;
	public BigDecimal getPerGrp() {	return perGrp;	}
	public void setPerGrp(BigDecimal perGrp) {	this.perGrp = perGrp;	}
	
	//
	@Column( name = "PER_YEAR", precision = 22, scale = 3, nullable = true ) 
	private BigDecimal perYear;
	public BigDecimal getPerYear() {	return perYear;	}
	public void setPerYear(BigDecimal perYear) {	this.perYear = perYear;	}

}