package com.flypaas.service.impl.city;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.dao.TbRsProvinceMapper;
import com.flypaas.model.TbRsProvince;
import com.flypaas.service.city.ProvinceService;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2016年12月30日 上午10:28:15
* 类说明
*/
@Service("/provienceServiceImpl")
public class ProvinceServiceImpl implements ProvinceService{
	@Autowired
	private TbRsProvinceMapper tbRsProviceMapper;
	
	@Override
	public List<TbRsProvince> queryAllProvince() {
		
		return tbRsProviceMapper.queryAllProvince();
	}

	@Override
	public List<TbRsProvince> queryProvinceByAreaId(Integer areaId) {

		return tbRsProviceMapper.queryProvinceByAreaId(areaId);
	}

}
