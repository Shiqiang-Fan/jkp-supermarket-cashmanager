package com.joyveb.cashmanage.bean.request;

import com.joyveb.cashmanage.bean.AbstractEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ReqGamesEntity extends AbstractEntity{
	private String gamecode;
	private Long orderamount;
	private Long gamenum;
}
