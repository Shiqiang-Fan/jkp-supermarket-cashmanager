package com.joyveb.cashmanage.validate.body;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.request.ReqCashEntity;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.validate.AbstractFieldValidator;
/**
 * 
 * 加密的即开票保安区信息的验证
 * 
 */
@Component("barcodeValidator")
public class BarcodeValidator extends AbstractFieldValidator<ReqCashEntity> {

	public BarcodeValidator() {
		super(ResponseResult.PARAM_ERROR);
	}

	@Override
	public boolean isValid(ReqCashEntity body) {
		return StringUtils.isNotBlank(body.getBarcode());
	}

	//private @Resource(name="merchantCache") MerchantCache mc;
}
