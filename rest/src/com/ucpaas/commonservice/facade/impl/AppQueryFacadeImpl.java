package com.ucpaas.commonservice.facade.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.constant.ErrorCode;
import com.ucpaas.commonservice.facade.AppQueryFacade;
import com.ucpaas.commonservice.model.AppInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;
import com.ucpaas.commonservice.service.AppService;

@Service("appQueryFacade")
public class AppQueryFacadeImpl implements AppQueryFacade {
	private static final Logger log = LoggerFactory.getLogger(AppQueryFacadeImpl.class);
	

	@Resource(name = "appService")
	private AppService appService;

	@Override
	public ResultInfo<AppInfo> getByAppSid(String appSid, Boolean isEqual, String status) {
		ResultInfo<AppInfo> resultInfo = new ResultInfo<AppInfo>(ErrorCode.C100000);
		log.debug("【查询应用】开始,appSid={},isEqual={},status={}", appSid, isEqual, status);

		try {
			if (StringUtils.isEmpty(appSid)) {
				resultInfo.setResultFail(ErrorCode.C121001);
			} else {
				resultInfo.setData(this.appService.getByAppSid(appSid, isEqual, status));
			}

		} catch (Throwable e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【查询应用】错误,appSid=" + appSid + ",isEqual=" + isEqual + ",status=" + status + ",resultInfo="
					+ resultInfo, e);

		}

		log.debug("【查询应用】结束,appSid={},isEqual={},status={},resultInfo={}", appSid, isEqual, status, resultInfo);
		log.info("【查询应用】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
		return resultInfo;
	}

	@Override
	public ResultInfo<AppInfo> getByAppSidCache(String appSid, Boolean isEqual, String status) {
		ResultInfo<AppInfo> resultInfo = new ResultInfo<AppInfo>(ErrorCode.C100000);
		log.debug("【查询应用】开始,appSid={},isEqual={},status={}", appSid, isEqual, status);

		try {
			if (StringUtils.isEmpty(appSid)) {
				resultInfo.setResultFail(ErrorCode.C121001);
			} else {
				resultInfo.setData(this.appService.getByAppSidCache(appSid, isEqual, status));
			}

		} catch (Throwable e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【查询应用】错误,appSid=" + appSid + ",isEqual=" + isEqual + ",status=" + status + ",resultInfo="
					+ resultInfo, e);

		}

		log.debug("【查询应用】结束,appSid={},isEqual={},status={},resultInfo={}", appSid, isEqual, status, resultInfo);
		log.info("【查询应用】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
		return resultInfo;
	}

}
