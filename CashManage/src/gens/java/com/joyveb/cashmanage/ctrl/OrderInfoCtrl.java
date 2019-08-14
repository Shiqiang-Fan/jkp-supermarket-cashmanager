package com.joyveb.cashmanage.ctrl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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

import com.joyveb.cashmanage.common.Constants;
import com.joyveb.cashmanage.entity.GameInfo;
import com.joyveb.cashmanage.entity.OrderInfo;
import com.joyveb.cashmanage.entity.OrderInfoKey;
import com.joyveb.cashmanage.entity.ReceiverInfo;
import com.joyveb.cashmanage.entity.ReceiverInfoKey;
import com.joyveb.cashmanage.entity.ShopInfo;
import com.joyveb.cashmanage.entity.ShopInfoExample;
import com.joyveb.cashmanage.service.OrderInfoService;
import com.joyveb.cashmanage.service.ReceiverInfoService;
import com.joyveb.cashmanage.service.ShopInfoService;
import com.joyveb.cashmanage.service.WhiteListService;
import com.joyveb.cashmanage.web.InitParm;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.common.ListInfo;
import com.joyveb.lbos.restful.common.PageInfo;
import com.joyveb.lbos.restful.common.ReturnInfo;
import com.joyveb.lbos.restful.spring.FieldsMapperBean;
import com.joyveb.lbos.restful.spring.QueryMapperBean;
import com.joyveb.lbos.restful.spring.QueryMapperBean.ConditionBean;
import com.joyveb.lbos.restful.spring.QueryMapperBean.LikeBean;
import com.joyveb.lbos.restful.spring.RequestJsonParam;
import com.joyveb.lbos.restful.util.KeyExplainHandler;

@Slf4j
@Controller
@RequestMapping("/orderInfo")
public class OrderInfoCtrl {
	
	private @Resource WhiteListService whiteListService;
	private @Resource OrderInfoService dbService;
	private @Resource ReceiverInfoService receiverInfoService;
	private @Resource ShopInfoService shopInfoService;
	@Resource(name="initParm")
	private InitParm initParm;
	private List<HashMap<String,Object>> list2 = new ArrayList<HashMap<String,Object>>();

	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo insert(@RequestBody OrderInfo info,HttpServletRequest req) {
		try {
			dbService.insert(info);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  OrderInfoCtrl insert error..",e);
			
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@RequestBody OrderInfo info,HttpServletRequest req) {
		try {
			dbService.updateByExample(info,dbService.getExample(info));
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  OrderInfoCtrl update error..",e);
			
		}
		return ReturnInfo.Faild;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public Object get(@RequestJsonParam(value = "query",required=false) QueryMapperBean info,
			@RequestJsonParam(value = "fields",required=false) FieldsMapperBean fmb,
			PageInfo para, HttpServletRequest req) {
		int totalCount = 0;
		List<HashMap<String,Object>> list = null;
		try {
			DbCondi dc = new DbCondi();
			dc.setEntityClass(OrderInfo.class);
			dc.setKeyClass(OrderInfoKey.class);
			dc.setQmb(info);
			dc.setPageinfo(para);
			dc.setFmb(fmb);
			dc.setTalbeName(getTableName());
			totalCount = dbService.getCount(dc);
			list = dbService.getData(dc);
			if(null!=list&&list.size()>0){
			for (int t = 0; t < list.size(); t++) {
				String td = (String) list.get(t).get("receiverid");
				ReceiverInfoKey ri = new ReceiverInfoKey();
				ri.setUuid(td);
				ReceiverInfo selectByPrimaryKey = receiverInfoService.selectByPrimaryKey(ri);
				if(null!=selectByPrimaryKey){
					list.get(t).put("address", selectByPrimaryKey.getAddress());
					list.get(t).put("phone", selectByPrimaryKey.getPhone());
					list.get(t).put("receiver", selectByPrimaryKey.getReceiver());
				}
	        }
			}
		} catch (Exception e) {
			log.warn("  OrderInfoCtrl get error..",e);
		}
		if(para.isPage()){
			return new ListInfo<HashMap<String, Object>>(totalCount, list, para);
		}else{
			return list;
		}
	}
	
	@RequestMapping(value = "/ocf", method = RequestMethod.GET)
	@ResponseBody
	public Object getOcf(@RequestJsonParam(value = "query",required=false) QueryMapperBean info,
			@RequestJsonParam(value = "fields",required=false) FieldsMapperBean fmb,
			PageInfo para, HttpServletRequest req) {
		String sql = "";
		String lk = "";
		String co = "";
		list2.clear();
		SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
		if(null!=info){
			List<LikeBean> likes = info.getLikes();
			List<ConditionBean> conditions = info.getConditions();
			if(null!=likes&&likes.size()!=0){
				lk = " and userid like '"+likes.get(0).getRegex()+"'";
			}
			if(null!=conditions&&conditions.size()!=0){
				String a = conditions.get(0).getValue().toString();
				String b = conditions.get(1).getValue().toString();
				try {
					co = " and date_format(ordertime, '%Y-%m-%d') between '"+ymd.format(new Date(Long.parseLong(a)))+"' and '"+ymd.format(new Date(Long.parseLong(b)))+"'";
				} catch (Exception e) {
					log.warn("日期转换异常",e);
				}
			}
		}
		try {
			if(para.getSkip()==0){
				sql =  "SELECT "+
							"DATE_FORMAT(ordertime,'%Y-%m-%d') days, "+
							" userid, "+
							"Sum(orde.TOTALAMOUNT) AS TOTALAMOUNT,"+
							"count(orde.orderid) AS GAMENUM "+
						"FROM "+
							"t_order_info orde "+
						"WHERE "+
							" orde.ORDERSTATUS IN (2, 4, 5, 6) "+lk+co+
							" GROUP BY "+
							"days,userid "+
							"ORDER BY "+
							"days ASC;";
			}else{
				sql ="SELECT "+
						"DATE_FORMAT(ordertime,'%Y-%m-%d') days, "+
						" userid, "+
						"Sum(orde.TOTALAMOUNT) AS TOTALAMOUNT,"+
						"count(orde.orderid) AS GAMENUM "+
					"FROM "+
						"t_order_info orde "+
					"WHERE "+
						" orde.ORDERSTATUS IN (2, 4, 5, 6) "+lk+co+
						"GROUP BY "+
						"days,userid "+
						"ORDER BY "+
						"days ASC limit "+para.getSkip()+","+para.getLimit()+";";
			}
			list2 = dbService.dosql(sql);
			//list2.addAll(list);
		} catch (Exception e) {
			log.warn("  OrderInfoCtrl get error..",e);
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
			List<OrderInfo> list = new ArrayList<OrderInfo>();
			for(String id :data){
				OrderInfo info = new OrderInfo();
				KeyExplainHandler.explainKey(id, info);
				list.add(info);
			}
			dbService.batchDelete( list);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  OrderInfoCtrl batchDelete error..",e);
			
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/batch",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo batchUpdate(@RequestBody OrderInfos data,HttpServletRequest req) {
		try {
			dbService.batchUpdate( data);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  OrderInfoCtrl batchUpdate error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/batch",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo batchInsert(@RequestBody OrderInfos data,HttpServletRequest req) {
		try {
			dbService.batchInsert( data);
			return ReturnInfo.Success;
		} catch (Exception e) {
		    log.warn("  OrderInfoCtrl batchInsert error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.GET)
	@ResponseBody
	public ListInfo<OrderInfo> get(@PathVariable String key,HttpServletRequest req) {
		int totalCount = 1;
		List<OrderInfo> list = new ArrayList<OrderInfo>();
		try {
			OrderInfo info = new OrderInfo();
			KeyExplainHandler.explainKey(key, info);
			list.add(dbService.selectByPrimaryKey(info));
		} catch (Exception e) {
			log.warn("  OrderInfoCtrl get by key error..",e);
		}
		return  new ListInfo<OrderInfo>(totalCount, list, 0, 1);
	}
	
	@RequestMapping(value="/send/{orderid}/{waybillno}",method=RequestMethod.GET)
	@ResponseBody
	public ReturnInfo send(@PathVariable String orderid,@PathVariable String waybillno,HttpServletRequest req) {
		OrderInfo oInfo = new OrderInfo();
		oInfo.setOrderid(orderid);
		oInfo.setWaybillno(waybillno);
		oInfo.setOrderstatus(Constants.ALREADYSEND);
		try {
			dbService.updateByPrimaryKeySelective(oInfo);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  OrderInfoCtrl batchInsert error..",e);
		}
		return  ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/orders/{orderid}",method=RequestMethod.GET)
	@ResponseBody
	public ReturnInfo orders(@PathVariable String orderid,HttpServletRequest req) {
		OrderInfo oInfo = new OrderInfo();
		oInfo.setOrderid(orderid);
		oInfo.setOrderstatus(Constants.ALREADYSIGN);
		try {
			dbService.updateByPrimaryKeySelective(oInfo);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  OrderInfoCtrl batchInsert error..",e);
		}
		return  ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/shop/{orderid}",method=RequestMethod.GET)
	@ResponseBody
	public List<ShopInfo> shop(@PathVariable String orderid,HttpServletRequest req) {
		ShopInfoExample sExample = new ShopInfoExample();
		sExample.createCriteria().andOrderidEqualTo(orderid);
		List<ShopInfo> selectByExample = shopInfoService.selectByExample(sExample);
		List<ShopInfo> list = new ArrayList<ShopInfo>();
		ShopInfo shopInfo = null;
		if (null!=selectByExample&&selectByExample.size()>0) {
			for(int i=0;i<selectByExample.size();i++){
				shopInfo = selectByExample.get(i);
				GameInfo gameInfo = initParm.getGameInfo(shopInfo.getGamecode());
				if(null!=gameInfo){
					shopInfo.setGamecode(gameInfo.getGamename());
					list.add(shopInfo);
				}
			}
		}
		return list;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.DELETE)
	@ResponseBody
	public ReturnInfo delete(@PathVariable String key,HttpServletRequest req) {
		try {
			OrderInfo info = new OrderInfo();
			KeyExplainHandler.explainKey(key, info);
			dbService.deleteByPrimaryKey(info);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  OrderInfoCtrl delete by key error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@PathVariable String key,@RequestBody OrderInfo info,HttpServletRequest req) {
		try {
			if(info!=null){
				KeyExplainHandler.explainKey(key, info);
				dbService.updateByPrimaryKey( info);
			}
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  OrderInfoCtrl update by key error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/orderexport",method=RequestMethod.GET)
	public Object exp(HttpServletRequest req,HttpServletResponse rep){
		String agents = req.getHeader("user-agent");
		String filename = "";
		try {
			SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
			
			if(agents.contains("Firefox")){
				filename = base64EncodeFileName("订单统计报表"+ymd.format(new Date(System.currentTimeMillis()))+".xls");
			}else{
				filename = URLEncoder.encode("订单统计报表"+ymd.format(new Date(System.currentTimeMillis()))+".xls", "utf-8");
			}
		} catch (UnsupportedEncodingException e1) {
			log.warn("导出订单报表错误",e1);
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
			log.warn("IO异常",e);
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
		return "t_order_info";
	}
	
	@SuppressWarnings("serial")
	public static class OrderInfos extends ArrayList<OrderInfo> {  
	    public OrderInfos() { super(); }  
	}
}
