package com.flypaas.controller.resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flypaas.annotation.Log;
import com.flypaas.model.TbRsRTPPRealtimeStatus;
import com.flypaas.service.resource.ResourceStatusService;
import com.flypaas.util.StrUtil;
import com.google.gson.Gson;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2016年12月23日 上午9:42:19
* 类说明
*/
@Controller
@RequestMapping("/resourceStatusController")
public class ResourceStatusController {
	public static Logger logger = Logger.getLogger(ResourceController.class);
	public static Gson gson = new Gson();
	
	@Autowired
	private ResourceStatusService resourceStatusServiceImpl;
	
	/**
	 * 查询该ip节点的最新状态
	 * @param request
	 * @param tbRsRTPPRealtimeStatus
	 * @return
	 */
	@RequestMapping("/queryStatusByIpNewData")
	@ResponseBody
	public String queryStatusByIpNewData(HttpServletRequest request,TbRsRTPPRealtimeStatus tbRsRTPPRealtimeStatus){
		logger.info("根据ip查询该节点在现在的并发量的并发量---------->>");
		String ip = request.getParameter("ip");
		tbRsRTPPRealtimeStatus = resourceStatusServiceImpl.queryStatusByIp(ip);
		if(tbRsRTPPRealtimeStatus!=null){
			if(tbRsRTPPRealtimeStatus.getConcurrency() == 0){
				return gson.toJson(1);
			}else{
				return gson.toJson(0);
			}
		}else{
			return gson.toJson(1);
		}
	}
	
	/**
	 * 根据ip查询该节点在某一段时间内的状态信息
	 * @param request
	 * @param tbRsRTPPRealtimeStatus
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/queryStatusByIpTime")
	@ResponseBody
	public String queryStatusByIpTime(HttpServletRequest request,TbRsRTPPRealtimeStatus tbRsRTPPRealtimeStatus) throws ParseException{
		String ip = request.getParameter("ip");
		String dateTime = request.getParameter("dateTime");
		if(StrUtil.isEmpty(dateTime)){
			SimpleDateFormat str = new SimpleDateFormat("yyyyMMdd");//时间格式
			Date date = new Date(); //当前时间
			dateTime = str.format(date);
		}
		logger.info("根据查询节点"+ip+"在"+dateTime+"的流量信息----------->>");
		List<Map<String, String>> list = resourceStatusServiceImpl.queryStatusByIpTime(ip,dateTime);
		
		return gson.toJson(list);
	}
	
	
}
