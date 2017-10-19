package com.flypaas.service;

import java.util.List;

import com.flypaas.entity.City;

public interface CityService {
	public List<City> getCity(int provinceId);
	public City getCityById(int id);
}
