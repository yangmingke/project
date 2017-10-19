package com.flypaas.dao;

import java.util.List;
import java.util.Map;

import com.flypaas.entity.FeeItem;

public interface FeeItemDao {
	public List<FeeItem> getFeeItemList(long id);
	public double getLowerCsm(Map<String, Object> map);
}
