package com.joyveb.cashmanage.action;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.AbstractEntity;
import com.joyveb.cashmanage.bean.TransMessageEntity;
import com.joyveb.cashmanage.bean.response.RepVersionEntity;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.service.VersionService;
import com.joyveb.cashmanage.validate.Validateable;

/**
 * 
 * 兑奖终端版本查询
 * 
 */
@Slf4j
@Component("action_1004")
public class Action_1004 extends AbstractReqAction {
	private @Resource(name = "validator_2002")
	Validateable validator;
	private @Resource(name = "versionService")
	VersionService versionService;

	@Override
	public AbstractEntity process(TransMessageEntity msg) {
		String sql = "select vol,version,pubtime,comment,url from t_app_version ORDER BY vol desc;";
		List<HashMap<String, Object>> versionMap = versionService.dosql(sql);
		RepVersionEntity versionn = new RepVersionEntity();
		if (null != versionMap && versionMap.size() > 0) {
			versionn.setComment(versionMap.get(0).get("comment").toString());
			versionn.setPubtime(Long.parseLong(versionMap.get(0).get("pubtime").toString()));
			versionn.setUrl(versionMap.get(0).get("url").toString());
			versionn.setVersion(versionMap.get(0).get("version").toString());
			versionn.setVol(Integer.parseInt(versionMap.get(0).get("vol").toString()));
		}
		return versionn;
	}

	@Override
	public AbstractEntity processValidFail(ResponseResult vr) {
		RepVersionEntity versionn = new RepVersionEntity();
		log.info(vr.getCode() + " " + vr.getDesc());
		return versionn;
	}

	@Override
	public AbstractEntity parseMessageBody(String reqjson) throws Exception {
		return null;
	}

	@Override
	public ResponseResult validate(TransMessageEntity msg) {
		return validator.validate(msg);
	}

}
