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
import com.joyveb.cashmanage.entity.ReceiverInfo;
import com.joyveb.cashmanage.entity.ReceiverInfoExample;
import com.joyveb.cashmanage.entity.ReceiverInfoExample.Criteria;
import com.joyveb.cashmanage.entity.ReceiverInfoKey;
import com.joyveb.cashmanage.mapper.ReceiverInfoMapper;
import com.joyveb.lbos.restful.common.CommonSqlMapper;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.util.SqlMaker;
@Slf4j
@Service
public class ReceiverInfoService implements ServiceInterface<ReceiverInfo, ReceiverInfoExample, ReceiverInfoKey>{

	@Resource
	private ReceiverInfoMapper mapper;
	private @Resource CommonSqlMapper common;
	
	
	@Override
	public int countByExample(ReceiverInfoExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public int deleteByExample(ReceiverInfoExample example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(ReceiverInfoKey key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(ReceiverInfo record)  {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(ReceiverInfo record)  {
		return mapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int batchInsert(List<ReceiverInfo> records)
			 {
		for(ReceiverInfo record : records){
			mapper.insert(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchUpdate(List<ReceiverInfo> records)
			 {
		for(ReceiverInfo record : records){
			mapper.updateByPrimaryKeySelective(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchDelete(List<ReceiverInfo> records)
			 {
		for(ReceiverInfo record : records){
			mapper.deleteByPrimaryKey(record);
		}
		return records.size();
	}

	@Override
	public List<ReceiverInfo> selectByExample(ReceiverInfoExample example)
			 {
		return mapper.selectByExample(example);
	}

	@Override
	public ReceiverInfo selectByPrimaryKey(ReceiverInfoKey key)
			 {
		return mapper.selectByPrimaryKey(key);
	}
	
	/*@Override
	public ReceiverInfo select()
			 {
		return  (ReceiverInfo) mapper.select();
	}*/

	@Override
	public List<ReceiverInfo> findAll(List<ReceiverInfo> records) {
		if(records==null||records.size()<=0){
			return mapper.selectByExample(new ReceiverInfoExample());
		}
		List<ReceiverInfo> list = new ArrayList<ReceiverInfo>();
		for(ReceiverInfo record : records){
			ReceiverInfo result = mapper.selectByPrimaryKey(record);
			if(result!=null){
				list.add(result);
			}
		}
		return list;
	}

	@Override
	public int updateByExampleSelective(ReceiverInfo record, ReceiverInfoExample example)  {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(ReceiverInfo record, ReceiverInfoExample example) {
		return mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(ReceiverInfo record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ReceiverInfo record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int sumByExample(ReceiverInfoExample example) {
		return 0;
	}

	@Override
	public void deleteAll()  {
		mapper.deleteByExample(new ReceiverInfoExample());
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
	public ReceiverInfoExample getExample(ReceiverInfo record) {
		ReceiverInfoExample example = new ReceiverInfoExample();
		if(record!=null){
			Criteria criteria = example.createCriteria();
							if(record.getUuid()!=null){
				criteria.andUuidEqualTo(record.getUuid());
				}
				if(record.getReceiver()!=null){
				criteria.andReceiverEqualTo(record.getReceiver());
				}
				if(record.getAddress()!=null){
				criteria.andAddressEqualTo(record.getAddress());
				}
				if(record.getPhone()!=null){
				criteria.andPhoneEqualTo(record.getPhone());
				}
				if(record.getUserid()!=null){
				criteria.andUseridEqualTo(record.getUserid());
				}
				if(record.getIsdefault()!=null){
				criteria.andIsdefaultEqualTo(record.getIsdefault());
				}

		}
		return example;
	}

	
}
