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

@Entity( name = "com.jbernate.mundi.domain.table.GoalMgr" )
@Table( name = "GOAL_MGR" )
@SequenceGenerator( name = "GOAL_MGR_S", sequenceName = "GOAL_MGR_S",  initialValue = 1, allocationSize = 1 )
public class GoalMgr implements Serializable{
	private static final long serialVersionUID = 1L;

	public GoalMgr(){};
	public GoalMgr( BigDecimal seq ){	this.seq = seq;	}

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
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "GOAL_MGR_S" )
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
	@JoinColumn( name = "USER_SEQ", referencedColumnName = "SEQ", columnDefinition = "NUMBER(16) NOT NULL UNIQUE" )
	private UserMgr userSeq;
	public UserMgr getUserSeq() {	return userSeq;	}
	public void setUserSeq(UserMgr userSeq) {	this.userSeq = userSeq;	}

	//
	@ManyToOne
	@JoinColumn( name = "PRDGRP_SEQ", referencedColumnName = "SEQ", columnDefinition = "NUMBER(16) NOT NULL UNIQUE" )
	private PrdgrpMgr prdgrpSeq;
	public PrdgrpMgr getPrdgrpSeq() {	return prdgrpSeq;	}
	public void setPrdgrpSeq(PrdgrpMgr prdgrpSeq) {	this.prdgrpSeq = prdgrpSeq;	}

	//
	@ManyToOne
	@JoinColumn( name = "HOSPT_SEQ", referencedColumnName = "SEQ", columnDefinition = "NUMBER(16) NOT NULL UNIQUE" )
	private HosptMgr hosptSeq;
	public HosptMgr getHosptSeq() {	return hosptSeq;	}
	public void setHosptSeq(HosptMgr hosptSeq) {	this.hosptSeq = hosptSeq;	}

	//
	@Column( name = "MON_1", precision = 22, scale = 3, nullable = true ) 
	private BigDecimal mon1;
	public BigDecimal getMon1() {	return mon1;	}
	public void setMon1(BigDecimal mon1) {	this.mon1 = mon1;	}

	//
	@Column( name = "MON_2", precision = 22, scale = 3, nullable = true ) 
	private BigDecimal mon2;
	public BigDecimal getMon2() {	return mon2;	}
	public void setMon2(BigDecimal mon2) {	this.mon2 = mon2;	}

	//
	@Column( name = "MON_3", precision = 22, scale = 3, nullable = true ) 
	private BigDecimal mon3;
	public BigDecimal getMon3() {	return mon3;	}
	public void setMon3(BigDecimal mon3) {	this.mon3 = mon3;	}

	//
	@Column( name = "MON_4", precision = 22, scale = 3, nullable = true ) 
	private BigDecimal mon4;
	public BigDecimal getMon4() {	return mon4;	}
	public void setMon4(BigDecimal mon4) {	this.mon4 = mon4;	}

	//
	@Column( name = "MON_5", precision = 22, scale = 3, nullable = true ) 
	private BigDecimal mon5;
	public BigDecimal getMon5() {	return mon5;	}
	public void setMon5(BigDecimal mon5) {	this.mon5 = mon5;	}

	//
	@Column( name = "MON_6", precision = 22, scale = 3, nullable = true ) 
	private BigDecimal mon6;
	public BigDecimal getMon6() {	return mon6;	}
	public void setMon6(BigDecimal mon6) {	this.mon6 = mon6;	}

	//
	@Column( name = "MON_7", precision = 22, scale = 3, nullable = true ) 
	private BigDecimal mon7;
	public BigDecimal getMon7() {	return mon7;	}
	public void setMon7(BigDecimal mon7) {	this.mon7 = mon7;	}

	//
	@Column( name = "MON_8", precision = 22, scale = 3, nullable = true ) 
	private BigDecimal mon8;
	public BigDecimal getMon8() {	return mon8;	}
	public void setMon8(BigDecimal mon8) {	this.mon8 = mon8;	}

	//
	@Column( name = "MON_9", precision = 22, scale = 3, nullable = true ) 
	private BigDecimal mon9;
	public BigDecimal getMon9() {	return mon9;	}
	public void setMon9(BigDecimal mon9) {	this.mon9 = mon9;	}

	//
	@Column( name = "MON_10", precision = 22, scale = 3, nullable = true ) 
	private BigDecimal mon10;
	public BigDecimal getMon10() {	return mon10;	}
	public void setMon10(BigDecimal mon10) {	this.mon10 = mon10;	}

	//
	@Column( name = "MON_11", precision = 22, scale = 3, nullable = true ) 
	private BigDecimal mon11;
	public BigDecimal getMon11() {	return mon11;	}
	public void setMon11(BigDecimal mon11) {	this.mon11 = mon11;	}

	//
	@Column( name = "MON_12", precision = 22, scale = 3, nullable = true ) 
	private BigDecimal mon12;
	public BigDecimal getMon12() {	return mon12;	}
	public void setMon12(BigDecimal mon12) {	this.mon12 = mon12;	}

}
