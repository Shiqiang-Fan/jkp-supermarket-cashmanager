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

import com.joyveb.auth.restful.bean.AdminRole;
import com.joyveb.auth.restful.bean.AdminRoleExample;
import com.joyveb.auth.restful.bean.AdminRoleKey;
import com.joyveb.auth.restful.service.AdminRoleService;
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
@RequestMapping("/adminrole")
public class AdminRoleCtrl {
	
	private @Resource(name="adminRoleService") AdminRoleService adminRoleService;
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo insert(@RequestBody AdminRole info,HttpServletRequest req) {
		ReturnInfo returninfo = new ReturnInfo("添加失败",false);
		try {
			AdminRoleExample adRole = new AdminRoleExample();
			adRole.createCriteria().andRoleCodeEqualTo(info.getRoleCode());
			List<AdminRole> list = adminRoleService.selectByExample(adRole);
			if(list.size()==0){
			//long startTime = System.currentTimeMillis();
			//LoggerManager.insertStart(req, info, log, getControllerName());
			adminRoleService.insert(info);
			//LoggerManager.insertEnd(req, log, getControllerName(), startTime);
			return ReturnInfo.Success;
			}else{
				returninfo.setDescription("角色代码重复");
				return returninfo;
			}
		}catch (DuplicateKeyException e){
			log.trace("主键冲突",e);
			return new ReturnInfo("添加失败,不可重复添加",false);
		} catch (Exception e) {
			log.warn("  AdminRoleCtrl insert error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@RequestBody AdminRole info,HttpServletRequest req) {
		try {
			//long startTime = System.currentTimeMillis();
			//LoggerManager.updateStart(req, info, log, getControllerName());
			adminRoleService.updateByPrimaryKey(info);
			//LoggerManager.updateEnd(req, oldPojo, info, log, getControllerName(), startTime);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  AdminRoleCtrl update error..",e);
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
			dc.setEntityClass(AdminRole.class);
			dc.setKeyClass(AdminRoleKey.class);
			dc.setQmb(info);
			dc.setPageinfo(para);
			dc.setFmb(fmb);
			dc.setTalbeName(getTableName());
			//long startTime = System.currentTimeMillis();
			//LoggerManager.queryStart(req, info, null, log, getControllerName());
			totalCount = adminRoleService.getCount(dc);
			list = adminRoleService.getData(dc);
			//LoggerManager.queryEnd(req, log, getControllerName(), startTime);
		} catch (Exception e) {
			log.warn("  AdminRoleCtrl get error..",e);
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
			List<AdminRole> list = new ArrayList<AdminRole>();
			for(String id :data){
				AdminRole info = new AdminRole();
				KeyExplainHandler.explainKey(id, info);
				list.add(info);
			}
			//LoggerManager.batchDeleteStart(req, list, log, getControllerName());
			adminRoleService.batchDelete(list);
			//LoggerManager.deleteEnd(req, log, getControllerName(), getTableName(), list.size());
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  AdminRoleCtrl batchDelete error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/batch",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo batchUpdate(@RequestBody AdminRoles data,HttpServletRequest req) {
		try {
			//LoggerManager.batchUpdateStart(req, data, log, getControllerName());
			//long startTime = System.currentTimeMillis();
			adminRoleService.batchUpdate(data);
			//LoggerManager.batchUpdateEnd(req,AdminRoleKey.class, oldPojos, data, log, getControllerName(), startTime);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  AdminRoleCtrl batchUpdate error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/batch",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo batchInsert(@RequestBody AdminRoles data,HttpServletRequest req) {
		try {
			//LoggerManager.batchInsertStart(req, data, log, getControllerName());
			//long startTime = System.currentTimeMillis();
			adminRoleService.batchInsert(data);
			//LoggerManager.insertEnd(req, log, getControllerName(), startTime);
			return ReturnInfo.Success;
		} catch (Exception e) {
		    log.warn("  AdminRoleCtrl batchInsert error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.GET)
	@ResponseBody
	public ListInfo<AdminRole> get(@PathVariable String key,HttpServletRequest req) {
		int totalCount = 1;
		List<AdminRole> list = null;
		try {
			AdminRoleExample info = new AdminRoleExample();
			KeyExplainHandler.explainKey(key, info);
			//LoggerManager.queryStart(req, null, info, log, getControllerName());
			//long startTime = System.currentTimeMillis();
			info.createCriteria().andRoleNameLike(key);
			list = adminRoleService.selectByExample(info);
			//LoggerManager.queryEnd(req, log, getControllerName(), startTime);
		} catch (Exception e) {
			log.warn("  AdminRoleCtrl get by key error..",e);
		}
		return  new ListInfo<AdminRole>(totalCount, list, 0, 1);
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.DELETE)
	@ResponseBody
	public ReturnInfo delete(@PathVariable String key,HttpServletRequest req) {
		try {
			AdminRole info = new AdminRole();
			KeyExplainHandler.explainKey(key, info);
			adminRoleService.deleteByPrimaryKey(info);
			//LoggerManager.deleteEnd(req, log, getControllerName(), getTableName(), 1);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  AdminRoleCtrl delete by key error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@PathVariable String key,@RequestBody AdminRole info,HttpServletRequest req) {
		try {
			if(info!=null){
				KeyExplainHandler.explainKey(key, info);
				adminRoleService.updateByPrimaryKey(info);
			}
			//LoggerManager.updateEnd(req, oldPojo, info, log, getControllerName(), startTime);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  AdminRoleCtrl update by key error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	private String getTableName(){
		return "t_admin_role";
	}
	
	@SuppressWarnings("serial")
	public static class AdminRoles extends ArrayList<AdminRole> {  
	    public AdminRoles() { super(); }  
	}
}
