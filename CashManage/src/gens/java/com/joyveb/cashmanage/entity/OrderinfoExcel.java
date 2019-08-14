package com.joyveb.cashmanage.entity;


public class OrderinfoExcel{

	public String getWINAMOUNT() {
		return WINAMOUNT;
	}
	public void setWINAMOUNT(String wINAMOUNT) {
		WINAMOUNT = wINAMOUNT;
	}
	private String ordertime;
	private String fullname;
	private String orderamount;
	private String WINAMOUNT;
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getOrderamount() {
		return orderamount;
	}
	public void setOrderamount(String orderamount) {
		this.orderamount = orderamount;
	}
	
	
	
}