package com.joyveb.auth.restful.ctrl.auth;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.joyveb.cashmanage.entity.AccWhitelistParent;
import com.joyveb.cashmanage.entity.AccWhitelistParentKey;
import com.joyveb.cashmanage.service.AccWhitelistParentService;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyveb.auth.restful.bean.AdminOperator;
import com.joyveb.auth.restful.bean.AdminOperatorExample;
import com.joyveb.auth.restful.service.AdminOperatorService;
import com.joyveb.cashmanage.common.Constants;
import com.joyveb.lbos.restful.common.Constans;
import com.joyveb.lbos.restful.common.ReturnInfo;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminLoginCtrl {

	private @Resource(name = "adminOperatorService") AdminOperatorService adminOperatorService;
	private @Resource(name = "logFlag") String logFlag;
	private @Resource(name = "accWhitelistParentService")
	AccWhitelistParentService accWhitelistParentService;
	@RequestMapping(value = Constans.LOGIN_URL, method = RequestMethod.POST)
	@ResponseBody
	public ReturnInfo chekingLogin(@RequestBody AdminOperator info,
			HttpServletRequest req, HttpSession session) {
		ReturnInfo returnInfo = new ReturnInfo(false);
		try {
			AdminOperatorExample aoe = new AdminOperatorExample();
			aoe.createCriteria().andUserNameEqualTo(info.getUserName());
			List<AdminOperator> list = adminOperatorService.selectByExample(aoe);
			if(list != null && list.size() > 0){
				AccWhitelistParentKey accWhitelistParentKey = new AccWhitelistParent();
				accWhitelistParentKey.setUsername(info.getUserName());
				AccWhitelistParent accWhitelistParent = accWhitelistParentService.selectByPrimaryKey(accWhitelistParentKey);
				AdminOperator result = list.get(0);
				if(result.getStatus() == Constans.INVALID){
					returnInfo.setDescription("用户状态为无效");
					returnInfo.setRetcode(Constants.USER_STATUS_INVALID);
					return returnInfo;
				}
				if(null!=accWhitelistParent&&accWhitelistParent.getStatus().equals("1")){
					returnInfo.setDescription("用户状态为无效");
					returnInfo.setRetcode(Constants.USER_STATUS_INVALID);
					return returnInfo;
				}
				if(result.getPassWord().equals(DigestUtils.md5Hex(info.getPassWord()))){
					Long time = (new Date()).getTime();
					//修改用户表的最后登陆时间
					result.setUserName(info.getUserName());
					result.setLastDate(time);					
					adminOperatorService.updateByPrimaryKey(result);
					session.setAttribute(info.getUserName(), result);
					session.setAttribute(Constans.SESSION_USERNAME_KEY, info.getUserName());
					ReturnInfo successReturn = new ReturnInfo(true);
					successReturn.setRetObj(result);
					return successReturn;
				}else{
					returnInfo.setDescription("密码错误");
					returnInfo.setRetcode(Constants.PASSWORD_ERROR);
				}
			}else{
				returnInfo.setDescription(info.getUserName() + "不存在");
				returnInfo.setRetcode(Constants.USER_NOT_EXIST);
			}
		} catch (Exception e) {
			returnInfo.setDescription("登录错误");
			log.warn("login error..",e);
		}
		return returnInfo;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public ReturnInfo logout(@RequestBody AdminOperator info,
			HttpServletRequest req, HttpSession session) {
		session.removeAttribute(info.getUserName());
		session.removeAttribute(Constans.SESSION_USERNAME_KEY);
		return ReturnInfo.Success;
	}

	@RequestMapping(value = "/changepwd", method = RequestMethod.POST)
	@ResponseBody
	public ReturnInfo changepwd(@RequestBody AdminOperator info,
			HttpServletRequest req) {
		ReturnInfo returnInfo = ReturnInfo.Faild;
		try {
				info.setPassWord(DigestUtils.md5Hex(info.getPassWord()));
				adminOperatorService.updateByPrimaryKey(info);
				returnInfo = ReturnInfo.Success;
		} catch (Exception e) {
			returnInfo = ReturnInfo.Faild;
			log.warn("change password error..",e);
		}
		return returnInfo;
	}
	
	@RequestMapping(value = "/checkAdminPwd", method = RequestMethod.POST)
	@ResponseBody
	public ReturnInfo checkAdminPwd(@RequestBody AdminOperator info,
			HttpServletRequest req) {
		try {
			Object loginname = req.getSession().getAttribute("username");
			AdminOperatorExample aoe = new AdminOperatorExample();
			aoe.createCriteria().andUserNameEqualTo(loginname.toString());
			aoe.createCriteria().andPassWordEqualTo(DigestUtils.md5Hex(info.getPassWord()));
			int count = adminOperatorService.countByExample(aoe);
			if(count==1){
				return ReturnInfo.Success;
			}
		} catch (Exception e) {
			log.warn("change password error..",e);
			return ReturnInfo.Faild;
		}
		return ReturnInfo.Faild;
	}
	

	@SuppressWarnings("serial")
	public static class AdminOperators extends ArrayList<AdminOperator> {
		public AdminOperators() {
			super();
		}
	}
	
	private  String getCustomerIp(HttpServletRequest request) {
		  String ip = request.getHeader("X-Real-IP");      
          if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
              ip = request.getHeader("X-Forwarded-For");      
          }      
          if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
        	  ip = request.getHeader("Proxy-Client-IP");      
          }      
          if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
              ip = request.getHeader("WL-Proxy-Client-IP");      
           }      
         if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
               ip = request.getRemoteAddr();      
          }      
//         	System.out.println("##"+request.getRemoteHost());
       //当操作系统支持：IPv6时，localhost的的IP会用IPv6表示。
       //可以改系统的host，担心部署的时候忘记，故在此转换
         if(ip.equals("0:0:0:0:0:0:0:1")){
        	 ip="127.0.0.1";
         }
         return ip;     
    }
}
