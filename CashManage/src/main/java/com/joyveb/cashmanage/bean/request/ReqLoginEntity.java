package com.joyveb.cashmanage.bean.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.joyveb.cashmanage.bean.AbstractEntity;

@Data
@EqualsAndHashCode(callSuper=true)
public class ReqLoginEntity extends AbstractEntity {

	/*// 1支付宝（彩票代销商）、2微信（彩票购买者）
	private String authcode;
	private String usertype;*/
	private String username;
	private String password;
}
