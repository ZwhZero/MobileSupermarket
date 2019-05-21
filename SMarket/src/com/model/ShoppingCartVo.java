package com.model;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCartVo {
	//单个购物车条目Item 与 购物车List 结合的抽象对象,存放全部条目信息
	private List<ShopCartGoods> shopCartList;
	private BigDecimal cartTotalPrice;
	private int cartTotalCount;
	
	public int getCartTotalCount() {
		return cartTotalCount;
	}
	public void setCartTotalCount(int cartTotalCount) {
		this.cartTotalCount = cartTotalCount;
	}
	public List<ShopCartGoods> getShopCartList() {
		return shopCartList;
	}
	public BigDecimal getCartTotalPrice() {
		return cartTotalPrice;
	}
	public void setShopCartList(List<ShopCartGoods> shopCartList) {
		this.shopCartList = shopCartList;
	}
	public void setCartTotalPrice(BigDecimal cartTotalPrice) {
		this.cartTotalPrice = cartTotalPrice;
	}
	
}
