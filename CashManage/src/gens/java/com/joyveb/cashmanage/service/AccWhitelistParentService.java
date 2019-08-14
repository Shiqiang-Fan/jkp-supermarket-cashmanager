package com.joyveb.cashmanage.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import javax.annotation.Resource;

import com.joyveb.lbos.restful.util.KeyExplainHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyveb.cashmanage.entity.AccWhitelistParent;
import com.joyveb.cashmanage.entity.AccWhitelistParentExample;
import com.joyveb.cashmanage.entity.AccWhitelistParentExample.Criteria;
import com.joyveb.cashmanage.entity.AccWhitelistParentKey;

import com.joyveb.cashmanage.mapper.AccWhitelistParentMapper;
import com.joyveb.lbos.restful.common.CommonSqlMapper;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.util.SqlMaker;
@Slf4j
@Service
public class AccWhitelistParentService implements ServiceInterface<AccWhitelistParent, AccWhitelistParentExample, AccWhitelistParentKey>{

	@Resource
	private AccWhitelistParentMapper mapper;
	private @Resource CommonSqlMapper common;
	
	
	@Override
	public int countByExample(AccWhitelistParentExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public int deleteByExample(AccWhitelistParentExample example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(AccWhitelistParentKey key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(AccWhitelistParent record)  {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(AccWhitelistParent record)  {
		return mapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int batchInsert(List<AccWhitelistParent> records)
			 {
		for(AccWhitelistParent record : records){
			mapper.insert(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchUpdate(List<AccWhitelistParent> records)
			 {
		for(AccWhitelistParent record : records){
			mapper.updateByPrimaryKeySelective(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchDelete(List<AccWhitelistParent> records)
			 {
		for(AccWhitelistParent record : records){
			mapper.deleteByPrimaryKey(record);
		}
		return records.size();
	}

	@Override
	public List<AccWhitelistParent> selectByExample(AccWhitelistParentExample example)
			 {
		return mapper.selectByExample(example);
	}

	@Override
	public AccWhitelistParent selectByPrimaryKey(AccWhitelistParentKey key)
			 {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public List<AccWhitelistParent> findAll(List<AccWhitelistParent> records) {
		if(records==null||records.size()<=0){
			return mapper.selectByExample(new AccWhitelistParentExample());
		}
		List<AccWhitelistParent> list = new ArrayList<AccWhitelistParent>();
		for(AccWhitelistParent record : records){
			AccWhitelistParent result = mapper.selectByPrimaryKey(record);
			if(result!=null){
				list.add(result);
			}
		}
		return list;
	}

	@Override
	public int updateByExampleSelective(AccWhitelistParent record, AccWhitelistParentExample example)  {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(AccWhitelistParent record, AccWhitelistParentExample example) {
		return mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(AccWhitelistParent record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AccWhitelistParent record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int sumByExample(AccWhitelistParentExample example) {
		return 0;
	}

	@Override
	public void deleteAll()  {
		mapper.deleteByExample(new AccWhitelistParentExample());
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
	public AccWhitelistParentExample getExample(AccWhitelistParent record) {
		AccWhitelistParentExample example = new AccWhitelistParentExample();
		if(record!=null){
			Criteria criteria = example.createCriteria();
			if(record.getUsername()!=null){
				criteria.andUsernameEqualTo(record.getUsername());
			}
			if(record.getFullname()!=null){
				criteria.andFullnameEqualTo(record.getFullname());
			}
			if(record.getIdcard()!=null){
				criteria.andIdcardEqualTo(record.getIdcard());
			}
			if(record.getMobile()!=null){
				criteria.andMobileEqualTo(record.getMobile());
			}
			if(record.getAddress()!=null){
				criteria.andAddressEqualTo(record.getAddress());
			}
			if(record.getStatus()!=null){
				criteria.andStatusEqualTo(record.getStatus());
			}
			if(record.getCommission()!=null){
				criteria.andCommissionEqualTo(record.getCommission());
			}
			if(record.getAlipaynum()!=null){
				criteria.andAlipaynumEqualTo(record.getAlipaynum());
			}
			if(record.getComment()!=null){
				criteria.andCommentEqualTo(record.getComment());
			}
			if(record.getCreatetime()!=null){
				criteria.andCreatetimeEqualTo(record.getCreatetime());
			}

		}
		return example;
	}
}
