package com.flypaas.admin.service.data;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.constant.LogConstant.LogType;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.service.LogService;
import com.flypaas.admin.service.TagService;
import com.flypaas.admin.util.ConfigUtils;
import com.flypaas.admin.util.file.FileUtil;

/**
 * 信息管理-版本管理
 * 
 * @author zenglb
 */
@Service
@Transactional
public class VersionServiceImpl implements VersionService {
	@Autowired
	private MasterDao dao;
	@Autowired
	private LogService logService;
	@Autowired
	private MsgService msgService;
	@Autowired
	private TagService tagService;
	
	private String base_path;

	@Override
	public PageContainer query(Map<String, String> params) {
		return dao.getSearchPage("version.query", "version.queryCount", params);
	}

	@Override
	public Map<String, Object> updateStatus(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		if("2".equals(params.get("version_status"))){
			dao.update("version.updateStatusCurrStatus", params);// 重置当前版本的状态
		}
		int i = dao.update("version.updateStatus", params);// 修改
		if (i > 0) {
			data.put("result", "success");
			data.put("msg", "操作成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "版本不存在或原始状态不对，操作失败");
		}

		logService.add(LogType.update, "信息管理-版本管理：版本状态变更", params, data);
		return data;
	}

	@Override
	public Map<String, Object> view(Integer id) {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> entity = dao.getOneInfo("version.getEntity", id);// 获取应用
		if (entity != null) {
			data.put("entity", entity);
		}
		return data;
	}

	@Override
	public Map<String, Object> save(Integer id, File version_file, String version_name_key, Map<String, Object> params){
		Map<String, Object> data = new HashMap<String, Object>();
		if (dao.getSearchSize("version.check", params) > 0) {// 查重
			data.put("result", "fail");
			data.put("msg", "此版本已配置，请重新编辑");
			return data;
		}
		
		List<Map<String, Object>> lst = tagService.getTagDataForDictionary("version_name", new HashMap<String, Object>());
		String version_name = null;
		Integer vnk = Integer.valueOf(version_name_key);
		for (Map<String, Object> map : lst) {
			if(version_name_key.equals(map.get("value"))){
				version_name = (String)map.get("text");
				break;
			}
		}
		int version_type = vnk / 10;
		params.put("version_type", version_type);
		params.put("version_name", version_name.replace("（开源）", "").replace("（安装）", ""));
		if(version_file!=null){
			String fileName ="UCS_" + version_name + "_V"+params.get("version_index") + "."+params.get("version_fileContentType");
			if(null == base_path){
				base_path = ConfigUtils.version_base_path +"/";
			}
			fileName = fileName.replace(" ", "_");
			fileName = fileName.replace("（开源）", "").replace("（安装）", "");
			String fn = FileUtil.upload(version_file, base_path + params.get("version_index")  + "/", fileName);
			params.put("version_path",fn);
		}
		if (id == null) {// 添加渠道
			int i = dao.insert("version.insert", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "添加成功");

			} else {
				data.put("result", "fail");
				data.put("msg", "添加失败");
			}
			logService.add(LogType.add, "信息管理-版本管理：添加版本", params, data);

		} else {// 修改渠道
			int i = dao.update("version.update", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "修改成功");

			} else {
				data.put("result", "fail");
				data.put("msg", "版本不存在，修改失败");
			}
			logService.add(LogType.update, "信息管理-版本管理：修改版本", params, data);
		}
		return data;
	}

}
