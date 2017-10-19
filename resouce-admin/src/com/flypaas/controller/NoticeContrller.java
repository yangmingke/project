package com.flypaas.controller;


import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.flypaas.constant.FlypaasConstant;
import com.flypaas.model.TbRsUserMsg;
import com.flypaas.service.NoticeService;
/**
 * 资源方管理
 * @author win10
 *
 */
@Controller
@RequestMapping("/notice")
public class NoticeContrller{
	@Autowired
	NoticeService noticeServiceImpl;
	
	public static Logger logger = Logger.getLogger(NoticeContrller.class);
	
	
	@RequestMapping("/toNoticePage")
	public ModelAndView toNoticePage(String sid,String username,String netSid){
		
		Map<String, String> result = new HashMap<String, String>();
		result.put("sid", sid);
		result.put("username", username);
		result.put("netSid", netSid);
		
		return new ModelAndView("jsp/notice",result);
	}
	
	@RequestMapping("/adminNotice")
	@ResponseBody
	public int adminNotice(TbRsUserMsg record){
		if(record == null){
			return 0;
		}
		
		record.setMsgType(FlypaasConstant.ADMIN_NOTICE);
		int result = noticeServiceImpl.createNotice(record);
		
		return result;
	}
	
	
}
