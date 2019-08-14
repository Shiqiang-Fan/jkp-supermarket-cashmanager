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
import com.joyveb.cashmanage.entity.Terminal;
import com.joyveb.cashmanage.entity.TerminalExample;
import com.joyveb.cashmanage.entity.TerminalExample.Criteria;
import com.joyveb.cashmanage.entity.TerminalKey;
import com.joyveb.cashmanage.mapper.TerminalMapper;
import com.joyveb.lbos.restful.common.CommonSqlMapper;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.util.SqlMaker;
@Slf4j
@Service
public class TerminalService implements ServiceInterface<Terminal, TerminalExample, TerminalKey>{

	@Resource
	private TerminalMapper mapper;
	private @Resource CommonSqlMapper common;
	
	
	@Override
	public int countByExample(TerminalExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public int deleteByExample(TerminalExample example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(TerminalKey key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(Terminal record)  {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(Terminal record)  {
		return mapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int batchInsert(List<Terminal> records)
			 {
		for(Terminal record : records){
			mapper.insert(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchUpdate(List<Terminal> records)
			 {
		for(Terminal record : records){
			mapper.updateByPrimaryKeySelective(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchDelete(List<Terminal> records)
			 {
		for(Terminal record : records){
			mapper.deleteByPrimaryKey(record);
		}
		return records.size();
	}

	@Override
	public List<Terminal> selectByExample(TerminalExample example)
			 {
		return mapper.selectByExample(example);
	}

	@Override
	public Terminal selectByPrimaryKey(TerminalKey key)
			 {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public List<Terminal> findAll(List<Terminal> records) {
		if(records==null||records.size()<=0){
			return mapper.selectByExample(new TerminalExample());
		}
		List<Terminal> list = new ArrayList<Terminal>();
		for(Terminal record : records){
			Terminal result = mapper.selectByPrimaryKey(record);
			if(result!=null){
				list.add(result);
			}
		}
		return list;
	}

	@Override
	public int updateByExampleSelective(Terminal record, TerminalExample example)  {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(Terminal record, TerminalExample example) {
		return mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(Terminal record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Terminal record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int sumByExample(TerminalExample example) {
		return 0;
	}

	@Override
	public void deleteAll()  {
		mapper.deleteByExample(new TerminalExample());
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
	public TerminalExample getExample(Terminal record) {
		TerminalExample example = new TerminalExample();
		if(record!=null){
			Criteria criteria = example.createCriteria();
							if(record.getSessionid()!=null){
				criteria.andSessionidEqualTo(record.getSessionid());
				}
				if(record.getIsused()!=null){
				criteria.andIsusedEqualTo(record.getIsused());
				}

		}
		return example;
	}
}
