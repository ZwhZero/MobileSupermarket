package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.model.OrderItem;

@Repository
public interface OrderItemMapper {
	//插入订单条目信息（OrderItem）
	public int addOrderItem(OrderItem orderItem);
	//查找订单条目信息(orderId,返回List)
	public List<OrderItem> findItemsByoId(@Param("orderId")int orderId);
}
