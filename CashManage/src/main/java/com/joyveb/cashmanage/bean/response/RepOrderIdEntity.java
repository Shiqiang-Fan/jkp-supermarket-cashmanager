package com.joyveb.cashmanage.bean.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.joyveb.cashmanage.bean.AbstractEntity;

@Data
@EqualsAndHashCode(callSuper = true)
public class RepOrderIdEntity extends AbstractEntity {
	private String orderid;
	private String twocodepath;//二维码路径
	private String rcode;
	private String paytimeout;
	private String des;
}
