package com.flypaas.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.AppShowNbrs;
import com.flypaas.entity.vo.PageContainer;
import com.flypaas.service.AppShowNbrsService;
@Service
@Transactional
public class AppShowNbrsServiceImpl extends DaoCenter implements AppShowNbrsService {
	
	public void add(AppShowNbrs nbrs){
		appShowNbrsDao.add(nbrs);
	}

	public PageContainer getAppShowNbrs(PageContainer page) {
		return appShowNbrsDao.getAppShowNbrs(page);
	}

	public void updateStatus(AppShowNbrs appShowNbrs) {
		appShowNbrsDao.updateStatus(appShowNbrs);
	}

	public AppShowNbrs get(AppShowNbrs appShowNbrs) {
		return appShowNbrsDao.get(appShowNbrs);
	}
}
