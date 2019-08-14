package com.joyveb.cashmanage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyveb.cashmanage.entity.StockManage;
import com.joyveb.cashmanage.entity.StockManageExample;
import com.joyveb.cashmanage.entity.StockManageExample.Criteria;
import com.joyveb.cashmanage.entity.StockManageKey;
import com.joyveb.cashmanage.mapper.StockManageMapper;
import com.joyveb.lbos.restful.common.CommonSqlMapper;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.util.KeyExplainHandler;
import com.joyveb.lbos.restful.util.SqlMaker;
@Slf4j
@Service
public class StockManageService implements ServiceInterface<StockManage, StockManageExample, StockManageKey>{

	@Resource
	private StockManageMapper mapper;
	private @Resource CommonSqlMapper common;
	
	
	@Override
	public int countByExample(StockManageExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public int deleteByExample(StockManageExample example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(StockManageKey key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(StockManage record)  {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(StockManage record)  {
		return mapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int batchInsert(List<StockManage> records)
			 {
		for(StockManage record : records){
			mapper.insert(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchUpdate(List<StockManage> records)
			 {
		for(StockManage record : records){
			mapper.updateByPrimaryKeySelective(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchDelete(List<StockManage> records)
			 {
		for(StockManage record : records){
			mapper.deleteByPrimaryKey(record);
		}
		return records.size();
	}

	@Override
	public List<StockManage> selectByExample(StockManageExample example)
			 {
		return mapper.selectByExample(example);
	}

	@Override
	public StockManage selectByPrimaryKey(StockManageKey key)
			 {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public List<StockManage> findAll(List<StockManage> records) {
		if(records==null||records.size()<=0){
			return mapper.selectByExample(new StockManageExample());
		}
		List<StockManage> list = new ArrayList<StockManage>();
		for(StockManage record : records){
			StockManage result = mapper.selectByPrimaryKey(record);
			if(result!=null){
				list.add(result);
			}
		}
		return list;
	}

	@Override
	public int updateByExampleSelective(StockManage record, StockManageExample example)  {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(StockManage record, StockManageExample example) {
		return mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(StockManage record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(StockManage record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int sumByExample(StockManageExample example) {
		return 0;
	}

	@Override
	public void deleteAll()  {
		mapper.deleteByExample(new StockManageExample());
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
	public StockManageExample getExample(StockManage record) {
		StockManageExample example = new StockManageExample();
		if(record!=null){
			Criteria criteria = example.createCriteria();
							if(record.getGamecode()!=null){
				criteria.andGamecodeEqualTo(record.getGamecode());
				}
				if(record.getTotal()!=null){
				criteria.andTotalEqualTo(record.getTotal());
				}
				if(record.getUpdatetime()!=null){
				criteria.andUpdatetimeEqualTo(record.getUpdatetime());
				}

		}
		return example;
	}
}
