package saveExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class SetNameSubject {
	public static final int COLUMN_DATASUBJECT_NAME_ID = 0;
	public static final int COLUMN_DATASUBJECT_CREDIT_ID = 1;
	public static final int COLUMN_DATASUBJECT_CODE_ID = 2;
	public static final int COLUMN_DATASUBJECT_TIME_ID = 3;
	
	public static final int COLUMN_NAMESUBJECT_CODE_ID = 0;
	public static final int COLUMN_NAMESUBJECT_NAME_ID = 1;
	
	public boolean compareCode(String nameSubject, String dataSubject) {
		if(nameSubject == dataSubject) return true;
		return false;
	}
	public static void main(String[] args) throws IOException {
		FileInputStream nameInputStream = new FileInputStream(new File("./nameSubject.xls"));
		HSSFWorkbook nameSubject = new HSSFWorkbook(nameInputStream);
		HSSFSheet nameSheet = nameSubject.getSheetAt(0);
		
		FileInputStream dataInputStream = new FileInputStream(new File("./dataSubject.xls"));
		HSSFWorkbook dataSubject = new HSSFWorkbook(dataInputStream);
		HSSFSheet dataSheet = dataSubject.getSheet("Subject");
		
		Cell nameCell;
		Cell dataCell;
		Row nameRow;
		Row dataRow;
		
		int dataRowNum = dataSheet.getLastRowNum();
		for(int datarownum = 1; datarownum <= dataRowNum; datarownum++) {
			dataRow = dataSheet.getRow(datarownum);
			dataCell = dataRow.getCell(COLUMN_DATASUBJECT_CODE_ID);
			
			int nameRowNum = nameSheet.getLastRowNum();
			for(int namerownum = 0; namerownum <= nameRowNum; namerownum++) {
				nameRow = nameSheet.getRow(namerownum);
				nameCell = nameRow.getCell(COLUMN_NAMESUBJECT_CODE_ID);
				
//				System.out.println(dataCell.getStringCellValue());
//				System.out.println(nameCell.getStringCellValue());
//				System.out.println(dataCell.getStringCellValue().startsWith(nameCell.getStringCellValue()));
//				System.out.println();
				String dataCellStringValue = dataCell.getStringCellValue();
				String nameCellStringValue = nameCell.getStringCellValue();
				if(dataCellStringValue.startsWith(nameCellStringValue)) {
					//System.out.println("IN!");
		
					dataRow.getCell(COLUMN_DATASUBJECT_NAME_ID)
						.setCellValue(nameRow.getCell(COLUMN_NAMESUBJECT_NAME_ID).getStringCellValue());
					
					//System.out.println(nameRow.getCell(COLUMN_NAMESUBJECT_NAME_ID).getStringCellValue());
					System.out.println(dataRow.getCell(COLUMN_DATASUBJECT_NAME_ID).getStringCellValue());
					//System.out.println();
					break;
				}
			}
		}
		File file = new File("./dataSubject.xls");
		file.getParentFile().mkdirs();
		
		FileOutputStream outFile = new FileOutputStream(file);
		dataSubject.write(outFile);
		System.out.println("Created file: " + file.getAbsolutePath());
		dataSubject.close();
		nameSubject.close();
	}

}
