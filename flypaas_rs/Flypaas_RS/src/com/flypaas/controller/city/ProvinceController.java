package com.flypaas.controller.city;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flypaas.controller.LoginController;
import com.flypaas.model.TbRsCity;
import com.flypaas.model.TbRsProvince;
import com.flypaas.service.city.CityService;
import com.flypaas.service.city.ProvinceService;
import com.google.gson.Gson;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2016年12月30日 上午10:27:43
* 类说明
*	 查询所有的省份  以json格式传到前台
*/
@Controller
@RequestMapping("/provinceController")
public class ProvinceController {
	public static Logger logger = Logger.getLogger(LoginController.class);
	public static Gson gson = new Gson();
	@Autowired
	private ProvinceService provienceServiceImpl;
	
	@Autowired 
	private CityService cityServiceImpl;
	
	/**
	 * 查出所有的省份  以json格式显示在前台
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryAllProvince")
	@ResponseBody
	public Object queryAllProvince(ModelMap model){
		List<TbRsProvince> list = provienceServiceImpl.queryAllProvince();
		return gson.toJson(list);
	}
	
	@RequestMapping("/queryProvinceByAreaId")
	@ResponseBody
	public List<TbRsCity> queryProvinceByAreaId(HttpServletRequest request){
		logger.info("按照区Id查询该区省份--------------------------->>");
		Integer areaId = Integer.valueOf(request.getParameter("areaId"));
		List<TbRsCity> list = cityServiceImpl.queryProvinceByAreaId(areaId);
		//得到最后一个省份所有的城市
//		int provinceId = list.get(list.size()-1).getProvinceid();
//		List<TbRsCity> cityList = cityServiceImpl.queryCityBypId(provinceId);
//		Map map =new HashMap();
//		map.put("list", list);
//		map.put("cityList", cityList);
		return list;
	}
}
