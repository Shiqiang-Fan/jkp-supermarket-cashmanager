package com.joyveb.cashmanage.bean.response;


import lombok.Data;
import lombok.EqualsAndHashCode;

import com.joyveb.cashmanage.bean.AbstractEntity;

@Data
@EqualsAndHashCode(callSuper=true)
public class RepQuerySumEntity extends AbstractEntity{
	private String sumamount;
	private String sumcount;
	private String sumdj;
	private Integer orderamount;
	private String rcode;
	private String des;
}
