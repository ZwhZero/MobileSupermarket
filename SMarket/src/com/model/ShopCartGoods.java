package com.model;

import java.math.BigDecimal;

public class ShopCartGoods {
	//结合商品和购物车的一个(抽象)对象,存放单个条目信息
	private int cartId;
	private int uId;
	private int gId;
	private int gCount; //购物车中此商品数量
	private String gName; //购物车中此商品名
	private BigDecimal price; //该商品单价t_goods:price
	private int count; //该商品库存t_goods:count
	private String imgurl; //商品图片
	private BigDecimal goodsTotalPrice; //该商品总价(数量*单价)
	private String limitCount; //限制数量的一个flag-返回结果
	
	public String getLimitCount() {
		return limitCount;
	}
	public void setLimitCount(String limitCount) {
		this.limitCount = limitCount;
	}
	public BigDecimal getGoodsTotalPrice() {
		return goodsTotalPrice;
	}
	public void setGoodsTotalPrice(BigDecimal goodsTotalPrice) {
		this.goodsTotalPrice = goodsTotalPrice;
	}
	//get,set方法
	public int getCartId() {
		return cartId;
	}
	public int getuId() {
		return uId;
	}
	public int getgId() {
		return gId;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setgCount(int gCount) {
		this.gCount = gCount;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public int getgCount() {
		return gCount;
	}
	public String getgName() {
		return gName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public int getCount() {
		return count;
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
	public void setgNumber(int gCount) {
		this.gCount = gCount;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
