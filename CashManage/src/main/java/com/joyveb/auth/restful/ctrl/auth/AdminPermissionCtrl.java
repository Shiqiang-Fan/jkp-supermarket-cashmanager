package com.joyveb.auth.restful.ctrl.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyveb.auth.restful.bean.AdminPermission;
import com.joyveb.auth.restful.bean.AdminPermissionExample;
import com.joyveb.auth.restful.bean.AdminPermissionKey;
import com.joyveb.auth.restful.service.AdminPermissionService;
import com.joyveb.cashmanage.utils.FileUtil;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.common.ListInfo;
import com.joyveb.lbos.restful.common.PageInfo;
import com.joyveb.lbos.restful.common.ReturnInfo;
import com.joyveb.lbos.restful.spring.FieldsMapperBean;
import com.joyveb.lbos.restful.spring.QueryMapperBean;
import com.joyveb.lbos.restful.spring.RequestJsonParam;
import com.joyveb.lbos.restful.util.JsonUtil;
import com.joyveb.lbos.restful.util.KeyExplainHandler;


@Slf4j
@Controller
@RequestMapping("/adminpermission")
public class AdminPermissionCtrl {
	
	private @Resource(name="adminPermissionService") AdminPermissionService adminPermissionService;
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo insert(@RequestBody AdminPermission info,HttpServletRequest req) {
		try {
			adminPermissionService.insert(info);
			return ReturnInfo.Success;
		}catch (DuplicateKeyException e){
			log.trace("主键冲突",e);
			return new ReturnInfo("添加失败,不可重复添加",false);
		} catch (Exception e) {
			log.warn("  AdminPermissionCtrl insert error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	public static void main(String[] args) {
		/*StringBuilder readFileByLines = FileUtil.readFileByLines("D:/SCCM/01开发库/02Codes/trunk/CashManage/WebContent/json/menu.json");
		char[] charArray = readFileByLines.toString().toCharArray();
		byte[] charArray1 = readFileByLines.toString().getBytes();*/
	}
	
	@RequestMapping(value="",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@RequestBody AdminPermission info,HttpServletRequest req) {
		try {
			adminPermissionService.updateByPrimaryKey(info);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  AdminPermissionCtrl update error..",e);
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
			dc.setEntityClass(AdminPermission.class);
			dc.setKeyClass(AdminPermissionKey.class);
			dc.setQmb(info);
			dc.setPageinfo(para);
			dc.setFmb(fmb);
			dc.setTalbeName(getTableName());
			totalCount = adminPermissionService.getCount(dc);
			list = adminPermissionService.getData(dc);
		} catch (Exception e) {
			log.warn("  AdminPermissionCtrl get error..",e);
		}
		if(para.isPage()){
			return new ListInfo<HashMap<String, Object>>(totalCount, list, para);
		}else{
			return list;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/parent", method = RequestMethod.GET)
	@ResponseBody
	public Object getParent(@RequestJsonParam(value = "query",required=false) QueryMapperBean info,
			@RequestJsonParam(value = "fields",required=false) FieldsMapperBean fmb,
			PageInfo para, HttpServletRequest req) {
		int totalCount = 0;
		List<HashMap<String,Object>> list = null;
		try {
			if(info==null){
				info = new QueryMapperBean();
				info.addEqual("pid", 0);
			}
			DbCondi dc = new DbCondi();
			dc.setEntityClass(AdminPermission.class);
			dc.setKeyClass(AdminPermissionKey.class);
			dc.setQmb(info);
			dc.setPageinfo(para);
			dc.setFmb(fmb);
			dc.setTalbeName(getTableName());
			totalCount = adminPermissionService.getCount(dc);
			list = adminPermissionService.getData(dc);
			//添加根节点
			totalCount++;
			/*
			 * "id":1000,"icon":"","_id":"id-1000_","status":"","name":"日志查询","pid":"","orderid":"","leaf":"","url":""
			 */
			HashMap rootNode = new HashMap();
			rootNode.put("id", "0");
			rootNode.put("_id", "id-0");
			rootNode.put("status", "");
			rootNode.put("name", "根节点");
			rootNode.put("pid", "");
			rootNode.put("orderid", "");
			rootNode.put("leaf", "");
			rootNode.put("url", "");
			list.add(0,rootNode);
		} catch (Exception e) {
			log.warn("  AdminPermissionCtrl get error..",e);
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
			List<AdminPermission> list = new ArrayList<AdminPermission>();
			for(String id :data){
				AdminPermission info = new AdminPermission();
				KeyExplainHandler.explainKey(id, info);
				list.add(info);
			}
			adminPermissionService.batchDelete(list);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  AdminPermissionCtrl batchDelete error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/batch",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo batchUpdate(@RequestBody AdminPermissions data,HttpServletRequest req) {
		try {
			adminPermissionService.batchUpdate(data);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  AdminPermissionCtrl batchUpdate error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/batch",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo batchInsert(@RequestBody AdminPermissions data,HttpServletRequest req) {
		try {
			adminPermissionService.batchInsert(data);
			return ReturnInfo.Success;
		} catch (Exception e) {
		    log.warn("  AdminPermissionCtrl batchInsert error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.GET)
	@ResponseBody
	public ListInfo<AdminPermission> get(@PathVariable String key,HttpServletRequest req) {
		int totalCount = 1;
		List<AdminPermission> list = null;
		try {
			AdminPermission info = new AdminPermission();
			KeyExplainHandler.explainKey(key, info);
			AdminPermissionExample ape = new AdminPermissionExample();
			ape.createCriteria().andIdEqualTo(Integer.parseInt(key));
			list = adminPermissionService.selectByExample(ape);
		} catch (Exception e) {
			log.warn("  AdminPermissionCtrl get by key error..",e);
		}
		return  new ListInfo<AdminPermission>(totalCount, list, 0, 1);
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.DELETE)
	@ResponseBody
	public ReturnInfo delete(@PathVariable String key,HttpServletRequest req) {
		try {
			AdminPermission info = new AdminPermission();
			KeyExplainHandler.explainKey(key, info);
			adminPermissionService.deleteByPrimaryKey(info);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  AdminPermissionCtrl delete by key error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@PathVariable String key,@RequestBody AdminPermission info,HttpServletRequest req) {
		try {
			if(info!=null){
				KeyExplainHandler.explainKey(key, info);
				adminPermissionService.updateByPrimaryKey(info);
			}
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  AdminPermissionCtrl update by key error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	private String getTableName(){
		return "t_admin_permission";
	}
	
	@SuppressWarnings("serial")
	public static class AdminPermissions extends ArrayList<AdminPermission> {  
	    public AdminPermissions() { super(); }  
	}
}
