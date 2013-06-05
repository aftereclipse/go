package cn.go.util;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 从属性文件config.properties文件获取常量配置值
 * 
 * @author lbb
 * 
 */
public final class ConstantTool {

	private static final Log log = LogFactory.getLog(ConstantTool.class);

	private static final String FILENAME = "config.properties";

	// 属性键
	private static final String LOGIN_USER_KEY = "key.loginUser";
	private static final String TIP_KEY = "key.tip";
	private static final String MODEL_KEY = "key.model";
	private static final String DATALIST_KEY = "key.dataList";
	private static final String PAGER_KEY = "key.pager";
	private static final String MAX_PAGER_SHOW_LENGTH_KEY = "key.maxPagerShowLength";
	private static final String CURRENT_PAGE_KEY = "key.currentPage";
	private static final String COUNT_PER_PAGE_KEY = "key.countPerPage";

	private static final String PAGER_DEFAULT_CURRENT_PAGE_KEY = "key.pager.defaultCurrentPage";
	private static final String PAGER_COUNT_PER_PAGE_KEY = "key.pager.countPerPage";
	private static final String PAGER_MAX_PAGE_SHOW_LENGTH_KEY = "key.pager.maxPageShowLength";

	private static final String ENCODING_NEED_KEY = "key.encoding.need";
	private static final String ENCODING_SOURCE_KEY = "key.encoding.source";
	private static final String ENCODING_TARGET_KEY = "key.encoding.target";

	private static final String WEBSITE_PREFIX_KEY = "key.website.prefix";
	private static final String WEBSITE_NEEDPREFIX_KEY = "key.website.needprefix";

	private static final String EMAIL_NOTIFICATION_NEED_KEY = "key.email.notification.need";
	private static final String EMAIL_NOTIFICATION_LABEL_KEY = "key.email.notification.label";

	// 属性值
	public static final String VALUE_LOGIN_USER;
	public static final String VALUE_TIP;
	public static final String VALUE_MODEL;
	public static final String VALUE_DATALIST;
	public static final String VALUE_PAGER;
	public static final String VALUE_MAX_PAGER_SHOW_LENGTH;
	public static final String VALUE_CURRENT_PAGE;
	public static final String VALUE_COUNT_PER_PAGE;

	public static final int VALUE_PAGER_DEFAULT_CURRENT_PAGE;
	public static int VALUE_PAGER_COUNT_PER_PAGE;
	public static final int VALUE_PAGER_MAX_PAGE_SHOW_LENGTH;

	public static final boolean VALUE_ENCODING_NEED;
	public static final String VALUE_ENCODING_SOURCE;
	public static final String VALUE_ENCODING_TARGET;

	public static final String VALUE_WEBSITE_PREFIX;
	public static final boolean VALUE_WEBSITE_NEEDPREFIX;

	public static final boolean VALUE_EMAIL_NOTIFICATION_NEED;
	public static final String VALUE_EMAIL_NOTIFICATION_LABEL;

	static {
		// 加载指定的属性文件内容
		Properties prop = PropertiesTool.loadProperties(FILENAME);

		VALUE_LOGIN_USER = prop.getProperty(LOGIN_USER_KEY.trim());
		VALUE_TIP = prop.getProperty(TIP_KEY.trim());
		VALUE_MODEL = prop.getProperty(MODEL_KEY.trim());
		VALUE_DATALIST = prop.getProperty(DATALIST_KEY.trim());
		VALUE_PAGER = prop.getProperty(PAGER_KEY.trim());
		VALUE_MAX_PAGER_SHOW_LENGTH = prop
				.getProperty(MAX_PAGER_SHOW_LENGTH_KEY.trim());
		VALUE_CURRENT_PAGE = prop.getProperty(CURRENT_PAGE_KEY.trim());
		VALUE_COUNT_PER_PAGE = prop.getProperty(COUNT_PER_PAGE_KEY.trim());

		VALUE_PAGER_DEFAULT_CURRENT_PAGE = Integer.parseInt(prop
				.getProperty(PAGER_DEFAULT_CURRENT_PAGE_KEY.trim()));
		VALUE_PAGER_COUNT_PER_PAGE = Integer.parseInt(prop
				.getProperty(PAGER_COUNT_PER_PAGE_KEY.trim()));
		VALUE_PAGER_MAX_PAGE_SHOW_LENGTH = Integer.parseInt(prop
				.getProperty(PAGER_MAX_PAGE_SHOW_LENGTH_KEY.trim()));

		VALUE_ENCODING_NEED = Boolean.parseBoolean(prop
				.getProperty(ENCODING_NEED_KEY.trim()));
		VALUE_ENCODING_SOURCE = prop.getProperty(ENCODING_SOURCE_KEY.trim());
		VALUE_ENCODING_TARGET = prop.getProperty(ENCODING_TARGET_KEY.trim());

		VALUE_WEBSITE_PREFIX = prop.getProperty(WEBSITE_PREFIX_KEY.trim());
		VALUE_WEBSITE_NEEDPREFIX = Boolean.parseBoolean(prop
				.getProperty(WEBSITE_NEEDPREFIX_KEY.trim()));

		VALUE_EMAIL_NOTIFICATION_NEED = Boolean.parseBoolean(prop
				.getProperty(EMAIL_NOTIFICATION_NEED_KEY.trim()));
		VALUE_EMAIL_NOTIFICATION_LABEL = prop
				.getProperty(EMAIL_NOTIFICATION_LABEL_KEY);

		// 日志
		log.info("Loading properties from file[" + FILENAME + "] ...");

		log.info("Load Param [" + LOGIN_USER_KEY + "] -> " + VALUE_LOGIN_USER);
		log.info("Load Param [" + TIP_KEY + "] -> " + VALUE_TIP);
		log.info("Load Param [" + MODEL_KEY + "] -> " + VALUE_MODEL);
		log.info("Load Param [" + DATALIST_KEY + "] -> " + VALUE_DATALIST);
		log.info("Load Param [" + PAGER_KEY + "] -> " + VALUE_PAGER);
		log.info("Load Param [" + MAX_PAGER_SHOW_LENGTH_KEY + "] -> "
				+ VALUE_MAX_PAGER_SHOW_LENGTH);
		log.info("Load Param [" + CURRENT_PAGE_KEY + "] -> "
				+ VALUE_CURRENT_PAGE);
		log.info("Load Param [" + COUNT_PER_PAGE_KEY + "] -> "
				+ VALUE_COUNT_PER_PAGE);

		log.info("Load Param [" + PAGER_DEFAULT_CURRENT_PAGE_KEY + "] -> "
				+ VALUE_PAGER_DEFAULT_CURRENT_PAGE);
		log.info("Load Param [" + PAGER_COUNT_PER_PAGE_KEY + "] -> "
				+ VALUE_PAGER_COUNT_PER_PAGE);
		log.info("Load Param [" + PAGER_MAX_PAGE_SHOW_LENGTH_KEY + "] -> "
				+ VALUE_PAGER_MAX_PAGE_SHOW_LENGTH);

		log.info("Load Param [" + ENCODING_NEED_KEY + "] -> "
				+ VALUE_ENCODING_NEED);
		log.info("Load Param [" + ENCODING_SOURCE_KEY + "] -> "
				+ VALUE_ENCODING_SOURCE);
		log.info("Load Param [" + ENCODING_TARGET_KEY + "] -> "
				+ VALUE_ENCODING_TARGET);

		log.info("Load Param [" + WEBSITE_PREFIX_KEY + "] -> "
				+ VALUE_WEBSITE_PREFIX);
		log.info("Load Param [" + WEBSITE_NEEDPREFIX_KEY + "] -> "
				+ VALUE_WEBSITE_NEEDPREFIX);
		log.info("Load Param [" + EMAIL_NOTIFICATION_NEED_KEY + "] -> "
				+ VALUE_EMAIL_NOTIFICATION_NEED);
		log.info("Load Param [" + EMAIL_NOTIFICATION_LABEL_KEY + "] -> "
				+ VALUE_EMAIL_NOTIFICATION_LABEL);

	}

	public static void setup() {
		log.info("Init ConstantTool ok! ");
	}
}
