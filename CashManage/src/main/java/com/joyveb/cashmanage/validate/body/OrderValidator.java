package com.joyveb.cashmanage.validate.body;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.request.ReqOrderRecevierEntity;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.validate.AbstractFieldValidator;
/**
 * 
 * 终端下单校验
 * 
 */
@Slf4j
@Component("orderValidator")
public class OrderValidator extends AbstractFieldValidator<ReqOrderRecevierEntity> {

	public OrderValidator() {
		super(ResponseResult.PARAM_ERROR);
	}

	@Override
	public boolean isValid(ReqOrderRecevierEntity msg) {
		if(msg == null) {
			log.warn("order validator fail,reqOrder is null");
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
		if(null==msg.getPhone()){
			log.warn("order validator fail,phone is null");
			return false;
		}else if(msg.getPhone().toString().length()!=11){
			log.warn("order validator fail,phone is not 11 length");
			return false;
		}
		/*if(null==msg.getGames()){
			log.warn("order validator fail,games is null");
			return false;
		}*/
		return true;
	}

}
