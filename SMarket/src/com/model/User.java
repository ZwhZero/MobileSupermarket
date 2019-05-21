package com.model;

public class User {
	private int uId;
	private String uName;
	private String uPassword;
    private String age;
    private String sex;
    private String phone;
    private String address;
    private String email;
    
    //set,get方法
	public int getuId() {
		return uId;
	}
	public String getuName() {
		return uName;
	}
	public String getuPassword() {
		return uPassword;
	}
	public String getAge() {
		return age;
	}
	public String getSex() {
		return sex;
	}
	public String getPhone() {
		return phone;
	}
	public String getAddress() {
		return address;
	}
	public String getEmail() {
		return email;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
}
