package com.joyveb.cashmanage.timertask;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.joyveb.cashmanage.action.PayFactory;
import com.joyveb.cashmanage.alipay.AlipayConfig;
import com.joyveb.cashmanage.alipay.TransactionRecord;
import com.joyveb.cashmanage.alipay.service.AlipayService;
import com.joyveb.cashmanage.common.Constants;
import com.joyveb.cashmanage.ctrl.TransferRecordCtrl.TransferRecords;
import com.joyveb.cashmanage.entity.OrderInfo;
import com.joyveb.cashmanage.entity.Prizer;
import com.joyveb.cashmanage.entity.PrizerKey;
import com.joyveb.cashmanage.entity.ShopInfo;
import com.joyveb.cashmanage.entity.ShopInfoExample;
import com.joyveb.cashmanage.entity.StockManage;
import com.joyveb.cashmanage.entity.StockManageKey;
import com.joyveb.cashmanage.entity.TransferRecord;
import com.joyveb.cashmanage.entity.TransferRecordExample;
import com.joyveb.cashmanage.entity.WhiteList;
import com.joyveb.cashmanage.service.OrderInfoService;
import com.joyveb.cashmanage.service.PrizerService;
import com.joyveb.cashmanage.service.ShopInfoService;
import com.joyveb.cashmanage.service.StockManageService;
import com.joyveb.cashmanage.service.TransferRecordService;
import com.joyveb.cashmanage.utils.IDGenerator;
import com.joyveb.cashmanage.web.InitParm;

import static java.lang.System.currentTimeMillis;

/**
 * 返奖
 */
@Slf4j
@Component("returnAward")
public class ReturnAward {
    @Resource(name = "initParm")
    private InitParm initParm;
    @Resource(name = "prizerService")
    private PrizerService prizerService;
    @Resource(name = "payFactory")
    private PayFactory payFactory;
    @Resource(name = "transferRecordService")
    private TransferRecordService transferRecordService;
    @Resource(name = "orderInfoService")
    private OrderInfoService orderInfoService;
    @Resource(name = "stockManageService")
    private StockManageService stockManageService;
    @Resource(name = "shopInfoService")
    private ShopInfoService shopInfoService;

    public void queryPayStatus(OrderInfo orderInfo, Map<String, OrderInfo> orderMap) {
        String orderid = orderInfo.getOrderid();
        OrderInfo orderInfo1 = orderMap.get(orderid);
        String alipayurl = initParm.getStringDbp("alipay.server.url", "https://openapi.alipaydev.com/gateway.do");
        AlipayClient alipayClient = AlipayService.getAlipayClient2(alipayurl, initParm.getStringDbp("appid", "2016072400106431"),
                initParm.getStringDbp("private_key", ""), initParm.getStringDbp("alipay_public_key", ""));
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{" + "    \"out_trade_no\":\"" + orderid + "\"" + "  }");
        try {
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            String tradeStatus = response.getTradeStatus();
            log.debug("轮询查询支付宝支付状态：" + response.getMsg() + " 订单号： " + orderid);
            // 如果支付成功了 从队列中删除该笔订单
            if (tradeStatus != null && tradeStatus.equals(AlipayConfig.TRADE_SUCCESS) && null != orderInfo1) {
                OrderInfo orderInfo2 = new OrderInfo();
                orderInfo2.setOrderstatus(Constants.ALREADYPAY);
                orderInfo2.setOrderid(orderid);
                orderInfoService.updateByPrimaryKeySelective(orderInfo2);
                //增加一级代销商佣金
                orderInfoService.saveCommision(orderInfo.getUserid(),orderInfo.getTotalamount(),orderInfo.getFreightprice());
                orderMap.remove(orderid);
                ShopInfoExample sie = new ShopInfoExample();
                sie.createCriteria().andOrderidEqualTo(orderid);
                List<ShopInfo> shopList = shopInfoService.selectByExample(sie);
                synchronized (new Object()) {
                    for (int i = 0; i < shopList.size(); i++) {
                        StockManageKey smk = new StockManageKey();
                        String gamecode = shopList.get(i).getGamecode();
                        smk.setGamecode(gamecode);
                        StockManage sms = stockManageService.selectByPrimaryKey(smk);
                        StockManage sm = new StockManage();
                        sm.setGamecode(gamecode);
                        sm.setUpdatetime(currentTimeMillis());
                        Long total = sms.getTotal();
                        sm.setTotal(total - shopList.get(i).getGamenum());
                        stockManageService.updateByPrimaryKey(sm);
                    }
                }
            }
            // 如果扫码后迟迟不支付 限定时间到了 就取消这笔订单
            if (tradeStatus == null || tradeStatus.equals(AlipayConfig.WAIT_BUYER_PAY)) {
                // 当前时间减去支付时间后的分钟 如果大于超时时间 就说明支付超时 需调用撤销接口
                long currentTimeMillis = System.currentTimeMillis();
                long dd = (currentTimeMillis - orderInfo.getPaytime()) / (1000 * 60);// 加三秒 终端二维码3分钟倒计时，后台三分钟加三秒后撤销订单
                int intDbp = initParm.getIntDbp("cancel.time", 20);
                log.info("当前时间减去下单时间分钟数：" + dd);
                log.info("数据库分钟数" + intDbp);
                if (dd >= intDbp) {
                    AlipayTradeCancelRequest cancelRequest = new AlipayTradeCancelRequest();
                    cancelRequest.setBizContent("{" + "    \"out_trade_no\":\"" + orderid + "\"" + "  }");
                    AlipayTradeCancelResponse cancelResponse = alipayClient.execute(cancelRequest);
                    if (null != cancelResponse && cancelResponse.getCode().equals(Constants.SUCCESS)) {
                        OrderInfo oInfo = new OrderInfo();
                        oInfo.setOrderid(orderid);
                        oInfo.setOrderstatus(Constants.PAYFAIL);
                        oInfo.setFailreason("该订单已撤销！");
                        log.info("该订单已撤销！" + orderid);
                        orderInfoService.updateByPrimaryKeySelective(oInfo);
                        orderMap.remove(orderid);
                    } else {
                        AlipayTradeCancelResponse execute = alipayClient.execute(cancelRequest);
                        log.info(orderid + "第一次撤销失败重试，撤销结果：" + execute.getAction() + "  " + execute.getCode());
                    }
                }
            }
        } catch (AlipayApiException e) {
            log.warn("扫码付查询订单错误", e);
        }
    }

    // 查询转账记录表中处理中和失败数据 然后重新转账
    public void transferTrans() {
        AlipayService alipayService = (AlipayService) payFactory.getPayService(Constants.ALIPAYFLAG);
        TransferRecordExample tfeExample = new TransferRecordExample();
        List<String> sList = new ArrayList<String>();
        sList.add(AlipayConfig.TRANSACTIONDEALIND);
        sList.add(AlipayConfig.TRANSACTIONFAIL);
        tfeExample.createCriteria().andStatusIn(sList).andTransfertypeEqualTo(Constants.ALIPAYFLAG);
        List<TransferRecord> tfeList = transferRecordService.selectByExample(tfeExample);
        int size = tfeList.size();
        if (null != tfeList && size > 0) {
            for (int y = 0; y < size; y++) {
                TransferRecord transferRecord = tfeList.get(y);
                TransactionRecord tr = new TransactionRecord();
                transferRecord.setTransfertime(currentTimeMillis());
                String outtradenum = transferRecord.getOuttradenum();
                tr.setOutBizNo(outtradenum);
                tr.setQueryType(Constants.QUERIES_TYPE_B2C);
                TransactionRecord queryState = alipayService.queryState(tr);
                // log.debug("连接第三方支付,第三方返回:::{}",queryState.getAlipayrecord());
                if (queryState.getStatus() != null && queryState.getStatus() == 1) {// 已经转账
                    log.debug("查询支付宝已存在交易 该 " + transferRecord.getTradenum() + " 已经转账");
                    updateTrans(transferRecord, AlipayConfig.TRANSACTIONSUCC, "");
                } else {
                    try {
                        TransactionRecord transToAcc = alipayService.transToAcc(transferRecord.getTransferin(), transferRecord.getTransferamount()
                                .toString(), outtradenum);
                        if (null == transToAcc) {
                            updateTrans(transferRecord, AlipayConfig.TRANSACTIONFAIL, "支付宝配置错误");
                        } else {
                            String alipayrecord = transToAcc.getAlipayrecord();
                            SAXReader reader = new SAXReader();
                            Document document = reader.read(new ByteArrayInputStream(alipayrecord.getBytes("UTF-8")));
                            Element root = document.getRootElement();
                            String order_id = root.element("response").element("alipay").elementText("order_id");
                            if (transToAcc.getStatus() == 1) {// 如果转账成功 更新prizer表
                                Prizer prizer = new Prizer();
                                prizer.setTransferstatus(AlipayConfig.TRANSACTIONSUCC);
                                prizer.setTransfertime(System.currentTimeMillis());
                                if (transferRecord.getMessageids().contains(",")) {//多个messageid
                                    String[] mids = transferRecord.getMessageids().split(",");
                                    for (int a = 0; a < mids.length; a++) {
                                        List<HashMap<String, Object>> pri = selectMid(mids[a]);
                                        if (null != pri && pri.size() != 0) {
                                            prizer.setMessageid(pri.get(0).get("MESSAGEID").toString());
                                            prizerService.updateByPrimaryKeySelective(prizer);
                                        }
                                    }
                                } else {
                                    prizer.setMessageid(transferRecord.getMessageids());
                                    prizerService.updateByPrimaryKeySelective(prizer);
                                }
                                log.debug("查询支付宝未转账后 该订单" + order_id + " 已重新转账");
                                transferRecord.setTradenum(order_id);
                                updateTrans(transferRecord, AlipayConfig.TRANSACTIONSUCC, "");
                            } else {
                                String error_msg = root.element("response").element("alipay").elementText("error_msg");
                                String out_biz_no = root.element("response").element("alipay").elementText("out_biz_no");
                                log.debug("转账再次错误：" + error_msg);
                                transferRecord.setTradenum(out_biz_no);
                                updateTrans(transferRecord, AlipayConfig.TRANSACTIONFAIL, error_msg);
                            }
                        }
                    } catch (Exception e) {
                        log.warn("支付宝转账错误：：" + e);
                    }
                }
            }
        }
    }

    // 正常转账流程 查询prizer表中 已兑奖未转账的记录 转账
    public void transferPrizer(List<WhiteList> list, Integer flag) {
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
        AlipayService alipayService = (AlipayService) payFactory.getPayService(Constants.ALIPAYFLAG);
        TransferRecord transferRecord = new TransferRecord();
        if (null != list && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                if (flag == 1) {
                    returnPrizer(list, sim, ymd, i, alipayService, transferRecord, flag);
                } else if (flag == 0) {
                    WhiteList whiteList = list.get(i);
                    Integer transfercycle = whiteList.getTransfercycle();
                    long sysc = System.currentTimeMillis();// 计算返奖周期 根据当前时间-设备领用时间 +1 除以转账周期 取余=0就是返奖日
                    long useTime = whiteList.getUsetime();
                    long su = (sysc - useTime);
                    long cu = su / (24 * 60 * 60 * 1000) + 1;
                    Long valueOf = Long.valueOf(transfercycle);
                    if (cu % valueOf == 0) {
                        returnPrizer(list, sim, ymd, i, alipayService, transferRecord, flag);
                    }
                }
            }
        } else {
            log.debug("无转账用户！");
        }
    }

    //计算日期返奖
    public void returnPrizer(List<WhiteList> list, SimpleDateFormat sim, SimpleDateFormat ymd, int i, AlipayService alipayService,
                             TransferRecord transferRecord, Integer flag) {
        double money = 0;
        String tsql = "";
        List<TransferRecord> selectAllMids = null;
        List<String> midlist = new ArrayList<String>();
        if (null != list && StringUtils.isNotBlank(list.get(i).getUsername())) {
            if (flag == 1) {//1表示系统重启 查询所有 未返奖记录
                selectAllMids = selectAllMids();
                for (int k = 0; k < selectAllMids.size(); k++) {
                    String messageids = selectAllMids.get(k).getMessageids();
                    midlist.add(messageids);
                }
                tsql = "select * from t_core_prizer where amountsign = 0 and transferstatus = 2 and userid = " + list.get(i).getUsername();
            } else {
                tsql = "select * from t_core_prizer where amountsign = 0 and transferstatus = 2 and userid = " + list.get(i).getUsername()
                        + " AND REQUESTTIME > UNIX_TIMESTAMP(date_sub(curdate(), INTERVAL " + list.get(i).getTransfercycle()
                        + " DAY))*1000  and REQUESTTIME < UNIX_TIMESTAMP(CURDATE())*1000";
            }
            List<HashMap<String, Object>> prizers = prizerService.dosql(tsql);
            String messageId = "";
            if (null != prizers && list.size() != 0) {
                int size2 = prizers.size();
                for (int y = 0; y < size2; y++) {
                    String mid = prizers.get(y).get("MESSAGEID").toString();
                    String subMid = mid.substring(17, mid.length());
                    if (null != selectAllMids && selectAllMids.size() != 0) {//只有系统重启时才会判断所有messageid是否已处理
                        if (midlist.contains(mid)) {
                            log.info("该记录已处理 MessageID：" + mid);
                        } else {
                            money += Double.parseDouble(prizers.get(y).get("WINAMOUNT").toString());
                            messageId += subMid + ",";
                        }
                    } else {
                        money += Double.parseDouble(prizers.get(y).get("WINAMOUNT").toString());
                        messageId += subMid + ",";
                    }
                }
                try {
                    money = money / 100;
                    if (!String.valueOf(money).equals("0.0")) {
                        String generate = IDGenerator.getInstance().generate();
                        transferRecord.setOuttradenum(generate);
                        transferRecord.setFactorage(BigDecimal.valueOf(0.5));
                        transferRecord.setMessageids(messageId);
                        transferRecord.setTransfertype("1");// 1代表支付宝
                        transferRecord.setTransferout(alipayService.getAppId());
                        transferRecord.setTransferin(list.get(i).getAlipaynum());
                        transferRecord.setTransferamount(BigDecimal.valueOf(money));
                        transferRecord.setStatus(AlipayConfig.TRANSACTIONDEALIND);// 处理中
                        transferRecord.setTransfertime(System.currentTimeMillis());
                        transferRecordService.insert(transferRecord);
                        TransactionRecord transToAcc = alipayService.transToAcc(list.get(i).getAlipaynum(), String.valueOf(money), generate);
                        if (null == transToAcc) {
                            updateTrans(transferRecord, AlipayConfig.TRANSACTIONFAIL, "支付宝配置错误");
                        } else {
                            Integer status = transToAcc.getStatus();
                            SAXReader reader = new SAXReader();
                            Document document = reader.read(new ByteArrayInputStream(transToAcc.getAlipayrecord().getBytes("UTF-8")));
                            Element root = document.getRootElement();
                            String order_id = root.element("response").element("alipay").elementText("order_id");
                            transferRecord.setTradenum(order_id);
                            if (status == 1) {// 1代表转账成功 然后 更新兑奖记录表转账状态为1成功 并向转账记录表增加一条数据
                                for (int j = 0; j < prizers.size(); j++) {
                                    PrizerKey pk = new PrizerKey();
                                    pk.setMessageid(prizers.get(j).get("MESSAGEID").toString());
                                    Prizer selectByPrimaryKey = prizerService.selectByPrimaryKey(pk);
                                    selectByPrimaryKey.setTransferstatus(AlipayConfig.TRANSACTIONSUCC);
                                    selectByPrimaryKey.setTransfertime(System.currentTimeMillis());
                                    prizerService.updateByPrimaryKey(selectByPrimaryKey);
                                }
                                updateTrans(transferRecord, AlipayConfig.TRANSACTIONSUCC, "");
                                log.info("支付宝转账成功，金额：" + money);
                            } else {// 代表转账失败
                                updateTrans(transferRecord, AlipayConfig.TRANSACTIONFAIL, transToAcc.getErrormsg());
                            }
                        }
                    }
                } catch (Exception e) {
                    log.warn("支付宝转账错误！", e);
                }
            } else {
                log.info("无可转账记录！");
            }

        }
    }

    public List<HashMap<String, Object>> selectMid(String mid) {
        List<HashMap<String, Object>> pri = prizerService.dosql("select MESSAGEID from t_core_prizer where MESSAGEID like '%" + mid + "'");
        return pri;
    }

    public List<TransferRecord> selectAllMids() {
        TransferRecords trs = new TransferRecords();
        List<TransferRecord> findAll = transferRecordService.findAll(trs);
        return findAll;
    }

    public void updateTrans(TransferRecord transferRecord, String status, String des) {
        transferRecord.setTransfertype(Constants.USER_TYPE_ALIPAY);
        transferRecord.setStatus(status);// 转账失败
        transferRecord.setFailreason(des);
        transferRecordService.updateByPrimaryKey(transferRecord);
    }
}
