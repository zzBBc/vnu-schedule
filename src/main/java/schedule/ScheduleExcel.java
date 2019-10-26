package schedule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import combination.GeneratePermutations;
import dataSubject.InfoSubject;


public class ScheduleExcel {
	public static final int COLUMN_NAME_ID = 0;
	public static final int COLUMN_CREDIT_ID = 1;
	public static final int COLUMN_CODE_ID = 2;
	public static final int COLUMN_TIME_ID = 3;
	
	//ArrayList (InfoSubject of a code subject)
	public static ArrayList<InfoSubject> getTime(String code) throws IOException {
		ArrayList<InfoSubject> getSubject = new ArrayList<InfoSubject>();
		InfoSubject infoSubject = null;
		FileInputStream dataInputStream = new FileInputStream(new File("dataSubject.xls"));
		HSSFWorkbook dataSubject = new HSSFWorkbook(dataInputStream);
		HSSFSheet dataSheet = dataSubject.getSheetAt(0);
	
		Cell dataCell;
		Row dataRow;
		
		int dataRowNum = dataSheet.getLastRowNum();
		for(int datarownum = 0; datarownum <= dataRowNum; datarownum++) {
			dataRow = dataSheet.getRow(datarownum);
			dataCell = dataRow.getCell(COLUMN_CODE_ID);
			
			//get information of a subject
			if(dataCell.getStringCellValue().startsWith(code)) {
				infoSubject = new InfoSubject();
				infoSubject.setName(dataRow.getCell(COLUMN_NAME_ID).getStringCellValue());
				
				//System.out.println(dataCell.getStringCellValue());
				infoSubject.setCode(dataCell.getStringCellValue());
				infoSubject.setTime(dataRow.getCell(COLUMN_TIME_ID).getStringCellValue());
				infoSubject.setSoTin(dataRow.getCell(COLUMN_CREDIT_ID).getStringCellValue());
				getSubject.add(infoSubject);
			}
		}
		dataSubject.close();
		
		return getSubject;
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
		for(rownum = 1; rownum < 11; rownum++) {
			row = sheet.createRow(rownum);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("" + rownum);
		}
		
		return schedule;
	}
	
	//ArrayList (all code of a subject)
	public static ArrayList<ArrayList<String>> codePerTime() throws IOException{
		Scanner scan = new Scanner(System.in);
		
		// Imports subject from keyboard
		ArrayList<String> selectSubject = ImportSubject.selectedSubject(scan);
		ArrayList<ArrayList<String>> codePerTime = new ArrayList<ArrayList<String>>();
		for(String value: selectSubject) {
			ArrayList<InfoSubject> time = getTime(value);
			ArrayList<String> getCode = new ArrayList<String>();
			
			int timeSize = time.size();
			//System.out.println(timeSize);
			for(int i = 0; i< timeSize; i++) {
				getCode.add(time.get(i).getCode());
				//System.out.println(time.get(i).getCode());
			}
			//System.out.println();
			codePerTime.add(getCode);
		}
		return codePerTime;
	}
	
	//ArrayList (subjects in a schedule) after combination
	public static List<ArrayList<String>> list() throws IOException{
		ArrayList<String> result = new ArrayList<String>();
		List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		
		ArrayList<ArrayList<String>> codePerTime = codePerTime();
		
		codePerTime.forEach(e -> System.out.println(e));
		
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
		
//		for(String value: result) {
//			String[] val = value.split("-");
//			
//			int valLength = val.length;
//			
//			String[] temp = new String[valLength - 1];
//			
//			for(int i = 1; i < valLength; i++) {
//				temp[i - 1] = val[i];
//			}
//			list.add(temp);
//		}
		
		return list;
	}
	
	public static void main(String[] args) throws IOException {
		SXSSFWorkbook TKB = new SXSSFWorkbook();
		
		//subjects after combination
		List<ArrayList<String>> list = list();

		//System.out.println(list);
		ArrayList<InfoSubject> getInfo = new ArrayList<InfoSubject>();
		
		int listSize = list.size();
		for(int j = 0; j < listSize; j++) {
			
			List<ArrayList<ArrayList<String>>> arrayTime = new ArrayList<ArrayList<ArrayList<String>>>();
			
			int listJLength = list.get(j).size();
			String[] name = new String[listJLength];
			String[] codeName = new String[listJLength];
			for(int i = 0; i < listJLength; i++) {
				//Get String[] time from a Subject
				getInfo = getTime(list.get(j).get(i));
				String a = getInfo.get(0).getTime().replaceAll("[(]", "");
				String b = a.replaceAll("[)]", "");
				List<String> c = Pattern.compile(",").splitAsStream(b)
						.parallel()
						.collect(Collectors.toList());
				//String[] c = b.split(",");
				ArrayList<ArrayList<String>> time = new ArrayList<ArrayList<String>>();
				c.forEach(value -> {
					//System.out.println(value);
						List<String> temp = Pattern.compile("-").splitAsStream(value)
							.parallel()
							.collect(Collectors.toList());
						
						//System.out.println(temp);
						time.add((ArrayList<String>) temp);
					});
				//System.out.println(time);
				//System.out.println();
				arrayTime.add(time);
				
//				for(String value: c) time.add(value.split("-"));
//				arrayTime.add(time);
				name[i] = getInfo.get(0).getName();
				codeName[i] = getInfo.get(0).getCode();
			}
			//System.out.println(arrayTime);
			System.out.println();
			
			TKB = CreateSchedule.createSchedule(TKB, arrayTime, name, j, codeName);

		}
		
		//Write File
		File file = new File("./TKB.xlsx");
		file.getParentFile().mkdirs();
		
		FileOutputStream outFile = new FileOutputStream(file);
		TKB.write(outFile);
		System.out.println("Created file: " + file.getAbsolutePath());
		TKB.close();
	}
}
