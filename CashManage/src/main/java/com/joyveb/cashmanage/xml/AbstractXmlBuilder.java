package com.joyveb.cashmanage.xml;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import com.joyveb.cashmanage.xml.bean.ResponseHeadBean;
import com.joyveb.cashmanage.xml.bean.ResponseMessageBody;
import com.ximpleware.AutoPilot;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;

@Slf4j
public abstract class AbstractXmlBuilder {

	public ResponseHeadBean parseResponseHeadXML(String responsexml) {
		VTDGen vg = new VTDGen();
		// 存放节点头信息
		ResponseHeadBean responseHeadBean = new ResponseHeadBean();
		Map<String, String> map = new HashMap<String, String>();
		try {
			// 把字符串形式的响应消息转换成字节数组
			byte[] byteresponseMessage = responsexml.getBytes("UTF-8");
			vg.setDoc(byteresponseMessage);
			vg.parse(false);
			VTDNav vn = vg.getNav();
			AutoPilot ap = new AutoPilot(vn);
			// 获得head节点的所有子节点
			ap.selectXPath("/message/head/node()");
			while ((ap.evalXPath()) != -1) {// 移动游标
				String name = "";
				String value = "";
				if (vn.getCurrentIndex() >= 0) {
					name = vn.toString(vn.getCurrentIndex());
				}
				if (vn.getText() >= 0) {
					value = vn.toString(vn.getText());
				}
				map.put(name, value);
			}
			responseHeadBean.setMessageid(map.get("messageid"));
			responseHeadBean.setCommand(map.get("command"));
			responseHeadBean.setResult(map.get("result"));
			responseHeadBean.setTimestamp(map.get("timestamp"));
			responseHeadBean.setMd(map.get("md"));
			ap.resetXPath();
			vg.clear();
		} catch (Exception e) {
			log.warn("body=" + responsexml, e);
		}
		return responseHeadBean;
	}

	public abstract ResponseMessageBody parseResponseBodyXML(ResponseHeadBean head,String responsexml);

	public static void main(String[] args) {
//		String responsexml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><message><head>"
//				+ "<messageid>cp201207231400000001</messageid><command>1000</command>"
//				+ "<timestamp>20120723140002</timestamp><result>001</result><md>a2d346f4c60b6f6da5b934d41b01b3ec</md></head><body>"
//				+ "<gamecode>G0233</gamecode>"
//				+ "<gameserial>12037</gameserial><packagenum>23498</packagenum>" +
//				"<ticketnum>103</ticketnum><winState>1</winState><winAmount>10000</winAmount>" +
//				"<amountSign>0</amountSign><commissionAmount>0</commissionAmount></body></message>";
//		ResponseHeadBean b=AbstractXmlBuilder.parseResponseHeadXML(responsexml);
//		try {
//			System.out.println(BeanUtils.describe(b));
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchMethodException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
