package com.cs.genericlib;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


/**
 * @author Meena Khan
 *
 */
public class ExcelLib {

	String filePath=Constants.excelFilePath;

	/**
	 * to get the cell of the sheet
	 */
	public String getCellData(String sheetName, int rowNum, int colNum) throws InvalidFormatException, IOException{
		FileInputStream excelInput=new FileInputStream(filePath);
		Workbook excelBook = WorkbookFactory.create(excelInput);
		Sheet sh = excelBook.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		Cell cell = row.getCell(colNum, Row.CREATE_NULL_AS_BLANK);
		if(cell.equals(null)){
			return cell.getStringCellValue().replaceAll(null, "");
		} 
		return cell.getStringCellValue();
	}

}
