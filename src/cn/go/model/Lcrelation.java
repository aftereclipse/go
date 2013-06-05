package cn.go.model;

public class Lcrelation {
	private int id;
	private int cid;
	private int lid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	@Override
	public String toString() {
		return "Lcrelation [id=" + id + ", cid=" + cid + ", lid=" + lid + "]";
	}

}
