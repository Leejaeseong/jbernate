package com.jbernate.cm.resolver;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.jbernate.cm.util.excel.ExcelConfig;
import com.jbernate.cm.util.excel.ExcelMake;
import com.jbernate.cm.util.excel.impl.PlainExcelMake;

/**
 * This class builds an Excel spreadsheet document using Apache POI library.
 * @author www.codejava.net
 *
 */
public class ExcelDownView extends AbstractExcelView {
 
	@Override
	protected void buildExcelDocument( Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response ) throws Exception {

		ExcelConfig config = ( ExcelConfig ) model.get( "config" );

		String userAgent = request.getHeader("User-Agent");

		String fileName = config.getFileName();

		if ( userAgent.indexOf( "MSIE" ) > -1 ) {
			fileName = URLEncoder.encode( fileName, "utf-8" );
		} else {
			fileName = new String( fileName.getBytes( "utf-8" ), "iso-8859-1" );
		}

		response.setHeader( "Content-Disposition", "attachment; filename=\"" + fileName + "\";" );
		response.setHeader( "Content-Transfer-Encoding", "binary" );

		List<?> dataList = (List<?>) model.get( "viewData" );

		String excelMakeType = ( String ) model.get( "excelMakeType" );

		ExcelMake excel = null;

		if ( excelMakeType.equals( ExcelMake.PLAIN_XLS ) ) {
			excel = new PlainExcelMake();
			excel.makeExcel( workbook, dataList, config );
		}
    }
 
}