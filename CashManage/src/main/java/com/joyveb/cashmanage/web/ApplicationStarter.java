package com.joyveb.cashmanage.web;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.joyveb.cashmanage.common.Cacheable;
import com.joyveb.cashmanage.entity.WhiteList;
import com.joyveb.cashmanage.timertask.QuartzTaskManager;
import com.joyveb.cashmanage.timertask.ReturnAward;
import com.joyveb.cashmanage.utils.CopyImg;
import com.joyveb.cashmanage.utils.HttpRequestor;

@Slf4j
public class ApplicationStarter implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		HttpRequestor httpRequestor = ctx.getBean("httpRequestor", HttpRequestor.class);
		ReturnAward returnAward = ctx.getBean("returnAward",ReturnAward.class);
		InitParm initParm = ctx.getBean("initParm",InitParm.class);
		QuartzTaskManager quartzTaskManager = ctx.getBean("quartzTaskManager",QuartzTaskManager.class);
		CopyImg copyImg = ctx.getBean("copyRequestor",CopyImg.class);
		@SuppressWarnings("unchecked")
		Map<String, Cacheable> map = ctx.getBean("cacheMap",Map.class);
		map.get("prizeSessionsCache").reload();
		//初始化dbp配置参数到map
		initParm.initDbp();
		//初始化游戏信息
		initParm.initGame();
		//初始化白名单
		initParm.initWhiteList();
		//初始化终端信息
		initParm.initTerminal();
		//初始化项目中游戏图片
		copyImg.initCopy();
		//活取白名单
		List<WhiteList> initWhiteList = initParm.initWhiteList();
		//检查transfer表处理中和失败的转账
		returnAward.transferTrans();
		//检查prizer表未转账的转账
		returnAward.transferPrizer(initWhiteList,1);
		//启动返奖定时任务
		quartzTaskManager.process();
		
		httpRequestor.reload();
		log.debug("CashManage系统启动完成");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		HttpRequestor httpRequestor = ctx.getBean("httpRequestor", HttpRequestor.class);
		httpRequestor.destroy();
		log.debug("CashManage系统关闭完成");
	}
}
