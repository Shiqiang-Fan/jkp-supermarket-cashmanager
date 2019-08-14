package com.joyveb.cashmanage.bean.request;

import com.joyveb.cashmanage.bean.AbstractEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReqCashQueryEntity extends AbstractEntity{
	private String count;//获取记录的数据如果为空默认为10
	private String start;//获取记录的开始位置默认从最晚的一个记录开始
	private String type; //0查所有1查已返奖2待返奖
}
