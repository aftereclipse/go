package cn.go.mvc;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.go.model.Category;
import cn.go.service.CategoryService;
import cn.go.util.DateTool;
import cn.go.util.Pager;

/**
 * 导航系统管理员操作的后台控制器
 * 
 * @author qingtian
 * 
 *         2013-6-6 下午08:47:29
 */
@Controller
@RequestMapping("/manager")
public class ManagerController extends BaseController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/index");
		return mav;
	}

	@RequestMapping(value = "/category/list", method = RequestMethod.GET)
	public ModelAndView index(
			HttpServletRequest req,
			Model pageModel,
			@RequestParam(value = "name", defaultValue = "", required = false) String name) {
		ModelAndView mav = categoryListDataHelp(req, pageModel, name);
		return mav;
	}

	private ModelAndView categoryListDataHelp(HttpServletRequest req,
			Model pageModel, String name) {
		name = toUTF8(name);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/category/list");

		mav.addObject("name", name);

		StringBuilder sql = new StringBuilder(" from category ");
		List<String> args = new ArrayList<String>();
		boolean first = true;
		if (StringUtils.isNotBlank(name)) {
			if (first) {
				sql.append(" where name like ? ");
				args.add("%" + name + "%");
				first = false;
			} else {
				sql.append(" and name like ? ");
				args.add("%" + name + "%");
			}
		}
		Pager pager = parsePager(req, pageModel, categoryService,
				"select count(*) " + sql.toString(), args.toArray());
		sql.append(" order by timetag desc limit ?, ? ");

		List<Category> data = categoryService.list(
				"select * " + sql.toString(), pager, args.toArray());

		mav.addAllObjects(pageModel.asMap());
		mav.addObject(DATALIST, data);

		return mav;
	}

	@RequestMapping(value = "/category/viewAdd", method = RequestMethod.GET)
	public String categoryViewAdd() {
		return "manager/category/viewAdd";
	}

	@RequestMapping(value = "/category/add", method = RequestMethod.POST)
	public ModelAndView categoryAdd(
			@RequestParam(value = "name", defaultValue = "", required = false) String name) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/category/viewAdd");

		mav.addObject("name", name);

		if (StringUtils.isBlank(name)) {
			mav.addObject(TIP, "请输入标签名称");
			return mav;
		}
		if (categoryService.get("name", name, null) != null) {
			mav.addObject(TIP, "已存在的标签名称");
			return mav;
		}
		Category model = new Category();
		model.setName(name);
		model.setClick(0);
		model.setTimetag(DateTool.now());

		if (categoryService.add(model) > 0) {
			mav.addObject(TIP, "ok创建成功");
			mav.addObject("name", "");
			return mav;
		} else {
			mav.addObject(TIP, "创建失败");
			return mav;
		}
	}

	@RequestMapping(value = "/category/viewModify", method = RequestMethod.GET)
	public ModelAndView categoryViewModify(
			@RequestParam(value = "id", defaultValue = "", required = false) String id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/category/viewModify");

		Category prevent = categoryService.get("id", id, null);
		if (prevent == null) {
			mav.setViewName("error");
			mav.addObject(TIP, "不存在的标签");
			return mav;
		}
		mav.addObject(MODEL, prevent);

		return mav;
	}

	@RequestMapping(value = "/category/modify", method = RequestMethod.POST)
	public ModelAndView categoryModify(
			@RequestParam(value = "id", defaultValue = "", required = false) String id,
			@RequestParam(value = "name", defaultValue = "", required = false) String name) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/category/viewModify");

		mav.addObject("name", name);

		Category prevent = categoryService.get("id", id, null);
		if (prevent == null) {
			mav.setViewName("error");
			mav.addObject(TIP, "不存在的标签");
			return mav;
		}
		mav.addObject(MODEL, prevent);

		if (StringUtils.isBlank(name)) {
			mav.addObject(TIP, "请输入标签名称");
			return mav;
		}
		if (StringUtils.equals(name, prevent.getName())) {
			mav.addObject(TIP, "无任何变更");
			return mav;
		}

		if (categoryService.get("name", name, null) != null) {
			mav.addObject(TIP, "已存在的标签名称");
			return mav;
		}
		prevent.setName(name);

		if (categoryService.update(prevent) > 0) {
			mav.addObject(TIP, "ok编辑成功");
			return mav;
		} else {
			mav.addObject(TIP, "编辑失败");
			return mav;
		}
	}

	@RequestMapping(value = "/category/delete", method = RequestMethod.GET)
	public ModelAndView categoryDelete(
			HttpServletRequest req,
			Model pageModel,
			@RequestParam(value = "name", defaultValue = "", required = false) String name,
			@RequestParam(value = "id", defaultValue = "", required = false) String id) {
		ModelAndView mav = categoryListDataHelp(req, pageModel, name);

		Category prevent = categoryService.get("id", id, null);
		if (prevent == null) {
			mav.setViewName("error");
			mav.addObject(TIP, "不存在的标签");
			return mav;
		}

		if (categoryService.delete(prevent) > 0) {
			mav = categoryListDataHelp(req, pageModel, name);
			mav.addObject(TIP, "ok删除成功");
			return mav;
		} else {
			mav.addObject(TIP, "删除失败");
			return mav;

		}
	}

}
