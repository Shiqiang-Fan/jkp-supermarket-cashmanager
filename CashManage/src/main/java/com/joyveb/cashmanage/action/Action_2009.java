package com.joyveb.cashmanage.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.AbstractEntity;
import com.joyveb.cashmanage.bean.TransMessageEntity;
import com.joyveb.cashmanage.bean.request.ReqSumDateEntity;
import com.joyveb.cashmanage.bean.request.ReqSumDateHolder;
import com.joyveb.cashmanage.bean.response.RepQuerySumEntity;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.entity.OrderInfo;
import com.joyveb.cashmanage.entity.OrderInfoExample;
import com.joyveb.cashmanage.entity.WhiteList;
import com.joyveb.cashmanage.service.OrderInfoService;
import com.joyveb.cashmanage.service.PrizerService;
import com.joyveb.cashmanage.service.ShopInfoService;
import com.joyveb.cashmanage.utils.JsonUtil;
import com.joyveb.cashmanage.validate.Validateable;

/**
 * 
 * 统计
 * 
 */
@Slf4j
@Component("action_2009")
public class Action_2009 extends AbstractReqAction {
	private @Resource(name = "validator_2002")
	Validateable validator;
	@Resource(name = "prizerService")
	private PrizerService prizerService;
	@Resource(name = "orderInfoService")
	private OrderInfoService orderInfoService;
	@Resource(name = "shopInfoService")
	private ShopInfoService shopInfoService;

	@Override
	public AbstractEntity process(TransMessageEntity msg) {
		WhiteList whiteList = getTokenMap().get(msg.getRequest().getHeader("token"));
		RepQuerySumEntity entity = new RepQuerySumEntity();
		ReqSumDateEntity body = (ReqSumDateEntity) msg.getBody();
		String starttime = body.getStarttime();
		String endtime = body.getEndtime();
		long sta = 0L;
		long ent = 0L;
		try {
			if(null!=starttime){
				sta = sim.parse(starttime).getTime();
				ent = sim.parse(endtime).getTime();
			}
			} catch (ParseException e) {
			log.warn("日期转换异常",e);
		}
		if (null != whiteList) {
			if (null != body) {
				String pubsql = " where REQUESTTIME BETWEEN '" + sta + "' and '" + ent + "' and USERID = " + whiteList.getUsername();
				String sumsql = "select sum(WINAMOUNT) sumamount from t_core_prizer" + pubsql + " and TRANSFERSTATUS = 2";
				List<HashMap<String, Object>> dosql = prizerService.dosql(sumsql);
				if (null != dosql && dosql.size() > 0) {
					HashMap<String, Object> hashMap = dosql.get(0);
					if (null != hashMap) {
						Object object = hashMap.get("sumamount");
						if (null != object) {
							String sumamount = object.toString();
							entity.setSumamount(sumamount);
						}
					} else {
						entity.setSumamount("0");
					}
				}
				String countsql = "select count(*) sumcount from t_core_prizer" + pubsql + " and AMOUNTSIGN = 0";
				List<HashMap<String, Object>> countl = prizerService.dosql(countsql);
				if (null != countl && countl.size() > 0) {
					HashMap<String, Object> hashMap = countl.get(0);
					if (null != hashMap) {
						Object object = hashMap.get("sumcount");
						if (null != object) {
							String sumcount = object.toString();
							entity.setSumcount(sumcount);
						}
					} else {
						entity.setSumcount("0");
					}
				}
				String sumwin = "select sum(WINAMOUNT) sumamount from t_core_prizer" + pubsql + " and AMOUNTSIGN = 0";
				List<HashMap<String, Object>> sm = prizerService.dosql(sumwin);
				if (null != sm && sm.size() > 0) {
					HashMap<String, Object> hashMap = sm.get(0);
					if (null != hashMap) {
						Object object = hashMap.get("sumamount");
						if (null != object) {
							String smcount = object.toString();
							entity.setSumdj(smcount);
						}
					} else {
						entity.setSumdj("0");
					}

				}
				List<String> values = new ArrayList<String>();
				values.add("1");
				values.add("3");
				values.add("7");
				OrderInfoExample oie = new OrderInfoExample();
				oie.createCriteria().andOrderstatusNotIn(values).andUseridEqualTo(whiteList.getUsername())
						.andOrdertimeBetween(sta, ent);
				int am = 0;
				List<OrderInfo> selectByExample = orderInfoService.selectByExample(oie);
				if (null != selectByExample && selectByExample.size() > 0) {
					for (int i = 0; i < selectByExample.size(); i++) {
						OrderInfo orderInfo = selectByExample.get(i);
						am += orderInfo.getTotalamount();
						/*
						 * ShopInfoExample sik = new ShopInfoExample();
						 * sik.createCriteria().andOrderidEqualTo(orderInfo.getOrderid()); List<ShopInfo>
						 * selectByExample2 = shopInfoService.selectByExample(sik);
						 * if(null!=selectByExample2&&selectByExample2.size()>0){ for(int
						 * y=0;y<selectByExample2.size();y++){ ShopInfo shopInfo = selectByExample2.get(y); am +=
						 * shopInfo.getOrderamount(); } }
						 */
					}
				}
				entity.setOrderamount(am);
			}
			entity.setRcode(ResponseResult.SUCCESS.getCode());
			entity.setDes(ResponseResult.SUCCESS.getDesc());
		} else {
			log.info(ResponseResult.NOTLOGIN.getDesc());
			processValidFailCondi(entity, ResponseResult.NOTLOGIN.getCode());
			entity.setDes(ResponseResult.NOTLOGIN.getDesc());
		}
		return entity;
	}

	private void processValidFailCondi(RepQuerySumEntity entity, String resultcode) {
		entity.setRcode(resultcode);
	}

	@Override
	public AbstractEntity processValidFail(ResponseResult vr) {
		RepQuerySumEntity rce = new RepQuerySumEntity();
		rce.setRcode(ResponseResult.PARAM_ERROR.getCode());
		return rce;
	}

	@Override
	public AbstractEntity parseMessageBody(String reqjson) throws Exception {
		ReqSumDateHolder holder = JsonUtil.json2Bean(reqjson, ReqSumDateHolder.class);
		return holder.getBody();
	}

	@Override
	public ResponseResult validate(TransMessageEntity msg) {
		return validator.validate(msg);
	}

}
