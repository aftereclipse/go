package cn.go.model;


public class User {
	private int id;
	private String uid;
	private String pwd;
	private String timetag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getTimetag() {
		return timetag;
	}

	public void setTimetag(String timetag) {
		this.timetag = timetag;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", uid=" + uid + ", pwd=" + pwd + "]";
	}

}
