package com.joyveb.cashmanage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyveb.cashmanage.entity.GameInput;
import com.joyveb.cashmanage.entity.GameInputExample;
import com.joyveb.cashmanage.entity.GameOut;
import com.joyveb.cashmanage.entity.GameOutExample;
import com.joyveb.cashmanage.entity.GameOutExample.Criteria;
import com.joyveb.cashmanage.entity.GameOutKey;
import com.joyveb.cashmanage.mapper.GameOutMapper;
import com.joyveb.cashmanage.utils.IDGenerator;
import com.joyveb.lbos.restful.common.CommonSqlMapper;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.util.KeyExplainHandler;
import com.joyveb.lbos.restful.util.SqlMaker;
@Slf4j
@Service
public class GameOutService implements ServiceInterface<GameOut, GameOutExample, GameOutKey> {

	@Resource
	private GameOutMapper mapper;
	private @Resource
	CommonSqlMapper common;
	private @Resource
	StockManageService stockManageService;
	private @Resource
	GameInputService gameInputService;
	private @Resource
	OrderInfoService orderInfoService;

	@Override
	public int countByExample(GameOutExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public int deleteByExample(GameOutExample example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(GameOutKey key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(GameOut record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(GameOut record) {
		return mapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int batchInsert(List<GameOut> records) {
		for (GameOut record : records) {
			if (record.getGamecode() == null) {
				continue;
			}
			record.setUuid(IDGenerator.getInstance().generate());
			record.setOuttime(System.currentTimeMillis());
			String sql = "UPDATE `t_order_info` SET `ORDERSTATUS`='4' WHERE `ORDERID`='" + record.getOrderid() + "'";
			orderInfoService.dosql(sql);
			GameInputExample gie = new GameInputExample();
			GameInput gInput = new GameInput();
			gInput.setIsonsale("1");
			gie.createCriteria().andGamecodeEqualTo(record.getGamecode()).andBooknumEqualTo(record.getBooknum())
					.andProductbatchEqualTo(record.getProductbatch());
			gameInputService.updateByExampleSelective(gInput, gie);
			mapper.insert(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchUpdate(List<GameOut> records) {
		for (GameOut record : records) {
			mapper.updateByPrimaryKeySelective(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchDelete(List<GameOut> records) {
		for (GameOut record : records) {
			mapper.deleteByPrimaryKey(record);
		}
		return records.size();
	}

	@Override
	public List<GameOut> selectByExample(GameOutExample example) {
		return mapper.selectByExample(example);
	}

	@Override
	public GameOut selectByPrimaryKey(GameOutKey key) {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public List<GameOut> findAll(List<GameOut> records) {
		if (records == null || records.size() <= 0) {
			return mapper.selectByExample(new GameOutExample());
		}
		List<GameOut> list = new ArrayList<GameOut>();
		for (GameOut record : records) {
			GameOut result = mapper.selectByPrimaryKey(record);
			if (result != null) {
				list.add(result);
			}
		}
		return list;
	}

	@Override
	public int updateByExampleSelective(GameOut record, GameOutExample example) {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(GameOut record, GameOutExample example) {
		return mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(GameOut record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(GameOut record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int sumByExample(GameOutExample example) {
		return 0;
	}

	@Override
	public void deleteAll() {
		mapper.deleteByExample(new GameOutExample());
	}

	public int getCount(DbCondi dc) {
		List<HashMap<String, Object>> resultSet = null;
		try {
			resultSet = common.executeSql(SqlMaker.getCountSql(dc));
		} catch (Exception e) {
			log.warn("执行sql异常",e);
		}
		return ((Number) resultSet.get(0).get("COUNT")).intValue();
	}

	public List<HashMap<String, Object>> getData(DbCondi dc) {
		List<HashMap<String, Object>> resultSet = null;
		try {
			String sql = SqlMaker.getData(dc);
			resultSet = common.executeSql(sql);
			KeyExplainHandler.addId(resultSet, dc.getKeyClass(), dc.getEntityClass());// add key
		} catch (Exception e) {
			log.warn("执行sql异常",e);
		}
		return resultSet;
	}

	public List<HashMap<String, Object>> dosql(String sql) {
		List<HashMap<String, Object>> resultSet = common.executeSql(sql);
		return resultSet;
	}

	@Override
	public GameOutExample getExample(GameOut record) {
		GameOutExample example = new GameOutExample();
		if (record != null) {
			Criteria criteria = example.createCriteria();
			if (record.getUuid() != null) {
				criteria.andUuidEqualTo(record.getUuid());
			}
			if (record.getGamecode() != null) {
				criteria.andGamecodeEqualTo(record.getGamecode());
			}
			if (record.getProductbatch() != null) {
				criteria.andProductbatchEqualTo(record.getProductbatch());
			}
			if (record.getBooknum() != null) {
				criteria.andBooknumEqualTo(record.getBooknum());
			}
			if (record.getOuttime() != null) {
				criteria.andOuttimeEqualTo(record.getOuttime());
			}
			if (record.getOutperson() != null) {
				criteria.andOutpersonEqualTo(record.getOutperson());
			}
			if (record.getOrderid() != null) {
				criteria.andOrderidEqualTo(record.getOrderid());
			}

		}
		return example;
	}
}
