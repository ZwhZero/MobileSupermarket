package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
/*import java.util.List;*/
import java.util.List;
//import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
/*import org.springframework.ui.Model;*/
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.Admin;
import com.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/login")
	public String login() {
		//System.out.println("111111");
		return "admin";
	}
	
	@RequestMapping("/adminlogin")/*管理员登录|管理员List*/
	public String adminlogin(Admin admin,HttpSession session,HttpServletResponse response,
			HttpServletRequest request) {
		admin = adminService.selectByNameAndPwd(admin);
		//HttpSession asession = request.getSession();
		Admin sadmin = (Admin)session.getAttribute("a");
		if (sadmin == null) {
			if (admin != null && admin.getaId() != 0) {// 账号或密码正确
				session.setAttribute("a", admin);
				List<Admin> adminlist = adminService.selectAll();
				request.setAttribute("adminlist", adminlist);
				return "page";
			} else {// 账号或密码错误
				response.setContentType("text/html; charset=utf-8");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = null;
				try {
					out = response.getWriter();
					out.print("<script>alert('账号密码错误');window.history.back(-1);</script>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					out.flush();
					out.close();
				}
				return "admin";
			}
		} else {//session不为空
			List<Admin> adminlist = adminService.selectAll();
			request.setAttribute("adminlist", adminlist);
			return "page";
		}
	}
	
	@RequestMapping("/page")/*登录拦截(伪)*/
	public String logout(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws IOException {
		//System.out.println("111111");
		Admin ad = (Admin)session.getAttribute("a");
		if(ad==null){
			response.setContentType("text/html; charset=utf-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.print("<script>alert('请先登陆!');window.location.href='http://localhost:8080/SMarket/admin/login';</script>");
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				out.flush();
				out.close();
			}
			return "admin";
		}else {
			return "page";
		}
	}
	
	@RequestMapping("/exit")/*登录退出*/
	public String exit(HttpSession session,HttpServletResponse response) throws IOException {
		//System.out.println("111111");
		session.removeAttribute("a"); 
//		session.invalidate(); 
		//中文处理
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("<script>alert('用户已退出登录，确定后退出该页面。');window.location.href='http://localhost:8080/SMarket/admin/login';</script>"); 
		}catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			out.flush();
			out.close();
		}
		return "admin";
	}
	
	@RequestMapping("/toUpdateAdmin")/*转入Admin信息修改页*/
	public String toUpdateAdmin(int aId,HttpServletRequest request) {
		Admin admin = adminService.selectById(aId);
		request.setAttribute("getAdmin",admin);
		return "adminUpdate";
		
	}
	@RequestMapping("/updateAdmin")/*更新指定管理员信息*/
	public String updateAdmin(Admin admin,HttpServletRequest request,HttpServletResponse response,Model model) {
		if(adminService.updateAdmin(admin)!=0) {
			admin = adminService.selectById(admin.getaId());
			request.setAttribute("admin", admin);
			return "redirect:/admin/adminlogin";
		}else {
			response.setContentType("text/html; charset=utf-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out=null;
			try {
				out = response.getWriter();
				out.print("<script>alert('获取修改信息错误！');window.history.back(-1);</script>");
			}catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				out.flush();
				out.close();
			}
			return "toUpdateAdmin";
		}
	}
	
	@RequestMapping("/deleteAdmin")/*删除操作*/
	public void deleteAdmin(int aId,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		if(adminService.deleteAdmin(aId) != 0) {
			PrintWriter out=null;
			try {
				out = response.getWriter();
				out.print("<script>alert('删除成功！');window.location.href='http://localhost:8080/SMarket/admin/adminlogin';</script>");
			}catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				out.flush();
				out.close();
			}
		}else {
			PrintWriter out=null;
			try {
				out = response.getWriter();
				out.print("<script>alert('删除失败！');window.history.back(-1);</script>");
			}catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				out.flush();
				out.close();
			}
		}
	}
	
	@RequestMapping("/toAddAdmin")/*转入增加管理员页面*/
	public String toAddAdmin(){
		return "adminAdd";
	}
	@RequestMapping("/addAdmin")/*增添管理员*/
	public void addAdmin(Admin admin,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		if(adminService.addAdmin(admin)!=0){
			PrintWriter out=null;
			try {
				out = response.getWriter();
				out.print("<script>alert('添加成功！');window.location.href='http://localhost:8080/SMarket/admin/adminlogin';</script>");
			}catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				out.flush();
				out.close();
			}
		}else {
			PrintWriter out=null;
			try {
				out = response.getWriter();
				out.print("<script>alert('添加失败！');window.history.back(-1);</script>");
			}catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				out.flush();
				out.close();
			}
		}
	}
	
	//@RequestMapping("/updateAdmin")/**/
	
}
