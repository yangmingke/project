package com.flypaas.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.EventBalanceDao;
import com.flypaas.daocenter.MyBatisDao;
@Repository
public class EventBalanceDaoImpl extends MyBatisDao implements EventBalanceDao {
	
	private static final String add="addEventBalance";
	private static final String update="updateEventBalance";
	private static final String get="getEventBalanceCount";
	private static final String getBySid="getEventBalanceBySid";
	public void add(Map<String, Object> map){
		sqlSessionTemplate.insert(add, map);
	}
	public void update(Map<String, Object> map) {
		sqlSessionTemplate.update(update, map);
	}
	public int get(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne(get,map);
	}
	public List<Map<String, Object>> getEventBalanceBySid(String sid) {
		return sqlSessionTemplate.selectList(getBySid,sid);
	}
}
