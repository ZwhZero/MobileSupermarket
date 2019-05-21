package com.model;

import java.math.BigDecimal;

public class Goods {
	int gId;
	String gName;
	String description;
	BigDecimal sellprice;
	int sellcount; //销售量
	int count; //库存
	int categoryId;
	String imgurl;
	
	public int getgId() {
		return gId;
	}
	public String getgName() {
		return gName;
	}
	public String getDescription() {
		return description;
	}
	public BigDecimal getSellprice() {
		return sellprice;
	}
	public int getSellcount() {
		return sellcount;
	}
	public int getCount() {
		return count;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public String getImgurl() {
		return imgurl;
	}
	
	public void setgId(int gId) {
		this.gId = gId;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setSellprice(BigDecimal sellprice) {
		this.sellprice = sellprice;
	}
	public void setSellcount(int sellcount) {
		this.sellcount = sellcount;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	
}
