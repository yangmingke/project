package com.flypaas.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.CloudNoticeDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.NotifyCallFile;
import com.flypaas.entity.vo.PageContainer;
@Repository
public class CloudNoticeDaoImpl extends MyBatisDao implements CloudNoticeDao {
	
	private static final String GETCLOUDNTC="getCloudNtc";
	private static final String GETCLOUDCOUNTNTC="getCloudCountNtc";
	private static final String ADD="addCloudNtc";
	private static final String DELETE="delCloudNtc";

	public PageContainer getCloudNtc(PageContainer page) {
		PageContainer result = null;
		Map<String, Object> params = null;
		result = getSearchPage(GETCLOUDNTC, GETCLOUDCOUNTNTC, page);
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

	public void add(NotifyCallFile ncf) {
		sqlSessionTemplate.insert(ADD, ncf);
	}

	public void delete(NotifyCallFile ncf) {
		sqlSessionTemplate.update(DELETE, ncf);
	}

}
