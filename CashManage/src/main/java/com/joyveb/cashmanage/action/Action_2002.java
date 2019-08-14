package com.joyveb.cashmanage.action;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.AbstractEntity;
import com.joyveb.cashmanage.bean.TransMessageEntity;
import com.joyveb.cashmanage.bean.request.ReqOrderEntityHolder;
import com.joyveb.cashmanage.bean.request.ReqOrderRecevierEntity;
import com.joyveb.cashmanage.bean.response.RepCommonEntity;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.entity.ReceiverInfo;
import com.joyveb.cashmanage.entity.WhiteList;
import com.joyveb.cashmanage.service.ReceiverInfoService;
import com.joyveb.cashmanage.utils.IDGenerator;
import com.joyveb.cashmanage.utils.JsonUtil;
import com.joyveb.cashmanage.validate.Validateable;

/**
 * 
 * 新增收货地址
 * 
 */
@Slf4j
@Component("action_2002")
public class Action_2002 extends AbstractReqAction {

	@Resource(name = "validator_2003")
	private Validateable validator;
	@Resource(name = "receiverInfoService")
	private ReceiverInfoService receiverInfoService;

	@Override
	public AbstractEntity process(TransMessageEntity msg) {
		RepCommonEntity entity = new RepCommonEntity();
		ReqOrderRecevierEntity reqOrderEntity = (ReqOrderRecevierEntity) msg.getBody();
		WhiteList whiteList = getTokenMap().get(msg.getRequest().getHeader("token"));
		if (null != whiteList) {
			if (null != reqOrderEntity) {
				ReceiverInfo oi = new ReceiverInfo();
				String generate = IDGenerator.getInstance().generate();
				oi.setAddress(reqOrderEntity.getAddress());
				oi.setPhone(reqOrderEntity.getPhone());
				oi.setReceiver(reqOrderEntity.getReceiver());
				// oi.setUserid(whiteList.getAlipayUserId());
				oi.setUserid(whiteList.getUsername());
				oi.setUuid(generate);
				oi.setIsdefault(reqOrderEntity.getIsdefault());
				receiverInfoService.insert(oi);
				entity.setRcode(ResponseResult.SUCCESS.getCode());
				entity.setDes(ResponseResult.SUCCESS.getDesc());
			} else {
				log.info(ResponseResult.PARAM_ERROR.getDesc());
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

	private void processValidFailCondi(RepCommonEntity entity, String resultcode) {
		entity.setRcode(resultcode);
	}

	@Override
	public AbstractEntity processValidFail(ResponseResult vr) {
		RepCommonEntity repCashEntity = new RepCommonEntity();
		repCashEntity.setRcode(ResponseResult.PARAM_ERROR.getCode());
		return repCashEntity;
	}

	@Override
	public AbstractEntity parseMessageBody(String reqjson) throws Exception {
		ReqOrderEntityHolder holder = JsonUtil.json2Bean(reqjson, ReqOrderEntityHolder.class);
		return holder.getBody();
	}

	@Override
	public ResponseResult validate(TransMessageEntity msg) {
		return validator.validate(msg);
	}

}
