package com.joyveb.cashmanage.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {

	/**
	 * 商户编号及商户Key
	 */
	public static final String MERCHANT_ID = "666666";
	public static final String MERCHANT_ID_KEY = "0123456789666666";
	
	public static final String SUCCESS = "10000"; // 成功
    public static final String PAYING = "10003";  // 用户支付中
    public static final String FAILED = "40004";  // 失败
    public static final String ERROR = "20000"; // 系统异常
	
	//1001请求的命令码
	public static final String COMMAND_1001="1001";
	//1002请求的命令码
	public static final String COMMAND_1002="1002";
	//1003请求的命令码
	public static final String COMMAND_1003="1003";
	//1004请求的命令码
	public static final String COMMAND_1004="1004";
	//2001请求的命令码
	public static final String COMMAND_2001="2001";
	//2002请求的命令码
	public static final String COMMAND_2002="2002";
	//2003请求的命令码
	public static final String COMMAND_2003="2003";
	//2004请求的命令码
	public static final String COMMAND_2004="2004";
	//2005请求的命令码
	public static final String COMMAND_2005="2005";
	//2006请求的命令码
	public static final String COMMAND_2006="2006";
	//2007请求的命令码
	public static final String COMMAND_2007="2007";
	//2008请求的命令码
	public static final String COMMAND_2008="2008";
	//2009请求的命令码
	public static final String COMMAND_2009="2009";
	//2010
	public static final String COMMAND_2010="2010";
	// 1支付宝（彩票代销商）、2微信（彩票购买者）
	public final static String USER_TYPE_ALIPAY = "1";
	public final static String USER_TYPE_WX = "2";
	
	public static final String B2B_VERSION = "1.0.0.0";
	//待支付
	public static final String WAITPAY = "1"; 
	//已支付
	public static final String ALREADYPAY = "2";
	//支付失败
	public static final String PAYFAIL = "3";
	//待发货
	public static final String WAITSEND = "4";
	//已发货
	public static final String ALREADYSEND = "5";
	//已签收
	public static final String ALREADYSIGN = "6";
	//B2B 1009请求的命令码
	public static final String B2B_COMMAND_1009="1009";
	//B2B 1010请求的命令码
	public static final String B2B_COMMAND_1010="1010";
	//支付宝标识
	public static final String ALIPAYFLAG = "1";
	//微信支付标识
	public static final String WECHARTFLAG = "2";
	//银行支付标识
	public static final String BANKFLAG = "3";
	//用户状态无效
	public static final int USER_STATUS_INVALID = 1;
	//该用户不存在
	public static final int USER_NOT_EXIST = 2;
	//密码错误
	public static final int PASSWORD_ERROR = 3;
	
	public final static String QUERIES_TYPE_INTIME = "1"; 
	public final static String QUERIES_TYPE_B2C = "2"; 
	/**
	 * 大奖
	 */
	public final static int BIGAWARD = 1;

	public static Map<String, String> resultMap = new HashMap<String, String>();
	public static List<String> commandList = new ArrayList<String>();
	static {
		commandList.add(COMMAND_1001);
		commandList.add(COMMAND_1002);
		commandList.add(COMMAND_1003);
		commandList.add(COMMAND_1004);
		commandList.add(COMMAND_2001);
		commandList.add(COMMAND_2002);
		commandList.add(COMMAND_2003);
		commandList.add(COMMAND_2004);
		commandList.add(COMMAND_2005);
		commandList.add(COMMAND_2006);
		commandList.add(COMMAND_2007);
		commandList.add(COMMAND_2008);
		commandList.add(COMMAND_2009);
		commandList.add(COMMAND_2010);
		resultMap.put(B2bResponseResult.USER_LOCKED.getCode(), ResponseResult.USER_LOCKED.getCode());
		resultMap.put(B2bResponseResult.SERVER_BUSY.getCode(), ResponseResult.SERVER_BUSY.getCode());
		resultMap.put(B2bResponseResult.Not_a_winner.getCode(), ResponseResult.NOT_A_WINNER.getCode());
	}
	
	/*// b2b 兑奖transaction
	public static final int B2B_TRANSACTION_SUCCESS = 0;
	public static final int B2B_TRANSACTION_FAIL = 1;

	public static final String LITTLE_SESSION_TYPE = "1";
	public static final String BIG_SESSION_TYPE = "2";

	// 返奖状态
	public static final int NO_RETURN_PRIZE = 0;
	public static final int RETURN_PRIZE_SUCCESS = 1;
	public static final int RETURN_PRIZE_FAIL = 2;
*/
	// 中奖状态
	public static final short WINSTATE_LITTLE_PRIZE = 0;
	public static final short WINSTATE_BIG_PRIZE = 1;
	public static final short WINSTATE_NOT_WINNER = 2;
	public static final short WINSTATE_NOTSUCCESS = 3;
	public static final short WINSTATE_ALREADPAID = 4;

}
