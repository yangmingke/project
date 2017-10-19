package com.flypaas.service;

import java.util.List;
import java.util.Map;

import com.flypaas.entity.Params;

public interface ParamsService {

	public List<Params> getParams(String paramsType);
	
	public List<Map<String, Object>> getEventType();
}
