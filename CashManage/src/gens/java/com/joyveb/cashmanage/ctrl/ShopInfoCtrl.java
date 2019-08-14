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

import com.joyveb.cashmanage.entity.ShopInfo;
import com.joyveb.cashmanage.entity.ShopInfoKey;
import com.joyveb.cashmanage.service.ShopInfoService;
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
@RequestMapping("/shopInfo")
public class ShopInfoCtrl {
	
	private @Resource ShopInfoService dbService;
	
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo insert(@RequestBody ShopInfo info,HttpServletRequest req) {
		try {
			dbService.insert(info);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  ShopInfoCtrl insert error..",e);
			
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@RequestBody ShopInfo info,HttpServletRequest req) {
		try {
			dbService.updateByExample(info,dbService.getExample(info));
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  ShopInfoCtrl update error..",e);
			
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
			dc.setEntityClass(ShopInfo.class);
			dc.setKeyClass(ShopInfoKey.class);
			dc.setQmb(info);
			dc.setPageinfo(para);
			dc.setFmb(fmb);
			dc.setTalbeName(getTableName());
			totalCount = dbService.getCount(dc);
			list = dbService.getData(dc);
		} catch (Exception e) {
			log.warn("  ShopInfoCtrl get error..",e);
			
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
			List<ShopInfo> list = new ArrayList<ShopInfo>();
			for(String id :data){
				ShopInfo info = new ShopInfo();
				KeyExplainHandler.explainKey(id, info);
				list.add(info);
			}
			dbService.batchDelete( list);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  ShopInfoCtrl batchDelete error..",e);
			
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/batch",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo batchUpdate(@RequestBody ShopInfos data,HttpServletRequest req) {
		try {
			dbService.batchUpdate( data);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  ShopInfoCtrl batchUpdate error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/batch",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo batchInsert(@RequestBody ShopInfos data,HttpServletRequest req) {
		try {
			dbService.batchInsert( data);
			return ReturnInfo.Success;
		} catch (Exception e) {
		    log.warn("  ShopInfoCtrl batchInsert error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.GET)
	@ResponseBody
	public ListInfo<ShopInfo> get(@PathVariable String key,HttpServletRequest req) {
		int totalCount = 1;
		List<ShopInfo> list = new ArrayList<ShopInfo>();
		try {
			ShopInfo info = new ShopInfo();
			KeyExplainHandler.explainKey(key, info);
			list.add(dbService.selectByPrimaryKey(info));
		} catch (Exception e) {
			log.warn("  ShopInfoCtrl get by key error..",e);
		}
		return  new ListInfo<ShopInfo>(totalCount, list, 0, 1);
	}
	
	@RequestMapping(value="/checkOrderId/{key}",method=RequestMethod.GET)
	@ResponseBody
	public List<HashMap<String,Object>> checkOrderId(@PathVariable String key,HttpServletRequest req) {
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		try {
			String sql="SELECT * FROM t_game_out WHERE ORDERID ='"+key+"';";
			list=dbService.dosql(sql);
		} catch (Exception e) {
			log.warn("  ShopInfoCtrl get by key error..",e);
		}
		return  list;
	}
	
	
	@RequestMapping(value="/getShop/{key}",method=RequestMethod.GET)
	@ResponseBody
	public List<HashMap<String,Object>> getGameCode(@PathVariable String key,HttpServletRequest req) {
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		try {
			String sql="";
			if("0".equals(key)){
				sql="SELECT"+
								" `order`.orderid AS orderid,"+
								" info.GAMECODE AS gamecode,"+
								" info.GAMENUM AS gamenum,"+
								" game.GAMENAME AS gamename"+
						" FROM"+
								" t_game_info AS game,"+
								" t_shop_info AS info,"+
								" t_order_info AS `order`"+
						" WHERE"+
								" `order`.ORDERID = info.ORDERID"+
								" AND `order`.ORDERSTATUS = 2"+
								" AND game.GAMECODE = info.GAMECODE"+
								" ORDER BY"+
								" `order`.paytime "+
								" ASC"+
								" limit 0,1;";
			}else{
				sql="SELECT"+
						" info.GAMECODE AS gamecode,"+
						" info.GAMENUM AS gamenum,"+
						" game.GAMENAME AS gamename"+
				" FROM"+
						" t_game_info AS game,"+
						" t_shop_info AS info,"+
						" t_order_info AS `order`"+
				" WHERE"+
						" `order`.ORDERID = info.ORDERID"+
						" AND `order`.ORDERSTATUS = 2"+
						" AND game.GAMECODE = info.GAMECODE"+
						" AND `order`.ORDERID ='"+key+
						"';";
			}
			list=dbService.dosql(sql);
			
		} catch (Exception e) {
			log.warn("  ShopInfoCtrl get by key error..",e);
		}
		return  list;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.DELETE)
	@ResponseBody
	public ReturnInfo delete(@PathVariable String key,HttpServletRequest req) {
		try {
			ShopInfo info = new ShopInfo();
			KeyExplainHandler.explainKey(key, info);
			dbService.deleteByPrimaryKey(info);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  ShopInfoCtrl delete by key error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@PathVariable String key,@RequestBody ShopInfo info,HttpServletRequest req) {
		try {
			if(info!=null){
				KeyExplainHandler.explainKey(key, info);
				dbService.updateByPrimaryKey( info);
			}
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  ShopInfoCtrl update by key error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	private String getControllerName(){
		return this.getClass().getSimpleName();
	}
	
	private String getTableName(){
		return "t_shop_info";
	}
	
	@SuppressWarnings("serial")
	public static class ShopInfos extends ArrayList<ShopInfo> {  
	    public ShopInfos() { super(); }  
	}
}
