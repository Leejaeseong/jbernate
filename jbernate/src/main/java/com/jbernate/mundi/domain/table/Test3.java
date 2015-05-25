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

@Entity( name = "com.jbernate.mundi.domain.table.Test3" )
@Table( name = "TEST_3" )
@SequenceGenerator( name = "TEST_3_S", sequenceName = "TEST_3_S",  initialValue = 1, allocationSize = 1 )
public class Test3 implements Serializable{
	private static final long serialVersionUID = 1L;

	public Test3(){};
	public Test3( BigDecimal seq ){	this.seq = seq;	}

	//Sequence
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "TEST_3_S" )
	@Column( name = "SEQ", columnDefinition = "NUMBER(16) NOT NULL UNIQUE", precision = 16, scale = 0 )
	private BigDecimal seq;
	public BigDecimal getSeq() {	return seq;	}
	public void setSeq(BigDecimal seq) {	this.seq = seq;	}

	//
	@Column( name = "ENG_USER_NM", length = 128, nullable = true ) 
	private String engUserNm;
	public String getEngUserNm() {	return engUserNm;	}
	public void setEngUserNm(String engUserNm) {	this.engUserNm = engUserNm;	}

	//
	@Column( name = "TEST_TEXT", length = -1, nullable = true ) 
	private String testText;
	public String getTestText() {	return testText;	}
	public void setTestText(String testText) {	this.testText = testText;	}

	//
	@ManyToOne
	@JoinColumn( name = "TEST2_SEQ", referencedColumnName = "SEQ", columnDefinition = "NUMBER(16) NOT NULL UNIQUE" )
	private Test2 test2Seq;
	public Test2 getTest2Seq() {	return test2Seq;	}
	public void setTest2Seq(Test2 test2Seq) {	this.test2Seq = test2Seq;	}

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

}
