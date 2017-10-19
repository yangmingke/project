package com.flypaas.dao;

import com.flypaas.entity.AppShowNbrs;
import com.flypaas.entity.vo.PageContainer;

public interface AppShowNbrsDao {

	public void add(AppShowNbrs nbrs);
	
	public void updateStatus(AppShowNbrs appShowNbrs);
	
	public PageContainer getAppShowNbrs(PageContainer page);
	
	public AppShowNbrs get(AppShowNbrs appShowNbrs);
}
