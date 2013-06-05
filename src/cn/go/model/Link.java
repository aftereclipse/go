package cn.go.model;

import java.util.Date;

public class Link {

	private int id;
	private String link;
	private String title;
	private String content;
	private int click;
	private String color;
	private Date timetag;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getClick() {
		return click;
	}
	public void setClick(int click) {
		this.click = click;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Date getTimetag() {
		return timetag;
	}
	public void setTimetag(Date timetag) {
		this.timetag = timetag;
	}
	
}
