package cn.go.util;

public enum DbOptEnum {

	// 插入
	INSERT(0),

	// 更新
	UPDATE(1),

	// 删除
	DELETE(2);

	int opt;

	private DbOptEnum(int opt) {
		this.opt = opt;
	}

	public int intValue() {
		return opt;
	}

	public String toString() {
		return String.valueOf(opt);
	}
}
