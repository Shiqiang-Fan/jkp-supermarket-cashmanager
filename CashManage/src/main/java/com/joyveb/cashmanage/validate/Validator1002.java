package com.joyveb.cashmanage.validate;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.AbstractEntity;
import com.joyveb.cashmanage.bean.TransMessageEntity;
import com.joyveb.cashmanage.common.ResponseResult;

/**
 * 
 * 1002校验
 * 
 */
@Component("validator_1002")
public class Validator1002 extends AbstractValidator {

	@Override
	public ResponseResult extraValidate(TransMessageEntity msg) {
		return ResponseResult.SUCCESS;
	}

	@Override
	@Resource(name="body1002Validators")
	public void setBodyValidators(List<FieldValidateable<AbstractEntity>> bodyValidators) {
		this.bodyValidators = bodyValidators;
	}

}
