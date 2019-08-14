package com.joyveb.cashmanage.action;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.AbstractEntity;
import com.joyveb.cashmanage.bean.TransMessageEntity;
import com.joyveb.cashmanage.bean.request.ReqUuidEntity;
import com.joyveb.cashmanage.bean.request.ReqUuidHolder;
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
 * 删除收货地址
 * 
 */
@Slf4j
@Component("action_2005")
public class Action_2005 extends AbstractReqAction {
	private @Resource(name = "validator_2005")
	Validateable validator;
	@Resource(name = "receiverInfoService")
	private ReceiverInfoService receiverInfoService;

	@Override
	public AbstractEntity process(TransMessageEntity msg) {
		WhiteList whiteList = getTokenMap().get(msg.getRequest().getHeader("token"));
		RepCommonEntity entity = new RepCommonEntity();
		ReqUuidEntity reqOrderEntity = (ReqUuidEntity) msg.getBody();
		if (null != whiteList) {
			if (null != reqOrderEntity) {
				ReceiverInfoExample re = new ReceiverInfoExample();
				re.createCriteria().andUseridEqualTo(whiteList.getUsername()).andUuidEqualTo(reqOrderEntity.getUuid());
				List<ReceiverInfo> selectByPrimaryKey = receiverInfoService.selectByExample(re);
				if (null == selectByPrimaryKey || selectByPrimaryKey.size() == 0) {
					entity.setRcode(ResponseResult.PARAM_ERROR.getCode());
					entity.setDes("无此条地址！");
				} else {
					ReceiverInfo oi = new ReceiverInfo();
					oi.setUuid(reqOrderEntity.getUuid());
					receiverInfoService.deleteByPrimaryKey(oi);
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
		ReqUuidHolder holder = JsonUtil.json2Bean(reqjson, ReqUuidHolder.class);
		return holder.getBody();
	}

	@Override
	public ResponseResult validate(TransMessageEntity msg) {
		return validator.validate(msg);
	}

}
