package cn.go.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.go.model.Category;
import cn.go.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements
		CategoryService {

	@Override
	public List getAllCategory() {
		String sql = "select * from category limit 0,100";
		
		
		return jdbcTemplate.queryForList(sql);
	}
	
}
