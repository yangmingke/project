package com.flypaas.dao;

import java.util.List;

import com.flypaas.model.TbRsProvince;

public interface TbRsProvinceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbRsProvince record);

    int insertSelective(TbRsProvince record);

    TbRsProvince selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbRsProvince record);

    int updateByPrimaryKey(TbRsProvince record);
    
    /**
  	 * 查询所有的省份，动态显示在前台
  	 * @return
  	 */
  	public List<TbRsProvince> queryAllProvince();
  	
  	/**
  	 * 查询该大区所有的省份，动态显示在前台
  	 * @return
  	 */
  	public List<TbRsProvince> queryProvinceByAreaId(Integer areaId);
}