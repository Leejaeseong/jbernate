package com.jbernate.cm.util.excel.impl;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.jbernate.cm.util.excel.CellElement;
import com.jbernate.cm.util.excel.ExcelConfig;
import com.jbernate.cm.util.excel.ExcelMake;
import com.jbernate.cm.util.reflect.ReflectUtil;

public class PlainExcelMake implements ExcelMake {

	@Override
	public void makeExcel( Workbook workbook, List<?> dataList, ExcelConfig config ) throws Exception {

		int rowIndex = 0;
		int colIndex = 0;

		//	별도의 넘버링을 사용할 경우
		CellElement numberingCellElement = config.getNumberingCellElement();

		List<CellElement> cellElements = null;

		if ( numberingCellElement != null ) {

			cellElements = new ArrayList<CellElement>();

			cellElements.add( null );

			cellElements.addAll( config.getCellElements() );

		} else {
			cellElements = config.getCellElements();
		}

		//	시트만들기
		HSSFSheet sheet = ( HSSFSheet ) workbook.createSheet( config.getSheetName() );
//		sheet.setDefaultColumnWidth(30);

//		//	헤더 스타일
//		HSSFFont font = workbook.createFont();
//			font.setFontName( "바탕" );
//			font.setBoldweight( HSSFFont.BOLDWEIGHT_BOLD );
//			font.setColor( HSSFColor.WHITE.index );
//
//		HSSFCellStyle style = workbook.createCellStyle();
//			style.setFillForegroundColor( HSSFColor.BLUE.index );
//			style.setFillPattern( CellStyle.SOLID_FOREGROUND );
//			style.setFont( font );

		//	헤더 row 추가
		HSSFRow header = sheet.createRow( rowIndex++ );

		//	별도의 넘버링 셀을 지정한 경우
		if ( numberingCellElement != null ) {

			HSSFCell cell = header.createCell( colIndex++ );

			cell.setCellValue( numberingCellElement.getCellName() );
//			cell.setCellStyle( style );
	    }

		//	표시 하고자 하는 필드에 해당하는 getter method 목록을 가져옴
		List<String> fieldNameList = new ArrayList<String>();

		for ( CellElement cellElement : cellElements ) {

			if ( cellElement == null ) continue;

			HSSFCell cell = header.createCell( colIndex++ );

			cell.setCellValue( cellElement.getCellName() );
//			cell.setCellStyle( style );

			fieldNameList.add( cellElement.getFieldName() );
		}

		//	데이터 List 를 엑셀로 출력

		if ( dataList.size() > 0 ) {

			int numbering = 1;

			//	fieldNameList 에 해당하는 getter 메소드 목록을 가져 옴
			List<Method> getterMethods = ReflectUtil.getGetterMethods( dataList.get(0).getClass(), fieldNameList );

			Object[] args = null;

			for ( Object data : dataList ) {

				colIndex = 0;

				HSSFRow row = sheet.createRow( rowIndex++ );

				//	별도의 넘버링을 사용할 경우
				if ( numberingCellElement != null ) {
					row.createCell( colIndex++ ).setCellValue( numbering++ );
			    }

				for ( Method getterMethod : getterMethods ) {
					
					Object value = getterMethod.invoke( data, args );

					if ( value == null ) {

						row.createCell( colIndex++ ).setCellValue( "" );

					} else {

						String returnTypeName = getterMethod.getReturnType().getSimpleName();

						if ( cellElements.get( colIndex ).getFormat() == null ) {

							if ( returnTypeName.equals( "int"    ) || returnTypeName.equals( "Integer" ) ) {

								row.createCell( colIndex++ ).setCellValue( ( Integer ) value );

							} else if ( returnTypeName.equals( "long"   ) || returnTypeName.equals( "Long"    ) ) {

								row.createCell( colIndex++ ).setCellValue( ( Long    ) value );

							} else if ( returnTypeName.equals( "float"  ) || returnTypeName.equals( "Float"   ) ) {

								row.createCell( colIndex++ ).setCellValue( ( Float   ) value );

							} else if ( returnTypeName.equals( "double" ) || returnTypeName.equals( "Double"  ) ) {

								row.createCell( colIndex++ ).setCellValue( ( Double  ) value );

							} else if ( returnTypeName.equals( "BigDecimal" ) ) {
								
								row.createCell( colIndex++ ).setCellValue( ( ( BigDecimal  ) value ).toString() );
								
							} else if ( returnTypeName.equals( "Date"   ) ) {

								row.createCell( colIndex++ ).setCellValue( ( Date    ) value );

							} else {

								row.createCell( colIndex++ ).setCellValue( String.valueOf( value ) );

							}

						} else {

							Object[] arrValue = { value };
							row.createCell( colIndex++ ).setCellValue( cellElements.get( colIndex++ ).getFormat().format( arrValue ) );

						}
					}

				}	//	for getterMethod

    		}	//	for dataList
		
        }	//	if

	}

}
