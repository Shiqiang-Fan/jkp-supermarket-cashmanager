package com.joyveb.cashmanage.xml.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResponseRecord implements Serializable {

	private String messageid;
	private String wagercardnum;
	private String gamecode;
	private String gameserialnum;
	private String packagenum;
	private String ticketnum;
	private long winAmount;
	private long commissionAmount;
	//private int amountSign;
}
