package com.joyveb.cashmanage.alipay;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class B2CTransQuery extends AbstractAlipay {

	@Override
	public Map<String, String> buildParams(TransactionRecord transaction) {
		
		Map<String,String> sParaTemp = new HashMap<String, String>();
		
		/*sParaTemp.put("service", AlipayServiceEnum.B2C_ALIPAY_TRANS_QUERY.getService());
		
		sParaTemp.put("partner", AlipayConfig.partner);*/
		
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		
		sParaTemp.put("out_biz_no", transaction.getOutBizNo());
		
		
		
		
		return sParaTemp;
	}

}
