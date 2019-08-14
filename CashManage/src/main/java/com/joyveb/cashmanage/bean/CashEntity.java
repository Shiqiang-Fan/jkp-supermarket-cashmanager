package com.joyveb.cashmanage.bean;

import lombok.Data;

@Data
public class CashEntity {
	public Integer limitnum;//连续兑奖限制次数
	public Integer totalnum;//累计兑奖限制次数
	public Long cashtime;//兑奖时间
	public String username;//兑奖限制用户
}
