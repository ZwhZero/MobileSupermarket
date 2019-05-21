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
	<title>添加管理员-后台管理</title>
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<jsp:include page="head.jsp"></jsp:include>
		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div class="add-body">
			<h1>◆管理员管理</h1><br/>
			<h2>&nbsp;&nbsp;●添加管理员信息</h2><br/>
				<form class="layui-form" action="" name="addForm">
					<table class="layui-table">
						<tr>
							<td>用 户 名：</td>
							<td><input type="text" name="aName" placeholder="请输入用户名"
								class="layui-input" /></td>
						</tr>
						<tr>
							<td>密 码：</td>
							<td><input type="text" name="aPassword" placeholder="请输入密码"
								class="layui-input" /></td>
						</tr>
						<tr>
							<td>操 作:</td>
							<td><input class="layui-btn" type="button" value="确认"
								onclick="addAdmin()" />&nbsp;&nbsp;&nbsp;<input
								class="layui-btn" type="reset" value="重置" /></td>
						</tr>
					</table>
				</form>
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
	function addAdmin() {
		var addForm = document.forms['addForm'];
		addForm.action = "<%=basePath%>admin/addAdmin";
		addForm.method = "post";
		addForm.submit();
	}
</script>

</html>