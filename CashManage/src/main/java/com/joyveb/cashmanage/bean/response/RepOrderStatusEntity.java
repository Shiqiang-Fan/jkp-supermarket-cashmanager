package com.joyveb.cashmanage.bean.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.joyveb.cashmanage.bean.AbstractEntity;

@Data
@EqualsAndHashCode(callSuper = true)
public class RepOrderStatusEntity extends AbstractEntity {
	private String orderstatus;
	private String rcode;
	private String des;
}
