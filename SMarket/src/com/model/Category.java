package com.model;

public class Category {
	int cId;
	String cName;
	int level;
	int pid;
	
	public int getcId() {
		return cId;
	}
	public String getcName() {
		return cName;
	}
	public int getLevel() {
		return level;
	}
	public int getPid() {
		return pid;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
}
