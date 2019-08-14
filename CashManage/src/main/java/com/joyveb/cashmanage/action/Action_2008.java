package com.joyveb.cashmanage.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.AbstractEntity;
import com.joyveb.cashmanage.bean.TransMessageEntity;
import com.joyveb.cashmanage.bean.request.ReqOrderIdEntity;
import com.joyveb.cashmanage.bean.request.ReqOrderIdHolder;
import com.joyveb.cashmanage.bean.response.RepGameEntity;
import com.joyveb.cashmanage.bean.response.RepOrderEntity;
import com.joyveb.cashmanage.bean.response.RepXqOrderEntity;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.entity.GameInfo;
import com.joyveb.cashmanage.entity.OrderInfo;
import com.joyveb.cashmanage.entity.OrderInfoExample;
import com.joyveb.cashmanage.entity.ShopInfo;
import com.joyveb.cashmanage.entity.ShopInfoExample;
import com.joyveb.cashmanage.entity.WhiteList;
import com.joyveb.cashmanage.service.OrderInfoService;
import com.joyveb.cashmanage.service.ShopInfoService;
import com.joyveb.cashmanage.utils.JsonUtil;
import com.joyveb.cashmanage.validate.Validateable;
import com.joyveb.cashmanage.web.InitParm;

/**
 * 
 * 订单信息查询
 * 
 */
@Slf4j
@Component("action_2008")
public class Action_2008 extends AbstractReqAction {
	private @Resource(name = "validator_2002")
	Validateable validator;
	@Resource(name = "orderInfoService")
	private OrderInfoService orderInfoService;
	@Resource(name = "shopInfoService")
	private ShopInfoService shopInfoService;
	@Resource(name = "initParm")
	private InitParm initParm;

	@Override
	public AbstractEntity process(TransMessageEntity msg) {
		WhiteList whiteList = getTokenMap().get(msg.getRequest().getHeader("token"));
		RepOrderEntity entity = new RepOrderEntity();
		List<RepGameEntity> list = new ArrayList<RepGameEntity>();
		List<RepXqOrderEntity> rxqList = new ArrayList<RepXqOrderEntity>();
		ReqOrderIdEntity body = (ReqOrderIdEntity) msg.getBody();
		String orderid2 = body.getOrderid();
		if (null != whiteList) {
			if (null != body) {
				String orderID = "";
				if (StringUtils.isNotBlank(orderid2)) {
					orderID = " and orderid = '" + orderid2 + "'";
				}
				String username = whiteList.getUsername();
				// 订单超过三十分钟待支付 更改订单状态已失效
				orderTimeOut(username);
				String sql = "select ORDERID as orderid,ORDERSTATUS as orderstatus,PAYTIME as paytime,ORDERTIME as ordertime,TOTALAMOUNT as totalamount,FREIGHTPRICE as freightprice from t_order_info  where USERID = "
						+ username + orderID + " ORDER BY PAYTIME desc";
				List<HashMap<String, Object>> dosql = orderInfoService.dosql(sql);
				if (null != dosql) {
					int size = dosql.size();
					for (int i = 0; i < size; i++) {
						RepXqOrderEntity roe = new RepXqOrderEntity();
						HashMap<String, Object> hashMap = dosql.get(i);
						String orderid = hashMap.get("orderid").toString();
						roe.setOrderid(orderid);
						roe.setOrderstatus(hashMap.get("orderstatus").toString());
						roe.setPaytime(Long.parseLong(hashMap.get("paytime").toString()));
						Object object = hashMap.get("ordertime");
						Object freightprice = hashMap.get("freightprice");
						String totalamount = hashMap.get("totalamount").toString();
						if (null != object) {
							roe.setOrdertime(new Date(Long.parseLong(object.toString())));
						}
						if(null!=freightprice){
							roe.setFreightprice(Long.parseLong(freightprice.toString()));
						}else{
							roe.setFreightprice(0L);
						}
						ShopInfoExample sie = new ShopInfoExample();
						sie.createCriteria().andOrderidEqualTo(orderid);
						List<ShopInfo> selectByExample = shopInfoService.selectByExample(sie);
						int size2 = selectByExample.size();
						long ordernu = 0L;
						if (null != selectByExample && size2 > 0) {
							for (int y = 0; y < size2; y++) {
								RepGameEntity gameEntity = new RepGameEntity();
								String gamecode = selectByExample.get(y).getGamecode();
								Integer orderamount = selectByExample.get(y).getOrderamount();
								Long gamenum = selectByExample.get(y).getGamenum();
								GameInfo gameInfo = initParm.getGameInfo(gamecode);
								if (null != gameInfo) {
									gameEntity.setGamename(gameInfo.getGamename());
									gameEntity.setDetailedpath(gameInfo.getDetailedpath());
									gameEntity.setPreviewpath(gameInfo.getPreviewpath());
								}
								gameEntity.setOrdernum(gamenum);
								gameEntity.setOrderamount(orderamount);
								gameEntity.setGamecode(gamecode);
								list.add(gameEntity);
								ordernu += gamenum;
							}
						}
						roe.setTotalamount(Long.parseLong(totalamount));
						roe.setTotalnum(ordernu);
						roe.setGames(list);
						rxqList.add(roe);
					}
				}
				entity.setRcode(ResponseResult.SUCCESS.getCode());
				entity.setDes(ResponseResult.SUCCESS.getDesc());
				entity.setRepXqOrderEntities(rxqList);
			}
		} else {
			log.info(ResponseResult.NOTLOGIN.getDesc());
			processValidFailCondi(entity, ResponseResult.NOTLOGIN.getCode());
			entity.setDes(ResponseResult.NOTLOGIN.getDesc());
		}
		return entity;
	}

	public void orderTimeOut(String username) {
		OrderInfoExample orderInfoExample = new OrderInfoExample();
		orderInfoExample.createCriteria().andUseridEqualTo(username).andOrderstatusEqualTo("1");
		List<OrderInfo> orderlist = orderInfoService.selectByExample(orderInfoExample);
		long timeout = initParm.getLongDbp("order.timeout", 30);
		if (orderlist.size() > 0) {
			for (int i = 0; i < orderlist.size(); i++) {
				OrderInfo orderInfo = orderlist.get(i);
				Long paytime = orderInfo.getPaytime();
				if ((System.currentTimeMillis() - paytime) / (1000 * 60) > timeout) {
					orderInfo.setOrderstatus("7");// 7代表订单超时失效
					orderInfoService.updateByPrimaryKey(orderInfo);
				}
			}
		}
	}

	private void processValidFailCondi(RepOrderEntity entity, String resultcode) {
		entity.setRcode(resultcode);
	}

	@Override
	public AbstractEntity processValidFail(ResponseResult vr) {
		RepOrderEntity rce = new RepOrderEntity();
		rce.setRcode(ResponseResult.PARAM_ERROR.getCode());
		return rce;
	}

	@Override
	public AbstractEntity parseMessageBody(String reqjson) throws Exception {
		ReqOrderIdHolder holder = JsonUtil.json2Bean(reqjson, ReqOrderIdHolder.class);
		return holder.getBody();
	}

	@Override
	public ResponseResult validate(TransMessageEntity msg) {
		return validator.validate(msg);
	}

}
