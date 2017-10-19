package com.flypaas.controller.city;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.flypaas.model.TbRsArea;
import com.flypaas.service.city.AreaService;
import com.google.gson.Gson;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年1月6日 下午4:37:35
* 类说明
*/
@Controller
@RequestMapping("/areaController")
public class AreaController {
	public static Logger logger = Logger.getLogger(AreaController.class);
	public static Gson gson = new Gson();
	@Autowired
	private AreaService areaServiceImpl;
	
	
	@RequestMapping("/queryAllArea")
	public ModelAndView queryArea(ModelMap model){
		logger.info("查询所有的大区--------------->>");
		//得到所有的大区
		List<TbRsArea> list = areaServiceImpl.queryAllArea();
		model.put("areaList", list);
		return new ModelAndView("jspPage/resource/resource-add",model);
	}
	
	@RequestMapping("/queryAllArea1")
	@ResponseBody
	public Object queryAllArea1(ModelMap model){
		logger.info("查询所有的大区--------------->>");
		//得到所有的大区
		List<TbRsArea> list = areaServiceImpl.queryAllArea();
		return gson.toJson(list);
	}
}
