package com.flypaas.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.FeeItemRelDao;
import com.flypaas.daocenter.MyBatisDao;
@Repository
public class FeeItemRelDaoImpl extends MyBatisDao implements FeeItemRelDao {
	private static final String GETFEEITEMRELLIST="getFeeItemRelListM";
	public List<Map<String,Object>> getFeeItemRelList() {
		return sqlSessionTemplate.selectList(GETFEEITEMRELLIST);
	}

}
