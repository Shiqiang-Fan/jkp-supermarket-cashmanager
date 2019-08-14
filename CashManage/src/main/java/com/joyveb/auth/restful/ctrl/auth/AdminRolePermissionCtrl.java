package com.joyveb.auth.restful.ctrl.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.joyveb.auth.restful.bean.AdminRolePermission;
import com.joyveb.auth.restful.bean.AdminRolePermissionExample;
import com.joyveb.auth.restful.bean.AdminRolePermissionKey;
import com.joyveb.auth.restful.service.AdminRolePermissionService;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.common.ListInfo;
import com.joyveb.lbos.restful.common.PageInfo;
import com.joyveb.lbos.restful.common.ReturnInfo;
import com.joyveb.lbos.restful.spring.FieldsMapperBean;
import com.joyveb.lbos.restful.spring.QueryMapperBean;
import com.joyveb.lbos.restful.spring.QueryMapperBean.EqualBean;
import com.joyveb.lbos.restful.spring.RequestJsonParam;
import com.joyveb.lbos.restful.util.KeyExplainHandler;

/**
 * 系统菜单树表操作
 * @author 
 *
 */
@Slf4j
@Controller
@RequestMapping("/adminrolepermission")
public class AdminRolePermissionCtrl {
	
	private @Resource(name="adminRolePermissionService") AdminRolePermissionService adminRolePermissionService;
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo insert(@RequestBody AdminRolePermission info,HttpServletRequest req) {
		try {
			adminRolePermissionService.insert(info);
			return ReturnInfo.Success;
		}catch (DuplicateKeyException e){
			log.trace("主键冲突",e);
			return new ReturnInfo("添加失败,不可重复添加",false);
		} catch (Exception e) {
			log.warn("  AdminRolePermissionCtrl insert error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@RequestBody AdminRolePermission info,HttpServletRequest req) {
		try {
			adminRolePermissionService.updateByPrimaryKey(info);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  AdminRolePermissionCtrl update error..",e);
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
			dc.setEntityClass(AdminRolePermission.class);
			dc.setKeyClass(AdminRolePermissionKey.class);
			dc.setQmb(info);
			dc.setPageinfo(para);
			dc.setFmb(fmb);
			dc.setTalbeName(getTableName());
			totalCount = adminRolePermissionService.getCount(dc);
			list = adminRolePermissionService.getData(dc);
		} catch (Exception e) {
			log.warn("  AdminRolePermissionCtrl get error..",e);
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
			List<AdminRolePermission> list = new ArrayList<AdminRolePermission>();
			for(String id :data){
				AdminRolePermission info = new AdminRolePermission();
				KeyExplainHandler.explainKey(id, info);
				list.add(info);
			}
			adminRolePermissionService.batchDelete(list);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  AdminRolePermissionCtrl batchDelete error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/batch",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo batchUpdate(@RequestBody AdminRolePermissions data,HttpServletRequest req) {
		try {
			adminRolePermissionService.batchUpdate(data);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  AdminRolePermissionCtrl batchUpdate error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/batch",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo batchInsert(@RequestBody AdminRolePermissions data,HttpServletRequest req) {
		try {
			//删除对应角色的记录
			AdminRolePermission  roleper = data.get(0);
			adminRolePermissionService.deleteByPrimaryKey(roleper);
			AdminRolePermissionExample ape = new AdminRolePermissionExample();
			ape.createCriteria().andRoleCodeEqualTo(roleper.getRoleCode());
			adminRolePermissionService.deleteByExample(ape);
			if(roleper.getPermissionId()!=-1){
				adminRolePermissionService.batchInsert(data);
			}
			return ReturnInfo.Success;
		} catch (Exception e) {
		    log.warn("  AdminRolePermissionCtrl batchInsert error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.GET)
	@ResponseBody
	public Object get(@PathVariable String key,HttpServletRequest req) {
		String sql = "select ROLE_CODE roleCode,PERMISSION_ID permissionId,FIELD1 field1 from t_admin_role_permission where ROLE_CODE = "+key;
		List<HashMap<String, Object>> resultSet = adminRolePermissionService.dosql(sql);
		KeyExplainHandler.addId(resultSet, AdminRolePermissionKey.class,AdminRolePermission.class);//add key
		return resultSet;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.DELETE)
	@ResponseBody
	public ReturnInfo delete(@PathVariable String key,HttpServletRequest req) {
		try {
			AdminRolePermission info = new AdminRolePermission();
			KeyExplainHandler.explainKey(key, info);
			adminRolePermissionService.deleteByPrimaryKey(info);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  AdminRolePermissionCtrl delete by key error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@PathVariable String key,@RequestBody AdminRolePermission info,HttpServletRequest req) {
		try {
			if(info!=null){
				KeyExplainHandler.explainKey(key, info);
				adminRolePermissionService.updateByPrimaryKey(info);
			}
			//LoggerManager.updateEnd(req, oldPojo, info, log, getControllerName(), startTime);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  AdminRolePermissionCtrl update by key error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	private String getTableName(){
		return "t_admin_role_permission";
	}
	
	@SuppressWarnings("serial")
	public static class AdminRolePermissions extends ArrayList<AdminRolePermission> {  
	    public AdminRolePermissions() { super(); }  
	}
}
