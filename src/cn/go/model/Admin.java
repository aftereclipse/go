package cn.go.model;

import java.sql.Date;

public class Admin {
	private int id;
	private String uname;
	private String passwd;
	private Date timetag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Date getTimetag() {
		return timetag;
	}

	public void setTimetag(Date timetag) {
		this.timetag = timetag;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", uname=" + uname + ", passwd=" + passwd
				+ ", timetag=" + timetag + "]";
	}

}
