package com.joyveb.cashmanage.action;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.AbstractEntity;
import com.joyveb.cashmanage.bean.TransMessageEntity;
import com.joyveb.cashmanage.bean.request.ReqLoginEntity;
import com.joyveb.cashmanage.bean.request.ReqLoginEntityHolder;
import com.joyveb.cashmanage.bean.response.RepCommonEntity;
import com.joyveb.cashmanage.bean.response.RepUpdatePassEntity;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.entity.WhiteList;
import com.joyveb.cashmanage.entity.WhiteListExample;
import com.joyveb.cashmanage.service.WhiteListService;
import com.joyveb.cashmanage.utils.JsonUtil;
import com.joyveb.cashmanage.validate.Validateable;
import com.joyveb.lbos.restful.common.Constans;

/**
 * 
 * 兑奖终端登录
 * 
 */
@Slf4j
@Component("action_1001")
public class Action_1001 extends AbstractReqAction {

	private @Resource(name = "validator_1001")
	Validateable validator;

	@Resource(name = "whiteListService")
	private WhiteListService whiteListService;

	@Override
	public AbstractEntity process(TransMessageEntity msg) {
		RepUpdatePassEntity repLoginEntity = new RepUpdatePassEntity();
		repLoginEntity.setRcode(ResponseResult.SUCCESS.getCode());
		repLoginEntity.setDes(ResponseResult.SUCCESS.getDesc());
		String id = msg.getRequest().getSession().getId();
		log.debug("sessionID:::" + id);
		ReqLoginEntity body = (ReqLoginEntity) msg.getBody();
		// 检查是否是白名单用户
		WhiteListExample whiteListExample = new WhiteListExample();
		whiteListExample.createCriteria().andUsernameEqualTo(body.getUsername());
		List<WhiteList> selectByExample = whiteListService.selectByExample(whiteListExample);
		if (null == selectByExample || selectByExample.size() == 0) {
			log.debug("该登录账户未注册");
			return processFail(ResponseResult.NOTWHITELIST);
		} else {
			WhiteList whiteList = selectByExample.get(0);
			if (!whiteList.getStatus().equals("0")) {
				return processFail(ResponseResult.NOTSTATUS);
			}
			if (!whiteList.getDevicenum().equals(msg.getHead().getDeviceid())) {
				return processFail(ResponseResult.NOTDEVICEID);
			}
			if (!whiteList.getDevicetype().equals(msg.getHead().getDevicetype())) {
				return processFail(ResponseResult.NOTDEVICETYPE);
			}
			if (!(body.getPassword()).equals(whiteList.getPassword())) {
				return processFail(ResponseResult.NOTPASS);
			}
			tokenMap.put(id, whiteList);
			msg.getResponse().addHeader(Constans.TOKEN, id);
			log.info("登录token：：" + id);
			whiteList.setLogintime(System.currentTimeMillis());
			whiteListService.updateByPrimaryKey(whiteList);
		}
		return repLoginEntity;
	}

	private RepCommonEntity processFail(ResponseResult vr) {
		RepCommonEntity repLoginEntity = new RepCommonEntity();
		repLoginEntity.setRcode(vr.getCode());
		repLoginEntity.setDes(vr.getDesc());
		log.info(vr.getCode() + " " + vr.getDesc());
		return repLoginEntity;
	}

	@Override
	public AbstractEntity processValidFail(ResponseResult vr) {
		return processFail(vr);
	}

	@Override
	public AbstractEntity parseMessageBody(String reqjson) throws Exception {
		ReqLoginEntityHolder holder = JsonUtil.json2Bean(reqjson, ReqLoginEntityHolder.class);
		return holder.getBody();
	}

	@Override
	public ResponseResult validate(TransMessageEntity msg) {
		return validator.validate(msg);
	}
}
