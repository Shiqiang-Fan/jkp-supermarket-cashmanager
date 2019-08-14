package com.joyveb.cashmanage.ctrl;

import com.joyveb.cashmanage.entity.AccWhitelistParent;
import com.joyveb.cashmanage.entity.AccWhitelistParentKey;
import com.joyveb.cashmanage.entity.WhiteList;
import com.joyveb.cashmanage.entity.WhiteListExample;
import com.joyveb.cashmanage.service.AccWhitelistParentService;
import com.joyveb.cashmanage.service.WhiteListService;
import com.joyveb.cashmanage.utils.CommonUtils;
import com.joyveb.cashmanage.web.InitParm;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.common.ListInfo;
import com.joyveb.lbos.restful.common.PageInfo;
import com.joyveb.lbos.restful.common.ReturnInfo;
import com.joyveb.lbos.restful.spring.FieldsMapperBean;
import com.joyveb.lbos.restful.spring.QueryMapperBean;
import com.joyveb.lbos.restful.spring.RequestJsonParam;
import com.joyveb.lbos.restful.util.KeyExplainHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/accWhitelistParent")
public class AccWhitelistParentCtrl {
	
	private @Resource AccWhitelistParentService dbService;
	private @Resource WhiteListService whiteListService;
	@Resource(name="initParm")
	private InitParm initParm;
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo insert(@RequestBody AccWhitelistParent info,HttpServletRequest req) {
		try {
			dbService.insert(info);
			return ReturnInfo.Success;
		}catch (DuplicateKeyException e1){
			log.info(info.getUsername()+"重复");
			ReturnInfo returnInfo = new ReturnInfo();
			returnInfo.setDescription("用户名"+info.getUsername()+"重复！");
			returnInfo.setSuccess(false);
			return returnInfo;
		}catch (Exception e) {
			log.warn("  AccWhitelistParentCtrl insert error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@RequestBody AccWhitelistParent info,HttpServletRequest req) {
		try {
			KeyExplainHandler.explainKey(info.getUsername(), info);
			dbService.updateByExample(info,dbService.getExample(info));
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  AccWhitelistParentCtrl update error..",e);
			
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
			dc.setEntityClass(AccWhitelistParent.class);
			dc.setKeyClass(AccWhitelistParentKey.class);
			dc.setQmb(info);
			dc.setPageinfo(para);
			dc.setFmb(fmb);
			dc.setTalbeName(getTableName());
			totalCount = dbService.getCount(dc);
			list = dbService.getData(dc);
		} catch (Exception e) {
			log.warn("  AccWhitelistParentCtrl get error..",e);
			
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
			List<AccWhitelistParent> list = new ArrayList<AccWhitelistParent>();
			for(String id :data){
				AccWhitelistParent info = new AccWhitelistParent();
				KeyExplainHandler.explainKey(id, info);
				list.add(info);
			}
			dbService.batchDelete( list);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  AccWhitelistParentCtrl batchDelete error..",e);
			
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/batch",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo batchUpdate(@RequestBody AccWhitelistParents data,HttpServletRequest req) {
		try {
			dbService.batchUpdate(data);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  AccWhitelistParentCtrl batchUpdate error..",e);
			
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/batch",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo batchInsert(@RequestBody AccWhitelistParents data,HttpServletRequest req) {
		try {
			dbService.batchInsert( data);
			return ReturnInfo.Success;
		} catch (Exception e) {
		    log.warn("  AccWhitelistParentCtrl batchInsert error..",e);
			
		}
		return ReturnInfo.Faild;
	}

	@RequestMapping(value="/equalComm/{username}",method=RequestMethod.GET)
	@ResponseBody
	public String getEqualComm(@PathVariable String username) {
		String comm = "";
		try {
			AccWhitelistParentKey accWhitelistParentKey = new AccWhitelistParent();
			accWhitelistParentKey.setUsername(username);
			AccWhitelistParent accWhitelistParent = dbService.selectByPrimaryKey(accWhitelistParentKey);
			comm = accWhitelistParent.getCommission();
		} catch (Exception e) {
			log.warn("  AccWhitelistParentCtrl get by key error..",e);
		}
		return  comm;
	}

	@RequestMapping(value="/{key}",method=RequestMethod.GET)
	@ResponseBody
	public ListInfo<AccWhitelistParent> get(@PathVariable String key,HttpServletRequest req) {
		int totalCount = 1;
		List<AccWhitelistParent> list = new ArrayList<AccWhitelistParent>();
		try {
			AccWhitelistParent info = new AccWhitelistParent();
			KeyExplainHandler.explainKey(key, info);
			list.add(dbService.selectByPrimaryKey(info));
		} catch (Exception e) {
			log.warn("  AccWhitelistParentCtrl get by key error..",e);
		}
		return  new ListInfo<AccWhitelistParent>(totalCount, list, 0, 1);
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.DELETE)
	@ResponseBody
	public ReturnInfo delete(@PathVariable String key,HttpServletRequest req) {
		try {
			AccWhitelistParent info = new AccWhitelistParent();
			KeyExplainHandler.explainKey(key, info);
			dbService.deleteByPrimaryKey(info);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  AccWhitelistParentCtrl delete by key error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@PathVariable String key,@RequestBody AccWhitelistParent info,HttpServletRequest req) {
		try {
			if(info!=null){
				KeyExplainHandler.explainKey(key, info);
				WhiteListExample whiteListExample = new WhiteListExample();
				whiteListExample.createCriteria().andParentnameEqualTo(info.getUsername());
				List<WhiteList> whiteLists = whiteListService.selectByExample(whiteListExample);
				if(CommonUtils.isNotEmpty(whiteLists)){
					for(WhiteList whiteList : whiteLists){
						whiteList.setStatus(info.getStatus());
						whiteListService.updateByPrimaryKey(whiteList);
					}
				}
				dbService.updateByPrimaryKey(info);
			}
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  AccWhitelistParentCtrl update by key error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	private String getControllerName(){
		return this.getClass().getSimpleName();
	}
	
	private String getTableName(){
		return "t_acc_whitelist_parent";
	}
	
	@SuppressWarnings("serial")
	public static class AccWhitelistParents extends ArrayList<AccWhitelistParent> {  
	    public AccWhitelistParents() { super(); }  
	}
}
