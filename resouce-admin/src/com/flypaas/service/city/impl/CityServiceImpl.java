package com.flypaas.service.city.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.dao.TbRsCityMapper;
import com.flypaas.dao.TbRsIspMapper;
import com.flypaas.model.TbRsCity;
import com.flypaas.model.TbRsIsp;
import com.flypaas.service.city.CityService;
import com.flypaas.util.GooleMapUtil;
import com.flypaas.util.StrUtil;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年2月8日 下午3:50:18
* 类说明
*/
@Service("/cityServiceImpl")
public class CityServiceImpl implements CityService {
	@Autowired
	private TbRsCityMapper tbRsCityMapper;
	@Autowired
	private TbRsIspMapper tbRsIspMapper;
	
	@Override
	public List<TbRsCity> queryAllCountry() {
		
		return tbRsCityMapper.queryAllCountry();
	}

	@Override
	public List<TbRsCity> queryProvinceByCId(int countryId) {

		return tbRsCityMapper.queryProvinceByCId(countryId);
	}


	@Override
	public List<TbRsCity> queryCityByPId(int provinceId) {

		return tbRsCityMapper.queryCityByPId(provinceId);
	}

	@Override
	public List<TbRsIsp> queryAllIsp() {

		return tbRsIspMapper.queryAllIsp();
	}
	
	@Override
	public Map queryCity(String countryid,String cityid){
		Map<String,String> para = new HashMap<String,String>();
		para.put("countryid","0".equals(countryid) ? null : countryid);
		para.put("cityid","0".equals(cityid) ? null : cityid);
		Map<String,Object> cityInfo = new HashMap<String,Object>();
		if((StringUtils.isEmpty(para.get("countryid")) && StringUtils.isEmpty(para.get("cityid")))){//如果国家和城市代码为空，则直接返回，0也作为空值
			return cityInfo;
		}
		List<TbRsCity> tbRsCityList = tbRsCityMapper.queryCityInfo(para);
		if(tbRsCityList == null || tbRsCityList.isEmpty()){
			tbRsCityList = tbRsCityMapper.queryCountryPrefixInfo(para);
		}
		if(tbRsCityList == null || tbRsCityList.isEmpty()){
			return cityInfo;
		}
		TbRsCity tbRsCity = tbRsCityList.get(0);
		String cityName;
		if(StrUtil.isEmpty(tbRsCity.getCity())){
			if(tbRsCityList.size() > 1){//如果电话区号对应多个国家，则显示首个国家（电话区号）
				cityName = tbRsCity.getCountry() + "(" + para.get("countryid") + ")";
			}else{
				cityName = tbRsCity.getCountry();
			}
		}else{
			cityName = tbRsCity.getCity();
		}
		cityInfo.put("cityName",cityName);
		if(tbRsCity.getLatitude() == null || tbRsCity.getLongitude() == null){//如果数据库未记录该城市经纬坐标，则查询谷歌地图城市经纬度
			List<Map> coordinatesList = (List) GooleMapUtil.getCoordinate(cityName).get("result");
			if(cityName.contains("香港")){//香港与深圳太接近，点容易重合，将香港坐标偏移
				String latitude = (String) coordinatesList.get(0).get("lat");
				String longitude = (String) coordinatesList.get(0).get("lng");
				Float lat = (Float.valueOf(latitude) - 0.3F);
				Float lng = (Float.valueOf(longitude) + 0.3F);
				cityInfo.put("latitude", latitude );
				cityInfo.put("longitude", longitude );
			}else{
				cityInfo.put("latitude", coordinatesList.get(0).get("lat"));
				cityInfo.put("longitude", coordinatesList.get(0).get("lng"));
			}
		}
		return cityInfo;
	}

}
