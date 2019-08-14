package com.joyveb.cashmanage.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.joyveb.cashmanage.entity.AccWhitelistParent;
import com.joyveb.cashmanage.service.AccWhitelistParentService;
import com.joyveb.cashmanage.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyveb.cashmanage.entity.LogOperter;
import com.joyveb.cashmanage.entity.WhiteList;
import com.joyveb.cashmanage.entity.WhiteListKey;
import com.joyveb.cashmanage.service.LogOperterService;
import com.joyveb.cashmanage.service.WhiteListService;
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
@RequestMapping("/whiteList")
public class WhiteListCtrl {

    private @Resource
    WhiteListService dbService;
    @Resource(name = "initParm")
    private InitParm initParm;
    @Resource(name = "logOperterService")
    private LogOperterService logOperterService;
    @Resource(name = "accWhitelistParentService")
    private AccWhitelistParentService accWhitelistParentService;
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ReturnInfo insert(@RequestBody WhiteList info, HttpServletRequest req) {
        try {
            info.setUuid(IDGenerator.getInstance().generate());
            info.setUsetime(System.currentTimeMillis());
            dbService.insert(info);
            LogOperter logOperter = new LogOperter();
            logOperter.setAction("1");
            logOperter.setObject("代销商用户  " + getControllerName());
            logOperter.setOperDate(System.currentTimeMillis());
            logOperter.setUuid(IDGenerator.getInstance().generate());
            logOperter.setContentInfo("新增代销商用户:" + "姓名:" + info.getFullname() + ",用户名:" + info.getUsername());
            if (null != req.getSession().getAttribute(Constans.SESSION_USERNAME_KEY)) {
                logOperter.setUserName(req.getSession().getAttribute(Constans.SESSION_USERNAME_KEY).toString());
            }
            logOperterService.insert(logOperter);
            initParm.initWhiteList();
            return ReturnInfo.Success;
        }catch (DuplicateKeyException e){
            log.trace("主键冲突",e);
            return new ReturnInfo("添加失败,不可重复添加",false);
        } catch (Exception e) {
            log.warn("  WhiteListCtrl insert error..", e);
        }
        return ReturnInfo.Faild;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnInfo update(@RequestBody WhiteList info, HttpServletRequest req) {
        try {
            KeyExplainHandler.explainKey(info.getUuid(), info);
            WhiteList white = new WhiteList();
            WhiteListKey wlk = new WhiteListKey();
            wlk.setUuid(info.getUuid());
            white = dbService.selectByPrimaryKey(wlk);
            dbService.updateByExampleSelective(info, dbService.getExample(info));
            LogOperter logOperter = new LogOperter();
            logOperter.setAction("2");
            logOperter.setObject("代销商用户  " + getControllerName());
            logOperter.setOperDate(System.currentTimeMillis());
            logOperter.setUuid(IDGenerator.getInstance().generate());
            String nameString = "";
            String userString = "";
            String alipaynumString = "";
            String addersssString = "";
            String commissionString = "";
            String devicenumString = "";
            String devicetypeString = "";
            String transfercycle = "";
            String deposit = "";
            String idcardString = "";
            String mobilestString = "";
            if (!info.getUsername().equals(white.getUsername())) {
                nameString = "" + white.getUsername() + "改为:" + info.getUsername();
            } else {
                nameString = "" + white.getUsername();
            }
            userString = updateOperater(info.getUsername(), white.getUsername(), "用户名");
            alipaynumString = updateOperater(info.getAlipaynum(), white.getAlipaynum(), "支付宝账号");
            addersssString = updateOperater(info.getAddress(), white.getAddress(), "用户地址");
            deposit = updateOperater(info.getDeposit() + "", white.getDeposit() + "", "押金");
            transfercycle = updateOperater(info.getTransfercycle() + "", white.getTransfercycle() + "", "转账周期");
            commissionString = updateOperater(info.getCommission(), white.getCommission(), "佣金");
            devicenumString = updateOperater(info.getDevicenum(), white.getDevicenum(), "设备编号");
            devicetypeString = updateOperater(info.getDevicetype() + "", white.getDevicetype() + "", "设备类型");
            idcardString = updateOperater(info.getIdcard() + "", white.getIdcard() + "", "身份证号");
            mobilestString = updateOperater(info.getMobile(), white.getMobile(), "手机号");
            logOperter.setContentInfo("修改代销商用户:" + nameString + userString + alipaynumString + addersssString + idcardString + mobilestString +
                    deposit + transfercycle + commissionString + devicenumString + devicetypeString);
            logOperter.setUserName(req.getSession().getAttribute(Constans.SESSION_USERNAME_KEY).toString());
            logOperterService.insert(logOperter);
            initParm.initWhiteList();
            return ReturnInfo.Success;
        } catch (Exception e) {
            log.warn("  WhiteListCtrl update error..", e);
        }
        return ReturnInfo.Faild;
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@RequestJsonParam(value = "query", required = false) QueryMapperBean info,
                      @RequestJsonParam(value = "fields", required = false) FieldsMapperBean fmb,
                      PageInfo para, HttpServletRequest req) {
        List<HashMap<String, Object>> list = null;
        List<HashMap<String, Object>> list2 = new ArrayList<HashMap<String, Object>>();
        int totalCount = 0;
        try {
            DbCondi dc = new DbCondi();
            dc.setEntityClass(WhiteList.class);
            dc.setKeyClass(WhiteListKey.class);
            dc.setQmb(info);
            dc.setPageinfo(para);
            dc.setFmb(fmb);
            dc.setTalbeName(getTableName());
            list = dbService.getData(dc);
            String sql = "SELECT Count(*) as totalcount FROM t_acc_whitelist";// GROUP BY t_acc_whitelist.`STATUS` HAVING status = 0
            List<HashMap<String, Object>> total = dbService.dosql(sql);
            if (total != null && total.size() > 0) {
                totalCount = Integer.parseInt(total.get(0).get("totalcount") + "");
            }
            if (null != list && list.size() > 0) {
                int size = list.size();
                list2.clear();
                for (int i = 0; i < size; i++) {
                    HashMap<String, Object> hashMap = list.get(i);
                    String parentname = hashMap.get("parentname").toString();
                    if (!parentname.equals("defaultstair")) {
                        list2.add(hashMap);
                    }
                }
            }
        } catch (Exception e) {
            log.warn("  WhiteListCtrl get error..", e);
        }
        if (para.isPage()) {
            return new ListInfo<HashMap<String, Object>>(list2.size(), list2, para);
        } else {
            return list;
        }
    }

    @RequestMapping(value = "/stair", method = RequestMethod.GET)
    @ResponseBody
    public Object getstair(@RequestJsonParam(value = "query", required = false) QueryMapperBean info,
                      @RequestJsonParam(value = "fields", required = false) FieldsMapperBean fmb,
                      PageInfo para, HttpServletRequest req) {
        List<HashMap<String, Object>> list = null;
        List<HashMap<String, Object>> list2 = new ArrayList<HashMap<String, Object>>();
        int totalCount = 0;
        try {
            DbCondi dc = new DbCondi();
            dc.setEntityClass(WhiteList.class);
            dc.setKeyClass(WhiteListKey.class);
            dc.setQmb(info);
            dc.setPageinfo(para);
            dc.setFmb(fmb);
            dc.setTalbeName(getTableName());
            list = dbService.getData(dc);
            String sql = "SELECT Count(*) as totalcount FROM t_acc_whitelist";// GROUP BY t_acc_whitelist.`STATUS` HAVING status = 0
            List<HashMap<String, Object>> total = dbService.dosql(sql);
            if (total != null && total.size() > 0) {
                totalCount = Integer.parseInt(total.get(0).get("totalcount") + "");
            }
            if (null != list && list.size() > 0) {
                int size = list.size();
                list2.clear();
                for (int i = 0; i < size; i++) {
                    HashMap<String, Object> hashMap = list.get(i);
                    String parentname = hashMap.get("parentname").toString();
                    if (parentname.equals("defaultstair")) {
                        list2.add(hashMap);
                    }
                }
            }
        } catch (Exception e) {
            log.warn("  WhiteListCtrl get error..", e);
        }
        if (para.isPage()) {
            return new ListInfo<HashMap<String, Object>>(list2.size(), list2, para);
        } else {
            return list;
        }
    }

    @RequestMapping(value = "/batch/delete", method = RequestMethod.POST)
    @ResponseBody
    public ReturnInfo batchDelete(@RequestBody List<String> data, HttpServletRequest req) {
        try {
            for (String id : data) {
                WhiteListKey infoKey = new WhiteListKey();
                KeyExplainHandler.explainKey(id, infoKey);
                WhiteList info = dbService.selectByPrimaryKey(infoKey);
                info.setStatus("1");
                dbService.updateByPrimaryKey(info);

                LogOperter logOperter = new LogOperter();
                logOperter.setAction("3");
                logOperter.setObject("代销商用户  " + getControllerName());
                logOperter.setOperDate(System.currentTimeMillis());
                logOperter.setUuid(IDGenerator.getInstance().generate());
                logOperter.setContentInfo("删除代销商用户:" + "姓名:" + info.getFullname() + ",用户名:" + info.getUsername());
                logOperter.setUserName(req.getSession().getAttribute(Constans.SESSION_USERNAME_KEY).toString());
                logOperterService.insert(logOperter);
                initParm.initWhiteList();
                return ReturnInfo.Success;
            }
        } catch (Exception e) {
            log.warn("  WhiteListCtrl batchDelete error..", e);

        }
        return ReturnInfo.Faild;
    }

    @RequestMapping(value = "/batch", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnInfo batchUpdate(@RequestBody WhiteLists data, HttpServletRequest req) {
        try {
            dbService.batchUpdate(data);
            LogOperter logOperter = new LogOperter();
            logOperter.setAction("2");
            logOperter.setObject("代销商用户  " + getControllerName());
            logOperter.setOperDate(System.currentTimeMillis());
            logOperter.setUuid(IDGenerator.getInstance().generate());
            logOperter.setContentInfo("修改代销商用户");
            logOperter.setUserName(req.getSession().getAttribute(Constans.SESSION_USERNAME_KEY).toString());
            logOperterService.insert(logOperter);
            initParm.initWhiteList();
            return ReturnInfo.Success;
        } catch (Exception e) {
            log.warn("  WhiteListCtrl batchUpdate error..", e);
        }
        return ReturnInfo.Faild;
    }

    @RequestMapping(value = "/batch", method = RequestMethod.POST)
    @ResponseBody
    public ReturnInfo batchInsert(@RequestBody WhiteLists data, HttpServletRequest req) {
        try {
            dbService.batchInsert(data);
            LogOperter logOperter = new LogOperter();
            logOperter.setAction("1");
            logOperter.setObject("代销商用户  " + getControllerName());
            logOperter.setOperDate(System.currentTimeMillis());
            logOperter.setUuid(IDGenerator.getInstance().generate());
            logOperter.setContentInfo("新增代销商用户");
            logOperter.setUserName(req.getSession().getAttribute(Constans.SESSION_USERNAME_KEY).toString());
            logOperterService.insert(logOperter);
            return ReturnInfo.Success;
        } catch (Exception e) {
            log.warn("  WhiteListCtrl batchInsert error..", e);
        }
        return ReturnInfo.Faild;
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.GET)
    @ResponseBody
    public ListInfo<WhiteList> get(@PathVariable String key, HttpServletRequest req) {
        List<WhiteList> list = new ArrayList<WhiteList>();
        List<WhiteList> list2 = new ArrayList<WhiteList>();
        try {
            WhiteList info = new WhiteList();
            KeyExplainHandler.explainKey(key, info);
            list.add(dbService.selectByPrimaryKey(info));
            int size = list.size();
            for (int i = 0; i < size; i++) {
                WhiteList whiteList = list.get(i);
               // if (whiteList.getStatus().equals("0")) {
                    list2.add(whiteList);
                //}
            }
        } catch (Exception e) {
            log.warn("  WhiteListCtrl get by key error..", e);
        }
        return new ListInfo<WhiteList>(list2.size(), list2, 0, 1);
    }

    @RequestMapping(value = "/id/{key}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean findById(@PathVariable String key, HttpServletRequest req) {
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        Boolean tag = null;
        try {
            String sql = "SELECT * FROM t_acc_whitelist WHERE "
                    + " USERNAME=" + key + ";";
            list = dbService.dosql(sql);
            if (list.size() > 0) {
                tag = true;
            } else {
                tag = false;
            }
        } catch (Exception e) {
            log.warn("  WhiteListCtrl get by key error..", e);
        }
        return tag;
    }

    @RequestMapping(value = "/commission/{key}", method = RequestMethod.GET)
    @ResponseBody
    public String findCommission(@PathVariable String key, HttpServletRequest req) {
        String hh = "";
        try {
            hh = initParm.getStringDbp("cash.commission", "3");
        } catch (Exception e) {
            log.warn("  WhiteListCtrl get by key error..", e);
        }
        return hh;
    }
    @RequestMapping(value = "/commission/maxCommi/{key}", method = RequestMethod.GET)
    @ResponseBody
    public String findMaxCommission(@PathVariable String key, HttpServletRequest req) {
        String hh = "0";
        try {
            List<HashMap<String, Object>> dosql = dbService.dosql("SELECT max(COMMISSION) commission from t_acc_whitelist where PARENTNAME = '" + key + "'");
            if(CommonUtils.isNotEmpty(dosql)){
                hh = (String) dosql.get(0).get("commission");
            }
        } catch (Exception e) {
            log.warn("  WhiteListCtrl get by key error..", e);
        }
        return hh;
    }
    @RequestMapping(value = "/selectCommission/{username}", method = RequestMethod.GET)
    @ResponseBody
    public Integer selectCommission(@PathVariable String username, HttpServletRequest req) {
        int hh = 0;
        try {
            int intDbp = initParm.getIntDbp("cash.commission", 9);
            AccWhitelistParent accWhitelistParent = new AccWhitelistParent();
            accWhitelistParent.setUsername(username);
            AccWhitelistParent accWhitelistParent1 = accWhitelistParentService.selectByPrimaryKey(accWhitelistParent);
            String commission1 = accWhitelistParent1.getCommission();
            hh = intDbp - Integer.parseInt(commission1);
        } catch (Exception e) {
            log.warn("  WhiteListCtrl select Commission error..", e);
        }
        return hh;
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnInfo delete(@PathVariable String key, HttpServletRequest req) {
        try {
            WhiteListKey infoKey = new WhiteListKey();
            KeyExplainHandler.explainKey(key, infoKey);
            WhiteList info = dbService.selectByPrimaryKey(infoKey);
            info.setStatus("1");
            dbService.updateByPrimaryKey(info);
            LogOperter logOperter = new LogOperter();
            logOperter.setAction("3");
            logOperter.setObject("代销商用户  " + getControllerName());
            logOperter.setOperDate(System.currentTimeMillis());
            logOperter.setUuid(IDGenerator.getInstance().generate());
            logOperter.setContentInfo("删除代销商用户:" + "姓名:" + info.getFullname() + ",用户名:" + info.getUsername());
            Object username = req.getSession().getAttribute(Constans.SESSION_USERNAME_KEY);
            if (null != username) {
                logOperter.setUserName(req.getSession().getAttribute(Constans.SESSION_USERNAME_KEY).toString());
            }
            initParm.initWhiteList();
            logOperterService.insert(logOperter);
            return ReturnInfo.Success;
        } catch (Exception e) {
            log.warn("  WhiteListCtrl delete by key error..", e);
        }
        return ReturnInfo.Faild;
    }

    @RequestMapping(value = "/Max/{key}", method = RequestMethod.GET)
    @ResponseBody
    public Object getMax(@PathVariable String key, HttpServletRequest req) {
        List<Object> maxNum = new ArrayList<Object>();

        try {
            String sql = "SELECT Max(t_acc_whitelist.USERNAME) as maxnum "
                    + "FROM t_acc_whitelist "
                    + ";";
            List<HashMap<String, Object>> maxNumList = dbService.dosql(sql);
            if (CommonUtils.isNotEmpty(maxNumList)&&null!=maxNumList.get(0)) {
                int mm = Integer.parseInt((String) maxNumList.get(0).get("maxnum")) + 1;
                maxNum.add(mm);
            } else {
                maxNum.add(1000);
            }
        } catch (Exception e) {
            log.warn("  WhiteListCtrl getMax by key error..", e);
        }
        return maxNum;
    }

    @RequestMapping(value = "/Reset/{key}", method = RequestMethod.GET)
    @ResponseBody
    public void Reset(@PathVariable String key, HttpServletRequest req) {
        try {
            String sql = "update t_acc_whitelist set password='0000' where uuid ='" + key + "';";
            dbService.dosql(sql);
            initParm.initWhiteList();
            WhiteList wlt = new WhiteList();
            WhiteListKey wlk = new WhiteListKey();
            wlk.setUuid(key);
            wlt = dbService.selectByPrimaryKey(wlk);
            String username = wlt.getUsername();
            String name = wlt.getFullname();
            LogOperter logOperter = new LogOperter();
            logOperter.setAction("2");
            logOperter.setObject("代销商用户  " + getControllerName());
            logOperter.setOperDate(System.currentTimeMillis());
            logOperter.setUuid(IDGenerator.getInstance().generate());
            if (null != username && null != name) {
                logOperter.setContentInfo("代销商用户:用户名：" + username + "姓名：" + name + "重置密码");
            } else {
                logOperter.setContentInfo("代销商用户:" + "重置密码");
            }
            Object attribute = req.getSession().getAttribute(Constans.SESSION_USERNAME_KEY);
            if (attribute != null) {
                logOperter.setUserName(attribute.toString());
            }
            logOperterService.insert(logOperter);
        } catch (Exception e) {
            log.warn("  WhiteListCtrl delete by key error..", e);
        }
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnInfo update(@PathVariable String key, @RequestBody WhiteList info, HttpServletRequest req) {
        try {
            WhiteList white = new WhiteList();
            if (info != null) {
                KeyExplainHandler.explainKey(key, info);
                WhiteListKey wlk = new WhiteListKey();
                wlk.setUuid(info.getUuid());
                white = dbService.selectByPrimaryKey(wlk);
                dbService.updateByPrimaryKeySelective(info);
                LogOperter logOperter = new LogOperter();
                logOperter.setAction("2");
                logOperter.setObject("代销商用户  " + getControllerName());
                logOperter.setOperDate(System.currentTimeMillis());
                logOperter.setUuid(IDGenerator.getInstance().generate());
                String nameString = "";
                String userString = "";
                String alipaynumString = "";
                String addersssString = "";
                String commissionString = "";
                String devicenumString = "";
                String devicetypeString = "";
                String idcardString = "";
                String transfercycle = "";
                String deposit = "";
                String mobilestString = "";
                if (!info.getUsername().equals(white.getUsername())) {
                    nameString = "" + white.getUsername() + "改为:" + info.getUsername();
                } else {
                    nameString = "" + white.getUsername();
                }
                userString = updateOperater(info.getUsername(), white.getUsername(), "用户名");
                alipaynumString = updateOperater(info.getAlipaynum(), white.getAlipaynum(), "支付宝账号");
                addersssString = updateOperater(info.getAddress(), white.getAddress(), "用户地址");
                deposit = updateOperater(info.getDeposit() + "", white.getDeposit() + "", "押金");
                transfercycle = updateOperater(info.getTransfercycle() + "", white.getTransfercycle() + "", "转账周期");
                commissionString = updateOperater(info.getCommission(), white.getCommission(), "佣金");
                devicenumString = updateOperater(info.getDevicenum(), white.getDevicenum(), "设备编号");
                devicetypeString = updateOperater(info.getDevicetype() + "", white.getDevicetype() + "", "设备类型");
                idcardString = updateOperater(info.getIdcard() + "", white.getIdcard() + "", "身份证号");
                mobilestString = updateOperater(info.getMobile(), white.getMobile(), "手机号");
                logOperter.setContentInfo("修改代销商用户:" + nameString + userString + alipaynumString + addersssString + idcardString + mobilestString +
                        deposit + transfercycle + commissionString + devicenumString + devicetypeString);
                Object attribute = req.getSession().getAttribute(Constans.SESSION_USERNAME_KEY);
                if (attribute != null) {
                    logOperter.setUserName(attribute.toString());
                }
                logOperterService.insert(logOperter);
                initParm.initWhiteList();
            }
            return ReturnInfo.Success;
        } catch (Exception e) {
            log.warn("  WhiteListCtrl update by key error..", e);
        }
        return ReturnInfo.Faild;
    }

    private String getControllerName() {
        return this.getClass().getSimpleName();
    }

    private String getTableName() {
        return "t_acc_whitelist";
    }

    private String updateOperater(String info, String white, String chineseStr) {
        if (!info.equals(white)) {
            return ";" + chineseStr + ":" + white + "改为:" + info;
        }
        return null;

    }

    @SuppressWarnings("serial")
    public static class WhiteLists extends ArrayList<WhiteList> {
        public WhiteLists() {
            super();
        }
    }
}
