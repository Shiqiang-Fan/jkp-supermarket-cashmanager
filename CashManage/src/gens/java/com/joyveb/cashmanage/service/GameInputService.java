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
import com.joyveb.cashmanage.entity.GameInputExample.Criteria;
import com.joyveb.cashmanage.entity.GameInputKey;
import com.joyveb.cashmanage.entity.StockManage;
import com.joyveb.cashmanage.entity.StockManageKey;
import com.joyveb.cashmanage.mapper.GameInputMapper;
import com.joyveb.cashmanage.utils.IDGenerator;
import com.joyveb.lbos.restful.common.CommonSqlMapper;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.util.KeyExplainHandler;
import com.joyveb.lbos.restful.util.SqlMaker;
@Slf4j
@Service
public class GameInputService implements ServiceInterface<GameInput, GameInputExample, GameInputKey> {

	@Resource
	private GameInputMapper mapper;
	private @Resource
	CommonSqlMapper common;
	private @Resource
	StockManageService stockManageService;

	@Override
	public int countByExample(GameInputExample example) {
		return mapper.countByExample(example);
	}

	@Override
	public int deleteByExample(GameInputExample example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(GameInputKey key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(GameInput record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(GameInput record) {
		return mapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int batchInsert(List<GameInput> records) {
		for (GameInput record : records) {
			if (record.getGamecode() == null) {
				continue;
			}
			record.setUuid(IDGenerator.getInstance().generate());
			record.setIsonsale("2");
			mapper.insert(record);
			StockManageKey smk = new StockManageKey();
			smk.setGamecode(record.getGamecode());
			StockManage sms = stockManageService.selectByPrimaryKey(smk);
			StockManage sm = new StockManage();
			sm.setGamecode(record.getGamecode());
			sm.setUpdatetime(System.currentTimeMillis());
			record.setInputtime(System.currentTimeMillis());
			if (null == sms) {
				sm.setTotal(1L);
				stockManageService.insert(sm);
			} else {
				Long total = sms.getTotal();
				sm.setTotal(total + 1);
				stockManageService.updateByPrimaryKey(sm);
			}

		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchUpdate(List<GameInput> records) {
		for (GameInput record : records) {
			mapper.updateByPrimaryKeySelective(record);
		}
		return records.size();
	}

	@Override
	@Transactional
	public int batchDelete(List<GameInput> records) {
		for (GameInput record : records) {
			GameInput info = new GameInput();
			info = mapper.selectByPrimaryKey(record);
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
			mapper.deleteByPrimaryKey(record);
		}
		return records.size();
	}

	@Override
	public List<GameInput> selectByExample(GameInputExample example) {
		return mapper.selectByExample(example);
	}

	@Override
	public GameInput selectByPrimaryKey(GameInputKey key) {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public List<GameInput> findAll(List<GameInput> records) {
		if (records == null || records.size() <= 0) {
			return mapper.selectByExample(new GameInputExample());
		}
		List<GameInput> list = new ArrayList<GameInput>();
		for (GameInput record : records) {
			GameInput result = mapper.selectByPrimaryKey(record);
			if (result != null) {
				list.add(result);
			}
		}
		return list;
	}

	@Override
	public int updateByExampleSelective(GameInput record, GameInputExample example) {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(GameInput record, GameInputExample example) {
		return mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(GameInput record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(GameInput record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int sumByExample(GameInputExample example) {
		return 0;
	}

	@Override
	public void deleteAll() {
		mapper.deleteByExample(new GameInputExample());
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
	public GameInputExample getExample(GameInput record) {
		GameInputExample example = new GameInputExample();
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
			if (record.getInputtime() != null) {
				criteria.andInputtimeEqualTo(record.getInputtime());
			}
			if (record.getInputperson() != null) {
				criteria.andInputpersonEqualTo(record.getInputperson());
			}
			if (record.getIsonsale() != null) {
				criteria.andIsonsaleEqualTo(record.getIsonsale());
			}
			if (record.getOnsaletime() != null) {
				criteria.andOnsaletimeEqualTo(record.getOnsaletime());
			}

		}
		return example;
	}
}
