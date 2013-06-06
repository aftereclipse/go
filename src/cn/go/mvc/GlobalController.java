package cn.go.mvc;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.go.model.Admin;
import cn.go.model.Category;
import cn.go.service.AdminService;
import cn.go.service.CategoryService;

/**
 * 所有用户直接交互的界面控制器
 * 
 * @author qingtian
 * 
 *         2013-6-6 下午08:46:58
 */
@Controller
@RequestMapping("/global")
public class GlobalController extends BaseController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("front/index");

		// 所有标签
		List<Category> categoryList = categoryService.list(
				"select * from category ", null);
		mav.addObject("categoryList", categoryList);

		// 所有链接

		return mav;
	}

	@RequestMapping(value = "/viewLogin", method = RequestMethod.GET)
	public ModelAndView viewLogin() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("viewLogin");

		return mav;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(
			HttpSession session,
			@RequestParam(value = "uname", defaultValue = "", required = false) String uname,
			@RequestParam(value = "passwd", defaultValue = "", required = false) String passwd) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("viewLogin");

		mav.addObject("uname", uname);

		if (StringUtils.isBlank(uname)) {
			mav.addObject(TIP, "请输入用户名");
			return mav;
		}
		if (StringUtils.isBlank(passwd)) {
			mav.addObject(TIP, "请输入密码");
			return mav;
		}
		Admin admin = adminService.login(uname, passwd);
		if (admin == null) {
			mav.addObject(TIP, "用户名或密码错误");
			return mav;
		}
		// 保存会话
		session.setAttribute(LOGIN_USER, uname);
		session.setAttribute("id", String.valueOf(admin.getId()));

		mav.clear();
		mav.setViewName("redirect:/manager/index");

		return mav;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("viewLogin");

		mav.addObject(TIP, "您已成功退出");

		session.removeAttribute(LOGIN_USER);
		session.removeAttribute("id");

		session.invalidate();

		return mav;
	}
}
