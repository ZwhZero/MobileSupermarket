package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.model.ShoppingCart;

@Repository
public interface ShoppingCartMapper {
	//添加商品到购物车
	int addToCart(ShoppingCart cart);
	//根据uid和gid查询购物车
	ShoppingCart selectCartByuIdAndgId(@Param("uId")int uId,@Param("gId")int gId);
	//更新购物车信息(按cartId,有新值的)
	int updateCartSelective(ShoppingCart cart);
	//更新购物车信息(按cartId,all)
	
	//查询指定用户所有购物车项(uId,返回List)
	List<ShoppingCart> selectCartByuId(int uId);
	//删除购物车项(cartId)
	int deleteCartByCartId(int cartId);
	//删除购物车项(uId)
	int deleteCartByuId(int uId);
}
