package com.flypaas.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.News;
import com.flypaas.entity.vo.PageContainer;
import com.flypaas.service.NewsService;
@Service
@Transactional
public class NewsServiceImpl extends DaoCenter implements NewsService {

	public PageContainer newsList(PageContainer page) {
		return newsDao.newsList(page);
	}

	public News getNewsInfo(String newsId) {
		return newsDao.getNewsInfo(newsId);
	}
	
	public void addVistorMsg(Map<String, Object> map){
		newsDao.addVistorMsg(map);
	}

}
