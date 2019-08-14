package com.joyveb.cashmanage.validate.head;

import java.text.ParseException;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.TransMessageEntity;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.validate.AbstractFieldValidator;
/**
 * 
 * 时间戳校验
 * 
 */
@Slf4j
@Component("timestampValidator")
public class TimestampValidator extends AbstractFieldValidator<TransMessageEntity> {

	public TimestampValidator() {
		super(ResponseResult.PARAM_ERROR);
	}

	@Override
	public boolean isValid(TransMessageEntity msg) {
		if(msg.getHead() == null) {
			log.warn("timestamp validator fail,messageHead is null");
			return false;
		}
		if(msg.getHead().getTimestamp() == null) {
			log.warn("timestamp validator fail,timestamp is null");
			return false;
		}
		String[] parsePatterns = {"yyyyMMddHHmmss"};
		try {
			DateUtils.parseDate(msg.getHead().getTimestamp(), parsePatterns);
			return true;
		} catch (ParseException e) {
			log.warn("timestamp validator fail,timestamp["+msg.getHead().getTimestamp()+"]parseexception.");
			return false;
		}
	}
}
