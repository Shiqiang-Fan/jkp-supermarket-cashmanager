package com.joyveb.cashmanage.bean.response;

import java.util.List;

import com.joyveb.cashmanage.bean.AbstractEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class RepGameInfoEntity extends AbstractEntity{
	private List<RepQueryGameEntity> games;
	//private String commission;
	private String freightnum;//满几本免运费
	private String freightprice;//运费
	private String rcode;
	private String des;
}
