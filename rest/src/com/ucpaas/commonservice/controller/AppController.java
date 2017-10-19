package com.ucpaas.commonservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ucpaas.commonservice.constant.ErrorCode;
import com.ucpaas.commonservice.model.AppInfo;
import com.ucpaas.commonservice.model.base.RespInfo;
import com.ucpaas.commonservice.service.AppService;

@RestController
@RequestMapping("/ucms")
public class AppController {

	private static final Logger logger = LoggerFactory.getLogger("http_log");
	@Autowired
	private AppService appService;

	@RequestMapping(value = "app/{appSid}", method = RequestMethod.GET)
	@ResponseBody
	public RespInfo<AppInfo> getAppByAppSid(@PathVariable String appSid) {
		logger.info("【查询应用信息】开始 appSid={}",appSid);
		RespInfo<AppInfo> resp = new RespInfo<AppInfo>(ErrorCode.C100000);
		AppInfo appInfo = null;
		try {
			appInfo = appService.getByAppSidCache(appSid, null, null);
			if (appInfo != null) {
				resp.setData(appInfo);
			} else {
				resp.setErrorCode(ErrorCode.C121005);
			}
		} catch (Exception e) {
			resp.setErrorCode(ErrorCode.C200099);
			logger.error("【查询应用信息】错误 appSid={}, e={}",appSid,e);
		}
		logger.info("【查询应用信息】结束 resp={}",resp);
		return resp;

	}
	
	
	@RequestMapping(value = "app/appBySid/{sid}", method = RequestMethod.GET)
	@ResponseBody
	public RespInfo<AppInfo> getAppBySid(@PathVariable String sid) {
		logger.info("【查询应用信息BySid】开始 sid={}",sid);
		RespInfo<AppInfo> resp = new RespInfo<AppInfo>(ErrorCode.C100000);
		List<AppInfo> appInfos = null;
		try {
			appInfos = appService.getBySid(sid);
			if (appInfos != null && appInfos.size()>0) {
				resp.setListData(appInfos);
			} else {
				resp.setErrorCode(ErrorCode.C121005);
			}
		} catch (Exception e) {
			resp.setErrorCode(ErrorCode.C200099);
			logger.error("【查询应用信息BySid】错误 sid={}, e={}",sid,e);
		}
		logger.info("【查询应用信息BySid】结束 resp={}",resp);
		return resp;

	}

}
