package com.joyveb.auth.restful.ctrl.auth;

import com.joyveb.auth.restful.bean.AdminOperator;
import com.joyveb.auth.restful.bean.AdminOperatorExample;
import com.joyveb.auth.restful.bean.AdminOperatorKey;
import com.joyveb.auth.restful.service.AdminOperatorService;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.common.ListInfo;
import com.joyveb.lbos.restful.common.PageInfo;
import com.joyveb.lbos.restful.common.ReturnInfo;
import com.joyveb.lbos.restful.spring.FieldsMapperBean;
import com.joyveb.lbos.restful.spring.QueryMapperBean;
import com.joyveb.lbos.restful.spring.RequestJsonParam;
import com.joyveb.lbos.restful.util.KeyExplainHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/adminoperator")
public class AdminOperatorCtrl {
	
	private @Resource(name="adminOperatorService") AdminOperatorService adminOperatorService;
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo insert(@RequestBody AdminOperator info,HttpServletRequest req) {
		ReturnInfo returninfo = new ReturnInfo("添加失败",false);
		try {
			AdminOperatorExample aoe = new AdminOperatorExample();
			aoe.createCriteria().andUserNameEqualTo(info.getUserName());
			List<AdminOperator> list = adminOperatorService.selectByExample(aoe);
			if(list != null){
			info.setCreateDate((new Date()).getTime());
			info.setPassWord(DigestUtils.md5Hex(info.getPassWord()));
			adminOperatorService.insert(info);
			return ReturnInfo.Success;
			}else{
				returninfo.setDescription("用户ID重复");
				return returninfo;
			}
		}catch (DuplicateKeyException e){
			log.trace("主键冲突",e);
			return new ReturnInfo("添加失败,不可重复添加",false);
		} catch (Exception e) {
			log.warn("  AdminOperatorCtrl insert error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@RequestBody AdminOperator info,HttpServletRequest req) {
		try {
			String passWord = info.getPassWord();
			if(passWord.length()>30){
				info.setPassWord(passWord);
			}else{
				info.setPassWord(DigestUtils.md5Hex(info.getPassWord()));
			}
			adminOperatorService.updateByPrimaryKey(info);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  AdminOperatorCtrl update error..",e);
		}
		return ReturnInfo.Faild;
	}
	@RequestMapping(value = "/getUserByRole",method = RequestMethod.GET)
	@ResponseBody
	public Object getUserByRole(HttpServletRequest req){
		AdminOperatorExample adminOperatorExample = new AdminOperatorExample();
		adminOperatorExample.createCriteria().andRoleCodeEqualTo("2").andStatusEqualTo(1);//2 一级代销商角色代码
		List<AdminOperator> adminOperators = adminOperatorService.selectByExample(adminOperatorExample);
		return adminOperators;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public Object get(@RequestJsonParam(value = "query",required=false) QueryMapperBean info,
			@RequestJsonParam(value = "fields",required=false) FieldsMapperBean fmb,
			PageInfo para, HttpServletRequest req) {
		int totalCount = 0;
		List<HashMap<String, Object>> list = null;
		try {
			DbCondi dc = new DbCondi();
			dc.setEntityClass(AdminOperator.class);
			dc.setKeyClass(AdminOperatorKey.class);
			dc.setQmb(info);
			dc.setPageinfo(para);
			dc.setFmb(fmb);
			dc.setTalbeName(getTableName());
			if(para.getSkip()!=null||para.getSkip()!=0){
				totalCount = adminOperatorService.getCount(dc);
			}
			list = adminOperatorService.getData(dc);
		} catch (Exception e) {
			log.warn("  AdminOperatorCtrl get error..",e);
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
			List<AdminOperator> list = new ArrayList<AdminOperator>();
			for(String id :data){
				AdminOperator info = new AdminOperator();
				KeyExplainHandler.explainKey(id, info);
				list.add(info);
			}
			adminOperatorService.batchDelete(list);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  AdminOperatorCtrl batchDelete error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/batch",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo batchUpdate(@RequestBody AdminOperators data,HttpServletRequest req) {
		try {
			adminOperatorService.batchUpdate(data);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  AdminOperatorCtrl batchUpdate error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/batch",method=RequestMethod.POST)
	@ResponseBody
	public ReturnInfo batchInsert(@RequestBody AdminOperators data,HttpServletRequest req) {
		try {
			adminOperatorService.batchInsert(data);
			return ReturnInfo.Success;
		} catch (Exception e) {
		    log.warn("  AdminOperatorCtrl batchInsert error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.GET)
	@ResponseBody
	public ListInfo<AdminOperator> get(@PathVariable String key,HttpServletRequest req) {
		int totalCount = 1;
		List<AdminOperator> list = null;
		try {
			AdminOperatorExample aoe = new AdminOperatorExample();
			aoe.createCriteria().andUserNameEqualTo(key);
			list = adminOperatorService.selectByExample(aoe);
		} catch (Exception e) {
			log.warn("  AdminOperatorCtrl get by key error..",e);
		}
		return  new ListInfo<AdminOperator>(totalCount, list, 0, 1);
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.DELETE)
	@ResponseBody
	public ReturnInfo delete(@PathVariable String key,HttpServletRequest req) {
		try {
			AdminOperator info = new AdminOperator();
			KeyExplainHandler.explainKey(key, info);
			adminOperatorService.deleteByPrimaryKey(info);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  AdminOperatorCtrl delete by key error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	@RequestMapping(value="/{key}",method=RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@PathVariable String key,@RequestBody AdminOperator info,HttpServletRequest req) {
		try {
			KeyExplainHandler.explainKey(key, info);
			if(info!=null){
				String passWord = info.getPassWord();
				if(passWord.length()>30){
					info.setPassWord(passWord);
				}else{
					info.setPassWord(DigestUtils.md5Hex(info.getPassWord()));
				}
				adminOperatorService.updateByPrimaryKeySelective(info);
			}
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  AdminOperatorCtrl update by key error..",e);
		}
		return ReturnInfo.Faild;
	}
	
	private String getTableName(){
		return "t_admin_operator";
	}
	
	@SuppressWarnings("serial")
	public static class AdminOperators extends ArrayList<AdminOperator> {  
	    public AdminOperators() { super(); }  
	}
}
