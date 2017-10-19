package com.ucpaas.commonservice.facade.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.constant.ErrorCode;
import com.ucpaas.commonservice.facade.AppFacade;
import com.ucpaas.commonservice.model.AppInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;
import com.ucpaas.commonservice.service.AppService;

@Service("appFacade")
public class AppFacadeImpl implements AppFacade {
	private static final Logger log = LoggerFactory.getLogger(AppFacadeImpl.class);

	@Resource(name = "appService")
	private AppService appService;

	@Override
	public ResultInfo<AppInfo> updateByAppSidCache(AppInfo appInfo) {
		ResultInfo<AppInfo> resultInfo = new ResultInfo<AppInfo>(ErrorCode.C100000);
		log.debug("【更新应用】开始,appInfo={}", appInfo);

		try {
			if (appInfo == null) {
				resultInfo.setResultFail(ErrorCode.C200001);
			}else {
				appInfo.setUpdateDate(new Date());
				resultInfo.setAffectedRows(this.appService.updateByAppSidCache(appInfo));
			}
			
		} catch (Throwable e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【更新应用】错误,appInfo=" + appInfo + ",resultInfo=" + resultInfo, e);

		}
		log.debug("【更新应用】结束,appInfo={},resultInfo={}", appInfo, resultInfo);
		log.info("【更新应用】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
		return resultInfo;
	}

	@Override
	public void updateClientCount(String appSid, boolean isAdd, Integer clientCount) {
		ResultInfo<AppInfo> resultInfo = new ResultInfo<AppInfo>(ErrorCode.C100000);
		log.debug("【更新应用子账号总数】开始,appSid={},isAdd={},clientCount={}", appSid,isAdd,clientCount);

		try {
			if (StringUtils.isEmpty(appSid)) {
				resultInfo.setResultFail(ErrorCode.C121001);
			}else if(clientCount == null){
				resultInfo.setResultFail(ErrorCode.C121002);
			}
			else {
				resultInfo.setAffectedRows(this.appService.updateClientCount(appSid, isAdd, clientCount));
			}
		} catch (Throwable e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【更新应用子账号总数】错误,appSid=" + appSid +",isAdd=" + isAdd + ",clientCount=" + ",resultInfo=" + resultInfo, e);

		}
		log.debug("【更新应用子账号总数】结束,appSid={},isAdd={},clientCount={},resultInfo={}", appSid,isAdd,clientCount, resultInfo);
		log.info("【更新应用子账号总数】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());

	}

}
