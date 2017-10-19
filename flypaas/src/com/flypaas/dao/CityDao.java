package com.flypaas.dao;

import java.util.List;

import com.flypaas.entity.City;

public interface CityDao {

	public List<City> getCity(int provinceId);
	public City getCityById(int id);
}
