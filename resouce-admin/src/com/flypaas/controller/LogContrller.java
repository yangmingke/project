package com.flypaas.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.flypaas.service.LogService;
import com.flypaas.util.PageContainer;
import com.flypaas.util.StrUtil;
/**
 * 资源方管理
 * @author win10
 *
 */
@Controller
@RequestMapping("/log")
public class LogContrller{
	
	public static Logger logger = Logger.getLogger(LogContrller.class);

	@Autowired
	LogService logServiceImpl;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/queryLog")
	public ModelAndView queryLog(HttpServletRequest request,@RequestParam(value="netSid")String netSid){
		
		String roleId = request.getParameter("roleId");//角色ID
		String datemin = request.getParameter("datemin");;//操作日期起
		String datemax = request.getParameter("datemax");;//操作日期止
		String operate = request.getParameter("operate");;//操作内容
		String currentPage = request.getParameter("page");//页码
		String pageRowCount = request.getParameter("pageRowCount");//一页显示最大数据条数
		
		operate = StrUtil.isEmpty(operate) ? null : operate.trim();
		int page = currentPage == null ? 1 : Integer.valueOf(currentPage);
		
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("roleId", StrUtil.isEmpty(roleId) ? null : roleId);
		para.put("datemin", StrUtil.isEmpty(datemin) ? null : datemin);
		para.put("datemax", StrUtil.isEmpty(datemax) ? null : datemax);
		para.put("operate", StrUtil.isEmpty(operate) ? null : operate);
		para.put("pageRowCount", StrUtil.isEmpty(pageRowCount) ? null : Integer.valueOf(pageRowCount));
		para.put("netSid", netSid);
		
		Map model = new HashMap();
		PageContainer  pageContainer  = logServiceImpl.queryLog(para,page,netSid);
		model.put("page", pageContainer);
		model.put("netSid", netSid);
		model.put("para", para);
		model.putAll(para);
		return new ModelAndView("jsp/account/list/operateLog",model);
	}
}
