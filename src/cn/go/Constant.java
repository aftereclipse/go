package cn.go;

import cn.go.util.ConstantTool;

public interface Constant {
	// 请求域的键值
	public static final String LOGIN_USER = ConstantTool.VALUE_LOGIN_USER;
	public static final String TIP = ConstantTool.VALUE_TIP;
	public static final String MODEL = ConstantTool.VALUE_MODEL;
	public static final String DATALIST = ConstantTool.VALUE_DATALIST;
	public static final String PAGER = ConstantTool.VALUE_PAGER;
	public static final String MAX_PAGER_SHOW_LENGTH = ConstantTool.VALUE_MAX_PAGER_SHOW_LENGTH;
	public static final String CURRENT_PAGE = ConstantTool.VALUE_CURRENT_PAGE;
	public static final String COUNT_PER_PAGE = ConstantTool.VALUE_COUNT_PER_PAGE;

	public static final int PAGER_DEFAULT_CURRENT_PAGE = ConstantTool.VALUE_PAGER_DEFAULT_CURRENT_PAGE;
	public static int PAGER_COUNT_PER_PAGE = ConstantTool.VALUE_PAGER_COUNT_PER_PAGE;
	public static final int PAGER_MAX_PAGE_SHOW_LENGTH = ConstantTool.VALUE_PAGER_MAX_PAGE_SHOW_LENGTH;

	// 针对GET请求的编码设置
	public static final boolean ENCODING_NEED = ConstantTool.VALUE_ENCODING_NEED;
	public static final String ENCODING_SOURCE = ConstantTool.VALUE_ENCODING_SOURCE;
	public static final String ENCODING_TARGET = ConstantTool.VALUE_ENCODING_TARGET;

	// 部署时是否显示项目名配置
	public static final String WEBSITE_PREFIX = ConstantTool.VALUE_WEBSITE_PREFIX;
	public static final boolean WEBSITE_NEEDPREFIX = ConstantTool.VALUE_WEBSITE_NEEDPREFIX;

	// 邮件通知配置
	public static final boolean EMAIL_NOTIFICATION_NEED = ConstantTool.VALUE_EMAIL_NOTIFICATION_NEED;
	public static final String EMAIL_NOTIFICATION_LABEL = ConstantTool.VALUE_EMAIL_NOTIFICATION_LABEL;
}
