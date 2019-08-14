package com.joyveb.cashmanage.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.joyveb.cashmanage.action.Action_2006;
import com.joyveb.cashmanage.common.Constants;
import com.joyveb.cashmanage.entity.OrderInfo;
import com.joyveb.cashmanage.entity.ShopInfo;
import com.joyveb.cashmanage.entity.ShopInfoExample;
import com.joyveb.cashmanage.entity.StockManage;
import com.joyveb.cashmanage.entity.StockManageKey;
import com.joyveb.cashmanage.service.OrderInfoService;
import com.joyveb.cashmanage.service.ShopInfoService;
import com.joyveb.cashmanage.service.StockManageService;

import static com.joyveb.cashmanage.action.AbstractReqAction.getOrderMap;

@Slf4j
public class ScanNotifyServlet extends HttpServlet {

    private static final long serialVersionUID = -4813847264787342794L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        OrderInfoService orderInfoService = (OrderInfoService) ctx.getBean("orderInfoService");
        ShopInfoService shopInfoService = (ShopInfoService) ctx.getBean("shopInfoService");
        StockManageService stockManageService = (StockManageService) ctx.getBean("stockManageService");
        Action_2006 action_2006 = (Action_2006) ctx.getBean("action_2006");
        String outTradeNo = request.getParameter("outTradeNo");
        log.info("outTradeNo：" + outTradeNo);
        OrderInfo orderInfo = getOrderMap().get(outTradeNo);
        Map<String, OrderInfo> orderMap = getOrderMap();
        if (StringUtils.isNotBlank(outTradeNo) && null != orderMap.get(outTradeNo)) {
            OrderInfo oik = new OrderInfo();
            oik.setOrderid(outTradeNo);
            oik.setOrderstatus(Constants.ALREADYPAY);
            orderInfoService.updateByPrimaryKeySelective(oik);
            orderMap.remove(outTradeNo);
            //增加一级代销商佣金
            orderInfoService.saveCommision(orderInfo.getUserid(),orderInfo.getTotalamount(),orderInfo.getFreightprice());
            //减库存
            ShopInfoExample sie = new ShopInfoExample();
            sie.createCriteria().andOrderidEqualTo(outTradeNo);
            List<ShopInfo> shopList = shopInfoService.selectByExample(sie);
            synchronized (new Object()) {
                for (int i = 0; i < shopList.size(); i++) {
                    StockManageKey smk = new StockManageKey();
                    String gamecode = shopList.get(i).getGamecode();
                    smk.setGamecode(gamecode);
                    StockManage sms = stockManageService.selectByPrimaryKey(smk);
                    StockManage sm = new StockManage();
                    sm.setGamecode(gamecode);
                    sm.setUpdatetime(System.currentTimeMillis());
                    Long total = sms.getTotal();
                    sm.setTotal(total - shopList.get(i).getGamenum());
                    stockManageService.updateByPrimaryKey(sm);
                }
            }
            log.info("支付宝主动通知，订单号：" + outTradeNo + "已支付成功！");
        } else {
            log.info("订单号为空！");
        }
    }
}