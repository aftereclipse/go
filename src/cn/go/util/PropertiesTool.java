package cn.go.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 属性文件操作加载工具类
 * 
 * @author qingtian 
 * 
 * 2011-10-8 上午10:28:24
 */
public final class PropertiesTool {

	private static final Log log = LogFactory.getLog(PropertiesTool.class);

	/**
	 * 加载指定名称的属性文件
	 */
	public static Properties loadProperties(String properties) {
		Properties prop = new Properties();
		InputStream in = PropertiesTool.class.getClassLoader()
				.getResourceAsStream(properties);
		try {
			prop.load(in);
		} catch (IOException e) {
			log.error(e.getCause());
		}
		return prop;
	}

}
