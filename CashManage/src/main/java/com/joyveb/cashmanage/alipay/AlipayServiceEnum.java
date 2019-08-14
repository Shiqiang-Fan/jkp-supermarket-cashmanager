/*package com.joyveb.cashmanage.alipay;
*//**
 * 支付宝 接口 service
 * @author mfg
 *
 *//*
public enum AlipayServiceEnum {
	
	//即时交易
	CHARGE_SERVICE("create_direct_pay_by_user",SignType.MD5.toString(),AlipayConfig.key,""),
	//B2C 单笔转账到银行卡
	B2C_TO_BANK_SERVICE("alipay.fund.trans.tobank",SignType.MD5.toString(),AlipayConfig.key,""),
	//批量付款转账到银行卡
	BATCH_TO_BANK_SERVICE("bptb_pay_file",SignType.MD5.toString(),AlipayConfig.key,""),
	//B2C 企业支付宝向个人支付宝打款
	B2C_TO_ACC_SERVICE("alipay.fund.trans.toacc",SignType.MD5.toString(),AlipayConfig.key,""),
	// 批量 企业支付宝 向个人支付宝打款
	BATCH_TO_ACC_SERVICE("batch_trans_notify",SignType.MD5.toString(),AlipayConfig.key,""),
	// 单笔交易查询接口
	SINGLE_TRADE_QUERY_SERVICE("single_trade_query",SignType.MD5.toString(),AlipayConfig.key,""),
	//B2C订单查询接口
	B2C_ALIPAY_TRANS_QUERY("alipay.fund.trans.query",SignType.MD5.toString(),AlipayConfig.key,"")
	;
	
	
	
	private String service;
	
	private String sign_type;
	
	private String privatekey;
	
	private String publickey;
	
	private AlipayServiceEnum(String service,String sign_type,String privatekey,String publickey){
		this.service=service;
		this.sign_type = sign_type;
		this.privatekey = privatekey;
		this.publickey = publickey;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getPrivatekey() {
		return privatekey;
	}

	public void setPrivatekey(String privatekey) {
		this.privatekey = privatekey;
	}

	public String getPublickey() {
		return publickey;
	}

	public void setPublickey(String publickey) {
		this.publickey = publickey;
	}
	
	
}
*/