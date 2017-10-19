package com.flypaas.controller.city;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.flypaas.controller.LoginController;
import com.flypaas.model.TbRsCity;
import com.flypaas.model.TbRsIsp;
import com.flypaas.service.city.CityService;
import com.flypaas.service.city.IspService;
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
	public static Logger logger = Logger.getLogger(LoginController.class);
	public static Gson gson = new Gson();
	@Autowired
	private CityService cityServiceImpl;
	
	@Autowired
	private IspService ispServiceImpl;
	/**
	 * 查询所有的国家
	 */
	@RequestMapping("/queryAllCountry")
	public ModelAndView  queryAllCountry(ModelMap model){
		logger.info("查询所有的国家--------------->>");
		List<TbRsCity> list = cityServiceImpl.queryAllCountry();
		List<TbRsIsp> list1 = ispServiceImpl.queryAllIsp();
		model.put("countryList", list);
		model.put("ispList", list1);
		return new ModelAndView("jspPage/resource/resource-add",model);
	}
	@RequestMapping("/queryAllIsp")
	@ResponseBody
	public String queryAllIsp(){
		logger.info("查询所有的Isp-------------------------->>");
		List<TbRsIsp> list = ispServiceImpl.queryAllIsp();
		return gson.toJson(list);
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
		List<TbRsCity> list = cityServiceImpl.queryProvinceByCountryId(countryId);
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
		logger.info("进去根据省份ID查询该省下的所有市区------------->>");
		int provinceId = Integer.valueOf(request.getParameter("provinceId"));
		List<TbRsCity> list = cityServiceImpl.queryCityBypId(provinceId);
		return gson.toJson(list);
	}
	/**
	 * 查询所有的城市
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryAll")
	public ModelAndView queryAll(ModelMap model){
		logger.info("查询所有的城市--------------->>");
		List<TbRsCity> list = cityServiceImpl.queryAll();
		
		
		model.put("cityList", list);
		return new ModelAndView("jspPage/resource/resource-add",model);
	}
	
	/**
	 * 查询所有的城市
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryAllcity")
	@ResponseBody
	public String queryAllcity(ModelMap model){
		logger.info("查询所有的城市--------------->>");
		List<TbRsCity> list = cityServiceImpl.queryAll();
		//model.put("cityList", list);
		return gson.toJson(list);
	}
	
	
	
}
