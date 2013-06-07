package cn.go.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 后台控制器
 * 
 * @author qingtian
 * 
 *         2013-6-6 下午08:47:29
 */
@Controller
@RequestMapping("/manager")
public class ManagerController extends BaseController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/index");
		return mav;
	}

}
