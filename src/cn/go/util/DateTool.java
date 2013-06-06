package cn.go.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {

	public static String now() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}
}
