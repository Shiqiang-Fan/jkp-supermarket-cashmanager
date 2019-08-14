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

import com.joyveb.cashmanage.entity.LogMoney;
import com.joyveb.cashmanage.entity.LogMoneyExample;
import com.joyveb.cashmanage.entity.LogMoneyExample.Criteria;
import com.joyveb.cashmanage.entity.LogMoneyKey;

import com.joyveb.cashmanage.mapper.LogMoneyMapper;
import com.joyveb.lbos.restful.common.CommonSqlMapper;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.util.SqlMaker;
@Slf4j
@Service
public class LogMoneyService implements ServiceInterface<LogMoney, LogMoneyExample, LogMoneyKey>{

	@Resource
	private LogMoneyMapper mapper;
	private @Resource CommonSqlMapper common;
	
	
	@Override
	public int countByExample(LogMoneyExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public int deleteByExample(LogMoneyExample example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(LogMoneyKey key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(LogMoney record)  {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(LogMoney record)  {
		return mapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int batchInsert(List<LogMoney> records)
			 {
		for(LogMoney record : records){
			mapper.insert(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchUpdate(List<LogMoney> records)
			 {
		for(LogMoney record : records){
			mapper.updateByPrimaryKeySelective(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchDelete(List<LogMoney> records)
			 {
		for(LogMoney record : records){
			mapper.deleteByPrimaryKey(record);
		}
		return records.size();
	}

	@Override
	public List<LogMoney> selectByExample(LogMoneyExample example)
			 {
		return mapper.selectByExample(example);
	}

	@Override
	public LogMoney selectByPrimaryKey(LogMoneyKey key)
			 {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public List<LogMoney> findAll(List<LogMoney> records) {
		if(records==null||records.size()<=0){
			return mapper.selectByExample(new LogMoneyExample());
		}
		List<LogMoney> list = new ArrayList<LogMoney>();
		for(LogMoney record : records){
			LogMoney result = mapper.selectByPrimaryKey(record);
			if(result!=null){
				list.add(result);
			}
		}
		return list;
	}

	@Override
	public int updateByExampleSelective(LogMoney record, LogMoneyExample example)  {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(LogMoney record, LogMoneyExample example) {
		return mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(LogMoney record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(LogMoney record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int sumByExample(LogMoneyExample example) {
		return 0;
	}

	@Override
	public void deleteAll()  {
		mapper.deleteByExample(new LogMoneyExample());
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
	public LogMoneyExample getExample(LogMoney record) {
		LogMoneyExample example = new LogMoneyExample();
		if(record!=null){
			Criteria criteria = example.createCriteria();
			if(record.getUuid()!=null){
				criteria.andUuidEqualTo(record.getUuid());
			}
			if(record.getParentname()!=null){
				criteria.andParentnameEqualTo(record.getParentname());
			}
			if(record.getAlipaynum()!=null){
				criteria.andAlipaynumEqualTo(record.getAlipaynum());
			}
			if(record.getExtractmoney()!=null){
				criteria.andExtractmoneyEqualTo(record.getExtractmoney());
			}
			if(record.getResiduemoney()!=null){
				criteria.andResiduemoneyEqualTo(record.getResiduemoney());
			}
			if(record.getStatus()!=null){
				criteria.andStatusEqualTo(record.getStatus());
			}
			if(record.getFailreason()!=null){
				criteria.andFailreasonEqualTo(record.getFailreason());
			}
			if(record.getExtracttime()!=null){
				criteria.andExtracttimeEqualTo(record.getExtracttime());
			}
			if(record.getDate()!=null){
				criteria.andDateEqualTo(record.getDate());
			}

		}
		return example;
	}
}
