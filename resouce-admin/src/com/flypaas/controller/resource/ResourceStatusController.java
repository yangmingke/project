package com.flypaas.controller.resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flypaas.service.resource.ResourceService;
import com.flypaas.util.StrUtil;
import com.google.gson.Gson;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年2月10日 下午5:08:30
* 类说明
*/
@Controller
@RequestMapping("/resourceStatusController")
public class ResourceStatusController {
	public static final Logger logger = Logger.getLogger(ResourceStatusController.class);
	public static Gson gson = new Gson();
	
	@Autowired
	private ResourceService resourceServiceImpl;
	
	
	/**
	 * 查询节点的历史状态  并以曲线图的格式显示在界面  根据IP查询
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryResourceStatusToPic")
	@ResponseBody
	public String queryResourceStatusToPic(HttpServletRequest request,ModelMap model){
		String dateTime = request.getParameter("dateTime");
		String ip = request.getParameter("ip");
		if(StrUtil.isEmpty(dateTime)){
			SimpleDateFormat str = new SimpleDateFormat("yyyyMMdd");//时间格式
			Date date = new Date(); //当前时间
			dateTime = str.format(date);
		}
		List<Map<String, String>> list = resourceServiceImpl.queryResourceRealStatus(ip,dateTime);
		return gson.toJson(list);
	}
	
}
