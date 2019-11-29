package schedule;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;

public class WriteListCode {
	public static final int ROW_WRITE_LIST_CODE = 11;
	
	public static String toString(String[] codeName) {
		String temp = "";
		
		for(String e: codeName) {
			temp += e + "\n";
		}
		
		return temp;
	}
	
	public static void writeListCode(String[] codeName, SXSSFSheet tempSheet) {
		//System.out.println(codeName.toString());
		
		
		Row row = tempSheet.getRow(ROW_WRITE_LIST_CODE);
		Cell cell = row.createCell(0, CellType.STRING);
		
		cell.setCellValue(toString(codeName));
	}
}
