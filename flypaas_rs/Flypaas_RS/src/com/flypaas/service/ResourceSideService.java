package com.flypaas.service;

import com.flypaas.model.TbRsAccountInfo;

public interface ResourceSideService {
	
	//注册时插入资源方信息
	public int insertSelective(TbRsAccountInfo record);
	
	//激活邮箱时修改资源方信息
	public int updateByPrimaryKeySelective(TbRsAccountInfo record);
	
	//按照netId查询当前账号的资源方信息
	public TbRsAccountInfo selectByPrimaryKey(String netId);

	public String getRouteArea(String netSid);

}
