package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.model.Order;

@Repository
public interface OrderMapper {
	//添加-初始化订单（获取返回的orderId）
	public int addOrder(@Param("order")Order order);
	//查找订单（orderId），返回Order对象
	public Order findOrderByoId(int orderId);
	//按需求更新Order信息(Order对象)
	public int updateOrderSelective(Order order);
	//更新OrderState信息(Order对象)
	public int updateOrderState(Order order);
	//查询用户历史订单(int uId)
	public List<Order> orderList(@Param("uId")int uId);
	//查询全部历史订单
	public List<Order> findAllOrder();
	//删除指定订单
	public int deleteOrderByoId(int orderId); 
		
}
