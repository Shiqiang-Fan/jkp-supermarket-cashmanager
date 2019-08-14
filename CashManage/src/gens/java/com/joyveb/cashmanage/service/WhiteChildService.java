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
import com.joyveb.cashmanage.entity.WhiteChild;
import com.joyveb.cashmanage.entity.WhiteChildExample;
import com.joyveb.cashmanage.entity.WhiteChildExample.Criteria;
import com.joyveb.cashmanage.entity.WhiteChildKey;
import com.joyveb.cashmanage.mapper.WhiteChildMapper;
import com.joyveb.lbos.restful.common.CommonSqlMapper;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.util.SqlMaker;
@Slf4j
@Service
public class WhiteChildService implements ServiceInterface<WhiteChild, WhiteChildExample, WhiteChildKey>{

	@Resource
	private WhiteChildMapper mapper;
	private @Resource CommonSqlMapper common;
	
	
	@Override
	public int countByExample(WhiteChildExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public int deleteByExample(WhiteChildExample example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(WhiteChildKey key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(WhiteChild record)  {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(WhiteChild record)  {
		return mapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int batchInsert(List<WhiteChild> records)
			 {
		for(WhiteChild record : records){
			mapper.insert(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchUpdate(List<WhiteChild> records)
			 {
		for(WhiteChild record : records){
			mapper.updateByPrimaryKeySelective(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchDelete(List<WhiteChild> records)
			 {
		for(WhiteChild record : records){
			mapper.deleteByPrimaryKey(record);
		}
		return records.size();
	}

	@Override
	public List<WhiteChild> selectByExample(WhiteChildExample example)
			 {
		return mapper.selectByExample(example);
	}

	@Override
	public WhiteChild selectByPrimaryKey(WhiteChildKey key)
			 {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public List<WhiteChild> findAll(List<WhiteChild> records) {
		if(records==null||records.size()<=0){
			return mapper.selectByExample(new WhiteChildExample());
		}
		List<WhiteChild> list = new ArrayList<WhiteChild>();
		for(WhiteChild record : records){
			WhiteChild result = mapper.selectByPrimaryKey(record);
			if(result!=null){
				list.add(result);
			}
		}
		return list;
	}

	@Override
	public int updateByExampleSelective(WhiteChild record, WhiteChildExample example)  {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(WhiteChild record, WhiteChildExample example) {
		return mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(WhiteChild record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(WhiteChild record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int sumByExample(WhiteChildExample example) {
		return 0;
	}

	@Override
	public void deleteAll()  {
		mapper.deleteByExample(new WhiteChildExample());
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
	public WhiteChildExample getExample(WhiteChild record) {
		WhiteChildExample example = new WhiteChildExample();
		if(record!=null){
			Criteria criteria = example.createCriteria();
							if(record.getUuid()!=null){
				criteria.andUuidEqualTo(record.getUuid());
				}
				if(record.getUsername()!=null){
				criteria.andUsernameEqualTo(record.getUsername());
				}
				if(record.getPassword()!=null){
				criteria.andPasswordEqualTo(record.getPassword());
				}
				if(record.getParentid()!=null){
				criteria.andParentidEqualTo(record.getParentid());
				}

		}
		return example;
	}
}
