package com.model;

import java.sql.Timestamp;
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.List;

public class ShoppingCart {
	//链接数据库,存放主要信息的cart对象
	private int cartId;
	private int uId;
	private int gId;
	private int gCount;
	Timestamp createTime;
//	private int uId;
//	private int gId;
//	private List<ShopCartItem> cartGoodsList;/*购物车itemList
//	private int totalNumber;
//	private BigDecimal totalMoney;
	//构造方法
	public ShoppingCart(int cartId,int uId,int gId,int gCount,Timestamp createTime)
	{
		this.cartId = cartId;
		this.uId = uId;
		this.gId = gId;
		this.gCount = gCount; //购物车中此商品数量
		this.createTime = createTime;
	}
	public ShoppingCart() {
		super();
	}
	
	//get(),set()
	public int getCartId() {
		return cartId;
	}
	public int getuId() {
		return uId;
	}
	public int getgId() {
		return gId;
	}
	public int getgCount() {
		return gCount;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public void setgId(int gId) {
		this.gId = gId;
	}
	public void setgCount(int gCount) {
		this.gCount = gCount;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
}
