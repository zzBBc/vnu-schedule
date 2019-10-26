package saveExcel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import dataSubject.InfoSubject;
import dataSubject.ArrayListSubject;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class CreateExcel {
	public static final int COLUMN_NAME_ID = 0;
	public static final int COLUMN_CREDIT_ID = 1;
	public static final int COLUMN_CODE_ID = 2;
	public static final int COLUMN_TIME_ID = 3;
	
	private static CellStyle createStyleForTitle(HSSFWorkbook workbook) {
		Font font = workbook.createFont();
		font.setBold(true);
		
		CellStyle style = workbook.createCellStyle();
		style.setFont(font);
		
		return style;
	}
	
	public static void main(String[] args) throws IOException{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Subject");
		
		ArrayList<InfoSubject> subject = ArrayListSubject.info("./AmazonloggedIn.txt");
		
		int rownum = 0;
		Cell cell;
		Row row;
		
		CellStyle style = createStyleForTitle(workbook);
		
		row = sheet.createRow(rownum);
		
		//Name
		cell = row.createCell(COLUMN_NAME_ID, CellType.STRING);
		cell.setCellValue("Mon hoc");
		cell.setCellStyle(style);
		//So Tin
		cell = row.createCell(COLUMN_CREDIT_ID, CellType.STRING);
		cell.setCellValue("So tin");
		cell.setCellStyle(style);
		//Ma mon hoc
		cell = row.createCell(COLUMN_CODE_ID, CellType.STRING);
		cell.setCellValue("Ma mon hoc");
		cell.setCellStyle(style);
		//Thoi gian
		cell = row.createCell(COLUMN_TIME_ID, CellType.STRING);
		cell.setCellValue("Thoi gian");
		cell.setCellStyle(style);
		
		//Data
		for(InfoSubject value: subject) {
			rownum++;
			row = sheet.createRow(rownum);
			
			//getName
			cell = row.createCell(COLUMN_NAME_ID, CellType.STRING);
			cell.setCellValue(value.getName());
			//getSoTin
			cell = row.createCell(COLUMN_CREDIT_ID, CellType.STRING);
			cell.setCellValue(value.getSoTin());
			//getCode
			cell = row.createCell(COLUMN_CODE_ID, CellType.STRING);
			cell.setCellValue(value.getCode());
			//getTime
			cell = row.createCell(COLUMN_TIME_ID, CellType.STRING);
			cell.setCellValue(value.getTime());
			
		}
		
		File file = new File("./dataSubject.xls");
		file.getParentFile().mkdirs();
		
		FileOutputStream outFile = new FileOutputStream(file);
		workbook.write(outFile);
		System.out.println("Created file: " + file.getAbsolutePath());
		
	}
}
