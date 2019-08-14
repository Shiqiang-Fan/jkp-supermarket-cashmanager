package com.joyveb.cashmanage.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.AbstractEntity;
import com.joyveb.cashmanage.bean.TransMessageEntity;
import com.joyveb.cashmanage.bean.request.ReqCashEntityHolder;
import com.joyveb.cashmanage.bean.response.RepListReceiverEntity;
import com.joyveb.cashmanage.bean.response.RepReceiverEntity;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.entity.WhiteList;
import com.joyveb.cashmanage.entity.WhiteListExample;
import com.joyveb.cashmanage.service.ReceiverInfoService;
import com.joyveb.cashmanage.service.WhiteListService;
import com.joyveb.cashmanage.utils.JsonUtil;
import com.joyveb.cashmanage.validate.Validateable;

/**
 * 
 * 查询收货地址
 * 
 */
@Slf4j
@Component("action_2003")
public class Action_2003 extends AbstractReqAction {
	private @Resource(name = "validator_2002")
	Validateable validator;
	@Resource(name = "receiverInfoService")
	private ReceiverInfoService receiverInfoService;
	@Resource(name = "whiteListService")
	private WhiteListService whiteListService;

	@Override
	public AbstractEntity process(TransMessageEntity msg) {
		WhiteList whiteList = getTokenMap().get(msg.getRequest().getHeader("token"));
		List<RepReceiverEntity> list = new ArrayList<RepReceiverEntity>();
		RepListReceiverEntity repListReceiverEntity = new RepListReceiverEntity();
		if (null != whiteList) {
			String username = whiteList.getUsername();
			String sql = "select uuid,receiver,address,phone,userid,isdefault from t_receiver_info where userid = " + username;
			List<HashMap<String, Object>> dosql = receiverInfoService.dosql(sql);
			if (null != dosql) {
				int size = dosql.size();
				for (int i = 0; i < size; i++) {
					RepReceiverEntity entity = new RepReceiverEntity();
					HashMap<String, Object> hashMap = dosql.get(i);
					entity.setAddress(hashMap.get("address").toString());
					entity.setPhone(Long.parseLong(hashMap.get("phone").toString()));
					entity.setReceiver(hashMap.get("receiver").toString());
					entity.setUuid(hashMap.get("uuid").toString());
					entity.setIsdefault(hashMap.get("isdefault").toString());
					list.add(entity);
				}
			} else {
				repListReceiverEntity.setRcode(ResponseResult.SUCCESS.getCode());
				repListReceiverEntity.setDes("该账户下没有收获地址");
			}
			WhiteListExample wle = new WhiteListExample();
			wle.createCriteria().andStatusEqualTo("0").andUsernameEqualTo(username);
			List<WhiteList> selectByExample = whiteListService.selectByExample(wle);
			if (null != selectByExample && selectByExample.size() > 0) {
				repListReceiverEntity.setCommission(selectByExample.get(0).getCommission());
			}
			repListReceiverEntity.setReceivers(list);
			repListReceiverEntity.setRcode(ResponseResult.SUCCESS.getCode());
			repListReceiverEntity.setDes(ResponseResult.SUCCESS.getDesc());
		} else {
			log.info(ResponseResult.NOTLOGIN.getDesc());
			processValidFailCondi(repListReceiverEntity, ResponseResult.NOTLOGIN.getCode());
			repListReceiverEntity.setDes(ResponseResult.NOTLOGIN.getDesc());
		}
		return repListReceiverEntity;
	}

	private void processValidFailCondi(RepListReceiverEntity entity, String resultcode) {
		entity.setRcode(resultcode);
	}

	@Override
	public AbstractEntity processValidFail(ResponseResult vr) {
		RepListReceiverEntity rce = new RepListReceiverEntity();
		rce.setRcode(ResponseResult.PARAM_ERROR.getCode());
		return rce;
	}

	@Override
	public AbstractEntity parseMessageBody(String reqjson) throws Exception {
		ReqCashEntityHolder holder = JsonUtil.json2Bean(reqjson, ReqCashEntityHolder.class);
		return holder.getBody();
	}

	@Override
	public ResponseResult validate(TransMessageEntity msg) {
		return validator.validate(msg);
	}

}
