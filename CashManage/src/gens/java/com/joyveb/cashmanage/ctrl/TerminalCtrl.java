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

import com.joyveb.cashmanage.common.PrizeSessionsCache;
import com.joyveb.cashmanage.common.Session;
import com.joyveb.cashmanage.entity.Terminal;
import com.joyveb.cashmanage.entity.TerminalKey;
import com.joyveb.cashmanage.service.TerminalService;
import com.joyveb.cashmanage.web.InitParm;
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
@RequestMapping("/terminal")
public class TerminalCtrl {
	
	private @Resource TerminalService dbService;
	private @Resource InitParm initParm;
	private @Resource PrizeSessionsCache psc;
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo insert(@RequestBody Terminal info,HttpServletRequest req) {
		try {
			dbService.insert(info);
			initParm.initTerminal();
			psc.reload();
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  TerminalCtrl insert error..",e);
			
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@RequestBody Terminal info,HttpServletRequest req) {
		try {
			dbService.updateByExample(info,dbService.getExample(info));
			initParm.initTerminal();
			psc.reload();
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  TerminalCtrl update error..",e);
			
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
			dc.setEntityClass(Terminal.class);
			dc.setKeyClass(TerminalKey.class);
			dc.setQmb(info);
			dc.setPageinfo(para);
			dc.setFmb(fmb);
			dc.setTalbeName(getTableName());
			totalCount = dbService.getCount(dc);
			list = dbService.getData(dc);
		} catch (Exception e) {
			log.warn("  TerminalCtrl get error..",e);
			
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
			List<Terminal> list = new ArrayList<Terminal>();
			for(String id :data){
				Terminal info = new Terminal();
				KeyExplainHandler.explainKey(id, info);
				list.add(info);
			}
			dbService.batchDelete(list);
			initParm.initTerminal();
			psc.reload();
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  TerminalCtrl batchDelete error..",e);
			
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/batch",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo batchUpdate(@RequestBody Terminals data,HttpServletRequest req) {
		try {
			dbService.batchUpdate( data);
			initParm.initTerminal();
			psc.reload();
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  TerminalCtrl batchUpdate error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/batch",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo batchInsert(@RequestBody Terminals data,HttpServletRequest req) {
		try {
			dbService.batchInsert( data);
			initParm.initTerminal();
			psc.reload();
			return ReturnInfo.Success;
		} catch (Exception e) {
		    log.warn("  TerminalCtrl batchInsert error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.GET)
	@ResponseBody
	public ListInfo<Terminal> get(@PathVariable String key,HttpServletRequest req) {
		int totalCount = 1;
		List<Terminal> list = new ArrayList<Terminal>();
		try {
			Terminal info = new Terminal();
			KeyExplainHandler.explainKey(key, info);
			list.add(dbService.selectByPrimaryKey(info));
		} catch (Exception e) {
			log.warn("  TerminalCtrl get by key error..",e);
		}
		return  new ListInfo<Terminal>(totalCount, list, 0, 1);
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.DELETE)
	@ResponseBody
	public ReturnInfo delete(@PathVariable String key,HttpServletRequest req) {
		try {
			Terminal info = new Terminal();
			KeyExplainHandler.explainKey(key, info);
			dbService.deleteByPrimaryKey(info);
			initParm.initTerminal();
			psc.reload();
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  TerminalCtrl delete by key error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@PathVariable String key,@RequestBody Terminal info,HttpServletRequest req) {
		try {
			if(info!=null){
				KeyExplainHandler.explainKey(key, info);
				dbService.updateByPrimaryKey( info);
			}
			initParm.initTerminal();
			psc.reload();
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  TerminalCtrl update by key error..",e);
		}
		return ReturnInfo.Faild;
	}
	@RequestMapping(value="/getCheck/{data}",method=RequestMethod.GET)
	@ResponseBody
	public Object getCheck(@PathVariable String data,HttpServletRequest req) {
		boolean tag = false;
		Terminal terminal=null;
		try {
			terminal=initParm.getTerminal(data);
			if(terminal==null){
				tag=true ;
			}else{
				tag=false;
			}
		} catch (Exception e) {
			log.warn("  TerminalCtrl getCheck error..",e);
		}
		return tag;
	}
	
	@RequestMapping(value="/Reset/{sessionid}",method=RequestMethod.GET)
	@ResponseBody
	public ReturnInfo Reset(@PathVariable String sessionid,HttpServletRequest req) {
		try {
			Terminal terminal=null;
			terminal=initParm.getTerminal(sessionid);
			String isused=terminal.getIsused();
			if ("0".equals(isused)) {
				terminal.setIsused("1");
			}else {
				terminal.setIsused("0");
			}
			dbService.updateByPrimaryKeySelective(terminal);
			initParm.initTerminal();
			psc.reload();
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  TerminalCtrl Reset by key error..",e);
		}
		return  ReturnInfo.Faild;
	}	
	
	private String getTableName(){
		return "t_ips_terminal";
	}
	
	@SuppressWarnings("serial")
	public static class Terminals extends ArrayList<Terminal> {  
	    public Terminals() { super(); }  
	}
}
