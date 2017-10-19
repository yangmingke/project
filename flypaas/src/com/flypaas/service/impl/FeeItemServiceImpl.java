package com.flypaas.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.FeeItem;
import com.flypaas.service.FeeItemService;
@Service
@Transactional
public class FeeItemServiceImpl extends DaoCenter implements FeeItemService {
	
	public List<FeeItem> getFeeItemList(long id) {
		 return feeItemDao.getFeeItemList(id);
	}

	public double getLowerCsm(Map<String, Object> map) {
		return feeItemDao.getLowerCsm(map);
	}
	
}
