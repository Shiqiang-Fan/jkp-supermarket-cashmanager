package com.joyveb.lbos.restful.spring;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**  
 * Copyright © 2014畅享互联.
 *
 * @Title: FieldsMapperBean.java
 * @Prject: LBOS
 * @Package: com.joyveb.lbos.restful.spring
 * @date: 2014年8月21日 下午5:02:30
 * @author: yangqiju 
 */
public @Data class FieldsMapperBean {

	List<SearchField> fields = new ArrayList<SearchField>();
	
	public @Data static class SearchField{
		private String fieldName;
		private int show;//1:true 0:false
	}
}
