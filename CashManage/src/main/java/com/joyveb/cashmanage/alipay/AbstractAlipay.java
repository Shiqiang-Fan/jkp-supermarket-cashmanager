package com.joyveb.cashmanage.alipay;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public abstract class AbstractAlipay {
	
	
	public  abstract Map<String,String> buildParams(TransactionRecord transaction);
	
	public  String  action(TransactionRecord transaction){//,AlipayServiceEnum alipayService
		
		try {
			/*if(StringUtils.isNotBlank(transaction.getFileName()) && StringUtils.isNotBlank(transaction.getFilePath())){
				return AlipaySubmit.buildRequest("bptb_pay_file", transaction.getFilePath(), this.buildParams(transaction));
			}
			if(alipayService == AlipayServiceEnum.CHARGE_SERVICE){
				Map<String, String> params = this.buildParams(transaction);
				String string = AlipaySubmit.buildRequest(params, "POST", "确认");
				return string;
			}*/
			return AlipaySubmit.buildRequest("","",this.buildParams(transaction));
		} catch (Exception e) { 
			log.debug("第三方操作失败，失败原因{}",e);
		}
		return "";
	}
}
