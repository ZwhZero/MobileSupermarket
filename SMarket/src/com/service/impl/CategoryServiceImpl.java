package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapper.CategoryMapper;
import com.model.Category;
import com.service.CategoryService;

@Service("CategoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	public CategoryMapper categoryMapper;
	
	//查询商品类别list
	public List<Category> selectAllCategory(){
		return categoryMapper.selectAllCategory();
	}
	//更新指定类别（id）
	public int updateCategory(Category category) {
		return categoryMapper.updateCategory(category);
	}
	//删除指定类别（id）
	public int deleteCategory(int cId) {
		return categoryMapper.deleteCategory(cId);
	}
	//按父类别id(pid)查询
	public List<Category> selectCategoryByPid(int pid) {
		return categoryMapper.selectCategoryByPid(pid);
	}
	//添加类别
	public int addCategory(Category category) {
		return categoryMapper.addCategory(category);
	}
	
}
