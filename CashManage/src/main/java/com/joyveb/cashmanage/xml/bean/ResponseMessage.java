package com.joyveb.cashmanage.xml.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResponseMessage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private ResponseHeadBean headBean;
	private ResponseMessageBody messageBody;

}
