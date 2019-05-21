package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapper.GoodsMapper;
import com.mapper.ShoppingCartMapper;
import com.model.ShoppingCart;
import com.service.ShoppingCartService;

@Service("ShoppingCartService")
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService{
	@Autowired
	public ShoppingCartMapper shoppingCartMapper;
	@Autowired
	public GoodsMapper goodsMapper;
	
	//添加商品到购物车
	public int addToCart(ShoppingCart cart) {
		return shoppingCartMapper.addToCart(cart);
	}
	
	//查找cart（gid,uid）
	public ShoppingCart selectCartByuIdAndgId(int uId,int gId) {
		return shoppingCartMapper.selectCartByuIdAndgId(uId, gId);
	};
	
	//更新购物车信息(按cartId,有新值的)
	public int updateCartSelective(ShoppingCart cart) {
		return shoppingCartMapper.updateCartSelective(cart);
	}
	
	//更新购物车(按cartId,all)
	
	
	//查询指定用户所有购物车项(uId,返回List)
	public List<ShoppingCart> selectCartByuId(int uId){
		return shoppingCartMapper.selectCartByuId(uId);
	}
	
	//删除购物车指定项信息
	public int deleteCartByCartId(int cartId) {
		return shoppingCartMapper.deleteCartByCartId(cartId);
	}
	
	//删除购物车项(uId)
	public int deleteCartByuId(int uId) {
		return shoppingCartMapper.deleteCartByuId(uId);
	}
}
