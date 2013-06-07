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
import cn.go.model.Link;
import cn.go.service.CategoryService;
import cn.go.service.LinkService;
import cn.go.util.DateTool;
import cn.go.util.Pager;

@Controller
@RequestMapping("/manager/link")
public class LinkController extends BaseController{

	@Autowired
	private LinkService linkService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/viewAdd", method = RequestMethod.GET)
	public ModelAndView viewAddLink()
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/link/viewAdd");
		List data = categoryService.getAllCategory();
		mav.addObject(DATALIST, data);
		return mav;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addLink(
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "content", required = false, defaultValue = "") String content,
			@RequestParam(value = "link", required = false, defaultValue = "") String link) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/link/viewAdd");
		mav.addObject("title",title);
		mav.addObject("content",content);
		mav.addObject("link",link);
		if (StringUtils.isBlank(title)) {
			mav.addObject(TIP, "请输入链接名称");
			return mav;
		}
		if (StringUtils.isBlank(content)) {
			mav.addObject(TIP, "请输入链接内容");
			return mav;
		}
		if (StringUtils.isBlank(link)) {
			mav.addObject(TIP, "请输入链接地址");
			return mav;
		}
		
		if (linkService.get("title", title, null) != null) {
			mav.addObject(TIP, "已存在的链接名称");
			return mav;
		}
//		if (linkService.get("content", title, null) != null) {
//			mav.addObject(TIP, "已存在的链接名称");
//			return mav;
//		}
		if (linkService.get("link", title, null) != null) {
			mav.addObject(TIP, "已存在的链接名称");
			return mav;
		}
		Link model = new Link();
		model.setLink(link);
		model.setTitle(title);
		model.setContent(content);
		model.setColor("#000000");
		model.setClick(0);
		model.setTimetag(DateTool.now());
		if (linkService.add(model) > 0) {
			mav.addObject(TIP, "ok创建成功");
			mav.addObject("title", "");
			mav.addObject("content", "");
			mav.addObject("link", "");
			return mav;
		} else {
			mav.addObject(TIP, "创建失败");
			return mav;
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listLink(
			HttpServletRequest req,
			Model pageModel,
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "content", required = false, defaultValue = "") String content,
			@RequestParam(value = "link", required = false, defaultValue = "") String link
			) {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/link/list");

		mav.addObject(title);
		mav.addObject(content);
		mav.addObject(link);
		
		List<String> args = new ArrayList<String>();
		
		StringBuilder sql = new StringBuilder(" from Link ");

		Pager pager = parsePager(req, pageModel, linkService,
				"select count(*) " + sql, args.toArray());
		sql.append(" limit ?,? ");

		List<Link> data = linkService.list("select * " + sql, pager,
				args.toArray());

		mav.addAllObjects(pageModel.asMap());
		mav.addObject(DATALIST, data);
		
		return mav;
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public ModelAndView modifyLink() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/link/viewModify");
		return mav;
	}
}
