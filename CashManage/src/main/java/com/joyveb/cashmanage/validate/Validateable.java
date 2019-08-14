package com.joyveb.cashmanage.validate;

import com.joyveb.cashmanage.common.ResponseResult;

public interface Validateable {
	public ResponseResult validate(Object msg);//Message
}
