package com.flypaas.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.ParamsDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.Params;
@Repository
public class ParamsDaoImpl extends MyBatisDao implements ParamsDao {
	private static final String GETPARAMS="getIndustry" ;
	private static final String GETEVENTTYPE="getEventType" ;
	
	public List<Params> getParams(String paramsType) {
		return sqlSessionTemplate.selectList(GETPARAMS, paramsType);
	}

	public List<Map<String, Object>> getEventType() {
		return sqlSessionTemplate.selectList(GETEVENTTYPE);
	}

}
