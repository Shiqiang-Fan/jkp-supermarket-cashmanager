package com.joyveb.cashmanage.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import javax.annotation.Resource;

import com.joyveb.cashmanage.utils.CommonUtils;
import com.joyveb.lbos.restful.util.KeyExplainHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyveb.cashmanage.entity.WhiteList;
import com.joyveb.cashmanage.entity.WhiteListExample;
import com.joyveb.cashmanage.entity.WhiteListExample.Criteria;
import com.joyveb.cashmanage.entity.WhiteListKey;

import com.joyveb.cashmanage.mapper.WhiteListMapper;
import com.joyveb.lbos.restful.common.CommonSqlMapper;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.util.SqlMaker;
@Slf4j
@Service
public class WhiteListService implements ServiceInterface<WhiteList, WhiteListExample, WhiteListKey>{

	@Resource
	private WhiteListMapper mapper;
	private @Resource CommonSqlMapper common;
	
	
	@Override
	public int countByExample(WhiteListExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public int deleteByExample(WhiteListExample example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(WhiteListKey key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(WhiteList record)  {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(WhiteList record)  {
		return mapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int batchInsert(List<WhiteList> records)
			 {
		for(WhiteList record : records){
			mapper.insert(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchUpdate(List<WhiteList> records)
			 {
		for(WhiteList record : records){
			mapper.updateByPrimaryKeySelective(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchDelete(List<WhiteList> records)
			 {
		for(WhiteList record : records){
			mapper.deleteByPrimaryKey(record);
		}
		return records.size();
	}

	@Override
	public List<WhiteList> selectByExample(WhiteListExample example)
			 {
		return mapper.selectByExample(example);
	}
	public String selectParentByName(String username){
		WhiteListExample whiteListExample = new WhiteListExample();
		whiteListExample.createCriteria().andUsernameEqualTo(username);
		List<WhiteList> whiteLists = mapper.selectByExample(whiteListExample);
		if(CommonUtils.isNotEmpty(whiteLists)){
			WhiteList whiteList = whiteLists.get(0);
			return whiteList.getParentname();
		}
		return "";
	}
	@Override
	public WhiteList selectByPrimaryKey(WhiteListKey key)
			 {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public List<WhiteList> findAll(List<WhiteList> records) {
		if(records==null||records.size()<=0){
			return mapper.selectByExample(new WhiteListExample());
		}
		List<WhiteList> list = new ArrayList<WhiteList>();
		for(WhiteList record : records){
			WhiteList result = mapper.selectByPrimaryKey(record);
			if(result!=null){
				list.add(result);
			}
		}
		return list;
	}

	@Override
	public int updateByExampleSelective(WhiteList record, WhiteListExample example)  {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(WhiteList record, WhiteListExample example) {
		return mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(WhiteList record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(WhiteList record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int sumByExample(WhiteListExample example) {
		return 0;
	}

	@Override
	public void deleteAll()  {
		mapper.deleteByExample(new WhiteListExample());
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
					KeyExplainHandler.addId(resultSet, dc.getKeyClass(), dc.getEntityClass());//add key
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
	public WhiteListExample getExample(WhiteList record) {
		WhiteListExample example = new WhiteListExample();
		if(record!=null){
			Criteria criteria = example.createCriteria();
							if(record.getUuid()!=null){
				criteria.andUuidEqualTo(record.getUuid());
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
				if(record.getTransfercycle()!=null){
				criteria.andTransfercycleEqualTo(record.getTransfercycle());
				}
				if(record.getUserid()!=null){
				criteria.andUseridEqualTo(record.getUserid());
				}
				if(record.getStatus()!=null){
				criteria.andStatusEqualTo(record.getStatus());
				}
				if(record.getUserkey()!=null){
				criteria.andUserkeyEqualTo(record.getUserkey());
				}
				if(record.getComment()!=null){
				criteria.andCommentEqualTo(record.getComment());
				}
				if(record.getUsetime()!=null){
				criteria.andUsetimeEqualTo(record.getUsetime());
				}
				if(record.getDeposit()!=null){
				criteria.andDepositEqualTo(record.getDeposit());
				}
				if(record.getDevicetype()!=null){
				criteria.andDevicetypeEqualTo(record.getDevicetype());
				}
				if(record.getDevicenum()!=null){
				criteria.andDevicenumEqualTo(record.getDevicenum());
				}
				if(record.getCommission()!=null){
				criteria.andCommissionEqualTo(record.getCommission());
				}
				if(record.getParentname()!=null){
				criteria.andParentnameEqualTo(record.getParentname());
				}
				if(record.getUsername()!=null){
				criteria.andUsernameEqualTo(record.getUsername());
				}
				if(record.getPassword()!=null){
				criteria.andPasswordEqualTo(record.getPassword());
				}
				if(record.getAlipaynum()!=null){
				criteria.andAlipaynumEqualTo(record.getAlipaynum());
				}
				if(record.getLogintime()!=null){
				criteria.andLogintimeEqualTo(record.getLogintime());
				}

		}
		return example;
	}
}
