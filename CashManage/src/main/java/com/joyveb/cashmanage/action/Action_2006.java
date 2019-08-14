package com.joyveb.cashmanage.action;

import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.joyveb.cashmanage.alipay.GoodsDetail;
import com.joyveb.cashmanage.alipay.scan.AlipayTradePrecreateRequestBuilder;
import com.joyveb.cashmanage.alipay.service.AlipayService;
import com.joyveb.cashmanage.bean.AbstractEntity;
import com.joyveb.cashmanage.bean.TransMessageEntity;
import com.joyveb.cashmanage.bean.request.ReqGamesEntity;
import com.joyveb.cashmanage.bean.request.ReqOrderEntity;
import com.joyveb.cashmanage.bean.request.ReqPayOrderHolder;
import com.joyveb.cashmanage.bean.response.RepOrderIdEntity;
import com.joyveb.cashmanage.common.Constants;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.entity.*;
import com.joyveb.cashmanage.service.*;
import com.joyveb.cashmanage.utils.IDGenerator;
import com.joyveb.cashmanage.utils.JsonUtil;
import com.joyveb.cashmanage.utils.ZxingUtils;
import com.joyveb.cashmanage.validate.Validateable;
import com.joyveb.cashmanage.web.InitParm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;

/**
 * 
 * 终端下单
 * 
 */
@Slf4j
@Component("action_2006")
public class Action_2006 extends AbstractReqAction {

	@Resource(name = "validator_2006")
	private Validateable validator;
	@Resource(name = "orderInfoService")
	private OrderInfoService orderInfoService;
	@Resource(name = "shopInfoService")
	private ShopInfoService shopInfoService;
	@Resource(name = "receiverInfoService")
	private ReceiverInfoService receiverInfoService;
	@Resource(name = "initParm")
	private InitParm initParm;
	@Resource(name = "stockManageService")
	private StockManageService stockManageService;
	@Resource(name = "whiteListService")
	private WhiteListService whiteListService;
	@Override
	public AbstractEntity process(TransMessageEntity msg) {
		RepOrderIdEntity entity = new RepOrderIdEntity();
		ReqOrderEntity reqOrderEntity = (ReqOrderEntity) msg.getBody();
		WhiteList whiteList = getTokenMap().get(msg.getRequest().getHeader("token"));
		String outTradeNo = IDGenerator.getInstance().generate();
		String codeImagePath = "";
		List<ReqGamesEntity> gamess = null;
		OrderInfo oi = new OrderInfo();
		Long totalamount = 0L;
		if (null != whiteList) {
			if (null != reqOrderEntity) {
				ReceiverInfoKey rKey = new ReceiverInfoKey();
				boolean again = reqOrderEntity.getAgainpay().equals("1");// 1为再次支付
				if (StringUtils.isNotBlank(reqOrderEntity.getAgainpay()) && again) {
					String orderid = reqOrderEntity.getOrderid();
					OrderInfoKey oik = new OrderInfoKey();
					oik.setOrderid(orderid);
					OrderInfo oInfo = orderInfoService.selectByPrimaryKey(oik);
					if (null != oInfo) {
						totalamount = oInfo.getTotalamount();
					}
					outTradeNo = orderid;
					String receiverid = queryReceiverByOrderid(orderid);
					rKey.setUuid(receiverid);
					gamess = queryGamesByOrderid(orderid);
				} else {
					rKey.setUuid(reqOrderEntity.getReceiverid());
					gamess = reqOrderEntity.getGames();
				}
				ReceiverInfo selectByPrimaryKey = receiverInfoService.selectByPrimaryKey(rKey);
				int sizes = gamess.size();
				long nm = 0l;
				long am = reqOrderEntity.getFreightprice();
				boolean isTrue = false;
				String kc = "";
				if (null != gamess && sizes > 0) {
					for (int y = 0; y < sizes; y++) {
						ReqGamesEntity reqGamesEntity = gamess.get(y);
						Long gamenum = reqGamesEntity.getGamenum();
						long orderamount = reqGamesEntity.getOrderamount();
						am += orderamount;
						nm += gamenum;
						String gamecode = reqGamesEntity.getGamecode();
						StockManageKey smk = new StockManageKey();
						smk.setGamecode(gamecode);
						String gamename = initParm.getGameNameByCode(gamecode);
						StockManage stock = stockManageService.selectByPrimaryKey(smk);
						if (null == stock) {
							log.info(gamename + "库存不足！");
							isTrue = true;
							break;
						} else if (stock.getTotal() - gamenum < 0) {
							kc += gamename + " ";
							log.info(gamename + "库存不足！");
							isTrue = true;
							break;
						}
					}
					if (!reqOrderEntity.getAgainpay().equals("1")) {
						if (null == selectByPrimaryKey || reqOrderEntity.getTotalamount() != am || reqOrderEntity.getTotalnum() != nm) {
							entity.setRcode(ResponseResult.PARAM_ERROR.getCode());
							entity.setDes(ResponseResult.PARAM_ERROR.getDesc());
							return entity;
						}
					}
					if (isTrue) {
						entity.setRcode(ResponseResult.STOCK.getCode());
						entity.setDes(kc + "游戏库存不足！");
					} else {
						long currentTimeMillis = currentTimeMillis();
						// oi.setDelivertime(reqOrderEntity.getDelivertime());
						String parentByName = whiteListService.selectParentByName(whiteList.getUsername());
						oi.setReceiverid(reqOrderEntity.getReceiverid());
						oi.setOrderid(outTradeNo);
						oi.setPaytime(currentTimeMillis);
						oi.setOrderstatus(Constants.WAITPAY);// 下单 待支付
						oi.setUserid(whiteList.getUsername());
						oi.setParentname(parentByName);
						oi.setTotalamount(reqOrderEntity.getTotalamount());
						oi.setOrdertime(currentTimeMillis());
						oi.setFreightprice(reqOrderEntity.getFreightprice());
						if (!again) {
							orderInfoService.insert(oi);
							// 插入商品表
							insertShop(gamess, outTradeNo);
						}
						Long totalamount1 = 0L;
						if (reqOrderEntity.getAgainpay().equals("1")) {
							totalamount1 = totalamount;
						} else {
							totalamount1 = reqOrderEntity.getTotalamount();
						}
						Long valueOf = Long.valueOf(totalamount1);
						Double bb = valueOf / 100.00;
						AlipayTradePrecreateResponse response = initAlipayClient(outTradeNo, bb, whiteList.getUsername(), gamess);
						String code = "";
						if (null != response) {
							code = response.getCode();
							log.info(response.getCode() + response.getSubMsg());
							if (code.equals(Constants.SUCCESS)) {
								log.info("支付宝下单成功！");
								String realPath = msg.getRequest().getSession().getServletContext().getRealPath("/");
								log.debug("realPath::" + realPath);
								String filePath = String.format(realPath + "/upload/gamesImage/%s.png", response.getOutTradeNo());
								codeImagePath = String.format("/upload/gamesImage/%s.png", response.getOutTradeNo());
								ZxingUtils.getQRCodeImge(response.getQrCode(), 256, filePath);
								// 将所有订单放入队列中，定时轮询是否已支付
								getOrderMap().put(outTradeNo, oi);
								// oi.setOrderstatus(Constants.ALREADYPAY);
								// orderInfoService.updateByPrimaryKey(oi);
							} else if (code.equals(Constants.FAILED)) {
								log.debug("支付宝扫码支付失败！" + "  " + response.getMsg());
								oi.setOrderstatus(Constants.PAYFAIL);
								oi.setFailreason(response.getSubMsg());
								orderInfoService.updateByPrimaryKey(oi);
							}else {
								log.debug(response.getCode() + response.getSubMsg());
							}
						} else {
							log.debug("支付宝扫码付系统异常！");
						}
						entity.setOrderid(outTradeNo);
						entity.setTwocodepath(codeImagePath);
						entity.setRcode(ResponseResult.SUCCESS.getCode());
						entity.setDes(ResponseResult.SUCCESS.getDesc());
						entity.setPaytimeout(initParm.getStringDbp("cancel.time", "5"));
					}
				} else {
					log.info(ResponseResult.PARAM_ERROR.getDesc());
					processValidFailCondi(entity, ResponseResult.PARAM_ERROR.getCode());
					entity.setDes(ResponseResult.PARAM_ERROR.getDesc());
				}
			} else {
				log.info(ResponseResult.NOTLOGIN.getDesc());
				processValidFailCondi(entity, ResponseResult.NOTLOGIN.getCode());
				entity.setDes(ResponseResult.NOTLOGIN.getDesc());
			}
		}
		return entity;
	}

	private void processValidFailCondi(RepOrderIdEntity entity, String resultcode) {
		entity.setRcode(resultcode);
	}
	@Override
	public AbstractEntity processValidFail(ResponseResult vr) {
		RepOrderIdEntity repCashEntity = new RepOrderIdEntity();
		repCashEntity.setRcode(ResponseResult.PARAM_ERROR.getCode());
		return repCashEntity;
	}

	@Override
	public AbstractEntity parseMessageBody(String reqjson) throws Exception {
		ReqPayOrderHolder holder = JsonUtil.json2Bean(reqjson, ReqPayOrderHolder.class);
		return holder.getBody();
	}

	@Override
	public ResponseResult validate(TransMessageEntity msg) {
		return validator.validate(msg);
	}

	public String queryReceiverByOrderid(String orderid) {
		OrderInfoKey oik = new OrderInfoKey();
		oik.setOrderid(orderid);
		OrderInfo oInfo = orderInfoService.selectByPrimaryKey(oik);
		if (null != oInfo) {
			return oInfo.getReceiverid();
		}
		return null;
	}

	public List<ReqGamesEntity> queryGamesByOrderid(String orderid) {
		ShopInfoExample sie = new ShopInfoExample();
		sie.createCriteria().andOrderidEqualTo(orderid);
		List<ShopInfo> selectByExample = shopInfoService.selectByExample(sie);
		List<ReqGamesEntity> list = new ArrayList<ReqGamesEntity>();
		if (null != selectByExample && selectByExample.size() > 0) {
			for (int i = 0; i < selectByExample.size(); i++) {
				ReqGamesEntity rge = new ReqGamesEntity();
				ShopInfo shopInfo = selectByExample.get(i);
				rge.setGamecode(shopInfo.getGamecode());
				rge.setGamenum(shopInfo.getGamenum());
				rge.setOrderamount(Long.parseLong(shopInfo.getOrderamount().toString()));
				list.add(rge);
			}
		}
		return list;
	}

	public static void main(String[] args) {
		double v = (100.00 - Double.valueOf(4)) / 100.00;
		double orderM = 480 / v;//订单原始金额
		double v1 = orderM * Double.valueOf(5) / 100.00;
		System.out.println(v1);
	}
	public void insertShop(List<ReqGamesEntity> gamess, String outTradeNo) {
		int size = gamess.size();
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				ShopInfo sf = new ShopInfo();
				ReqGamesEntity reqGamesEntity = gamess.get(i);
				sf.setUuid(IDGenerator.getInstance().generate());
				sf.setGamecode(reqGamesEntity.getGamecode());
				sf.setGamenum(reqGamesEntity.getGamenum());
				sf.setOrderamount(Integer.parseInt(reqGamesEntity.getOrderamount().toString()));
				sf.setOrderid(outTradeNo);
				shopInfoService.insert(sf);
			}
		}
	}

	// 初始化alipayClient
	public AlipayTradePrecreateResponse initAlipayClient(String outTradeNo, Double bb, String username, List<ReqGamesEntity> gamess) {
		String alipayurl = initParm.getStringDbp("alipay.server.url", "https://openapi.alipaydev.com/gateway.do");
		AlipayClient alipayClient = AlipayService.getAlipayClient2(alipayurl, initParm.getStringDbp("appid", "2016072400106431"),
				initParm.getStringDbp("private_key", ""), initParm.getStringDbp("alipay_public_key", ""));
		String notify = initParm.getStringDbp("alipay.notify", "http://192.168.21.212:8080/CashManage/scan.action?outTradeNo=" + outTradeNo);
		List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();
		int size = gamess.size();
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				ReqGamesEntity reqGamesEntity = gamess.get(i);
				GoodsDetail gDetail = GoodsDetail.newInstance(reqGamesEntity.getGamecode() + i,
						initParm.getGameNameByCode(reqGamesEntity.getGamecode()), reqGamesEntity.getOrderamount(),
						Integer.parseInt(reqGamesEntity.getGamenum().toString()));
				goodsDetailList.add(gDetail);
			}
		}
		// 创建扫码支付请求builder，设置请求参数
		AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder().setSubject("即开票社会网点" + username + "门店订票消费")
				.setTotalAmount(bb.toString()).setOutTradeNo(outTradeNo)
				// .setStoreId(alipayUserInfo.getAlipayUserId())
				// .setTimeoutExpress("5m")
				.setNotifyUrl(notify + outTradeNo)// 支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
				.setGoodsDetailList(goodsDetailList);// 商品明细列表，需填写购买商品详细信息，
		AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
		request.setNotifyUrl(builder.getNotifyUrl());
		request.setBizContent(builder.toJsonString());
		request.putOtherTextParam("app_auth_token", builder.getAppAuthToken());

		log.debug("通知地址：：" + request.getNotifyUrl());
		AlipayTradePrecreateResponse response = null;
		try {
			log.debug(alipayClient.toString());
			response = alipayClient.execute(request);
			log.info(response.getCode() + response.getSubMsg());
			log.debug(response.getMsg());
		} catch (Exception e) {
			log.debug("扫码付交易异常", e);
		}
		return response;
	}
}
