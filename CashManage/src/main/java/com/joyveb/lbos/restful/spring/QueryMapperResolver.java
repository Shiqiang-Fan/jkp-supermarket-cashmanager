package com.joyveb.lbos.restful.spring;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import com.joyveb.lbos.restful.spring.QueryMapperBean.ConditionBean;
import com.joyveb.lbos.restful.spring.QueryMapperBean.EqualBean;
import com.joyveb.lbos.restful.spring.QueryMapperBean.LikeBean;
import com.joyveb.lbos.restful.spring.QueryMapperBean.OrBean;
import com.joyveb.lbos.restful.util.JsonUtil;

/**
 * Copyright © 2014畅享互联.
 * 
 * @Title: QueryMapperResolver.java
 * @Prject: LBOS
 * @Package: com.joyveb.lbos.restful.spring
 * @date: 2014年8月20日 下午5:35:09
 * @author: yangqiju
 * @Description: TODO regex
 */
public class QueryMapperResolver {
	
	public static QueryMapperBean genQueryMapper(String json) {
		QueryMapperBean qmb = new QueryMapperBean();
		ObjectNode node = JsonUtil.toObjectNode(json);
		Iterator<Map.Entry<String, JsonNode>> iter = node.getFields();
		while (iter.hasNext()) {
			Entry<String, JsonNode> entry = iter.next();
			String key = entry.getKey();
			JsonNode value = entry.getValue();
			if(isEqualType(entry.getKey(), entry.getValue())){
				equalHandler(key, value, qmb);
			}else if(isConditionType(entry.getKey(), entry.getValue())){
				conditionHandler(key, value, qmb);
			}else if(isOrType(entry.getKey(), entry.getValue())){
				orHandler(key, value, qmb);
			}else if(isLikeAndType(entry.getKey(), entry.getValue())){
				likeHandler(key, value, qmb);
			}else{
				throw new RuntimeException("query mapper json error..");
			}
		}
		return qmb;
	}
	

	private static void equalHandler(String key, JsonNode value, QueryMapperBean qmb) {
		qmb.getEquals().add(new EqualBean(key, StringUtils.trim(value.asText())));
	}

	private static void conditionHandler(String key, JsonNode value, QueryMapperBean qmb) {
		Iterator<Map.Entry<String, JsonNode>> iter = value.getFields();
		while (iter.hasNext()) {
			Entry<String, JsonNode> entry = iter.next();
			qmb.getConditions().add(new ConditionBean(key, entry.getKey(), (StringUtils.trim(entry.getValue().asText()))));
		}
	}

	private static void orHandler(String key, JsonNode value, QueryMapperBean qmb) {
		if(value.isArray()){
			for(int i=0 ;i<value.size();i++){
				Iterator<Map.Entry<String, JsonNode>> iter = value.get(i).getFields();
				while (iter.hasNext()) {
					Entry<String, JsonNode> entry = iter.next();
					qmb.getOrs().add(new OrBean(entry.getKey(),StringUtils.trim(entry.getValue().asText())));
				}
			}
		}else{
			Iterator<Map.Entry<String, JsonNode>> iter = value.getFields();
			while (iter.hasNext()) {
				Entry<String, JsonNode> entry = iter.next();
				qmb.getOrs().add(new OrBean(entry.getKey(), StringUtils.trim(entry.getValue().asText())));
			}
		}
	}

	private static void likeHandler(String key, JsonNode value, QueryMapperBean qmb) {
		qmb.getLikes().add(new LikeBean(key, StringUtils.trim(value.get("$regex").asText()), StringUtils.trim(value.get("$options").asText())));
	}

	private static boolean isEqualType(String key, JsonNode value) {
		// equal {"keyy":"testcode"}
		return StringUtils.containsNone(value.toString(), '{', '}');
	}

	private static boolean isConditionType(String key, JsonNode value) {
		// condition {"keyy":{"<":"10",">":"1"}}
		return StringUtils.containsAny(value.toString(), '>', '=', '<');
	}

	private static boolean isOrType(String key, JsonNode value) {
		// or {"$or":[{"author":"Daniel"},{"author":"Jessica"}]}
		return StringUtils.containsIgnoreCase(key, "$or");
	}

	private static boolean isLikeAndType(String key, JsonNode value) {
		// like {"keyy":{"$regex":"testcode","$options":"i"},"value":"a"}
		return StringUtils.containsIgnoreCase(value.toString(), "$regex");
	}

}
