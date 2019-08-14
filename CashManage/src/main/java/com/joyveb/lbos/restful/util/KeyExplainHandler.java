package com.joyveb.lbos.restful.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java_cup.internal_error;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Copyright © 2014畅享互联.
 * 
 * @Title: KeyExplainHandler.java
 * @Prject: RestfulTest
 * @Package: com.joyveb.lbos.restful.util
 * @date: 2014年8月21日 下午1:03:27
 * @author: yangqiju
 */
public class KeyExplainHandler {

	private static ObjectMapper mapper = new ObjectMapper();
	public final static String ID_KEY = "_id";
	
	// field-value_field-value_
	@SuppressWarnings("rawtypes")
	public static String genKey(HashMap map, Class<?> clazz) {
		StringBuilder key = new StringBuilder();
		for (Field field : FieldUtils.allDeclaredField(clazz)) {
			key.append(field.getName()).append("-")
					.append(map.get(field.getName())).append("_");
		}
		return key.toString();
	}
	
	public static <T> String genPojoKey(T pojo,Class<?> keyClazz) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		StringBuilder key = new StringBuilder();
		for(Field field : FieldUtils.allDeclaredField(keyClazz)){
			key.append(field.getName()).append("-")
			.append(ObjectUtils.toString(FieldUtils.getObjectValue(pojo, field.getName()))).append("_");
		}
		return key.toString();
	}
	
	
	/**
	 * 
	 * @param key
	 * @param pojo     example::ParaDbproperties
	 * @param
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T explainKey(String key, T pojo) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Map<String, Object> fvMap = new HashMap<String, Object>();
		int firstIndex=key.indexOf("-");
		int lastIndex=key.lastIndexOf("_");
		String thekey=key.substring(0,firstIndex);
		String thevalue=key.substring(firstIndex+1, lastIndex);
		fvMap.put(thekey, thevalue);
	/*	String[] strs = StringUtils.split(key, "_");
		for (String s : strs) {
			String[] fvs = StringUtils.split(s, "-");
			fvMap.put(fvs[0], fvs[1]);
		}*/
		//get Key
//		for (Field field : FieldUtils.allDeclaredField(pojo.getClass())) {
////			if(fvMap.get(field.getName())!=null){
////				FieldUtils.setObjectValue(pojo, field,fvMap.get(field.getName()));
////			}
//			Object value = FieldUtils.getObjectValue(pojo, field.getName());
//			if(value!=null){
//				fvMap.put(field.getName(), value);
//			}
//		}
		T source = (T) converType(fvMap, pojo.getClass());
		for (Field field : FieldUtils.allDeclaredField(source.getClass())) {
			Object value = FieldUtils.getObjectValue(source, field.getName());
			if(value!=null){
				FieldUtils.setObjectValue(pojo, field, value);
			}
		}
		fvMap.clear();
		return pojo;
	}
	
	private static <T> T converType(Object source,Class<T> clazz){
		return mapper.convertValue(source, clazz);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void addId(List<HashMap<String,Object>> list,Class<?> keyClazz,Class<?> pojoClazz){
		for(HashMap map : list){
			for(Field filed:FieldUtils.allDeclaredField(pojoClazz)){
				if(map.get(filed.getName())==null){
					map.put(filed.getName(), "");
				}
			}
			map.put(KeyExplainHandler.ID_KEY, KeyExplainHandler.genKey(map, keyClazz));
			map.put("detailsfield", "1");
			map.put("detailsfieldtwo", "2");
			map.put("detailsfieldthree", "3");
		}
	}
}
