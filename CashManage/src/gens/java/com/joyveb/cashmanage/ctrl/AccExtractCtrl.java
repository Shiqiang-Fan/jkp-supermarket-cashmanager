package com.joyveb.cashmanage.ctrl;

import com.joyveb.cashmanage.entity.AccExtract;
import com.joyveb.cashmanage.entity.AccExtractAccept;
import com.joyveb.cashmanage.entity.AccExtractKey;
import com.joyveb.cashmanage.service.AccExtractService;
import com.joyveb.cashmanage.service.AccNeightCheckService;
import com.joyveb.lbos.restful.common.DbCondi;
import com.joyveb.lbos.restful.common.ListInfo;
import com.joyveb.lbos.restful.common.PageInfo;
import com.joyveb.lbos.restful.common.ReturnInfo;
import com.joyveb.lbos.restful.spring.FieldsMapperBean;
import com.joyveb.lbos.restful.spring.QueryMapperBean;
import com.joyveb.lbos.restful.spring.RequestJsonParam;
import com.joyveb.lbos.restful.util.KeyExplainHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/accExtract")
public class AccExtractCtrl {

    private @Resource
    AccExtractService dbService;
    private @Resource
    AccNeightCheckService accNeightCheckService;


    @RequestMapping(value = "/extract", method = RequestMethod.POST)
    @ResponseBody
    public ReturnInfo extract(@RequestBody AccExtractAccept info, HttpServletRequest req) {
        ReturnInfo returnInfo = dbService.checkIdData(info, req);
        try {
            //校验身份证号后四位 提现限额
            if (returnInfo.getRetcode() == 1) {
                return returnInfo;
            } else {
                //核对金额
                accNeightCheckService.allowTract(info.getUsername());
                AccExtract accExtract = dbService.selectAccExtractByUsername(info.getUsername());
                if (accExtract.getStatus().equals("1")) {
                    returnInfo.setSuccess(false);
                    returnInfo.setRetcode(1);
                    returnInfo.setDescription("账户冻结，请联系客服");
                    return returnInfo;
                } else {
                    returnInfo = dbService.alipayTransFer(info.getMoney(), info.getUsername(), info.getAlipaynum());
                }
            }
        } catch (Exception e) {
            returnInfo.setSuccess(false);
            returnInfo.setRetcode(1);
            returnInfo.setDescription("系统异常，请联系客服");
            log.warn("用户提现异常", e);
        }
        return returnInfo;
    }

    //根据用户名查询余额
    @RequestMapping(value = "/selectMoney", method = RequestMethod.POST)
    @ResponseBody
    public ReturnInfo selectMoneyByUsername(@RequestBody AccExtractAccept info, HttpServletRequest req) {
        BigDecimal bigDecimal = dbService.selectMoneyByUsername(info.getUsername());
        ReturnInfo returnInfo = new ReturnInfo();
        returnInfo.setSuccess(true);
        returnInfo.setRetcode(0);
        returnInfo.setRetObj(bigDecimal);
        return returnInfo;
    }

    @RequestMapping(value = "/selectQuota", method = RequestMethod.GET)
    @ResponseBody
    public ReturnInfo selectQuota(HttpServletRequest req) {
        ReturnInfo returnInfo = dbService.selectQuota();
        return returnInfo;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ReturnInfo insert(@RequestBody AccExtract info, HttpServletRequest req) {
        try {
            dbService.insert(info);
            return ReturnInfo.Success;
        } catch (Exception e) {
            log.warn("  AccExtractCtrl insert error..", e);

        }
        return ReturnInfo.Faild;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnInfo update(@RequestBody AccExtract info, HttpServletRequest req) {
        try {
            dbService.updateByExample(info, dbService.getExample(info));
            return ReturnInfo.Success;
        } catch (Exception e) {
            log.warn("  AccExtractCtrl update error..", e);

        }
        return ReturnInfo.Faild;
    }


    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@RequestJsonParam(value = "query", required = false) QueryMapperBean info,
                      @RequestJsonParam(value = "fields", required = false) FieldsMapperBean fmb,
                      PageInfo para, HttpServletRequest req) {
        int totalCount = 0;
        List<HashMap<String, Object>> list = null;
        try {
            DbCondi dc = new DbCondi();
            dc.setEntityClass(AccExtract.class);
            dc.setKeyClass(AccExtractKey.class);
            dc.setQmb(info);
            dc.setPageinfo(para);
            dc.setFmb(fmb);
            dc.setTalbeName(getTableName());
            totalCount = dbService.getCount(dc);
            list = dbService.getData(dc);
        } catch (Exception e) {
            log.warn("  AccExtractCtrl get error..", e);

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
            List<AccExtract> list = new ArrayList<AccExtract>();
            for (String id : data) {
                AccExtract info = new AccExtract();
                KeyExplainHandler.explainKey(id, info);
                list.add(info);
            }
            dbService.batchDelete(list);
            return ReturnInfo.Success;
        } catch (Exception e) {
            log.warn("  AccExtractCtrl batchDelete error..", e);

        }
        return ReturnInfo.Faild;
    }

    @RequestMapping(value = "/batch", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnInfo batchUpdate(@RequestBody AccExtracts data, HttpServletRequest req) {
        try {
            dbService.batchUpdate(data);
            return ReturnInfo.Success;
        } catch (Exception e) {
            log.warn("  AccExtractCtrl batchUpdate error..", e);

        }
        return ReturnInfo.Faild;
    }

    @RequestMapping(value = "/batch", method = RequestMethod.POST)
    @ResponseBody
    public ReturnInfo batchInsert(@RequestBody AccExtracts data, HttpServletRequest req) {
        try {
            dbService.batchInsert(data);
            return ReturnInfo.Success;
        } catch (Exception e) {
            log.warn("  AccExtractCtrl batchInsert error..", e);

        }
        return ReturnInfo.Faild;
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.GET)
    @ResponseBody
    public ListInfo<AccExtract> get(@PathVariable String key, HttpServletRequest req) {
        int totalCount = 1;
        List<AccExtract> list = new ArrayList<AccExtract>();
        try {
            AccExtract info = new AccExtract();
            KeyExplainHandler.explainKey(key, info);
            list.add(dbService.selectByPrimaryKey(info));
        } catch (Exception e) {
            log.warn("  AccExtractCtrl get by key error..", e);
        }
        return new ListInfo<AccExtract>(totalCount, list, 0, 1);
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnInfo delete(@PathVariable String key, HttpServletRequest req) {
        try {
            AccExtract info = new AccExtract();
            KeyExplainHandler.explainKey(key, info);
            dbService.deleteByPrimaryKey(info);
            return ReturnInfo.Success;
        } catch (Exception e) {
            log.warn("  AccExtractCtrl delete by key error..", e);
        }
        return ReturnInfo.Faild;
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnInfo update(@PathVariable String key, @RequestBody AccExtract info, HttpServletRequest req) {
        try {
            AccExtract oldPojo = null;
            if (info != null) {
                KeyExplainHandler.explainKey(key, info);
                dbService.updateByPrimaryKey(info);
            }
            return ReturnInfo.Success;
        } catch (Exception e) {
            log.warn("  AccExtractCtrl update by key error..", e);
        }
        return ReturnInfo.Faild;
    }

    private String getControllerName() {
        return this.getClass().getSimpleName();
    }

    private String getTableName() {
        return "t_acc_extract";
    }

    @SuppressWarnings("serial")
    public static class AccExtracts extends ArrayList<AccExtract> {
        public AccExtracts() {
            super();
        }
    }
}
