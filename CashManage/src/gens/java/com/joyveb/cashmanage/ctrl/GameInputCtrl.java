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
import com.joyveb.cashmanage.entity.GameInput;
import com.joyveb.cashmanage.entity.GameInputExample;
import com.joyveb.cashmanage.entity.GameInputKey;
import com.joyveb.cashmanage.entity.StockManage;
import com.joyveb.cashmanage.entity.StockManageKey;
import com.joyveb.cashmanage.service.GameInputService;
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
@RequestMapping("/gameInput")
public class GameInputCtrl {

	private @Resource
	GameInputService dbService;
	private @Resource
	StockManageService stockManageService;
	@Resource(name = "initParm")
	private InitParm initParm;

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public ReturnInfo insert(@RequestBody GameInput info, HttpServletRequest req) {
		try {
			info.setUuid(IDGenerator.getInstance().generate());
			info.setInputtime(System.currentTimeMillis());
			info.setIsonsale("2");
			dbService.insert(info);
			StockManageKey smk = new StockManageKey();
			smk.setGamecode(info.getGamecode());
			StockManage sms = stockManageService.selectByPrimaryKey(smk);
			StockManage sm = new StockManage();
			sm.setGamecode(info.getGamecode());
			sm.setUpdatetime(System.currentTimeMillis());
			if (null == sms) {
				sm.setTotal(1L);
				stockManageService.insert(sm);
			} else {
				Long total = sms.getTotal();
				sm.setTotal(total + 1);
				stockManageService.updateByPrimaryKey(sm);
			}
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  GameInputCtrl insert error..", e);
		}
		return ReturnInfo.Faild;
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@RequestBody GameInput info, HttpServletRequest req) {
		try {
			dbService.updateByExample(info, dbService.getExample(info));
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  GameInputCtrl update error..", e);

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
			dc.setEntityClass(GameInput.class);
			dc.setKeyClass(GameInputKey.class);
			dc.setQmb(info);
			dc.setPageinfo(para);
			dc.setFmb(fmb);
			dc.setTalbeName(getTableName());
			totalCount = dbService.getCount(dc);
			list = dbService.getData(dc);
			for (int i = 0; i < list.size(); i++) {
				String gamecode = (String) list.get(i).get("gamecode");
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
			log.warn("  GameInputCtrl get error..", e);

		}
		if (para.isPage()) {
			return new ListInfo<HashMap<String, Object>>(totalCount, list, para);
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/worning", method = RequestMethod.GET)
	@ResponseBody
	public Object getWonring(@RequestJsonParam(value = "query", required = false) QueryMapperBean info,
			@RequestJsonParam(value = "fields", required = false) FieldsMapperBean fmb, PageInfo para, HttpServletRequest req) {
		long currentDays = System.currentTimeMillis();
		long twentyFour = 21 * 24 * 60 * 60 * 1000;
		long day = 24 * 60 * 60 * 1000;
		int totalCount = 0;
		List<HashMap<String, Object>> list = null;
		try {
			DbCondi dc = new DbCondi();
			dc.setEntityClass(GameInput.class);
			dc.setKeyClass(GameInputKey.class);
			dc.setQmb(info);
			dc.setPageinfo(para);
			dc.setFmb(fmb);
			dc.setTalbeName(getTableName());
			list = dbService.getData(dc);
			totalCount = dbService.getCount(dc);
			for (int i = 0; i < list.size(); i++) {
				String gamecode = (String) list.get(i).get("gamecode");
				long inputtime = Long.parseLong(list.get(i).get("inputtime").toString());
				long overtime = currentDays - inputtime;
				String gamename = "";
				GameInfo game = initParm.getGameInfo(gamecode);
				if (overtime >= twentyFour) {
					list.get(i).put("overtime", "自动开售");
				} else {
					list.get(i).put("overtime", ((twentyFour - overtime) / day + 1) + "");
				}
				if (game == null)
					list.get(i).put("gamename", "");
				else {
					gamename = game.getGamename();
					if (gamename != null)
						list.get(i).put("gamename", gamename);
				}
			}
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < list.size() - i - 1; j++) {
					HashMap<String, Object> hh = new HashMap<String, Object>();
					long oper = Long.parseLong(list.get(j).get("inputtime").toString());
					long oper1 = Long.parseLong(list.get(j + 1).get("inputtime").toString());
					if (oper1 < oper) {
						hh.putAll(list.get(j));
						list.set(j, list.get(j + 1));
						list.set((j + 1), hh);
					}
				}
			}

		} catch (Exception e) {
			log.warn("  GameInputCtrl getWonring error..", e);
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
			List<GameInput> list = new ArrayList<GameInput>();
			for (String id : data) {
				GameInput info = new GameInput();
				KeyExplainHandler.explainKey(id, info);
				list.add(info);
			}
			dbService.batchDelete(list);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  GameInputCtrl batchDelete error..", e);

		}
		return ReturnInfo.Faild;
	}

	@RequestMapping(value = "/batch", method = RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo batchUpdate(@RequestBody GameInputs data, HttpServletRequest req) {
		try {
			dbService.batchUpdate(data);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  GameInputCtrl batchUpdate error..", e);
		}
		return ReturnInfo.Faild;
	}

	@RequestMapping(value = "/batch", method = RequestMethod.POST)
	@ResponseBody
	public ReturnInfo batchInsert(@RequestBody GameInputs data, HttpServletRequest req) {
		try {
			dbService.batchInsert(data);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  GameInputCtrl batchInsert error..", e);
		}
		return ReturnInfo.Faild;
	}

	@RequestMapping(value = "/{key}", method = RequestMethod.GET)
	@ResponseBody
	public ListInfo<GameInput> get(@PathVariable String key, HttpServletRequest req) {
		int totalCount = 1;
		List<GameInput> list = new ArrayList<GameInput>();
		try {
			GameInput info = new GameInput();
			KeyExplainHandler.explainKey(key, info);
			list.add(dbService.selectByPrimaryKey(info));
		} catch (Exception e) {
			log.warn("  GameInputCtrl get by key error..", e);
		}
		return new ListInfo<GameInput>(totalCount, list, 0, 1);
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
			GameInputExample gipe = dbService.getExample(info);
			list = dbService.selectByExample(gipe);
			if (list.size() == 0) {
				tag = true;
			} else {
				tag = false;
			}
		} catch (Exception e) {
			log.warn("  GameInputCtrl getCheck error..", e);
		}
		return tag;
	}

	@RequestMapping(value = "/getCheckGamecode/{data_code}", method = RequestMethod.GET)
	@ResponseBody
	public Object getCheckGacode(@PathVariable String data_code, HttpServletRequest req) {
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
			log.warn("  GameInputCtrl getCheck error..", e);
		}
		return tag;
	}

	@RequestMapping(value = "/{key}", method = RequestMethod.DELETE)
	@ResponseBody
	public ReturnInfo delete(@PathVariable String key, HttpServletRequest req) {
		try {
			GameInput info = new GameInput();
			KeyExplainHandler.explainKey(key, info);
			info = dbService.selectByPrimaryKey(info);
			// "1"为已出库，库存不变
			if ("2".equals(info.getIsonsale())) {
				StockManageKey smk = new StockManageKey();
				smk.setGamecode(info.getGamecode());
				StockManage sms = stockManageService.selectByPrimaryKey(smk);
				StockManage sm = new StockManage();
				sm.setGamecode(info.getGamecode());
				sm.setUpdatetime(System.currentTimeMillis());
				if (null == sms) {
					sm.setTotal(1L);
					stockManageService.insert(sm);
				} else {
					Long total = sms.getTotal();
					sm.setTotal(total - 1);
					stockManageService.updateByPrimaryKey(sm);
				}
			}
			dbService.deleteByPrimaryKey(info);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  GameInputCtrl delete by key error..", e);
		}
		return ReturnInfo.Faild;
	}

	@RequestMapping(value = "/{key}", method = RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@PathVariable String key, @RequestBody GameInput info, HttpServletRequest req) {
		try {
			if (info != null) {
				KeyExplainHandler.explainKey(key, info);
				dbService.updateByPrimaryKey(info);
			}
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  GameInputCtrl update by key error..", e);
		}
		return ReturnInfo.Faild;
	}

	private String getControllerName() {
		return this.getClass().getSimpleName();
	}

	private String getTableName() {
		return "t_game_input";
	}

	@SuppressWarnings("serial")
	public static class GameInputs extends ArrayList<GameInput> {
		public GameInputs() {
			super();
		}
	}
}
