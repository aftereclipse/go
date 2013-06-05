package cn.go.model;

import java.sql.Date;

public class Category {
	private int id;
	private String name;
	private int click;
	private Date timetag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getClick() {
		return click;
	}

	public void setClick(int click) {
		this.click = click;
	}

	public Date getTimetag() {
		return timetag;
	}

	public void setTimetag(Date timetag) {
		this.timetag = timetag;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", click=" + click
				+ ", timetag=" + timetag + "]";
	}

}
