package com.flypaas.service.impl.city;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.dao.TbRsCityMapper;
import com.flypaas.model.TbRsCity;
import com.flypaas.service.city.CityService;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2016年12月30日 上午10:26:55
* 类说明
*/
@Service("/cityServiceImpl")
public class CityServiceImpl implements CityService {
	@Autowired
	private TbRsCityMapper tbRsCityMapper;
	@Override
	public List<TbRsCity> queryCityBypId(int pId) {
		
		return tbRsCityMapper.queryCityBypId(pId);
	}
	
	@Override
	public List<TbRsCity> queryAll() {
		
		return tbRsCityMapper.queryAll();
	}

	@Override
	public List<TbRsCity> queryProvinceByAreaId(Integer areaId) {
		
		return tbRsCityMapper.queryProvinceByAreaId(areaId);
	}

	@Override
	public List<TbRsCity> queryAllCountry() {
		
		return tbRsCityMapper.queryAllCountry();
	}

	@Override
	public List<TbRsCity> queryProvinceByCountryId(int cId) {

		return tbRsCityMapper.queryProvinceByCountryId(cId);
	}


}
