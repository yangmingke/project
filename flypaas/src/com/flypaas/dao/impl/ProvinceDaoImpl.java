package com.flypaas.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.ProvinceDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.Province;
@Repository
public class ProvinceDaoImpl extends MyBatisDao implements ProvinceDao {
	private static final String GETPRIVINCE="getProvince";
	private static final String GETPROINCEBYID="getProinceById";
	
	public List<Province> getProvince() {
		return sqlSessionTemplate.selectList(GETPRIVINCE);
	}

	public Province getProvinceByid(int id) {
		return sqlSessionTemplate.selectOne(GETPROINCEBYID, id);
	}

}
