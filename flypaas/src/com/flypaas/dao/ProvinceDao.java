package com.flypaas.dao;

import java.util.List;

import com.flypaas.entity.Province;

/** 
* TODO 省
* @author 29p9g02
* @version 
*/
public interface ProvinceDao {

	/**
	 * TODO 获取省份
	 * @author 29p9g02
	 * 2014-4-8
	 */
	public List<Province> getProvince();
	
	public Province getProvinceByid(int id);
}
