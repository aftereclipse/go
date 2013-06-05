package cn.go.mvc;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;

import cn.go.Constant;
import cn.go.service.BaseService;
import cn.go.util.Pager;


/**
 * 提供通用方法的基础控制器，能力如：
 * <ul>
 * <li>将GET请求值编码为UTF8</li>
 * <li>解析生成Pager对象</li>
 * <li>重定向页面</li>
 * </ul>
 * 
 * @author qingtian
 * 
 *         2013-2-27 上午10:27:16
 */
public class BaseController implements Constant {
	private static final Log log = LogFactory.getLog(BaseController.class);

	public String toUTF8(String source) {
		if (source == null)
			return "";
		String target = null;
		if (Constant.ENCODING_NEED) {
			try {
				target = new String(source.getBytes(Constant.ENCODING_SOURCE),
						Constant.ENCODING_TARGET);
			} catch (UnsupportedEncodingException e) {
				log.error(e, e.getCause());
				e.printStackTrace();
				target = source;
			}
		} else {
			target = source;
		}
		return target;
	}

	public Pager parsePager(HttpServletRequest req, Model pageModel,
			BaseService<? extends Object> service, String sql, Object... args) {
		// 前台分页
		int p = PAGER_DEFAULT_CURRENT_PAGE;
		int countPerPage = PAGER_COUNT_PER_PAGE;
		try {
			p = param(req, "page", PAGER_DEFAULT_CURRENT_PAGE);
			if (p < 1)
				p = PAGER_DEFAULT_CURRENT_PAGE;
		} catch (NumberFormatException e) {
			p = PAGER_DEFAULT_CURRENT_PAGE;
		}
		try {
			countPerPage = param(req, "countPerPage", PAGER_COUNT_PER_PAGE);
		} catch (NumberFormatException e) {
			countPerPage = PAGER_COUNT_PER_PAGE;
		}

		int currentPage = p;
		int totalCount = service.totalCount(sql, args);
		Pager pager = new Pager(currentPage, countPerPage, totalCount);
		// 针对可能的原访问页数大于实际总页数，此处重置下
		if (currentPage > pager.getTotalPage())
			currentPage = p = pager.getTotalPage();

		// 分页相关配置响应（用于页面计算与分页JS初始化）
		pageModel.addAttribute("currentPage", currentPage);
		pageModel.addAttribute("countPerPage", countPerPage);
		pageModel.addAttribute("pager", pager);

		return pager;
	}

	public String urlEncode(String str) throws UnsupportedEncodingException {
		return URLEncoder.encode(URLEncoder.encode(str, "utf-8"), "utf-8");
	}

	public String getReferRelative(HttpServletRequest req) {
		String refer = req.getHeader("Referer");
		int r3 = StringUtils.lastIndexOf(refer, "/");
		String relative = refer.substring(r3 + 1, refer.length());
		// 检测tip，如果有则去掉此参数
		int indexT = relative.indexOf("tip");
		if (indexT > 0) {
			relative = relative.substring(0, indexT - 1);
		}
		return relative;
	}

	public String redirectURL(String relative, String tip) {
		if (relative.indexOf("?") > 0) {
			return relative + "&tip=" + tip;
		} else {
			return relative + "?tip=" + tip;
		}
	}

	private String param(HttpServletRequest req, String name) {
		String v = (String) req.getParameter(name);
		String value = v != null ? v.trim() : "";
		return value;
	}

	private int param(HttpServletRequest req, String name, int defaultValue) {
		String value = param(req, name);
		return StringUtils.isNumeric(value) && value != null
				&& !"".equals(value) ? Integer.parseInt(value) : defaultValue;
	}
}
