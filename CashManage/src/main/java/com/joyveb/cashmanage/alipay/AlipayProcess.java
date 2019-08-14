package com.joyveb.cashmanage.alipay;

public enum AlipayProcess {
	
	
	TRANSACTIONSUCC(1,"交易成功","T","SUCCESS"),//请求成功受理成功
	
	TRANSACTIONFAIL(2,"交易失败","T","FAIL"),//请求成功但转账受理失败
	
	TRANSACTIONDEALIND(3,"正在处理","T","DEALING");//请求成功正在处理
	
	private int status;
	private String des;
	private String tradeStatus;
	private String resultCode;
	
	private AlipayProcess(int status,String des,String tradeStatus,String resultCode) {
		this.status = status;
		this.des = des;
		this.tradeStatus = tradeStatus;
		this.setResultCode(resultCode);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	
	public static AlipayProcess getStatusByTradeStatus(String tradeStatus,String resultCode){
		
		for(AlipayProcess alipayProcess : AlipayProcess.values()){
			if(alipayProcess.getTradeStatus().equals(tradeStatus) && alipayProcess.getResultCode().equals(resultCode)){
				return alipayProcess;
			}
		}
		return null;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

}
