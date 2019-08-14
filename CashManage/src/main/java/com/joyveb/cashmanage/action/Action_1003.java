package com.joyveb.cashmanage.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.AbstractEntity;
import com.joyveb.cashmanage.bean.TransMessageEntity;
import com.joyveb.cashmanage.bean.request.ReqCashQueryEntity;
import com.joyveb.cashmanage.bean.request.ReqCashQueryEntityHolder;
import com.joyveb.cashmanage.bean.response.RepCashInfo;
import com.joyveb.cashmanage.bean.response.RepCashQueryEntity;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.entity.PrizerExample;
import com.joyveb.cashmanage.entity.WhiteList;
import com.joyveb.cashmanage.service.PrizerService;
import com.joyveb.cashmanage.utils.JsonUtil;
import com.joyveb.cashmanage.validate.Validateable;

/**
 * 
 * 兑奖记录查询
 * 
 */
@Slf4j
@Component("action_1003")
public class Action_1003 extends AbstractReqAction {
	private @Resource(name = "validator_2002")
	Validateable validator;
	@Resource(name = "prizerService")
	private PrizerService prizerService;

	@Override
	public AbstractEntity process(TransMessageEntity msg) {
		RepCashQueryEntity repCashQueryEntity = new RepCashQueryEntity();
		String isFj = "";
		String total = "";
		String count = "";
		ReqCashQueryEntity body = (ReqCashQueryEntity) msg.getBody();
		if (null == body) {
			repCashQueryEntity.setDes("查询兑奖记录参数为空");
			log.info("查询兑奖记录参数为空");
		} else {
			if (!body.getType().equals("0")) {
				isFj = " and p.TRANSFERSTATUS = " + body.getType();
			}
		}
		String curd = ymd.format(new Date());
		String cs = curd + " 00:00:00"; 
		String es = curd + " 23:59:59";
		Long cd = 0L;
		Long ed = 0L;
		try {
			cd = sim.parse(cs).getTime();
			ed = sim.parse(es).getTime();
		} catch (ParseException e1) {
			log.warn("兑奖记录时间格式转换异常", e1);
		}
		Map<String, WhiteList> tokenMap = getTokenMap();
		WhiteList whiteList = tokenMap.get(msg.getRequest().getHeader("token"));
		log.debug(msg.getRequest().getHeader("token"));
		if (null != whiteList) {
			String username = whiteList.getUsername();
			String sql2 = "select sum(WINAMOUNT) AS curamount from  t_core_prizer where userid = " + username
					+ " AND REQUESTTIME BETWEEN " + cd + " AND "+ ed;
			String sql = "select p.TRANSFERSTATUS AS 'transferflag',p.REQUESTTIME AS 'requesttime',p.WINAMOUNT AS "
					+ "'winamount',p.AMOUNTSIGN AS 'amountsign',p.BARCODE AS 'gameinfo'" + " from t_core_prizer p"
					+ " where p.AMOUNTSIGN = 0 and p.USERID = " + username + isFj+ " ORDER BY requesttime DESC ";
			List<HashMap<String, Object>> totalList = prizerService.dosql("select count(*) AS 'total' from t_core_prizer where userid = " + username);
			List<HashMap<String, Object>> countList = prizerService.dosql("select count(*) AS 'count' from t_core_prizer p where userid = "
					+ username + " AND AMOUNTSIGN = 0" + " AND p.REQUESTTIME BETWEEN " + cd + " AND "+ ed);
			List<HashMap<String, Object>> list = prizerService.dosql(sql);
			List<HashMap<String, Object>> list2 = prizerService.dosql(sql2);
			PrizerExample prizerExample = new PrizerExample();
			try {
				prizerExample.createCriteria().andAmountsignEqualTo(isFj).andRequesttimeEqualTo(System.currentTimeMillis());
				repCashQueryEntity.setCurday(ymd.parse(ymd.format(new Date())));
			} catch (ParseException e) {
				log.warn("兑奖记录时间格式转换异常", e);
			}
			List<RepCashInfo> cashList = new ArrayList<RepCashInfo>();
			if (null != totalList) {
				total = String.valueOf(totalList.get(0).get("total"));
			}
			if (null != countList) {
				count = String.valueOf(countList.get(0).get("count"));
			}

			if (null != list) {
				for (int i = 0; i < list.size(); i++) {
					RepCashInfo repCashInfo = new RepCashInfo();
					HashMap<String, Object> prizeMap = list.get(i);
					try {
						repCashQueryEntity.setCurday(sim.parse(sim.format(new Date())));
						repCashInfo.setTime(Long.parseLong(prizeMap.get("requesttime").toString()));
					} catch (ParseException e) {
						log.warn("兑奖记录查询日期转换异常", e);
					}
					repCashInfo.setAmountsign(prizeMap.get("amountsign").toString());
					repCashInfo.setGameInfo(prizeMap.get("gameinfo").toString());
					repCashInfo.setTransferStatus(prizeMap.get("transferflag").toString());
					if (null != prizeMap.get("winamount")) {
						int parseDouble = Integer.parseInt(prizeMap.get("winamount").toString());
						// double a= parseDouble / 100;
						repCashInfo.setWinAmount(parseDouble + "");
					}
					cashList.add(repCashInfo);
				}
				repCashQueryEntity.setCount(list.size());
				if (null != list2 && list2.size() != 0) {
					HashMap<String, Object> listt = list2.get(0);
					if (null != listt && listt.size() != 0) {
						repCashQueryEntity.setCurdayAmount(Long.parseLong(listt.get("curamount").toString()));
					} else {
						repCashQueryEntity.setCurdayAmount(0L);
					}
				}
				repCashQueryEntity.setRcode(ResponseResult.SUCCESS.getCode());
				repCashQueryEntity.setCurdayTotal(Integer.parseInt(count));
				repCashQueryEntity.setRecords(cashList);
				repCashQueryEntity.setTotal(Integer.parseInt(total));
				try {
					repCashQueryEntity.setCurday(sim.parse(sim.format(new Date())));
				} catch (ParseException e) {
					log.warn("兑奖记录查询日期转换异常", e);
				}
			}
		} else {
			log.info("该账户未登录,token无效");
			repCashQueryEntity.setRcode(ResponseResult.NOTLOGIN.getCode());
			repCashQueryEntity.setDes(ResponseResult.NOTLOGIN.getDesc());
		}
		return repCashQueryEntity;
	}

	@Override
	public AbstractEntity processValidFail(ResponseResult vr) {
		log.info(vr.getCode() + " " + vr.getDesc());
		RepCashQueryEntity repCashQueryEntity = new RepCashQueryEntity();
		return repCashQueryEntity;
	}

	@Override
	public AbstractEntity parseMessageBody(String reqjson) throws Exception {
		ReqCashQueryEntityHolder holder = JsonUtil.json2Bean(reqjson, ReqCashQueryEntityHolder.class);
		return holder.getBody();
	}

	@Override
	public ResponseResult validate(TransMessageEntity msg) {
		return validator.validate(msg);
	}
}
