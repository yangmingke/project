package com.flypaas.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.NewsDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.News;
import com.flypaas.entity.vo.PageContainer;
@Repository
public class NewsDaoImpl extends MyBatisDao implements NewsDao {

	private static final String GETRATELIST="getNewsList" ;
	private static final String GETNEWSLISTCOUNT="getNewsListCount" ;
	private static final String GETNEWSINFO="getNewsInfo" ;
	private static final String ADDVISTORMSG="addVistorMsg";
	
	public PageContainer newsList(PageContainer page) {
		PageContainer result = null;
		Map<String, Object> params = null;
		result = getSearchPage(GETRATELIST, GETNEWSLISTCOUNT, page);
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

	public News getNewsInfo(String newsId) {
		return sqlSessionTemplate.selectOne(GETNEWSINFO,newsId);
	}

	public void addVistorMsg(Map<String, Object> map) {
		sqlSessionTemplate.insert(ADDVISTORMSG, map);
	}

}
