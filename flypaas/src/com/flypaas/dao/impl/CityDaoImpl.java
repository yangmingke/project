package com.flypaas.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.CityDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.City;
@Repository
public class CityDaoImpl extends MyBatisDao implements CityDao {
	private static final String GETCITY="getCityByPid" ;
	private static final String GETCITYBYID="getCityId";
	
	public List<City> getCity(int provinceId) {
		return sqlSessionTemplate.selectList(GETCITY, provinceId);
	}

	public City getCityById(int id) {
		return sqlSessionTemplate.selectOne(GETCITYBYID, id);
	}

}
