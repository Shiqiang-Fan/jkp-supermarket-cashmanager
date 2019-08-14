package com.joyveb.auth.restful.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyveb.auth.restful.bean.AdminRolePermission;
import com.joyveb.auth.restful.bean.AdminRolePermissionExample;
import com.joyveb.auth.restful.bean.AdminRolePermissionExample.Criteria;
import com.joyveb.auth.restful.bean.AdminRolePermissionKey;
import com.joyveb.auth.restful.mapper.AdminRolePermissionMapper;
import com.joyveb.cashmanage.service.ServiceInterface;
import com.joyveb.lbos.restful.common.CommonSqlMapper;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.util.KeyExplainHandler;
import com.joyveb.lbos.restful.util.SqlMaker;

@Service
public class AdminRolePermissionService implements ServiceInterface<AdminRolePermission, AdminRolePermissionExample, AdminRolePermissionKey>{

	@Resource
	private AdminRolePermissionMapper mapper;
	private @Resource CommonSqlMapper common;
	
	
	@Override
	public int countByExample(AdminRolePermissionExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public int deleteByExample(AdminRolePermissionExample example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(AdminRolePermissionKey key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(AdminRolePermission record)  {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(AdminRolePermission record)  {
		return mapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int batchInsert(List<AdminRolePermission> records)
			 {
		for(AdminRolePermission record : records){
			mapper.insert(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchUpdate(List<AdminRolePermission> records)
			 {
		for(AdminRolePermission record : records){
			mapper.updateByPrimaryKeySelective(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchDelete(List<AdminRolePermission> records)
			 {
		for(AdminRolePermission record : records){
			mapper.deleteByPrimaryKey(record);
		}
		return records.size();
	}

	@Override
	public List<AdminRolePermission> selectByExample(AdminRolePermissionExample example)
			 {
		return mapper.selectByExample(example);
	}

	@Override
	public AdminRolePermission selectByPrimaryKey(AdminRolePermissionKey key)
			 {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public List<AdminRolePermission> findAll(List<AdminRolePermission> records) {
		if(records==null||records.size()<=0){
			return mapper.selectByExample(new AdminRolePermissionExample());
		}
		List<AdminRolePermission> list = new ArrayList<AdminRolePermission>();
		for(AdminRolePermission record : records){
			AdminRolePermission result = mapper.selectByPrimaryKey(record);
			if(result!=null){
				list.add(result);
			}
		}
		return list;
	}

	@Override
	public int updateByExampleSelective(AdminRolePermission record, AdminRolePermissionExample example)  {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(AdminRolePermission record, AdminRolePermissionExample example) {
		return mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(AdminRolePermission record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AdminRolePermission record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int sumByExample(AdminRolePermissionExample example) {
		return 0;
	}

	@Override
	public void deleteAll()  {
		mapper.deleteByExample(new AdminRolePermissionExample());
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
	public AdminRolePermissionExample getExample(AdminRolePermission record) {
		AdminRolePermissionExample example = new AdminRolePermissionExample();
		if(record!=null){
			Criteria criteria = example.createCriteria();
							if(record.getRoleCode()!=null){
				criteria.andRoleCodeEqualTo(record.getRoleCode());
				}
				if(record.getPermissionId()!=null){
				criteria.andPermissionIdEqualTo(record.getPermissionId());
				}
				if(record.getField1()!=null){
				criteria.andField1EqualTo(record.getField1());
				}

		}
		return example;
	}
}
