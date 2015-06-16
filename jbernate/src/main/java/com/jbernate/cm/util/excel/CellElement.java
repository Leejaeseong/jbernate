package com.jbernate.cm.util.excel;

import java.text.Format;

/*
 * 	List<CellElement> cellElements = new ArrayList<CellElement>();
 * 	//	cellElements.add( new CellElement( "idx", "IDX", 10 ) );
 * 		cellElements.add( new CellElement( "id", "아이디", 20 ) );
 * 		cellElements.add( new CellElement( "name", "이름", 20, new MessageFormat( "{0} 님" ) ) );
 * 		cellElements.add( new CellElement( "birthday", "생일", 20, new MessageFormat( "{0,date,yyyy/MM/dd hh:mm:ss}" ) ) );
 * 		cellElements.add( new CellElement( "age", "나이", 10, new MessageFormat( "{0,number,#,##0} 세" ) ) );
 * 		cellElements.add( new CellElement( "weight", "몸무게", 15, new MessageFormat( "{0,number,#,##0.0#} kg" ) ) );
 * 		cellElements.add( new CellElement( "height", "키", 15, new MessageFormat( "{0,number,#,##0.0#} cm" ) ) );
 * 		cellElements.add( new CellElement( "gender", "성별", 10 ) );
 * 
 * 	excelConfig.setCellElements( cellElements );
 * 
 * 	//	번호 항목 표시 여부 및 항목 이름
 * 	//	넘버링이 필요 없을 경우 지정하지 않음(null)
 * 	excelConfig.setNumberingCellElement( new CellElement( "", "번호", 10 ) );
 * 
 */

/**
 * CellElement 를 생성 할 때는 다음과 같이 생성한다.<br>
 * <br>
 * new CellElement( "필드명", "엑셀에 표시할 필드명", 필드폭(정수) )<br> 
 * 또는<br>
 * new CellElement( "필드명", "엑셀에 표시할 필드명", 필드폭(정수), new java.text.MessageFormat(format) )<br>
 * <br>
 * MessageFormat 을 설정 할 경우 필드에 표시되는 내용은 문자형(Label)로 처리 된다.<br>
 */
public class CellElement {

	/**	데이터 객체의 필드명 */
	private String fieldName;

	/** 항목 상단에 표시될 이름 */
	private String cellName;

	/**	항목의 너비 */
	private int cellWidth;

	/**	항목 데이터 포맷 */
	private Format format;

	public CellElement() {}

	public CellElement( String fieldName, String cellName, int cellWidth ) {
		this.fieldName = fieldName;
		this.cellName  = cellName;
		this.cellWidth = cellWidth;
	}

	public CellElement( String fieldName, String cellName, int cellWidth, Format format ) {
		this.fieldName = fieldName;
		this.cellName  = cellName;
		this.cellWidth = cellWidth;
		this.format    = format;
	}

	//	getters and setters

	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getCellName() {
		return cellName;
	}
	public void setCellName(String cellName) {
		this.cellName = cellName;
	}

	public int getCellWidth() {
		return cellWidth;
	}
	public void setCellWidth(int cellWidth) {
		this.cellWidth = cellWidth;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

}
