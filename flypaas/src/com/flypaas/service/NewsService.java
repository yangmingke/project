package com.flypaas.service;

import java.util.Map;

import com.flypaas.entity.News;
import com.flypaas.entity.vo.PageContainer;

public interface NewsService {
	public PageContainer newsList(PageContainer page);
	
	public News getNewsInfo(String newsId);
	
	public void addVistorMsg(Map<String, Object> map);
}
