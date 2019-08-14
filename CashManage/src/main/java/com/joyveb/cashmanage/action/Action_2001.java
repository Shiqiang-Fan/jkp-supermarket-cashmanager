package com.joyveb.cashmanage.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.AbstractEntity;
import com.joyveb.cashmanage.bean.TransMessageEntity;
import com.joyveb.cashmanage.bean.request.ReqGameInfoEntity;
import com.joyveb.cashmanage.bean.request.ReqGameInfoEntityHolder;
import com.joyveb.cashmanage.bean.response.RepGameInfoEntity;
import com.joyveb.cashmanage.bean.response.RepQueryGameEntity;
import com.joyveb.cashmanage.common.ResponseResult;
import com.joyveb.cashmanage.entity.GameInfo;
import com.joyveb.cashmanage.entity.GameInput;
import com.joyveb.cashmanage.service.GameInfoService;
import com.joyveb.cashmanage.service.GameInputService;
import com.joyveb.cashmanage.service.StockManageService;
import com.joyveb.cashmanage.utils.JsonUtil;
import com.joyveb.cashmanage.validate.Validateable;
import com.joyveb.cashmanage.web.InitParm;

/**
 * 
 * 兑奖终端订票查询即开票信息
 * 
 */
@Slf4j
@Component("action_2001")
public class Action_2001 extends AbstractReqAction {

	private @Resource(name = "validator_2001")
	Validateable validator;
	private @Resource(name = "gameInputService")
	GameInputService gameInputService;
	@Resource(name = "initParm")
	private InitParm initParm;
	private @Resource(name = "stockManageService")
	StockManageService stockManageService;
	private @Resource(name = "gameInfoService")
	GameInfoService gameInfoService;

	@Override
	public AbstractEntity process(TransMessageEntity msg) {
		ReqGameInfoEntity body = (ReqGameInfoEntity) msg.getBody();
		List<GameInfo> findAll = initParm.getGameinfos();
		RepGameInfoEntity games = new RepGameInfoEntity();
		List<RepQueryGameEntity> list = new ArrayList<RepQueryGameEntity>();
		int parseInt = Integer.parseInt(body.getGametype());
		String sql = "select * from t_stock_manage; ";
		List<HashMap<String, Object>> stock = stockManageService.dosql(sql);
		if (parseInt > 0 && parseInt < 6) {
			if (null != findAll) {
				for (int i = 0; i < findAll.size(); i++) {
					GameInfo gameInfo2 = findAll.get(i);
					RepQueryGameEntity gameInfo = new RepQueryGameEntity();
					for (int j = 0; j < stock.size(); j++) {
						if (null != gameInfo2) {
							String gamecode = gameInfo2.getGamecode();
							if (null != gamecode && gamecode.equals(stock.get(j).get("GAMECODE"))) {
								gameInfo.setDetailedpath(gameInfo2.getDetailedpath());
								gameInfo.setFacevalue(gameInfo2.getFacevalue());
								gameInfo.setGamename(gameInfo2.getGamename());
								gameInfo.setPreviewpath(gameInfo2.getPreviewpath());
								gameInfo.setGamecode(gamecode);
								gameInfo.setPrice(gameInfo2.getGameprice().toString());
								gameInfo.setBooktotal(Integer.parseInt(stock.get(j).get("TOTAL").toString()));
								if (body.getGametype().equals(gameInfo2.getGametype()) && !body.getGametype().equals("1")
										&& !gameInfo2.getGametype().equals("6")) {
									list.add(gameInfo);
								} else if (body.getGametype().equals("1") && !gameInfo2.getGametype().equals("6")) {// 1代表所有数据
																													// 6为下架
									list.add(gameInfo);
								}
							}
						}
					}

				}
				games.setGames(removeDuplicate(list));
				games.setRcode(ResponseResult.SUCCESS.getCode());
				games.setDes(ResponseResult.SUCCESS.getDesc());
			}
		} else {
			games.setRcode(ResponseResult.PARAM_ERROR.getCode());
			games.setDes(ResponseResult.PARAM_ERROR.getDesc());
			log.warn("gametype is not 1,2,3,4,5 or null!");
		}
		games.setFreightnum(initParm.getStringDbp("freight.num", "2"));
		games.setFreightprice(initParm.getStringDbp("freight.price", "13"));
		return games;
	}

	public static List<RepQueryGameEntity> removeDuplicate(List<RepQueryGameEntity> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).equals(list.get(i))) {
					list.remove(j);
				}
			}
		}
		return list;
	}

	@Override
	public AbstractEntity processValidFail(ResponseResult vr) {
		RepGameInfoEntity versionn = new RepGameInfoEntity();
		log.warn(vr.getCode() + " " + vr.getDesc());
		return versionn;
	}

	@Override
	public AbstractEntity parseMessageBody(String reqjson) throws Exception {
		ReqGameInfoEntityHolder holder = JsonUtil.json2Bean(reqjson, ReqGameInfoEntityHolder.class);
		return holder.getBody();
	}

	@Override
	public ResponseResult validate(TransMessageEntity msg) {
		return validator.validate(msg);
	}

	@SuppressWarnings("serial")
	public static class GameInputs extends ArrayList<GameInput> {
		public GameInputs() {
			super();
		}
	}
}
