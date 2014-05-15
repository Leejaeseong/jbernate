package com.jbernate.common.bean;

public class OrderBean {

	/** 컬럼명 */
	private String colNm;
	
	/** 
	 * 정렬 타입
	 * ASC	: ascend
	 * DESC	: descend
	 */
	private String type;
	public enum Type{ ASC, DESC };
	
	public OrderBean( String colNm, OrderBean.Type t ) {
		this.colNm 	= colNm;
		this.type	= t.toString();
	}

	public String getColNm() {
		return colNm;
	}

	public void setColNm(String colNm) {
		this.colNm = colNm;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}