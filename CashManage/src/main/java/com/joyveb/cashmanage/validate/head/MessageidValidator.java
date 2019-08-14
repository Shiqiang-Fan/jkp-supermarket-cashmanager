package com.joyveb.cashmanage.validate.head;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.TransMessageEntity;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.validate.AbstractFieldValidator;
/**
 * 
 * messageid校验
 * 
 */
@Slf4j
@Component("messageidValidator")
public class MessageidValidator extends AbstractFieldValidator<TransMessageEntity> {

	public MessageidValidator() {
		super(ResponseResult.PARAM_ERROR);
	}

	@Override
	public boolean isValid(TransMessageEntity msg) {
		if(msg.getHead() == null) {
			log.warn("messageid validator fail,messageHead is null");
			return false;
		}
		String msgId = msg.getHead().getMessageid();
		if(msgId.length() < 15 || msgId.length() > 32) {
			log.warn("messageid validator fail,msgId["+msgId+"]length error.except length is[15-32]");
			return false;
		}
		
		return true;
	}

}
