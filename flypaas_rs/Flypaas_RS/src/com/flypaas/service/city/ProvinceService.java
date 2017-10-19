package com.flypaas.service.city;

import java.util.List;

import com.flypaas.model.TbRsProvince;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2016年12月30日 上午10:27:57
* 类说明
*/
public interface ProvinceService {
	
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
