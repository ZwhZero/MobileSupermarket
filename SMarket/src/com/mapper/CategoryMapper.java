package com.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.model.Category;

@Repository
public interface CategoryMapper {
	//查询商品类别list
	List<Category> selectAllCategory();
	//按父类别id：pid查询
	List<Category> selectCategoryByPid(int pid);
	//更新指定类别（id）
	int updateCategory(Category category);
	//删除指定类别（id）
	int deleteCategory(int cId);
	//添加类别
	int addCategory(Category category);
	
}
