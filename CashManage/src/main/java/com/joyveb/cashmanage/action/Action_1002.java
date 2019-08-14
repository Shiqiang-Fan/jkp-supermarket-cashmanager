package com.joyveb.cashmanage.action;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.joyveb.cashmanage.service.WhiteListService;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.http.client.ClientProtocolException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.alipay.service.AlipayService;
import com.joyveb.cashmanage.bean.AbstractEntity;
import com.joyveb.cashmanage.bean.CashEntity;
import com.joyveb.cashmanage.bean.TransMessageEntity;
import com.joyveb.cashmanage.bean.request.ReqCashEntity;
import com.joyveb.cashmanage.bean.request.ReqCashEntityHolder;
import com.joyveb.cashmanage.bean.response.RepCashEntity;
import com.joyveb.cashmanage.common.B2bResultCode;
import com.joyveb.cashmanage.common.Constants;
import com.joyveb.cashmanage.common.PrizeSessionsCache;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.common.Session;
import com.joyveb.cashmanage.common.ValidPrize;
import com.joyveb.cashmanage.entity.GameInput;
import com.joyveb.cashmanage.entity.GameInputExample;
import com.joyveb.cashmanage.entity.Prizer;
import com.joyveb.cashmanage.entity.PrizerExample;
import com.joyveb.cashmanage.entity.PrizerKey;
import com.joyveb.cashmanage.entity.WhiteList;
import com.joyveb.cashmanage.service.GameInputService;
import com.joyveb.cashmanage.service.PrizerService;
import com.joyveb.cashmanage.utils.DESCoder;
import com.joyveb.cashmanage.utils.HttpRequestor;
import com.joyveb.cashmanage.utils.JsonUtil;
import com.joyveb.cashmanage.utils.MixBarcodeUtil;
import com.joyveb.cashmanage.utils.Utils;
import com.joyveb.cashmanage.validate.Validateable;
import com.joyveb.cashmanage.web.InitParm;
import com.joyveb.cashmanage.xml.XmlBuilder1009;

/**
 * 
 * 兑奖
 * 
 */
@Slf4j
@Component("action_1002")
public class Action_1002 extends AbstractReqAction {

	@Resource(name = "validator_1002")
	private Validateable validator;
	@Resource(name = "xmlBuilder1009")
	private XmlBuilder1009 xmlBuilder1009;
	@Resource(name = "httpRequestor")
	private HttpRequestor httpRequestor;
	@Resource(name = "alipayService")
	private AlipayService alipayService;
	@Resource(name = "prizerService")
	private PrizerService prizerService;
	@Resource(name = "initParm")
	private InitParm initParm;
	@Resource(name = "gameInputService")
	private GameInputService gameInputService;
	private @Resource(name = "prizeSessionsCache")
	PrizeSessionsCache psc;
	@Resource(name = "whiteListService")
	private WhiteListService whiteListService;
	private final static String VALIDPRIZE_HEAD_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+ "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"+ "<soapenv:Body>"+ "<ns1:execute soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:ns1=\"http://service.escb2b.gtech.com\">"+ "<fn xsi:type=\"soapenc:string\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">{0}"+ "</fn>"+ "<attr soapenc:arrayType=\"ns1:NameValuePair[7]\" xsi:type=\"soapenc:Array\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">"+ "<attr href=\"#id0\"/>"+ "<attr href=\"#id1\"/>"+ "<attr href=\"#id2\"/>"+ "<attr href=\"#id3\"/>"+ "<attr href=\"#id4\"/>"+ "<attr href=\"#id5\"/>"+ "<attr href=\"#id6\"/>"+ "</attr>"+ "</ns1:execute>";
	private final static String VALIDPRIZE_BODY_XML = "<multiRef id=\"{0}\" soapenc:root=\"0\" soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\" xsi:type=\"{1}:NameValuePair\" xmlns:{1}=\"http://service.escb2b.gtech.com\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\"><name xsi:type=\"soapenc:string\">{2}</name><value xsi:type=\"soapenc:string\">{3}</value></multiRef>";
	private Session session;
	private String gamecode = "";
	private String productbatch = "";
	private String booknum = "";
	private String ticnum = "";
	private String decodeBarcode;

	@Override
	public AbstractEntity process(TransMessageEntity msg) {
		RepCashEntity entity = new RepCashEntity();
		PrizerKey pp = new PrizerKey();
		ReqCashEntity reqCashEntity = (ReqCashEntity) msg.getBody();
		pp.setMessageid(msg.head.getMessageid());
		Prizer selectByPrimaryKey = prizerService.selectByPrimaryKey(pp);
		WhiteList whiteList = getTokenMap().get(msg.getRequest().getHeader("token"));
		if (null == selectByPrimaryKey) {
			if (null != whiteList) {
				String username = whiteList.getUsername();
				String totalname = username + "total";
				String barCode = reqCashEntity.getBarcode();
				// String requestXml = this.getReqXMl(barCode, "0000" + whiteList.getUsername(),whiteList.getIdcard());
				try {
					// 检查是否已兑奖 是否限制兑非系统票 连续或累计兑奖未中奖次数限制
					RepCashEntity check = check(barCode, entity, username);
					if (null != check) {
						return check;
					}
					session = psc.getPrizeSession();
					if (session == null) {// 终端繁忙
						processValidFailCondi(entity, ResponseResult.SERVER_BUSY.getCode(), ResponseResult.SERVER_BUSY.getDesc());
						return entity;
					}
					String requestxml = getValidPrizeXML(session, decodeBarcode);
					log.debug("CashManage send to GtechSim xml : " + requestxml);
					this.psc.release(session);
					String resultXml = httpRequestor.post(requestxml, initParm.getIntDbp("posttimeout", 10000));
					Map<String, String> b2brespmap = mapFromB2bXML(resultXml);
					if (null == b2brespmap || b2brespmap.size() == 0) {
						processValidFailCondi(entity, B2bResultCode.CASH_NOT_SUCC.getCode() + "", B2bResultCode.CASH_NOT_SUCC.getDesc());
						log.info(ResponseResult.CONNECTB2BERROR.getDesc() + " sessionId[" + session.getId() + "] B2B response为空");
						return entity;
					} else {
						String resultCode = StringUtils.defaultString(b2brespmap.get("resultCode"), "45");
						String wonAmount = StringUtils.defaultString(b2brespmap.get("wonAmount"), "0");
						if (resultCode.equals("null")) {
							resultCode = "45";
						}
						if (wonAmount.equals("null")) {
							wonAmount = "0";
						}
						log.debug("兑奖终端ID： " + session.getId() + " and 返回码:" + resultCode + " and 中奖金额(分):" + wonAmount);
						log.debug("GtechSim Return XML: " + resultXml);
						log.debug("xml转换map： " + b2brespmap);
						if (resultCode.equals(B2bResultCode.PAY_THE_WINNER.getCode() + "")
								|| resultCode.equals(B2bResultCode.File_claim.getCode() + "")) {
							entity.setRcode(ResponseResult.SUCCESS.getCode());
							entity.setDes(ResponseResult.SUCCESS.getDesc());
							String barcode = gamecode + "-" + productbatch + "-" + booknum + "-" + ticnum;
							Prizer prizer = new Prizer();
							prizer.setBarcode(barcode);
							prizer.setDeviceid(msg.head.getDeviceid());
							prizer.setMessageid(msg.head.getMessageid());
							prizer.setRequesttime(System.currentTimeMillis());
							prizer.setUserid(whiteList.getUsername());
							String parentByName = whiteListService.selectParentByName(whiteList.getUsername());
							prizer.setParentname(parentByName);
							// prizer.setUserid(alipayUserInfo.getAlipayUserId());
							prizer.setBarcode(barcode);
							if (resultCode.equals(B2bResultCode.File_claim.getCode() + "")) {
								prizer.setAmountsign("1");// 1表示大奖
								prizer.setWinamount(null);
								PrizerExample pe = new PrizerExample();
								pe.createCriteria().andBarcodeEqualTo(prizer.getBarcode()).andDeviceidEqualTo(prizer.getDeviceid())
										.andAmountsignEqualTo(prizer.getAmountsign()).andUseridEqualTo(prizer.getUserid());
								List<Prizer> priList = prizerService.selectByExample(pe);
								if (null == priList || priList.size() == 0) {
									prizerService.insert(prizer);
								}
								entity.setAmountTip("恭喜您中得大奖，请到福彩指定地点领奖！");
								entity.setWinstate(Constants.WINSTATE_BIG_PRIZE);
								entity.setDes("恭喜您中得大奖，请到福彩指定地点领奖！");
								initParm.clearCashMap(username);
							} else {
								prizer.setAmountsign("0");
								prizer.setWinamount(Integer.parseInt((wonAmount)));
								// 小奖 往T_CORE_PRIZE存一条记录
								prizer.setTransferstatus("2");
								prizerService.insert(prizer);
								entity.setWinstate(Constants.WINSTATE_LITTLE_PRIZE);
								entity.setDes("恭喜您中得小奖");
								initParm.clearCashMap(username);
							}
						} else if (resultCode.equals(B2bResultCode.Not_a_winner.getCode() + "")
								|| resultCode.equals(B2bResultCode.Previously_paid_by_other.getCode() + "")
								|| resultCode.equals(B2bResultCode.Previously_paid_by_you.getCode() + "")) {
							entity.setRcode("001");
							CashEntity alCashMap = initParm.getAlCashMap(username);
							CashEntity tota = initParm.getAlCashMap(totalname);
							CashEntity ce = new CashEntity();
							ce.setCashtime(System.currentTimeMillis());
							if (null == alCashMap) {
								ce.setLimitnum(1);
							} else {
								ce.setLimitnum(alCashMap.getLimitnum() + 1);
							}
							if (null == tota) {
								ce.setTotalnum(1);
							} else {
								ce.setTotalnum(tota.getTotalnum() + 1);
							}
							ce.setUsername(username);
							initParm.setAlCashMap(username, ce);// 连续兑奖次数限制
							initParm.setAlCashMap(totalname, ce);// 累计兑奖次数限制
							entity.setWinstate(Short.parseShort(resultCode));
						} else {
							errorCode(resultCode, entity, b2brespmap);
						}
						entity.setGamecode(gamecode);
						entity.setGameserial(productbatch);
						entity.setPackagenum(booknum);
						entity.setTicketnum(ticnum);
						entity.setWinamount(Long.parseLong(wonAmount));
					}
				} catch (ClientProtocolException e) {
					log.warn(ResponseResult.CONNECTB2BERROR.getDesc() + " 兑奖发送http请求异常：" + e);
					entity.setRcode(B2bResultCode.CASH_NOT_SUCC.getCode() + "");
					entity.setDes(B2bResultCode.CASH_NOT_SUCC.getDesc());
				} catch (IOException e) {
					log.warn(ResponseResult.CONNECTB2BERROR.getCode() + " 兑奖IO异常：" + e);
					entity.setRcode(B2bResultCode.CASH_NOT_SUCC.getCode() + "");
					entity.setDes(B2bResultCode.CASH_NOT_SUCC.getDesc());
				}
			} else {
				log.info(ResponseResult.NOTLOGIN.getDesc());
				processValidFailCondi(entity, ResponseResult.NOTLOGIN.getCode(), ResponseResult.NOTLOGIN.getDesc());
			}
		} else {
			log.warn(ResponseResult.MISSAGEIDREP.getDesc());
			processValidFailCondi(entity, ResponseResult.MISSAGEIDREP.getCode(), ResponseResult.MISSAGEIDREP.getDesc());
			entity.setDes(ResponseResult.MISSAGEIDREP.getDesc());
		}
		return entity;
	}

	private void processValidFailCondi(RepCashEntity entity, String resultcode, String des) {
		entity.setRcode(resultcode);
		entity.setDes(des);
	}

	@Override
	public AbstractEntity processValidFail(ResponseResult vr) {
		RepCashEntity repCashEntity = new RepCashEntity();
		repCashEntity.setRcode(ResponseResult.NOT_PRIZE_TIME.getCode());
		repCashEntity.setDes(ResponseResult.NOT_PRIZE_TIME.getDesc());
		return repCashEntity;
	}

	@Override
	public AbstractEntity parseMessageBody(String reqjson) throws Exception {
		ReqCashEntityHolder holder = JsonUtil.json2Bean(reqjson, ReqCashEntityHolder.class);
		return holder.getBody();
	}

	@Override
	public ResponseResult validate(TransMessageEntity msg) {
		return validator.validate(msg);
	}

	public RepCashEntity check(String barCode, RepCashEntity entity, String username) {
		String desDecrypt = DESCoder.desDecrypt(barCode, Constants.MERCHANT_ID_KEY);
		decodeBarcode = MixBarcodeUtil.decodeBarcode(desDecrypt);
		if (null != decodeBarcode) {
			gamecode = decodeBarcode.substring(0, 5);
			productbatch = decodeBarcode.substring(5, 10);
			booknum = decodeBarcode.substring(10, 17);
			ticnum = decodeBarcode.substring(17, 20);
		} else {
			log.info("barcode为空！");
		}
		if (initParm.getStringDbp("cash.rest", "0").equals("1")) {// 兑奖限制 // 0不限制// 1兑奖票必须为本系统卖出的票
			GameInputExample gameInputExample = new GameInputExample();
			gameInputExample.createCriteria().andGamecodeEqualTo(gamecode).andProductbatchEqualTo(productbatch).andBooknumEqualTo(booknum);
			List<GameInput> selectByExample = gameInputService.selectByExample(gameInputExample);
			if (selectByExample.size() <= 0) {
				log.info(ResponseResult.NOTBOOKNUM.getDesc());
				processValidFailCondi(entity, ResponseResult.NOTBOOKNUM.getCode(), ResponseResult.NOTBOOKNUM.getDesc());
				return entity;
			}
		}

		String bc = gamecode + "-" + productbatch + "-" + booknum + "-" + ticnum;
		PrizerExample pepe = new PrizerExample();
		pepe.createCriteria().andBarcodeEqualTo(bc).andAmountsignEqualTo("0");
		List<Prizer> selectByExample2 = prizerService.selectByExample(pepe);
		if (selectByExample2.size() > 0) {// 查询prizer表中是否有记录 有就是已兑奖不往中心发
			entity.setGamecode(gamecode);
			entity.setGameserial(productbatch);
			entity.setPackagenum(booknum);
			entity.setTicketnum(ticnum);
			processValidFailCondi(entity, ResponseResult.ALREADY_CASH.getCode(), ResponseResult.ALREADY_CASH.getDesc());
			return entity;
		}

		int lim = initParm.getIntDbp("limit.cash", 3);
		int tot = initParm.getIntDbp("total.cash", 99);
		CashEntity alCashMap = initParm.getAlCashMap(username);
		CashEntity tal = initParm.getAlCashMap(username + "total");
		if (null != alCashMap && alCashMap.getLimitnum() >= lim && lim != 0) {
			if (Utils.isdiff(alCashMap.getCashtime())) {
				processValidFailCondi(entity, ResponseResult.LIMITCASH.getCode(), "连续兑奖未中奖" + lim + "次，用户已被锁定！");
				return entity;
			} else {
				initParm.clearCashMap(username);
			}
		}
		if (null != tal && tal.getTotalnum() >= tot && tot != 0) {
			if (Utils.isdiff(tal.getCashtime())) {
				processValidFailCondi(entity, ResponseResult.TOTALCASH.getCode(), "累计兑奖未中奖" + tot + "次，用户已被锁定");
				return entity;
			} else {
				initParm.clearCashMap(username + "total");
			}
		}
		return null;
	}

	private static void errorCode(String resultCode, RepCashEntity entity, Map<String, String> b2brespmap) {
		for (int i = 0; i < B2bResultCode.CanLockErrors.size(); i++) {
			if (resultCode.equals(B2bResultCode.CanLockErrors.get(i) + "")) {
				entity.setRcode(resultCode);
				entity.setDes(B2bResultCode.getById(Integer.parseInt(resultCode)).getDesc());
				if (resultCode.equals(B2bResultCode.CASH_NOT_SUCC.getCode() + "")) {
					log.debug("GtechSim return resultText: " + b2brespmap.get("resultText"));
				}
			}
		}
	}

	private String getValidPrizeXML(Session session, String serialNum) throws IllegalArgumentException {
		ValidPrize vp = new ValidPrize();
		vp.setSerialNumber(serialNum);
		vp.setTransaction("validRequest");
		vp.setTransactionType("Ival");
		vp.setSessionID(session.getId());
		vp.setSequenceNumber(String.valueOf(session.getSequenceNumber()));
		vp.setTime(FastDateFormat.getInstance("HH:mm:ss").format(System.currentTimeMillis()));
		vp.setChecksum(getCheckSum(vp));
		StringBuilder buff = new StringBuilder();
		buff.append(MessageFormat.format(VALIDPRIZE_HEAD_XML, vp.getTransactionType()))
				.append(MessageFormat.format(VALIDPRIZE_BODY_XML, "id5", "ns2", "time", vp.getTime()))
				.append(MessageFormat.format(VALIDPRIZE_BODY_XML, "id1", "ns3", "transaction", vp.getTransaction()))
				.append(MessageFormat.format(VALIDPRIZE_BODY_XML, "id6", "ns4", "checksum", vp.getChecksum()))
				.append(MessageFormat.format(VALIDPRIZE_BODY_XML, "id4", "ns5", "sequenceNumber", vp.getSequenceNumber()))
				.append(MessageFormat.format(VALIDPRIZE_BODY_XML, "id0", "ns6", "transactionType", vp.getTransactionType()))
				.append(MessageFormat.format(VALIDPRIZE_BODY_XML, "id3", "ns7", "sessionID", vp.getSessionID()))
				.append(MessageFormat.format(VALIDPRIZE_BODY_XML, "id2", "ns8", "serialNumber", vp.getSerialNumber()));
		buff.append("</soapenv:Body></soapenv:Envelope>");
		return buff.toString();
	}

	private String getCheckSum(ValidPrize vp) {
		StringBuilder sb = new StringBuilder("");
		List<String> factors = new ArrayList<String>();
		factors.add("serialNumber=" + vp.getSerialNumber());
		factors.add("sessionID=" + vp.getSessionID());
		factors.add("time=" + vp.getTime());
		factors.add("transaction=" + vp.getTransaction());
		factors.add("sequenceNumber=" + vp.getSequenceNumber());
		Collections.sort(factors);
		for (int i = 0; i < factors.size(); i++) {
			sb.append(factors.get(i));
			if (i != factors.size() - 1) {
				sb.append(":");
			}
		}
		log.debug("Redeem the prize request:" + sb.toString());
		return DigestUtils.md5Hex(sb.toString());
	}

	/**
	 * 将b2b字符串xml转换成Map<String, String>
	 * 
	 * @throws DocumentException
	 * */
	private Map<String, String> mapFromB2bXML(String xmlStr) {
		Map<String, String> strMap = new HashMap<String, String>();
		try {
			Document doc = DocumentHelper.parseText(xmlStr);
			Element root = doc.getRootElement();
			Element soapenvBodyElm = root.element("Body");
			List<?> nodes = soapenvBodyElm.elements("multiRef");
			for (Iterator<?> it = nodes.iterator(); it.hasNext();) {
				Element elm = (Element) it.next();
				Element nameElm = elm.element("name");
				Element valueElm = elm.element("value");
				strMap.put(nameElm.getTextTrim(), valueElm.getTextTrim());
			}
		} catch (DocumentException e) {
			log.warn("[兑奖] 将B2B响应的XML转换成Map异常:", e);
		}
		return strMap;
	}
}
