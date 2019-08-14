package com.joyveb.cashmanage.bean.response;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.joyveb.cashmanage.bean.AbstractEntity;
@Data
@EqualsAndHashCode(callSuper = true)
public class RepCashQueryEntity extends AbstractEntity{
	private Integer total;
	private Integer count;
	private Integer curdayTotal;
	private Long curdayAmount;
	private Date curday;
	private List<RepCashInfo> records;
	private String rcode;
	private String des;
}
