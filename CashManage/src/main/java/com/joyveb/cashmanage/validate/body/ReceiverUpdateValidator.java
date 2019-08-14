package com.joyveb.cashmanage.validate.body;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.request.ReqRecevierUuidEntity;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.validate.AbstractFieldValidator;
/**
 * 
 * 终端下单校验
 * 
 */
@Slf4j
@Component("receiverUpdateValidator")
public class ReceiverUpdateValidator extends AbstractFieldValidator<ReqRecevierUuidEntity> {

	public ReceiverUpdateValidator() {
		super(ResponseResult.PARAM_ERROR);
	}

	@Override
	public boolean isValid(ReqRecevierUuidEntity msg) {
		if(StringUtils.isBlank(msg.getUuid())){
			log.warn("order validator fail,uuid is null");
			return false;
		}
		if(StringUtils.isBlank(msg.getAddress())){
			log.warn("order validator fail,address is null");
			return false;
		}
		if(StringUtils.isBlank(msg.getReceiver())){
			log.warn("order validator fail,receiver is null");
			return false;
		}
		if(StringUtils.isBlank(msg.getPhone().toString())||msg.getPhone().toString().length()!=11){
			log.warn("order validator fail,phone is null or not 11 length");
			return false;
		}
		if(StringUtils.isBlank(msg.getIsdefault())){
			log.warn("order validator fail,isdefault is null or not 1 or 0");
			return false;
		}
		return true;
	}

}
