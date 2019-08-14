package com.joyveb.cashmanage.bean.response;

import java.util.List;

import com.joyveb.cashmanage.bean.AbstractEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class RepListReceiverEntity extends AbstractEntity{
	private List<RepReceiverEntity> receivers;
	private String commission;
	private String rcode;
	private String des;
}
