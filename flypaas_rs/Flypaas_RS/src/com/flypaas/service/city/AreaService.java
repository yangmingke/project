package com.flypaas.service.city;

import java.util.List;

import com.flypaas.model.TbRsArea;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年1月6日 下午4:39:32
* 类说明
*/
public interface AreaService {
	/**
	 * 查询所有的片区
	 * @return
	 */
	public List<TbRsArea> queryAllArea();
}
