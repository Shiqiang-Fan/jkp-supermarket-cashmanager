package com.joyveb.cashmanage.validate;

import java.util.List;

import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import com.joyveb.cashmanage.bean.AbstractEntity;
import com.joyveb.cashmanage.bean.TransMessageEntity;
import com.joyveb.cashmanage.common.ResponseResult;
/**
 * 
 * 校验
 * 
 */
public abstract class AbstractValidator implements Validateable {

	@Override
	public ResponseResult validate(Object obj) {
		TransMessageEntity msg=(TransMessageEntity)obj;
		ResponseResult result = ResponseResult.SUCCESS;
		if(StringUtils.isNotBlank(msg.getResult())) {
			result = ResponseResult.getById(msg.getResult());
		}
		if (result != ResponseResult.SUCCESS) {
			return result;
		}
		for (FieldValidateable<TransMessageEntity> v : headValidators) {
			result = v.check(msg);
			if (result != ResponseResult.SUCCESS) {
				return result;
			}
		}
		//log.info("head验证的请求耗时[" + (System.currentTimeMillis() - start) +"]ms");
		if (bodyValidators != null) {
			for (FieldValidateable<AbstractEntity> v : bodyValidators) {
				result = v.check(msg.getBody());
				if (result != ResponseResult.SUCCESS) {
					return result;
				}
			}
		}
		//log.info("body验证的请求耗时[" + (System.currentTimeMillis() - start) + "]ms");
		return this.extraValidate(msg);
	}

	public abstract ResponseResult extraValidate(TransMessageEntity msg);

	public abstract void setBodyValidators(List<FieldValidateable<AbstractEntity>> bodyValidators);

	protected List<FieldValidateable<AbstractEntity>> bodyValidators;
	private @Resource(name="headValidators") List<FieldValidateable<TransMessageEntity>> headValidators;
}
