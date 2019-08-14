package com.joyveb.cashmanage.action;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.joyveb.cashmanage.bean.AbstractEntity;
import com.joyveb.cashmanage.bean.HeadEntity;
import com.joyveb.cashmanage.bean.MessageEntity;
import com.joyveb.cashmanage.bean.TransMessageEntity;
import com.joyveb.cashmanage.common.Constants;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.entity.OrderInfo;
import com.joyveb.cashmanage.entity.WhiteList;
import com.joyveb.cashmanage.utils.JsonUtil;

/**
 * 
 * action抽象类
 * 
 */
@Slf4j
public abstract class AbstractReqAction implements Actionable {

	SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
	public static Map<String, WhiteList> tokenMap = new HashMap<String, WhiteList>();
	protected static Map<String, OrderInfo> orderMap = new ConcurrentHashMap<String, OrderInfo>();

	@Override
	@SuppressWarnings("finally")
	public String action(TransMessageEntity msg) {
		String resultJson = null;
		ResponseResult vr = ResponseResult.SUCCESS;
		// request
		AbstractEntity reqbean = null;
		// reponse
		AbstractEntity repbean = null;
		try {
			reqbean = this.parseMessageBody(msg.getReqJson());
			msg.setBody(reqbean);
			vr = this.validate(msg);
			if (StringUtils.equals(vr.getCode(), ResponseResult.SUCCESS.getCode())) {
				log.debug("validate is ok, process start");
				repbean = process(msg);
			} else {
				log.debug("fail");
				repbean = processValidFail(vr);
			}
			resultJson = buildReponseStr(repbean, msg);
		} catch (Exception e) {
			log.warn("action出现问题", e);
			vr = ResponseResult.SERVER_BUSY;
			repbean = processValidFail(vr);
			resultJson = buildReponseStr(repbean, msg);
		}
		return resultJson;
	}

	public abstract AbstractEntity process(TransMessageEntity msg);

	/**
	 * 处理失败情况
	 * 
	 * @return
	 */
	public abstract AbstractEntity processValidFail(ResponseResult vr);

	public abstract AbstractEntity parseMessageBody(String reqjson) throws Exception;

	public abstract ResponseResult validate(TransMessageEntity msg);

	/**
	 * 返回处理完成后的json
	 * 
	 * @param en
	 * @return
	 */
	public String buildReponseStr(AbstractEntity en, TransMessageEntity transmessage) {
		HeadEntity he = transmessage.getHead();
		try {
			String timeStamp = String.valueOf(System.currentTimeMillis());
			he.setTimestamp(timeStamp);
			he.setBodymd(DigestUtils.md5Hex(he.getMessageid() + timeStamp + Constants.MERCHANT_ID_KEY + JsonUtil.bean2JsonAsString(en)));

			MessageEntity response = new MessageEntity();
			response.setHead(he);
			response.setBody(en);
			log.debug("build response[" + response.toString() + "]");
			return JsonUtil.beanToJson(response);
		} catch (Exception e) {
			log.warn("出现错误", e);
		}
		return "";
	}

	public static Map<String, WhiteList> getTokenMap() {
		return tokenMap;
	}

	public static void setTokenMap(Map<String, WhiteList> tokenMap) {
		AbstractReqAction.tokenMap = tokenMap;
	}

	public static Map<String, OrderInfo> getOrderMap() {
		return orderMap;
	}

	public static void setOrderMap(Map<String, OrderInfo> orderMap) {
		AbstractReqAction.orderMap = orderMap;
	}
}
