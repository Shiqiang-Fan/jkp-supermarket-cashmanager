package com.joyveb.cashmanage.validate.body;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.request.ReqLoginEntity;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.validate.AbstractFieldValidator;
/**
 * 
 * 授权码
 * 
 */
@Slf4j
@Component("authCodeValidator")
public class AuthCodeValidator extends AbstractFieldValidator<ReqLoginEntity> {

	public AuthCodeValidator() {
		super(ResponseResult.PARAM_ERROR);
	}

	@Override
	public boolean isValid(ReqLoginEntity body) {
		
		if(StringUtils.isBlank(body.getUsername())){
			log.warn("username validator fail,username is null");
			return false;
		}
		if(StringUtils.isBlank(body.getPassword())){
			log.warn("password validator fail,password is null");
			return false;
		}
		return true;
	}

}
