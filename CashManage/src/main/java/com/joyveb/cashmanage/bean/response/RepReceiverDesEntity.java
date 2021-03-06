package com.joyveb.cashmanage.bean.response;

import com.joyveb.cashmanage.bean.AbstractEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class RepReceiverDesEntity extends AbstractEntity{
	private String receiver;
	private String address;
	private Long phone;
	private String rcode;
	private String des;
	private String uuid;
}
