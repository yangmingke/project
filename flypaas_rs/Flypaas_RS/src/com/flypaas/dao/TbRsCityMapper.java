package com.flypaas.dao;

import java.util.List;

import com.flypaas.model.TbRsCity;

public interface TbRsCityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbRsCity record);

    int insertSelective(TbRsCity record);

    TbRsCity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbRsCity record);

    int updateByPrimaryKey(TbRsCity record);
    
    /**
     * 查询所有的洲
     * @return
     */
    public List<TbRsCity> queryAllCountry();
    
    /**
     * 查询某个国家下的省
     * @param cId
     * @return
     */
    public List<TbRsCity> queryProvinceByCountryId(int cId);
    
    
    /**
     * 查询某个国家是属于哪个洲的
     * @param cId
     * @return
     */
    public int queryContinentByCountry(int cId);
    
    /**
     * 查询某个城市是属于哪个片区的
     * @param cId
     * @return
     */
    public int queryAreaByCity(int cId);
    
    
    
    /**
     * 根据省份Id查找该省份下的市区
     * @param pId
     * @return
     */
    public List<TbRsCity> queryCityBypId(int pId);
    
    /**
     * 查询所有的城市
     * @return
     */
    public List<TbRsCity> queryAll();
    
    /**
  	 * 查询该大区所有的省份，动态显示在前台
  	 * @return
  	 */
  	public List<TbRsCity> queryProvinceByAreaId(Integer areaId);
  	
  	/**
     * 根据ID查询城市名称
     * @param city country
     * @return
     */
    public TbRsCity queryCityByCityId(String country, String city);
}