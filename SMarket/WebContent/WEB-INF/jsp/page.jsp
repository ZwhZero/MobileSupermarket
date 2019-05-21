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
	<!-- base标记是一个基链接标记，是一个单标记。用以改变文件中所有连结标记的参数内定值。它只能应用于标记<head>与</head>之间。
		效果：你网页上的所有相对路径在链接时都将在前面加上基链接指向的地址。
		重要属性：href—设定前缀的链接地址；target—设定文件显示的窗口,同a标记中的target
	 -->
	<base href="<%=basePath%>">
	<link rel="stylesheet" href="css/layui.css">
	<title>后台管理</title>
</head>

<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<jsp:include page="head.jsp"></jsp:include>
		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div class="">
				<h1>◆管理员管理</h1><br/>
				<h2>&nbsp;&nbsp;●管理员信息List</h2><br/>
				<a href="<%=basePath%>admin/toAddAdmin"><button class="layui-btn layui-btn-primary" name="addAdmin" >添加管理员</button></a>
				
				<table class="layui-table" lay-even>
					<tr>
						<th>Id</th>
						<th>用户名</th>
						<th>密码</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty adminlist }">
						<c:forEach items="${adminlist}" var="admin">
							<tr>
								<td>${admin.aId}</td>
								<td>${admin.aName}</td>
								<td>${admin.aPassword}</td>
								<td>
									<a href="<%=basePath%>admin/toUpdateAdmin?aId=${admin.aId}">编辑</a>&nbsp;&nbsp;&nbsp;
									<a href="<%=basePath%>admin/deleteAdmin?aId=${admin.aId}">删除</a>
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
	<script>
		//JavaScript代码区域
		layui.use('element', function() {
			var element = layui.element;

		});
</script>

</html>
