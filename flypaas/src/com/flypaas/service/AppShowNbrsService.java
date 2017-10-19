package com.flypaas.service;


import com.flypaas.entity.AppShowNbrs;
import com.flypaas.entity.vo.PageContainer;

public interface AppShowNbrsService {
	
	public void add(AppShowNbrs nbrs);
	
	public void updateStatus(AppShowNbrs appShowNbrs);
	
	public AppShowNbrs get(AppShowNbrs appShowNbrs);
	
	public PageContainer getAppShowNbrs(PageContainer page);
}
