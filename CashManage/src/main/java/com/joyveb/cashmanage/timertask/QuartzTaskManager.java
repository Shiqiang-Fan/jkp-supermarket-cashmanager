package com.joyveb.cashmanage.timertask;

import com.joyveb.cashmanage.action.Action_1001;
import com.joyveb.cashmanage.action.Action_2006;
import com.joyveb.cashmanage.service.AccNeightCheckService;
import com.joyveb.cashmanage.service.AccWhitelistParentService;
import com.joyveb.cashmanage.utils.IDGenerator;
import com.joyveb.cashmanage.web.InitParm;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * 定时任务管理
 */
@Slf4j
@Component("quartzTaskManager")
public class QuartzTaskManager {

	@Resource(name = "executor")
	private ThreadPoolTaskExecutor executor;
	@Resource(name = "scheduler")
	private Scheduler scheduler;
	@Resource(name = "initParm")
	private InitParm initParm;
	@Resource(name = "action_1001")
	private Action_1001 action_1001;
	@Resource(name = "action_2006")
	private Action_2006 action_2006;
	@Resource(name = "returnAward")
	private ReturnAward returnAward;;
	@Resource(name = "accNeightCheckService")
	private AccNeightCheckService accNeightCheckService;
	@Resource(name = "accWhitelistParentService")
	private AccWhitelistParentService accWhitelistParentService;
	public void stopAllJob(String id) {
		stopJob(id);
	}

	private void setQuartzExecutorSize(int size) {
		executor.setCorePoolSize(size);
		executor.setMaxPoolSize(size);
	}

	public void startJob(String key) {
		try {
			JobDetail detail = new JobDetail();
			detail.getJobDataMap().put("initParm", initParm);
			detail.getJobDataMap().put("returnAward", returnAward);
			detail.setName(key);
			detail.setJobClass(InstertJob.class);
			scheduler.addJob(detail, true);
			CronTrigger cronTrigger = new CronTrigger(key, Scheduler.DEFAULT_GROUP, detail.getName(), Scheduler.DEFAULT_GROUP);
			cronTrigger.setCronExpression(getQuartzTime(initParm.getStringDbp("quartztime", "2")));
			scheduler.scheduleJob(cronTrigger);
			log.info("返奖定时任务 加载完成" + key + "detail.getName() " + detail.getName());
		} catch (SchedulerException e) {
			log.debug("返奖定时任务发生异常",e);
		} catch (ParseException e) {
			log.debug("返奖定时任务类型转换异常",e);
		}
	}

	public void startNightCheck(String key) {
		try {
			JobDetail detail = new JobDetail();
			detail.getJobDataMap().put("accNeightCheckService", accNeightCheckService);
			detail.getJobDataMap().put("accWhitelistParentService", accWhitelistParentService);
			detail.setName(key);
			detail.setJobClass(NightCheckJob.class);
			scheduler.addJob(detail, true);
			CronTrigger cronTrigger = new CronTrigger(key, Scheduler.DEFAULT_GROUP, detail.getName(), Scheduler.DEFAULT_GROUP);
			cronTrigger.setCronExpression(getQuartzTime(initParm.getStringDbp("night.check.quartztime", "23")));
			scheduler.scheduleJob(cronTrigger);
			log.info("提现夜对任务加载完成" + key + "detail.getName() " + detail.getName());
		} catch (SchedulerException e) {
			log.debug("提现夜对任务发生异常",e);
		} catch (ParseException e) {
			log.debug("提现夜对任务类型转换异常",e);
		}
	}

	public void startPayStatusJob(String key) {
		try {
			JobDetail detail = new JobDetail();
			detail.getJobDataMap().put("initParm", initParm);
			detail.getJobDataMap().put("action_2006", action_2006);
			detail.getJobDataMap().put("returnAward", returnAward);
			detail.setName(key);
			detail.setJobClass(PayStatusJob.class);
			scheduler.addJob(detail, true);
			CronTrigger cronTrigger = new CronTrigger(key, Scheduler.DEFAULT_GROUP, detail.getName(), Scheduler.DEFAULT_GROUP);
			cronTrigger.setCronExpression(getQuartzTimeBySeconds(initParm.getStringDbp("pay.status.time", "5")));
			scheduler.scheduleJob(cronTrigger);
			log.info("轮询订单状态定时任务 加载完成" + key + "detail.getName() " + detail.getName());
		} catch (SchedulerException e) {
			log.debug("轮询订单状态定时任务发生异常",e);
		} catch (ParseException e) {
			log.debug("轮询订单状态定时任务类型转换异常",e);
		}

	}

	public void startTokenJob(String key) {
		try {
			JobDetail detail = new JobDetail();
			detail.setName(key);
			detail.getJobDataMap().put("action_1001", action_1001);
			detail.getJobDataMap().put("initParm", initParm);
			detail.setJobClass(TokenJob.class);
			scheduler.addJob(detail, true);
			CronTrigger cronTrigger = new CronTrigger(key, Scheduler.DEFAULT_GROUP, detail.getName(), Scheduler.DEFAULT_GROUP);
			cronTrigger.setCronExpression(getQuartzTimeByHour(initParm.getStringDbp("token.check.time", "2")));
			scheduler.scheduleJob(cronTrigger);
			log.info("登录token定时任务 加载完成" + key + "detail.getName() " + detail.getName());
		} catch (SchedulerException e) {
			log.debug("登录token定时任务发生异常",e);
		} catch (ParseException e) {
			log.debug("登录token定时任务类型转换异常",e);
		}

	}

	public void stopJob(String key) {
		try {
			scheduler.unscheduleJob(key, Scheduler.DEFAULT_GROUP);
			scheduler.shutdown(true);
			System.err.println("定时任务停止完成" + key);
		} catch (SchedulerException e) {
			log.debug("定时任务停止异常",e);
		}
	}

	public void reloadJob(String key) {

		try {
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(key, Scheduler.DEFAULT_GROUP);
			if (trigger != null) {
				String originConExpression = trigger.getCronExpression();
				// 如果相等，则表示用户并没有重新设定数据库中的任务时间，这种情况不需要重新rescheduleJob
				if (!originConExpression.equalsIgnoreCase(initParm.getStringDbp("quartztime", "5 * * * * ?"))) {
					trigger.setCronExpression(initParm.getStringDbp("quartztime", "5 * * * * ?"));
					scheduler.rescheduleJob(key, Scheduler.DEFAULT_GROUP, trigger);
				}
			} else {
				setQuartzExecutorSize(executor.getCorePoolSize() + 1);
				startJob(key);
			}
			System.err.println("定时任务 reload完成");
		} catch (SchedulerException e) {
			log.debug("定时任务reload异常",e);
		} catch (ParseException e) {
			log.debug("定时任务reload类型转换异常",e);
		}

	}

	/**
	 * 时间格式 小时
	 */
	private String getQuartzTime(String time) {
		String timer = "0 0 " + time + " * * ?";
		return timer;
	}

	// 每多少分钟
	private String getQuartzTimeByHour(String time) {
		String timer = "0 0/" + time + " * * * ?";
		return timer;
	}

	// 每多少秒
	private String getQuartzTimeBySeconds(String time) {
		String timer = "*/" + time + " * * * * ?";
		return timer;
	}

	public void process() {
		// 启动返奖定时任务
		startJob(IDGenerator.getInstance().generate());
		// 启动轮询扫码支付状态
		startPayStatusJob(IDGenerator.getInstance().generate());
		// 启动登录token有效期检查
		startTokenJob(IDGenerator.getInstance().generate());
		//提现夜对
		startNightCheck(IDGenerator.getInstance().generate());
	}
}
