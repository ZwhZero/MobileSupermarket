package com.service;

import java.util.List;

import com.model.ShoppingCart;

public interface ShoppingCartService {
	//添加商品到购物车
	int addToCart(ShoppingCart cart);
	
	//查找cart(uid,gid)
	ShoppingCart selectCartByuIdAndgId(int uId,int gId);
	
	//更新购物车信息(按cartId,有新值的)
	int updateCartSelective(ShoppingCart cart);
	
	//更新购物车(按cartId,all)
	
	//查询指定用户所有购物车项(uId,返回List)
	List<ShoppingCart> selectCartByuId(int uId);
	
	//删除购物车指定信息
	int deleteCartByCartId(int cartId);
	
	//删除购物车项(uId)
	int deleteCartByuId(int uId);
}
