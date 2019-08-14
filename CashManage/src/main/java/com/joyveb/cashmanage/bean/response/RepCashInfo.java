package com.joyveb.cashmanage.bean.response;

import com.joyveb.cashmanage.bean.AbstractEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RepCashInfo extends AbstractEntity{
	private String gameInfo;
	private Long time;
	private String winAmount;
	private String amountsign;
	private String transferStatus;
}
