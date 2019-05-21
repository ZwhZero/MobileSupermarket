package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapper.OrderItemMapper;
import com.model.OrderItem;
import com.service.OrderItemService;

@Service("OrderItemService")
@Transactional
public class OrderItemServiceImpl implements OrderItemService {
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	//插入订单条目信息（OrderItem）
	public int addOrderItem(OrderItem orderItem) {
		return orderItemMapper.addOrderItem(orderItem);
	}
	//查找订单条目信息(orderId,返回List)
	public List<OrderItem> findItemsByoId(int orderId) {
		return orderItemMapper.findItemsByoId(orderId);
	}
}
