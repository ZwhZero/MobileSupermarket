package com.service;

import java.util.List;

import com.model.OrderItem;

public interface OrderItemService {
	//插入订单条目信息（OrderItem）
	public int addOrderItem(OrderItem orderItem);
	//查找订单条目信息(orderId,返回List)
	public List<OrderItem> findItemsByoId(int orderId);
	
	
}
