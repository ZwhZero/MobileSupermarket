<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

		<div class="layui-header">
			<div class="layui-logo">Zero超市后台管理</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			<ul class="layui-nav layui-layout-left">
				<!-- <li class="layui-nav-item"><a href="">控制台</a></li> -->
			</ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item">
					${a.aName}&nbsp;欢迎你
				</li>
				<li class="layui-nav-item">
					<a href="http://localhost:8080/SMarket/admin/login" target="_top">登录</a>
				</li>
				<li class="layui-nav-item">
					<a href="http://localhost:8080/SMarket/admin/exit" target="_top">退了</a>
				</li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item layui-nav-itemed"><a class=""
						href="javascript:;">用户管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="http://localhost:8080/SMarket/admin/adminlogin">管理员管理</a>
							</dd>
							<dd>
								<a href="http://localhost:8080/SMarket/user/findAllUser">客户管理</a>
							</dd>
						</dl>
					</li>
					<li class="layui-nav-item"><a href="javascript:;">商品管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="http://localhost:8080/SMarket/category/findAllCategory">商品类别管理</a>
							</dd>
							<dd>
								<a href="http://localhost:8080/SMarket/goods/findAllGoods">商品详细管理</a>
							</dd>
						</dl>
					</li>
					<li class="layui-nav-item"><a href="http://localhost:8080/SMarket/order/findAllOrder">订单管理</a></li>
				</ul>
			</div>
		</div>

		<div class="layui-footer">
			<!-- 底部固定区域 -->
			<p>学习用途       @William   &nbsp;&nbsp;&nbsp;   联系我们：1132586581@qq.com</p>
		</div>
	