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

@Entity( name = "com.jbernate.mundi.domain.table.MnMkjavaMetaVi" )
@Table( name = "MN_MKJAVA_META_VI" )
@SequenceGenerator( name = "MN_MKJAVA_META_VI_S", sequenceName = "MN_MKJAVA_META_VI_S",  initialValue = 1, allocationSize = 1 )
public class MnMkjavaMetaVi implements Serializable{
	private static final long serialVersionUID = 1L;

	public MnMkjavaMetaVi(){};
	public MnMkjavaMetaVi( BigDecimal seq ){	this.seq = seq;	}

	//
	@Column( name = "DB_NM", length = 128, nullable = true ) 
	private String dbNm;
	public String getDbNm() {	return dbNm;	}
	public void setDbNm(String dbNm) {	this.dbNm = dbNm;	}

	//
	@Column( name = "TAB_NM", length = 128, nullable = false ) 
	private String tabNm;
	public String getTabNm() {	return tabNm;	}
	public void setTabNm(String tabNm) {	this.tabNm = tabNm;	}

	//
	@Column( name = "TAB_DESC", length = 1, nullable = false ) 
	private String tabDesc;
	public String getTabDesc() {	return tabDesc;	}
	public void setTabDesc(String tabDesc) {	this.tabDesc = tabDesc;	}

	//
	@Column( name = "COL_NM", length = 128, nullable = true ) 
	private String colNm;
	public String getColNm() {	return colNm;	}
	public void setColNm(String colNm) {	this.colNm = colNm;	}

	//
	@Column( name = "COL_DESC", length = 1, nullable = false ) 
	private String colDesc;
	public String getColDesc() {	return colDesc;	}
	public void setColDesc(String colDesc) {	this.colDesc = colDesc;	}

	//
	@Column( name = "COL_TYPE", length = 128, nullable = true ) 
	private String colType;
	public String getColType() {	return colType;	}
	public void setColType(String colType) {	this.colType = colType;	}

	//
	@Column( name = "COL_LEN", precision = 18, scale = 0, nullable = true ) 
	private BigDecimal colLen;
	public BigDecimal getColLen() {	return colLen;	}
	public void setColLen(BigDecimal colLen) {	this.colLen = colLen;	}

	//
	@Column( name = "COL_PREC", precision = 18, scale = 0, nullable = true ) 
	private BigDecimal colPrec;
	public BigDecimal getColPrec() {	return colPrec;	}
	public void setColPrec(BigDecimal colPrec) {	this.colPrec = colPrec;	}

	//
	@Column( name = "COL_SCALE", precision = 18, scale = 0, nullable = true ) 
	private BigDecimal colScale;
	public BigDecimal getColScale() {	return colScale;	}
	public void setColScale(BigDecimal colScale) {	this.colScale = colScale;	}

	//
	@Column( name = "COL_NULL", length = 1, nullable = true ) 
	private String colNull;
	public String getColNull() {	return colNull;	}
	public void setColNull(String colNull) {	this.colNull = colNull;	}

	//
	@Column( name = "COL_SEQ", precision = 18, scale = 0, nullable = true ) 
	private BigDecimal colSeq;
	public BigDecimal getColSeq() {	return colSeq;	}
	public void setColSeq(BigDecimal colSeq) {	this.colSeq = colSeq;	}

	//
	@Column( name = "IS_PK", length = 1, nullable = false ) 
	private String isPk;
	public String getIsPk() {	return isPk;	}
	public void setIsPk(String isPk) {	this.isPk = isPk;	}

	//
	@Column( name = "R_CNT", precision = 18, scale = 0, nullable = true ) 
	private BigDecimal rCnt;
	public BigDecimal getRCnt() {	return rCnt;	}
	public void setRCnt(BigDecimal rCnt) {	this.rCnt = rCnt;	}

	//
	@Column( name = "REF_TAB_N_COL", length = 257, nullable = true ) 
	private String refTabNCol;
	public String getRefTabNCol() {	return refTabNCol;	}
	public void setRefTabNCol(String refTabNCol) {	this.refTabNCol = refTabNCol;	}

	//Sequence
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "MN_MKJAVA_META_VI_S" )
	@Column( name = "SEQ", columnDefinition = "NUMBER(16) NOT NULL UNIQUE", precision = 16, scale = 0 )
	private BigDecimal seq;
	public BigDecimal getSeq() {	return seq;	}
	public void setSeq(BigDecimal seq) {	this.seq = seq;	}

}
