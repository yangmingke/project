package com.flypaas.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.Province;
import com.flypaas.service.ProvinceService;
@Service
@Transactional
public class ProvinceServiceImpl extends DaoCenter implements ProvinceService {
	
	public List<Province> getProvince() {
		return provinceDao.getProvince();
	}
	public Province getProvinceByid(int id) {
		return provinceDao.getProvinceByid(id);
	}

}
