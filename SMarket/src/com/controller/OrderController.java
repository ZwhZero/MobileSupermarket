package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.model.Admin;
//import com.google.common.collect.Lists;
import com.model.Goods;
import com.model.Order;
import com.model.OrderItem;
import com.model.OrderVo;
import com.model.ShoppingCart;
import com.model.User;
import com.service.GoodsService;
import com.service.OrderItemService;
import com.service.OrderService;
import com.service.ShoppingCartService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	public OrderService orderService;
	@Autowired
	public ShoppingCartService shoppingCartService;
	@Autowired
	public GoodsService goodsService;
	@Autowired
	public OrderItemService orderItemService;
	
	//前台相关：1.生成订单（初始化）
	@RequestMapping("/addOrder")
    @ResponseBody
    public void addOrder(String cartTotalCount,String cartTotalPrice,HttpServletResponse response,HttpSession session) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		User currentuser = (User)session.getAttribute("u");
		if(currentuser == null) { //未登录,返回带有error_code字段(700)的错误json对象 
			Map<String,String> map = new HashMap<>();
			map.put("error_code","700");
			JSONObject v = JSONObject.fromObject(map);
			response.getWriter().print(v);
		} else { //已登录，初始化新订单，设置总数量、总价，设置指定orderId下的orderItem
			int currentuId = currentuser.getuId();
			Order newOrder = new Order();
			newOrder.setuId(currentuId);
			newOrder.setPhone(currentuser.getPhone());
			newOrder.setAddress(currentuser.getAddress());
			BigDecimal totalPrice = new BigDecimal(cartTotalPrice);
			System.out.println(totalPrice);
			newOrder.setoNumber(Integer.parseInt(cartTotalCount));
			newOrder.setoMoney(totalPrice);
			orderService.addOrder(newOrder);
			int newoId = newOrder.getOrderId();
			System.out.println(newoId);
			//获取当前数据库新生成的Order对象
			Order dbOrder = orderService.findOrderByoId(newoId);
			//获取当前user的购物车清单，存于currentCartList
			List<ShoppingCart> currentCartList = shoppingCartService.selectCartByuId(currentuId);
			//用来存放orderItem的List
//			List<OrderItem> orderItemList = Lists.newArrayList();
			//商品的新销量以及库存
			int newSellCount = 0; /*销量*/
			int newCount = 0; /*库存*/
			
			if(currentCartList == null) { //获取购物车清单失败，返回错误代码：703
				Map<String,String> map = new HashMap<>();
				map.put("error_code","703");
				JSONObject v = JSONObject.fromObject(map);
				response.getWriter().print(v);
			} else { //购物车不为空
				//遍历每个购物车，将购物车item信息赋给订单item，注意设置orderId
				if(!CollectionUtils.isEmpty(currentCartList)) {
					for(ShoppingCart cartItem : currentCartList) {
						//for (循环变量类型 循环变量名称 : 要被遍历的对象) {循环体}
						OrderItem orderItem = new OrderItem();
						orderItem.setuId(cartItem.getuId());
						orderItem.setgId(cartItem.getgId());
						orderItem.setgCount(cartItem.getgCount());
						orderItem.setAddTime(dbOrder.getCreateTime());
						orderItem.setOrderId(newoId);
						Goods goods = goodsService.findGoodsBygId(cartItem.getgId());
						if(goods != null) {
							orderItem.setgName(goods.getgName());
							orderItem.setgPrice(goods.getSellprice());
							newSellCount = goods.getSellcount() + cartItem.getgCount();
							goods.setSellcount(newSellCount);
							newCount = goods.getCount() - cartItem.getgCount();
							goods.setCount(newCount);
							//更新指定商品的销量以及库存
							goodsService.updateGoods(goods);
							
						}
						orderItemService.addOrderItem(orderItem);
					}
				}
				//清除当前用户购物车（uId）
				shoppingCartService.deleteCartByuId(currentuId);
			}
			//返回已登录处理完成信息:flag + newoId
			Map<String,Object> map = new HashMap<>();
			map.put("result","true");
			map.put("newoId", newoId);
			JSONObject v = JSONObject.fromObject(map);
			System.out.println(v);
			response.getWriter().print(v);
		}
		
	}
	
	//2.加载生成的订单以供修改确认
	@RequestMapping("/forderInfo")
    @ResponseBody
	public void forderInfo(int newoId,HttpServletResponse response,HttpSession session) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		User currentuser = (User)session.getAttribute("u");
		if(currentuser == null) { //未登录,返回带有error_code字段(700)的错误json对象 
			Map<String,String> map = new HashMap<>();
			map.put("error_code","700");
			JSONObject v = JSONObject.fromObject(map);
			response.getWriter().print(v);
		} else {
			int orderId = newoId;
			String uName = currentuser.getuName();
			//获取封装好的order所有信息
			OrderVo orderVo = getAllOrder(orderId,uName);
			JSONObject v = JSONObject.fromObject(orderVo);
			System.out.println(v);
			response.getWriter().print(v);
		}
	}
	
	//3.按提交的确认订单信息更新订单
	@RequestMapping("/fsubmitOrder")
    @ResponseBody
    public void fsubmitOrder(int newoId,String phone,String address,String remark,HttpServletResponse response,HttpSession session) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		User currentuser = (User)session.getAttribute("u");
		Map<String,String> map = new HashMap<>();
		if(currentuser == null) { //未登录,返回带有error_code字段(700)的错误json对象 
			map.put("error_code","700");
			JSONObject v = JSONObject.fromObject(map);
			response.getWriter().print(v);
		} else {
			Order myOrder = orderService.findOrderByoId(newoId);
			Order updataOrder = new Order();
			int orderState = 1; /*用户确认订单后状态更新为1*/
			updataOrder.setPhone(phone);
			updataOrder.setAddress(address);
			updataOrder.setRemark(remark);
			updataOrder.setOrderId(myOrder.getOrderId());
			updataOrder.setuId(myOrder.getuId());
			updataOrder.setoNumber(myOrder.getoNumber());
			updataOrder.setoMoney(myOrder.getoMoney());
			updataOrder.setCreateTime(myOrder.getCreateTime());
			updataOrder.setOrderState(orderState);
			orderService.updateOrderSelective(updataOrder);
			map.put("result","true");
			JSONObject v = JSONObject.fromObject(map);
			response.getWriter().print(v);
		}
	}
    
    //4.加载历史订单
	@RequestMapping("/forderList")
    @ResponseBody
    public void forderList(HttpServletResponse response,HttpSession session) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		User currentuser = (User)session.getAttribute("u");
		Map<String,Object> map = new HashMap<>();
		if(currentuser == null) { //未登录,返回带有error_code字段(700)的错误json对象 
			map.put("error_code","700");
			JSONObject v = JSONObject.fromObject(map);
			response.getWriter().print(v);
		} else {
			int currentuId = currentuser.getuId();
			String currentuName = currentuser.getuName();
			List<Order> myOrderList = orderService.orderList(currentuId);
			//用来放置返回的OrderVo对象的List
			List<OrderVo> orderVoList = Lists.newArrayList();
			
			if(!CollectionUtils.isEmpty(myOrderList)) {
				//遍历用户的全部订单信息，获取相关信息封装成对应的OrderVo对象
				for(Order orderItem : myOrderList) {
					int orderId = orderItem.getOrderId();
					OrderVo oneOrderVo = getAllOrder(orderId,currentuName);
					orderVoList.add(oneOrderVo);
				}
			}
			JSONArray v = JSONArray.fromObject(orderVoList);
			response.getWriter().print(v);
		}
	}
	
	//封装订单相关所有内容
	private OrderVo getAllOrder(int orderId,String uName) {
		OrderVo orderVo = new OrderVo();
		//获取订单条目信息
		List<OrderItem> orderItemList = orderItemService.findItemsByoId(orderId);
		//获取新建的Order对象
		Order myOrder = orderService.findOrderByoId(orderId);
		//将createTime转换为String格式方便前台展示
		Timestamp timestamp = myOrder.getCreateTime();   
        String createTimeStr = "";   
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");   
        try {   
            //方法一   
        	createTimeStr = sdf.format(timestamp);   
            System.out.println(createTimeStr);   
            //方法二   
//            createTimeStr = timestamp.toString();   
//            System.out.println(createTimeStr);   
        } catch (Exception e) {   
            e.printStackTrace();   
        }  
        
		//设置封装对象orderVo的属性
		orderVo.setOrderItemList(orderItemList);
		orderVo.setOrderNumber(myOrder.getoNumber());
		orderVo.setOrderPrice(myOrder.getoMoney());
		orderVo.setOrderId(orderId);
		orderVo.setCreateTime(createTimeStr);
		orderVo.setuName(uName);
		orderVo.setPhone(myOrder.getPhone());
		orderVo.setAddress(myOrder.getAddress());
		orderVo.setRemark(myOrder.getRemark());
		orderVo.setOrderState(myOrder.getOrderState());
		return orderVo;
	}
	
	//后台相关-1.展示全部订单信息
	@RequestMapping("/findAllOrder")
	public String findAllOrder(HttpServletRequest request) {
		List<Order> orderList = orderService.findAllOrder();
		request.setAttribute("orderList", orderList);
		return "orderList";
	}
	//2.更新订单状态信息
	@RequestMapping("/updateOrder")
	public void updateOrder(Order order,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=null;
		System.out.println(order.getOrderId() + order.getOrderState());
		if(orderService.updateOrderState(order)!=0) { /*修改订单信息成功*/
			try {
				out = response.getWriter();
				out.print("<script>alert('修改信息成功！');window.location.href='http://localhost:8080/SMarket/order/findAllOrder';</script>");
			}catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				out.flush();
				out.close();
			}
		} else { /*修改订单信息失败*/
			try {
				out = response.getWriter();
				out.print("<script>alert('修改信息失败！');window.history.back(-1);</script>");
			}catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				out.flush();
				out.close();
			}
		}
	}
	//2.删除指定订单
	@RequestMapping("/deleteOrder")
	public void deleteOrder(int orderId,HttpServletResponse response,HttpSession session) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		Admin currentAdmin = (Admin)session.getAttribute("a");
		PrintWriter out=null;
		if(currentAdmin!=null) { /*管理员登录授权*/
			if(orderService.deleteOrderByoId(orderId)!=0) {
				try {
					out = response.getWriter();
					out.print("<script>alert('删除成功！');window.location.href='http://localhost:8080/SMarket/order/findAllOrder';</script>");
				}catch(IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally{
					out.flush();
					out.close();
				}
			} else {
				try {
					out = response.getWriter();
					out.print("<script>alert('删除失败！');window.history.back(-1);</script>");
				}catch(IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally{
					out.flush();
					out.close();
				}
			}
		} else { /*管理员未登录，无权限*/
			try {
				out = response.getWriter();
				out.print("<script>alert('未登录！不允许删除。');window.history.back(-1);</script>");
			}catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				out.flush();
				out.close();
			}
		}
	}
	
}
