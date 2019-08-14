package com.joyveb.cashmanage.alipay;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class AlipaySingleQuery extends AbstractAlipay {

	@Override
	public Map<String, String> buildParams(TransactionRecord transaction) {
		
		Map<String,String> sParaTemp = new HashMap<String, String>();
		
		/*sParaTemp.put("service", AlipayServiceEnum.SINGLE_TRADE_QUERY_SERVICE.getService());
		
		sParaTemp.put("partner", AlipayConfig.partner);*/
		
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		
		sParaTemp.put("out_trade_no", transaction.getOutBizNo());
		
		return sParaTemp;
	}
	
}
