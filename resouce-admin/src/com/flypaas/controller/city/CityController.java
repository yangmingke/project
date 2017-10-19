package com.flypaas.controller.city;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.flypaas.constant.FlypaasConstant;
import com.flypaas.model.TbRsCity;
import com.flypaas.model.TbRsIsp;
import com.flypaas.service.city.CityService;
import com.google.gson.Gson;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2016年12月30日 上午10:24:46
* 类说明
* 点击省份 查找该省下的市区
*/
@Controller
@RequestMapping("/cityController")
public class CityController {
	public static Logger logger = Logger.getLogger(CityController.class);
	public static Gson gson = new Gson();
	@Autowired
	private CityService cityServiceImpl;
	
	
	public ModelAndView querySystemProperty(ModelMap model){
		//查询所有国家
		List<TbRsCity> countryList = new ArrayList<TbRsCity>();
		countryList = cityServiceImpl.queryAllCountry();
				
				  
		//查询该节点所在国家下的所有省份
		List<TbRsCity> provinceList = new ArrayList<TbRsCity>();
		Integer countryid  = FlypaasConstant.DEFAULT_COUNTRY;
		provinceList = cityServiceImpl.queryProvinceByCId(countryid);
		if(provinceList.size()==1 && provinceList.get(0).getProvinceid()==0){
			provinceList.get(0).setProvince("暂无数据");
		}
				
		//查询该节点所在省份下的所有市区
		List<TbRsCity> cityList = new ArrayList<TbRsCity>();
		Integer provinceid  = FlypaasConstant.DEFAULT_PROVINCE;
		cityList = cityServiceImpl.queryCityByPId(provinceid);
		if(cityList.size()==1 && cityList.get(0).getCityid()==0){
			cityList.get(0).setCity("暂无数据");
		}
				
		//查询所有的运营商
		List<TbRsIsp> ispList = new ArrayList<TbRsIsp>();
		ispList = cityServiceImpl.queryAllIsp();
		
		model.put("countryList", countryList);
		model.put("provinceList", provinceList);
		model.put("cityList", cityList);
		model.put("ispList", ispList);
		
		return new ModelAndView("/jsp/resource/resource-addResource",model);
	}
	
	
	
	
	
	
	/**
	 * 查询所有的国家
	 */
	@RequestMapping("/queryAllCountry")
	public ModelAndView  queryAllCountry(ModelMap model){
		logger.info("查询所有的国家--------------->>");
		List<TbRsCity> list = cityServiceImpl.queryAllCountry();

		model.put("countryList", list);

		return new ModelAndView("jspPage/resource/resource-add",model);
	}

	/**
	 * 查询所有的国家Ajax
	 */
	@RequestMapping("/queryAllCountryAjax")
	@ResponseBody
	public String  queryAllCountryAjax(){
		logger.info("查询所有的国家--------------->>");
		List<TbRsCity> list = cityServiceImpl.queryAllCountry();
		
		return gson.toJson(list);
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 查询某个国家下的省
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryProvinceByCountryId")
	@ResponseBody
	public String queryProvinceByCountryId(HttpServletRequest request){
		logger.info("查询某个国家下的所有省---------------->>");
		logger.info(request.getParameter("countryId"));
		int countryId = Integer.valueOf(request.getParameter("countryId"));
		List<TbRsCity> list = cityServiceImpl.queryProvinceByCId(countryId);
		return gson.toJson(list);
	}
	
	
	/**
	 * 获取前台页面传过来的pId  
	 * 根据pId查询该省下的所有市区
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryCityBypId")
	@ResponseBody
	public String queryCityBypId(HttpServletRequest request){
		logger.info("根据省份ID查询该省下的所有市区------------->>");
		int provinceId = Integer.valueOf(request.getParameter("provinceId"));
		List<TbRsCity> list = cityServiceImpl.queryCityByPId(provinceId);
		return gson.toJson(list);
	}
	
	
	
	
}
