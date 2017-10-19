package com.flypaas.service.city;

import java.util.List;
import java.util.Map;

import com.flypaas.model.TbRsCity;
import com.flypaas.model.TbRsIsp;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年2月8日 下午3:48:58
* 类说明
*/
public interface CityService {
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
    
    
 /*-------------------------------查询所有的运营商----------------------*/
    /**
     * 查询所有的Isp动态显示在页面
     * @return
     */
    public List<TbRsIsp> queryAllIsp();


	Map queryCity(String countryid, String cityid);
}
