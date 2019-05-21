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
	<title>订单管理-后台管理</title>
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
		//'${list.orderId}','${list.uId}','${list.phone}','${list.address}','${list.oNumber}','${list.oMoney}','${list.createTime}','${list.remark}'
		function showEdit(tag,orderId,uId,phone,address,oNumber,oMoney,createTime,remark){
			var light=document.getElementById(tag);
			var fade=document.getElementById('fade');
			var oldOrderId = orderId;
			var olduId = uId;
			var oldPhone = phone;
			var oldAddress = address;
			var oldoNumber = oNumber;
			var oldoMoney = oMoney;
			var oldCreateTime = createTime;
			var oldRemark = remark;
			document.getElementById('theOrderId').value = oldOrderId;
			document.getElementById('editOrderId').innerHTML = oldOrderId;
			document.getElementById('theuId').value = olduId;
			document.getElementById('edituId').innerHTML = olduId;
			document.getElementById('thePhone').value = oldPhone;
			document.getElementById('editPhone').innerHTML = oldPhone;
			document.getElementById('theAddress').value = oldAddress;
			document.getElementById('editAddress').innerHTML = oldAddress;
			document.getElementById('theoNumber').value = oldoNumber;
			document.getElementById('editoNumber').innerHTML = oldoNumber;
			document.getElementById('theoMoney').value = oldoMoney;
			document.getElementById('editoMoney').innerHTML = oldoMoney;
			document.getElementById('theCreateTime').value = oldCreateTime;
			document.getElementById('editCreateTime').innerHTML = oldCreateTime;
			document.getElementById('theRemark').value = oldRemark;
			document.getElementById('editRemark').innerHTML = oldRemark;
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
			<div class="orderList">
				<h1>◆订单管理</h1><br/>
				<h2>&nbsp;&nbsp;●订单信息List</h2><br/>
				<table class="layui-table" lay-even>
					<tr>
						<th>订单Id</th>
						<th>用户Id</th>
						<th>联系电话</th>
						<th>联系地址</th>
						<th>商品数量</th>
						<th>总金额</th>
						<th>创建时间</th>
						<th>留言</th>
						<th>订单状态</th>
						<th>操作</th>
					</tr>
					<c:if test="${!empty orderList }">
						<c:forEach items="${orderList}" var="list">
							<tr>
								<td>${list.orderId}</td>
								<td>${list.uId}</td>
								<td>${list.phone}</td>
								<td>${list.address}</td>
								<td>${list.oNumber}</td>
								<td>${list.oMoney}</td>
								<td>${list.createTime}</td>
								<td>${list.remark}</td>
								<td>${list.orderState}</td>
								<td>
									<a href="javascript:void(0);" onclick="showEdit('light','${list.orderId}','${list.uId}','${list.phone}','${list.address}','${list.oNumber}','${list.oMoney}','${list.createTime}','${list.remark}')">编辑</a>&nbsp;&nbsp;&nbsp;
									<a href="<%=basePath%>order/deleteOrder?orderId=${list.orderId}">删除</a>
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
					<h2>编辑订单信息</h2>
					<h3>---请根据实际情况更改订单状态</h3>
					<form action="<%=basePath%>order/updateOrder" method="post">
					<input type="hidden" name="orderId" id="theOrderId" value="" />
					<input type="hidden" name="uId" id="theuId" value="" />
					<input type="hidden" name="phone" id="thePhone" value="" />
					<input type="hidden" name="address" id="theAddress" value="" />
					<input type="hidden" name="oNumber" id="theoNumber" value="" />
					<input type="hidden" name="oMoney" id="theoMoney" value="" />
					<input type="hidden" name="createTime" id="theCreateTime" value="" />
					<input type="hidden" name="remark" id="theRemark" value="" />
						<table class="layui-table">
							<tr>
								<td>订单id：</td>
								<td><span id="editOrderId">0</span></td>
							</tr>
					 		<tr>
					 			<td>用户id：</td>
					 			<td><span id="edituId">0</span></td>
					 		</tr>
					 		<tr>
					 			<td>联系电话：</td>
					 			<td><span id="editPhone">0</span></td>
					 		</tr>
					 		<tr>
					 			<td>联系地址：</td>
					 			<td><span id="editAddress">null</span></td>
					 		</tr>
					 		<tr>
					 			<td>商品总数：</td>
					 			<td><span id="editoNumber">null</span></td>
					 		</tr>
					 		<tr>
					 			<td>总金额：</td>
					 			<td><span id="editoMoney">null</span></td>
					 		</tr>
							<tr>
								<td>创建时间：</td>
								<td><span id="editCreateTime">null</span></td>
							</tr>
							<tr>
								<td>留言：</td>
								<td><span id="editRemark">null</span></td>
							</tr>
							<tr>
					 			<td>订单状态：</td>
					 			<td>
				 					<input type="radio" name="orderState" value="1" title="未处理"  checked>未处理
				 					<input type="radio" name="orderState" value="2" title="已处理">已处理
					 			</td>
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