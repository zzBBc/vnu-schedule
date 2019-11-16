package schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImportSubject {
	public static int SIZE = 0;
	public static List<String> selectedSubject(Scanner scan) {
		List<String> selectedSubject = new ArrayList<>();
		System.out.println("Nhap cac ma mon hoc");
		System.out.println("Nhap ok de ket thuc:");
		for(;;) {
			String subject = scan.nextLine();
			SIZE++;
			if(subject.equals("ok")) break;
			selectedSubject.add(subject);
		}

		return selectedSubject;
	}
}
