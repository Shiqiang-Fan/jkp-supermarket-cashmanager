package com.joyveb.cashmanage.alipay.service;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserUserinfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserUserinfoShareResponse;
import com.joyveb.cashmanage.alipay.AbstractAlipay;
//import com.joyveb.cashmanage.alipay.AlipayServiceEnum;
import com.joyveb.cashmanage.alipay.AlipaySubmit;
import com.joyveb.cashmanage.alipay.AlipayUserInfo;
import com.joyveb.cashmanage.alipay.PackageBinder;
import com.joyveb.cashmanage.alipay.TransactionRecord;
import com.joyveb.cashmanage.web.InitParm;

@Slf4j
@Component("alipayService")
public class AlipayService {
	private @Resource(name="alipaySingleQuery") AbstractAlipay alipaySingleQuery;
	private @Resource(name="b2CTransQuery") AbstractAlipay b2CTransQuery;
	@Resource(name="initParm")
	private InitParm initParm;
	//支付宝网关地址
	private final String serverUrl = "https://openapi.alipay.com/gateway.do";
	//应用ID
	private final String appId = "2015051300073240";
	//返回结果格式：xml、json;
	private final String format = "json";
	//商户RSA私钥
	private final String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALKzxQ+m0N4VVOcD"
			+ "Mi6cEkxtVg5luMGBacy8xyMVHcqA5o0xKLBiq3vKWIOnuy5rXXyAv6DyGRud3shl"
			+ "YCXD+GiI4sm8lwyCDib6a7rYYsxcVoph+UcV+JQbX1ZVL4syVzI+M7Tt0LDYLqtK"
			+ "UzzVbq6p7dbdyscj25oH/TPjuD/DAgMBAAECgYBmh+KPAmVgptiZ2ymVuJJ/K1Ru"
			+ "2kpRLFFp2P5g0KLRJ3gglo4D9fjyA70ZfMlW7t8qqixIyd9CCyhhzliGEpSZYB45"
			+ "Z871aAoikLu3shgIy/hERpr4vdsytybpJPpo9MWWrKpHNiqXWoMGhnXRo7R7Wh7n"
			+ "3gQx76uwU6HeNYpDwQJBAN6UOFrD4+Nk2tTHmmbFTEV6d6ID6cbQhAmFEOq7YXOK"
			+ "4By/9yNw5lXvF2v5e7piCQQYOMok2KJHElVqMti1h3ECQQDNiPUz9nGfoZYlTH1E"
			+ "c6o+zXmS/SvLckuAoyn60RhBAc81T9KJ6n1rXtHobKA2QQIVsMkVmEnYX36ev2Fz"
			+ "TuhzAkEArI5MglGLwybVYFC+qx4aW2LujHRsMO5G7vpyewh6LJFl3eQVPSyx5b1O"
			+ "cy8fdQe75ajBnjWinWxsEjwa31ccgQJATs8J/h+r16KUuL6IdbvH9obA6/yJU1wC"
			+ "2VM24IZWP27bLbzqpJk19/qGkBE+qS7qBrkfkVkwT3fmTCZjzlveQwJAS56UxfKA"
			+ "B1xuxTRnOTlxeaKpdtVTXbiDukcZ//rfRWVNgG3zEzyqyrveva4EEwRuCsmo0tZQ"
			+ "nTZixlb87vzeYA==";
	
	//字符集格式
	private final String charset = "utf-8";
	
	public String getAppId() {
		return appId;
	}

	//默认值
	private final String prodCode ="WAP_FAST_LOGIN";
	
	public AlipayClient getAlipayClient(){
		AlipayClient client = new DefaultAlipayClient(serverUrl, appId, privateKey,format, charset);
		return client;
	}
	public static AlipayClient getAlipayClient2(String alipayurl,String appid,String private_key,String alipay_public_key){
		AlipayClient client = new DefaultAlipayClient(alipayurl,appid,private_key,"json","utf-8",alipay_public_key);;
		return client;
	}
	
	@SuppressWarnings("finally")
	private String getQueryToken(String authCode) {
		//请求对象
		AlipaySystemOauthTokenRequest req = new AlipaySystemOauthTokenRequest();
		req.setCode(authCode);
		//GrantType传固定值authorization_code
		req.setGrantType("authorization_code");
		//返回结果对象
		AlipaySystemOauthTokenResponse res = null;
		try {
			res = getAlipayClient().execute(req);
		} catch (AlipayApiException e) {
			log.warn("get token error ", e.getMessage(), e);
		}
		if(null!=res){
			return res.getAccessToken();
		}else{
			return null;
		}
	}
	
	private AlipayUserInfo getUserInfo(String access_token) throws AlipayApiException{
		AlipayUserInfo alipayUserInfo = null;
		//请求对象
		AlipayUserUserinfoShareRequest req = new AlipayUserUserinfoShareRequest();
		req.setProdCode(prodCode);
		//返回结果对象
		AlipayUserUserinfoShareResponse res = getAlipayClient().execute(req, access_token);
		if(res != null){
    		log.debug("详细地址-address:" + res.getAddress());
    		log.debug("区域编码，暂时不返回值-addressCode:" + res.getAddressCode());
    		log.debug("支付宝用户ID-alipayUserId:" + res.getAlipayUserId());
    		log.debug("区县名称-area:" + res.getArea());
    		log.debug("用户头像-avatar:" + res.getAvatar());
    		log.debug("body:" + res.getBody());
    		log.debug("证件号码-cereNO:" + res.getCertNo());
    		log.debug("证件类型-certTypeValue:" + res.getCertTypeValue());
    		log.debug("市名称-city:" + res.getCity());
    		log.debug("用户支付宝账号绑定的邮箱地址-email:" + res.getEmail());
    		log.debug("公司名称（用户类型是公司类型时公司名称才有此字段）-firmName:" + res.getFirmName());
    		log.debug("性别（F：女性；M：男性）-gender:" + res.getGender());
    		log.debug("T为是银行卡认证，F为非银行卡认证-isBankAuth:" + res.getIsBankAuth());
    		log.debug("T：表示A类实名认证；F：表示非A类实名认证-isCertified:" + res.getIsCertified());
    		log.debug("T为是身份证认证，F为非身份证认证-isIdAuth:" + res.getIsIdAuth());
    		log.debug("T为通过营业执照认证，F为没有通过-isLicenceAuth:" + res.getIsLicenceAuth());
    		log.debug("T为是手机认证，F为非手机认证-isMobileAuth:" + res.getIsMobileAuth());
    		log.debug("是否是学生-isStudentCertified:" + res.getIsStudentCertified());
    		log.debug("手机号码-mobile:" + res.getMobile());
    		log.debug("电话号码-phone:" + res.getPhone());
    		log.debug("省份名称-province:" + res.getProvince());
    		log.debug("用户的真实姓名-realName:" + res.getRealName());
    		log.debug("用户的userId-userId:" + res.getUserId());
    		log.debug("用户状态（Q/T/B/W）。Q代表快速注册用户T代表已认证用户B代表被冻结账户W代表已注册，未激活的账户-userStatus:" + res.getUserStatus());
    		log.debug("用户类型（1/2）1代表公司账户2代表个人账户-userTypeValue:" + res.getUserTypeValue());
    		log.debug("邮政编码-zip:" + res.getZip());

    		alipayUserInfo = new AlipayUserInfo();
    		alipayUserInfo.setAlipayUserId(res.getAlipayUserId());
    		alipayUserInfo.setEmail(res.getEmail());
    		alipayUserInfo.setMobile(res.getMobile());
    		alipayUserInfo.setRealName(res.getRealName());
    		alipayUserInfo.setCertNo(res.getCertNo());
    		alipayUserInfo.setIsCertified(res.getIsCertified());
		}
		return alipayUserInfo;
	}
	
	/**
	 * 根据授权码获取支付宝信息
	 * @param auth_code
	 * @throws Exception 
	 */
	public AlipayUserInfo queryUserInfo(String auth_code) throws AlipayApiException{
		log.debug("bean");
		String accessToken = this.getQueryToken(auth_code);
		log.debug("accessToken::"+accessToken);
		if(StringUtils.isNotBlank(accessToken)){
			return this.getUserInfo(accessToken);
		}
		
		return null;
	}
	
	/**
	 * 转账到支付宝
	 * @param
	 * @throws Exception 
	 */
	public TransactionRecord transToAcc(String alipayUserId, String amount,String orderId) throws Exception{
		//合作者身份ID
		String tran = initParm.getStringDbp("alipay.tran.id", "2088401373010247");
		//商户私钥（转账）
		String key = initParm.getStringDbp("alipay.tran.key", "azaku8mw81iwxq76rgm3zd26i9toqjtm");
		TransactionRecord transactionRecord = AlipaySubmit.alipayFundTransToAcc(alipayUserId, amount,orderId,tran,key);
		// 1转账成功，2转账失败
		return transactionRecord;
	}

	public TransactionRecord queryState(TransactionRecord transaction) {
		
		if("1".equals(transaction.getQueryType())){
			String record = alipaySingleQuery.action(transaction);//, AlipayServiceEnum.SINGLE_TRADE_QUERY_SERVICE
			transaction.setAlipayrecord(record);
			//log.debug("alipay querystate response:[{}]",record);
			PackageBinder.bindImmediatelyRecord(transaction);
		}else{
			String record = b2CTransQuery.action(transaction);//, AlipayServiceEnum.B2C_ALIPAY_TRANS_QUERY
			transaction.setAlipayrecord(record);
			//log.debug("alipay querystate response:[{}]",record);
			PackageBinder.bindQueryRecord(transaction);
		}
		
		
		return transaction;
	}
	
	public static void main(String[] args) {
		AlipayService userInfo = new AlipayService();
		try {
			//我的
			String access_token = "kuaijieB50ec6f38183b4d0ba55ed30e7c04dX73";
			/*//公司账号
			access_token = "kuaijieBf310f21b3dc44b9c8310d2ed974e5X24";
			//于瑞斌
			access_token = "kuaijieBd6cfe36bb2874c76a85cb7de33b6dD55";*/
			TransactionRecord transactionRecord = null;
			System.out.println(null==transactionRecord);
			/*String auth_code = "";
			String accessToken = userInfo.getQueryToken(auth_code);
			log.debug("accessToken:" + accessToken);
			accessToken = access_token;
			userInfo.getUserInfo(accessToken);*/
		
		} catch (Exception e) {
			log.warn("AlipayApi error",e);
		}
	}
	
}