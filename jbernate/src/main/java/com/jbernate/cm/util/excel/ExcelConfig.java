package com.jbernate.cm.util.excel;

import java.util.List;

public class ExcelConfig {

	/** 
	 * 파일명
	 */
	private String fileName;

	/** 
	 * 상단에 표시할 제목<br>
	 * 병합할 셀은 다음의 cellElements 와 번호 항목 표시 여부에 따라 자동으로 결정 됨
	 */
	private String excelTitle;

	/** 
	 * 엑셀 파일의 시트 제목
	 */
	private String sheetName;

	/** 
	 * 엑셀 파일에서 사용 할 필드 목록을 정함
	 */
	private List<CellElement> cellElements;

	/** 
	 * 넘버 컬럼(필요시 설정)
	 */
	private CellElement numberingCellElement;

	//	getters and setters

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getExcelTitle() {
		return excelTitle;
	}
	public void setExcelTitle(String excelTitle) {
		this.excelTitle = excelTitle;
	}

	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public List<CellElement> getCellElements() {
		return cellElements;
	}
	public void setCellElements(List<CellElement> cellElements) {
		this.cellElements = cellElements;
	}

	public CellElement getNumberingCellElement() {
		return numberingCellElement;
	}
	public void setNumberingCellElement(CellElement numberingCellElement) {
		this.numberingCellElement = numberingCellElement;
	}

}
