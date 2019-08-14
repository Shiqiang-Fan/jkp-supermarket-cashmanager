package com.joyveb.cashmanage.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import javax.annotation.Resource;

import com.joyveb.lbos.restful.util.KeyExplainHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyveb.cashmanage.entity.LogCommission;
import com.joyveb.cashmanage.entity.LogCommissionExample;
import com.joyveb.cashmanage.entity.LogCommissionExample.Criteria;
import com.joyveb.cashmanage.entity.LogCommissionKey;

import com.joyveb.cashmanage.mapper.LogCommissionMapper;
import com.joyveb.lbos.restful.common.CommonSqlMapper;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.util.SqlMaker;
@Slf4j
@Service
public class LogCommissionService implements ServiceInterface<LogCommission, LogCommissionExample, LogCommissionKey>{

	@Resource
	private LogCommissionMapper mapper;
	private @Resource CommonSqlMapper common;
	
	
	@Override
	public int countByExample(LogCommissionExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public int deleteByExample(LogCommissionExample example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(LogCommissionKey key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(LogCommission record)  {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(LogCommission record)  {
		return mapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int batchInsert(List<LogCommission> records)
			 {
		for(LogCommission record : records){
			mapper.insert(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchUpdate(List<LogCommission> records)
			 {
		for(LogCommission record : records){
			mapper.updateByPrimaryKeySelective(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchDelete(List<LogCommission> records)
			 {
		for(LogCommission record : records){
			mapper.deleteByPrimaryKey(record);
		}
		return records.size();
	}

	@Override
	public List<LogCommission> selectByExample(LogCommissionExample example)
			 {
		return mapper.selectByExample(example);
	}

	@Override
	public LogCommission selectByPrimaryKey(LogCommissionKey key)
			 {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public List<LogCommission> findAll(List<LogCommission> records) {
		if(records==null||records.size()<=0){
			return mapper.selectByExample(new LogCommissionExample());
		}
		List<LogCommission> list = new ArrayList<LogCommission>();
		for(LogCommission record : records){
			LogCommission result = mapper.selectByPrimaryKey(record);
			if(result!=null){
				list.add(result);
			}
		}
		return list;
	}

	@Override
	public int updateByExampleSelective(LogCommission record, LogCommissionExample example)  {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(LogCommission record, LogCommissionExample example) {
		return mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(LogCommission record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(LogCommission record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int sumByExample(LogCommissionExample example) {
		return 0;
	}

	@Override
	public void deleteAll()  {
		mapper.deleteByExample(new LogCommissionExample());
	}
	
	
	public int getCount(DbCondi dc){
		List<HashMap<String, Object>> resultSet = null;
		try {
			resultSet = common.executeSql(SqlMaker.getCountSql(dc));
			return ((Number) resultSet.get(0).get("COUNT")).intValue();
		} catch (Exception e) {
			log.warn("执行sql异常",e);
			return 0;
		}
	}
	
	public List<HashMap<String,Object>> getData(DbCondi dc){
		 List<HashMap<String, Object>> resultSet = null;
                try {
                    String sql = SqlMaker.getData(dc);
                    resultSet = common.executeSql(sql);
					KeyExplainHandler.addId(resultSet, dc.getKeyClass(),dc.getEntityClass());//add key
                } catch (IllegalAccessException e) {
					log.warn("执行sql异常",e);
                } catch (InvocationTargetException e) {
					log.warn("执行sql异常",e);
                }
                return resultSet;
	}
	
	public List<HashMap<String,Object>> dosql(String sql){
		List<HashMap<String,Object>> resultSet = common.executeSql(sql);
		return resultSet;
	}
	@Override
	public LogCommissionExample getExample(LogCommission record) {
		LogCommissionExample example = new LogCommissionExample();
		if(record!=null){
			Criteria criteria = example.createCriteria();
			if(record.getUuid()!=null){
				criteria.andUuidEqualTo(record.getUuid());
			}
			if(record.getParentname()!=null){
				criteria.andParentnameEqualTo(record.getParentname());
			}
			if(record.getSecondname()!=null){
				criteria.andSecondnameEqualTo(record.getSecondname());
			}
			if(record.getMoney()!=null){
				criteria.andMoneyEqualTo(record.getMoney());
			}
			if(record.getOrdertime()!=null){
				criteria.andOrdertimeEqualTo(record.getOrdertime());
			}
			if(record.getStatus()!=null){
				criteria.andStatusEqualTo(record.getStatus());
			}
			if(record.getDate()!=null){
				criteria.andDateEqualTo(record.getDate());
			}

		}
		return example;
	}
}
