package com.ucpaas.commonservice.facade.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.constant.ErrorCode;
import com.ucpaas.commonservice.facade.AppSuperClientRelFacade;
import com.ucpaas.commonservice.model.AppSuperClientRelInfo;
import com.ucpaas.commonservice.model.ClientInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;
import com.ucpaas.commonservice.service.AppSuperClientRelService;

@Service("appSuperClientRelFacade")
public class AppSuperClientRelFacadeImpl implements AppSuperClientRelFacade {
	private static final Logger log = LoggerFactory.getLogger(AppSuperClientRelFacadeImpl.class);

	@Resource(name = "appSuperClientRelService")
	private AppSuperClientRelService appSuperClientRelService;

	@Override
	public ResultInfo<AppSuperClientRelInfo> getByAppSid(String appSid) {
		ResultInfo<AppSuperClientRelInfo> resultInfo = new ResultInfo<AppSuperClientRelInfo>(ErrorCode.C100000);

		try {
			if (StringUtils.isEmpty(appSid)) {
				resultInfo.setResultFail(ErrorCode.C121001);
			} else {
				resultInfo.setData(this.appSuperClientRelService.getByAppSid(appSid));
			}

		} catch (Throwable e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【查询应用超级子账号关系】错误,appSid=" + appSid + ",resultInfo=" + resultInfo, e);

		}

		resultInfo.getTimeCount();
		log.info("【查询应用超级子账号关系】结束,appSid={},resultInfo={}", appSid, resultInfo);

		return resultInfo;
	}

	@Override
	public ResultInfo<AppSuperClientRelInfo> insert(AppSuperClientRelInfo appSuperClientRelInfo) {
		ResultInfo<AppSuperClientRelInfo> resultInfo = new ResultInfo<AppSuperClientRelInfo>(ErrorCode.C100000);

		try {
			if (StringUtils.isEmpty(appSuperClientRelInfo.getAppSid())) {
				resultInfo.setResultFail(ErrorCode.C121001);
			} else if (StringUtils.isEmpty(appSuperClientRelInfo.getClientNumber())) {
				resultInfo.setResultFail(ErrorCode.C131001);
			} else {
				resultInfo.setAffectedRows(this.appSuperClientRelService.insert(appSuperClientRelInfo));
			}

		} catch (Throwable e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【插入应用超级子账号关系】错误,appSuperClientRelInfo=" + appSuperClientRelInfo + ",resultInfo=" + resultInfo, e);

		}

		resultInfo.getTimeCount();
		log.info("【插入应用超级子账号关系】结束,appSuperClientRelInfo={},resultInfo={}", appSuperClientRelInfo, resultInfo);

		return resultInfo;
	}

	@Override
	public ResultInfo<ClientInfo> getClientByAppSid(String appSid) {
		ResultInfo<ClientInfo> resultInfo = new ResultInfo<ClientInfo>(ErrorCode.C100000);

		try {
			if (StringUtils.isEmpty(appSid)) {
				resultInfo.setResultFail(ErrorCode.C121001);
			} else {
				resultInfo.setData(this.appSuperClientRelService.getClientByAppSid(appSid));
			}

		} catch (Throwable e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【查询应用超级子账号】错误,appSid=" + appSid + ",resultInfo=" + resultInfo, e);

		}

		resultInfo.getTimeCount();
		log.info("【查询应用超级子账号】结束,appSid={},resultInfo={}", appSid, resultInfo);

		return resultInfo;
	}

}
