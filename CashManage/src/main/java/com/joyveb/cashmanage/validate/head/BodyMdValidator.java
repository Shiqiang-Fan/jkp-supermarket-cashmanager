package com.joyveb.cashmanage.validate.head;

import java.security.MessageDigest;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.TransMessageEntity;
import com.joyveb.cashmanage.common.Constants;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.utils.JsonUtil;
import com.joyveb.cashmanage.validate.AbstractFieldValidator;
/**
 * 
 * BodyMD加密校验
 * 
 */
@Slf4j
@Component("bodyMdValidator")
public class BodyMdValidator extends AbstractFieldValidator<TransMessageEntity> {

	public BodyMdValidator() {
		super(ResponseResult.PARAM_ERROR);
	}

	@Override
	public boolean isValid(TransMessageEntity msg) {
		if(msg.getHead() == null) {
			log.warn("bodyMd validator fail,messageHead is null");
			return false;
		}
		if(msg.getReqJson() == null || msg.getHead() == null || msg.getHead().getBodymd() == null) {
			log.warn("bodyMd validator fail,messageHead or bodyxml is null");
			return false;
		}
		
		String bodyMd = "";
		log.debug(msg.getHead().getMessageid()+msg.getHead().getTimestamp()+Constants.MERCHANT_ID_KEY+JsonUtil.beanToJson(msg.getBody()));
		bodyMd = DigestUtils.md5Hex(msg.getHead().getMessageid()+msg.getHead().getTimestamp()+Constants.MERCHANT_ID_KEY+JsonUtil.beanToJson(msg.getBody()));
		log.debug("messageId:["+msg.getHead().getMessageid()+"] timestamp:["+msg.getHead().getTimestamp()+"] key:["+Constants.MERCHANT_ID_KEY+"] body:["+JsonUtil.bean2Json(msg.getBody())+"]");
		
		// TODO 验证失败，body属性位置移动导致
		if(!StringUtils.equals(msg.getHead().getBodymd(),bodyMd)){
			log.warn("bodyMd validator fail,bodyMD is ["+msg.getHead().getBodymd()+"]the except bodyMd is["+bodyMd+"].");
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(DigestUtils.md5Hex("cac5069570675918146717805796214671780579620123456789666666{'authcode':'a215618240c54292be2a005a642dQD55','usertype':'1'}"));
		System.out.println(MD5("41c937a6cbaef4531472176036198147217603619820123456789666666{'gametype':'1'}"));
		//System.out.println(DigestUtils.md5Hex("ABC00000146718289598014671828959800123456789666666{"+authcode": "a0c5d012c06943ca984ca4fc2b15VX55", "usertype": "1"}));
	}
	
	public static String MD5(String str) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			log.warn("md5异常",e);
			return "";
		}

		char[] charArray = str.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}
		byte[] md5Bytes = md5.digest(byteArray);

		StringBuilder hexValue = new StringBuilder();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}


}
