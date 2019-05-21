package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.User;
import com.service.UserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    
    //前台相关-登录
    @RequestMapping("/userLogin")
    @ResponseBody
    public void userLogin(User user,HttpServletResponse response,HttpServletRequest request) throws IOException {
//    	String uName = request.getParameter("uName");
//    	String uPassword = request.getParameter("uPassword");
    	User dbuser = userService.loginUser(user);
    	HttpSession session = request.getSession();
    	User suser = (User)session.getAttribute("u");
//    	System.out.println(user.getuName());
    	String logResult = "";
    	if(suser==null) {
    		if(dbuser!=null && dbuser.getuId()!=0) {
    			session.setAttribute("u", dbuser);
    			logResult = "true";
    		} else {
    			logResult = "false";
    		}
    	} else {
    		logResult = "true";
    	}
    	Map<String, String> map=new HashMap<>();
    	map.put("result",logResult );
//    	System.out.println(map);
    	JSONArray v = JSONArray.fromObject(map);
//    	System.out.println(v);
    	//jsonCallBack 定义的名，或者callback=? 时服务器随机生成的函数名
    	String cbstr=request.getParameter("callback");
    	response.getWriter().print(cbstr + "(" + v + ")");
//    	return map;
//    	PrintWriter out = null;
    }
    
    //前台退出登录操作
    @RequestMapping("/floginOut")
    @ResponseBody
    public void floginOut(HttpServletResponse response,HttpServletRequest request) throws IOException {
    	response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User Suser =  (User)session.getAttribute("u");
		Map<String, String> map=new HashMap<>();
		if(Suser!=null) {
			session.setAttribute("u", null);
			map.put("result", "true");
			JSONObject v = JSONObject.fromObject(map);
			response.getWriter().print(v);
		} else {
			map.put("err_code", "700");
			JSONObject v = JSONObject.fromObject(map);
			response.getWriter().print(v);
		}
    }
    
    @RequestMapping("/getSuser")
    @ResponseBody
    public void getSuser(HttpServletResponse response,HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
    	response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User Suser =  (User)session.getAttribute("u");
//		System.out.println(Suser);
		//session不为空(Suser!=null)执行
		if(Suser!=null) { /*已登录，返回用户信息*/
			ObjectMapper mapper = new ObjectMapper();
			//java对象转json对象
			String jsuser = mapper.writeValueAsString(Suser);
//			System.out.println(jsuser);
			response.getWriter().print(jsuser);
		} else { /*未登录状态，返回带有error_code字段(700)的错误json对象 */
			Map<String,String> map = new HashMap<>();
			map.put("error_code","700");
			JSONObject v = JSONObject.fromObject(map);
			response.getWriter().print(v);
		}
    }
    
    //前台-注册功能
    @RequestMapping("/faddUser")
    @ResponseBody
    public void faddUser(User user,HttpServletResponse response,HttpServletRequest request) throws IOException {
    	response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		User dbUser = userService.findUserByuName(user.getuName());
		String addResult = "";
		if(dbUser == null) { /*填写的用户名未被使用*/
			if(userService.addUser(user)!=0) { /*添加成功*/
				System.out.println(user); /*查看获取值，调试用*/
				addResult = "true";
			} 
		}else {
			addResult = "false";
		}
		Map<String, String> map=new HashMap<>();
    	map.put("result",addResult );
    	JSONObject v = JSONObject.fromObject(map);
    	System.out.println(v);/*查看输出json数据，调试*/
    	response.getWriter().print(v);
    }
    
    //前台-Ajax检测注册用户名是否可用
    @RequestMapping("/fcheckName")
    @ResponseBody
    public void fcheckName(String inputName,HttpServletResponse response) throws IOException {
    	response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		Map<String,String> map = new HashMap<>();
		User dbUser = userService.findUserByuName(inputName);
		if(dbUser != null) { //该用户名已被使用,返回提示信息
			map.put("result", "该用户名已存在，请更换！");
		} else { //该用户名可以使用,返回空信息
			map.put("result", "");
		}
		JSONObject v = JSONObject.fromObject(map);
		response.getWriter().println(v);
    }
    
    //前台-找回密码页-Ajax实现找回密码：fforgetPwd
	@RequestMapping("/fforgetPwd")
	@ResponseBody
    public void fforgetPwd(String uName,String phone,String newPassword,HttpServletResponse response) throws IOException {
    	response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		Map<String,String> map = new HashMap<>();
		String result = "";
		User user = userService.findUserByuName(uName);
		if(user!=null) { /*用户存在，核对手机号*/
			if(phone.equals(user.getPhone())) { /*手机号正确，允许修改密码*/
				user.setuPassword(newPassword);
				if(userService.updateUser(user)!=0) {/*修改成功*/
					result = "true";
					map.put("newPassword", newPassword);
				} else { /*数据库修改失败*/
					result = "editError";
				}
			} else { /*手机号错误，返回错误信息*/
				result = "phoneError";
			}
		} else { /*用户不存在，返回错误信息*/
			result = "nameError";
		}
		map.put("result", result);
		JSONObject v = JSONObject.fromObject(map);
		response.getWriter().println(v);
    }
    //前台-找回密码页-Ajax检测用户是否存在
    @RequestMapping("/fpwdCheckName")
    @ResponseBody
    public void fpwdCheckName(String inputName,HttpServletResponse response) throws IOException {
    	response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		Map<String,String> map = new HashMap<>();
		User dbUser = userService.findUserByuName(inputName);
		if(dbUser != null) { //该用户存在,返回正确提示信息
			map.put("result", "√ 该用户存在！");
		} else { //该用户不存在,返回错误提示信息
			map.put("result", "× 该用户不存在，请核对！");
		}
		JSONObject v = JSONObject.fromObject(map);
		response.getWriter().println(v);
    }
    
    //前台-密码修改页-修改密码：fchangePwd
    @RequestMapping("/fchangePwd")
	@ResponseBody
    public void fchangePwd(String oldPwd,String newPassword,HttpServletResponse response,HttpServletRequest request) throws IOException {
    	response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User currentUser =  (User)session.getAttribute("u");
		Map<String,String> map = new HashMap<>();
		String result = "";
		if(currentUser!=null) { /*用户已登录，核对原密码*/
			String myPassword = currentUser.getuPassword(); /*获取登录密码*/
			//System.out.println(oldPwd + "," + myPassword);/*测试用*/
			if(oldPwd.equals(myPassword)) { /*原密码正确，允许修改密码*/
				currentUser.setuPassword(newPassword);
				if(userService.updateUser(currentUser)!=0) {/*修改成功*/
					session.setAttribute("u", null);
					result = "true";
					map.put("newPassword", newPassword);
				} else { /*数据库修改失败*/
					result = "editError";
				}
			} else { /*原密码错误，返回错误信息*/
				result = "oldPwdError";
			}
		} else { /*用户未登录，返回错误信息*/
			result = "loginError";
		}
		map.put("result", result);
		JSONObject v = JSONObject.fromObject(map);
		response.getWriter().println(v);
    }
    
    
//	后台相关-------------------------------------------------
    @RequestMapping("/findAllUser")/*展示用户信息*/
    public String findAllUser(HttpServletRequest request){
        List<User> listUser =  userService.findAllUser();
        request.setAttribute("listUser", listUser);
        return "userList";
    }
    
    @RequestMapping("/toUpdateUser")/*转入User信息修改页*/
	public String toUpdateUser(int uId,HttpServletRequest request) {
		User user= userService.findUser(uId);
		request.setAttribute("getUser",user);
		return "userUpdate";
	}
    @RequestMapping("/updateUser")/*更新指定客户信息*/
	public String updateUser(User user,HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {
		if(userService.updateUser(user)!=0) {
			user= userService.findUser(user.getuId());
			request.setAttribute("user", user);
			return "redirect:/user/findAllUser";
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
			return "toUpdateUser";
		}
	}
    
    @RequestMapping("/deleteUser")/*删除客户操作*/
	public void deleteUser(int uId,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		if(userService.deleteUser(uId) != 0) {
			PrintWriter out=null;
			try {
				out = response.getWriter();
				out.print("<script>alert('删除成功！');window.location.href='http://localhost:8080/SMarket/user/findAllUser';</script>");
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
    
    @RequestMapping("/toAddUser")/*转入增加客户页面*/
	public String toAddUser(){
		return "userAdd";
	}
    @RequestMapping("/addUser")/*增添客户信息*/
	public void addUser(User user,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		if(userService.addUser(user)!=0){
			PrintWriter out=null;
			try {
				out = response.getWriter();
				out.print("<script>alert('添加成功！');window.location.href='http://localhost:8080/SMarket/user/findAllUser';</script>");
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
}