package com.ucpaas.commonservice.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.dao.AllocUinDao;
import com.ucpaas.commonservice.service.AllocUinService;


@Service("allocUinService")
public class AllocUinServiceImpl implements AllocUinService {
	
	@Resource(name = "allocUinDao")
	private AllocUinDao allocUinDao;

	
	@Override
	public Integer selectUinBySectionId(int sectionId) throws Exception {
		return this.allocUinDao.selectUinBySectionId(sectionId);
	}

}
