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
	<title>类别管理-后台管理</title>
	<style>
		* {
			margin:0;
			padding:0
		}
		html, body {
			height: 100%;
			width: 100%;
			font-size:12px
		}
		.white_content {
			display: none;
			position: absolute;
			top: 25%;
			left: 25%;
			width: 50%;
			padding: 6px 16px;
			border: 12px solid #D6E9F1;
			background-color: white;
			z-index:1002;
			overflow: auto;
		}
		.black_overlay {
			display: none;
			position: absolute;
			top: 0%;
			left: 0%;
			width: 100%;
			height: 100%;
			background-color: #f5f5f5;
			z-index: 1001;
			-moz-opacity: 0.8;
			opacity: .80;
			filter: alpha(opacity=80);
		}
		.close {
			float:right;
			clear:both;
			width:100%;
			text-align:right;
			margin:0 0 6px 0
		}
		.close a {
			color:#333;
			text-decoration:none;
			font-size:14px;
			font-weight:700
		}
		.con {
			text-indent:1.5pc;
			line-height:21px
		}
	</style>
	<script>
		function show(tag,cId,cName,level,pid){
			var light=document.getElementById(tag);
			var fade=document.getElementById('fade');
			var oldId = cId;
			var oldName = cName;
			var oldPid = pid;
			document.getElementById('mycId').value = oldId;
			document.getElementById('editId').innerHTML = oldId;
			document.getElementById('editName').value = oldName;
			document.getElementById('editPid').value = oldPid;
			light.style.display='block';
			fade.style.display='block';
		}
		function showAdd(tag){
			var light=document.getElementById(tag);
			var fade=document.getElementById('fade');
			light.style.display='block';
			fade.style.display='block';
		}
		function hide(tag){
			var light=document.getElementById(tag);
			var fade=document.getElementById('fade');
			light.style.display='none';
			fade.style.display='none';
		}
	</script>
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<jsp:include page="head.jsp"></jsp:include>
		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div class="category">
				<h1>◆类别管理</h1><br/>
				<h2>&nbsp;&nbsp;●类别信息List</h2><br/>
				<a href="javascript:void(0)" onclick="showAdd('light2')" class="layui-btn">添加新类别</a>
				<table class="layui-table" lay-even>
					<tr>
						<th>Id</th>
						<th>类别名</th>
						<th>级别</th>
						<th>父节点id</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty categoryList }">
						<c:forEach items="${categoryList}" var="parent" varStatus="vs">
							<c:if test="${parent.level == 1 }">
								<tr>
									<td>${parent.cId}</td>
									<td>${parent.cName}</td>
									<td>${parent.level}</td>
									<td>${parent.pid}</td>
									<td>
										<a href="javascript:void(0)" onclick="show('light','${parent.cId}','${parent.cName}','${parent.level}','${parent.pid}')">编辑</a>&nbsp;&nbsp;&nbsp;
										<a href="<%=basePath%>category/deleteCategory?cId=${parent.cId}">删除</a>
									</td>
								</tr>
    							<c:forEach items="${categoryList}" var="child" varStatus="vs">
    								<c:if test="${child.pid == parent.cId }">
									    <tr>
											<td>${child.cId}</td>
											<td>${child.cName}</td>
											<td>${child.level}</td>
											<td>${child.pid}</td>
											<td>
												<a href="javascript:void(0)" onclick="show('light','${child.cId}','${child.cName}','${child.level}','${child.pid}')">编辑</a>&nbsp;&nbsp;&nbsp;
												<a href="<%=basePath%>category/deleteCategory?cId=${child.cId}">删除</a>
											</td>
										</tr>
    								</c:if>
    							</c:forEach>
   							 </c:if>
						</c:forEach>
					</c:if>
				</table>
			</div>
			
			<!-- 弹出层1内容 -->
			<div id="light" class="white_content">
				<div class="close"><a href="javascript:void(0)" onclick="hide('light')"> 关闭</a></div>
				<div class="con"> 
					<h2>编辑类别</h2>
					<h3>---请认真核对父类别id的值，一级类别填写“0”</h3>
					<form action="<%=basePath%>category/updateCategory" method="post">
					<input type="hidden" name="cId" id="mycId" value="" />
						<table class="layui-table">
							<tr>
								<td>类别id：</td>
								<td><span id="editId">0</span></td>
							</tr>
					 		<tr>
					 			<td>类别名：</td>
					 			<td><input type="text" id="editName" name="cName" value="null"></td>
					 		</tr>
					 		<tr>
					 			<td>类别级别：</td>
					 			<td>
				 					<input type="radio" name="level" value="1" title="一级">一级
				 					<input type="radio" name="level" value="2" title="二级" checked>二级
					 			</td>
					 		</tr>
							<tr>
								<td>父类别id：</td>
								<td><input type="text" id="editPid" name="pid" value="0"></td>
							</tr>
							<tr>
								<td>操作：</td>
								<td>
									<input type="submit" name="submit" value="提交" class="layui-btn">
									<input type="reset" name="reset" value="重置" class="layui-btn">
								</td>
							</tr>
					 	</table>
					 </form>
				</div>
			</div>
			<!-- 弹出层2内容 -->
			<div id="light2" class="white_content">
				<div class="close"><a href="javascript:void(0)" onclick="hide('light2')"> 关闭</a></div>
				<div class="con"> 
					<h2>添加类别</h2>
					<h3>---请认真核对父类别id的值，一级类别填写“0”(默认值)</h3>
					<form action="<%=basePath%>category/addCategory" method="post">
						<table class="layui-table">
					 		<tr>
					 			<td>类别名：</td>
					 			<td><input type="text" name="cName" value=""></td>
					 		</tr>
					 		<tr>
					 			<td>类别级别：</td>
					 			<td>
				 					<input type="radio" name="level" value="1" title="一级">一级
				 					<input type="radio" name="level" value="2" title="二级" checked>二级
					 			</td>
					 		</tr>
							<tr>
								<td>父类别id：</td>
								<td><input type="text" name="pid" value="0"></td>
							</tr>
							<tr>
								<td>操作：</td>
								<td>
									<input type="submit" name="submit" value="提交" class="layui-btn">
									<input type="reset" name="reset" value="重置" class="layui-btn">
								</td>
							</tr>
					 	</table>
					 </form>
				</div>
			</div>
			
			<div id="fade" class="black_overlay"></div>
			
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