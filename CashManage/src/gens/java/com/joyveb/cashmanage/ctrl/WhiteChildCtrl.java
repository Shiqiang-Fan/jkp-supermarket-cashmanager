package com.joyveb.cashmanage.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.joyveb.cashmanage.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyveb.lbos.restful.common.Constans;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.common.ListInfo;
import com.joyveb.lbos.restful.common.PageInfo;
import com.joyveb.lbos.restful.common.ReturnInfo;
import com.joyveb.lbos.restful.spring.FieldsMapperBean;
import com.joyveb.lbos.restful.spring.QueryMapperBean;
import com.joyveb.lbos.restful.spring.RequestJsonParam;
import com.joyveb.lbos.restful.util.KeyExplainHandler;
import  com.joyveb.cashmanage.service.WhiteChildService;
import com.joyveb.cashmanage.utils.IDGenerator;
import com.joyveb.cashmanage.web.InitParm;
import com.joyveb.cashmanage.entity.LogOperter;
import  com.joyveb.cashmanage.entity.WhiteChild;
import  com.joyveb.cashmanage.entity.WhiteChildKey;
import  com.joyveb.cashmanage.entity.WhiteChildExample;
import com.joyveb.cashmanage.entity.WhiteList;
import com.joyveb.cashmanage.entity.WhiteListKey;

@Slf4j
@Controller
@RequestMapping("/whiteChild")
public class WhiteChildCtrl {
	
	private @Resource WhiteChildService dbService;
	private @Resource InitParm initParm;
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo insert(@RequestBody WhiteChild info,HttpServletRequest req) {
		try {
			info.setUuid(IDGenerator.getInstance().generate());
			dbService.insert(info);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  WhiteChildCtrl insert error..",e);
			
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@RequestBody WhiteChild info,HttpServletRequest req) {
		try {
			dbService.updateByExample(info,dbService.getExample(info));
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  WhiteChildCtrl update error..",e);
			
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
			dc.setEntityClass(WhiteChild.class);
			dc.setKeyClass(WhiteChildKey.class);
			dc.setQmb(info);
			dc.setPageinfo(para);
			dc.setFmb(fmb);
			dc.setTalbeName(getTableName());
			totalCount = dbService.getCount(dc);
			list = dbService.getData(dc);
		} catch (Exception e) {
			log.warn("  WhiteChildCtrl get error..",e);
			
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
			List<WhiteChild> list = new ArrayList<WhiteChild>();
			for(String id :data){
				WhiteChild info = new WhiteChild();
				KeyExplainHandler.explainKey(id, info);
				list.add(info);
			}
			dbService.batchDelete( list);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  WhiteChildCtrl batchDelete error..",e);
			
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/batch",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo batchUpdate(@RequestBody WhiteChilds data,HttpServletRequest req) {
		try {
			dbService.batchUpdate( data);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  WhiteChildCtrl batchUpdate error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/batch",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo batchInsert(@RequestBody WhiteChilds data,HttpServletRequest req) {
		try {
			dbService.batchInsert( data);
			return ReturnInfo.Success;
		} catch (Exception e) {
		    log.warn("  WhiteChildCtrl batchInsert error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.GET)
	@ResponseBody
	public ListInfo<WhiteChild> get(@PathVariable String key,HttpServletRequest req) {
		int totalCount = 1;
		List<WhiteChild> list = new ArrayList<WhiteChild>();
		try {
			WhiteChildExample info = new WhiteChildExample();
			info.createCriteria().andParentidEqualTo(key);
			list.addAll(dbService.selectByExample(info));
		} catch (Exception e) {
			log.warn("  WhiteChildCtrl get by key error..",e);
		}
		return  new ListInfo<WhiteChild>(totalCount, list, 0, 1);
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.DELETE)
	@ResponseBody
	public ReturnInfo delete(@PathVariable String key,HttpServletRequest req) {
		try {
			WhiteChildKey info = new WhiteChildKey();
			info.setUuid(key);
			dbService.deleteByPrimaryKey(info);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  WhiteChildCtrl delete by key error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@PathVariable String key,@RequestBody WhiteChild info,HttpServletRequest req) {
		try {
			if(info!=null){
				KeyExplainHandler.explainKey(key, info);
				dbService.updateByPrimaryKeySelective(info);
			}
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  WhiteChildCtrl update by key error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/checkUsername/{key}",method=RequestMethod.GET)
	@ResponseBody
	public boolean checkUsername(@PathVariable String key,HttpServletRequest req) {
		List<WhiteChild> list = new ArrayList<WhiteChild>();
		WhiteList wlst=null;
		boolean mm=false;
		try {
			wlst=initParm.getWhiteList(key);
			if (wlst==null) {
				WhiteChildExample info = new WhiteChildExample();
				info.createCriteria().andUsernameEqualTo(key);
				list.addAll(dbService.selectByExample(info));
				if (CommonUtils.isEmpty(list)) {
					mm=true;
				}
			}
		} catch (Exception e) {
			log.warn("  WhiteChildCtrl checkUsername error..",e);
		}
		return  mm;
	}
	
	@RequestMapping(value="/Max/{key}",method=RequestMethod.GET)
	@ResponseBody
	public Object getMax(@PathVariable String key,HttpServletRequest req) {
		List<Object> maxNum=new ArrayList<Object>(); 
		
		try {
			String sql="SELECT Max(t_acc_whitechild.USERNAME) as maxnum "
					+ "FROM t_acc_whitechild "
					+ ";";
			List<HashMap<String, Object>> maxNumList=dbService.dosql(sql);
			int mm=Integer.parseInt((String) maxNumList.get(0).get("maxnum")) +1;
			 maxNum.add(mm);
		} catch (Exception e) {
			log.warn("  WhiteListCtrl getMax by key error..",e);
		}
		return maxNum;
	}
	
	@RequestMapping(value="/Reset/{key}",method=RequestMethod.GET)
	@ResponseBody
	public boolean Reset(@PathVariable String key,HttpServletRequest req) {
		try {
			String sql="update t_acc_whitechild set password='0000' where uuid ='"+key+"';";
			dbService.dosql(sql);
			return true;
		} catch (Exception e) {
			log.warn("  WhiteListCtrl delete by key error..",e);
		}
		return false;
	}	
	private String getControllerName(){
		return this.getClass().getSimpleName();
	}
	
	private String getTableName(){
		return "t_acc_whitechild";
	}
	
	@SuppressWarnings("serial")
	public static class WhiteChilds extends ArrayList<WhiteChild> {  
	    public WhiteChilds() { super(); }  
	}
}
