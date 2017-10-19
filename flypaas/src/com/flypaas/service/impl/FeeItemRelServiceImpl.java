package com.flypaas.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.flypaas.daocenter.DaoCenter;
import com.flypaas.service.FeeItemRelService;
@Service
@Transactional
public class FeeItemRelServiceImpl extends DaoCenter implements FeeItemRelService {
	
	public List<Map<String,Object>> getFeeItemRelList() {
		return feeItemRelDao.getFeeItemRelList();
	}
}
