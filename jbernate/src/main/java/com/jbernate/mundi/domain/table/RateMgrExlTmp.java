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

@Entity( name = "com.jbernate.mundi.domain.table.RateMgrExlTmp" )
@Table( name = "RATE_MGR_EXL_TMP" )
@SequenceGenerator( name = "RATE_MGR_EXL_TMP_S", sequenceName = "RATE_MGR_EXL_TMP_S",  initialValue = 1, allocationSize = 1 )
public class RateMgrExlTmp implements Serializable{
	private static final long serialVersionUID = 1L;

	public RateMgrExlTmp(){};
	public RateMgrExlTmp( BigDecimal seq ){	this.seq = seq;	}

	//Sequence
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "RATE_MGR_EXL_TMP_S" )
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
	@Column( name = "RATE_CD", length = 1, nullable = false ) 
	private String rateCd;
	public String getRateCd() {	return rateCd;	}
	public void setRateCd(String rateCd) {	this.rateCd = rateCd;	}

	//
	@Column( name = "ACHIEV", precision = 22, scale = 3, nullable = true ) 
	private BigDecimal achiev;
	public BigDecimal getAchiev() {	return achiev;	}
	public void setAchiev(BigDecimal achiev) {	this.achiev = achiev;	}

	//
	@Column( name = "VALUE", precision = 22, scale = 3, nullable = true ) 
	private BigDecimal value;
	public BigDecimal getValue() {	return value;	}
	public void setValue(BigDecimal value) {	this.value = value;	}

	//
	@Column( name = "YYYYMM", length = 6, nullable = true ) 
	private String yyyymm;
	public String getYyyymm() {	return yyyymm;	}
	public void setYyyymm(String yyyymm) {	this.yyyymm = yyyymm;	}

}
