package com.joyveb.lbos.restful.spring;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**  
 * Copyright © 2014畅享互联.
 *
 * @Title: RequestJsonParam.java
 * @Prject: RestfulTest
 * @Package: com.joyveb.restful.ctrl
 * @date: 2014年8月15日 上午10:00:03
 * @author: yangqiju 
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestJsonParam {

	/**
	 * 用于绑定的请求参数名字
	 */
	String value() default "";
	
	/**
	 * 是否必须，默认是
	 */
	boolean required() default true;

}
