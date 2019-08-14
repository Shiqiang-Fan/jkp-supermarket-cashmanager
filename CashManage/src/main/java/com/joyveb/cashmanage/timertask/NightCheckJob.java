package com.joyveb.cashmanage.timertask;

import com.joyveb.cashmanage.entity.AccNeightCheck;
import com.joyveb.cashmanage.entity.AccWhitelistParent;
import com.joyveb.cashmanage.entity.AccWhitelistParentExample;
import com.joyveb.cashmanage.service.AccNeightCheckService;
import com.joyveb.cashmanage.service.AccWhitelistParentService;
import com.joyveb.cashmanage.utils.CommonUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.math.BigDecimal;
import java.util.List;

public class NightCheckJob implements Job {
	@Override
	public void execute(JobExecutionContext arg0) {
		//夜对定时任务 每天计算所有一级代销商 当天佣金 - 当天提现 = 当天可提现金额 入夜对表
		AccNeightCheckService accNeightCheckService = (AccNeightCheckService) arg0.getJobDetail().getJobDataMap().get("accNeightCheckService");
		AccWhitelistParentService accWhitelistParentService = (AccWhitelistParentService) arg0.getJobDetail().getJobDataMap().get("accWhitelistParentService");
		AccWhitelistParentExample accWhitelistParentExample = new AccWhitelistParentExample();
		accWhitelistParentExample.createCriteria().andStatusEqualTo("0");
		List<AccWhitelistParent> accWhitelistParents = accWhitelistParentService.selectByExample(accWhitelistParentExample);
		for(AccWhitelistParent accWhitelistParent : accWhitelistParents){
			//sum今日佣金-sum今日提现=今日可提现金额
			BigDecimal todayCommissionsMoney = accNeightCheckService.countCommissionsMoney(CommonUtils.returnYYMMDD(),accWhitelistParent.getUsername());
			BigDecimal todayLogMoney = accNeightCheckService.countLogMoney(CommonUtils.returnYYMMDD(),accWhitelistParent.getUsername());
			BigDecimal todaySubtract = todayCommissionsMoney.subtract(todayLogMoney);
			AccNeightCheck accNeightCheck = new AccNeightCheck();
			accNeightCheck.setUsername(accWhitelistParent.getUsername());
			accNeightCheck.setMoney(todaySubtract);
			accNeightCheck.setDate(CommonUtils.returnYYMMDD());
			accNeightCheckService.insert(accNeightCheck);
		}
	}
}
