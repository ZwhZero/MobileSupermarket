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
	<link rel="stylesheet" href="css/admin.css">
	
	<title>管理员登录页面</title>
</head>
<body>
		<!--页面头部-->
		<div class="header" id="header">
			<div class="title">
				<p>Zero超市-后台管理系统</p>
			</div>
			<div class="welcome">
				<p>Welcome</p>
			</div>
		</div>
		
		<!--主登录部分-->
		<div class="log-main" id="log-main">
			<div class="log-box">
			<form action="<%=basePath%>admin/adminlogin" method="post">
				<!-- 头部信息 -->
				<div class="log-top">
					<p class="loghead">登录</p>
				</div>
				<!-- 输入框input -->
				<div class="log-in">
					<img src="img/logName.png" width="20px" height="20px" alt="" />
					<input type="text" name="aName" placeholder="输入管理员用户名" />
				</div>
				<div class="log-in">
					<img src="img/logPwd.png" width="20px" height="20px" alt="" />
					<input type="password" name="aPassword" placeholder="输入您的密码" />
				</div>
				<div class="log-sub">
					<input type="submit" name="submit" value="登 录" />
					<input type="reset" name="reset" value="重 置" />
				</div>
			</form>
			</div>
		</div>
		
		<!--页面底部-->
		<div class="footer" id="footer">
			<div class="content">
				<p>仅学习用途  版权所有   @William </p>
				<div class="ex">
					<span>联系邮箱：1132586581@qq.com</span>
				</div>
				<div class="ex">
					<span>联系地址：广州航海学院</span>
				</div>
				<div class="ex">
					<span>联系QQ：1132586581</span>
				</div>
			</div>
		</div>
	</body>
</html>