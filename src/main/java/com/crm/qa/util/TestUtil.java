package com.crm.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase {

	public static int PageLoadTimeout = 30;
	public static int Imlicitlywait = 10;
	public static String TESTDATA_SHEETPATH = System.getProperty("user.dir")
			+ "\\src\\main\\java\\com\\crm\\qa\\testdata\\CRM Project.xlsx";
	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	static FileInputStream fis;

	public void switchToFrame() {
		getdriver().switchTo().frame("mainpanel");
	}

	public static Object[][] excelFileData(String sheetname) {
		try {
			fis = new FileInputStream(TESTDATA_SHEETPATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wb = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sheet = wb.getSheet(sheetname);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {

				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
			}
		}
		return data;
	}

	

	

	public static void ExtentReporterNG2() {

	}

}
