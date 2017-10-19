package com.flypaas.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.Params;
import com.flypaas.service.ParamsService;
@Service
@Transactional
public class ParamsServiceImpl extends DaoCenter implements ParamsService {
	
	public List<Params> getParams(String paramsType) {
		return paramsDao.getParams(paramsType);
	}

	public List<Map<String, Object>> getEventType() {
		return paramsDao.getEventType();
	}

}
