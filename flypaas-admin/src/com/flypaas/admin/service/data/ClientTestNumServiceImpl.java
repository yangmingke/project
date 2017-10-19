package com.flypaas.admin.service.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.util.JsonUtils;
import com.flypaas.admin.util.file.BizException;
import com.flypaas.admin.util.rest.resp.Resp;

/**
 * 信息管理-测试号码
 * 
 * @author zenglb
 */
@Service
@Transactional
public class ClientTestNumServiceImpl implements ClientTestNumService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private MasterDao dao;

	@Override
	public PageContainer query(Map<String, Object> params) {
		PageContainer p = new PageContainer();
		Map<String, Object> m = dao.getOneInfo("clientTestNum.getUserBySid", params);
		if (null != m && null != m.get("sid")) {
			params.putAll(m);
			m = dao.getOneInfo("clientTestNum.getTestAppBySid", params);
			if (null != m && null != m.get("app_sid")) {
				params.putAll(m);
				List<Map<String, Object>> list = dao.getSearchList("clientTestNum.findTestAppClients", params);
				List<Map<String, Object>> list2 = dao.getSearchList("clientTestNum.findBindMobiles", params);
				int rownum = 1;
				for (Map<String, Object> map : list) {
					map.put("rownum", rownum++);
					map.putAll(params);
					for (Map<String, Object> m2 : list2) {
						if (map.get("client_number").equals(m2.get("clientnum"))) {
							map.put("test_id", m2.get("test_id"));
							map.put("nbr_id", m2.get("nbr_id"));
							break;
						}
					}
				}
				p.setList(list);
			}
		}
		return p;
	}

	@Override
	public Map<String, Object> bindTestNum(HashMap<String, Object> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			Map<String, Object> m = dao.getOneInfo("clientTestNum.getUserBySid", params);
			logger.info("测试号码绑定，开始请求通知rest");
			String json = com.flypaas.admin.util.rest.RestClient.bandClients((String) m.get("sid"),
					(String) m.get("token"), (String) params.get("app_sid"), (String) params.get("client_number"),
					(String) params.get("test_id"));
			Resp resp = JsonUtils.toObject(json, Resp.class);
			if ("000000".equals(resp.getResp().getRespCode())) {
				if (StringUtils.isBlank((String) params.get("nbr_id"))) {
					dao.update("clientTestNum.insertTestNum", params);
				} else {
					dao.update("clientTestNum.updateTestNum", params);
				}
				data.put("code", 0);
				data.put("msg", "操作成功");
			} else {
				data.put("code", -2);
				data.put("msg", "rest绑定失败:code=" + resp.getResp().getRespCode());
			}
		} catch (Throwable e) {
			logger.error("rest绑定失败," + e.getMessage(), e);
			throw new BizException(-2, "rest绑定失败");
		}
		return data;
	}

}
