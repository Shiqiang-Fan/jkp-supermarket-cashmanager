package com.joyveb.auth.restful.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyveb.auth.restful.bean.AdminRole;
import com.joyveb.auth.restful.bean.AdminRoleExample;
import com.joyveb.auth.restful.bean.AdminRoleExample.Criteria;
import com.joyveb.auth.restful.bean.AdminRoleKey;
import com.joyveb.auth.restful.mapper.AdminRoleMapper;
import com.joyveb.cashmanage.service.ServiceInterface;
import com.joyveb.lbos.restful.common.CommonSqlMapper;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.util.KeyExplainHandler;
import com.joyveb.lbos.restful.util.SqlMaker;

@Service
public class AdminRoleService implements ServiceInterface<AdminRole, AdminRoleExample, AdminRoleKey>{

	@Resource
	private AdminRoleMapper mapper;
	private @Resource CommonSqlMapper common;
	
	@Override
	public int countByExample(AdminRoleExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public int deleteByExample(AdminRoleExample example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(AdminRoleKey key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(AdminRole record)  {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(AdminRole record)  {
		return mapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int batchInsert(List<AdminRole> records)
			 {
		for(AdminRole record : records){
			mapper.insert(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchUpdate(List<AdminRole> records)
			 {
		for(AdminRole record : records){
			mapper.updateByPrimaryKeySelective(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchDelete(List<AdminRole> records)
			 {
		for(AdminRole record : records){
			mapper.deleteByPrimaryKey(record);
		}
		return records.size();
	}

	@Override
	public List<AdminRole> selectByExample(AdminRoleExample example)
			 {
		return mapper.selectByExample(example);
	}

	@Override
	public AdminRole selectByPrimaryKey(AdminRoleKey key)
			 {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public List<AdminRole> findAll(List<AdminRole> records) {
		if(records==null||records.size()<=0){
			return mapper.selectByExample(new AdminRoleExample());
		}
		List<AdminRole> list = new ArrayList<AdminRole>();
		for(AdminRole record : records){
			AdminRole result = mapper.selectByPrimaryKey(record);
			if(result!=null){
				list.add(result);
			}
		}
		return list;
	}

	@Override
	public int updateByExampleSelective(AdminRole record, AdminRoleExample example)  {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(AdminRole record, AdminRoleExample example) {
		return mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(AdminRole record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AdminRole record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int sumByExample(AdminRoleExample example) {
		return 0;
	}

	@Override
	public void deleteAll()  {
		mapper.deleteByExample(new AdminRoleExample());
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
	public AdminRoleExample getExample(AdminRole record) {
		AdminRoleExample example = new AdminRoleExample();
		if(record!=null){
			Criteria criteria = example.createCriteria();
							if(record.getRoleCode()!=null){
				criteria.andRoleCodeEqualTo(record.getRoleCode());
				}
				if(record.getRoleName()!=null){
				criteria.andRoleNameEqualTo(record.getRoleName());
				}

		}
		return example;
	}
}
