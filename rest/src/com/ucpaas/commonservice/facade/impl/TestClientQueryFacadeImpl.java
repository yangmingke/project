package com.ucpaas.commonservice.facade.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.constant.ErrorCode;
import com.ucpaas.commonservice.facade.TestClientQueryFacade;
import com.ucpaas.commonservice.model.TestClientInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;
import com.ucpaas.commonservice.service.TestClientService;

@Service("testClientQueryFacade")
public class TestClientQueryFacadeImpl implements TestClientQueryFacade {
	private static final Logger log = LoggerFactory.getLogger(TestClientQueryFacadeImpl.class);

	@Resource(name = "testClientService")
	private TestClientService testClientService;

	@Override
	public ResultInfo<TestClientInfo> getListByAppSid(String appSid) {
		ResultInfo<TestClientInfo> resultInfo = new ResultInfo<TestClientInfo>(ErrorCode.C100000);
		log.debug("【查询应用下的测试子账号】开始, appSid={}", appSid);

		try {
			if (StringUtils.isEmpty(appSid)) {
				resultInfo.setResultFail(ErrorCode.C121001);
			} else {
				resultInfo.setListData(this.testClientService.getListByAppSid(appSid));
			}

		} catch (Exception e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【查询应用下的测试子账号】错误,appSid=" + appSid + ",resultInfo=" + resultInfo, e);
		}
		log.debug("【查询应用下的测试子账号】结束,appSid={},resultInfo={}", appSid, resultInfo);
		log.info("【查询应用下的测试子账号】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
		return resultInfo;
	}

	@Override
	public ResultInfo<TestClientInfo> getByClientNumber(String clientNumber) {
		ResultInfo<TestClientInfo> resultInfo = new ResultInfo<TestClientInfo>(ErrorCode.C100000);
		log.debug("【查询测试子账号】开始, clientNumber={}", clientNumber);

		try {
			if (StringUtils.isEmpty(clientNumber)) {
				resultInfo.setResultFail(ErrorCode.C131001);
			} else {
				resultInfo.setData(this.testClientService.getByClientNumber(clientNumber));
			}

		} catch (Exception e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【查询测试子账号】错误,clientNumber=" + clientNumber + ",resultInfo=" + resultInfo, e);
		}
		
		log.debug("【查询测试子账号】结束,clientNumber={},resultInfo={}", clientNumber, resultInfo);
		log.info("【查询测试子账号】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
		return resultInfo;
	}

	@Override
	public ResultInfo<TestClientInfo> getListBySid(String sid) {
		ResultInfo<TestClientInfo> resultInfo = new ResultInfo<TestClientInfo>(ErrorCode.C100000);
		log.debug("【查询开发者下的测试子账号】开始, sid={}", sid);

		try {
			if (StringUtils.isEmpty(sid)) {
				resultInfo.setResultFail(ErrorCode.C111001);
			} else {
				resultInfo.setListData(this.testClientService.getListBySid(sid));
			}

		} catch (Exception e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【查询开发者下的测试子账号】错误,sid=" + sid + ",resultInfo=" + resultInfo, e);
		}
		log.debug("【查询开发者下的测试子账号】结束,sid={},resultInfo={}", sid, resultInfo);
		log.info("【查询开发者下的测试子账号】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
		return resultInfo;
	}

}
