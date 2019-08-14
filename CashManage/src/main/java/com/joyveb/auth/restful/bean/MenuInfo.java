package com.joyveb.auth.restful.bean;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 主菜单信息
 * 
 */
public @Data class MenuInfo {
	private String _id;
	private String name;
	private String icon;
	private Integer ordernum;
	private List<SubMenu> children = new ArrayList<SubMenu>();

	public static @Data class SubMenu {
		private String name;
		private String url;
		private Integer ordernum;
		private String icon;
		private Integer id;
	}
}
