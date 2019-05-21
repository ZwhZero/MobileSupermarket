package com.controller;

import java.io.IOException;
import java.math.BigDecimal;
//import java.util.Formatter.BigDecimalLayoutForm;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.model.Goods;
import com.model.ShopCartGoods;
import com.model.ShoppingCart;
import com.model.ShoppingCartVo;
import com.model.User;
import com.service.GoodsService;
import com.service.ShoppingCartService;
import com.util.BigDecimalUtil;

//import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {
	@Autowired
	public ShoppingCartService shoppingCartService;
	@Autowired
	public GoodsService goodsService;
	
	//前台相关 1.添加至购物车
	@RequestMapping("/addToCart")
    @ResponseBody
    public void addToCart(int gId,int gCount,HttpServletResponse response,HttpSession session) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		User user = (User)session.getAttribute("u");
//		int uId = user.getuId();
		String addResult = "";
		Map<String,String> map = new HashMap<>();
		if(user == null) { //用户未登录
			addResult = "logError";
			map.put("result", addResult);
			JSONObject v = JSONObject.fromObject(map);
			response.getWriter().print(v);
			System.out.println("获取session中的user失败");
		} else { //用户已登录
			if(gId == 0 || gCount == 0) { //已登录但没有获取到欲添加的商品信息
				addResult = "sendError";
				map.put("result", addResult);
				JSONObject v = JSONObject.fromObject(map);
				System.out.println(v);
				response.getWriter().print(v);
			} else { //已登录且获取商品信息成功
				int uId = user.getuId();
				ShoppingCart cart = shoppingCartService.selectCartByuIdAndgId(uId,gId);
				if(cart == null) { //购物车项不存在，新建购物车项
					ShoppingCart mycart = new ShoppingCart();
					mycart.setuId(uId);
					mycart.setgId(gId);
					mycart.setgCount(gCount);
					shoppingCartService.addToCart(mycart);
					System.out.println("新建购物车项success");
				} else { //已有购物车,更新购物车数量信息
					gCount = cart.getgCount() + gCount;
					cart.setgCount(gCount);
					shoppingCartService.updateCartSelective(cart);
					System.out.println("更新购物车数量信息success");
				}
				addResult = "true";
				map.put("result", addResult);
				JSONObject v = JSONObject.fromObject(map);
				System.out.println(v);
				response.getWriter().print(v);
			}
		}
	}
	//2.购物车List
	@RequestMapping("/fcartList")
    @ResponseBody
	public void fcartList(HttpServletResponse response,HttpSession session) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		User user = (User)session.getAttribute("u");
		if(user == null) { //未登录,返回带有error_code字段(700)的错误json对象
			Map<String,String> map = new HashMap<>();
			map.put("error_code","700");
			JSONObject v = JSONObject.fromObject(map);
			response.getWriter().print(v);
		} else { //已登录，返回限制数量且转换为json对象后的ShoppingCartVo对象
			int currentuId = user.getuId();
			ShoppingCartVo currentCart = getCartVoLimit(currentuId);
			System.out.println(currentCart);
			JSONObject v = JSONObject.fromObject(currentCart);
			System.out.println(v);
			response.getWriter().print(v);
		}
	}
	//3.Ajax更新购物车相关信息
	@RequestMapping("/fupdateCart")
    @ResponseBody
    public void fupdateCart(int gId,int gCount,HttpServletResponse response,HttpSession session) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		User user = (User)session.getAttribute("u");
		Map<String,Object> map = new HashMap<>();
		if(user == null) { //未登录,返回带有error_code字段(700)的错误json对象
//			Map<String,String> map = new HashMap<>();
			map.put("error_code","700");
			JSONObject v = JSONObject.fromObject(map);
			response.getWriter().print(v);
		} else { //已登录，返回变化后的商品数量与需要变化的总价格、总数量
			int currentuId = user.getuId();
			ShoppingCart updateCart = shoppingCartService.selectCartByuIdAndgId(currentuId, gId);
			updateCart.setgCount(gCount);
			shoppingCartService.updateCartSelective(updateCart);
			ShoppingCartVo currentCartVo = getCartVoLimit(currentuId);
			BigDecimal cartTotalPrice = currentCartVo.getCartTotalPrice();
			int cartTotalCount = currentCartVo.getCartTotalCount();
			map.put("gCount", gCount);
			map.put("cartTotalCount", cartTotalCount);
			map.put("cartTotalPrice", cartTotalPrice);
			JSONObject v = JSONObject.fromObject(map);
			System.out.println("更新前台购物车信息：" + v);
			response.getWriter().print(v);
		}
	}
	
	//4.前台删除购物车指定项信息
	@RequestMapping("/fdeleteItem")
    @ResponseBody
	public void fdeleteItem(int cartId,HttpServletResponse response,HttpSession session) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		User currentuser = (User)session.getAttribute("u");
		Map<String,String> map = new HashMap<>();
		if(currentuser == null) { //未登录,返回带有error_code字段(700)的错误json对象
//			Map<String,String> map = new HashMap<>();
			map.put("error_code","700");
			JSONObject v = JSONObject.fromObject(map);
			response.getWriter().print(v);
		} else { //已登录，删除指定购物车信息，返回提示成功的json对象
//			int uId = currentuser.getuId();
			if(cartId!=0) {
				shoppingCartService.deleteCartByCartId(cartId);
				map.put("result","true");
			} else {
				map.put("result","false");
			}
			JSONObject v = JSONObject.fromObject(map);
			response.getWriter().print(v);
		}
	}
    
	//5.清空购物车fdeleteCart
	@RequestMapping("/fdeleteCart")
    @ResponseBody
    public void fdeleteCart(HttpServletResponse response,HttpSession session) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		User currentuser = (User)session.getAttribute("u");
		Map<String,String> map = new HashMap<>();
		if(currentuser == null) { //未登录,返回带有error_code字段(700)的错误json对象
			map.put("error_code","700");
			JSONObject v = JSONObject.fromObject(map);
			response.getWriter().print(v);
		} else { //已登录，清空指定购物车所有信息，返回提示成功的json对象
			int currentuId = currentuser.getuId();
			//清除当前用户购物车（uId）
			shoppingCartService.deleteCartByuId(currentuId);
			map.put("result","true");
			JSONObject v = JSONObject.fromObject(map);
			response.getWriter().print(v);
		}
	}
    
	//判断库存并返回处理后的结果ShoppingCartVo对象
	private ShoppingCartVo getCartVoLimit(int uId) {
		ShoppingCartVo cartVo = new ShoppingCartVo();
		List<ShoppingCart> cartList = shoppingCartService.selectCartByuId(uId);
		
		//用来放置ShopCartGoods的list
		List<ShopCartGoods> cartGoodsList = Lists.newArrayList();
		//购物车总数量
		int cartTotalCount = 0;
		//购物车总价
		BigDecimal cartTotalPrice = new BigDecimal("0");
		
		if(!CollectionUtils.isEmpty(cartList)) {
			//遍历每个购物车，计算购物车中每个产品的数量*总价，库存等，用ShopCartGoods封装
			for(ShoppingCart cartItem : cartList) {
				//for (循环变量类型 循环变量名称 : 要被遍历的对象) {循环体}
				ShopCartGoods cartGoods = new ShopCartGoods();
				cartGoods.setuId(uId);
				cartGoods.setCartId(cartItem.getCartId());
				cartGoods.setgId(cartItem.getgId());
				cartGoods.setgCount(cartItem.getgCount());
				Goods goods = goodsService.findGoodsBygId(cartItem.getgId());
				if(goods != null) {
					cartGoods.setgName(goods.getgName());
					cartGoods.setPrice(goods.getSellprice());
					cartGoods.setCount(goods.getCount());
					cartGoods.setImgurl(goods.getImgurl());
					
					//判断库存
					int buyLimitCount = 0;
					if(goods.getCount() >= cartItem.getgCount()) {
						//库存充足
						buyLimitCount = cartItem.getgCount();
						cartGoods.setLimitCount("isEnough");
					} else {
						//库存不足
						buyLimitCount = goods.getCount();
						cartGoods.setLimitCount("isNotEnough");
						//购物车更新库存
						ShoppingCart updateCart = new ShoppingCart();
						updateCart.setCartId(cartItem.getCartId());
						updateCart.setgCount(buyLimitCount);
						shoppingCartService.updateCartSelective(updateCart);
					}
					cartGoods.setgCount(buyLimitCount);
					//设置该商品总价(商品单价*该商品个数)
					cartGoods.setGoodsTotalPrice(BigDecimalUtil.mul(goods.getSellprice().doubleValue(), cartGoods.getgCount()));
					//更新购物车商品总数量
					cartTotalCount = cartTotalCount + buyLimitCount;
					//更新购物车总价 
					cartTotalPrice = BigDecimalUtil.add(cartTotalPrice.doubleValue(),cartGoods.getGoodsTotalPrice().doubleValue());
				}
				cartGoodsList.add(cartGoods);
			}
		}
		cartVo.setCartTotalPrice(cartTotalPrice);
		cartVo.setShopCartList(cartGoodsList);
		cartVo.setCartTotalCount(cartTotalCount);
		return cartVo;
	}
	
}
