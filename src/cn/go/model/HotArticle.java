package cn.go.model;

import java.util.Date;

public class Hotarticle {
	private int id;
	private String title;
	private String link;
	private int click;
	private Date timetag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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

}
