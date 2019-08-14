package com.joyveb.cashmanage.bean.response;

import com.joyveb.cashmanage.bean.AbstractEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class RepUpdatePassEntity extends AbstractEntity{
	private String ischild;//是否为二级账户
	private String rcode;
	private String des;
}
