package com.joyveb.cashmanage.common;

import java.util.ArrayList;
import java.util.List;

public enum B2bResponseResult {

	SUCCESS("001","成功"),
	SERVER_ERROR("901", "系统内部错误"),
	
	PARAM_ERROR("201","参数错误"),
	NOT_PRIZE_TIME("202", "非兑奖时间，正常时间为9-24点"),
	USER_LOCKED("203", "该用户频繁兑未中奖票，锁定"),
	SERVER_BUSY("204", "服务器繁忙"),
	PROCESSING("205", "该请求正在处理(对于相同的messageid，正在处理情况)"),
	IPS_ERROR("206", "IP地址未授权"),
	IPS_RETURNERROR("207", "该日期数据暂未生成，请稍候重试"),
	
	File_claim("302","大奖"),//超出终端兑奖限额，可以认为是中大奖 重试需要锁用户
	Not_a_winner("303","未中奖"),//未中奖情况 重试需要锁用户
	Previously_paid_by_other("304","已兑奖，重试需要锁用户"),//已兑奖，之前兑奖成功的终端机与本次不是同一台终端 重试需要锁用户
	Previously_paid_by_you("305","已兑奖，重试需要锁用户"),//已兑奖，之前兑奖成功的终端机与本次是同一台终端 重试需要锁用户
	Previously_claimed_by_other("306","Previously claimed by other"),//已兑大奖，之前兑奖成功的终端机与本次不是同一台终端 重试需要锁用户
	Previously_claimed_by_you("307","Previously claimed by you"),//已兑大奖，之前兑奖成功的终端机与本次是同一台终端 重试需要锁用户
	Winning_ticket_on_HOLD("308","Winning ticket on HOLD"),//有争议的中奖票 重试需要锁用户
	Game_closed("309","Game closed"),//批次已终结 重试需要锁用户
	Insufficient_privilege("310","Insufficient privilege"),//无兑奖权限，表示终端是否有兑奖权限 换个终端
	Invalid_attempt("312","Invalid attempt"),//GTECH系统内部未准备就绪，可稍候重新尝试
	Db_transaction_failure("313","Db transaction failure"),//GTECH系统内部未准备就绪，可稍候重新尝试)，非本地票可能也返回此错误，需要验证 重试需要锁用户
	Invalid_pack_status("315","Invalid pack status"),//无效的本状态  重试需要锁用户
	Record_not_found("316","Record not found"),//可能由于批结，系统已经删除此票数据，记录找不到 重试需要锁用户
	Cashing_limit_exceeded("319","Cashing limit exceeded"),//超出此终端机当天总兑奖限额 换个终端重试
	File_claim_at_lotter("322","File claim at lotter"),//大奖兑奖，先到新街口处兑奖，再去B2B兑奖，会出现此情况 重试需要锁用户
	Pack_not_confirmed("328","Pack not confirmed"),//本没有确认 重试需要锁用户
	Pack_not_activated("329","Pack not activated"),//本没有激活 重试需要锁用户
	Claim_at_Online_Terminal("333","Claim at Online Terminal"),//先去终端机兑奖、再去B2B兑奖，会出现此情况 重试需要锁用户
	Game_not_active("340","Game not active"),//游戏未激活 重试需要锁用户
	Invalid_attempt41("341","Invalid attempt"),//GTECH系统内部未准备就绪，可稍候重新尝试
	Not_Settled("343","Not Settled"),//此票未开售 重试需要锁用户
	WAGERCARDNOTREG("006","投注卡未注册"),	
	WAGERCARDPWDERROR("007","投注卡密码错误"),	
	UNKNOWGAMEID("014","未知彩种编号"),
	PERIODSALESPAUSE("015","未知彩种编号"),
	PERIODSALESCUTOFF("016","本期销售截止"),
	PERIODSALESCLOSE("017","本期销售关闭"),
	GAMESALESCLOSE("019","彩种销售关闭"),
	WAGERCARDNUMERROR("101","投注卡号码错误"),
	WAGERCARDREGED("102","投注卡已经注册"),
	CERTIFICATETYPEERROR("103","证件类型错误"),
	CERTIFICATENUMERROR("104","证件号码错误"),
	PHONENUMERROR("105","电话号码错误"),
	WAGERCARDUNREG("106","投注卡已经销户"),
	CERTIFICATENOTADULT("107","注册用户未成年"),
	DISABLEUSERERROR("108","用户销户错误"),
	WAGERCARDPWDFORMATERROR("109","投注卡密码格式错误"),	
	CHECKSUMERROR("802","checksum错误"),
	IPADDRERROR("803","IP地址未授权"),
	MERCHANTNUMERROR("804","商户ID未授权"),
	VERSIONERROR("805","协议版本错误"),
	UNKNOWREQUESTTYPE("806","未知请求类型"),
	UNKNOWKEY("807","消息KEY未知"),
	NOCURRPERIOD("808","当前期未开期"),
	MERCHANTKEYERROR("809","商户安全码错误"),
	INTERNALSERVERERROR("901","系统内部错误"),
	PERIODUNMATCH ("909","投注期号不匹配"),
	PRODUCTUNMATCH("911","游戏名称不匹配"),
	SESSIONIDUNMATCH("912","SessionID不匹配"),
	SEQUENCENUMBERUNMATCH("913","sequenceNumber不匹配"),
	TIMEUNMATCH("914","time不匹配"),
	GETSESSIONIDERROR("908","获取sessionId失败"),
	CONNECTB2BERROR("907","连接B2b失败"),
	UNKNOWCODE("999","未知错误代码");
	
	protected static final List<String> list = new ArrayList<String>();
	
	static {
		list.add(B2bResponseResult.IPS_ERROR.getCode());
		list.add(B2bResponseResult.IPS_RETURNERROR.getCode());
		list.add(B2bResponseResult.UNKNOWCODE.getCode());
		list.add(B2bResponseResult.CONNECTB2BERROR.getCode());
		list.add(B2bResponseResult.TIMEUNMATCH.getCode());
		list.add(B2bResponseResult.GETSESSIONIDERROR.getCode());
		list.add(B2bResponseResult.SEQUENCENUMBERUNMATCH.getCode());
		list.add(B2bResponseResult.SESSIONIDUNMATCH.getCode());
		list.add(B2bResponseResult.PRODUCTUNMATCH.getCode());
		list.add(B2bResponseResult.PERIODUNMATCH.getCode());
		list.add(B2bResponseResult.MERCHANTKEYERROR.getCode());
		list.add(B2bResponseResult.UNKNOWKEY.getCode());
		list.add(B2bResponseResult.UNKNOWREQUESTTYPE.getCode());
		list.add(B2bResponseResult.VERSIONERROR.getCode());
		list.add(B2bResponseResult.MERCHANTNUMERROR.getCode());
		list.add(B2bResponseResult.IPADDRERROR.getCode());
		list.add(B2bResponseResult.CHECKSUMERROR.getCode());
		list.add(B2bResponseResult.WAGERCARDPWDFORMATERROR.getCode());
		list.add(B2bResponseResult.DISABLEUSERERROR.getCode());
	}
	private String code;
	private String desc;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	private B2bResponseResult(String code,String desc){
		this.code=code;
		this.desc=desc;
	}
	public static B2bResponseResult getById(String code){
		for(B2bResponseResult result:B2bResponseResult.values()){
			if(result.getCode().equals(code)){
				return result;
			}
		}
		return null;
	}
	public static String getDesById(String code){
		for(B2bResponseResult result:B2bResponseResult.values()){
			if(result.getCode().equals(code)){
				return result.getDesc();
			}
		}
		return null;
	}
}
