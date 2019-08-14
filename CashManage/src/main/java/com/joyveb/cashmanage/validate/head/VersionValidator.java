package com.joyveb.cashmanage.validate.head;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.TransMessageEntity;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.validate.AbstractFieldValidator;
/**
 * 
 * 版本校验
 * 
 */
@Slf4j
@Component("versionValidator")
public class VersionValidator extends AbstractFieldValidator<TransMessageEntity> {

	public VersionValidator() {
		super(ResponseResult.PARAM_ERROR);
	}

	@Override
	public boolean isValid(TransMessageEntity msg) {
		if(msg.getHead() == null) {
			log.warn("version validator fail,messageHead is null");
			return false;
		}
		if (StringUtils.isNotBlank(msg.getHead().getVersion())
				&& StringUtils.startsWith(msg.getHead().getVersion(), "1")
				&& msg.getHead().getVersion().length() <= 8) {
			return true;
		}
		log.warn("version validator fail,version is null or version length too short or version not start with 1");
		return false;
	}

}
