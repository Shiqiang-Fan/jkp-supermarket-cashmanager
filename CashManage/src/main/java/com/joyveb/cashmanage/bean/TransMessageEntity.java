package com.joyveb.cashmanage.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransMessageEntity extends HeadEntityHolder {

	private AbstractEntity body;
	//private WebApplicationContext ctx;
	private String reqJson;
	private String result;
	//private String failReason;
	private HttpServletRequest request;
	private HttpServletResponse response;

}
