package com.joyveb.cashmanage.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import com.joyveb.cashmanage.bean.CashEntity;
import com.joyveb.cashmanage.ctrl.DbPropertiesCtrl.DbPropertiess;
import com.joyveb.cashmanage.ctrl.GameInfoCtrl.GameInfos;
import com.joyveb.cashmanage.ctrl.TerminalCtrl.Terminals;
import com.joyveb.cashmanage.entity.DbProperties;
import com.joyveb.cashmanage.entity.GameInfo;
import com.joyveb.cashmanage.entity.Terminal;
import com.joyveb.cashmanage.entity.WhiteList;
import com.joyveb.cashmanage.entity.WhiteListExample;
import com.joyveb.cashmanage.service.DbPropertiesService;
import com.joyveb.cashmanage.service.GameInfoService;
import com.joyveb.cashmanage.service.TerminalService;
import com.joyveb.cashmanage.service.WhiteListService;


@Component("initParm")
public class InitParm {
	@Resource(name="dbPropertiesService")
	public DbPropertiesService dbPropertiesService;
	@Resource(name="gameInfoService")
	private GameInfoService gameInfoService;
	@Resource(name="whiteListService")
	private WhiteListService whiteListService;
	@Resource(name="terminalService")
	private TerminalService terminalService;
	//根据主键
	private static Map<String, String> dbpMap = new ConcurrentHashMap<String, String>();
	//根据主键
	private static Map<String, GameInfo> gameMap = new ConcurrentHashMap<String, GameInfo>();
	//根据username
	private static Map<String, WhiteList> whiteMap = new ConcurrentHashMap<String, WhiteList>();
	//根据主键
	private static Map<String, Terminal> terminalMap = new ConcurrentHashMap<String, Terminal>();
	//所有的gameinfo
	private static List<GameInfo> gameinfo = new ArrayList<GameInfo>();
	//已兑奖未中奖限制
	private static Map<String, CashEntity> alCashMap = new ConcurrentHashMap<String, CashEntity>();
	public int getIntDbp(String key,int num){                                    
		return NumberUtils.toInt(dbpMap.get(key), num); 
	}
	
	public long getLongDbp(String key,int num){
		return NumberUtils.toLong(dbpMap.get(key),num);
	}
	
	public String getStringDbp(String key,String val){
		return StringUtils.defaultString(dbpMap.get(key),val);
	}
	
	public void initDbp(){
		dbpMap.clear();
		DbPropertiess dbPropertiess = new DbPropertiess();
		List<DbProperties> dbProperties = dbPropertiesService.findAll(dbPropertiess);
		int size = dbProperties.size();
		for(int i=0;i<size;i++){
			DbProperties dbProperties2 = dbProperties.get(i);
			dbpMap.put(dbProperties2.getThekey(), dbProperties2.getValue());
		}
	}
	
	public void initTerminal(){
		terminalMap.clear();
		Terminals terminals = new Terminals();
		List<Terminal> terminal= terminalService.findAll(terminals);
		int size = terminal.size();
		for(int i=0;i<size;i++){
			Terminal terminal2 = terminal.get(i);
			terminalMap.put(terminal2.getSessionid(), terminal2);
		}
	}
	public Terminal getTerminal(String sessionid){
		return terminalMap.get(sessionid);
	}
	public Map<String, Terminal> getTerminals() {
		return  terminalMap;
	}
	
	public void initGame(){
		gameinfo.clear();
		gameMap.clear();
		GameInfos dbPropertiess = new GameInfos();
		List<GameInfo> dbProperties = gameInfoService.findAll(dbPropertiess);
		gameinfo.addAll(dbProperties);
		int size = dbProperties.size();
		for(int i=0;i<size;i++){
			GameInfo gameInfo = dbProperties.get(i);
			gameMap.put(gameInfo.getGamecode(), gameInfo);
		}
	}
	public GameInfo getGameInfo(String gameCode){
		return gameMap.get(gameCode);
	}
	public List<GameInfo> getGameinfos() {
		return gameinfo;
	}
	
	public String getGameNameByCode(String gameCode){
		if(null!=gameMap.get(gameCode)){
			return gameMap.get(gameCode).getGamename();
		}
		return "";
	}
	
	//初始化所有代销商
	public List<WhiteList> initWhiteList(){
		WhiteListExample wle = new WhiteListExample();
		wle.createCriteria().andStatusEqualTo("0");
		List<WhiteList> whites = whiteListService.selectByExample(wle);
		if(null!=whites && whites.size()>0){
			for (int i = 0; i < whites.size(); i++) {
				whiteMap.put(whites.get(i).getUsername(), whites.get(i));
			}
			return whites;
		}
		return null;
	}
	
	public WhiteList getWhiteList(String username){
		return whiteMap.get(username);
	}
	public CashEntity getAlCashMap(String str){
		return alCashMap.get(str);
	}
	public void setAlCashMap(String str,CashEntity it){
		alCashMap.put(str, it);
	}
	public void clearCashMap(String str){
		alCashMap.remove(str);
	}
}
