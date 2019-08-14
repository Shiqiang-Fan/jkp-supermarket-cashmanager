package com.joyveb.cashmanage.alipay;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * 关于银行交易的实体封装
 *
 */
@Data
public class TransactionRecord {
	
	Map<String, Object> extendsParm = new HashMap<String,Object>();
	
	//具体实现接口的人员更具实际接口情况增减字段
    private String id;
    private String playerid;
    private String bankcard;
    private Long dealtime;
    private Long processeddate;
    private Long amount;
    private Integer type;
    private Integer status;
    
    //支付宝参数
    private String filePath;//批量到银行卡 上传文件
    private String fileName;//批量到银行卡 上传文件
    private String bankName;//银行名称
    private String cardHolder;//银行持卡人、第三方买家
    private String receiverNum;//收款方账户
    private String alipayrecord;//第三方返回信息
    private String outBizNo;//商户订单号
    private String orderNo;//第三方交易号    
    private String receiverType;//收款方账户类型
    private String tradeStatus;//第三方原始交易状态
    private String queryType;//查询类型 第三方查询类型(1 即时到账查询,2 B2C 查询  3 银行查询)
    private String errormsg;//第三方失败描述
    private String returnurl;//第三方返回地址
    
}