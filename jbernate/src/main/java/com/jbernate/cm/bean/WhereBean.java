package com.jbernate.cm.bean;

public class WhereBean {

	/**	컬럼 명	*/
	private String colNm;
	/** 컬럼 값 */
	private Object colVal;
	
	/**
	 * 조건 타입
	 * EQ 			: equal
	 * GE 			: greater then
	 * LE 			: lesser then
	 * LIKEANY 		: like ( MatchMode.ANYWHERE )
	 * LIKEPRE 		: like ( MatchMode.START )
	 * LIKEPOST 	: like ( MatchMode.END )
	 * IN			: colVal[]
	 * ISNULL		: isNull
	 * ISNOTNULL	: isNotNull
	 * BETWEEN		: between colVal.split( "," )[ 0 ] and colVal.split( "," )[ 1 ]
	 */
	private String clause;	
	public static enum Clause{ EQ, GE, LE, LIKEANY, LIKEPRE, LIKEPOST, IN, ISNULL, ISNOTNULL };

	public WhereBean( String colNm, Object colVal, Clause c ) {
		this.colNm 	= colNm;
		this.colVal = colVal;
		this.clause	= c.toString();
	}

	public String getColNm() {
		return colNm;
	}

	public void setColNm(String colNm) {
		this.colNm = colNm;
	}

	public Object getColVal() {
		return colVal;
	}

	public void setColVal(Object colVal) {
		this.colVal = colVal;
	}

	public String getClause() {
		return clause;
	}

	public void setClause(String clause) {
		this.clause = clause;
	}

	
	
}