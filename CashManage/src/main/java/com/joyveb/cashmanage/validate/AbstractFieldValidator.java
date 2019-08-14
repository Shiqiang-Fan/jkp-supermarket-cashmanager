package com.joyveb.cashmanage.validate;

import com.joyveb.cashmanage.common.ResponseResult;

public abstract class AbstractFieldValidator<T> implements FieldValidateable<T> {

	public AbstractFieldValidator(ResponseResult failResult) {
		this.failResult = failResult;
	}

	@Override
	public ResponseResult check(T t) {
		if(t == null) {
			return this.failResult;
		}
		if(isValid(t)) {
			return ResponseResult.SUCCESS;
		}
		return failResult;
	}

	public abstract boolean isValid(T t);

	private final ResponseResult failResult;

}
