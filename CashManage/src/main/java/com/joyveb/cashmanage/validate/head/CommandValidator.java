package com.joyveb.cashmanage.validate.head;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.TransMessageEntity;
import com.joyveb.cashmanage.common.Constants;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.validate.AbstractFieldValidator;
/**
 * 
 * Command校验
 * 
 */
@Slf4j
@Component("commandValidator")
public class CommandValidator extends AbstractFieldValidator<TransMessageEntity> {

	public CommandValidator() {
		super(ResponseResult.PARAM_ERROR);
	}

	@Override
	public boolean isValid(TransMessageEntity msg) {
		
		if(StringUtils.isBlank(msg.getHead().getCommand())){
			log.warn("command validator fail,command is null");
			return false;
		}
		
		if(!Constants.commandList.contains(msg.getHead().getCommand())) {
			log.warn("command validator fail,command is not correct");
			return false;
		}
		return true;
	}

}
