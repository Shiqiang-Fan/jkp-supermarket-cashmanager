package com.joyveb.cashmanage.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.common.ListInfo;
import com.joyveb.lbos.restful.common.PageInfo;
import com.joyveb.lbos.restful.common.ReturnInfo;
import com.joyveb.lbos.restful.spring.FieldsMapperBean;
import com.joyveb.lbos.restful.spring.QueryMapperBean;
import com.joyveb.lbos.restful.spring.RequestJsonParam;
import com.joyveb.lbos.restful.util.KeyExplainHandler;

import  com.joyveb.cashmanage.service.LogMoneyService;
import  com.joyveb.cashmanage.entity.LogMoney;
import  com.joyveb.cashmanage.entity.LogMoneyKey;

@Slf4j
@Controller
@RequestMapping("/logMoney")
public class LogMoneyCtrl {
	
	private @Resource LogMoneyService dbService;
	
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo insert(@RequestBody LogMoney info,HttpServletRequest req) {
		try {
			dbService.insert(info);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  LogMoneyCtrl insert error..",e);
			
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@RequestBody LogMoney info,HttpServletRequest req) {
		try {
			dbService.updateByExample(info,dbService.getExample(info));
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  LogMoneyCtrl update error..",e);
			
		}
		return ReturnInfo.Faild;
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public Object get(@RequestJsonParam(value = "query",required=false) QueryMapperBean info,
			@RequestJsonParam(value = "fields",required=false) FieldsMapperBean fmb,
			PageInfo para, HttpServletRequest req) {
		int totalCount = 0;
		List<HashMap<String,Object>> list = null;
		try {
			DbCondi dc = new DbCondi();
			dc.setEntityClass(LogMoney.class);
			dc.setKeyClass(LogMoneyKey.class);
			dc.setQmb(info);
			dc.setPageinfo(para);
			dc.setFmb(fmb);
			dc.setTalbeName(getTableName());
			totalCount = dbService.getCount(dc);
			list = dbService.getData(dc);
		} catch (Exception e) {
			log.warn("  LogMoneyCtrl get error..",e);
			
		}
		if(para.isPage()){
			return new ListInfo<HashMap<String,Object>>(totalCount, list, para);
		}else{
			return list;
		}
	}
	
	@RequestMapping(value="/batch/delete",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo batchDelete(@RequestBody List<String> data,HttpServletRequest req) {
		try {
			List<LogMoney> list = new ArrayList<LogMoney>();
			for(String id :data){
				LogMoney info = new LogMoney();
				KeyExplainHandler.explainKey(id, info);
				list.add(info);
			}
			dbService.batchDelete( list);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  LogMoneyCtrl batchDelete error..",e);
			
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/batch",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo batchUpdate(@RequestBody LogMoneys data,HttpServletRequest req) {
		try {
			dbService.batchUpdate(data);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  LogMoneyCtrl batchUpdate error..",e);
			
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/batch",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo batchInsert(@RequestBody LogMoneys data,HttpServletRequest req) {
		try {
			dbService.batchInsert( data);
			return ReturnInfo.Success;
		} catch (Exception e) {
		    log.warn("  LogMoneyCtrl batchInsert error..",e);
			
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.GET)
	@ResponseBody
	public ListInfo<LogMoney> get(@PathVariable String key,HttpServletRequest req) {
		int totalCount = 1;
		List<LogMoney> list = new ArrayList<LogMoney>();
		try {
			LogMoney info = new LogMoney();
			KeyExplainHandler.explainKey(key, info);
			list.add(dbService.selectByPrimaryKey(info));
		} catch (Exception e) {
			log.warn("  LogMoneyCtrl get by key error..",e);
		}
		return  new ListInfo<LogMoney>(totalCount, list, 0, 1);
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.DELETE)
	@ResponseBody
	public ReturnInfo delete(@PathVariable String key,HttpServletRequest req) {
		try {
			LogMoney info = new LogMoney();
			KeyExplainHandler.explainKey(key, info);
			dbService.deleteByPrimaryKey(info);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  LogMoneyCtrl delete by key error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@PathVariable String key,@RequestBody LogMoney info,HttpServletRequest req) {
		try {
			LogMoney oldPojo = null;
			if(info!=null){
				KeyExplainHandler.explainKey(key, info);
				oldPojo = dbService.selectByPrimaryKey(info);
				dbService.updateByPrimaryKey(info);
			}
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  LogMoneyCtrl update by key error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	private String getControllerName(){
		return this.getClass().getSimpleName();
	}
	
	private String getTableName(){
		return "t_log_money";
	}
	
	@SuppressWarnings("serial")
	public static class LogMoneys extends ArrayList<LogMoney> {  
	    public LogMoneys() { super(); }  
	}
}
