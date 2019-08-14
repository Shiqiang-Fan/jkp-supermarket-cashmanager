package com.joyveb.auth.restful.ctrl.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.joyveb.auth.restful.bean.AdminPermission;
import com.joyveb.auth.restful.bean.MenuInfo;
import com.joyveb.auth.restful.bean.MenuInfo.SubMenu;
import com.joyveb.auth.restful.service.AdminPermissionService;
import com.joyveb.lbos.restful.common.PageInfo;
import com.joyveb.lbos.restful.spring.FieldsMapperBean;
import com.joyveb.lbos.restful.spring.QueryMapperBean;
import com.joyveb.lbos.restful.spring.RequestJsonParam;

@Slf4j
@Controller
@RequestMapping("/menu")
public class MenuCtrl {

	private @Resource(name = "adminPermissionService") AdminPermissionService adminPermissionService;
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	@ResponseBody
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object get(
			@RequestJsonParam(value = "query", required = false) QueryMapperBean queryMapperBean,
			@RequestJsonParam(value = "fields", required = false) FieldsMapperBean fmb,
			PageInfo para, HttpServletRequest req, HttpSession session) {
		List<MenuInfo> menuInfos = new ArrayList<MenuInfo>();
		try {
			String username = session.getAttribute("username") !=null ? 
					session.getAttribute("username").toString() : "";
			if(StringUtils.isNotBlank(username)){
				String sql = "SELECT * FROM t_admin_permission "
						+ "WHERE ID IN (SELECT PERMISSION_ID "
						+ "FROM t_admin_role_permission WHERE ROLE_CODE IN ("
						+ "SELECT ROLE_CODE FROM t_admin_operator WHERE USER_NAME= '"
						+ username + "') AND STATUS = 1) OR ID IN (SELECT pid FROM t_admin_permission"
						+ " WHERE id IN(SELECT PERMISSION_ID FROM t_admin_role_permission "
						+ " WHERE ROLE_CODE IN (SELECT ROLE_CODE FROM t_admin_operator"
						+ " WHERE USER_NAME= '" + username + "') AND STATUS = 1))";
				List<HashMap<String, Object>> result = adminPermissionService.dosql(sql);
				Map<Integer, AdminPermission> aps = new HashMap<Integer, AdminPermission>();
				Map<Integer, MenuInfo> prtMenus = new HashMap<Integer, MenuInfo>();// id - mainmenu
				if (result == null || result.size() <= 0) {
					return menuInfos;
				}
	
				for (HashMap<String, Object> menu : result) {
					AdminPermission ap = new AdminPermission();
					ap.setId(Integer.valueOf(menu.get("ID").toString()));
					ap.setPid(Integer.valueOf(menu.get("PID").toString()));
					ap.setLeaf(Integer.valueOf(menu.get("LEAF").toString()));
					ap.setName(menu.get("NAME").toString());
					Object orderid = menu.get("ORDER_ID");
					ap.setOrderId(orderid != null ? Integer.valueOf(orderid
							.toString()) : 100);
					Object url = menu.get("URL");
					ap.setUrl(url != null ? url.toString() : "");
					Object icon = menu.get("ICON");
					ap.setIcon(icon != null ? icon.toString() : "");
					if (ap.getLeaf() == 0) {// 主菜单
						MenuInfo info = new MenuInfo();
						info.set_id(ap.getId().toString());
						info.setName(ap.getName());
						info.setIcon(ap.getIcon());
						info.setOrdernum(ap.getOrderId());
						boolean isSet = false;
						for (int i = 0; i < menuInfos.size(); i++) {
							if (menuInfos.get(i).getOrdernum() > ap.getOrderId()) {
								isSet = true;
								menuInfos.add(i, info);
								break;
							}
						}
						if (!isSet) {
							menuInfos.add(menuInfos.size(), info);
						}
						prtMenus.put(ap.getId(), info);
					} else {
						aps.put(ap.getId(), ap);// 子菜单
					}
				}
				for (Map.Entry<Integer, AdminPermission> ap : aps.entrySet()) {
					MenuInfo pmi = prtMenus.get(ap.getValue().getPid());
					List<SubMenu> subMenus = pmi.getChildren();
					SubMenu subMenu = new SubMenu();
					subMenu.setName(ap.getValue().getName());
					subMenu.setOrdernum(ap.getValue().getOrderId());
					subMenu.setUrl(ap.getValue().getUrl());
					subMenu.setIcon(ap.getValue().getIcon());
					subMenu.setId(ap.getValue().getId());
					boolean isSet = false;
					for (int i = 0; i < subMenus.size(); i++) {
						if (subMenus.get(i).getOrdernum() > ap.getValue()
								.getOrderId()) {
							isSet = true;
							subMenus.add(i, subMenu);
							break;
						}
					}
					if (!isSet) {
						subMenus.add(subMenus.size(), subMenu);
					}
				}
				prtMenus.clear();
				prtMenus = null;
				aps.clear();
				aps = null;
				result.clear();
				result = null;
			}
		} catch (Exception e) {
			log.warn("MenuCtrl get parameter error.", e);
		}
		return menuInfos;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String menu(HttpSession session){
		
		String username = session.getAttribute("username") !=null ? 
				session.getAttribute("username").toString() : "";
		if(StringUtils.isBlank(username)){
			return "/menu/failed";
		}
		
		return "/menu/success";
	}
	
	
	@RequestMapping(value = "/failed", method = RequestMethod.GET)
	@ResponseStatus(value = org.springframework.http.HttpStatus.FORBIDDEN)
	public void failed(){
		
	}
	
}
