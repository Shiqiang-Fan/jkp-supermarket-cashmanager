package com.joyveb.cashmanage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyveb.cashmanage.entity.DbProperties;
import com.joyveb.cashmanage.entity.DbPropertiesExample;
import com.joyveb.cashmanage.entity.DbPropertiesExample.Criteria;
import com.joyveb.cashmanage.entity.DbPropertiesKey;
import com.joyveb.cashmanage.mapper.DbPropertiesMapper;
import com.joyveb.lbos.restful.common.CommonSqlMapper;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.util.KeyExplainHandler;
import com.joyveb.lbos.restful.util.SqlMaker;
@Slf4j
@Service
public class DbPropertiesService implements ServiceInterface<DbProperties, DbPropertiesExample, DbPropertiesKey>{

	@Resource
	private DbPropertiesMapper mapper;
	private @Resource CommonSqlMapper common;
	
	
	@Override
	public int countByExample(DbPropertiesExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public int deleteByExample(DbPropertiesExample example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(DbPropertiesKey key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(DbProperties record)  {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(DbProperties record)  {
		return mapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int batchInsert(List<DbProperties> records)
			 {
		for(DbProperties record : records){
			mapper.insert(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchUpdate(List<DbProperties> records)
			 {
		for(DbProperties record : records){
			mapper.updateByPrimaryKeySelective(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchDelete(List<DbProperties> records)
			 {
		for(DbProperties record : records){
			mapper.deleteByPrimaryKey(record);
		}
		return records.size();
	}

	@Override
	public List<DbProperties> selectByExample(DbPropertiesExample example)
			 {
		return mapper.selectByExample(example);
	}

	@Override
	public DbProperties selectByPrimaryKey(DbPropertiesKey key)
			 {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public List<DbProperties> findAll(List<DbProperties> records) {
		if(records==null||records.size()<=0){
			return mapper.selectByExample(new DbPropertiesExample());
		}
		List<DbProperties> list = new ArrayList<DbProperties>();
		for(DbProperties record : records){
			DbProperties result = mapper.selectByPrimaryKey(record);
			if(result!=null){
				list.add(result);
			}
		}
		return list;
	}

	@Override
	public int updateByExampleSelective(DbProperties record, DbPropertiesExample example)  {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(DbProperties record, DbPropertiesExample example) {
		return mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(DbProperties record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(DbProperties record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int sumByExample(DbPropertiesExample example) {
		return 0;
	}

	@Override
	public void deleteAll()  {
		mapper.deleteByExample(new DbPropertiesExample());
	}
	
	public int getCount(DbCondi dc){
		List<HashMap<String, Object>> resultSet = null;
		try {
			resultSet = common.executeSql(SqlMaker.getCountSql(dc));
		} catch (Exception e) {
			log.warn("执行sql异常",e);
		}
		return ((Number) resultSet.get(0).get("COUNT")).intValue();
	}
	
	public List<HashMap<String,Object>> getData(DbCondi dc){
		List<HashMap<String,Object>> resultSet = null;
		try{
			String sql = SqlMaker.getData(dc); 
			resultSet = common.executeSql(sql);
			KeyExplainHandler.addId(resultSet, dc.getKeyClass(),dc.getEntityClass());//add key
		}catch (Exception e) {
			log.warn("执行sql异常",e);
		}
		return resultSet;
	}
	
	public List<HashMap<String,Object>> dosql(String sql){
		List<HashMap<String,Object>> resultSet = common.executeSql(sql);
		return resultSet;
	}
	@Override
	public DbPropertiesExample getExample(DbProperties record) {
		DbPropertiesExample example = new DbPropertiesExample();
		if(record!=null){
			Criteria criteria = example.createCriteria();
							if(record.getThekey()!=null){
				criteria.andThekeyEqualTo(record.getThekey());
				}
				if(record.getValue()!=null){
				criteria.andValueEqualTo(record.getValue());
				}
				if(record.getDes()!=null){
				criteria.andDesEqualTo(record.getDes());
				}

		}
		return example;
	}
}
