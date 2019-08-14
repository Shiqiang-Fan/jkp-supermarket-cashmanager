package com.joyveb.cashmanage.bean.response;

import com.joyveb.cashmanage.bean.AbstractEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class RepCommonEntity extends AbstractEntity{
	private String rcode;
	private String des;
}
