package com.joyveb.cashmanage.validate.body;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.request.ReqOrderEntity;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.validate.AbstractFieldValidator;
/**
 * 
 * 终端下单校验
 * 
 */
@Slf4j
@Component("payOrderValidator")
public class PayOrderValidator extends AbstractFieldValidator<ReqOrderEntity> {

	public PayOrderValidator() {
		super(ResponseResult.PARAM_ERROR);
	}

	@Override
	public boolean isValid(ReqOrderEntity msg) {
		if(msg == null) {
			log.warn("order validator fail,reqOrder is null");
			return false;
		}
		if(null==msg.getTotalamount()&&!msg.getAgainpay().equals("1")){
			log.warn("order validator fail,totalamount is null");
			return false;
		}
		if(null==msg.getTotalnum()&&!msg.getAgainpay().equals("1")){
			log.warn("order validator fail,totalnum is null");
		}
		if(StringUtils.isBlank(msg.getReceiverid())&&!msg.getAgainpay().equals("1")){
			log.warn("order validator fail,Receiverid is null");
			return false;
		}
		if(null==msg.getGames()&&!msg.getAgainpay().equals("1")){
			log.warn("order validator fail,games is null");
			return false;
		}
		return true;
	}

}
