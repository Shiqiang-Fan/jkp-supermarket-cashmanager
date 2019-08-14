package com.joyveb.cashmanage.ctrl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyveb.cashmanage.entity.TransferRecord;
import com.joyveb.cashmanage.entity.TransferRecordKey;
import com.joyveb.cashmanage.service.TransferRecordService;
import com.joyveb.cashmanage.web.InitParm;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.common.ListInfo;
import com.joyveb.lbos.restful.common.PageInfo;
import com.joyveb.lbos.restful.common.ReturnInfo;
import com.joyveb.lbos.restful.spring.FieldsMapperBean;
import com.joyveb.lbos.restful.spring.QueryMapperBean;
import com.joyveb.lbos.restful.spring.QueryMapperBean.ConditionBean;
import com.joyveb.lbos.restful.spring.QueryMapperBean.EqualBean;
import com.joyveb.lbos.restful.spring.QueryMapperBean.LikeBean;
import com.joyveb.lbos.restful.spring.RequestJsonParam;
import com.joyveb.lbos.restful.util.KeyExplainHandler;

@Slf4j
@Controller
@RequestMapping("/transferRecord")
public class TransferRecordCtrl {
	
	private @Resource TransferRecordService dbService;
	@Resource(name="initParm")
	private InitParm initParm;
	private List<HashMap<String,Object>> list2 = new ArrayList<HashMap<String,Object>>();
	
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo insert(@RequestBody TransferRecord info,HttpServletRequest req) {
		try {
			dbService.insert(info);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  TransferRecordCtrl insert error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@RequestBody TransferRecord info,HttpServletRequest req) {
		try {
			dbService.updateByExample(info,dbService.getExample(info));
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  TransferRecordCtrl update error..",e);
			
		}
		return ReturnInfo.Faild;
	}
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public Object get(@RequestJsonParam(value = "query",required=false) QueryMapperBean info,
			@RequestJsonParam(value = "fields",required=false) FieldsMapperBean fmb,
			PageInfo para, HttpServletRequest req) {
		int totalCount = 0;
		List<HashMap<String,Object>> list = null;
		try {
			DbCondi dc = new DbCondi();
			dc.setEntityClass(TransferRecord.class);
			dc.setKeyClass(TransferRecordKey.class);
			dc.setQmb(info);
			dc.setPageinfo(para);
			dc.setFmb(fmb);
			dc.setTalbeName(getTableName());
			totalCount = dbService.getCount(dc);
			list = dbService.getData(dc);
		} catch (Exception e) {
			log.warn("  TransferRecordCtrl get error..",e);
			
		}
		if(para.isPage()){
			return new ListInfo<HashMap<String, Object>>(totalCount, list, para);
		}else{
			return list;
		}
	}
	
	@RequestMapping(value = "/bb", method = RequestMethod.GET)
	@ResponseBody
	public Object getBB(@RequestJsonParam(value = "query",required=false) QueryMapperBean info,
			@RequestJsonParam(value = "fields",required=false) FieldsMapperBean fmb,
			PageInfo para, HttpServletRequest req) {
		list2.clear();
		SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
		List<HashMap<String,Object>> list = null;
		String sql = "";
		String eq = "";
		String lk = "";
		String co = "";
		if(null!=info){
			List<EqualBean> equals = info.getEquals();
			List<LikeBean> likes = info.getLikes();
			List<ConditionBean> conditions = info.getConditions();
			if(null!=equals&&equals.size()!=0){
				eq = " and transfertype = "+equals.get(0).getValue();
			}
			if(null!=likes&&likes.size()!=0){
				lk = " and date_format(transfertime, '%Y-%m-%d') like '"+likes.get(0).getRegex()+"'";
			}
			if(null!=conditions&&conditions.size()!=0){
				String a = conditions.get(0).getValue().toString();
				String b = conditions.get(1).getValue().toString();
				try {
					co = " and date_format(transfertime, '%Y-%m-%d') between '"+ymd.format(new Date(Long.parseLong(a)))+"' and '"+ymd.format(new Date(Long.parseLong(b)))+"'";
				} catch (Exception e) {
					log.warn("执行sql异常",e);
				}
			}
		}
		
		try {
			if(null==para.getLimit()||null==para.getSkip()){
				sql = "select transfertime,transfertype,count(transfertime) count, sum(transferamount) transferamount,sum(factorage) factorage from t_acc_transferrecord where 1=1 and status='1' "+eq+lk+co+" group by date_format(transfertime,'%y%m%d') desc limit 0,10;";
			}else{
				sql = "select transfertime,transfertype,count(transfertime) count, sum(transferamount) transferamount,sum(factorage) factorage from t_acc_transferrecord where 1=1 and status='1' "+eq+lk+co+" group by date_format(transfertime,'%y%m%d') desc limit "+para.getSkip()+","+para.getLimit();
			}
			list = dbService.dosql(sql);
			long alipay = initParm.getLongDbp("alipay.quota", 100000);
			long wechat = initParm.getLongDbp("wechat.quota", 100000);
			BigDecimal ali = BigDecimal.valueOf(alipay);
			BigDecimal chat = BigDecimal.valueOf(wechat);
			if(null!=list&&list.size()!=0){
				int size = list.size();
				for(int i=0;i<size;i++){
					HashMap<String, Object> hashMap = list.get(i);
					Object transfertype = hashMap.get("transfertype");
					if(null!=hashMap.get("transferamount")){
						BigDecimal transferamount = (BigDecimal) hashMap.get("transferamount");
						if(null!=transfertype&&transfertype.equals("1")){
							hashMap.put("xiane", ali.subtract(transferamount));
						}
						if(null!=transfertype&&transfertype.equals("2")){
							hashMap.put("xiane", chat.subtract(transferamount));
						}
					}
					
					list2.add(hashMap);
				}
			}
		} catch (Exception e) {
			log.warn("  PrizerCtrl get error..",e);
		}
		if(para.isPage()){
			return new ListInfo<HashMap<String, Object>>(list2.size(), list2, para);
		}else{
			return list2;
		}
	}
	
	@RequestMapping(value="/batch/delete",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo batchDelete(@RequestBody List<String> data,HttpServletRequest req) {
		try {
			List<TransferRecord> list = new ArrayList<TransferRecord>();
			for(String id :data){
				TransferRecord info = new TransferRecord();
				KeyExplainHandler.explainKey(id, info);
				list.add(info);
			}
			dbService.batchDelete( list);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  TransferRecordCtrl batchDelete error..",e);
			
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/batch",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo batchUpdate(@RequestBody TransferRecords data,HttpServletRequest req) {
		try {
			dbService.batchUpdate( data);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  TransferRecordCtrl batchUpdate error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/batch",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo batchInsert(@RequestBody TransferRecords data,HttpServletRequest req) {
		try {
			dbService.batchInsert( data);
			return ReturnInfo.Success;
		} catch (Exception e) {
		    log.warn("  TransferRecordCtrl batchInsert error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.GET)
	@ResponseBody
	public ListInfo<TransferRecord> get(@PathVariable String key,HttpServletRequest req) {
		int totalCount = 1;
		List<TransferRecord> list = new ArrayList<TransferRecord>();
		try {
			TransferRecord info = new TransferRecord();
			KeyExplainHandler.explainKey(key, info);
			list.add(dbService.selectByPrimaryKey(info));
		} catch (Exception e) {
			log.warn("  TransferRecordCtrl get by key error..",e);
		}
		return  new ListInfo<TransferRecord>(totalCount, list, 0, 1);
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.DELETE)
	@ResponseBody
	public ReturnInfo delete(@PathVariable String key,HttpServletRequest req) {
		try {
			TransferRecord info = new TransferRecord();
			KeyExplainHandler.explainKey(key, info);
			dbService.deleteByPrimaryKey(info);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  TransferRecordCtrl delete by key error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@PathVariable String key,@RequestBody TransferRecord info,HttpServletRequest req) {
		try {
			if(info!=null){
				KeyExplainHandler.explainKey(key, info);
				dbService.updateByPrimaryKey( info);
			}
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  TransferRecordCtrl update by key error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/transferexport",method=RequestMethod.GET)
	public Object exp(HttpServletRequest req,HttpServletResponse rep){
		String agents = req.getHeader("user-agent");
		String filename = "";
		try {
			SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
			if(agents.contains("Firefox")){
				filename = base64EncodeFileName("财务转账报表"+ymd.format(new Date(System.currentTimeMillis()))+".xls");
			}else{
				filename = URLEncoder.encode("财务转账报表"+ymd.format(new Date(System.currentTimeMillis()))+".xls", "utf-8");
			}
		} catch (UnsupportedEncodingException e1) {
			log.warn("导出财务转账报表错误",e1);
		}
		
		HSSFWorkbook workbook = dbService.exprort(list2);
		rep.setCharacterEncoding("utf-8");
		rep.setContentType("multipart/form-data");
		rep.setHeader("Content-Disposition",
				"attachment;fileName="+filename);
		try {
			OutputStream outputStream = rep.getOutputStream();
			workbook.write(outputStream);
			outputStream.close();
		} catch (IOException e) {
			log.warn("IO error",e);
			return ReturnInfo.Faild;
		}
		return ReturnInfo.Faild;
		
	}
	public static String base64EncodeFileName(String fileName) {
		Base64 base64 = new Base64();
		try {
			return "=?UTF-8?B?"
					+ new String(base64.encode(fileName.getBytes("UTF-8")))
					+ "?=";
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	private String getControllerName(){
		return this.getClass().getSimpleName();
	}
	
	private String getTableName(){
		return "t_acc_transferrecord";
	}
	
	@SuppressWarnings("serial")
	public static class TransferRecords extends ArrayList<TransferRecord> {  
	    public TransferRecords() { super(); }  
	}
}
