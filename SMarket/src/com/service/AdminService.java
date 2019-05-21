package com.service;

import java.util.List;

/*import java.util.List;*/
import com.model.Admin;

public interface AdminService {
	//查询（登录用）
	Admin selectByNameAndPwd(Admin admin);

	// 查询全部
	List<Admin> selectAll();

	// 查询单条（id）
	Admin selectById(int aId);

	// 更新（按id）
	int updateAdmin(Admin admin);

	// 删除（按id）
	int deleteAdmin(int aId);

	// 添加
	int addAdmin(Admin admin);
}
