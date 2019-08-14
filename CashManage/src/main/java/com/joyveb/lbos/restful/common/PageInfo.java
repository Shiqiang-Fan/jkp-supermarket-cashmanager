package com.joyveb.lbos.restful.common;

import lombok.Setter;

/**  
 * Copyright © 2014畅享互联.
 *
 * @Title: DbPara.java
 * @Prject: LBOS
 * @Package: com.joyveb.lbos.restful.common
 * @date: 2014年8月19日 下午9:28:37
 * @author: yangqiju 
 */
public class PageInfo {

	private @Setter boolean page;
	private @Setter Integer skip;
	private @Setter Integer limit;
	private @Setter String sort;
	public Integer getSkip() {
		return skip==null?0:skip;
	}
	public Integer getLimit() {
		return limit==null?35:limit;
	}
	public String getSort() {
		return sort;
	}
	public boolean isPage() {
		return page;
	}
	
}
