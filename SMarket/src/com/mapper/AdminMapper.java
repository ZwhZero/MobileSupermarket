package com.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.model.Admin;

@Repository
public interface AdminMapper {
	//以接口形式定义数据库操作方法，
	 Admin selectByNameAndPwd(Admin admin);
	
	//查询全部
	List<Admin> selectAll();
	
	//查询单条（id）
	Admin selectById(int aId);
	
	//更新（按id）
	int updateAdmin(Admin admin);
	//Integer 
	//删除（按id）
	int deleteAdmin(int aId);
	
	//添加
	int addAdmin(Admin admin);
}
