package com.flypaas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.constant.RouterConstant;
import com.flypaas.dao.TbRsAccountInfoMapper;
import com.flypaas.model.TbRsAccountInfo;
import com.flypaas.service.ResourceSideService;

@Service("resourceSideServiceImpl")
public class ResourceSideServiceImpl implements ResourceSideService {
	@Autowired
	private TbRsAccountInfoMapper tbRsAccountInfoMapper;
	
	@Override
	public int insertSelective(TbRsAccountInfo record) {
		
		return tbRsAccountInfoMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(TbRsAccountInfo record) {
		
		return tbRsAccountInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public TbRsAccountInfo selectByPrimaryKey(String netSid) {
		
		return tbRsAccountInfoMapper.selectByPrimaryKey(netSid);
	}
	@Override
	public String getRouteArea(String netSid){
		TbRsAccountInfo tbRsAccountInfo = selectByPrimaryKey(netSid);
		String routeArea = tbRsAccountInfo.getNetArea();
		String isPrivateNet = tbRsAccountInfo.getIsPrivateNet();//是否开启私有域
		if(isPrivateNet == null || !"1".equals(isPrivateNet)){
			routeArea = RouterConstant.ROUTE_DEFAULT_KEY_CN;
		}
		return routeArea;
	}

}
