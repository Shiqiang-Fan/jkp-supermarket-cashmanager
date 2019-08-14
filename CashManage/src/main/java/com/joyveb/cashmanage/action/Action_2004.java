package com.joyveb.cashmanage.action;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.AbstractEntity;
import com.joyveb.cashmanage.bean.TransMessageEntity;
import com.joyveb.cashmanage.bean.request.ReqReceiverUuidHolder;
import com.joyveb.cashmanage.bean.request.ReqRecevierUuidEntity;
import com.joyveb.cashmanage.bean.response.RepCommonEntity;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.entity.ReceiverInfo;
import com.joyveb.cashmanage.entity.ReceiverInfoExample;
import com.joyveb.cashmanage.entity.WhiteList;
import com.joyveb.cashmanage.service.ReceiverInfoService;
import com.joyveb.cashmanage.utils.JsonUtil;
import com.joyveb.cashmanage.validate.Validateable;

/**
 * 
 * 修改收货地址
 * 
 */
@Slf4j
@Component("action_2004")
public class Action_2004 extends AbstractReqAction {

	@Resource(name = "receiverInfoService")
	private ReceiverInfoService receiverInfoService;
	@Resource(name = "validator_2004")
	private Validateable validator;

	@Override
	public AbstractEntity process(TransMessageEntity msg) {
		WhiteList whiteList = getTokenMap().get(msg.getRequest().getHeader("token"));
		RepCommonEntity entity = new RepCommonEntity();
		ReqRecevierUuidEntity reqOrderEntity = (ReqRecevierUuidEntity) msg.getBody();
		if (null != whiteList) {
			String username = whiteList.getUsername();
			if (null != reqOrderEntity) {
				ReceiverInfoExample re = new ReceiverInfoExample();
				re.createCriteria().andUseridEqualTo(username).andUuidEqualTo(reqOrderEntity.getUuid());
				List<ReceiverInfo> selectByPrimaryKey = receiverInfoService.selectByExample(re);
				if (null == selectByPrimaryKey || selectByPrimaryKey.size() == 0) {
					entity.setRcode(ResponseResult.PARAM_ERROR.getCode());
					entity.setDes("无此条地址！");
				} else {
					ReceiverInfoExample rie = new ReceiverInfoExample();
					rie.createCriteria().andUseridEqualTo(username);
					List<ReceiverInfo> selectByExample = receiverInfoService.selectByExample(rie);
					if (null != selectByExample && selectByExample.size() > 0) {
						for (int u = 0; u < selectByExample.size(); u++) {
							ReceiverInfo receiverInfo = selectByExample.get(u);
							receiverInfo.setIsdefault("0");
							receiverInfoService.updateByPrimaryKey(receiverInfo);
						}
					}
					ReceiverInfo oi = new ReceiverInfo();
					oi.setAddress(reqOrderEntity.getAddress());
					oi.setPhone(reqOrderEntity.getPhone());
					oi.setUserid(username);
					oi.setReceiver(reqOrderEntity.getReceiver());
					oi.setUuid(reqOrderEntity.getUuid());
					oi.setIsdefault(reqOrderEntity.getIsdefault());
					receiverInfoService.updateByPrimaryKey(oi);
					entity.setRcode(ResponseResult.SUCCESS.getCode());
					entity.setDes(ResponseResult.SUCCESS.getDesc());
				}
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
		RepCommonEntity rce = new RepCommonEntity();
		rce.setRcode(ResponseResult.PARAM_ERROR.getCode());
		return rce;
	}

	@Override
	public AbstractEntity parseMessageBody(String reqjson) throws Exception {
		ReqReceiverUuidHolder holder = JsonUtil.json2Bean(reqjson, ReqReceiverUuidHolder.class);
		return holder.getBody();
	}

	@Override
	public ResponseResult validate(TransMessageEntity msg) {
		return validator.validate(msg);
	}

}
