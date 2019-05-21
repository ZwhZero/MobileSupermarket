package com.model;

import java.math.BigDecimal;
//import java.sql.Timestamp;
import java.util.List;

public class OrderVo {
	//封装订单相关所有内容的抽象对象
	private List<OrderItem> orderItemList;
	private int orderNumber;
	private BigDecimal orderPrice;
	private int orderId;
	private String createTime;
	private String uName;
	private String phone;
	private String address;
	private String remark;
	private int orderState;
	
	//get、set方法
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	public BigDecimal getOrderPrice() {
		return orderPrice;
	}
	public int getOrderId() {
		return orderId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public String getuName() {
		return uName;
	}
	public String getPhone() {
		return phone;
	}
	public String getAddress() {
		return address;
	}
	public String getRemark() {
		return remark;
	}
	public int getOrderState() {
		return orderState;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}
	
}
