package com.joyveb.cashmanage.common;

import java.util.ArrayList;
import java.util.List;


public enum B2bResultCode {

	PAY_THE_WINNER(1,"001","Pay the winner"),//兑奖成功
	File_claim(2,"302","File claim"),//超出终端兑奖限额，可以认为是中大奖 重试需要锁用户
	Not_a_winner(3,"303","Not a winner"),//未中奖情况 重试需要锁用户
	Previously_paid_by_other(4,"304","Previously paid by other"),//已兑奖，之前兑奖成功的终端机与本次不是同一台终端 重试需要锁用户
	Previously_paid_by_you(5,"305","Previously paid by you"),//已兑奖，之前兑奖成功的终端机与本次是同一台终端 重试需要锁用户
	Previously_claimed_by_other(6,"306","Previously claimed by other"),//已兑大奖，之前兑奖成功的终端机与本次不是同一台终端 重试需要锁用户
	Previously_claimed_by_you(7,"307","Previously claimed by you"),//已兑大奖，之前兑奖成功的终端机与本次是同一台终端 重试需要锁用户
	Winning_ticket_on_HOLD(8,"308","有争议的中奖票 重试需要锁用户"),//有争议的中奖票 重试需要锁用户 Winning ticket on HOLD
	Game_closed(9,"309","批次已终结 重试需要锁用户"),//批次已终结 重试需要锁用户 Game closed
	Insufficient_privilege(10,"310","无兑奖权限，表示终端是否有兑奖权限 换个终端"),//无兑奖权限，表示终端是否有兑奖权限 换个终端 Insufficient privilege
	Invalid_attempt(12,"312","GTECH系统内部未准备就绪，可稍候重新尝试"),//GTECH系统内部未准备就绪，可稍候重新尝试 Invalid attempt
	Db_transaction_failure(13,"313","GTECH系统内部未准备就绪，可稍候重新尝试，非本地票可能也返回此错误，需要验证 重试需要锁用户"),//GTECH系统内部未准备就绪，可稍候重新尝试，非本地票可能也返回此错误，需要验证 重试需要锁用户Db transaction failure
	Invalid_pack_status(15,"315","无效的本状态  重试需要锁用户"),//无效的本状态  重试需要锁用户Invalid pack status
	Record_not_found(16,"316","可能由于批结，系统已经删除此票数据，记录找不到 重试需要锁用户"),//可能由于批结，系统已经删除此票数据，记录找不到 重试需要锁用户Record not found
	Cashing_limit_exceeded(19,"319","超出此终端机当天总兑奖限额 换个终端重试"),//超出此终端机当天总兑奖限额 换个终端重试Cashing limit exceeded
	File_claim_at_lotter(22,"322","大奖兑奖，先到新接口处兑奖，再去B2B兑奖，会出现此情况 重试需要锁用户"),//大奖兑奖，先到新接口处兑奖，再去B2B兑奖，会出现此情况 重试需要锁用户File claim at lotter
	Session_limit(24, "999", "终端到达上限 换个终端"), //终端到达上限 换个终端Session limit
	Pack_not_confirmed(28,"328","本没有确认 重试需要锁用户"),//本没有确认 重试需要锁用户Pack not confirmed
	Pack_not_activated(29,"329","未开售，本没有激活 重试需要锁用户"),//本没有激活 重试需要锁用户Pack not activated
	Claim_at_Online_Terminal(33,"333","先去终端机兑奖、再去B2B兑奖，会出现此情况 重试需要锁用户"),//先去终端机兑奖、再去B2B兑奖，会出现此情况 重试需要锁用户Claim at Online Terminal
	Game_not_active(40,"340","游戏未激活 重试需要锁用户"),//游戏未激活 重试需要锁用户Game not active
	Invalid_attempt41(41,"341","GTECH系统内部未准备就绪，可稍候重新尝试"),//GTECH系统内部未准备就绪，可稍候重新尝试Invalid attempt
	Not_Settled(43,"343","Not Settled"),
	
	CASH_NOT_SUCC(45,"345","兑奖不成功"),//之前没有 45 自己加的45
	
	UNKNOWN_CODE(999,"999","UNKNOWN CODE"),//此票未开售 重试需要锁用户
	
	// 新加错误码
	
	FORMAT_ERROR(46, "346", "Format error"),
	TOOMANG_CWLC_CONNECTIONS(47, "347", "Too many CWLC connections"),
	DATA_ERROR(48, "348", "Data error"),
	BAD_AGENTID(49, "349", "Bad agent ID"),
	BAD_GAMENUM(50, "350", "Bad game #"),
	BAD_BATCHNUM(51, "351", "Bad batch #"),
	BAD_BOOKNUM(52, "352", "Bad book #"),
	BAD_TICKETNUM(53, "353", "Bad ticket #"),
	BAD_VIRNNUM(54, "354", "Bad VIRN #"),
	BAD_INVENTORY(55, "355", "Bad inventory verification"),
	SALES_SUSPENDED(56, "356", "Sales suspended"),
	DATA_OFFLINE(57, "357", "Data offline"),
	DATA_NOT_EXIST(58, "358", "Data does not exist"),
	TICKET_FROM_DIFF_PROVICNE(59, "359", "Ticket from different province"),
	SERVICE_INACTIVE(60, "360", "Validation service inactive"),
	SERVICE_TIMEOUT(61, "361", "Validation service timeout"),
	MAXREQUESTNUM(62, "362", "IVAL #of ESA requests exceeded"),
	PRIZEGRADENUM_ERROR(63, "363", "IVAL Undefined prizeGradeNum received"),
	PRIZEAMOUNT_ERROR(64, "364", "IVAL Received different prize amount"),
	BUFFERPOOL_ERROR(65, "365", "Ticket pool is full -- try later"),
	UNKNOWNCODE(66, "366", "Unknown CWLIC error code"),
	
	SAMETICKETPENDING(67, "367", "Same ticket pending(at the same location) - try later"),
	TICKETPROCESSBYOTHER(68, "368", "Ticket is processed by other - try later"),
	MXSRVTERMINALINDEX_NOTFOUND(69, "369", "MXSRV terminal stats index not found"),
	APPLICATION_ERROR(70, "370", "MXSRV application not defined"),
	MXSRV_MAXNUM_EXCEEDED(71, "371", "MXSRV max # of requests exceeded"),
	MSGTYPE_NOTSUPPORTED(72, "372", "MXSRV message type not supported"),
	MXSRV_NOCONNECTION(73, "373", "MXSRV no connection"),
	MXSRV_BUILDMSG_ERROR(74, "374", "MXSRV cannot build message"),
	MSG_TOOBIG(75, "375", "MXSRV message too big"),
	BADTERMINAL_NBR(76, "376", "MXSRV bad terminal nbr"),
	RESPONSE_TIMEOUT(77, "377", "MXSRV response timeout"),
	TRANSLET_ERROR(78, "378", "MXSRV translet error"),
	COMMAND_DELIVER_ERROR(79, "379", "MXSRV command delivery failure"),
	REQUEST_FAILURE(80, "380", "MXSRV request failure"),
	MXSRV_UNKNOWN_ERROR(81, "381", "MXSRV unknown error"),
	CWLC_INTERFACE_ERROR(82, "382", "CWLIC HTTP interface error(check ELOG for details)"),
	DUPLICATE_BARCODE(83, "383", "Duplicate barcode");
	
	private int code;
	private String result;
	private String desc;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getResult() {
		return result;
	}
	private B2bResultCode(int code,String result,String desc){
		this.code=code;
		this.result = result;
		this.desc=desc;
	}
	public static B2bResultCode getById(int code){
		for(B2bResultCode result:B2bResultCode.values()){
			if(result.getCode() == code){
				return result;
			}
		}
		return null;
	}

	public static final List<Integer> CanLockErrors = new ArrayList<Integer>();
	public static final List<Integer> ChangeSessionErrors = new ArrayList<Integer>();

	static {
		CanLockErrors.add(B2bResultCode.File_claim.getCode());
		CanLockErrors.add(B2bResultCode.Not_a_winner.getCode());
		CanLockErrors.add(B2bResultCode.Previously_paid_by_you.getCode());
		CanLockErrors.add(B2bResultCode.Previously_paid_by_other.getCode());
		CanLockErrors.add(B2bResultCode.Previously_claimed_by_other.getCode());
		CanLockErrors.add(B2bResultCode.Previously_claimed_by_you.getCode());
		CanLockErrors.add(B2bResultCode.Winning_ticket_on_HOLD.getCode());
		CanLockErrors.add(B2bResultCode.Game_closed.getCode());
		CanLockErrors.add(B2bResultCode.Db_transaction_failure.getCode());
		CanLockErrors.add(B2bResultCode.Invalid_pack_status.getCode());
		CanLockErrors.add(B2bResultCode.Record_not_found.getCode());
		CanLockErrors.add(B2bResultCode.File_claim_at_lotter.getCode());
		CanLockErrors.add(B2bResultCode.Pack_not_activated.getCode());
		CanLockErrors.add(B2bResultCode.Pack_not_confirmed.getCode());
		CanLockErrors.add(B2bResultCode.Claim_at_Online_Terminal.getCode());
		CanLockErrors.add(B2bResultCode.Game_not_active.getCode());
		CanLockErrors.add(B2bResultCode.Not_Settled.getCode());
		
		CanLockErrors.add(B2bResultCode.CASH_NOT_SUCC.getCode());
		
		ChangeSessionErrors.add(B2bResultCode.Insufficient_privilege.getCode());
		ChangeSessionErrors.add(B2bResultCode.Cashing_limit_exceeded.getCode());
		ChangeSessionErrors.add(B2bResultCode.Session_limit.getCode());
	}

}
