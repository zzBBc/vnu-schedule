package dataSubject;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class ArrayListSubject {
	// save properties to subject
	public static InfoSubject info(InfoSubject subject, String contentLine){
		//System.out.println(contentLine);
		//System.out.println(subject.getName());
		//System.out.println();
		
		if(subject.getName() == null && TakeSubject.takeName(contentLine))
			subject.setName(contentLine);
		else if(subject.getSoTin() == null && TakeSubject.takeSoTin(contentLine))
			subject.setSoTin(contentLine);
		else if(subject.getCode() == null && TakeSubject.takeCode(contentLine))
			subject.setCode(contentLine);
		else if(subject.getTime() == null && TakeSubject.takeTime(contentLine)) 
			subject.setTime(contentLine);
		
		//System.out.println(subject.getName());
		return subject;
	}
	
	//ArrayList save subject data
	@SuppressWarnings({ "resource", "finally" })
	public static ArrayList<InfoSubject> info(String fileName){
		ArrayList<InfoSubject> info = new ArrayList<InfoSubject>();
		//A subject
		Scanner scan;
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(fileName);
			scan = new Scanner(inputStream);
			String contentLine = scan.nextLine();
			InfoSubject subject = new InfoSubject();
			for(;;) {
				//System.out.println(subject.getName());
				//System.out.println(contentLine);
				
				contentLine = HandleString.xoa_dau(contentLine);
				
				//System.out.println(contentLine);
				
				subject = info(subject, contentLine);
				
				//System.out.println(subject.getName());
				//check subject != null => add subject to ArrayList => new subject
				
				if(check(subject)) {
					info.add(subject);
					subject = new InfoSubject();
				}
				contentLine = scan.nextLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
		} finally{
			return info;
		}
	}
	
	//All properties of infoSubject are null?
	public static boolean check(InfoSubject object) {
		
		return object.getCode() != null && 
				object.getName() != null && 
				object.getSoTin() != null && 
				object.getTime() != null;
	}
}
