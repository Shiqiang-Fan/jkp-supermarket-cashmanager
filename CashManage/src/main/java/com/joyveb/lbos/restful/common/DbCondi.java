package com.joyveb.lbos.restful.common;

import java.util.Map;

import lombok.Data;

import com.joyveb.lbos.restful.spring.FieldsMapperBean;
import com.joyveb.lbos.restful.spring.QueryMapperBean;

/**  
 * Copyright © 2014畅享互联.
 *
 * @Title: DbCondi.java
 * @Prject: LBOS
 * @Package: com.joyveb.lbos.restful.common
 * @date: 2014年8月21日 下午5:26:02
 * @author: yangqiju 
 */
public @Data class DbCondi {

	private Class<?> keyClass;
	private Class<?> entityClass;
	private String talbeName;
	private FieldsMapperBean fmb;
	private PageInfo pageinfo;
	private QueryMapperBean qmb;
	private Map<String,Object> other;
}
