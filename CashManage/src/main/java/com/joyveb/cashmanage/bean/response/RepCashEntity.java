package com.joyveb.cashmanage.bean.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.joyveb.cashmanage.bean.AbstractEntity;

@Data
@EqualsAndHashCode(callSuper = true)
public class RepCashEntity extends AbstractEntity {

	//private String message;
	//大奖提示
	private String amountTip;
	
	private String gamecode;
	private String gameserial;
	private String packagenum;
	private String ticketnum;
	private short winstate;
	private long winamount;
	//private short amountsign;
	private String rcode;
	private String des;
	/*private String ticketfeaturecode;
	private String wininfo;
	private String decrCode;*/
	
}
