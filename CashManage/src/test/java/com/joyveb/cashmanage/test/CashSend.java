package com.joyveb.cashmanage.test;
/*package com.joyveb.cashmange.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.joyveb.cashmange.utils.HttpRequestor;
import com.joyveb.cashmange.xml.XmlBuilder1009;

public class CashSend {

	private XmlBuilder1009 xmlBuilder1009 = new XmlBuilder1009();
	
	private String getReqXMl(String barcode, String voteId, String idNo){
		return xmlBuilder1009.buildRequestXml(barcode, voteId, idNo);
	}
	
	public static void main(String[] args) {
		
		CashSend cashSend = new CashSend();
		
		String barcode = "RPY4VfklesJAKXERGiIECd3Vna9ynX9SbALmxao55cWLwG7B0pl13LAFJ3EAuAfTbblmATe9/LCSEai2zk7Hu3WYf7DA+3L0JdkeUglBNILVP7MDzo0sCQ==";
		String requestXml = cashSend.getReqXMl(barcode, "20880043586971049671437952312455", "12010419921228253X");
		
		try {
			HttpRequestor request =  new HttpRequestor();
			
			request.reload();
			String result = request.post(requestXml, 100000);
			
			System.out.println("result:==" + result);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
*/