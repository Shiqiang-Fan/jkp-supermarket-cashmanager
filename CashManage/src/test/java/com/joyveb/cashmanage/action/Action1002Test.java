/*package com.joyveb.cashmanage.action;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.joyveb.cashmanage.bean.request.ReqCashEntity;
import com.joyveb.cashmanage.utils.DESCoder;
import com.joyveb.cashmanage.utils.MixBarcodeUtil;

public class Action1002Test extends BaseActionTest {
	
	public static void main(String[] args) {
		//String barcode = "G0353120610012363113AAKAAJCDJLFNCKQGAG000";
		String barcode = "G0353120610012363113AAKAAJCDJLFNCKQGAG000";
		ReqCashEntity cash = new ReqCashEntity();
		cash.setBarcode(DESCoder.desEncrypt(MixBarcodeUtil.encodeBarcode(barcode), key));
		cash.setUserid("2088702522580555");
		cash.setIdno("12010419921228253X");
		
		try {
			String requestJson = getQequestStr("1002", cash);
			System.out.println(requestJson);
			
			String result = requset.post(requestJson);
			System.out.println("result::"+result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
*/