package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapper.OrderMapper;
import com.model.Order;
import com.service.OrderService;

@Service("OrderService")
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper orderMapper;
	
	//添加-初始化订单（通过插入的order对象get方法获取返回的orderId）
	public int addOrder(Order order) {
		return orderMapper.addOrder(order);
	}
	
	//查找订单（orderId），返回Order对象
	public Order findOrderByoId(int orderId) {
		return orderMapper.findOrderByoId(orderId);
	}
	
	//按需求更新Order信息(Order对象)
	public int updateOrderSelective(Order order) {
		return orderMapper.updateOrderSelective(order);
	}
	
	//更新OrderState信息(Order对象)
	public int updateOrderState(Order order) {
		return orderMapper.updateOrderState(order);
	}
	
	//查询用户历史订单(int uId)
	public List<Order> orderList(int uId) {
		return orderMapper.orderList(uId);
	}
	
	//查询全部历史订单
	public List<Order> findAllOrder(){
		return orderMapper.findAllOrder();
	}
	
	//删除指定订单
	public int deleteOrderByoId(int orderId) {
		return orderMapper.deleteOrderByoId(orderId);
	}
	
}
