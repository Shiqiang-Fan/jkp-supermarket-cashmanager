package com.joyveb.cashmanage.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.joyveb.cashmanage.entity.*;
import com.joyveb.cashmanage.mapper.AccExtractMapper;
import com.joyveb.cashmanage.mapper.LogCommissionMapper;
import com.joyveb.cashmanage.mapper.LogMoneyMapper;
import com.joyveb.cashmanage.utils.CommonUtils;
import com.joyveb.lbos.restful.util.KeyExplainHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyveb.cashmanage.entity.AccNeightCheckExample.Criteria;

import com.joyveb.cashmanage.mapper.AccNeightCheckMapper;
import com.joyveb.lbos.restful.common.CommonSqlMapper;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.util.SqlMaker;
@Slf4j
@Service
public class AccNeightCheckService implements ServiceInterface<AccNeightCheck, AccNeightCheckExample, AccNeightCheckKey>{

	@Resource
	private AccNeightCheckMapper mapper;
	private @Resource CommonSqlMapper common;
	@Resource
	private LogCommissionMapper logCommissionMapper;
	@Resource
	private LogMoneyMapper logMoneyMapper;
	@Resource
	private AccExtractMapper accExtractMapper;
	@Override
	public int countByExample(AccNeightCheckExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public int deleteByExample(AccNeightCheckExample example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(AccNeightCheckKey key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(AccNeightCheck record)  {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(AccNeightCheck record)  {
		return mapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int batchInsert(List<AccNeightCheck> records)
			 {
		for(AccNeightCheck record : records){
			mapper.insert(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchUpdate(List<AccNeightCheck> records)
			 {
		for(AccNeightCheck record : records){
			mapper.updateByPrimaryKeySelective(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchDelete(List<AccNeightCheck> records)
			 {
		for(AccNeightCheck record : records){
			mapper.deleteByPrimaryKey(record);
		}
		return records.size();
	}

	public void allowTract(String username){
		//查看夜对表是否有昨天记录
		AccNeightCheck accNeightCheck = new AccNeightCheck();
		accNeightCheck.setDate(CommonUtils.returnYesterdayDate());
		accNeightCheck.setUsername(username);
		AccNeightCheck selectAcc = mapper.selectByPrimaryKey(accNeightCheck);
		//夜对表为空 计算昨日可提现金额 加今日可提现金额 == 提现金额 修改状态为允许提现
		if(null==selectAcc){
			if(compareMoney(username)){
				updateTractStatus(username,"0");
			}
		}else{
			if(compareTodayMoney(username)){
				updateTractStatus(username,"0");
			}
		}
	}
	public boolean compareMoney(String username){
		//sum昨日佣金-sum昨日提现=昨日可提现金额
		BigDecimal yesterdayCommissionsMoney = countCommissionsMoney(CommonUtils.returnYesterdayDate(),username);
		BigDecimal yesterdayLogMoney = countLogMoney(CommonUtils.returnYesterdayDate(),username);
		BigDecimal yesterdaySubtract = yesterdayCommissionsMoney.subtract(yesterdayLogMoney);
		//sum今日佣金-sum今日提现=今日可提现金额
		BigDecimal todayCommissionsMoney = countCommissionsMoney(CommonUtils.returnYYMMDD(),username);
		BigDecimal todayLogMoney = countLogMoney(CommonUtils.returnYYMMDD(),username);
		BigDecimal todaySubtract = todayCommissionsMoney.subtract(todayLogMoney);
		//计算出的金额
		BigDecimal countMoney = yesterdaySubtract.add(todaySubtract);
		AccExtract accExtract = new AccExtract();
		accExtract.setUsername(username);
		AccExtract tractMoney = accExtractMapper.selectByPrimaryKey(accExtract);
		BigDecimal money = tractMoney.getMoney();
		return money.compareTo(countMoney)==0;
	}
	public boolean compareTodayMoney(String username){
		//sum今日佣金-sum今日提现=今日可提现金额
		BigDecimal todayCommissionsMoney = countCommissionsMoney(CommonUtils.returnYYMMDD(),username);
		BigDecimal todayLogMoney = countLogMoney(CommonUtils.returnYYMMDD(),username);
		BigDecimal todaySubtract = todayCommissionsMoney.subtract(todayLogMoney);
		//计算出的金额
		AccExtract accExtract = new AccExtract();
		accExtract.setUsername(username);
		AccExtract tractMoney = accExtractMapper.selectByPrimaryKey(accExtract);
		BigDecimal money = tractMoney.getMoney();
		return money.compareTo(todaySubtract)==0;
	}
	public void updateTractStatus(String username,String status){
		AccExtract accExtract = new AccExtract();
		accExtract.setUsername(username);
		accExtract.setStatus(status);
		accExtractMapper.updateByPrimaryKeySelective(accExtract);
	}
	public BigDecimal countCommissionsMoney(String date,String username){
		LogCommissionExample logCommissionExample = new LogCommissionExample();
		logCommissionExample.createCriteria().andDateEqualTo(date).andParentnameEqualTo(username);
		List<LogCommission> logCommissions = logCommissionMapper.selectByExample(logCommissionExample);
		BigDecimal money = new BigDecimal(0);
		if(CommonUtils.isNotEmpty(logCommissions)){
			for(LogCommission logCommission : logCommissions){
				money = money.add(logCommission.getMoney());
			}
		}
		return money;
	}

	public BigDecimal countLogMoney(String date,String username){
		LogMoneyExample logMoneyExample = new LogMoneyExample();
		logMoneyExample.createCriteria().andDateEqualTo(date).andParentnameEqualTo(username).andStatusEqualTo("1");
		List<LogMoney> logMonies = logMoneyMapper.selectByExample(logMoneyExample);
		BigDecimal money = new BigDecimal(0);
		if(CommonUtils.isNotEmpty(logMonies)){
			for(LogMoney logMoney : logMonies){
				money = money.add(logMoney.getExtractmoney());
			}
		}
		return money;
	}
	@Override
	public List<AccNeightCheck> selectByExample(AccNeightCheckExample example)
			 {
		return mapper.selectByExample(example);
	}

	@Override
	public AccNeightCheck selectByPrimaryKey(AccNeightCheckKey key)
			 {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public List<AccNeightCheck> findAll(List<AccNeightCheck> records) {
		if(records==null||records.size()<=0){
			return mapper.selectByExample(new AccNeightCheckExample());
		}
		List<AccNeightCheck> list = new ArrayList<AccNeightCheck>();
		for(AccNeightCheck record : records){
			AccNeightCheck result = mapper.selectByPrimaryKey(record);
			if(result!=null){
				list.add(result);
			}
		}
		return list;
	}

	@Override
	public int updateByExampleSelective(AccNeightCheck record, AccNeightCheckExample example)  {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(AccNeightCheck record, AccNeightCheckExample example) {
		return mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(AccNeightCheck record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AccNeightCheck record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int sumByExample(AccNeightCheckExample example) {
		return 0;
	}

	@Override
	public void deleteAll()  {
		mapper.deleteByExample(new AccNeightCheckExample());
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
	public AccNeightCheckExample getExample(AccNeightCheck record) {
		AccNeightCheckExample example = new AccNeightCheckExample();
		if(record!=null){
			Criteria criteria = example.createCriteria();
			if(record.getDate()!=null){
				criteria.andDateEqualTo(record.getDate());
			}
			if(record.getUsername()!=null){
				criteria.andUsernameEqualTo(record.getUsername());
			}
			if(record.getMoney()!=null){
				criteria.andMoneyEqualTo(record.getMoney());
			}

		}
		return example;
	}
}
