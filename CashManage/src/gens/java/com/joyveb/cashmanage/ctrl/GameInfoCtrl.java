package com.joyveb.cashmanage.ctrl;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.joyveb.cashmanage.entity.GameInfo;
import com.joyveb.cashmanage.entity.GameInfoKey;
import com.joyveb.cashmanage.service.GameInfoService;
import com.joyveb.cashmanage.utils.CopyImg;
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
@RequestMapping("/gameInfo")
public class GameInfoCtrl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private @Resource
    GameInfoService dbService;
    @Resource(name = "initParm")
    private InitParm initParm;
    private @Resource
    CopyImg copyImg;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@RequestJsonParam(value = "query", required = false) QueryMapperBean info,
                      @RequestJsonParam(value = "fields", required = false) FieldsMapperBean fmb, PageInfo para, HttpServletRequest req) {
        int totalCount = 0;
        List<HashMap<String, Object>> list = null;
        try {
            DbCondi dc = new DbCondi();
            dc.setEntityClass(GameInfo.class);
            dc.setKeyClass(GameInfoKey.class);
            dc.setQmb(info);
            dc.setPageinfo(para);
            dc.setFmb(fmb);
            dc.setTalbeName(getTableName());
            totalCount = dbService.getCount(dc);
            list = dbService.getData(dc);
        } catch (Exception e) {
            log.warn("  GameInfoCtrl get error..", e);

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
            List<GameInfo> list = new ArrayList<GameInfo>();
            for (String id : data) {
                GameInfo info = new GameInfo();
                KeyExplainHandler.explainKey(id, info);
                list.add(info);
            }
            dbService.batchDelete(list);
            return ReturnInfo.Success;
        } catch (Exception e) {
            log.warn("  GameInfoCtrl batchDelete error..", e);

        }
        return ReturnInfo.Faild;
    }

    @RequestMapping(value = "/uploadDetailedpath/{detTime}", method = RequestMethod.POST)
    @ResponseBody
    public String upload(HttpServletRequest request, @PathVariable String detTime, @RequestParam("detailedpath") MultipartFile file, ModelMap modelMap) {

        String dicName = file.getOriginalFilename();
        if (dicName == null || "".equals(dicName)) {
            return "success";
        }
        // 获得项目的路径
        String sc = request.getSession().getServletContext().getRealPath(File.separator + "upload" + File.separator + "gamesImage") + File.separator;
        String diskName = initParm.getStringDbp("game.pic.disk", "D://cipan") + File.separator;
        //String diskName = "D://cipan";
        File targetFile = new File(sc, detTime + ".jpg");
        File diskdir = new File(diskName);
        File diskFile = new File(diskName, detTime + ".jpg");
        File scfile = new File(sc);
        log.debug("sc::" + sc);
        log.debug("diskName::" + diskName);
        // 再重新创建一个新的目录文件
        if (!scfile.exists()) {
            scfile.mkdirs();
        }
        // 保存
        try {
            diskFile.setWritable(true, false);
            targetFile.setWritable(true, false);
            file.transferTo(targetFile);
            if (!diskdir.exists()) {
                diskdir.mkdirs();
            }
            copyImg.copyFile(targetFile, diskFile);
        } catch (Exception e) {
            log.warn("copy file error", e);
        }
        return "success";
    }

    @RequestMapping(value = "/uploadPreviewpath/{preTime}", method = RequestMethod.POST)
    @ResponseBody
    public String upload2(HttpServletRequest request, @PathVariable String preTime, @RequestParam("previewpath") MultipartFile file, ModelMap modelMap) {
        String picName = file.getOriginalFilename();
        if (picName == null || "".equals(picName)) {
            return "success";
        }
        // 获得项目的路径
        String sc = request.getSession().getServletContext().getRealPath(File.separator + "upload" + File.separator + "gamesImage") + File.separator;
        String diskName = initParm.getStringDbp("game.pic.disk", "D://cipan") + File.separator;
        //String diskName = "D://cipan";
        File targetFile = new File(sc, preTime + ".jpg");
        File diskdir = new File(diskName);
        File diskFile = new File(diskName, preTime + ".jpg");
        File scfile = new File(sc);
        log.debug("sc::" + sc);
        log.debug("diskName::" + diskName);
        // 再重新创建一个新的目录文件
        if (!scfile.exists()) {
            scfile.mkdirs();
        }
        // 保存
        try {
            diskFile.setWritable(true, false);
            targetFile.setWritable(true, false);
            file.transferTo(targetFile);
            if (!diskdir.exists()) {
                diskdir.mkdirs();
            }
            copyImg.copyFile(targetFile, diskFile);
        } catch (Exception e) {
            log.warn("copy file error", e);
        }
        return "success";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ReturnInfo insert(@RequestBody GameInfo info, HttpServletRequest request) {
        try {
            String detailedpath = info.getDetailedpath();
            if ("".equals(detailedpath) || detailedpath == null) {
                info.setDetailedpath("upload" + File.separator + "gamesImage" + File.separator + "HYSB" + File.separator + "icon_default.png");
            } else {
                info.setDetailedpath("upload" + File.separator + "gamesImage" + File.separator + info.getDetailedpath());
            }
            String previewpath = info.getPreviewpath();
            if ("".equals(previewpath) || previewpath == null) {
                info.setPreviewpath("upload" + File.separator + "gamesImage" + File.separator + "HYSB" + File.separator + "icon_default.png");
            } else {
                info.setPreviewpath("upload" + File.separator + "gamesImage" + File.separator + info.getPreviewpath());
            }
            info.setGameprice(info.getGameprice() * 100);
            dbService.insert(info);
            initParm.initGame();
            return ReturnInfo.Success;
        } catch (Exception e) {
            log.warn("  GameInfoCtrl update error..", e);
        }
        return ReturnInfo.Faild;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnInfo update(@RequestBody GameInfo info, HttpServletRequest req) {
        try {
            if (info != null) {
                String previewpath = info.getPreviewpath();
                String detailedpath = info.getDetailedpath();
                if (!"".equals(detailedpath)) {
                    info.setDetailedpath("upload" + File.separator + "gamesImage" + File.separator + detailedpath);
                } else {
                    info.setDetailedpath("upload" + File.separator + "gamesImage" + File.separator + "HYSB" + File.separator + "icon_default.png");
                }
                if (!"".equals(previewpath)) {
                    info.setPreviewpath("upload" + File.separator + "gamesImage" + File.separator + previewpath);
                } else {
                    info.setPreviewpath("upload" + File.separator + "gamesImage" + File.separator + "HYSB" + File.separator + "icon_default.png");
                }
                info.setGameprice(info.getGameprice() * 100);
                dbService.updateByExample(info, dbService.getExample(info));

                initParm.initGame();
                return ReturnInfo.Success;
            }
        } catch (Exception e) {
            log.warn("  GameInfoCtrl update error..", e);

        }
        return ReturnInfo.Faild;
    }

    @SuppressWarnings({"rawtypes", "unchecked", "finally"})
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(HttpServletRequest req, HttpServletResponse rsp) {
        ResponseEntity en = null;
        try {
            String path1 = req.getParameter("filename");
            String path = URLDecoder.decode(StringUtils.trim(path1), "UTF-8");// 一次解码
            File file = new File(path);
            String fileName = file.getName();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 下载不同文件ContentTtype是不相同的
            fileName = URLEncoder.encode(fileName, "UTF-8");
            String fileinfo = new String(fileName.getBytes("gb2312"), "ISO8859-1");
            headers.setContentDispositionFormData("attachment", fileinfo);// 下载后文件名称
            en = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        } catch (IOException ex) {
            log.warn("GameInfo IO exception", ex);
        }
        return en;
    }

    @RequestMapping(value = "/batch", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnInfo batchUpdate(@RequestBody GameInfos data, HttpServletRequest req) {
        try {
            dbService.batchUpdate(data);
            return ReturnInfo.Success;
        } catch (Exception e) {
            log.warn("  GameInfoCtrl batchUpdate error..", e);
        }
        return ReturnInfo.Faild;
    }

    @RequestMapping(value = "/batch", method = RequestMethod.POST)
    @ResponseBody
    public ReturnInfo batchInsert(@RequestBody GameInfos data, HttpServletRequest req) {
        try {
            dbService.batchInsert(data);
            return ReturnInfo.Success;
        } catch (Exception e) {
            log.warn("  GameInfoCtrl batchInsert error..", e);
        }
        return ReturnInfo.Faild;
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.GET)
    @ResponseBody
    public ListInfo<GameInfo> get(@PathVariable String key, HttpServletRequest req) {
        int totalCount = 1;
        List<GameInfo> list = new ArrayList<GameInfo>();
        try {
            GameInfo info = new GameInfo();
            KeyExplainHandler.explainKey(key, info);
            list.add(dbService.selectByPrimaryKey(info));
        } catch (Exception e) {
            log.warn("  GameInfoCtrl get by key error..", e);
        }
        return new ListInfo<GameInfo>(totalCount, list, 0, 1);
    }

    @RequestMapping(value = "/gameCode/{key}", method = RequestMethod.GET)
    @ResponseBody
    public boolean findGameCode(@PathVariable String key, HttpServletRequest req) {
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        boolean mm = false;
        try {
            String sql = "select * from " + getTableName() + " where gamecode='" + key + "';";
            list.addAll(dbService.dosql(sql));
            if (list.size() == 0) {
                mm = false;
            } else {
                mm = true;
            }
        } catch (Exception e) {
            log.warn("  WhiteListCtrl get by key error..", e);
        }
        return mm;
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnInfo delete(@PathVariable String key, HttpServletRequest req) {
        try {
            GameInfo info = new GameInfo();
            KeyExplainHandler.explainKey(key, info);
            dbService.deleteByPrimaryKey(info);
            return ReturnInfo.Success;
        } catch (Exception e) {
            log.warn("  GameInfoCtrl delete by key error..", e);
        }
        return ReturnInfo.Faild;
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnInfo update(@PathVariable String key, @RequestBody GameInfo info, HttpServletRequest req) {
        try {
            if (info != null) {
                KeyExplainHandler.explainKey(key, info);
                String previewpath = info.getPreviewpath();
                String detailedpath = info.getDetailedpath();
                if (!"".equals(detailedpath)) {
                    info.setDetailedpath("upload/gamesImage/" + detailedpath);
                }
                if (!"".equals(previewpath)) {
                    info.setPreviewpath("upload/gamesImage/" + previewpath);
                }
                info.setGameprice(info.getGameprice() * 100);
                dbService.updateByPrimaryKey(info);
            }
            initParm.initGame();
            return ReturnInfo.Success;
        } catch (Exception e) {
            log.warn("  GameInfoCtrl update by key error..", e);
        }
        return ReturnInfo.Faild;
    }

    private String getControllerName() {
        return this.getClass().getSimpleName();
    }

    private String getTableName() {
        return "t_game_info";
    }

    @SuppressWarnings("serial")
    public static class GameInfos extends ArrayList<GameInfo> {
        public GameInfos() {
            super();
        }
    }

}
