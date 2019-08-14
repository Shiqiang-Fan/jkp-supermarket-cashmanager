package com.joyveb.cashmanage.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.digest.DigestUtils;

import com.joyveb.cashmanage.utils.JsonUtil;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
public class HeadEntity extends AbstractEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String version;
	public String deviceid;//终端唯一编号
	public String devicetype;//终端唯一类型
	public String command;
	public String messageid;
	public String timestamp;
	public String bodymd;
	public void setMd5Bodyd(String key,AbstractEntity bodyentity){
		log.debug(JsonUtil.bean2JsonAsString(bodyentity));
		this.bodymd=DigestUtils.md5Hex(this.messageid+this.timestamp+key+JsonUtil.bean2JsonAsString(bodyentity));
	}
}