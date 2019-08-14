package com.joyveb.auth.restful.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyveb.auth.restful.bean.AdminPermission;
import com.joyveb.auth.restful.bean.AdminPermissionExample;
import com.joyveb.auth.restful.bean.AdminPermissionExample.Criteria;
import com.joyveb.auth.restful.bean.AdminPermissionKey;
import com.joyveb.auth.restful.mapper.AdminPermissionMapper;
import com.joyveb.cashmanage.service.ServiceInterface;
import com.joyveb.lbos.restful.common.CommonSqlMapper;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.util.KeyExplainHandler;
import com.joyveb.lbos.restful.util.SqlMaker;

@Service
public class AdminPermissionService implements ServiceInterface<AdminPermission, AdminPermissionExample, AdminPermissionKey>{

	@Resource
	private AdminPermissionMapper mapper;
	private @Resource CommonSqlMapper common;
	
	
	@Override
	public int countByExample(AdminPermissionExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public int deleteByExample(AdminPermissionExample example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(AdminPermissionKey key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(AdminPermission record)  {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(AdminPermission record)  {
		return mapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int batchInsert(List<AdminPermission> records)
			 {
		for(AdminPermission record : records){
			mapper.insert(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchUpdate(List<AdminPermission> records)
			 {
		for(AdminPermission record : records){
			mapper.updateByPrimaryKeySelective(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchDelete(List<AdminPermission> records)
			 {
		for(AdminPermission record : records){
			mapper.deleteByPrimaryKey(record);
		}
		return records.size();
	}

	@Override
	public List<AdminPermission> selectByExample(AdminPermissionExample example)
			 {
		return mapper.selectByExample(example);
	}

	@Override
	public AdminPermission selectByPrimaryKey(AdminPermissionKey key)
			 {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public List<AdminPermission> findAll(List<AdminPermission> records) {
		if(records==null||records.size()<=0){
			return mapper.selectByExample(new AdminPermissionExample());
		}
		List<AdminPermission> list = new ArrayList<AdminPermission>();
		for(AdminPermission record : records){
			AdminPermission result = mapper.selectByPrimaryKey(record);
			if(result!=null){
				list.add(result);
			}
		}
		return list;
	}

	@Override
	public int updateByExampleSelective(AdminPermission record, AdminPermissionExample example)  {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(AdminPermission record, AdminPermissionExample example) {
		return mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(AdminPermission record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AdminPermission record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int sumByExample(AdminPermissionExample example) {
		return 0;
	}

	@Override
	public void deleteAll()  {
		mapper.deleteByExample(new AdminPermissionExample());
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
	public AdminPermissionExample getExample(AdminPermission record) {
		AdminPermissionExample example = new AdminPermissionExample();
		if(record!=null){
			Criteria criteria = example.createCriteria();
							if(record.getId()!=null){
				criteria.andIdEqualTo(record.getId());
				}
				if(record.getPid()!=null){
				criteria.andPidEqualTo(record.getPid());
				}
				if(record.getName()!=null){
				criteria.andNameEqualTo(record.getName());
				}
				if(record.getUrl()!=null){
				criteria.andUrlEqualTo(record.getUrl());
				}
				if(record.getLeaf()!=null){
				criteria.andLeafEqualTo(record.getLeaf());
				}
				if(record.getStatus()!=null){
				criteria.andStatusEqualTo(record.getStatus());
				}
				if(record.getOrderId()!=null){
				criteria.andOrderIdEqualTo(record.getOrderId());
				}
				if(record.getIcon()!=null){
				criteria.andIconEqualTo(record.getIcon());
				}

		}
		return example;
	}
}
