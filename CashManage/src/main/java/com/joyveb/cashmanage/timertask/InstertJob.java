package com.joyveb.cashmanage.timertask;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import sun.util.logging.resources.logging;

import com.joyveb.cashmanage.entity.WhiteList;
import com.joyveb.cashmanage.web.InitParm;
@Slf4j
public class InstertJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) {
		ReturnAward returnAward = (ReturnAward) arg0.getJobDetail().getJobDataMap().get("returnAward");
		InitParm initParm = (InitParm) arg0.getJobDetail().getJobDataMap().get("initParm");
		List<WhiteList> list = initParm.initWhiteList();
		returnAward.transferTrans();// 检查转账失败的 重新转
		returnAward.transferPrizer(list, 0);// 转账
	}
}
