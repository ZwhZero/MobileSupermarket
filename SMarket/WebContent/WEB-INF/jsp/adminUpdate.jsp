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
	<title>修改管理员信息-后台管理</title>
	<script type="text/javascript">
		function updateAdmin(){
			var aForm = document.forms['adminForm'];
			aForm.action = "<%=basePath%>admin/updateAdmin";
			aForm.method = "Post";
			aForm.submit();
		}
	</script>
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<jsp:include page="head.jsp"></jsp:include>
		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div class="admin-edit">
				<h1>◆管理员管理</h1><br/>
				<h2>&nbsp;&nbsp;●管理员信息修改</h2><br/>
				<form class="layui-form" action="" name="adminForm">
				<input type="hidden" name="aId" value="${getAdmin.aId}" />
					<table class="layui-table">
						<tr>
							<td style="width:20%">ID：</td>
							<td>${getAdmin.aId}</td>
						</tr>
						<tr>
							<td>用 户 名：</td>
							<td><input type="text" name="aName" value="${getAdmin.aName}" class="layui-input" /></td>
						</tr>
						<tr>
							<td>密 码：</td>
							<td><input type="text" name="aPassword" value="${getAdmin.aPassword}" class="layui-input" /> </td>
						</tr>
						<tr>
							<td>操 作:</td>
							<td><input class="layui-btn" type="button" value="确认修改" onclick="updateAdmin()" />&nbsp;&nbsp;&nbsp;<input class="layui-btn" type="reset" value="重置" /></td>
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
</script>

</html>