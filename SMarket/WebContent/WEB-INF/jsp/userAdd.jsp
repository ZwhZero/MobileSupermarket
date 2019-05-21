<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<!-- base标记效果：你网页上的所有相对路径在链接时都将在前面加上基链接指向的地址。 -->
	<base href="<%=basePath%>">
	<link rel="stylesheet" href="css/layui.css">
	<title>添加客户信息-后台管理</title>
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<jsp:include page="head.jsp"></jsp:include>
		<div class="layui-body">
			<h1>◆客户管理</h1><br/>
			<h2>&nbsp;&nbsp;●添加客户信息</h2><br/>
			<!-- 内容主体区域 -->
			<div class="user-add">
			<form class="layui-form" action="" name="addUserForm">
					<table class="layui-table">
						<tr>
							<td>用 户 名：</td>
							<td><input type="text" name="uName" placeholder="请输入用户名"
								class="layui-input" /></td>
						</tr>
						<tr>
							<td>密 码：</td>
							<td><input type="text" name="uPassword" placeholder="请输入密码"
								class="layui-input" /></td>
						</tr>
						<tr>
							<td>年 龄：</td>
							<td><input type="text" name="age" placeholder="请输入您的年龄" class="layui-input" /></td>
						</tr>
						<tr>
							<td>性 别：</td>
							<td>
								<!-- <input type="text" name="sex" placeholder="请输入您的性别" class="layui-input" /> --> 
								<!-- <div class="layui-input-block"> -->
									<input type="radio" name="sex" value="男" lay-ignore checked > 男 &nbsp;&nbsp;&nbsp;
									<input type="radio" name="sex" value="女" lay-ignore > 女
								<!-- </div> -->
							</td>
						</tr>
						<tr>
							<td>联系方式：</td>
							<td><input type="text" name="phone" placeholder="请输入您的联系方式" class="layui-input" /></td>
						</tr>
						<tr>
							<td>地 址：</td>
							<td><input type="text" name="address" placeholder="请输入您的住址" class="layui-input" /> </td>
						</tr>
						<tr>
							<td>邮 箱：</td>
							<td><input type="text" name="email" placeholder="请输入您的邮箱" class="layui-input" /> </td>
						</tr>
						<tr>
							<td>操 作:</td>
							<td><input class="layui-btn" type="button" value="确认"
								onclick="addUser()" />&nbsp;&nbsp;&nbsp;<input
								class="layui-btn" type="reset" value="重置" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>	
</body>
<script src="js/layui.all.js"></script>
<script type="text/javascript">
	//JavaScript代码区域
	layui.use('element', function() {
		var element = layui.element;

	});
	
	/* form.render('radio','test1'); */
	
	function addUser() {
		var auForm = document.forms['addUserForm'];
		auForm.action = "<%=basePath%>user/addUser";
		auForm.method = "post";
		auForm.submit();
	}
</script>

</html>