package com.joyveb.lbos.restful.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;

import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.common.PageInfo;
import com.joyveb.lbos.restful.spring.FieldsMapperBean;
import com.joyveb.lbos.restful.spring.FieldsMapperBean.SearchField;
import com.joyveb.lbos.restful.spring.QueryMapperBean;
import com.joyveb.lbos.restful.spring.QueryMapperBean.ConditionBean;
import com.joyveb.lbos.restful.spring.QueryMapperBean.EqualBean;
import com.joyveb.lbos.restful.spring.QueryMapperBean.LikeBean;
import com.joyveb.lbos.restful.spring.QueryMapperBean.OrBean;

/**
 * Copyright © 2014畅享互联.
 * 
 * @Title: SqlMaker.java
 * @Prject: RestfulTest
 * @Package: com.joyveb.restful.component
 * @date: 2014年8月15日 上午10:35:08
 * @author: yangqiju
 */
@Slf4j
public class SqlMaker {
	
	public final static String TABLE_SUFFIX_KEY="SUFFIX";
	
	public final static String TABLE_NAME = "TABLE_NAME";
	protected final static String[] INJECTION_ID= {"'","and","exec","insert","select","delete","update","count","*","%","chr","mid","master","truncate","char","declare","; ","or","+",","};//,"-"
	public final static String SELECTTYPE = "select";
	public final static String COUNTTYPE = "count";
	//public final static SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
	public static <T> String getCountSql(DbCondi dc)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Map<String,Field> fieldMap = getFieldMap(dc.getEntityClass());
		return selectInfoSql(" COUNT(*) COUNT ", dc,fieldMap,COUNTTYPE);
	}
	
	public static String getData(DbCondi dc) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = dc.getEntityClass();
		FieldsMapperBean fmb = dc.getFmb();
		Map<String,Field> fieldMap = getFieldMap(clazz);
		return selectInfoSql(getDbFieldNames(clazz,fmb,fieldMap), dc,fieldMap,SELECTTYPE);
	}
	
	public static Map<String,Field> getFieldMap(Class<?> clazz){
		Map<String,Field> fieldMap = new HashMap<String, Field>();
		for (Field field : FieldUtils.allDeclaredField(clazz)) {
			fieldMap.put(field.getName(), field);
		}
		return fieldMap;
	}
	
	public static String getDbFieldNames(Class<?> clazz,FieldsMapperBean fmb,Map<String,Field> fieldMap){
		StringBuilder fields = new StringBuilder();
		if(fmb!=null&&fmb.getFields().size()>0){
			for(SearchField sf : fmb.getFields()){
				if(sf.getShow()==1){
					//添加验证 是否包含字段
					if(checkQueryField(sf.getFieldName(), fieldMap)){
						fields.append(FieldUtils.field2SqlColomn(sf.getFieldName())).append(" ").append(sf.getFieldName()).append(",");
					}else{
						log.debug("The query fields[{}] are not among Class [{}]..",sf.getFieldName(),clazz.getSimpleName());
					}
				}
			}
		}else{
			for(Entry<String,Field> entry : fieldMap.entrySet()){
				fields.append(FieldUtils.field2SqlColomn(entry.getKey())).append(" ").append(entry.getKey()).append(",");
			}
		}
		return fields.substring(0, fields.lastIndexOf(","));
	}
	
	private static boolean checkQueryField(String fieldName,Map<String,Field> fieldMap){
		if(fieldMap.get(fieldName)==null){
			return false;
		}
		return true;
	}
	
	private static String getTableName(Class<?> clazz,DbCondi dc){
		return dc.getTalbeName();
	}

	public static String selectInfoSql(String selectInfo ,DbCondi dc,Map<String,Field> fieldMap,String queryType) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class<?> clazz = dc.getEntityClass();
		QueryMapperBean queryCondi = dc.getQmb();
		PageInfo para = dc.getPageinfo();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ").append(selectInfo).append(" FROM ");
		sql.append(getTableName(clazz, dc));
		if(queryCondi==null){
			addPageLimit(para, sql,queryType);
			return sql.toString();
		}
		List<EqualBean> equals =queryCondi.getEquals();
		List<ConditionBean> conditions = queryCondi.getConditions();
		List<LikeBean> likes = queryCondi.getLikes();
		List<OrBean> ors = queryCondi.getOrs();
		if(equals.size()==0&&conditions.size()==0&&likes.size()==0&&ors.size()==0){
		//	throw new RuntimeException("error!!! can not query all data.");
			addPageLimit(para, sql,queryType);
			return sql.toString();
		}
		Map<String,String> fieldsMap = new HashMap<String, String>();
		for (Field field : FieldUtils.allDeclaredField(clazz)) {
			fieldsMap.put(field.getName(), FieldUtils.field2SqlColomn(field.getName()));
		}
		List<String> ands = new ArrayList<String>(); 
		List<Object> values = new ArrayList<Object>();
		for(EqualBean eb : equals){
			if(fieldsMap.get(eb.getFieldName())!=null){
				ands.add(String.format(" %s = '%s' ", fieldsMap.get(eb.getFieldName()),eb.getValue()));
				values.add(eb.getValue());
			}
		}
		for(ConditionBean cb : conditions){
			if(fieldsMap.get(cb.getFieldName())!=null){
				if(cb.getFieldName().equals("operDate")){
					ands.add(String.format(" %s %s '%s' ", fieldsMap.get(cb.getFieldName()),cb.getCondi(),cb.getValue()));
				}else{
					ands.add(String.format(" %s %s '%s' ", fieldsMap.get(cb.getFieldName()),cb.getCondi(),Long.parseLong(cb.getValue().toString())));//ymd.format(new Date(Long.parseLong(cb.getValue().toString())
				}
				values.add(cb.getValue());
			}
		}
		for(LikeBean lb : likes){
			if(fieldsMap.get(lb.getFieldName())!=null){
				if(fieldsMap.get(lb.getFieldName()).equals("REQUESTTIME")){
					ands.add(String.format(" %s like '%%%s%%' ", "date_format(REQUESTTIME, '%Y-%m-%d')",lb.getRegex()));
				}else if(fieldsMap.get(lb.getFieldName()).equals("TRANSFERTIME")){
					ands.add(String.format(" %s like '%%%s%%' ", "date_format(TRANSFERTIME, '%Y-%m-%d')",lb.getRegex()));
				}else{
					ands.add(String.format(" %s like '%%%s%%' ", fieldsMap.get(lb.getFieldName()),lb.getRegex()));
				}
				values.add(lb.getRegex());
			}
		}
		
		for(Object value : values){
			if(hasInjection(value.toString())){
				throw new RuntimeException("error!!! has sql indection.");
			}
		}
		StringBuilder andStr = new StringBuilder();
		for(int i=0 ;i<ands.size();i++){
			andStr.append(ands.get(i));
			if(i<(ands.size()-1)){
				andStr.append(" AND ");
			}
		}

		StringBuilder orStr = new StringBuilder();
		Map<String,List<OrBean>> orMap = new HashMap<String, List<OrBean>>();
		for(int i=0 ;i<ors.size();i++){
			OrBean ob = ors.get(i);
			if(orMap.containsKey(ob.getFieldName())){
				orMap.get(ob.getFieldName()).add(ob);
			}else{
				List<OrBean> tmp = new ArrayList<OrBean>();
				tmp.add(ob);
				orMap.put(ob.getFieldName(), tmp);
			}
		}
		int time = 0;
		for(Entry<String,List<OrBean>> entry : orMap.entrySet()){
			orStr.append(" (");
			List<OrBean> orbs = entry.getValue();
			for(int i = 0;i<orbs.size();i++){
				orStr.append(String.format(" %s = '%s' ", orbs.get(i).getFieldName(),orbs.get(i).getValue()));
				if(i<(orbs.size()-1)){
					orStr.append(" OR ");
				}
			}
			orStr.append(") ");
			time++;
			if(time!=orMap.size()){
				orStr.append(" AND ");
			}
		}
		
		fieldsMap.clear();
		boolean frist = true;
		if(StringUtils.isNotBlank(andStr)){
			if(frist){
				sql.append(" WHERE ");
				frist = false;
			}else{
				sql.append(" AND ");
			}
			sql.append(andStr);
		}
		if(StringUtils.isNotBlank(orStr)){
			if(frist){
				sql.append(" WHERE ");
				frist = false;
			}else{
				sql.append(" AND ");
			}
			sql.append(orStr);
		}
		addPageLimit(para, sql,fieldMap,queryType);
		fieldMap.clear();
		fieldMap = null;
		return sql.toString();
	}
	
	public static boolean hasInjection(String str){
		for (int i=0 ; i < INJECTION_ID.length ; i++ ){
			if (str.toLowerCase().indexOf(INJECTION_ID[i])>=0){
				return true;
				}  
		}
		return false;
	}
	
	public static void addPageLimit(PageInfo para,StringBuilder sql,Map<String,Field>  fieldMap,String type){
		if(para!=null){
			if(StringUtils.isNotBlank(para.getSort())){
				if('-'==StringUtils.trim(para.getSort()).charAt(0)){
					String fieldName = para.getSort().substring(1, para.getSort().length());
					if(checkQueryField(fieldName.toUpperCase(), fieldMap)){
						sql.append(" ORDER BY ");
						sql.append(fieldName);
						sql.append(" DESC ");
					}
				}else{
					if(checkQueryField(para.getSort().toUpperCase(), fieldMap)){
						sql.append(" ORDER BY ");
						sql.append(para.getSort()).append(" ASC ");
					}
				}
			}
			if(Integer.MAX_VALUE != para.getLimit()&&type.equals("select")){
				sql.append(" limit ").append(para.getSkip()).append(",").append(para.getLimit());
			}
		}
	}
	
	public static void addPageLimit(PageInfo para,StringBuilder sql,String type){
		if(para!=null){
			if(StringUtils.isNotBlank(para.getSort())){
				sql.append(" ORDER BY ");
				if('-'==StringUtils.trim(para.getSort()).charAt(0)){
					if(para.getSort().substring(1, para.getSort().length()).toLowerCase().equals("operdate")){
						sql.append("OPER_DATE");
					}else {
						sql.append(para.getSort().substring(1, para.getSort().length()).toLowerCase());
					}
					sql.append(" DESC ");
				}else{
					if(para.getSort().toUpperCase().equals("OPERDATE")){
						sql.append("OPER_DATE").append(" ASC ");
					}else{
						sql.append(para.getSort().toUpperCase()).append(" ASC ");
					}
				}
			}
			if(Integer.MAX_VALUE != para.getLimit()&&type.equals("select")){
				sql.append(" limit ").append(para.getSkip()).append(",").append(para.getLimit());
			}
		}
	}
	
}
