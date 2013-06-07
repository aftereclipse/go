package cn.go.service;

import java.util.List;

import cn.go.model.Category;

public interface CategoryService extends BaseService<Category>{
	public List<Category> getAllCategory();
}
