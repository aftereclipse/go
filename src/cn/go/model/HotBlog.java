package cn.go.model;


public class Hotblog {
	private int id;
	private String title;
	private String link;
	private int click;
	private String timetag;

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

	public String getTimetag() {
		return timetag;
	}

	public void setTimetag(String timetag) {
		this.timetag = timetag;
	}

	@Override
	public String toString() {
		return "Hotblog [id=" + id + ", title=" + title + ", link=" + link
				+ ", click=" + click + ", timetag=" + timetag + "]";
	}

}
