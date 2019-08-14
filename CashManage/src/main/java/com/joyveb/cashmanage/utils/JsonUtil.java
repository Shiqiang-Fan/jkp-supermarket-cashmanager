package com.joyveb.cashmanage.utils;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.util.TokenBuffer;

import com.alibaba.fastjson.JSON;

public class JsonUtil {
	static ObjectMapper mapper = new ObjectMapper();
	static 
	{
		mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
	}

	public static <T> T json2Bean(JsonNode node, Class<T> clazz) {
		try {
			return mapper.readValue(node, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static String beanToJson(Object bean) {
		try {
			if(bean==null)
			{
				return null;
			}
			TokenBuffer buffer = new TokenBuffer(mapper);
			mapper.writeValue(buffer, bean);
			JsonNode node = mapper.readTree(buffer.asParser());
			mapper.writeValueAsString(node);
			return mapper.writeValueAsString(node);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static <T> T json2Bean(String jsonTxt, Class<T> clazz) {
		try {
			return mapper.readValue(jsonTxt, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static JsonNode bean2Json(Object bean) {
		try {
			if(bean==null)
			{
				return null;
			}
			TokenBuffer buffer = new TokenBuffer(mapper);
			mapper.writeValue(buffer, bean);
			JsonNode node = mapper.readTree(buffer.asParser());
			return node;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static ArrayNode newArrayNode() {
		return mapper.createArrayNode();
	}

	public static ArrayNode toArrayNode(String jsontxt) {
		try {
			return mapper.readValue(jsontxt, ArrayNode.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
//	public static String bean2JsonAsString(Object bean) {
//		try {
//			if(bean==null)
//			{
//				return null;
//			}
//			//prettywriter
//			//return mapper.defaultPrettyPrintingWriter().writeValueAsString(bean);
//			
//			//defaultwriter
//			return mapper.writeValueAsString(bean);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//	}
	/**
	 * fastjson
	 * @param bean
	 * @return
	 */
	public static String bean2JsonAsString(Object bean) {
		try {

			return JSON.toJSONString(bean); 
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
