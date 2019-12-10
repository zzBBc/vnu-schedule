package optimizeSchedule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class OptimizeSchedule {
	public static final int TIME_END_IN_MORNING = 5;
	public static final int TIME_END_IN_EVENING = 10;
	public static final int MONDAY = 1;
	public static final int FRIDAY = 5;

	// Get Free Time in a sheet
	public static int getFreeTime(XSSFSheet scheduleSheet) {
		// Free time morning
		int getFreeTime = 0;

		for (int colnum = MONDAY; colnum <= FRIDAY; colnum++) {
			int count = 0;

			for (int rownum = 1; rownum <= TIME_END_IN_MORNING; rownum++) {
				Row row = scheduleSheet.getRow(rownum);
				Cell cell = row.getCell(colnum);

				if (cell == null)
					count++;
			}
			if (count == 5)
				getFreeTime++;

			// Free time evening
			count = 0;

			for (int rownum = 6; rownum <= TIME_END_IN_EVENING; rownum++) {
				Row row = scheduleSheet.getRow(rownum);
				Cell cell = row.getCell(colnum);

				if (cell == null)
					count++;
			}

			if (count == 5)
				getFreeTime++;
		}

		return getFreeTime;
	}

	// optimal schedule
	public static XSSFWorkbook optimizeSchedule(XSSFWorkbook schedule) {
		int maxFreeTime = 0;

		int lastNumberOfSheets = schedule.getNumberOfSheets();
		int numberOfSheets = schedule.getNumberOfSheets();
		// System.out.println(numberOfSheets);
		// Get max free time a
		for (int sheetnum = 0; sheetnum < numberOfSheets; sheetnum++) {
			// System.out.println(sheetnum);
			XSSFSheet scheduleSheet = schedule.getSheetAt(sheetnum);
			int sheetFreeTime = getFreeTime(scheduleSheet);

			if (sheetFreeTime > maxFreeTime)
				maxFreeTime = sheetFreeTime;
			// System.out.println(schedule.getSheetName(sheetnum));
		}

		System.out.println();
		System.out.println(maxFreeTime);
		for (int sheetnum = 0; sheetnum < numberOfSheets; sheetnum++) {
			XSSFSheet scheduleSheet = schedule.getSheetAt(sheetnum);
			// System.out.println(scheduleSheet.getRow(0).getCell(0));
			int sheetFreeTime = getFreeTime(scheduleSheet);

			if (sheetFreeTime != maxFreeTime) {
				// System.out.println(sheetFreeTime);
				// System.out.println("deleted " + schedule.getSheetName(sheetnum));
				schedule.removeSheetAt(sheetnum);
				// System.out.println(schedule.getNumberOfSheets());
				numberOfSheets -= 1;
			}
		}

		if (numberOfSheets != lastNumberOfSheets)
			optimizeSchedule(schedule);

		return schedule;
	}

	@SuppressWarnings("resource")
	public static void returnOptimizedSchedule() throws IOException {
		FileInputStream scheduleInputStream = new FileInputStream(new File("./output/TKB.xlsx"));
		XSSFWorkbook schedule = new XSSFWorkbook(scheduleInputStream);
		schedule = optimizeSchedule(schedule);

		File file = new File("./output/TKB.xlsx");
		file.getParentFile().mkdirs();

		FileOutputStream outputFile = new FileOutputStream(file);
		schedule.write(outputFile);
		schedule.close();
		System.out.println("Created file: " + file.getAbsolutePath());
	}
}
