package com.joyveb.cashmanage.timertask;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import com.joyveb.cashmanage.action.Action_2006;
import com.joyveb.cashmanage.entity.OrderInfo;

public class PayStatusJob implements Job {
	@Override
	public void execute(JobExecutionContext arg0) {
		Action_2006 action_2006 = (Action_2006) arg0.getJobDetail().getJobDataMap().get("action_2006");
		ReturnAward returnAward = (ReturnAward) arg0.getJobDetail().getJobDataMap().get("returnAward");
		Map<String, OrderInfo> orderMap = action_2006.getOrderMap();
		if (null != orderMap && orderMap.size() != 0) {
			Iterator<Entry<String, OrderInfo>> iterator = orderMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, OrderInfo> next = iterator.next();
				OrderInfo orderInfo = next.getValue();
				Long ordertime = orderInfo.getOrdertime();
				long time = ordertime + 60000;// 下单时间加一分钟 等待一分钟 支付宝主动通知后再查询支付状态
				if (System.currentTimeMillis() > time) {
					returnAward.queryPayStatus(orderInfo, orderMap);
				}
			}
			/*
			 * for(String key : orderMap.keySet()){ log.debug("mapKEY::"+key+"   mapVALUE:::"+orderMap.get(key)); }
			 */
		}
	}
}
