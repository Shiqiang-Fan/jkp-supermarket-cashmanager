package com.joyveb.cashmanage.validate.body;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.request.ReqUuidEntity;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.validate.AbstractFieldValidator;
/**
 * 
 * uuid校验
 * 
 */
@Slf4j
@Component("receiverUuidValidator")
public class ReceiverUuidValidator extends AbstractFieldValidator<ReqUuidEntity> {

	public ReceiverUuidValidator() {
		super(ResponseResult.PARAM_ERROR);
	}

	@Override
	public boolean isValid(ReqUuidEntity msg) {
		if(msg == null) {
			log.warn("order validator fail,reqOrder is null");
			return false;
		}
		if(StringUtils.isBlank(msg.getUuid())){
			log.warn("order validator fail,uuid is null");
			return false;
		}
		return true;
	}

}
