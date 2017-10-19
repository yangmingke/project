package com.flypaas.service.city;

import java.util.List;

import com.flypaas.model.TbRsIsp;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年1月14日 下午5:12:41
* 类说明
*/
public interface IspService {
	/**
     * 查询所有的Isp动态显示在页面
     * @return
     */
    public List<TbRsIsp> queryAllIsp();
}
