package com.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mapper.UserMapper;
import com.model.User;
import com.service.UserService;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService{   
	@Autowired
    public UserMapper userMapper;
	
	//登录用(用户名密码查询)
    public User loginUser(User user) {
    	User us = userMapper.loginUser(user);//返回数据库查找到的User
    	return us;
    }
    
    //查询全部用户
    public List<User> findAllUser() {
        // TODO Auto-generated method stub
        List<User> findAllUser = userMapper.findAllUser();
        return findAllUser;
    }
    
    // 查询单条（id）
	public User findUser(int uId) {
		User findUser = userMapper.findUser(uId);
		return findUser;
	}
	
	// 查询单条（name）
	public User findUserByuName(String uName) {
		return userMapper.findUserByuName(uName);
	}
	
	// 更新（按id）
	public int updateUser(User user) {
		return userMapper.updateUser(user);
	}

	// 删除（按id）
	public int deleteUser(int uId) {
		return userMapper.deleteUser(uId);
	}

	// 添加
	public int addUser(User user) {
		return userMapper.addUser(user);
	}
}
