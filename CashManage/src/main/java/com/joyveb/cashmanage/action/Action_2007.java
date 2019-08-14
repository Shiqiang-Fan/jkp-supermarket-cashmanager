package com.joyveb.cashmanage.action;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.AbstractEntity;
import com.joyveb.cashmanage.bean.TransMessageEntity;
import com.joyveb.cashmanage.bean.request.ReqOrderIdEntity;
import com.joyveb.cashmanage.bean.request.ReqOrderIdHolder;
import com.joyveb.cashmanage.bean.response.RepOrderStatusEntity;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.entity.OrderInfo;
import com.joyveb.cashmanage.entity.OrderInfoKey;
import com.joyveb.cashmanage.entity.WhiteList;
import com.joyveb.cashmanage.service.OrderInfoService;
import com.joyveb.cashmanage.utils.JsonUtil;
import com.joyveb.cashmanage.validate.Validateable;

/**
 * 
 * 查询订单状态
 * 
 */
@Slf4j
@Component("action_2007")
public class Action_2007 extends AbstractReqAction {

	@Resource(name = "validator_2001")
	private Validateable validator;
	@Resource(name = "orderInfoService")
	private OrderInfoService orderInfoService;

	@Override
	public AbstractEntity process(TransMessageEntity msg) {
		RepOrderStatusEntity entity = new RepOrderStatusEntity();
		ReqOrderIdEntity reqOrderEntity = (ReqOrderIdEntity) msg.getBody();
		WhiteList whiteList = getTokenMap().get(msg.getRequest().getHeader("token"));
		if (null != whiteList) {
			if (null != reqOrderEntity) {
				String orderid = reqOrderEntity.getOrderid();
				OrderInfoKey oKey = new OrderInfoKey();
				oKey.setOrderid(orderid);
				OrderInfo selectByPrimaryKey = orderInfoService.selectByPrimaryKey(oKey);
				entity.setOrderstatus(selectByPrimaryKey.getOrderstatus());
				entity.setRcode(ResponseResult.SUCCESS.getCode());
				entity.setDes(ResponseResult.SUCCESS.getDesc());
			} else {
				log.warn(ResponseResult.PARAM_ERROR.getDesc());
				processValidFailCondi(entity, ResponseResult.PARAM_ERROR.getCode());
				entity.setDes(ResponseResult.PARAM_ERROR.getDesc());
			}
		} else {
			log.info(ResponseResult.NOTLOGIN.getDesc());
			processValidFailCondi(entity, ResponseResult.NOTLOGIN.getCode());
			entity.setDes(ResponseResult.NOTLOGIN.getDesc());
		}
		return entity;
	}

	private void processValidFailCondi(RepOrderStatusEntity entity, String resultcode) {
		entity.setRcode(resultcode);
	}

	@Override
	public AbstractEntity processValidFail(ResponseResult vr) {
		RepOrderStatusEntity repCashEntity = new RepOrderStatusEntity();
		repCashEntity.setRcode(ResponseResult.PARAM_ERROR.getCode());
		return repCashEntity;
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
