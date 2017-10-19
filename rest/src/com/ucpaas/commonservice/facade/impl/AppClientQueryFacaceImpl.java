package com.ucpaas.commonservice.facade.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.constant.ErrorCode;
import com.ucpaas.commonservice.facade.AppClientQueryFacace;
import com.ucpaas.commonservice.model.AppClientInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;
import com.ucpaas.commonservice.service.AppClientService;

@Service("appClientQueryFacace")
public class AppClientQueryFacaceImpl implements AppClientQueryFacace {
	private static final Logger log = LoggerFactory.getLogger(AppClientQueryFacaceImpl.class);

	@Resource(name = "appClientService")
	private AppClientService appClientService;

	@Override
	public ResultInfo<AppClientInfo> getPageListByAppSid(String appSid, Integer start, Integer pageSize) {
		ResultInfo<AppClientInfo> resultInfo = new ResultInfo<AppClientInfo>(ErrorCode.C100000);
		log.debug("【分页查询应用子账号】开始,appSid={},start={},pageSize={}", appSid, start, pageSize);

		try {
			if (StringUtils.isEmpty(appSid)) {
				resultInfo.setResultFail(ErrorCode.C121001);
			} else if (start == null) {
				resultInfo.setResultFail(ErrorCode.C200004);
			} else if (pageSize == null) {
				resultInfo.setResultFail(ErrorCode.C200005);
			} else {
				// start为大于0的整数
				if (start < 0) {
					start = 0;
				}
				// pageSize为小于100的整数
				if (pageSize > 100) {
					pageSize = 100;
				}
				if (pageSize < 0) {
					pageSize = 0;
				}
				resultInfo.setListData(this.appClientService.getPageListByAppSid(appSid, start, pageSize));
			}

		} catch (Throwable e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【分页查询应用子账号】错误,appSid=" + appSid + ",start=" + start + ",pageSize=" + pageSize + ",resultInfo="
					+ resultInfo, e);

		}

		log.info("【分页查询应用子账号】结束,appSid={},start={},pageSize={},ErrorCode={}", appSid, start, pageSize, resultInfo.getErrorCode());
		log.info("【分页查询应用子账号】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
		return resultInfo;
	}

	@Override
	public ResultInfo<Integer> getAppClientCountByAppSid(String appSid) {
		ResultInfo<Integer> resultInfo = new ResultInfo<Integer>(ErrorCode.C100000);
		log.debug("【查询某应用下子账号总数】开始,appSid={}", appSid);

		try {
			if (StringUtils.isEmpty(appSid)) {
				resultInfo.setResultFail(ErrorCode.C121001);
			} else {
				resultInfo.setData(this.appClientService.getAppClientCountByAppSid(appSid));
			}

		} catch (Throwable e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【查询某应用下子账号总数】错误,appSid=" + appSid + ",resultInfo=" + resultInfo, e);

		}

		log.debug("【查询某应用下子账号总数】结束,appSid={},resultInfo={}", appSid, resultInfo);
		log.info("【查询某应用下子账号总数】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
		return resultInfo;
	}

}
