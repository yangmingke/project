package com.ucpaas.commonservice.facade.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.constant.ErrorCode;
import com.ucpaas.commonservice.facade.ClientQueryFacade;
import com.ucpaas.commonservice.model.ClientInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;
import com.ucpaas.commonservice.service.ClientService;

@Service("clientQueryFacade")
public class ClientQueryFacadeImpl implements ClientQueryFacade {
	private static final Logger log = LoggerFactory.getLogger(ClientQueryFacadeImpl.class);

	@Resource(name = "clientService")
	private ClientService clientService;

	@Override
	public ResultInfo<ClientInfo> getByClientNumber(String clientNumber) {
		ResultInfo<ClientInfo> resultInfo = new ResultInfo<ClientInfo>(ErrorCode.C100000);
		log.debug("【查询clientByClientNumber】开始,clientNumber={}", clientNumber);

		try {
			if (StringUtils.isEmpty(clientNumber)) {
				resultInfo.setResultFail(ErrorCode.C131001);
			} else {
				resultInfo.setData(this.clientService.getByClientNumber(clientNumber));
			}
		} catch (Throwable e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【查询clientByClientNumber】错误,clientNumber=" + clientNumber + ",resultInfo=" + resultInfo, e);
		}
		log.info("【查询clientByClientNumber】结束,clientNumber={},result={},errorCode={},timeCount={}",clientNumber,resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
		return resultInfo;
	}

	@Override
	public ResultInfo<ClientInfo> getByMobileAndAppSidCache(String mobile, String appSid) {
		ResultInfo<ClientInfo> resultInfo = new ResultInfo<ClientInfo>(ErrorCode.C100000);
		log.debug("【查询client】开始, mobile={},appSid={}", mobile, appSid);

		try {
			if (StringUtils.isEmpty(mobile)) {
				resultInfo.setResultFail(ErrorCode.C131002);
			} else if (StringUtils.isEmpty(appSid)) {
				resultInfo.setResultFail(ErrorCode.C121001);
			} else {
				resultInfo.setData(this.clientService.getByMobileAndAppSidCache(mobile, appSid));
			}

		} catch (Exception e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【查询client】错误,mobile=" + mobile + ",appSid=" + appSid + ",resultInfo=" + resultInfo, e);
		}
		log.debug("【查询client】结束,mobile={},appSid={},resultInfo={}", mobile, appSid, resultInfo);
		log.info("【查询client】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
		return resultInfo;
	}

	@Override
	public ResultInfo<ClientInfo> getByUserIdAndAppSidCache(String userId, String appSid) {
		ResultInfo<ClientInfo> resultInfo = new ResultInfo<ClientInfo>(ErrorCode.C100000);
		log.debug("【查询client】开始, userId={},appSid={}", userId, appSid);

		try {
			if (StringUtils.isEmpty(userId)) {
				resultInfo.setResultFail(ErrorCode.C131003);
			} else if (StringUtils.isEmpty(appSid)) {
				resultInfo.setResultFail(ErrorCode.C121001);
			} else {
				resultInfo.setData(this.clientService.getByUserIdAndAppSidCache(userId, appSid));
			}

		} catch (Exception e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【查询client】错误,userId=" + userId + ",appSid=" + appSid + ",resultInfo=" + resultInfo, e);
		}
		log.debug("【查询client】结束,userId={},appSid={},resultInfo={}", userId, appSid, resultInfo);
		log.info("【查询client】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
		return resultInfo;
	}

	@Override
	public ResultInfo<ClientInfo> getByClientNumberCacheTest(String clientNumber) {
		ResultInfo<ClientInfo> resultInfo = new ResultInfo<ClientInfo>(ErrorCode.C100000);
		log.debug("【查询client】开始,clientNumber={}", clientNumber);

		try {
			if (StringUtils.isEmpty(clientNumber)) {
				resultInfo.setResultFail(ErrorCode.C131001);
			} else {
				resultInfo.setData(this.clientService.getByClientNumberCache(clientNumber));
			}
		} catch (Throwable e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【查询client】错误,clientNumber=" + clientNumber + ",resultInfo=" + resultInfo, e);
		}
		log.debug("【查询client】结束,clientNumber={},resultInfo={}", clientNumber, resultInfo);
		log.info("【查询client】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
		return resultInfo;
	}

}
