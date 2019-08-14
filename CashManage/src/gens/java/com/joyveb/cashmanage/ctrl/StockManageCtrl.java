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

import com.joyveb.cashmanage.entity.GameInfoKey;
import com.joyveb.cashmanage.entity.StockManage;
import com.joyveb.cashmanage.entity.StockManageKey;
import com.joyveb.cashmanage.service.GameInfoService;
import com.joyveb.cashmanage.service.StockManageService;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.common.ListInfo;
import com.joyveb.lbos.restful.common.PageInfo;
import com.joyveb.lbos.restful.common.ReturnInfo;
import com.joyveb.lbos.restful.spring.FieldsMapperBean;
import com.joyveb.lbos.restful.spring.QueryMapperBean;
import com.joyveb.lbos.restful.spring.RequestJsonParam;
import com.joyveb.lbos.restful.util.KeyExplainHandler;

@Slf4j
@Controller
@RequestMapping("/stockManage")
public class StockManageCtrl {
	
	private @Resource StockManageService dbService;
	private @Resource GameInfoService gameInfoService;
	
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo insert(@RequestBody StockManage info,HttpServletRequest req) {
		try {
			dbService.insert(info);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  StockManageCtrl insert error..",e);
			
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@RequestBody StockManage info,HttpServletRequest req) {
		try {
			dbService.updateByExample(info,dbService.getExample(info));
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  StockManageCtrl update error..",e);
			
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
			dc.setEntityClass(StockManage.class);
			dc.setKeyClass(StockManageKey.class);
			dc.setQmb(info);
			dc.setPageinfo(para);
			dc.setFmb(fmb);
			dc.setTalbeName(getTableName());
			totalCount = dbService.getCount(dc);
			list = dbService.getData(dc);
			for (int i = 0; i < list.size(); i++) {
				String gamecode=(String) list.get(i).get("gamecode");
				GameInfoKey gameInfotKey = new GameInfoKey();
				gameInfotKey.setGamecode(gamecode);
				String gamename=null;
				if(gameInfoService.selectByPrimaryKey(gameInfotKey)==null){
					list.get(i).put("gamename", " ");
				}else{
					gamename=gameInfoService.selectByPrimaryKey(gameInfotKey).getGamename();
					list.get(i).put("gamename", gamename);
				}
			}
		} catch (Exception e) {
			log.warn("  StockManageCtrl get error..",e);
			
		}
		if(para.isPage()){
			return new ListInfo<HashMap<String, Object>>(totalCount, list, para);
		}else{
			return list;
		}
	}
	
	@RequestMapping(value="/batch/delete",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo batchDelete(@RequestBody List<String> data,HttpServletRequest req) {
		try {
			List<StockManage> list = new ArrayList<StockManage>();
			for(String id :data){
				StockManage info = new StockManage();
				KeyExplainHandler.explainKey(id, info);
				list.add(info);
			}
			dbService.batchDelete( list);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  StockManageCtrl batchDelete error..",e);
			
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/batch",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo batchUpdate(@RequestBody StockManages data,HttpServletRequest req) {
		try {
			dbService.batchUpdate( data);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  StockManageCtrl batchUpdate error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/batch",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo batchInsert(@RequestBody StockManages data,HttpServletRequest req) {
		try {
			dbService.batchInsert( data);
			return ReturnInfo.Success;
		} catch (Exception e) {
		    log.warn("  StockManageCtrl batchInsert error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.GET)
	@ResponseBody
	public ListInfo<StockManage> get(@PathVariable String key,HttpServletRequest req) {
		int totalCount = 1;
		List<StockManage> list = new ArrayList<StockManage>();
		try {
			StockManage info = new StockManage();
			KeyExplainHandler.explainKey(key, info);
			list.add(dbService.selectByPrimaryKey(info));
		} catch (Exception e) {
			log.warn("  StockManageCtrl get by key error..",e);
		}
		return  new ListInfo<StockManage>(totalCount, list, 0, 1);
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.DELETE)
	@ResponseBody
	public ReturnInfo delete(@PathVariable String key,HttpServletRequest req) {
		try {
			StockManage info = new StockManage();
			KeyExplainHandler.explainKey(key, info);
			dbService.deleteByPrimaryKey(info);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  StockManageCtrl delete by key error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@PathVariable String key,@RequestBody StockManage info,HttpServletRequest req) {
		try {
			if(info!=null){
				KeyExplainHandler.explainKey(key, info);
				dbService.updateByPrimaryKey( info);
			}
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  StockManageCtrl update by key error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	private String getControllerName(){
		return this.getClass().getSimpleName();
	}
	
	private String getTableName(){
		return "t_stock_manage";
	}
	
	@SuppressWarnings("serial")
	public static class StockManages extends ArrayList<StockManage> {  
	    public StockManages() { super(); }  
	}
}
