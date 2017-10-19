package com.flypaas.admin.service.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.constant.DbConstant.TablePrefix;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.service.CommonService;

/**
 * 业务管理-即时消息
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class ImServiceImpl implements ImService {
	@Autowired
	private MasterDao dao;
	@Autowired
	private CommonService commonService;

	@Override
	public PageContainer query(Map<String, String> params) {
		PageContainer page = new PageContainer();
		List<String> data = commonService.getExistTable(TablePrefix.tb_im_log_, params.get("start_date"),
				params.get("end_date"));
		if (data.size() > 0) {
			Map<String, Object> p = new HashMap<String, Object>();
			p.putAll(params);
			p.put("table_list", data);
			page = dao.getSearchPage("im.query", "im.queryCount", p);
		}
		return page;
	}

	@Override
	public Map<String, Object> view(Map<String, String> params) {
		return dao.getOneInfo("im.view", params);
	}
}
