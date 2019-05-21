package com.service;

import java.util.List;

import com.model.Order;

public interface OrderService {
	//添加-初始化订单（通过插入的order对象get方法获取返回的orderId）
	public int addOrder(Order order);
	//查找订单（orderId），返回Order对象
	public Order findOrderByoId(int orderId);
	//按需求更新Order信息(Order对象)
	public int updateOrderSelective(Order order);
	//更新OrderState信息(Order对象)
	public int updateOrderState(Order order);
	//查询用户历史订单(int uId)
	public List<Order> orderList(int uId);
	//查询全部历史订单
	public List<Order> findAllOrder();
	//删除指定订单
	public int deleteOrderByoId(int orderId); 
	
}
