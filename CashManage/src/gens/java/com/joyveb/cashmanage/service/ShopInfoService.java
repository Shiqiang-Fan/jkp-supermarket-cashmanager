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
import com.joyveb.cashmanage.entity.ShopInfo;
import com.joyveb.cashmanage.entity.ShopInfoExample;
import com.joyveb.cashmanage.entity.ShopInfoExample.Criteria;
import com.joyveb.cashmanage.entity.ShopInfoKey;
import com.joyveb.cashmanage.mapper.ShopInfoMapper;
import com.joyveb.lbos.restful.common.CommonSqlMapper;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.util.SqlMaker;
@Slf4j
@Service
public class ShopInfoService implements ServiceInterface<ShopInfo, ShopInfoExample, ShopInfoKey>{

	@Resource
	private ShopInfoMapper mapper;
	private @Resource CommonSqlMapper common;
	
	
	@Override
	public int countByExample(ShopInfoExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public int deleteByExample(ShopInfoExample example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(ShopInfoKey key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(ShopInfo record)  {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(ShopInfo record)  {
		return mapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int batchInsert(List<ShopInfo> records)
			 {
		for(ShopInfo record : records){
			mapper.insert(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchUpdate(List<ShopInfo> records)
			 {
		for(ShopInfo record : records){
			mapper.updateByPrimaryKeySelective(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchDelete(List<ShopInfo> records)
			 {
		for(ShopInfo record : records){
			mapper.deleteByPrimaryKey(record);
		}
		return records.size();
	}

	@Override
	public List<ShopInfo> selectByExample(ShopInfoExample example)
			 {
		return mapper.selectByExample(example);
	}

	@Override
	public ShopInfo selectByPrimaryKey(ShopInfoKey key)
			 {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public List<ShopInfo> findAll(List<ShopInfo> records) {
		if(records==null||records.size()<=0){
			return mapper.selectByExample(new ShopInfoExample());
		}
		List<ShopInfo> list = new ArrayList<ShopInfo>();
		for(ShopInfo record : records){
			ShopInfo result = mapper.selectByPrimaryKey(record);
			if(result!=null){
				list.add(result);
			}
		}
		return list;
	}

	@Override
	public int updateByExampleSelective(ShopInfo record, ShopInfoExample example)  {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(ShopInfo record, ShopInfoExample example) {
		return mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(ShopInfo record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ShopInfo record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int sumByExample(ShopInfoExample example) {
		return 0;
	}

	@Override
	public void deleteAll()  {
		mapper.deleteByExample(new ShopInfoExample());
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
	public ShopInfoExample getExample(ShopInfo record) {
		ShopInfoExample example = new ShopInfoExample();
		if(record!=null){
			Criteria criteria = example.createCriteria();
							if(record.getUuid()!=null){
				criteria.andUuidEqualTo(record.getUuid());
				}
				if(record.getOrderid()!=null){
				criteria.andOrderidEqualTo(record.getOrderid());
				}
				if(record.getGamecode()!=null){
				criteria.andGamecodeEqualTo(record.getGamecode());
				}
				if(record.getOrderamount()!=null){
				criteria.andOrderamountEqualTo(record.getOrderamount());
				}
				if(record.getGamenum()!=null){
				criteria.andGamenumEqualTo(record.getGamenum());
				}

		}
		return example;
	}
}
