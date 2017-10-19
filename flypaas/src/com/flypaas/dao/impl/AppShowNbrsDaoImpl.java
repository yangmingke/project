package com.flypaas.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.AppShowNbrsDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.AppShowNbrs;
import com.flypaas.entity.vo.PageContainer;
@Repository
public class AppShowNbrsDaoImpl extends MyBatisDao implements AppShowNbrsDao {

	private static final String ADD="addAppShowNbrs";
	private static final String UPDATESTATUS="updateAppShowNbrsStatus";
	private static final String GET="getAppShowNbrs";
	private static final String GETCOUNT="getAppShowNbrCount";
	private static final String GETMODEL="getModel";
	public void add(AppShowNbrs nbrs) {
		sqlSessionTemplate.insert(ADD, nbrs);
	}
	public PageContainer getAppShowNbrs(PageContainer page) {
		PageContainer result = null;
		Map<String, Object> params = null;
		result = getSearchPage(GET, GETCOUNT, page);
		params = result.getParams();
		if (null == params) {
			params = new HashMap<String, Object>();
			result.setParams(params);
		}
		if (null == params.get("total")) {
			params.put("total", 0);
		}
		return result;
	}

	public void updateStatus(AppShowNbrs appShowNbrs) {
		sqlSessionTemplate.update(UPDATESTATUS, appShowNbrs);
	}

	public AppShowNbrs get(AppShowNbrs appShowNbrs) {
		return sqlSessionTemplate.selectOne(GETMODEL, appShowNbrs);
	}

}
