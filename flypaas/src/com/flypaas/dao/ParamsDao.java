package com.flypaas.dao;

import java.util.List;
import java.util.Map;

import com.flypaas.entity.Params;

public interface ParamsDao {
	
	public List<Params> getParams(String paramsType);
	
	public List<Map<String, Object>> getEventType();
}
