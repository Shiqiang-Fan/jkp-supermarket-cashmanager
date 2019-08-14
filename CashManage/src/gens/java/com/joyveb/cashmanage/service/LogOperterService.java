package com.joyveb.cashmanage.service;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.joyveb.lbos.restful.util.KeyExplainHandler;
import com.joyveb.cashmanage.entity.LogOperter;
import com.joyveb.cashmanage.entity.LogOperterExample;
import com.joyveb.cashmanage.entity.LogOperterExample.Criteria;
import com.joyveb.cashmanage.entity.LogOperterKey;
import com.joyveb.cashmanage.mapper.LogOperterMapper;
import com.joyveb.lbos.restful.common.CommonSqlMapper;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.util.SqlMaker;
@Slf4j
@Service
public class LogOperterService implements ServiceInterface<LogOperter, LogOperterExample, LogOperterKey>{

	@Resource
	private LogOperterMapper mapper;
	private @Resource CommonSqlMapper common;
	
	
	@Override
	public int countByExample(LogOperterExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public int deleteByExample(LogOperterExample example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(LogOperterKey key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(LogOperter record)  {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(LogOperter record)  {
		return mapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int batchInsert(List<LogOperter> records)
			 {
		for(LogOperter record : records){
			mapper.insert(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchUpdate(List<LogOperter> records)
			 {
		for(LogOperter record : records){
			mapper.updateByPrimaryKeySelective(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchDelete(List<LogOperter> records)
			 {
		for(LogOperter record : records){
			mapper.deleteByPrimaryKey(record);
		}
		return records.size();
	}

	@Override
	public List<LogOperter> selectByExample(LogOperterExample example)
			 {
		return mapper.selectByExample(example);
	}

	@Override
	public LogOperter selectByPrimaryKey(LogOperterKey key)
			 {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public List<LogOperter> findAll(List<LogOperter> records) {
		if(records==null||records.size()<=0){
			return mapper.selectByExample(new LogOperterExample());
		}
		List<LogOperter> list = new ArrayList<LogOperter>();
		for(LogOperter record : records){
			LogOperter result = mapper.selectByPrimaryKey(record);
			if(result!=null){
				list.add(result);
			}
		}
		return list;
	}

	@Override
	public int updateByExampleSelective(LogOperter record, LogOperterExample example)  {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(LogOperter record, LogOperterExample example) {
		return mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(LogOperter record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(LogOperter record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int sumByExample(LogOperterExample example) {
		return 0;
	}

	@Override
	public void deleteAll()  {
		mapper.deleteByExample(new LogOperterExample());
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
	public LogOperterExample getExample(LogOperter record) {
		LogOperterExample example = new LogOperterExample();
		if(record!=null){
			Criteria criteria = example.createCriteria();
							if(record.getUuid()!=null){
				criteria.andUuidEqualTo(record.getUuid());
				}
				if(record.getUserName()!=null){
				criteria.andUserNameEqualTo(record.getUserName());
				}
				if(record.getAction()!=null){
				criteria.andActionEqualTo(record.getAction());
				}
				if(record.getObject()!=null){
				criteria.andObjectEqualTo(record.getObject());
				}
				if(record.getContentInfo()!=null){
				criteria.andContentInfoEqualTo(record.getContentInfo());
				}
				if(record.getOperDate()!=null){
				criteria.andOperDateEqualTo(record.getOperDate());
				}
				if(record.getStatus()!=null){
				criteria.andStatusEqualTo(record.getStatus());
				}

		}
		return example;
	}
}
