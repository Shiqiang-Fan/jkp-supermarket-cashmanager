package com.joyveb.cashmanage.action;

import com.joyveb.cashmanage.bean.AbstractEntity;
import com.joyveb.cashmanage.bean.HeadEntity;
import com.joyveb.cashmanage.bean.TransMessageEntity;
import com.joyveb.cashmanage.utils.IDGenerator;
import com.joyveb.cashmanage.utils.JsonUtil;

public class BaseActionTest {
	
	/*public static String key = "0123456789666666";
	
	public static HttpRequestorTest requset = null;
	static{
		if(requset == null){
			requset = new HttpRequestorTest();
		}
		requset.reload();
	}

	public static String getQequestStr(String command, AbstractEntity entity){
		HeadEntity head=new HeadEntity();
		head.setVersion("1.0");
		head.setDeviceid("DeviceId-001");
		head.setCommand(command);
		head.setMessageid(IDGenerator.nextID());
		head.setTimestamp(System.currentTimeMillis()+"");
		head.setMd5Bodyd(key, entity);
		
		TransMessageEntity msgEntity = new TransMessageEntity();
		msgEntity.setHead(head);
		msgEntity.setBody(entity);
		
		return JsonUtil.bean2JsonAsString(msgEntity);
	}*/
	
}
