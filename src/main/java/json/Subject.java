package json;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Subject {

	public static final int DAY_NUM = 0;
	public static final int TIME_START_NUM = 1;
	public static final int TIME_END_NUM = 2;
	public static final int ROOM_NUM = 3;

	private String name;
	private int credit;
	private String code;

	private Time[] time;

	public Subject() {

	}

	public String getCode() {
		return code;
	}
	public int getCredit() {
		return credit;
	}

	public String getName() {
		return name;
	}
	public Time[] getTime() {
		return time;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTime(String contentLine) {
		List<Time> tempTime = new ArrayList<>();
		String a = contentLine.replaceAll("[(]", "");
		String b =  a.replaceAll("[)]", "");

		//System.out.println(b);

		List<String> c = Pattern.compile(",")
				.splitAsStream(b)
				.parallel()
				.collect(Collectors.toList());

		c.forEach(e -> {
			String[] aTimeInDay = e.split("-");

			Time toTimeObject = new Time();

			toTimeObject.setDay(aTimeInDay[DAY_NUM]);
			toTimeObject.setTimeStart(Integer.parseInt(aTimeInDay[TIME_START_NUM]));
			toTimeObject.setTimeEnd(Integer.parseInt(aTimeInDay[TIME_END_NUM]));
			toTimeObject.setRoom(aTimeInDay[ROOM_NUM]);

			tempTime.add(toTimeObject);
		});
		Time[] time = tempTime.toArray(new Time[0]);
		this.time = time;
	}

	@Override
	public String toString() {
		return "Ten mon hoc: " + getName() + "\n" +
				"So tin: " + getCredit() +  "\n" +
				"Ma mon hoc: " + getCode() + "\n" +
				"Thoi gian: " + getTime() + "\n";
	}
}
