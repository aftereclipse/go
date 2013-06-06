package cn.go.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.go.Constant;

public class LoginFilter implements Filter {

	private static final Log log = LogFactory.getLog(LoginFilter.class);

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) req;
		HttpServletResponse httpResp = (HttpServletResponse) resp;
		String uri = httpReq.getRequestURI();
		if (Constant.WEBSITE_NEEDPREFIX) {
			int index = uri.indexOf(Constant.WEBSITE_PREFIX);
			if (index != -1)
				uri = uri.substring(index + Constant.WEBSITE_PREFIX.length());
		}
		HttpSession session = httpReq.getSession();
		String loginUser = (String) session.getAttribute(Constant.LOGIN_USER);
		String path = httpReq.getContextPath();

		// 静态资源直接放行
		if (isStaticResource(uri)) {
			log.info("static resource go：" + uri);
			chain.doFilter(req, resp);
			return;
		}
		// 已登录
		if (StringUtils.isNotBlank(loginUser)) {
			log.info("go：" + uri);
			chain.doFilter(req, resp);
		} else {
			log.info("not logined：" + uri);
			httpResp.sendRedirect(httpReq.getScheme() + "://"
					+ httpReq.getServerName() + ":" + httpReq.getServerPort()
					+ path + "/global/viewLogin");
			return;
		}
	}

	private boolean isStaticResource(String uri) {
		String[] anonymousURI = { "common/" };
		for (String tmp : anonymousURI) {
			if (StringUtils.startsWith(uri, tmp)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void destroy() {

	}
}
