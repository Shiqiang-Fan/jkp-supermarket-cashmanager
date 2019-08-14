package com.joyveb.cashmanage.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.alipay.service.AlipayService;
import com.joyveb.cashmanage.common.Constants;

@Component("payFactory")
public class PayFactory {
	@Resource(name = "alipayService")
	private AlipayService alipayService;

	// WechartService

	public Object getPayService(String payFlag) {
		if (payFlag.equals(Constants.ALIPAYFLAG)) {
			return alipayService;
		}
		return null;
	}
}
