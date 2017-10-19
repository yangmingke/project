package com.flypaas.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.flypaas.model.TbRsConfig;
import com.flypaas.service.system.SystemService;
/**
 * 系统管理
 * @author win10
 *
 */
@Controller
@RequestMapping("/systemContrller")
public class SystemContrller{

	@Autowired
	SystemService systemServiceImpl;
	
	@RequestMapping("/sysConfigList")
	public ModelAndView sysConfigList(){
		List<TbRsConfig> tbRsConfig = systemServiceImpl.getSysConfigList();
		
		return new ModelAndView("jsp/system/sys_config","tbRsConfig",tbRsConfig);
	}
	
	@ResponseBody
	@RequestMapping("/updateConfig")
	public String updateConfig(TbRsConfig tbRsConfig){
		String code = systemServiceImpl.updateConfig(tbRsConfig);
		return code;
	}
	
	@ResponseBody
	@RequestMapping("/addConfig")
	public String addConfig(TbRsConfig tbRsConfig){
		String code = systemServiceImpl.addConfig(tbRsConfig);
		return code;
	}
	
	@ResponseBody
	@RequestMapping("/delConfig")
	public String delConfig(TbRsConfig tbRsConfig){
		String code = systemServiceImpl.delConfig(tbRsConfig);
		return code;
	}
}
