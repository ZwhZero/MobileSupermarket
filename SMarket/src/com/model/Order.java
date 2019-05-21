package com.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
//import java.util.Date;

public class Order {
	int orderId;
	int uId;
	String phone;
	String address;
	int oNumber;
	BigDecimal oMoney;
	Timestamp createTime;
	String remark;
	int orderState;
	
	//get、set方法
	public int getOrderId() {
		return orderId;
	}
	public int getuId() {
		return uId;
	}
	public String getPhone() {
		return phone;
	}
	public String getAddress() {
		return address;
	}
	public int getoNumber() {
		return oNumber;
	}
	public BigDecimal getoMoney() {
		return oMoney;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public String getRemark() {
		return remark;
	}
	public int getOrderState() {
		return orderState;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setoNumber(int oNumber) {
		this.oNumber = oNumber;
	}
	public void setoMoney(BigDecimal oMoney) {
		this.oMoney = oMoney;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}
	
}
