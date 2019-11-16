package dataSubject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import json.Subject;


public class ListSubject {

	static int DAY_NUM = 0;
	static int TIME_START_NUM = 1;
	static int TIME_END_NUM = 2;
	static int ROOM_NUM = 3;

	//All properties of infoSubject are null?
	public static boolean check(Subject object) {

		//System.out.println(object.getTime());
		//System.out.println();

		return object.getCode() != null &&
				object.getName() != null &&
				object.getCredit() != 0 &&
				object.getTime() != null;
	}

	// save properties to subject
	public static Subject info(Subject subject, String contentLine){
		//System.out.println(contentLine);
		//System.out.println(subject.getName());
		//System.out.println();


		if(subject.getName() == null && TakeSubject.takeName(contentLine))
			subject.setName(contentLine);
		else if(subject.getCredit() == 0 && TakeSubject.takeSoTin(contentLine))
			subject.setCredit(Integer.parseInt(contentLine));
		else if(subject.getCode() == null && TakeSubject.takeCode(contentLine))
			subject.setCode(contentLine);
		else if(subject.getTime() == null && TakeSubject.takeTime(contentLine))
			subject.setTime(contentLine);

		//System.out.println(subject.getName());
		return subject;
	}

	// Get list subjects from file
	@SuppressWarnings({ "resource", "finally" })
	public static Subject[] subjects(String fileName){
		List<Subject> subjects = new ArrayList<>();

		//A subject
		Scanner scan;
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(fileName);
			scan = new Scanner(inputStream);
			String contentLine = scan.nextLine();
			Subject subject = new Subject();
			for(;;) {
				//System.out.println(subject.getName());
				//System.out.println(contentLine);

				contentLine = HandleString.xoa_dau(contentLine);

				//System.out.println(contentLine);

				subject = info(subject, contentLine);

				//System.out.println(subject.getName());
				//check subject != null => add subject to ArrayList => new subject

				//System.out.println(check(subject));

				if(check(subject)) {
					subjects.add(subject);
					subject = new Subject();
				}

				contentLine = scan.nextLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
		} finally{
			Subject[] tempSubject = subjects.toArray(new Subject[0]);
			return tempSubject;
		}
	}
}
