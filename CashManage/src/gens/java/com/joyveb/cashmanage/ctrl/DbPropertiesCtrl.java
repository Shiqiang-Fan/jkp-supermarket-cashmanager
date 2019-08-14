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

import com.joyveb.cashmanage.entity.DbProperties;
import com.joyveb.cashmanage.entity.DbPropertiesKey;
import com.joyveb.cashmanage.entity.LogOperter;
import com.joyveb.cashmanage.service.DbPropertiesService;
import com.joyveb.cashmanage.service.LogOperterService;
import com.joyveb.cashmanage.utils.IDGenerator;
import com.joyveb.cashmanage.web.InitParm;
import com.joyveb.lbos.restful.common.Constans;
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
@RequestMapping("/dbProperties")
public class DbPropertiesCtrl {

    private @Resource
    DbPropertiesService dbService;
    @Resource(name = "initParm")
    private InitParm initParm;
    @Resource(name = "logOperterService")
    private LogOperterService logOperterService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ReturnInfo insert(@RequestBody DbProperties info, HttpServletRequest req) {
        try {
            dbService.insert(info);
            initParm.initDbp();
            LogOperter logOperter = new LogOperter();
            logOperter.setAction("1");
            logOperter.setObject("系统参数  " + getControllerName());
            logOperter.setOperDate(System.currentTimeMillis());
            logOperter.setUuid(IDGenerator.getInstance().generate());
            logOperter.setContentInfo("新增系统参数:" + "键:" + info.getThekey() + ",值:" + info.getValue() + ",描述:" + info.getDes());
            if (null != req.getSession().getAttribute(Constans.SESSION_USERNAME_KEY)) {
                logOperter.setUserName(req.getSession().getAttribute(Constans.SESSION_USERNAME_KEY).toString());
            }
            logOperterService.insert(logOperter);
            return ReturnInfo.Success;
        } catch (Exception e) {
            log.warn("  DbPropertiesCtrl insert error..", e);

        }
        return ReturnInfo.Faild;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnInfo update(@RequestBody DbProperties info, HttpServletRequest req) {
        try {
            DbProperties dbps = new DbProperties();
            DbPropertiesKey dbpsk = new DbPropertiesKey();
            dbpsk.setThekey(info.getThekey());
            dbps = dbService.selectByPrimaryKey(dbpsk);
            dbService.updateByExample(info, dbService.getExample(info));
            initParm.initDbp();
            String thekeyString = "";
            String valueString = "";
            String deString = "";
            if (!dbps.getThekey().equals(info.getThekey())) {
                thekeyString = "键由:" + dbps.getThekey() + "改为:" + info.getThekey();
            } else {
                thekeyString = "键" + info.getThekey();
            }
            if (!dbps.getValue().equals(info.getValue())) {
                valueString = "值由:" + dbps.getValue() + "改为:" + info.getValue();
            }
            if (!dbps.getDes().equals(info.getDes())) {
                deString = "描述由:" + dbps.getDes() + "改为:" + info.getDes();
            }
            LogOperter logOperter = new LogOperter();
            logOperter.setAction("2");
            logOperter.setObject("系统参数  " + getControllerName());
            logOperter.setOperDate(System.currentTimeMillis());
            logOperter.setUuid(IDGenerator.getInstance().generate());
            logOperter.setContentInfo("修改系统参数:" + thekeyString + valueString + deString);
            logOperter.setUserName(req.getSession().getAttribute(Constans.SESSION_USERNAME_KEY).toString());
            logOperterService.insert(logOperter);

            return ReturnInfo.Success;
        } catch (Exception e) {
            log.warn("  DbPropertiesCtrl update error..", e);

        }
        return ReturnInfo.Faild;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@RequestJsonParam(value = "query", required = false) QueryMapperBean info,
                      @RequestJsonParam(value = "fields", required = false) FieldsMapperBean fmb, PageInfo para, HttpServletRequest req) {
        int totalCount = 0;
        List<HashMap<String, Object>> list = null;
        try {
            DbCondi dc = new DbCondi();
            dc.setEntityClass(DbProperties.class);
            dc.setKeyClass(DbPropertiesKey.class);
            dc.setQmb(info);
            dc.setPageinfo(para);
            dc.setFmb(fmb);
            dc.setTalbeName(getTableName());
            totalCount = dbService.getCount(dc);
            list = dbService.getData(dc);
        } catch (Exception e) {
            log.warn("  DbPropertiesCtrl get error..", e);

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
            List<DbProperties> list = new ArrayList<DbProperties>();
            for (String id : data) {
                DbProperties info = new DbProperties();
                KeyExplainHandler.explainKey(id, info);
                list.add(info);
                LogOperter logOperter = new LogOperter();
                logOperter.setAction("3");
                logOperter.setObject("系统参数  " + getControllerName());
                logOperter.setOperDate(System.currentTimeMillis());
                logOperter.setUuid(IDGenerator.getInstance().generate());
                logOperter.setContentInfo("删除系统参数:" + "键:" + info.getThekey() + ",值:" + info.getValue() + ",描述:" + info.getDes());
                logOperter.setUserName(req.getSession().getAttribute(Constans.SESSION_USERNAME_KEY).toString());
                logOperterService.insert(logOperter);

            }
            dbService.batchDelete(list);
            initParm.initDbp();

            return ReturnInfo.Success;
        } catch (Exception e) {
            log.warn("  DbPropertiesCtrl batchDelete error..", e);

        }
        return ReturnInfo.Faild;
    }

    @RequestMapping(value = "/batch", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnInfo batchUpdate(@RequestBody DbPropertiess data, HttpServletRequest req) {
        try {
            dbService.batchUpdate(data);
            initParm.initDbp();
            LogOperter logOperter = new LogOperter();
            logOperter.setAction("2");
            logOperter.setObject("系统参数  " + getControllerName());
            logOperter.setOperDate(System.currentTimeMillis());
            logOperter.setUuid(IDGenerator.getInstance().generate());
            logOperter.setContentInfo("修改系统参数");
            logOperter.setUserName(req.getSession().getAttribute(Constans.SESSION_USERNAME_KEY).toString());
            logOperterService.insert(logOperter);
            return ReturnInfo.Success;
        } catch (Exception e) {
            log.warn("  DbPropertiesCtrl batchUpdate error..", e);
        }
        return ReturnInfo.Faild;
    }

    @RequestMapping(value = "/batch", method = RequestMethod.POST)
    @ResponseBody
    public ReturnInfo batchInsert(@RequestBody DbPropertiess data, HttpServletRequest req) {
        try {
            dbService.batchInsert(data);
            initParm.initDbp();
            LogOperter logOperter = new LogOperter();
            logOperter.setAction("1");
            logOperter.setObject("系统参数  " + getControllerName());
            logOperter.setOperDate(System.currentTimeMillis());
            logOperter.setUuid(IDGenerator.getInstance().generate());
            logOperter.setContentInfo("新增系统参数");
            logOperter.setUserName(req.getSession().getAttribute(Constans.SESSION_USERNAME_KEY).toString());
            logOperterService.insert(logOperter);
            return ReturnInfo.Success;
        } catch (Exception e) {
            log.warn("  DbPropertiesCtrl batchInsert error..", e);
        }
        return ReturnInfo.Faild;
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.GET)
    @ResponseBody
    public ListInfo<DbProperties> get(@PathVariable String key, HttpServletRequest req) {
        int totalCount = 1;
        List<DbProperties> list = new ArrayList<DbProperties>();
        try {
            DbProperties info = new DbProperties();
            KeyExplainHandler.explainKey(key, info);
            list.add(dbService.selectByPrimaryKey(info));
        } catch (Exception e) {
            log.warn("  DbPropertiesCtrl get by key error..", e);
        }
        return new ListInfo<DbProperties>(totalCount, list, 0, 1);
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnInfo delete(@PathVariable String key, HttpServletRequest req) {
        try {
            DbProperties getinfo = new DbProperties();
            DbProperties info = new DbProperties();
            KeyExplainHandler.explainKey(key, info);
            getinfo = dbService.selectByPrimaryKey(info);
            dbService.deleteByPrimaryKey(info);

            initParm.initDbp();
            LogOperter logOperter = new LogOperter();
            logOperter.setAction("3");
            logOperter.setObject("系统参数  " + getControllerName());
            logOperter.setOperDate(System.currentTimeMillis());
            logOperter.setUuid(IDGenerator.getInstance().generate());
            logOperter.setContentInfo("删除系统参数:" + "键:" + getinfo.getThekey() + ",值:" + getinfo.getValue() + ",描述:" + getinfo.getDes());
            logOperter.setUserName(req.getSession().getAttribute(Constans.SESSION_USERNAME_KEY).toString());
            logOperterService.insert(logOperter);
            return ReturnInfo.Success;
        } catch (Exception e) {
            log.warn("  DbPropertiesCtrl delete by key error..", e);
        }
        return ReturnInfo.Faild;
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnInfo update(@PathVariable String key, @RequestBody DbProperties info, HttpServletRequest req) {
        try {
            DbProperties dbps = new DbProperties();
            DbPropertiesKey dbpsk = new DbPropertiesKey();
            LogOperter logOperter = new LogOperter();
            String thekeyString = "";
            String valueString = "";
            String deString = "";
            if (info != null) {
                KeyExplainHandler.explainKey(key, info);
                dbpsk.setThekey(info.getThekey());
                dbps = dbService.selectByPrimaryKey(dbpsk);
                dbService.updateByPrimaryKey(info);
                if (!info.getThekey().equals(dbps.getThekey())) {
                    thekeyString = "键由:" + dbps.getThekey() + "改为:" + info.getThekey();
                } else {
                    thekeyString = "键" + info.getThekey();
                }
                if (!dbps.getValue().equals(info.getValue())) {
                    valueString = "值由:" + dbps.getValue() + "改为:" + info.getValue();
                }
                if (!dbps.getDes().equals(info.getDes())) {
                    deString = "描述由:" + dbps.getDes() + "改为:" + info.getDes();
                }
            }
            initParm.initDbp();
            logOperter.setAction("2");
            logOperter.setObject("系统参数  " + getControllerName());
            logOperter.setOperDate(System.currentTimeMillis());
            logOperter.setUuid(IDGenerator.getInstance().generate());
            logOperter.setContentInfo("修改系统参数:" + thekeyString + valueString + deString);
            Object attribute = req.getSession().getAttribute(Constans.SESSION_USERNAME_KEY);
            if (attribute != null) {
                logOperter.setUserName(attribute.toString());
            }
            logOperterService.insert(logOperter);
            return ReturnInfo.Success;
        } catch (Exception e) {
            log.warn("  DbPropertiesCtrl update by key error..", e);
        }
        return ReturnInfo.Faild;
    }

    private String getControllerName() {
        return this.getClass().getSimpleName();
    }

    private String getTableName() {
        return "t_manage_dbproperties";
    }

    @SuppressWarnings("serial")
    public static class DbPropertiess extends ArrayList<DbProperties> {
        public DbPropertiess() {
            super();
        }
    }
}
