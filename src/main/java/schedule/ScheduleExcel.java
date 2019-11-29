package schedule;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import combination.GeneratePermutations;
import json.Subject;
import json.Time;


public class ScheduleExcel {
	public static final int COLUMN_NAME_ID = 0;
	public static final int COLUMN_CREDIT_ID = 1;
	public static final int COLUMN_CODE_ID = 2;
	public static final int COLUMN_TIME_ID = 3;

	//ArrayList (all code of a subject)
	public static List<ArrayList<Subject>> codePerTime() throws IOException{
		Scanner scan = new Scanner(System.in);

		// Imports subject from keyboard
		// List all subject want to registration
		List<String> selectedSubject = ImportSubject.selectedSubject(scan);

		/* List all subject(
		List all subject have the same code) */
		ArrayList<ArrayList<Subject>> codePerTime = new ArrayList<>();

		for(String codeSubject: selectedSubject) {
			//List all subject have the same code
			ArrayList<Subject> listSubject = getListSubjects(codeSubject);

			codePerTime.add(listSubject);
		}
		return codePerTime;
	}

	//List all subject have the same code
	public static ArrayList<Subject> getListSubjects(String codeSubject) throws IOException {
		List<Subject> listSubject = new ArrayList<>();

		Subject[] subjects = json.InputJson.getDataSubject();

		for(Subject subject: subjects)
			if(subject.getCode().startsWith(codeSubject)) listSubject.add(subject);

		return (ArrayList<Subject>) listSubject;
	}

	/*List all
	(code of subjects in a schedule) after combination */
	public static List<ArrayList<String>> list() throws IOException{
		List<String> result = new ArrayList<>();

		List<ArrayList<String>> list = new ArrayList<>();

		List<ArrayList<Subject>> codePerTime = codePerTime();

		//codePerTime.forEach(e -> System.out.println(e));

		//Combination
		GeneratePermutations.generatePermutation(codePerTime, result, 0, "");

		//System.out.println(result.size());
		//result.forEach(e -> System.out.println(e));

		result.parallelStream()
		.forEach(value -> {
			List<String> val = Pattern.compile("-").splitAsStream(value)
					.filter(element -> element != "")
					.parallel()
					.collect(Collectors.toList());
			list.add((ArrayList<String>) val);
		});

		return list;
	}

	public static void returnExcelSchedule() throws IOException {
		SXSSFWorkbook TKB = new SXSSFWorkbook();

		Subject[] subjects = json.InputJson.getDataSubject();

		/*List all
		(code of subjects in a schedule) after combination */
		List<ArrayList<String>> list = list();

		int listSize = list.size();

		// schedule index j
		for(int j = 0; j < listSize; j++) {

			List<ArrayList<Time>> arrayTime = new ArrayList<>();
			List<String> tempArrayName = new ArrayList<>();
			List<String> tempArrayCode = new ArrayList<>();

			int listJSize = list.get(j).size();
			for(int i = 0; i < listJSize; i++)
				for(Subject subject: subjects)
					if(list.get(j).get(i).equals(subject.getCode())) {
						List<Time> temp = new ArrayList<> (Arrays.asList(subject.getTime()));
						arrayTime.add((ArrayList<Time>) temp);
						tempArrayName.add(subject.getName());
						tempArrayCode.add(subject.getCode());

						break;
					}

			String[] arrayName = tempArrayName.toArray(new String[0]);
			String[] arrayCode = tempArrayCode.toArray(new String[0]);
			
			TKB = CreateSchedule.createSchedule(TKB, arrayTime, arrayName, j, arrayCode);
		}

		//Write File
		File file = new File("./output/TKB.xlsx");
		file.getParentFile().mkdirs();

		FileOutputStream outFile = new FileOutputStream(file);
		TKB.write(outFile);
		//System.out.println("Created file: " + file.getAbsolutePath());
		TKB.close();
	}

	//Create frames of excel
	public static SXSSFWorkbook scheduleFrames(SXSSFWorkbook schedule,String index) {
		SXSSFSheet sheet = schedule.createSheet("TKB " + index);

		int rownum = 0;
		Row row;
		Cell cell;

		row = sheet.createRow(rownum);

		//Tiet
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("Tiet");

		//T2-7
		for(int i = 2; i < 7; i++) {
			cell = row.createCell(i - 1, CellType.STRING);
			cell.setCellValue("T" + i);
		};

		//First collum 1-10
		for(rownum = 1; rownum < 12; rownum++) {
			row = sheet.createRow(rownum);
			if(rownum == 11) break;
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("" + rownum);
		}

		return schedule;
	}
}
