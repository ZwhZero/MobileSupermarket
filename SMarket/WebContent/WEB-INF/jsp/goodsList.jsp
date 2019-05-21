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
	<title>商品管理-后台管理</title>
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
		function showEdit(tag,gId,gName,description,sellprice,sellcount,count,categoryId,imgurl){
			var light=document.getElementById(tag);
			var fade=document.getElementById('fade');
			var oldgId = gId;
			var oldgName = gName;
			var oldDescription = description;
			var oldSellprice = sellprice;
			var oldSellcount = sellcount;
			var oldCount = count;
			var oldCategoryId = categoryId;
			var oldImgurl = imgurl;
			document.getElementById('mygId').value = oldgId;
			document.getElementById('editgId').innerHTML = oldgId;
			document.getElementById('editgName').value = oldgName;
			document.getElementById('editDescription').value = oldDescription;
			document.getElementById('editSellprice').value = oldSellprice;
			document.getElementById('editSellcount').value = oldSellcount;
			document.getElementById('editCount').value = oldCount;
			document.getElementById('editCategoryId').value = oldCategoryId;
			document.getElementById('editImgurl').value = oldImgurl;
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
			<div class="goods">
				<h1>◆商品管理</h1><br/>
				<h2>&nbsp;&nbsp;●商品信息List</h2><br/>
				<a href="javascript:void(0)" onclick="showAdd('light2')" class="layui-btn">添加商品</a>
				<table class="layui-table" lay-even>
					<tr>
						<th>gId</th>
						<th>商品名</th>
						<th>商品描述</th>
						<th>售价</th>
						<th>销量</th>
						<th>库存</th>
						<th>类别id</th>
						<th>图片url名</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty goodsList }">
						<c:forEach items="${goodsList}" var="list">
							<tr>
								<td>${list.gId}</td>
								<td>${list.gName}</td>
								<td>${list.description}</td>
								<td>${list.sellprice}</td>
								<td>${list.sellcount}</td>
								<td>${list.count}</td>
								<td>${list.categoryId}</td>
								<td>${list.imgurl}</td>
								<td>
									<a href="javascript:void(0);" onclick="showEdit('light','${list.gId}','${list.gName}','${list.description}','${list.sellprice}','${list.sellcount}','${list.count}','${list.categoryId}','${list.imgurl}')">编辑</a>&nbsp;&nbsp;&nbsp;
									<a href="<%=basePath%>goods/deleteGoods?gId=${list.gId}">删除</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
			
			<!-- 弹出层1内容 -->
			<div id="light" class="white_content">
				<div class="close"><a href="javascript:void(0)" onclick="hide('light')"> 关闭</a></div>
				<div class="con"> 
					<h2>编辑商品信息</h2>
					<h3>---请认真核对类别id的值、图片url名</h3>
					<form action="<%=basePath%>goods/updateGoods" method="post">
					<input type="hidden" name="gId" id="mygId" value="" />
						<table class="layui-table">
							<tr>
								<td>商品id：</td>
								<td><span id="editgId">0</span></td>
							</tr>
					 		<tr>
					 			<td>商品名：</td>
					 			<td><input type="text" id="editgName" name="gName" value="null"></td>
					 		</tr>
					 		<tr>
					 			<td>商品描述：</td>
					 			<td><input type="text" id="editDescription" name="description" value="null"></td>
					 		</tr>
					 		<tr>
					 			<td>商品售价：</td>
					 			<td><input type="text" id="editSellprice" name="sellprice" value="null"></td>
					 		</tr>
					 		<tr>
					 			<td>商品销量：</td>
					 			<td><input type="text" id="editSellcount" name="sellcount" value="null"></td>
					 		</tr>
					 		<tr>
					 			<td>商品库存：</td>
					 			<td><input type="text" id="editCount" name="count" value="null"></td>
					 		</tr>
							<tr>
								<td>类别id：</td>
								<td><input type="text" id="editCategoryId" name="categoryId" value="0"></td>
							</tr>
							<tr>
								<td>图片url名：</td>
								<td><input type="text" id="editImgurl" name="imgurl" value="0"></td>
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
					<h2>添加商品信息</h2>
					<h3>---请认真核对类别id的值、图片url名</h3>
					<form action="<%=basePath%>goods/addGoods" method="post">
						<table class="layui-table">
					 		<tr>
					 			<td>商品名：</td>
					 			<td><input type="text" name="gName" value=""></td>
					 		</tr>
					 		<tr>
					 			<td>商品描述：</td>
					 			<td><input type="text" name="description" value=""></td>
					 		</tr>
					 		<tr>
					 			<td>商品售价：</td>
					 			<td><input type="text" name="sellprice" value=""></td>
					 		</tr>
					 		<tr>
					 			<td>商品销量：</td>
					 			<td><input type="text" name="sellcount" value=""></td>
					 		</tr>
					 		<tr>
					 			<td>商品库存：</td>
					 			<td><input type="text" name="count" value=""></td>
					 		</tr>
							<tr>
								<td>类别id：</td>
								<td><input type="text" name="categoryId" value=""></td>
							</tr>
							<tr>
								<td>图片url名：</td>
								<td><input type="text" name="imgurl" value=""></td>
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