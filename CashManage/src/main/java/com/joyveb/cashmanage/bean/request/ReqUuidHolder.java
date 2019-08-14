package com.joyveb.cashmanage.bean.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.joyveb.cashmanage.bean.AbstractEntity;

@Data
@EqualsAndHashCode(callSuper=true)
public class ReqUuidHolder extends AbstractEntity {

	private ReqUuidEntity body;
	
}
