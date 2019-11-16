package schedule;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import json.Time;

public class CreateSchedule {

	public static SXSSFWorkbook createSchedule(SXSSFWorkbook TKB, List<ArrayList<Time>> arrayTime, String[] name, int index, String[] codeName) {
		TKB = ScheduleExcel.scheduleFrames(TKB, "" + index);
		SXSSFSheet tempSheet = TKB.getSheet("TKB " + index);
		Row rowDate;
		Cell cellDate = null;
		int colnum = 0;

		int arrayTimeSize = arrayTime.size();
		for(int j = 0; j < arrayTimeSize; j++)
			for(Time time : arrayTime.get(j)) {

				// get collum
				rowDate = tempSheet.getRow(0);
				for(int i = 1; i < 6; i++) {
					cellDate = rowDate.getCell(i);

					if(cellDate.getStringCellValue().equals(time.getDay())) {
						colnum = i;
						break;
					}
				}

				//Get row
				int rownumfrom = time.getTimeStart();
				int rownumto = time.getTimeEnd();

				//Write Time to TKB
				for(int i = rownumfrom; i <= rownumto; i++) {
					rowDate = tempSheet.getRow(i);
					cellDate = rowDate.getCell(colnum);

					if(cellDate != null) {
						String compareString = time.getRoom() + "\n" + name[j] + "\n(" + codeName[j] +")";
						String cellDateString = cellDate.getStringCellValue();
						if(cellDateString.equals(compareString)) break;
						else if (!cellDateString.equals(compareString)) {
							System.out.println("Deleted " + index);
							TKB.removeSheetAt(TKB.getSheetIndex("TKB " + index));
							return TKB;
						}
					}
					else {
						cellDate = rowDate.createCell(colnum, CellType.STRING);
						cellDate.setCellValue(time.getRoom() + "\n" + name[j] + "\n(" + codeName[j] + ")");
					}
				}

				String compareString = time.getRoom() + "\n" + name[j] + "\n(" + codeName[j] +")";
				String cellDateString = cellDate.getStringCellValue();
				if(cellDateString.equals(compareString)) continue;
				else if (!cellDateString.equals(compareString)) {
					TKB.removeSheetAt(TKB.getSheetIndex("TKB " + index));
					return TKB;
				}
			}

		return TKB;
	}

}
