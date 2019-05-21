package com.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrderItem {
	int itemId;
	int uId;
	int gId;
	String gName;
	BigDecimal gPrice;
	int gCount;
	int orderId;
	Timestamp addTime;
	
	//get、set方法
	public int getItemId() {
		return itemId;
	}
	public int getuId() {
		return uId;
	}
	public int getgId() {
		return gId;
	}
	public String getgName() {
		return gName;
	}
	public BigDecimal getgPrice() {
		return gPrice;
	}
	public int getgCount() {
		return gCount;
	}
	public int getOrderId() {
		return orderId;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public void setgId(int gId) {
		this.gId = gId;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public void setgPrice(BigDecimal gPrice) {
		this.gPrice = gPrice;
	}
	public void setgCount(int gCount) {
		this.gCount = gCount;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	
}
