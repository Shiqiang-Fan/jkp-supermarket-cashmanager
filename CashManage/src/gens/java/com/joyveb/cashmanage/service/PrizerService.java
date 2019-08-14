package com.joyveb.cashmanage.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyveb.cashmanage.entity.Prizer;
import com.joyveb.cashmanage.entity.PrizerExample;
import com.joyveb.cashmanage.entity.PrizerExample.Criteria;
import com.joyveb.cashmanage.entity.PrizerExcel;
import com.joyveb.cashmanage.entity.PrizerKey;
import com.joyveb.cashmanage.mapper.PrizerMapper;
import com.joyveb.lbos.restful.common.CommonSqlMapper;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.util.ExportExcel;
import com.joyveb.lbos.restful.util.KeyExplainHandler;
import com.joyveb.lbos.restful.util.SqlMaker;
@Slf4j
@Service
public class PrizerService implements ServiceInterface<Prizer, PrizerExample, PrizerKey>{

	@Resource
	private PrizerMapper mapper;
	private @Resource CommonSqlMapper common;
	
	
	public HSSFWorkbook exprort(List<HashMap<String,Object>> list) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		try {
			List<PrizerExcel> list2 = new ArrayList<PrizerExcel>();
			PrizerExcel heji = new PrizerExcel();
			int sc = 0;
			BigDecimal am =new BigDecimal(0);
			BigDecimal divide =new BigDecimal(100);
			if(null!=list&&list.size()!=0){
				int size = list.size();
				for(int i=0;i<size;i++){
					PrizerExcel prizerExcel = new PrizerExcel();
					HashMap<String, Object> hashMap = list.get(i);
					if(null!=hashMap.get("requesttime")){
						String requesttime = hashMap.get("requesttime").toString();
						prizerExcel.setRequesttime(DateFormatUtils.format(ymd.parse(requesttime),"yyyy-MM-dd"));
					}
					if(null!=hashMap.get("count")){
						String count = hashMap.get("count").toString();
						prizerExcel.setCount(count);
						sc += Integer.parseInt(count);
					}
					if(null!=hashMap.get("amount")){
						String amount = hashMap.get("amount").toString();
						BigDecimal bignum1 =new BigDecimal(amount);  
						bignum1 = bignum1.divide(divide);
						prizerExcel.setAmount(bignum1.toString());
						am =bignum1.add(am);
					}
					list2.add(prizerExcel);
				}
				
				heji.setRequesttime("合计");
				heji.setAmount(am+"");
				heji.setCount(sc+"");
				list2.add(heji);
				ExportExcel<PrizerExcel> ex = new ExportExcel<PrizerExcel>();
				// 调用导出excel
				String[] title = {"交易日期","兑奖笔数","兑奖金额(元)"};
				workbook = ex.exportExcel("财务兑奖信息",title, list2,"yyyy-MM-dd");
			}else{
				log.debug("导出内容为空！");
			}
		} catch (Exception e) {
			log.warn("AddressBookExport is error" + e);
		}
		return workbook;
	}
	
	@Override
	public int countByExample(PrizerExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public int deleteByExample(PrizerExample example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(PrizerKey key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(Prizer record)  {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(Prizer record)  {
		return mapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int batchInsert(List<Prizer> records)
			 {
		for(Prizer record : records){
			mapper.insert(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchUpdate(List<Prizer> records)
			 {
		for(Prizer record : records){
			mapper.updateByPrimaryKeySelective(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchDelete(List<Prizer> records)
			 {
		for(Prizer record : records){
			mapper.deleteByPrimaryKey(record);
		}
		return records.size();
	}

	@Override
	public List<Prizer> selectByExample(PrizerExample example)
			 {
		return mapper.selectByExample(example);
	}

	@Override
	public Prizer selectByPrimaryKey(PrizerKey key)
			 {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public List<Prizer> findAll(List<Prizer> records) {
		if(records==null||records.size()<=0){
			return mapper.selectByExample(new PrizerExample());
		}
		List<Prizer> list = new ArrayList<Prizer>();
		for(Prizer record : records){
			Prizer result = mapper.selectByPrimaryKey(record);
			if(result!=null){
				list.add(result);
			}
		}
		return list;
	}

	@Override
	public int updateByExampleSelective(Prizer record, PrizerExample example)  {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(Prizer record, PrizerExample example) {
		return mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(Prizer record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Prizer record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int sumByExample(PrizerExample example) {
		return 0;
	}

	@Override
	public void deleteAll()  {
		mapper.deleteByExample(new PrizerExample());
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
	public PrizerExample getExample(Prizer record) {
		PrizerExample example = new PrizerExample();
		if(record!=null){
			Criteria criteria = example.createCriteria();
			if(record.getMessageid()!=null){
				criteria.andMessageidEqualTo(record.getMessageid());
			}
			if(record.getUserid()!=null){
				criteria.andUseridEqualTo(record.getUserid());
			}
			if(record.getParentname()!=null){
				criteria.andParentnameEqualTo(record.getParentname());
			}
			if(record.getDeviceid()!=null){
				criteria.andDeviceidEqualTo(record.getDeviceid());
			}
			if(record.getBarcode()!=null){
				criteria.andBarcodeEqualTo(record.getBarcode());
			}
			if(record.getAmountsign()!=null){
				criteria.andAmountsignEqualTo(record.getAmountsign());
			}
			if(record.getTransferstatus()!=null){
				criteria.andTransferstatusEqualTo(record.getTransferstatus());
			}
			if(record.getWinamount()!=null){
				criteria.andWinamountEqualTo(record.getWinamount());
			}
			if(record.getRequesttime()!=null){
				criteria.andRequesttimeEqualTo(record.getRequesttime());
			}
			if(record.getTransfertime()!=null){
				criteria.andTransfertimeEqualTo(record.getTransfertime());
			}

		}
		return example;
	}
}
