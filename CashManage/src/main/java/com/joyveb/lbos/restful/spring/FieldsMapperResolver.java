package com.joyveb.lbos.restful.spring;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import com.joyveb.lbos.restful.spring.FieldsMapperBean.SearchField;
import com.joyveb.lbos.restful.util.JsonUtil;

/**
 * Copyright © 2014畅享互联.
 * 
 * @Title: FieldsMapperResolver.java
 * @Prject: LBOS
 * @Package: com.joyveb.lbos.restful.spring
 * @date: 2014年8月21日 下午5:07:08
 * @author: yangqiju
 */
public class FieldsMapperResolver {
	
	public static FieldsMapperBean genQueryMapper(String json) {
		FieldsMapperBean fmb = new FieldsMapperBean();
		ObjectNode node = JsonUtil.toObjectNode(json);
		Iterator<Map.Entry<String, JsonNode>> iter = node.getFields();
		while (iter.hasNext()) {
			Entry<String, JsonNode> entry = iter.next();
			String key = entry.getKey();
			JsonNode value = entry.getValue();
			SearchField sf = new SearchField();
			sf.setFieldName(key);
			sf.setShow(value.asInt());
			fmb.getFields().add(sf);
		}
		return fmb;
	}
}
