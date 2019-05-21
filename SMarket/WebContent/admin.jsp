<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String path = request.getContextPath();//返回可返回站点的根路径/Market(即/WebContent目录)
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
/* 
	request.getScheme()：返回协议:默认为http; 
	request.getServerName():浏览器显示的主机名：localhost（？）
	request.getServerPort()：服务器端口号：8080
	basePath = http://localhost:8080/Market/
*/
%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<!-- base标记是一个基链接标记，是一个单标记。用以改变文件中所有连结标记的参数内定值。它只能应用于标记<head>与</head>之间。
		效果：你网页上的所有相对路径在链接时都将在前面加上基链接指向的地址。
		重要属性：href—设定前缀的链接地址；target—设定文件显示的窗口,同a标记中的target
	 -->
	<base href="<%=basePath%>">
	<link rel="stylesheet" href="#">
	
	<title>管理员登录页面</title>
</head>
<body>
	<div class="header">
		欢迎来到Zero超市后台管理-登录页面
	</div>
	
	<div class="login-box">
		<form class="table" method="post" action="<%=basePath%>admin/adminlogin">
			<table>
				<tr>
					<td><label for="account">用户名:</label></td>
					<td><input type="text" name="aName" id="name"></td>
				</tr>
				<tr>
					<td><label for="password">密码:</label></td>
					<td><input type="password" name="aPassword" id="password"></td>
				</tr>
				<tr>
					<td><input type="submit" name="admin" value="登录"></td>
					<td><input type="reset" name="reset" value="重置"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>