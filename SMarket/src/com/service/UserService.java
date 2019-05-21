package com.service;

import java.util.List;
import com.model.User;

public interface UserService {
	// 用户登录
	User loginUser(User user);
	
	// 查询全部
	List<User> findAllUser();
	
	// 查询单条（id）
	User findUser(int uId);
	
	// 查询单条（name）
	User findUserByuName(String uName);

	// 更新（按id）
	int updateUser(User user);

	// 删除（按id）
	int deleteUser(int uId);

	// 添加
	int addUser(User user);
}
