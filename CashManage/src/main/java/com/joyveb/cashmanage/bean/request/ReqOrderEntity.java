package com.joyveb.cashmanage.bean.request;

import java.util.List;

import com.joyveb.cashmanage.bean.AbstractEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ReqOrderEntity extends AbstractEntity{
	/*private String receiver;
	private String address;
	private String delivertime;
	private Long phone;*/
	private String receiverid;
	private Long totalamount;
	private Long totalnum;
	private List<ReqGamesEntity> games;
	private String againpay;
	private String orderid;
	private Long freightprice;
}
