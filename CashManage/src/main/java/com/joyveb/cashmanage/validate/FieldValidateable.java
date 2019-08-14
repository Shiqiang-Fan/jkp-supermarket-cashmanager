package com.joyveb.cashmanage.validate;

import com.joyveb.cashmanage.common.ResponseResult;

public interface FieldValidateable<T> {
	public ResponseResult check(T t);
}
