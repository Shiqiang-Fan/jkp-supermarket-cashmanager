package com.joyveb.cashmanage.validate.head;

import java.util.Calendar;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.request.ReqCashEntity;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.validate.AbstractFieldValidator;
/**
 * 
 * 兑奖时间校验
 * 
 */
@Slf4j
@Component("prizeTimeValidator")
public class PrizeTimeValidator extends AbstractFieldValidator<ReqCashEntity> {

	public PrizeTimeValidator() {
		super(ResponseResult.NOT_PRIZE_TIME);
	}

	@Override
	public boolean isValid(ReqCashEntity msg) {
		Calendar startCal = Calendar.getInstance();
		startCal.set(Calendar.HOUR_OF_DAY, 9);
		startCal.set(Calendar.MINUTE, 0);
		startCal.set(Calendar.SECOND, 0);
		Calendar endCal = Calendar.getInstance();
		endCal.set(Calendar.HOUR_OF_DAY, 23);
		endCal.set(Calendar.MINUTE, 58);
		endCal.set(Calendar.SECOND, 0);
		boolean prizetime = System.currentTimeMillis() >= startCal.getTimeInMillis() && System.currentTimeMillis() <= endCal.getTimeInMillis();
		if(!prizetime) {
			log.info("非兑奖时间");
			return false;
		}
		return true;
	}

}
