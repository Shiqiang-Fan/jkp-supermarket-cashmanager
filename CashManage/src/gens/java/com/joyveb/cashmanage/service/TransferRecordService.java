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

import com.joyveb.cashmanage.entity.TransferExcel;
import com.joyveb.cashmanage.entity.TransferRecord;
import com.joyveb.cashmanage.entity.TransferRecordExample;
import com.joyveb.cashmanage.entity.TransferRecordExample.Criteria;
import com.joyveb.cashmanage.entity.TransferRecordKey;
import com.joyveb.cashmanage.mapper.TransferRecordMapper;
import com.joyveb.lbos.restful.common.CommonSqlMapper;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.util.ExportExcel;
import com.joyveb.lbos.restful.util.KeyExplainHandler;
import com.joyveb.lbos.restful.util.SqlMaker;
@Slf4j
@Service
public class TransferRecordService implements ServiceInterface<TransferRecord, TransferRecordExample, TransferRecordKey>{

	@Resource
	private TransferRecordMapper mapper;
	private @Resource CommonSqlMapper common;
	
	public HSSFWorkbook exprort(List<HashMap<String,Object>> list) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		try {
			List<TransferExcel> list2 = new ArrayList<TransferExcel>();
			TransferExcel heji = new TransferExcel();
			int sc = 0;
			BigDecimal ft = new BigDecimal(0);
			BigDecimal ta = new BigDecimal(0);
			if(null!=list&&list.size()!=0){
				int size = list.size();
				for(int i=0;i<size;i++){
					TransferExcel transferExcel = new TransferExcel();
					HashMap<String, Object> hashMap = list.get(i);
					if(null!=hashMap.get("transfertime")){
						String transfertime = hashMap.get("transfertime").toString();
						transferExcel.setTransfertime(DateFormatUtils.format(ymd.parse(transfertime),"yyyy-MM-dd"));
					}
					if(null!=hashMap.get("transfertype")){
						String transfertype = hashMap.get("transfertype").toString();
						if(transfertype.equals("1")){
							transferExcel.setTransfertype("支付宝");
						}
						if(transfertype.equals("2")){
							transferExcel.setTransfertype("微信");
						}
						if(transfertype.equals("3")){
							transferExcel.setTransfertype("银行");
						}
					}
					if(null!=hashMap.get("count")){
						String count = hashMap.get("count").toString();
						transferExcel.setCount(count);
						sc += Integer.parseInt(count);
					}
					if(null!=hashMap.get("transferamount")){
						String transferamount = hashMap.get("transferamount").toString();
						transferExcel.setTransferamount(transferamount);
						BigDecimal re = new BigDecimal(transferamount);
						ta = ta.add(re);
					}
					if(null!=hashMap.get("factorage")){
						String factorage = hashMap.get("factorage").toString();
						transferExcel.setFactorage(factorage);
						BigDecimal ao = new BigDecimal(factorage);
						ft = ft.add(ao);
					}
					if(null!=hashMap.get("xiane")){
						String xiane = hashMap.get("xiane").toString();
						transferExcel.setXiane(xiane);
					}
					list2.add(transferExcel);
				}
				heji.setTransfertime("合计");
				heji.setCount(sc+"");
				heji.setTransferamount(ta.toString());
				heji.setFactorage(ft+"");
				list2.add(heji);
				ExportExcel<TransferExcel> ex = new ExportExcel<TransferExcel>();
				// 调用导出excel
				String[] title = {"交易日期","转账渠道","转账笔数","转账总额(元)","手续费","单日转账限额"};
				workbook = ex.exportExcel("财务转账信息",title, list2,"yyyy-MM-dd");
			}else{
				log.debug("导出内容为空！");
			}
		} catch (Exception e) {
			log.warn("AddressBookExport is error" + e);
		}
		return workbook;
	}
	@Override
	public int countByExample(TransferRecordExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public int deleteByExample(TransferRecordExample example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(TransferRecordKey key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(TransferRecord record)  {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(TransferRecord record)  {
		return mapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int batchInsert(List<TransferRecord> records)
			 {
		for(TransferRecord record : records){
			mapper.insert(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchUpdate(List<TransferRecord> records)
			 {
		for(TransferRecord record : records){
			mapper.updateByPrimaryKeySelective(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchDelete(List<TransferRecord> records)
			 {
		for(TransferRecord record : records){
			mapper.deleteByPrimaryKey(record);
		}
		return records.size();
	}

	@Override
	public List<TransferRecord> selectByExample(TransferRecordExample example)
			 {
		return mapper.selectByExample(example);
	}

	@Override
	public TransferRecord selectByPrimaryKey(TransferRecordKey key)
			 {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public List<TransferRecord> findAll(List<TransferRecord> records) {
		if(records==null||records.size()<=0){
			return mapper.selectByExample(new TransferRecordExample());
		}
		List<TransferRecord> list = new ArrayList<TransferRecord>();
		for(TransferRecord record : records){
			TransferRecord result = mapper.selectByPrimaryKey(record);
			if(result!=null){
				list.add(result);
			}
		}
		return list;
	}

	@Override
	public int updateByExampleSelective(TransferRecord record, TransferRecordExample example)  {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(TransferRecord record, TransferRecordExample example) {
		return mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(TransferRecord record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TransferRecord record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int sumByExample(TransferRecordExample example) {
		return 0;
	}

	@Override
	public void deleteAll()  {
		mapper.deleteByExample(new TransferRecordExample());
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
	public TransferRecordExample getExample(TransferRecord record) {
		TransferRecordExample example = new TransferRecordExample();
		if(record!=null){
			Criteria criteria = example.createCriteria();
							if(record.getOuttradenum()!=null){
				criteria.andOuttradenumEqualTo(record.getOuttradenum());
				}
				if(record.getTransferout()!=null){
				criteria.andTransferoutEqualTo(record.getTransferout());
				}
				if(record.getTransferin()!=null){
				criteria.andTransferinEqualTo(record.getTransferin());
				}
				if(record.getTransferamount()!=null){
				criteria.andTransferamountEqualTo(record.getTransferamount());
				}
				if(record.getFactorage()!=null){
				criteria.andFactorageEqualTo(record.getFactorage());
				}
				if(record.getStatus()!=null){
				criteria.andStatusEqualTo(record.getStatus());
				}
				if(record.getFailreason()!=null){
				criteria.andFailreasonEqualTo(record.getFailreason());
				}
				if(record.getTradenum()!=null){
				criteria.andTradenumEqualTo(record.getTradenum());
				}
				if(record.getTransfertime()!=null){
				criteria.andTransfertimeEqualTo(record.getTransfertime());
				}

		}
		return example;
	}
}
