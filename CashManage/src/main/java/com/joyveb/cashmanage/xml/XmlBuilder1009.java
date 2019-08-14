package com.joyveb.cashmanage.xml;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.common.Constants;
import com.joyveb.cashmanage.xml.bean.ResponseHeadBean;
import com.joyveb.cashmanage.xml.bean.ResponseMessage1009Body;
import com.joyveb.cashmanage.xml.bean.ResponseMessageBody;
import com.ximpleware.AutoPilot;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;

@Slf4j
@Component("xmlBuilder1009")
public class XmlBuilder1009 extends AbstractXmlBuilder {

	public String buildRequestXml(String barcode, String voteId, String idNo) {
		StringBuilder reqxml = new StringBuilder();
		reqxml.append("<?xml version=\"1.0\" encoding=\"utf-8\"?><message><head>");
		reqxml.append("<version>").append(Constants.B2B_VERSION).append("</version>");
		reqxml.append("<merchantid>").append(Constants.MERCHANT_ID).append("</merchantid>");
		reqxml.append("<command>").append(Constants.B2B_COMMAND_1009).append("</command>");
		reqxml.append("<messageid>").append(formatMsgId(Constants.MERCHANT_ID)) // IDGenerator.nextID()
				.append("</messageid>");
		String nowTime = FastDateFormat.getInstance("yyyyMMddHHmmss").format(System.currentTimeMillis());
		reqxml.append("<timestamp>").append(nowTime).append("</timestamp>");

		String bodyxml = buildBody1009Xml(barcode, voteId, idNo);
		log.debug("1009body xml is--" + bodyxml);
		reqxml.append("<md>").append(DigestUtils.md5Hex(bodyxml)).append("</md>");
		reqxml.append("</head>").append(bodyxml).append("</message>");

		return reqxml.toString();
	}

	public String formatMsgId(String merNum) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String sysDate = df.format(new Date());
		return merNum + sysDate + System.currentTimeMillis();
	}

	private String buildBody1009Xml(String barcode, String voteId, String idNo) {
		StringBuilder bodyxml = new StringBuilder();
		bodyxml.append("<body>");
		bodyxml.append("<wagercardnum>").append(voteId).append("</wagercardnum>");
		bodyxml.append("<idno>").append(idNo).append("</idno>");
		bodyxml.append("<barcode>").append(barcode).append("</barcode>");
		bodyxml.append("</body>");
		return bodyxml.toString();
	}

	@Override
	public ResponseMessageBody parseResponseBodyXML(ResponseHeadBean head, String responsexml) {
		ResponseMessage1009Body body = new ResponseMessage1009Body();
		System.out.println(responsexml);
		AutoPilot ap = null;
		try {
			if (head == null) {
				throw new Exception("MessageHead object is null");
			}
			VTDGen vg = new VTDGen();
			vg.setDoc(responsexml.getBytes("UTF-8"));
			vg.parse(false);
			VTDNav vn = vg.getNav();
			ap = new AutoPilot();
			ap.bind(vn);
			body.setGamecode(XmlBuilderUtils.getXPathValue(ap, "/message/body/gamecode"));
			body.setGameserial(XmlBuilderUtils.getXPathValue(ap, "/message/body/gameserial"));
			body.setPackagenum(XmlBuilderUtils.getXPathValue(ap, "/message/body/packagenum"));
			body.setTicketnum(XmlBuilderUtils.getXPathValue(ap, "/message/body/ticketnum"));
			body.setWinState(Integer.parseInt(XmlBuilderUtils.getXPathValue(ap, "/message/body/winstate")));
			body.setWinAmount(Long.parseLong(XmlBuilderUtils.getXPathValue(ap, "/message/body/winamount")));
			body.setCommisionAmount(Long.parseLong(XmlBuilderUtils.getXPathValue(ap, "/message/body/commisionamount")));
			ap.resetXPath();
			vg.clear();
		} catch (Exception e) {
			log.warn("xml parse error",e);
		}
		return body;
	}

	public static void main(String[] args) {
		// ReqScratchValidEntity en=new ReqScratchValidEntity();
		// en.setBarcode("21324123jslkdajfsajdla");
		// TransMessageEntity msg =new TransMessageEntity();
		// msg.setBody(en);
		// MobileUser user=new MobileUser();
		// user.setVoteid("111111");
		// user.setIdno("130602");
		//
		// XmlBuilder1009 builder1009=new XmlBuilder1009();
		// System.out.println(builder1009.buildRequestXml(msg, user));;
		/*XmlBuilder1009 xmlBuilder1009 = new XmlBuilder1009();
		String responsexml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><message><head>"
				+ "<messageid>cp201207231400000001</messageid><command>1000</command>"
				+ "<timestamp>20120723140002</timestamp><result>001</result><md>a2d346f4c60b6f6da5b934d41b01b3ec</md></head><body>"
				+ "<gamecode>G0233</gamecode>" + "<gameserial>12037</gameserial><packagenum>23498</packagenum>"
				+ "<ticketnum>103</ticketnum><winstate>1</winstate><winamount>10000</winamount>" + "<amountsign>0</amountsign></body></message>";
		ResponseHeadBean head = new ResponseHeadBean();

		try {
			ResponseMessageBody b = xmlBuilder1009.parseResponseBodyXML(head, responsexml);
			System.out.println(BeanUtils.describe(b));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}*/
	}

}
