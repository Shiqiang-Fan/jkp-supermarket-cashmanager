package com.joyveb.cashmanage.ctrl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.joyveb.cashmanage.entity.Version;
import com.joyveb.cashmanage.entity.VersionExample;
import com.joyveb.cashmanage.entity.VersionKey;
import com.joyveb.cashmanage.service.VersionService;
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
@RequestMapping("/version")
public class VersionCtrl {

	private @Resource
	VersionService dbService;
	@Resource(name = "initParm")
	private InitParm initParm;
	private @Resource
	CopyImg copyImg;
	private @Resource
	String pathName;

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public ReturnInfo insert(@RequestBody Version info, HttpServletRequest req) {
		try {
			info.setPubtime(System.currentTimeMillis());
			String path = info.getApkpath();
			path = path.substring(path.lastIndexOf("\\") + 1);
			info.setUrl("upload" + File.separator + "gamesImage" + File.separator + path);
			dbService.insert(info);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  VersionCtrl insert error..", e);
		}
		return ReturnInfo.Faild;
	}

	@RequestMapping(value = "/Upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(HttpServletRequest request, @RequestParam("apkpath") MultipartFile file, ModelMap modelMap) {
		String fileName = null;
		fileName = file.getOriginalFilename();
		pathName = fileName;
		if (fileName == null || "".equals(fileName)) {
			return "success";
		}
		// 获得项目的路径
		String sc = request.getSession().getServletContext().getRealPath(File.separator + "upload" + File.separator + "gamesImage") + File.separator;
		String diskName = initParm.getStringDbp("game.pic.disk", "") + File.separator;
		File targetFile = new File(sc, fileName);
		File diskdir = new File(diskName);
		File scfile = new File(sc);
		File diskFile = new File(diskName, fileName);
		// 创建一个目录文件
		if (!scfile.exists()) {
			scfile.mkdirs();
		}
		if (!diskFile.exists()) {
			scfile.mkdirs();
		}
		// 保存
		try {
			diskFile.setWritable(true, false);
			targetFile.setWritable(true, false);
			log.debug("路径===" + targetFile.toString());
			file.transferTo(targetFile);
			if (!diskdir.exists()) {
				diskdir.mkdirs();
			}
			copyImg.copyFile(targetFile, diskFile);
		} catch (Exception e) {
			log.debug("VersionCtrl upload " + e);
		}
		return "success";
	}

	@RequestMapping(value = "/vol", method = RequestMethod.POST)
	@ResponseBody
	public boolean findVol(@RequestBody String key, HttpServletRequest req) {
		Version version;
		boolean hh = false;
		try {
			Integer keyInteger = Integer.parseInt(key);
			VersionKey vikKey = new VersionKey();
			vikKey.setVol(keyInteger);
			version = dbService.selectByPrimaryKey(vikKey);
			if (version == null) {
				hh = true;
			} else {
				hh = false;
			}
		} catch (Exception e) {
			log.warn("  VersionCtrl findVol by key error..", e);
		}
		return hh;
	}

	@RequestMapping(value = "/Version", method = RequestMethod.POST)
	@ResponseBody
	public boolean findVersion(@RequestBody String key, HttpServletRequest req) {
		List<Version> version;
		boolean hh = false;
		try {
			Version info = new Version();
			info.setVersion(key);
			VersionExample vep = dbService.getExample(info);
			version = dbService.selectByExample(vep);
			if (version != null && !version.isEmpty()) {
				hh = false;
			} else {
				hh = true;
			}
		} catch (Exception e) {
			log.warn("  VersionCtrl findVersion by key error..", e);
		}
		return hh;
	}

	@RequestMapping(value = "/url", method = RequestMethod.POST)
	@ResponseBody
	public boolean findUrl(@RequestBody String key, HttpServletRequest req) {
		boolean hh = false;
		try {
			String sc = req.getSession().getServletContext().getRealPath(File.separator + "upload" + File.separator + "gamesImage") + File.separator;
			File pathFile = new File(sc + key);
			if (pathFile.exists()) {
				hh = false;
			} else {
				hh = true;
			}
		} catch (Exception e) {
			log.warn("  VersionCtrl findUrl by key error..", e);
		}
		return hh;
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@RequestBody Version info, HttpServletRequest req) {
		try {
			String path = info.getApkpath();
			path = path.substring(path.lastIndexOf("\\") + 1);
			if (!"".equals(path)) {
				info.setUrl("upload" + File.separator + "gamesImage" + File.separator + path);
			} else {
				info.setUrl(null);
			}
			info.setApkpath(null);
			dbService.updateByExampleSelective(info, dbService.getExample(info));
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  VersionCtrl update error..", e);

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
			dc.setEntityClass(Version.class);
			dc.setKeyClass(VersionKey.class);
			dc.setQmb(info);
			dc.setPageinfo(para);
			dc.setFmb(fmb);
			dc.setTalbeName(getTableName());
			totalCount = dbService.getCount(dc);
			list = dbService.getData(dc);
		} catch (Exception e) {
			log.warn("  VersionCtrl get error..", e);

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
			List<Version> list = new ArrayList<Version>();
			for (String id : data) {
				Version info = new Version();
				KeyExplainHandler.explainKey(id, info);
				list.add(info);
			}
			dbService.batchDelete(list);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  VersionCtrl batchDelete error..", e);

		}
		return ReturnInfo.Faild;
	}

	@RequestMapping(value = "/batch", method = RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo batchUpdate(@RequestBody Versions data, HttpServletRequest req) {
		try {
			dbService.batchUpdate(data);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  VersionCtrl batchUpdate error..", e);
		}
		return ReturnInfo.Faild;
	}

	@RequestMapping(value = "/batch", method = RequestMethod.POST)
	@ResponseBody
	public ReturnInfo batchInsert(@RequestBody Versions data, HttpServletRequest req) {
		try {
			dbService.batchInsert(data);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  VersionCtrl batchInsert error..", e);
		}
		return ReturnInfo.Faild;
	}

	@RequestMapping(value = "/{key}", method = RequestMethod.GET)
	@ResponseBody
	public ListInfo<Version> get(@PathVariable String key, HttpServletRequest req) {
		int totalCount = 1;
		List<Version> list = new ArrayList<Version>();
		try {
			Version info = new Version();
			KeyExplainHandler.explainKey(key, info);
			list.add(dbService.selectByPrimaryKey(info));
		} catch (Exception e) {
			log.warn("  VersionCtrl get by key error..", e);
		}
		return new ListInfo<Version>(totalCount, list, 0, 1);
	}

	@RequestMapping(value = "/{key}", method = RequestMethod.DELETE)
	@ResponseBody
	public ReturnInfo delete(@PathVariable String key, HttpServletRequest req) {
		try {
			Version info = new Version();
			KeyExplainHandler.explainKey(key, info);
			dbService.deleteByPrimaryKey(info);
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  VersionCtrl delete by key error..", e);
		}
		return ReturnInfo.Faild;
	}

	@RequestMapping(value = "/{key}", method = RequestMethod.PUT)
	@ResponseBody
	public ReturnInfo update(@PathVariable String key, @RequestBody Version info, HttpServletRequest req) {
		try {
			if (info != null) {
				KeyExplainHandler.explainKey(key, info);
				String path = info.getApkpath();
				path = path.substring(path.lastIndexOf("\\") + 1);
				if (!"".equals(path)) {
					info.setUrl("upload" + File.separator + "gamesImage" + File.separator + path);
				} else {
					info.setUrl(null);
				}
				info.setApkpath(null);
				dbService.updateByPrimaryKeySelective(info);
			}
			return ReturnInfo.Success;
		} catch (Exception e) {
			log.warn("  VersionCtrl update by key error..", e);
		}
		return ReturnInfo.Faild;
	}

	private String getControllerName() {
		return this.getClass().getSimpleName();
	}

	private String getTableName() {
		return "t_app_version";
	}

	@SuppressWarnings("serial")
	public static class Versions extends ArrayList<Version> {
		public Versions() {
			super();
		}
	}
}
