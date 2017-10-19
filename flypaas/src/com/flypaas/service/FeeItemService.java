package com.flypaas.service;

import java.util.List;
import java.util.Map;

import com.flypaas.entity.FeeItem;

public interface FeeItemService {

	public List<FeeItem> getFeeItemList(long id);
	
	public double getLowerCsm(Map<String, Object> map);
}
