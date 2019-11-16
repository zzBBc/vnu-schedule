package dataSubject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TakeSubject { // find name, time, so tin with regex

	public static boolean takeCode(String line) {
		Pattern regexFind = Pattern.compile("\\w{3}\\d{4}(\\s)?(\\w{1,11})?");
		Matcher afterMatcher = regexFind.matcher(line);
		return afterMatcher.matches();
	}
	// Boolean to find
	public static boolean takeName(String line) {// Can't get name, don't know the reason.
		//Pattern regexFind = Pattern.compile("(\\s)*(\\w{2,7}(\\s)?)+(\\d{1})?");

		Pattern regexFind = Pattern.compile("((\\s)*\\w{2,6}(\\s)?)*(\\d{1})?");
		Matcher afterMatcher = regexFind.matcher(line);

		//System.out.println(afterMatcher.matches());

		return afterMatcher.matches();
	}
	public static boolean takeSoTin(String line) {
		Pattern regexFind = Pattern.compile("\\d{1}"); // A Problem
		Matcher afterMatcher = regexFind.matcher(line);
		return afterMatcher.matches();
	}
	public static boolean takeTime(String line) { // A problem
		//Pattern regexFind = Pattern.compile("(\\w{1}\\d{1}[-][(]\\d{1}[-]\\d{1,2}[)][-]\\d{3}\\w{1}\\d{1}[,]?)+"); //T2-(3-5)-303T5
		//Pattern regexFindTime = Pattern.compile("\\w{1}\\d{1}[-][(]\\d{1}[-]\\d{1,2}[)][-](\\w{2,3}(\\s)?)+"); //T3-(6-8)-KTX M? Tr�
		//Pattern regexFindTimeOth = Pattern.compile("(\\w{1}\\d{1}[-][(]\\d{1}[-]\\d{1,2}[)][-]\\d{3}\\w{1}\\d{1}[,]?)+\\w{1}\\d{1}[-][(]\\d{1}[-]\\d{1,2}[)][-](\\w{3,5}(\\s)?)+(\\d{1})?"); // T4-(1-5)-Ph�ng m�y 1
		Pattern regexFind = Pattern.compile("(\\w{2}[-][(]\\d{1}[-]\\d{1,2}[)][-](\\w{2,5}(\\s)?)+(\\d{1})?[,]?)+");
		Matcher afterMatcher = regexFind.matcher(line);
		//Matcher afterMatcherTime = regexFindTime.matcher(line);
		//Matcher afterMatcherTimeOth = regexFindTimeOth.matcher(line);
		/* String[] arrOfStr = line.split(",");

	        for (String a : arrOfStr) {
	            System.out.println(a);
	    } */
		return afterMatcher.matches();
	}
}
