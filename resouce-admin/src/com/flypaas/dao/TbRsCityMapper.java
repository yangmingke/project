package com.flypaas.dao;

import java.util.List;
import java.util.Map;

import com.flypaas.model.TbRsCity;

public interface TbRsCityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbRsCity record);

    int insertSelective(TbRsCity record);

    TbRsCity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbRsCity record);

    int updateByPrimaryKey(TbRsCity record);
    
    TbRsCity queryCityByname(String cityName);
    
    
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
    
/*-------------------------------------------------管理系统-------------------------------------------*/    
    /**
     * 查询所有的国家
     * @return
     */
    public List<TbRsCity> queryAllCountry();
    
    /**
     * 根据国家ID查询改国家下所有的市区
     * @return
     */
    public List<TbRsCity> queryProvinceByCId(int countryId);
    
    /**
     * 根据国家ID查询某个省份下所有的市区
     * @return
     */
    public List<TbRsCity> queryCityByPId(int provinceId);
    
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
     * 根据ID查询城市名称
     * @param city country
     * @return
     */
    public TbRsCity queryCityByCityId(String country, String city);

	public List<TbRsCity> queryCityInfo(Map<String, String> para);

	public List<TbRsCity> queryCountryPrefixInfo(Map<String, String> para);
}