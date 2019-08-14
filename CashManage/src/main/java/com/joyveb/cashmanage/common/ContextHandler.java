package com.joyveb.cashmanage.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ContextHandler implements ApplicationContextAware {
	private static ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ContextHandler.ctx = applicationContext;
	}
	
	public static <T> T  getBean(String beanName,Class<T> requiredType){
		return ctx.getBean(beanName, requiredType);
	}
	
	public static Object  getBean(String beanName){
		return ctx.getBean(beanName);
	}

}
