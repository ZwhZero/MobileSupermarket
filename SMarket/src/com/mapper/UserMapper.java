package com.mapper;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.model.User;

@Repository
public interface UserMapper {
	// 用户登录
	User loginUser(User user);
	
	// 查询全部
	List<User> findAllUser();

	// 查询单条（id）
	User findUser(int uId);
	
	// 查询单条（name）
	User findUserByuName(String uName);

	// 更新（按id）
	int updateUser(User user);/*Integer(整型)类型可return空值*/
	
	// 删除（按id）
	int deleteUser(int uId);

	// 添加
	int addUser(User user);
	
}