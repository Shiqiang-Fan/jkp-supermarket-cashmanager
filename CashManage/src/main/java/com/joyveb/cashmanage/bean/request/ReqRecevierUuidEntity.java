package com.joyveb.cashmanage.bean.request;

import com.joyveb.cashmanage.bean.AbstractEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ReqRecevierUuidEntity extends AbstractEntity{
	private String uuid;
	private String receiver;
	private String address;
	private Long phone;
	private String isdefault;
}
