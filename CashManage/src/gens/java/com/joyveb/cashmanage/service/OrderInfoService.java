package com.joyveb.cashmanage.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import com.joyveb.cashmanage.entity.*;
import com.joyveb.cashmanage.utils.CommonUtils;
import com.joyveb.cashmanage.utils.IDGenerator;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyveb.cashmanage.entity.OrderInfoExample.Criteria;
import com.joyveb.cashmanage.mapper.OrderInfoMapper;
import com.joyveb.lbos.restful.common.CommonSqlMapper;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.util.ExportExcel;
import com.joyveb.lbos.restful.util.KeyExplainHandler;
import com.joyveb.lbos.restful.util.SqlMaker;

import static java.lang.System.currentTimeMillis;

@Slf4j
@Service
public class OrderInfoService implements ServiceInterface<OrderInfo, OrderInfoExample, OrderInfoKey>{

	@Resource
	private OrderInfoMapper mapper;
	private @Resource CommonSqlMapper common;
	@Resource(name = "logCommissionService")
	private LogCommissionService logCommissionService;
	@Resource(name = "accWhitelistParentService")
	private AccWhitelistParentService accWhitelistParentService;
	@Resource(name = "accExtractService")
	private AccExtractService accExtractService;
	@Resource(name = "whiteListService")
	private WhiteListService whiteListService;
	public HSSFWorkbook exprort(List<HashMap<String,Object>> list) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		try {
			List<OrderinfoExcel> list2 = new ArrayList<OrderinfoExcel>();
			OrderinfoExcel heji = new OrderinfoExcel();
			int sc = 0;
			BigDecimal ta = new BigDecimal(0);
			BigDecimal divide =new BigDecimal(100);
			if(null!=list&&list.size()!=0){
				int size = list.size();
				for(int i=0;i<size;i++){
					OrderinfoExcel transferExcel = new OrderinfoExcel();
					HashMap<String, Object> hashMap = list.get(i);
					if(null!=hashMap.get("days")){
						String ordertime = hashMap.get("days").toString();
						transferExcel.setOrdertime(DateFormatUtils.format(ymd.parse(ordertime),"yyyy-MM-dd"));
					}
					if(null!=hashMap.get("userid")){
						String fullname = hashMap.get("userid").toString();
							transferExcel.setFullname(fullname);
						}
					if(null!=hashMap.get("GAMENUM")){
						String orderamount = hashMap.get("GAMENUM").toString();
						transferExcel.setOrderamount(orderamount);
						sc += Integer.parseInt(orderamount);
					}
					if(null!=hashMap.get("TOTALAMOUNT")){
						String winamount = hashMap.get("TOTALAMOUNT").toString();
						BigDecimal wm = new BigDecimal(winamount);
						wm = wm.divide(divide);
						transferExcel.setWINAMOUNT(wm.toString());
						ta = ta.add(wm);
						
					}
					list2.add(transferExcel);
				}
				heji.setOrdertime("合计");
				heji.setOrderamount(sc+"");
				heji.setWINAMOUNT(ta.toString());
				list2.add(heji);
				ExportExcel<OrderinfoExcel> ex = new ExportExcel<OrderinfoExcel>();
				// 调用导出excel
				String[] title = {"交易日期","代销商","订单数量","订单总额(元)"};
				workbook = ex.exportExcel("订单总量信息",title, list2,"yyyy-MM-dd");
			}else{
				log.debug("导出内容为空！");
			}
		} catch (Exception e) {
			log.warn("AddressBookExport is error" + e);
		}
		return workbook;
	}
	//二级代销商下单 计算一级代销商佣金并记录 并更新用户提现表
	public void saveCommision(String username,Long totalamount1,Long freightprice){
		try {
			Long f = freightprice/100;
			Long valueOf = Long.valueOf(totalamount1);
			Double bb = valueOf / 100.00;
			double v3 = bb - f;
			WhiteListExample whiteListExample = new WhiteListExample();
			whiteListExample.createCriteria().andUsernameEqualTo(username);
			List<WhiteList> whiteLists = whiteListService.selectByExample(whiteListExample);
			if(CommonUtils.isNotEmpty(whiteLists)){
				WhiteList whiteList = whiteLists.get(0);
				LogCommission logCommission = new LogCommission();
				AccWhitelistParentExample accWhitelistParentExample = new AccWhitelistParentExample();
				accWhitelistParentExample.createCriteria().andUsernameEqualTo(whiteList.getParentname()).andStatusEqualTo("0");
				List<AccWhitelistParent> accWhitelistParents = accWhitelistParentService.selectByExample(accWhitelistParentExample);
				if(CommonUtils.isNotEmpty(accWhitelistParents)){
					AccWhitelistParent accWhitelistParent = accWhitelistParents.get(0);
					String commission = accWhitelistParent.getCommission();
					//Double money2 = money+Double.valueOf(whiteList.getCommission())*money/100;
					double v = (100.00 - Double.valueOf(whiteList.getCommission())) / 100.00;
					double orderM = v3 / v;//订单原始金额
					double v1 = orderM * (Double.valueOf(commission)-Double.valueOf(whiteList.getCommission())) / 100.00;
					logCommission.setMoney(BigDecimal.valueOf(v1));
					logCommission.setOrdertime(currentTimeMillis());
					logCommission.setSecondname(whiteList.getUsername());
					logCommission.setStatus("0");//下单成功
					logCommission.setUuid(IDGenerator.getInstance().generate());
					logCommission.setParentname(whiteList.getParentname());
					SimpleDateFormat yymmdd = new SimpleDateFormat("yyyyMMdd");
					logCommission.setDate(yymmdd.format(currentTimeMillis()));
					logCommissionService.insert(logCommission);
					AccExtractKey accExtractKey = new AccExtractKey();
					accExtractKey.setUsername(whiteList.getParentname());
					AccExtract accExtract1 = accExtractService.selectByPrimaryKey(accExtractKey);
					AccExtract accExtract = new AccExtract();
					accExtract.setUsername(whiteList.getParentname());
					accExtract.setStatus("1");
					if(null!=accExtract1){
						BigDecimal money1 = accExtract1.getMoney();
						double v2 = money1.doubleValue() + v1;
						accExtract.setMoney(BigDecimal.valueOf(v2));
						accExtractService.updateByPrimaryKey(accExtract);
					}else{
						accExtract.setMoney(BigDecimal.valueOf(v1));
						accExtractService.insert(accExtract);
					}
				}
			}
		}catch (Exception e){
			log.warn("用户下单存储一级代销商佣金错误",e);
		}
	}
	@Override
	public int countByExample(OrderInfoExample example) {
		return mapper.countByExample(example);
	}
	@Override
	public int deleteByExample(OrderInfoExample example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(OrderInfoKey key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(OrderInfo record)  {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(OrderInfo record)  {
		return mapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int batchInsert(List<OrderInfo> records)
			 {
		for(OrderInfo record : records){
			mapper.insert(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchUpdate(List<OrderInfo> records)
			 {
		for(OrderInfo record : records){
			mapper.updateByPrimaryKeySelective(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchDelete(List<OrderInfo> records)
			 {
		for(OrderInfo record : records){
			mapper.deleteByPrimaryKey(record);
		}
		return records.size();
	}

	@Override
	public List<OrderInfo> selectByExample(OrderInfoExample example)
			 {
		return mapper.selectByExample(example);
	}

	@Override
	public OrderInfo selectByPrimaryKey(OrderInfoKey key)
			 {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public List<OrderInfo> findAll(List<OrderInfo> records) {
		if(records==null||records.size()<=0){
			return mapper.selectByExample(new OrderInfoExample());
		}
		List<OrderInfo> list = new ArrayList<OrderInfo>();
		for(OrderInfo record : records){
			OrderInfo result = mapper.selectByPrimaryKey(record);
			if(result!=null){
				list.add(result);
			}
		}
		return list;
	}

	@Override
	public int updateByExampleSelective(OrderInfo record, OrderInfoExample example)  {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(OrderInfo record, OrderInfoExample example) {
		return mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderInfo record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(OrderInfo record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int sumByExample(OrderInfoExample example) {
		return 0;
	}

	@Override
	public void deleteAll()  {
		mapper.deleteByExample(new OrderInfoExample());
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
	public OrderInfoExample getExample(OrderInfo record) {
		OrderInfoExample example = new OrderInfoExample();
		if(record!=null){
			Criteria criteria = example.createCriteria();
			if(record.getOrderid()!=null){
				criteria.andOrderidEqualTo(record.getOrderid());
			}
			if(record.getOrderstatus()!=null){
				criteria.andOrderstatusEqualTo(record.getOrderstatus());
			}
			if(record.getDelivertime()!=null){
				criteria.andDelivertimeEqualTo(record.getDelivertime());
			}
			if(record.getFailreason()!=null){
				criteria.andFailreasonEqualTo(record.getFailreason());
			}
			if(record.getLogisticsid()!=null){
				criteria.andLogisticsidEqualTo(record.getLogisticsid());
			}
			if(record.getWaybillno()!=null){
				criteria.andWaybillnoEqualTo(record.getWaybillno());
			}
			if(record.getLogisticsstatus()!=null){
				criteria.andLogisticsstatusEqualTo(record.getLogisticsstatus());
			}
			if(record.getLogisticsremark()!=null){
				criteria.andLogisticsremarkEqualTo(record.getLogisticsremark());
			}
			if(record.getPaytime()!=null){
				criteria.andPaytimeEqualTo(record.getPaytime());
			}
			if(record.getUserid()!=null){
				criteria.andUseridEqualTo(record.getUserid());
			}
			if(record.getParentname()!=null){
				criteria.andParentnameEqualTo(record.getParentname());
			}
			if(record.getReceiverid()!=null){
				criteria.andReceiveridEqualTo(record.getReceiverid());
			}
			if(record.getOrdertime()!=null){
				criteria.andOrdertimeEqualTo(record.getOrdertime());
			}
			if(record.getTotalamount()!=null){
				criteria.andTotalamountEqualTo(record.getTotalamount());
			}
			if(record.getFreightprice()!=null){
				criteria.andFreightpriceEqualTo(record.getFreightprice());
			}
			if(record.getTaketime()!=null){
				criteria.andTaketimeEqualTo(record.getTaketime());
			}
		}
		return example;
	}
}
