package com.ucpaas.commonservice.facade.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.constant.ErrorCode;
import com.ucpaas.commonservice.facade.AppBalanceFacade;
import com.ucpaas.commonservice.model.AppBalanceInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;
import com.ucpaas.commonservice.service.AppBalanceService;

@Service("appBalanceFacade")
public class AppBalanceFacadeImpl implements AppBalanceFacade {
	private static final Logger log = LoggerFactory.getLogger(AppBalanceFacadeImpl.class);
	
	@Resource(name="appBalanceService")
	private AppBalanceService appBalanceService;

	@Override
	public ResultInfo<AppBalanceInfo> updateByAppSid(AppBalanceInfo appBalanceInfo) {
		ResultInfo<AppBalanceInfo> resultInfo = new ResultInfo<AppBalanceInfo>(ErrorCode.C100000);
		log.debug("【更新应用余额】开始,appBalanceInfo={}", appBalanceInfo);

		try {
			if (appBalanceInfo == null) {
				resultInfo.setResultFail(ErrorCode.C200001);
			}else {
				appBalanceInfo.setUpdateDate(new Date());
				resultInfo.setAffectedRows(this.appBalanceService.updateByAppSid(appBalanceInfo));
			}
			
		} catch (Throwable e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【更新应用余额】错误,appBalanceInfo=" + appBalanceInfo + ",resultInfo=" + resultInfo, e);

		}
		log.debug("【更新应用余额】结束,appBalanceInfo={},resultInfo={}", appBalanceInfo, resultInfo);
		log.info("【更新应用余额】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
		return resultInfo;
	}

}
