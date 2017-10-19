package com.flypaas.service.city;

import java.util.List;

import com.flypaas.model.TbRsCity;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2016年12月30日 上午10:26:37
* 类说明
*/
public interface CityService {
	/**
     * 查询所有的国家
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
}
