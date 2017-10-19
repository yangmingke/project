package com.flypaas.controller.operation;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.flypaas.service.operation.OperationService;
import com.flypaas.util.DateUtil;
import com.flypaas.util.StrUtil;
/**
 * 运维管理
 * @author win10
 *
 */
@Controller
@RequestMapping("/operation")
public class OperationContrller{
	@Autowired
	OperationService operationServiceImpl;
	
	@RequestMapping("/queryPaketLoss")
	@ResponseBody
	public Map queryPacketLoss(HttpServletRequest request, HttpServletResponse response){
		List<String> ipList= operationServiceImpl.queryAllRTPP();
		if(ipList == null || ipList.isEmpty()){
			StrUtil.writeMsg(response, "未找到相关RTPP节点");
			return null;
		}
		String ip = request.getParameter("ip");
		String date = request.getParameter("date");
		if(StrUtil.isEmpty(ip)){
			ip = ipList.get(0);
		}
		if(StrUtil.isEmpty(date)){
			Calendar cal=Calendar.getInstance();
//			cal.add(Calendar.DATE,-1);
			Date time=cal.getTime();
			date = new SimpleDateFormat("yyyy-MM-dd").format(time);
		}
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			model = operationServiceImpl.queryPacketLoss(ip,date);
		}catch(Exception e){
			System.out.println(e.getMessage());
			model.put("ipList", ipList);
			model.put("date", date);
			return model;
		}
		model.put("ipList", ipList);
		model.put("date", date);
		model.put("today", DateUtil.getTdDate());
		return model;
	}
	
	@RequestMapping("/queryPaketLossResource")
	@ResponseBody
	public Map queryPacketLossResource(HttpServletRequest request, HttpServletResponse response){
		List<String> ipList= operationServiceImpl.queryAllRTPP();
		if(ipList == null || ipList.isEmpty()){
			StrUtil.writeMsg(response, "未找到相关RTPP节点");
			return null;
		}
		String destIp = request.getParameter("destIp");
		String date = request.getParameter("date");
		if(StrUtil.isEmpty(destIp)){
			destIp = ipList.get(0);
		}
		if(StrUtil.isEmpty(date)){
			Calendar cal=Calendar.getInstance();
			cal.add(Calendar.DATE,0);
			Date time=cal.getTime();
			date = new SimpleDateFormat("yyyy-MM-dd").format(time);
		}
		Map<String,Object> model = new HashMap<String,Object>();
		try{
		model = operationServiceImpl.queryPacketLossResource(destIp,date);
		}catch(Exception e){
			System.out.println(e.getMessage());
			model.put("destIp", destIp);
			model.put("ipList", ipList);
			model.put("date", date);
			return model;
		}
		model.put("destIp", destIp);
		model.put("ipList", ipList);
		model.put("date", date);
		model.put("today", DateUtil.getTdDate());
		return model;
	}
	
	@RequestMapping("/querySessionPaketLoss")
	public ModelAndView querySessionPacketLoss(String dateTime,String userSid,String appSid, String sessionID){
		Map<String,Object> model = new HashMap<String,Object>();
		model = operationServiceImpl.querySessionPacketLoss(dateTime, userSid, appSid, sessionID);
		return new ModelAndView("jsp/operation/analysisResult",model);
	}
	
	
}
