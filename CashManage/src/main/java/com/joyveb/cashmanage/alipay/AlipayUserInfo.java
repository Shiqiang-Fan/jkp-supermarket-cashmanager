package com.joyveb.cashmanage.alipay;

import lombok.Data;

@Data
public class AlipayUserInfo {

	private String alipayUserId;
	private String email;
	private String mobile;
	private String realName;
	// 身份证号
	private String certNo;
	//是否实名用户
	private String isCertified;
	private String loginTime;
}
