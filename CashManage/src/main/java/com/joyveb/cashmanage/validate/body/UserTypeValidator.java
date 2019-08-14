/*package com.joyveb.cashmanage.validate.body;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.request.ReqLoginEntity;
import com.joyveb.cashmanage.common.Constants;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.validate.AbstractFieldValidator;
*//**
 * 
 * 用户类型
 * 
 *//*
@Slf4j
@Component("userTypeValidator")
public class UserTypeValidator extends AbstractFieldValidator<ReqLoginEntity> {

	public UserTypeValidator() {
		super(ResponseResult.PARAM_ERROR);
	}

	@Override
	public boolean isValid(ReqLoginEntity body) {
		
		if(StringUtils.isBlank(body.getUsertype())){
			log.warn("usertype validator fail,usertype is null");
			return false;
		}
		
		if(!(StringUtils.equals(Constants.USER_TYPE_ALIPAY, body.getUsertype()) || StringUtils.equals(Constants.USER_TYPE_WX, body.getUsertype())||  StringUtils.equals(Constants.BANKFLAG, body.getUsertype()))) {
			log.warn("usertype validator fail,usertype is not correct");
			return false;
		}
		return true;
	}

}
*/