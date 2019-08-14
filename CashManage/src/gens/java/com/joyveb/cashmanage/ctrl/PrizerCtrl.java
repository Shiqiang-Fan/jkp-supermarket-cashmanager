package com.joyveb.cashmanage.ctrl;

import com.joyveb.cashmanage.entity.Prizer;
import com.joyveb.cashmanage.entity.PrizerKey;
import com.joyveb.cashmanage.service.PrizerService;
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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/prizer")
public class PrizerCtrl {
	
	private @Resource PrizerService dbService;
	private List<HashMap<String,Object>> list2 = new ArrayList<HashMap<String,Object>>();
	
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo insert(@RequestBody Prizer info,HttpServletRequest req) {
		try {
			dbService.insert(info);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  PrizerCtrl insert error..",e);
			
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@RequestBody Prizer info,HttpServletRequest req) {
		try {
			dbService.updateByExample(info,dbService.getExample(info));
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  PrizerCtrl update error..",e);
			
		}
		return ReturnInfo.Faild;
	}
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public Object get(@RequestJsonParam(value = "query",required=false) QueryMapperBean info,
			@RequestJsonParam(value = "fields",required=false) FieldsMapperBean fmb,
			PageInfo para, HttpServletRequest req) {
		List<HashMap<String,Object>> list2 =null;
		int totalCount=0;
		try {
			DbCondi dc = new DbCondi();
			dc.setEntityClass(Prizer.class);
			dc.setKeyClass(PrizerKey.class);
			dc.setQmb(info);
			dc.setPageinfo(para);
			dc.setFmb(fmb);
			dc.setTalbeName(getTableName());
			totalCount=dbService.getCount(dc);
			list2 = dbService.getData(dc);
		} catch (Exception e) {
			log.warn("  PrizerCtrl get error..",e);
		}
		if(para.isPage()){
			return new ListInfo<HashMap<String, Object>>(totalCount, list2, para);
		}else{
			return list2;
		}
	}
	
	@RequestMapping(value = "/bb", method = RequestMethod.GET)
	@ResponseBody
	public Object getBB(@RequestJsonParam(value = "query",required=false) QueryMapperBean info,
			@RequestJsonParam(value = "fields",required=false) FieldsMapperBean fmb,
			PageInfo para, HttpServletRequest req) {
		List<HashMap<String,Object>> list = null;
		list2.clear();
		SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
		String sql = "";
		String lk = "";
		String co = "";
		if(null!=info){
			List<LikeBean> likes = info.getLikes();
			List<ConditionBean> conditions = info.getConditions();
			if(null!=likes&&likes.size()!=0){
				lk = " and date_format(requesttime, '%Y-%m-%d') like '"+likes.get(0).getRegex()+"'";
			}
			if(null!=conditions&&conditions.size()!=0){
				String a = conditions.get(0).getValue().toString();
				String b = conditions.get(1).getValue().toString();
				try {
					co = " and date_format(requesttime, '%Y-%m-%d') between '"+ymd.format(new Date(Long.parseLong(a)))+"' and '"+ymd.format(new Date(Long.parseLong(b)))+"'";
				} catch (Exception e) {
					log.warn("日期转换异常",e);
				}
			}
		}
		try {
			if(null==para.getLimit()||null==para.getSkip()){
				sql = "SELECT requesttime,count(*) count, SUM(WINAMOUNT) amount FROM t_core_prizer where 1=1 and AMOUNTSIGN='0' "+lk+co+" GROUP BY DATE_FORMAT(REQUESTTIME,'%Y%M%D') desc LIMIT 0,10";
			}else{
				sql = "SELECT requesttime,count(*) count, SUM(WINAMOUNT) amount FROM t_core_prizer where 1=1 and AMOUNTSIGN='0' "+lk+co+"GROUP BY DATE_FORMAT(REQUESTTIME,'%Y%M%D') desc LIMIT "+para.getSkip()+","+para.getLimit();
			}
			list = dbService.dosql(sql);
			list2.addAll(list);
		} catch (Exception e) {
			log.warn("  PrizerCtrl getBB error..",e);
		}
		if(para.isPage()){
			return new ListInfo<HashMap<String, Object>>(list2.size(), list2, para);
		}else{
			return list;
		}
	}
	
	@RequestMapping(value="/batch/delete",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo batchDelete(@RequestBody List<String> data,HttpServletRequest req) {
		try {
			List<Prizer> list = new ArrayList<Prizer>();
			for(String id :data){
				Prizer info = new Prizer();
				KeyExplainHandler.explainKey(id, info);
				list.add(info);
			}
			dbService.batchDelete( list);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  PrizerCtrl batchDelete error..",e);
			
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/batch",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo batchUpdate(@RequestBody Prizers data,HttpServletRequest req) {
		try {
			dbService.batchUpdate( data);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  PrizerCtrl batchUpdate error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/batch",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo batchInsert(@RequestBody Prizers data,HttpServletRequest req) {
		try {
			dbService.batchInsert( data);
			return ReturnInfo.Success;
		} catch (Exception e) {
		    log.warn("  PrizerCtrl batchInsert error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.GET)
	@ResponseBody
	public ListInfo<Prizer> get(@PathVariable String key,HttpServletRequest req) {
		int totalCount = 1;
		List<Prizer> list = new ArrayList<Prizer>();
		try {
			Prizer info = new Prizer();
			KeyExplainHandler.explainKey(key, info);
			list.add(dbService.selectByPrimaryKey(info));
		} catch (Exception e) {
			log.warn("  PrizerCtrl get by key error..",e);
		}
		return  new ListInfo<Prizer>(totalCount, list, 0, 1);
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.DELETE)
	@ResponseBody
	public ReturnInfo delete(@PathVariable String key,HttpServletRequest req) {
		try {
			Prizer info = new Prizer();
			KeyExplainHandler.explainKey(key, info);
			dbService.deleteByPrimaryKey(info);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  PrizerCtrl delete by key error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@PathVariable String key,@RequestBody Prizer info,HttpServletRequest req) {
		try {
			if(info!=null){
				KeyExplainHandler.explainKey(key, info);
				dbService.updateByPrimaryKey( info);
			}
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  PrizerCtrl update by key error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/cashexport",method=RequestMethod.GET)
	public Object exp(HttpServletRequest req,HttpServletResponse rep){
		String agents = req.getHeader("user-agent");
		String filename = "";
		try {
			SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
			if(agents.contains("Firefox")){
				filename = base64EncodeFileName("财务兑奖报表"+ymd.format(new Date(System.currentTimeMillis()))+".xls");
			}else{
				filename = URLEncoder.encode("财务兑奖报表"+ymd.format(new Date(System.currentTimeMillis()))+".xls","utf-8");
			}
		} catch (UnsupportedEncodingException e1) {
			log.warn("导出财务兑奖报表错误",e1);
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
		return "t_core_prizer";
	}
	
	@SuppressWarnings("serial")
	public static class Prizers extends ArrayList<Prizer> {  
	    public Prizers() { super(); }  
	}
}
