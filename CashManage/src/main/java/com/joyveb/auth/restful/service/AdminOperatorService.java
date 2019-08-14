package com.joyveb.auth.restful.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyveb.auth.restful.bean.AdminOperator;
import com.joyveb.auth.restful.bean.AdminOperatorExample;
import com.joyveb.auth.restful.bean.AdminOperatorExample.Criteria;
import com.joyveb.auth.restful.bean.AdminOperatorKey;
import com.joyveb.auth.restful.mapper.AdminOperatorMapper;
import com.joyveb.cashmanage.service.ServiceInterface;
import com.joyveb.lbos.restful.common.CommonSqlMapper;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.util.KeyExplainHandler;
import com.joyveb.lbos.restful.util.SqlMaker;
@Service
public class AdminOperatorService implements ServiceInterface<AdminOperator, AdminOperatorExample, AdminOperatorKey>{

	@Resource
	private AdminOperatorMapper mapper;
	private @Resource CommonSqlMapper common;
	
	
	@Override
	public int countByExample(AdminOperatorExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public int deleteByExample(AdminOperatorExample example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(AdminOperatorKey key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(AdminOperator record)  {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(AdminOperator record)  {
		return mapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int batchInsert(List<AdminOperator> records)
			 {
		for(AdminOperator record : records){
			mapper.insert(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchUpdate(List<AdminOperator> records)
			 {
		for(AdminOperator record : records){
			mapper.updateByPrimaryKeySelective(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchDelete(List<AdminOperator> records)
			 {
		for(AdminOperator record : records){
			mapper.deleteByPrimaryKey(record);
		}
		return records.size();
	}

	@Override
	public List<AdminOperator> selectByExample(AdminOperatorExample example)
			 {
		return mapper.selectByExample(example);
	}

	@Override
	public AdminOperator selectByPrimaryKey(AdminOperatorKey key)
			 {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public List<AdminOperator> findAll(List<AdminOperator> records) {
		if(records==null||records.size()<=0){
			return mapper.selectByExample(new AdminOperatorExample());
		}
		List<AdminOperator> list = new ArrayList<AdminOperator>();
		for(AdminOperator record : records){
			AdminOperator result = mapper.selectByPrimaryKey(record);
			if(result!=null){
				list.add(result);
			}
		}
		return list;
	}

	@Override
	public int updateByExampleSelective(AdminOperator record, AdminOperatorExample example)  {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(AdminOperator record, AdminOperatorExample example) {
		return mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(AdminOperator record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AdminOperator record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int sumByExample(AdminOperatorExample example) {
		return 0;
	}

	@Override
	public void deleteAll()  {
		mapper.deleteByExample(new AdminOperatorExample());
	}
	
	public int getCount(DbCondi dc) throws Exception{
		List<HashMap<String,Object>> resultSet = common.executeSql(SqlMaker.getCountSql(dc));
		return ((Number) resultSet.get(0).get("COUNT")).intValue();
	}
	
	public List<HashMap<String,Object>> getData(DbCondi dc) throws Exception{
		String sql = SqlMaker.getData(dc); 
		List<HashMap<String,Object>> resultSet = common.executeSql(sql);
		KeyExplainHandler.addId(resultSet, dc.getKeyClass(),dc.getEntityClass());//add key
		return resultSet;
	}
	
	public List<HashMap<String,Object>> dosql(String sql){
		List<HashMap<String,Object>> resultSet = common.executeSql(sql);
		return resultSet;
	}
	@Override
	public AdminOperatorExample getExample(AdminOperator record) {
		AdminOperatorExample example = new AdminOperatorExample();
		if(record!=null){
			Criteria criteria = example.createCriteria();
							if(record.getUserName()!=null){
				criteria.andUserNameEqualTo(record.getUserName());
				}
				if(record.getPassWord()!=null){
				criteria.andPassWordEqualTo(record.getPassWord());
				}
				if(record.getRealName()!=null){
				criteria.andRealNameEqualTo(record.getRealName());
				}
				if(record.getRetry()!=null){
				criteria.andRetryEqualTo(record.getRetry());
				}
				if(record.getStatus()!=null){
				criteria.andStatusEqualTo(record.getStatus());
				}
				if(record.getCreateDate()!=null){
				criteria.andCreateDateEqualTo(record.getCreateDate());
				}
				if(record.getLastDate()!=null){
				criteria.andLastDateEqualTo(record.getLastDate());
				}
				if(record.getRoleCode()!=null){
				criteria.andRoleCodeEqualTo(record.getRoleCode());
				}

		}
		return example;
	}
}
