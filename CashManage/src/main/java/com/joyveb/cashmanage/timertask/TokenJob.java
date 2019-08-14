package com.joyveb.cashmanage.timertask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import com.joyveb.cashmanage.action.Action_1001;
import com.joyveb.cashmanage.entity.WhiteList;
import com.joyveb.cashmanage.web.InitParm;
@Slf4j
public class TokenJob implements Job {
	@Override
	public void execute(JobExecutionContext arg0) {
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Action_1001 action_1001 = (Action_1001) arg0.getJobDetail().getJobDataMap().get("action_1001");
		InitParm initParm = (InitParm) arg0.getJobDetail().getJobDataMap().get("initParm");
		Map<String, WhiteList> tokenMap = action_1001.getTokenMap();
		/*
		 * for(String key : tokenMap.keySet()){ log.debug("mapKEY::"+key+"   mapVALUE:::"+tokenMap.get(key)); }
		 */
		Iterator<Entry<String, WhiteList>> iterator = tokenMap.entrySet().iterator();
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();
		if (tokenMap.size() != 0) {
			while (iterator.hasNext()) {
				Entry<String, WhiteList> next = iterator.next();
				WhiteList whiteList = next.getValue();
				Long loginTime = whiteList.getLogintime();
				int intDbp = initParm.getIntDbp("token.expiry.date", 8);
				// log.debug("key::"+next.getKey()+"  value::"+alipayUserInfo);
				// log.debug("token有效期：："+intDbp+"分钟");
				try {
					calendar.setTime(sim.parse(sim.format(loginTime)));
					calendar.add(calendar.MINUTE, intDbp);
					if (calendar.getTimeInMillis() - calendar1.getTimeInMillis() < 0) {
						tokenMap.remove(next.getKey());
					}
				} catch (ParseException e) {
					log.warn("日期转换异常",e);
				}
			}
		}
	}

}
