package com.joyveb.cashmanage.common;

import lombok.Data;


/**
 * 兑奖请求信息类
 * @author Administrator
 *
 */
public @Data class ValidPrize {
	
	private String sessionID;
	private String sequenceNumber;
	private String time;
	private String checksum;
	private String prodRevision;
	
	private String transactionType;
	private String transaction = "validRequest";
	private String serialNumber;

}
