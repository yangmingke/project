package com.flypaas.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.FeeItemDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.FeeItem;
@Repository
public class FeeItemDaoImpl extends MyBatisDao implements FeeItemDao {
	private static final String GETFEEITEMLIST="getFeeItemListById";
	private static final String GETLOWERCSM="getLowerCsm";
	public List<FeeItem> getFeeItemList(long id){
		return sqlSessionTemplate.selectList(GETFEEITEMLIST,id);
	}
	public double getLowerCsm(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne(GETLOWERCSM,map);
	}
}
