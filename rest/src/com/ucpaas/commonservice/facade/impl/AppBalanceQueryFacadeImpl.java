package com.ucpaas.commonservice.facade.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.constant.ErrorCode;
import com.ucpaas.commonservice.facade.AppBalanceQueryFacade;
import com.ucpaas.commonservice.model.AppBalanceInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;
import com.ucpaas.commonservice.service.AppBalanceService;

@Service("appBalanceQueryFacade")
public class AppBalanceQueryFacadeImpl implements AppBalanceQueryFacade {
	private static final Logger log = LoggerFactory.getLogger(AppBalanceQueryFacadeImpl.class);

	@Resource(name = "appBalanceService")
	private AppBalanceService appBalanceService;

	@Override
	public ResultInfo<AppBalanceInfo> getByAppSid(String appSid) {
		ResultInfo<AppBalanceInfo> resultInfo = new ResultInfo<AppBalanceInfo>(ErrorCode.C100000);
		log.debug("【查询应用余额】开始,appSid={}", appSid);

		try {
			if (StringUtils.isEmpty(appSid)) {
				resultInfo.setResultFail(ErrorCode.C121001);
			} else {
				resultInfo.setData(this.appBalanceService.getByAppSid(appSid));
			}

		} catch (Throwable e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【查询应用余额】错误,appSid=" + appSid + ",resultInfo=" + resultInfo, e);

		}
		log.debug("【查询应用余额】结束,appSid={},resultInfo={}", appSid, resultInfo);
		log.info("【查询应用余额】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
		return resultInfo;
	}

}
