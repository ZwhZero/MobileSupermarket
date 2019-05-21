package com.service.impl;

import java.util.List;

/*import javax.annotation.Resource;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapper.AdminMapper;
import com.model.Admin;
import com.service.AdminService;

@Service("AdminService")
@Transactional
public class AdminServiceImpl implements AdminService{
	@Autowired
	public AdminMapper adminMapper;
		//登录用(用户名密码查询)
	    public Admin selectByNameAndPwd(Admin admin) {
	    	Admin ad = adminMapper.selectByNameAndPwd(admin);//返回数据库查找到的Admin
	    	return ad;
	    }
	    // 查询全部
	    public List<Admin> selectAll(){
	    	List<Admin> selectAll = adminMapper.selectAll();
	    	return selectAll;
	    }
		// 查询单条（id）
		public Admin selectById(int aId) {
			Admin selectById = adminMapper.selectById(aId);
			return selectById;
		}
		// 更新（按id）
		public int updateAdmin(Admin admin) {
			return adminMapper.updateAdmin(admin);
		}
		// 删除（按id）
		public int deleteAdmin(int aId) {
			return adminMapper.deleteAdmin(aId);
		}
		// 添加
		public int addAdmin(Admin admin) {
			return adminMapper.addAdmin(admin);
		}
}
