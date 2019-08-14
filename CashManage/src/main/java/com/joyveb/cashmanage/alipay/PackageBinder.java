package com.joyveb.cashmanage.alipay;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;

import com.ximpleware.AutoPilot;
import com.ximpleware.EOFException;
import com.ximpleware.EncodingException;
import com.ximpleware.EntityException;
import com.ximpleware.ParseException;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XPathParseException;

@Slf4j
public class PackageBinder {
	
	public static final int SUCCESS=1;//成功
	
	public static final int FAIL=2;//失败
	
	public static final String TRADE_FAIL="F";//淘宝返回请求失败；
	
	public static final String TRADE_SUCCESS="T";//淘宝返回请求成功；
	
	public static final String TRADE_FINISHED="SUCCESS";//充值完成
	
	public static void  bindWithDrawRecord(TransactionRecord transactionRecord){
		
		try {
			VTDGen vg = new VTDGen();
			vg.setDoc(transactionRecord.getAlipayrecord().getBytes("utf-8"));
			vg.parse(false);
			VTDNav vn = vg.getNav();
			AutoPilot ap = new AutoPilot();
			ap.bind(vn);
			String tradeStatus = getXPathValue(ap, "/alipay/is_success");
			String result_code = getXPathValue(ap, "/alipay/response/alipay/result_code");
			if(StringUtils.isNotBlank(tradeStatus) && StringUtils.isNotBlank(result_code)){
				AlipayProcess processEnum = AlipayProcess.getStatusByTradeStatus(tradeStatus,result_code);
				if(processEnum!=null){
					transactionRecord.setStatus(processEnum.getStatus());
				}
			}
			if(transactionRecord.getStatus()==FAIL){//失败
				transactionRecord.setErrormsg(getXPathValue(ap, "/alipay/response/alipay/error_msg"));
			}
			String amount = getXPathValue(ap, "/alipay/request/param[@name='amount']");
			String receiverNum = getXPathValue(ap, "/alipay/request/param[@name='payee_account']");
			transactionRecord.setReceiverNum(receiverNum);
			transactionRecord.setAmount(new BigDecimal(amount).longValue());
			transactionRecord.setOutBizNo(getXPathValue(ap, "/alipay/request/param[@name='out_biz_no']"));
			transactionRecord.setOrderNo(getXPathValue(ap, "/alipay/response/alipay/order_id"));
			String payDate = getXPathValue(ap, "/alipay/response/alipay/pay_date");
			transactionRecord.setTradeStatus(result_code);
			if(StringUtils.isNotBlank(payDate)){
				transactionRecord.setProcesseddate(format2Date(payDate));
			}else{
				transactionRecord.setProcesseddate(System.currentTimeMillis());
			}
		} catch (UnsupportedEncodingException e) {
			log.warn("xml dose not support utf-8 encoding",e);
		} catch (EncodingException e) {
			log.warn("encoding error",e);
		} catch (EOFException e) {
			log.warn("xml parse error",e);
		} catch (EntityException e) {
			log.warn("xml parse error",e);
		} catch (ParseException e) {
			log.warn("xml parse error",e);
		} catch (XPathParseException e) {
			log.warn("xml parse error",e);
		} catch (java.text.ParseException e) {
			log.warn("xml parse error",e);
		}
	}
	
	private static String getXPathValue(AutoPilot ap, String xPath)
			throws XPathParseException{
		ap.selectXPath(xPath);
		String txt = ap.evalXPathToString();
		if (txt == null || txt.length() <= 0) {
			log.debug(xPath + " is empty");
		}
		return txt;
	}
	
	private static String getXPathValue(AutoPilot ap, String xPath,
			String defaultv) throws XPathParseException {
		ap.selectXPath(xPath);
		String txt = ap.evalXPathToString();
		if (txt == null || txt.length() <= 0) {
			return defaultv;
		}
		return txt;
	}
	
	private static Long format2Date(String payDate) throws java.text.ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return format.parse(payDate).getTime();
	}
	
	/**
	 * B2C 查询
	 * @param transactionRecord
	 */
	public static void  bindQueryRecord(TransactionRecord transactionRecord){
		VTDGen vg = new VTDGen();
		try {
			if(null!=transactionRecord.getAlipayrecord()){
				vg.setDoc(transactionRecord.getAlipayrecord().getBytes(AlipayConfig.input_charset));
			}else{
				vg.setDoc(null);
			}
			vg.parse(false);
			VTDNav vn = vg.getNav();
			AutoPilot ap = new AutoPilot();
			ap.bind(vn);
			String tradeStatus = getXPathValue(ap, "/alipay/is_success");
			String result_code = getXPathValue(ap, "/alipay/response/alipay/status");
			AlipayProcess processEnum = AlipayProcess.getStatusByTradeStatus(tradeStatus,result_code);
			if(processEnum!=null){
				transactionRecord.setStatus(processEnum.getStatus());
			}
			transactionRecord.setTradeStatus(result_code);
			transactionRecord.setOutBizNo(getXPathValue(ap, "/alipay/response/alipay/out_biz_no"));
			transactionRecord.setOrderNo(getXPathValue(ap, "/alipay/response/alipay/order_id"));
			String payDate = getXPathValue(ap, "/alipay/response/alipay/pay_date");
			if(StringUtils.isNotBlank(payDate)){
				transactionRecord.setProcesseddate(format2Date(payDate));
			}else{
				transactionRecord.setProcesseddate(System.currentTimeMillis());
			}
		} catch (Exception e) {
			log.warn("支付宝转账失败，请联系运维人员处理",e);
		}
	}
	
	/**
	 * 即时查询
	 */
	public static void bindImmediatelyRecord(TransactionRecord transactionRecord){
		VTDGen vg = new VTDGen();
		try {
			vg.setDoc(transactionRecord.getAlipayrecord().getBytes("utf-8"));
			vg.parse(false);
			VTDNav vn = vg.getNav();
			AutoPilot ap = new AutoPilot();
			ap.bind(vn);
			String tradeStatus = getXPathValue(ap, "/alipay/is_success");
			String result_code = getXPathValue(ap, "/alipay/response/alipay/result_code");
			if(TRADE_FINISHED.equals(result_code) && TRADE_SUCCESS.equals(tradeStatus)){
				transactionRecord.setStatus(SUCCESS);
			} else if(TRADE_FAIL.equals(tradeStatus)){
				result_code=getXPathValue(ap, "/alipay/error");
			} else {
				transactionRecord.setStatus(FAIL);
			}
			String amount=getXPathValue(ap,"/alipay/response/trade/total_fee");
			if(StringUtils.isNotBlank(amount)){
				transactionRecord.setAmount(new BigDecimal(amount).multiply(new BigDecimal(100)).longValue());
			}
			String buyer_email = getXPathValue(ap, "/alipay/response/trade/buyer_email");
			//buyer_email
			transactionRecord.setCardHolder(buyer_email);
			if(StringUtils.isNotBlank(result_code)){
				transactionRecord.setTradeStatus(result_code);
			}
			String outBizNo = getXPathValue(ap, "/alipay/request/param[@name='out_trade_no']");
			if(StringUtils.isNotBlank(outBizNo)){
				transactionRecord.setOutBizNo(getXPathValue(ap, "/alipay/request/param[@name='out_trade_no']"));
			}
			transactionRecord.setOrderNo(getXPathValue(ap, "/alipay/response/trade/trade_no"));
			String payDate = getXPathValue(ap, "/alipay/response/alipay/pay_date");
			if(StringUtils.isNotBlank(payDate)){
				transactionRecord.setProcesseddate(format2Date(payDate));
			}else{
				transactionRecord.setProcesseddate(System.currentTimeMillis());
			}
		} catch (UnsupportedEncodingException e) {
			log.warn("xml dose not support utf-8 encoding error",e);
		} catch (EncodingException e) {
			log.warn("encoding error",e);
		} catch (EOFException e) {
			log.warn("xml parse error",e);
		} catch (EntityException e) {
			log.warn("xml parse error",e);
		} catch (ParseException e) {
			log.warn("xml parse error",e);
		} catch (XPathParseException e) {
			log.warn("xml parse error",e);
		} catch (java.text.ParseException e) {
			log.warn("xml parse error",e);
		}
	}
}
