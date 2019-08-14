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
import com.joyveb.cashmanage.entity.Version;
import com.joyveb.cashmanage.entity.VersionExample;
import com.joyveb.cashmanage.entity.VersionExample.Criteria;
import com.joyveb.cashmanage.entity.VersionKey;
import com.joyveb.cashmanage.mapper.VersionMapper;
import com.joyveb.lbos.restful.common.CommonSqlMapper;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.util.SqlMaker;
@Slf4j
@Service
public class VersionService implements ServiceInterface<Version, VersionExample, VersionKey>{

	@Resource
	private VersionMapper mapper;
	private @Resource CommonSqlMapper common;
	
	
	@Override
	public int countByExample(VersionExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public int deleteByExample(VersionExample example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(VersionKey key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(Version record)  {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(Version record)  {
		return mapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int batchInsert(List<Version> records)
			 {
		for(Version record : records){
			mapper.insert(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchUpdate(List<Version> records)
			 {
		for(Version record : records){
			mapper.updateByPrimaryKeySelective(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchDelete(List<Version> records)
			 {
		for(Version record : records){
			mapper.deleteByPrimaryKey(record);
		}
		return records.size();
	}

	@Override
	public List<Version> selectByExample(VersionExample example)
			 {
		return mapper.selectByExample(example);
	}

	@Override
	public Version selectByPrimaryKey(VersionKey key)
			 {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public List<Version> findAll(List<Version> records) {
		if(records==null||records.size()<=0){
			return mapper.selectByExample(new VersionExample());
		}
		List<Version> list = new ArrayList<Version>();
		for(Version record : records){
			Version result = mapper.selectByPrimaryKey(record);
			if(result!=null){
				list.add(result);
			}
		}
		return list;
	}

	@Override
	public int updateByExampleSelective(Version record, VersionExample example)  {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(Version record, VersionExample example) {
		return mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(Version record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Version record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int sumByExample(VersionExample example) {
		return 0;
	}

	@Override
	public void deleteAll()  {
		mapper.deleteByExample(new VersionExample());
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
	public VersionExample getExample(Version record) {
		VersionExample example = new VersionExample();
		if(record!=null){
			Criteria criteria = example.createCriteria();
							if(record.getVol()!=null){
				criteria.andVolEqualTo(record.getVol());
				}
				if(record.getVersion()!=null){
				criteria.andVersionEqualTo(record.getVersion());
				}
				if(record.getPubtime()!=null){
				criteria.andPubtimeEqualTo(record.getPubtime());
				}
				if(record.getUrl()!=null){
				criteria.andUrlEqualTo(record.getUrl());
				}
				if(record.getComment()!=null){
				criteria.andCommentEqualTo(record.getComment());
				}
				if(record.getApkpath()!=null){
				criteria.andApkpathEqualTo(record.getApkpath());
				}

		}
		return example;
	}
}
