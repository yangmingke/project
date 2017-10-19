package com.flypaas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.flypaas.service.DemoService;
import com.flypaas.util.PageContainer;


/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2016年12月29日 下午4:47:47
* 类说明
*/
@Controller
@RequestMapping("/demoController")
public class DemoController {
	
	@Autowired
	private DemoService demoServiceImpl;
	
	/**
	 * 查看系统日志文件
	 * @param session
	 * @param model
	 * @param list
	 * @return
	 */
	@RequestMapping("/queryDemo")
	public ModelAndView queryDemo(int page){
		
		PageContainer  pageContainer  = demoServiceImpl.queryDemo("E8480",page);
		return new ModelAndView("log","page",pageContainer);
	}
	
}
