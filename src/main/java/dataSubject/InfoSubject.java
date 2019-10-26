package dataSubject;

public class InfoSubject {
	private String name;
	private String soTin;
	private String code;
	private String time;

	public InfoSubject(String name, String soTin, String code, String time) {
		this.name = name;
		this.code = code;
		this.time = time;
		this.soTin = soTin;
	}
	public InfoSubject() {}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSoTin() {
		// TODO Auto-generated method stub
		return soTin;
	}
	public void setSoTin(String soTin) {
		this.soTin = soTin;
	}
	
	public String toString() {
		return "Ten mon hoc: " + getName() + "\n" +
				"So tin: " + getSoTin() +  "\n" +
				"Ma mon hoc: " + getCode() + "\n" +
				"Thoi gian: " + getTime() + "\n";
	}
}
