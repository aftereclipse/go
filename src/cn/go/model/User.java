package cn.go.model;

import java.sql.Date;

public class User {
	private int id;
	private String uid;
	private String pwd;
	private Date timetag;

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

	public Date getTimetag() {
		return timetag;
	}

	public void setTimetag(Date timetag) {
		this.timetag = timetag;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", uid=" + uid + ", pwd=" + pwd + "]";
	}

}
