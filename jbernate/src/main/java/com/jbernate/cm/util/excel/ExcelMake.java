package com.jbernate.cm.util.excel;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

public interface ExcelMake {

	static String PLAIN_XLS  = "plainXls";
	static String PLAIN_XLSX = "plainXlsx";

	void makeExcel( Workbook workbook, List<?> dataList, ExcelConfig config ) throws Exception;
}
