package com.joyveb.cashmanage.xml.bean;

import lombok.Data;

/**
 * @title 响应的头部信息实体类
 * @author wz
 * 
 */
@Data
public class CommonHeadBean {
	private String command; //命令吗
	private String messageid;//消息ID
	private String timestamp;//请求时间(结算时间)
	private String md;//头部校验码

}
