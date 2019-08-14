package com.joyveb.cashmanage.action;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.AbstractEntity;
import com.joyveb.cashmanage.bean.TransMessageEntity;
import com.joyveb.cashmanage.bean.request.ReqUpdatePassEntity;
import com.joyveb.cashmanage.bean.request.ReqUpdatePassHolder;
import com.joyveb.cashmanage.bean.response.RepCommonEntity;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.entity.WhiteList;
import com.joyveb.cashmanage.entity.WhiteListExample;
import com.joyveb.cashmanage.service.WhiteListService;
import com.joyveb.cashmanage.utils.JsonUtil;
import com.joyveb.cashmanage.utils.MD5Utils;
import com.joyveb.cashmanage.validate.Validateable;

/**
 * 
 * 修改密码
 * 
 */
@Slf4j
@Component("action_2010")
public class Action_2010 extends AbstractReqAction {
	private @Resource(name = "validator_2002")
	Validateable validator;
	@Resource(name = "whiteListService")
	private WhiteListService whiteListService;

	@Override
	public AbstractEntity process(TransMessageEntity msg) {
		WhiteList whiteList = getTokenMap().get(msg.getRequest().getHeader("token"));
		RepCommonEntity entity = new RepCommonEntity();
		ReqUpdatePassEntity body = (ReqUpdatePassEntity) msg.getBody();
		if (null != whiteList) {
			if (null != body) {
				body.getOldpass();
				WhiteListExample wle = new WhiteListExample();
				wle.createCriteria().andUsernameEqualTo(body.getUsername()).andPasswordEqualTo(body.getOldpass());
				List<WhiteList> selectByExample = whiteListService.selectByExample(wle);
				if (selectByExample.size() <= 0) {
					log.info(ResponseResult.NOTPASSWORD.getDesc());
					processValidFailCondi(entity, ResponseResult.NOTPASSWORD.getCode());
					entity.setDes(ResponseResult.NOTPASSWORD.getDesc());
				} else {
					whiteList.setPassword(body.getNewpass());
					whiteListService.updateByPrimaryKeySelective(whiteList);
					entity.setRcode(ResponseResult.SUCCESS.getCode());
					entity.setDes(ResponseResult.SUCCESS.getDesc());
				}
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
		ReqUpdatePassHolder holder = JsonUtil.json2Bean(reqjson, ReqUpdatePassHolder.class);
		return holder.getBody();
	}

	@Override
	public ResponseResult validate(TransMessageEntity msg) {
		return validator.validate(msg);
	}

}
