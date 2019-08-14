package com.joyveb.cashmanage.bean.request;

import com.joyveb.cashmanage.bean.AbstractEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ReqGameInfoEntityHolder extends AbstractEntity{
	private ReqGameInfoEntity body;
}
