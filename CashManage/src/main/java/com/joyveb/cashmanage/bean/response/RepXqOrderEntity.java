package com.joyveb.cashmanage.bean.response;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.joyveb.cashmanage.bean.AbstractEntity;

@Data
@EqualsAndHashCode(callSuper=true)
public class RepXqOrderEntity extends AbstractEntity{
	private String orderid;
	private String orderstatus;
	private Long totalnum;
	private Long totalamount;
	private Date ordertime;
	private Long paytime;
	private Long freightprice;
	private List<RepGameEntity> games;
}
