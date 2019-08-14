package com.joyveb.cashmanage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyveb.cashmanage.entity.GameInfo;
import com.joyveb.cashmanage.entity.GameInfoExample;
import com.joyveb.cashmanage.entity.GameInfoExample.Criteria;
import com.joyveb.cashmanage.entity.GameInfoKey;
import com.joyveb.cashmanage.mapper.GameInfoMapper;
import com.joyveb.lbos.restful.common.CommonSqlMapper;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.util.KeyExplainHandler;
import com.joyveb.lbos.restful.util.SqlMaker;
@Slf4j
@Service
public class GameInfoService implements ServiceInterface<GameInfo, GameInfoExample, GameInfoKey>{

	@Resource
	private GameInfoMapper mapper;
	private @Resource CommonSqlMapper common;
	
	
	@Override
	public int countByExample(GameInfoExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public int deleteByExample(GameInfoExample example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(GameInfoKey key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(GameInfo record)  {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(GameInfo record)  {
		return mapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int batchInsert(List<GameInfo> records)
			 {
		for(GameInfo record : records){
			mapper.insert(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchUpdate(List<GameInfo> records)
			 {
		for(GameInfo record : records){
			mapper.updateByPrimaryKeySelective(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchDelete(List<GameInfo> records)
			 {
		for(GameInfo record : records){
			mapper.deleteByPrimaryKey(record);
		}
		return records.size();
	}

	@Override
	public List<GameInfo> selectByExample(GameInfoExample example)
			 {
		return mapper.selectByExample(example);
	}

	@Override
	public GameInfo selectByPrimaryKey(GameInfoKey key)
			 {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public List<GameInfo> findAll(List<GameInfo> records) {
		if(records==null||records.size()<=0){
			return mapper.selectByExample(new GameInfoExample());
		}
		List<GameInfo> list = new ArrayList<GameInfo>();
		for(GameInfo record : records){
			GameInfo result = mapper.selectByPrimaryKey(record);
			if(result!=null){
				list.add(result);
			}
		}
		return list;
	}

	@Override
	public int updateByExampleSelective(GameInfo record, GameInfoExample example)  {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(GameInfo record, GameInfoExample example) {
		return mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(GameInfo record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(GameInfo record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int sumByExample(GameInfoExample example) {
		return 0;
	}

	@Override
	public void deleteAll()  {
		mapper.deleteByExample(new GameInfoExample());
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
	public GameInfoExample getExample(GameInfo record) {
		GameInfoExample example = new GameInfoExample();
		if(record!=null){
			Criteria criteria = example.createCriteria();
							if(record.getGamecode()!=null){
				criteria.andGamecodeEqualTo(record.getGamecode());
				}
				if(record.getGamename()!=null){
				criteria.andGamenameEqualTo(record.getGamename());
				}
				if(record.getPreviewpath()!=null){
				criteria.andPreviewpathEqualTo(record.getPreviewpath());
				}
				if(record.getDetailedpath()!=null){
				criteria.andDetailedpathEqualTo(record.getDetailedpath());
				}
				if(record.getFacevalue()!=null){
				criteria.andFacevalueEqualTo(record.getFacevalue());
				}
				if(record.getGametype()!=null){
				criteria.andGametypeEqualTo(record.getGametype());
				}
				if(record.getGameprice()!=null){
				criteria.andGamepriceEqualTo(record.getGameprice());
				}

		}
		return example;
	}
}
