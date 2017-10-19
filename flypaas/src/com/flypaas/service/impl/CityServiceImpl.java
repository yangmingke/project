package com.flypaas.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.City;
import com.flypaas.service.CityService;
@Service
@Transactional
public class CityServiceImpl extends DaoCenter implements CityService {
	
	public List<City> getCity(int provinceId) {
		return cityDao.getCity(provinceId);
	}
	public City getCityById(int id){
		return cityDao.getCityById(id);
	}

}
