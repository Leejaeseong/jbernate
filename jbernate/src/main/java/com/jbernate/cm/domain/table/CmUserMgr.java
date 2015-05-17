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

@Entity( name = "com.jbernate.cm.domain.table.CmUserMgr" )
@Table( name = "CM_USER_MGR" )
@SequenceGenerator( name = "CM_USER_MGR_S", sequenceName = "CM_USER_MGR_S",  initialValue = 1, allocationSize = 1 )
public class CmUserMgr implements Serializable{
	private static final long serialVersionUID = 1L;

	public CmUserMgr(){};
	public CmUserMgr( BigDecimal seq ){	this.seq = seq;	}

	//
	@Column( name = "INS_DT" )
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern=ConstUtil.FORMAT_DATE)
	private Date insDt;
	public Date getInsDt() {	return insDt;	}
	public void setInsDt(Date insDt) {	this.insDt = insDt;	}

	//Sequence
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "CM_USER_MGR_S" )
	@Column( name = "SEQ", columnDefinition = "NUMBER(16) NOT NULL UNIQUE", precision = 16, scale = 0 )
	private BigDecimal seq;
	public BigDecimal getSeq() {	return seq;	}
	public void setSeq(BigDecimal seq) {	this.seq = seq;	}

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
	@Column( name = "CO_CD", length = 30, nullable = false ) 
	private String coCd;
	public String getCoCd() {	return coCd;	}
	public void setCoCd(String coCd) {	this.coCd = coCd;	}

	//
	@Column( name = "LOGIN_ID", length = 30, nullable = true ) 
	private String loginId;
	public String getLoginId() {	return loginId;	}
	public void setLoginId(String loginId) {	this.loginId = loginId;	}

	//
	@Column( name = "PWD", length = 256, nullable = true ) 
	private String pwd;
	public String getPwd() {	return pwd;	}
	public void setPwd(String pwd) {	this.pwd = pwd;	}

	//
	@Column( name = "PWD_EX_DT" )
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern=ConstUtil.FORMAT_DATE)
	private Date pwdExDt;
	public Date getPwdExDt() {	return pwdExDt;	}
	public void setPwdExDt(Date pwdExDt) {	this.pwdExDt = pwdExDt;	}

	//
	@ManyToOne
	@JoinColumn( name = "ROLE_SEQ", referencedColumnName = "SEQ", columnDefinition = "NUMBER(16) NOT NULL UNIQUE" )
	private CmRoleMgr roleSeq;
	public CmRoleMgr getRoleSeq() {	return roleSeq;	}
	public void setRoleSeq(CmRoleMgr roleSeq) {	this.roleSeq = roleSeq;	}

	//
	@Column( name = "USER_NM", length = 128, nullable = true ) 
	private String userNm;
	public String getUserNm() {	return userNm;	}
	public void setUserNm(String userNm) {	this.userNm = userNm;	}

	//
	@Column( name = "USE_YN", length = 1, nullable = false ) 
	private String useYn;
	public String getUseYn() {	return useYn;	}
	public void setUseYn(String useYn) {	this.useYn = useYn;	}

	//
	@Column( name = "ACC_ST_DT" )
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern=ConstUtil.FORMAT_DATE)
	private Date accStDt;
	public Date getAccStDt() {	return accStDt;	}
	public void setAccStDt(Date accStDt) {	this.accStDt = accStDt;	}

	//
	@Column( name = "ACC_ED_DT" )
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern=ConstUtil.FORMAT_DATE)
	private Date accEdDt;
	public Date getAccEdDt() {	return accEdDt;	}
	public void setAccEdDt(Date accEdDt) {	this.accEdDt = accEdDt;	}

}
