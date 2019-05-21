package com.service;

import java.util.List;

import com.model.Category;

public interface CategoryService {
	//查询商品类别list
	List<Category> selectAllCategory();
	//更新指定类别（id）
	int updateCategory(Category category);
	//删除指定类别（id）
	int deleteCategory(int cId);
	//按父类别id(pid)查询
	List<Category> selectCategoryByPid(int pid);
	//添加类别
	int addCategory(Category category);
	
}
