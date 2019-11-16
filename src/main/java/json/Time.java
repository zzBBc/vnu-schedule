package json;

public class Time {

	private String day;
	private int timeStart;
	private int timeEnd;
	private String room;

	public Time() {

	}

	public Time(String day, int timeStart, int timeEnd, String room) {
		this.day = day;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.room = room;
	}

	public String getDay() {
		return day;
	}

	public String getRoom() {
		return room;
	}

	public int getTimeEnd() {
		return timeEnd;
	}

	public int getTimeStart() {
		return timeStart;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public void setTimeEnd(int timeEnd) {
		this.timeEnd = timeEnd;
	}

	public void setTimeStart(int timeStart) {
		this.timeStart = timeStart;
	}
}
