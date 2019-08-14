/*
package com.joyveb.cashmanage.action;

import java.io.IOException;

import com.joyveb.cashmanage.bean.request.ReqLoginEntity;
//兑奖终端登陆测试
public class Action1001Test extends BaseActionTest {
	
	public static void main(String[] args) {
		
		ReqLoginEntity login = new ReqLoginEntity();
		*/
/*login.setUsertype("1");
		// 于瑞斌 - 2016-06-08 16:47分获取
		//login.setAuthcode("5ddcb896924243d88dac4b7cb40cTE55");
		login.setAuthcode("aa");*//*

		
		try {
			//1...1001
			//login.setPassword("0000");
			//login.setUsername("1002");
			//String requestJson = getQequestStr("1001", login);
			//System.out.println(requestJson);
			//{"body":{"password":"0000","username":"1002"},"head":{"bodymd":"6f1cde5f4955cf8e60cc024d7138fb17","command":"1001","deviceid":"DeviceId-001","messageid":"4028963b5919f4a3015919f4a37a0001","timestamp":"1482199180154","version":"1.1"}}
			//result::{"head":{"version":"1.1","deviceid":"DeviceId-001","devicetype":null,"command":"1001","messageid":"4028963b5919f4a3015919f4a37a0001","timestamp":"1482199180555","bodymd":"6a212a1857d4523503ec414f8a4e870a"},"body":{"rcode":"508","des":"终端ID不同"}}
	
			
			//正确的
			login.setPassword("1111");
			login.setUsername("1001");
			
			*/
/*错误的 1密码错误
			 * login.setPassword("1111111");
			login.setUsername("1001");
			返回："body":{"rcode":"505","des":"登录密码不正确"}
			2用户名错误
			login.setPassword("1111");
			login.setUsername("1001q");
			*
			返回："body":{"rcode":"204","des":"服务器繁忙"}
			*//*

			
			String requestJson = getQequestStr("1001", login);
			System.out.println(requestJson);

			//String result = requset.post("192.168.21.212", requestJson);
			String result = requset.post("localhost", requestJson);
			System.out.println("result::"+result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
*/
