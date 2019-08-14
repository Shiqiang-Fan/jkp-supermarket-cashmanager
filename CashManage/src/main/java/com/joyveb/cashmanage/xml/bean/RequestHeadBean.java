package com.joyveb.cashmanage.xml.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @title 响应的头部信息实体类
 * @author wz
 * 
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RequestHeadBean extends CommonHeadBean{
	private String version;
	private String merchantid;
}
