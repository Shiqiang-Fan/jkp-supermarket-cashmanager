package com.joyveb.cashmanage.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyveb.cashmanage.entity.GameInfo;
import com.joyveb.cashmanage.entity.GameInfoKey;
import com.joyveb.cashmanage.entity.GameInput;
import com.joyveb.cashmanage.entity.GameInputExample;
import com.joyveb.cashmanage.entity.GameOut;
import com.joyveb.cashmanage.entity.GameOutKey;
import com.joyveb.cashmanage.service.GameInputService;
import com.joyveb.cashmanage.service.GameOutService;
import com.joyveb.cashmanage.service.OrderInfoService;
import com.joyveb.cashmanage.service.StockManageService;
import com.joyveb.cashmanage.utils.IDGenerator;
import com.joyveb.cashmanage.web.InitParm;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.common.ListInfo;
import com.joyveb.lbos.restful.common.PageInfo;
import com.joyveb.lbos.restful.common.ReturnInfo;
import com.joyveb.lbos.restful.spring.FieldsMapperBean;
import com.joyveb.lbos.restful.spring.QueryMapperBean;
import com.joyveb.lbos.restful.spring.RequestJsonParam;
import com.joyveb.lbos.restful.util.KeyExplainHandler;

@Slf4j
@Controller
@RequestMapping("/gameOut")
public class GameOutCtrl {

	private @Resource
	GameOutService dbService;
	private @Resource
	GameInputService gameInputService;
	private @Resource
	StockManageService stockManageService;
	private @Resource
	OrderInfoService orderInfoService;
	@Resource(name = "initParm")
	private InitParm initParm;

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public ReturnInfo insert(@RequestBody GameOut info, HttpServletRequest req) {
		try {
			info.setUuid(IDGenerator.getInstance().generate());
			info.setOuttime(System.currentTimeMillis());
			dbService.insert(info);
			String sql = "UPDATE `t_order_info` SET `ORDERSTATUS`='4' WHERE `ORDERID`='" + info.getOrderid() + "'";
			orderInfoService.dosql(sql);
			GameInputExample gie = new GameInputExample();
			GameInput gInput = new GameInput();
			gInput.setIsonsale("1");
			gie.createCriteria().andGamecodeEqualTo(info.getGamecode()).andBooknumEqualTo(info.getBooknum())
					.andProductbatchEqualTo(info.getProductbatch());
			gameInputService.updateByExampleSelective(gInput, gie);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  GameOutCtrl insert error..", e);

		}
		return ReturnInfo.Faild;
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@RequestBody GameOut info, HttpServletRequest req) {
		try {
			dbService.updateByExample(info, dbService.getExample(info));
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  GameOutCtrl update error..", e);

		}
		return ReturnInfo.Faild;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public Object get(@RequestJsonParam(value = "query", required = false) QueryMapperBean info,
			@RequestJsonParam(value = "fields", required = false) FieldsMapperBean fmb, PageInfo para, HttpServletRequest req) {
		int totalCount = 0;
		List<HashMap<String, Object>> list = null;
		try {
			DbCondi dc = new DbCondi();
			dc.setEntityClass(GameOut.class);
			dc.setKeyClass(GameOutKey.class);
			dc.setQmb(info);
			dc.setPageinfo(para);
			dc.setFmb(fmb);
			dc.setTalbeName(getTableName());
			totalCount = dbService.getCount(dc);
			list = dbService.getData(dc);
			for (int i = 0; i < list.size(); i++) {
				String gamecode = (String) list.get(i).get("gamecode");
				GameInfoKey gameInfotKey = new GameInfoKey();
				gameInfotKey.setGamecode(gamecode);
				String gamename = "";
				GameInfo game = initParm.getGameInfo(gamecode);
				if (game == null) {
					continue;
				}
				gamename = game.getGamename();
				if (gamename != null)
					list.get(i).put("gamename", gamename);
			}
		} catch (Exception e) {
			log.warn("  GameOutCtrl get error..", e);

		}
		if (para.isPage()) {
			return new ListInfo<HashMap<String, Object>>(totalCount, list, para);
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/batch/delete", method = RequestMethod.POST)
	@ResponseBody
	public ReturnInfo batchDelete(@RequestBody List<String> data, HttpServletRequest req) {
		try {
			List<GameOut> list = new ArrayList<GameOut>();
			for (String id : data) {
				GameOut info = new GameOut();
				KeyExplainHandler.explainKey(id, info);
				list.add(info);
			}
			dbService.batchDelete(list);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  GameOutCtrl batchDelete error..", e);

		}
		return ReturnInfo.Faild;
	}

	@RequestMapping(value = "/batch", method = RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo batchUpdate(@RequestBody GameOuts data, HttpServletRequest req) {
		try {
			dbService.batchUpdate(data);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  GameOutCtrl batchUpdate error..", e);
		}
		return ReturnInfo.Faild;
	}

	@RequestMapping(value = "/batch", method = RequestMethod.POST)
	@ResponseBody
	public ReturnInfo batchInsert(@RequestBody GameOuts data, HttpServletRequest req) {
		try {
			dbService.batchInsert(data);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  GameOutCtrl batchInsert error..", e);
		}
		return ReturnInfo.Faild;
	}

	@RequestMapping(value = "/{key}", method = RequestMethod.GET)
	@ResponseBody
	public ListInfo<GameOut> get(@PathVariable String key, HttpServletRequest req) {
		int totalCount = 1;
		List<GameOut> list = new ArrayList<GameOut>();
		try {
			GameOut info = new GameOut();
			KeyExplainHandler.explainKey(key, info);
			list.add(dbService.selectByPrimaryKey(info));
		} catch (Exception e) {
			log.warn("  GameOutCtrl get by key error..", e);
		}
		return new ListInfo<GameOut>(totalCount, list, 0, 1);
	}

	@RequestMapping(value = "/getCheck/{data}", method = RequestMethod.GET)
	@ResponseBody
	public Object getCheck(@PathVariable String data, HttpServletRequest req) {
		List<GameInput> list = null;
		boolean tag = false;
		try {
			GameInput info = new GameInput();
			info.setBooknum(data.substring(10, 17));
			info.setGamecode(data.substring(0, 5));
			info.setProductbatch(data.substring(5, 10));
			info.setIsonsale("2");
			GameInputExample gope = gameInputService.getExample(info);
			list = gameInputService.selectByExample(gope);
			if (list.size() == 0) {
				tag = true;
			} else {
				tag = false;
			}
		} catch (Exception e) {
			log.warn("  GameOutCtrl getCheck error..", e);
		}
		return tag;
	}

	@RequestMapping(value = "/getCheckGamecode/{data_code}", method = RequestMethod.GET)
	@ResponseBody
	public Object getCheckGamecode(@PathVariable String data_code, HttpServletRequest req) {
		boolean tag = false;
		try {
			String gamecode = data_code;
			GameInfo gameInfo2 = initParm.getGameInfo(gamecode);
			if (gameInfo2 == null) {
				tag = true;
			} else {
				tag = false;
			}
		} catch (Exception e) {
			log.warn("  GameOutCtrl getCheckGamecode error..", e);
		}
		return tag;
	}

	@RequestMapping(value = "/{key}", method = RequestMethod.DELETE)
	@ResponseBody
	public ReturnInfo delete(@PathVariable String key, HttpServletRequest req) {
		try {
			GameOut info = new GameOut();
			KeyExplainHandler.explainKey(key, info);
			dbService.deleteByPrimaryKey(info);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  GameOutCtrl delete by key error..", e);
		}
		return ReturnInfo.Faild;
	}

	@RequestMapping(value = "/{key}", method = RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@PathVariable String key, @RequestBody GameOut info, HttpServletRequest req) {
		try {
			if (info != null) {
				KeyExplainHandler.explainKey(key, info);
				dbService.updateByPrimaryKey(info);
			}
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  GameOutCtrl update by key error..", e);
		}
		return ReturnInfo.Faild;
	}

	private String getControllerName() {
		return this.getClass().getSimpleName();
	}

	private String getTableName() {
		return "t_game_out";
	}

	@SuppressWarnings("serial")
	public static class GameOuts extends ArrayList<GameOut> {
		public GameOuts() {
			super();
		}
	}
}
