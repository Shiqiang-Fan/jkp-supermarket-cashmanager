package com.joyveb.cashmanage.xml.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseMessage1009Body extends ResponseMessageBody{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4593563617447529414L;
	private String gamecode;
	private String gameserial;//==GAMEBATCH
	private String packagenum;//==BOOKNUM
	private String ticketnum;
	private int winState;
	private long winAmount;
//	private int amountSign;
	private long commisionAmount;
	
}
