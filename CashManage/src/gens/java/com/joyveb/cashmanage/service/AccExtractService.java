package com.joyveb.cashmanage.service;

import com.joyveb.cashmanage.action.PayFactory;
import com.joyveb.cashmanage.alipay.AlipayConfig;
import com.joyveb.cashmanage.alipay.TransactionRecord;
import com.joyveb.cashmanage.alipay.service.AlipayService;
import com.joyveb.cashmanage.common.Constants;
import com.joyveb.cashmanage.entity.*;
import com.joyveb.cashmanage.entity.AccExtractExample.Criteria;
import com.joyveb.cashmanage.mapper.AccExtractMapper;
import com.joyveb.cashmanage.mapper.AccWhitelistParentMapper;
import com.joyveb.cashmanage.mapper.LogMoneyMapper;
import com.joyveb.cashmanage.utils.CommonUtils;
import com.joyveb.cashmanage.web.InitParm;
import com.joyveb.lbos.restful.common.CommonSqlMapper;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.common.ReturnInfo;
import com.joyveb.lbos.restful.util.KeyExplainHandler;
import com.joyveb.lbos.restful.util.SqlMaker;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.System.currentTimeMillis;

@Slf4j
@Service
public class AccExtractService implements ServiceInterface<AccExtract, AccExtractExample, AccExtractKey> {

    @Resource
    private AccExtractMapper mapper;
    private @Resource
    CommonSqlMapper common;
    @Resource(name = "initParm")
    private InitParm initParm;
    @Resource(name = "accWhitelistParentMapper")
    private AccWhitelistParentMapper accWhitelistParentMapper;
    @Resource(name = "payFactory")
    private PayFactory payFactory;
    @Resource(name = "logMoneyMapper")
    private LogMoneyMapper logMoneyMapper;

    @Override
    public int countByExample(AccExtractExample example) {
        return mapper.countByExample(example);
    }

    @Override
    public int deleteByExample(AccExtractExample example) {
        return mapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(AccExtractKey key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    public int insert(AccExtract record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(AccExtract record) {
        return mapper.insertSelective(record);
    }

    @Override
    @Transactional
    public int batchInsert(List<AccExtract> records) {
        for (AccExtract record : records) {
            mapper.insert(record);
        }
        return records.size();
    }

    public ReturnInfo alipayTransFer(BigDecimal money, String username, String alipayNum) {
        ReturnInfo returnInfo = new ReturnInfo();
        AlipayService alipayService = (AlipayService) payFactory.getPayService(Constants.ALIPAYFLAG);
        String s = CommonUtils.returnYYMMDDHHMM();
        s = s + username;
        try {
            TransactionRecord transToAcc = alipayService.transToAcc(alipayNum, String.valueOf(money), s);
            String alipayrecord = transToAcc.getAlipayrecord();
            SAXReader reader = new SAXReader();
            Document document = reader.read(new ByteArrayInputStream(alipayrecord.getBytes("UTF-8")));
            Element root = document.getRootElement();
            String error_msg = root.element("response").element("alipay").elementText("error_msg");
            LogMoney logMoney = new LogMoney();
            logMoney.setUuid(s);
            logMoney.setAlipaynum(alipayNum);
            logMoney.setDate(CommonUtils.returnYYMMDD());
            logMoney.setExtracttime(currentTimeMillis());
            logMoney.setExtractmoney(money);
            logMoney.setParentname(username);
            returnInfo.setRetcode(1);
            returnInfo.setDescription(transToAcc.getErrormsg());
            returnInfo.setSuccess(false);
            if (null == transToAcc) {
                log.info("向用户" + username + "转账失败,支付宝配置错误");
                insertLogMoney(logMoney, AlipayConfig.TRANSACTIONFAIL, error_msg);
            } else {
                Integer status = transToAcc.getStatus();
                if (status == 1) {// 1代表转账成功
                    updateAccExtractMoney(username, money, alipayNum);
                    insertLogMoney(logMoney, AlipayConfig.TRANSACTIONSUCC, "提现成功");
                    log.info("向用户" + username + "转账成功，金额：" + money);
                    returnInfo.setSuccess(true);
                    returnInfo.setDescription("提现成功！");
                    returnInfo.setRetcode(0);
                } else {// 代表转账失败
                    insertLogMoney(logMoney, AlipayConfig.TRANSACTIONFAIL, error_msg);
                    returnInfo.setSuccess(false);
                    returnInfo.setDescription(error_msg);
                    returnInfo.setRetcode(1);
                }
            }
        } catch (Exception e) {
            log.debug("支付宝转账错误！", e);
        }
        return returnInfo;
    }

    public void insertLogMoney(LogMoney logMoney, String status, String failcause) {
        logMoney.setStatus(status);
        logMoney.setFailreason(failcause);
        logMoneyMapper.insert(logMoney);
    }

    public void updateAccExtractMoney(String username, BigDecimal money, String alipayNum) {
        String sql = "update t_acc_extract set MONEY = MONEY - "+money+" ,ALIPAYNUM = '"+alipayNum+"' ,STATUS = '1' where USERNAME = '"+username+"'";
        common.executeSql(sql);
    }

    public ReturnInfo checkIdData(AccExtractAccept accExtractAccept, HttpServletRequest req) {
        AccWhitelistParentExample accWhitelistParentExample = new AccWhitelistParentExample();
        accWhitelistParentExample.createCriteria().andUsernameEqualTo(accExtractAccept.getUsername()).andStatusEqualTo("0");
        List<AccWhitelistParent> accWhitelistParents = accWhitelistParentMapper.selectByExample(accWhitelistParentExample);
        AccExtractKey accExtractKey = new AccExtractKey();
        accExtractKey.setUsername(accExtractAccept.getUsername());
        AccExtract accExtract = mapper.selectByPrimaryKey(accExtractKey);
        ReturnInfo returnInfo = new ReturnInfo();
        returnInfo.setSuccess(true);
        returnInfo.setRetcode(0);
        if (CommonUtils.isNotEmpty(accWhitelistParents)) {
            AccWhitelistParent accWhitelistParent1 = accWhitelistParents.get(0);
            String quota = initParm.getStringDbp("tract.money.quota", "100");
            BigDecimal bigDecimal = new BigDecimal(quota);
            if (accExtractAccept.getMoney().compareTo(bigDecimal) < 0) {
                returnInfo.setDescription("满" + quota + "元可提现");
                returnInfo.setSuccess(false);
                returnInfo.setRetcode(1);
            }
            if(accExtractAccept.getMoney().compareTo(accExtract.getMoney())>0){
                returnInfo.setDescription("提现金额大于账户金额！");
                returnInfo.setSuccess(false);
                returnInfo.setRetcode(1);
            }
            String idcard = accWhitelistParent1.getIdcard();
            String substring = idcard.substring(idcard.length() - 4, idcard.length());
            if (!accExtractAccept.getIdcard4last().equals(substring)) {
                returnInfo.setDescription("身份证号不匹配，请核对！");
                returnInfo.setSuccess(false);
                returnInfo.setRetcode(1);
            }
            String mobile = accWhitelistParent1.getMobile();
            if(!mobile.equals(accExtractAccept.getMobile())){
                returnInfo.setDescription("手机号不匹配，请核对！");
                returnInfo.setSuccess(false);
                returnInfo.setRetcode(1);
            }
            Object attribute = req.getSession().getAttribute(accExtractAccept.getUsername());
            if(null==attribute){
                returnInfo.setDescription("该用户未登录");
                returnInfo.setSuccess(false);
                returnInfo.setRetcode(1);
            }
        } else {
            returnInfo.setSuccess(false);
            returnInfo.setDescription("此用户不存在！");
            returnInfo.setRetcode(1);
        }
        return returnInfo;
    }

    public ReturnInfo selectQuota() {
        ReturnInfo returnInfo = new ReturnInfo();
        returnInfo.setSuccess(true);
        returnInfo.setRetcode(0);
        String quota = initParm.getStringDbp("tract.money.quota", "100");
        returnInfo.setDescription("满" + quota + "元可提现");
        returnInfo.setSuccess(false);
        returnInfo.setRetcode(1);
        returnInfo.setRetObj(quota);
        return returnInfo;
    }

    public AccExtract selectAccExtractByUsername(String username) {
        AccExtract accExtract = new AccExtract();
        accExtract.setUsername(username);
        AccExtract accExtract1 = mapper.selectByPrimaryKey(accExtract);
        return accExtract1;
    }

    public BigDecimal selectMoneyByUsername(String username) {
        AccExtract accExtract = selectAccExtractByUsername(username);
        if (null == accExtract) {
            BigDecimal bigDecimal = new BigDecimal("0");
            return bigDecimal;
        } else {
            BigDecimal money = accExtract.getMoney();
            return money;
        }
    }

    @Override
    @Transactional
    public int batchUpdate(List<AccExtract> records) {
        for (AccExtract record : records) {
            mapper.updateByPrimaryKeySelective(record);
        }
        return records.size();
    }

    @Override
    @Transactional
    public int batchDelete(List<AccExtract> records) {
        for (AccExtract record : records) {
            mapper.deleteByPrimaryKey(record);
        }
        return records.size();
    }

    @Override
    public List<AccExtract> selectByExample(AccExtractExample example) {
        return mapper.selectByExample(example);
    }

    @Override
    public AccExtract selectByPrimaryKey(AccExtractKey key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    public List<AccExtract> findAll(List<AccExtract> records) {
        if (records == null || records.size() <= 0) {
            return mapper.selectByExample(new AccExtractExample());
        }
        List<AccExtract> list = new ArrayList<AccExtract>();
        for (AccExtract record : records) {
            AccExtract result = mapper.selectByPrimaryKey(record);
            if (result != null) {
                list.add(result);
            }
        }
        return list;
    }

    @Override
    public int updateByExampleSelective(AccExtract record, AccExtractExample example) {
        return mapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(AccExtract record, AccExtractExample example) {
        return mapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(AccExtract record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AccExtract record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public int sumByExample(AccExtractExample example) {
        return 0;
    }

    @Override
    public void deleteAll() {
        mapper.deleteByExample(new AccExtractExample());
    }


    public int getCount(DbCondi dc) {
        List<HashMap<String, Object>> resultSet = null;
        try {
            resultSet = common.executeSql(SqlMaker.getCountSql(dc));
            return ((Number) resultSet.get(0).get("COUNT")).intValue();
        } catch (Exception e) {
            log.warn("执行sql异常",e);
            return 0;
        }
    }

    public List<HashMap<String, Object>> getData(DbCondi dc) {
        List<HashMap<String, Object>> resultSet = null;
        try {
            String sql = SqlMaker.getData(dc);
            resultSet = common.executeSql(sql);
            KeyExplainHandler.addId(resultSet, dc.getKeyClass(), dc.getEntityClass());//add key
        } catch (IllegalAccessException e) {
            log.warn("执行sql异常",e);
        } catch (InvocationTargetException e) {
            log.warn("执行sql异常",e);
        }
        return resultSet;
    }

    public List<HashMap<String, Object>> dosql(String sql) {
        List<HashMap<String, Object>> resultSet = common.executeSql(sql);
        return resultSet;
    }

    @Override
    public AccExtractExample getExample(AccExtract record) {
        AccExtractExample example = new AccExtractExample();
        if (record != null) {
            Criteria criteria = example.createCriteria();
            if (record.getUsername() != null) {
                criteria.andUsernameEqualTo(record.getUsername());
            }
            if (record.getMoney() != null) {
                criteria.andMoneyEqualTo(record.getMoney());
            }
            if (record.getAlipaynum() != null) {
                criteria.andAlipaynumEqualTo(record.getAlipaynum());
            }
            if (record.getStatus() != null) {
                criteria.andStatusEqualTo(record.getStatus());
            }

        }
        return example;
    }
}
