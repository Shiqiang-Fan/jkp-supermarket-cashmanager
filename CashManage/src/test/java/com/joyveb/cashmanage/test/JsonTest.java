package com.joyveb.cashmanage.test;

import com.joyveb.cashmanage.utils.IDGenerator;


public class JsonTest {

	public static void main(String[] args) {
		
		System.out.println(IDGenerator.nextID());
		
		/*Message msg = new Message();
		
		MessageHead head = new MessageHead();
		head.setVersion("1.0.0.0");
		head.setDeviceId("deviceid");
		head.setCommand("1009");
		head.setMessageId("20150101122600");
		head.setTimestamp("20150101122600");
		head.setBodyMd("4232ea4bc4a0804ebaf7ade7559e1871");
		
		MessageBody1011 b1011 = new MessageBody1011();
		b1011.setA("a");
		b1011.setB(1);
		
		msg.setHead(head);
		msg.setBody(b1011);
		
		String requestJson = JsonUtil.bean2Json(msg).toString();
		
		System.out.println(requestJson);
		
		String jsonTxt = "{\"head\":{\"version\":\"1.0.0.0\",\"deviceId\":\"deviceid\",\"command\":\"1009\",\"messageId\":\"20150101122600\",\"timestamp\":\"20150101122600\",\"bodyMd\":\"4232ea4bc4a0804ebaf7ade7559e1871\"},\"body\":{\"a\":\"a\",\"b\":1}}";
		
		Message msg1 = JsonUtil.json2Bean(jsonTxt, Message.class);
		
		MessageBody b10111 = msg1.getBody();
		MessageBody1011 b101111 = (MessageBody1011)msg1.getBody();
		
		System.out.println("");*/
		
		
	}
	
}
