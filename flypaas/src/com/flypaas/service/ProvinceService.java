package com.flypaas.service;

import java.util.List;

import com.flypaas.entity.Province;

public interface ProvinceService {
	public List<Province> getProvince();
	public Province getProvinceByid(int id);
}
