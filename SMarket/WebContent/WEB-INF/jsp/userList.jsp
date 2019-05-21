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
	<title>客户信息管理-后台管理</title>
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<jsp:include page="head.jsp"></jsp:include>
		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div class="userList">
				<h1>◆客户管理</h1><br/>
				<h2>&nbsp;&nbsp;●客户信息List</h2><br/>
				<a href="<%=basePath%>user/toAddUser"><button class="layui-btn layui-btn-primary" name="addUser" >新添客户</button></a>
				
				<table class="layui-table" lay-even>
					<tr>
						<th>Id</th>
						<th>用户名</th>
						<th>密码</th>
						<th>年龄</th>
						<th>性别</th>
						<th>联系电话</th>
						<th>地址</th>
						<th>邮箱</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty listUser }">
						<c:forEach items="${listUser}" var="list">
							<tr>
								<td>${list.uId}</td>
								<td>${list.uName}</td>
								<td>${list.uPassword}</td>
								<td>${list.age}</td>
								<td>${list.sex}</td>
								<td>${list.phone}</td>
								<td>${list.address}</td>
								<td>${list.email}</td>
								<td>
									<a href="<%=basePath%>user/toUpdateUser?uId=${list.uId}">编辑</a>&nbsp;&nbsp;&nbsp;
									<a href="<%=basePath%>user/deleteUser?uId=${list.uId}">删除</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
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
</script>
</html>