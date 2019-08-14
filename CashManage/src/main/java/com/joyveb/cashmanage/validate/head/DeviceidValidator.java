package com.joyveb.cashmanage.validate.head;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.TransMessageEntity;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.validate.AbstractFieldValidator;
/**
 * 
 * deviceid校验
 * 
 */
@Slf4j
@Component("deviceidValidator")
public class DeviceidValidator extends AbstractFieldValidator<TransMessageEntity> {

	public DeviceidValidator() {
		super(ResponseResult.PARAM_ERROR);
	}

	@Override
	public boolean isValid(TransMessageEntity msg) {
		if(msg.getHead() == null || StringUtils.isBlank(msg.getHead().getDeviceid())) {
			log.warn("deviceid validator fail,deviceid is null");
			return false;
		}
		return true;
	}

}
