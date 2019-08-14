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
@Component("payValidator")
public class PayValidator extends AbstractFieldValidator<ReqOrderEntity> {

	public PayValidator() {
		super(ResponseResult.PARAM_ERROR);
	}

	@Override
	public boolean isValid(ReqOrderEntity msg) {
		if(StringUtils.isBlank(msg.getReceiverid())){
			log.warn("order validator fail,receiveid is null");
			return false;
		}
		if(null==msg.getTotalamount()){
			log.warn("order validator fail,totalamount is null");
			return false;
		}
		if(null==msg.getTotalnum()){
			log.warn("order validator fail,totalnum is null");
			return false;
		}
		if(null==msg.getGames()){
			log.warn("order validator fail,games is null or not 11 length");
			return false;
		}
		return true;
	}

}
