package cn.go.service.impl;

import org.springframework.stereotype.Service;

import cn.go.model.Category;
import cn.go.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements
		CategoryService {

}
