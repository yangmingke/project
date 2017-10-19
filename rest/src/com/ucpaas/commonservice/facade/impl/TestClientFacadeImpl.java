package com.ucpaas.commonservice.facade.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.constant.ErrorCode;
import com.ucpaas.commonservice.facade.TestClientFacade;
import com.ucpaas.commonservice.model.TestClientInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;
import com.ucpaas.commonservice.service.TestClientService;

@Service("testClientFacade")
public class TestClientFacadeImpl implements TestClientFacade {
	private static final Logger log = LoggerFactory.getLogger(TestClientFacadeImpl.class);

	@Resource(name = "testClientService")
	private TestClientService testClientService;

	@Override
	public ResultInfo<TestClientInfo> updateByClientNumber(TestClientInfo testClientInfo) {
		ResultInfo<TestClientInfo> resultInfo = new ResultInfo<TestClientInfo>(ErrorCode.C100000);
		log.debug("【更新测试子账号】开始,testClientInfo={}", testClientInfo);

		try {
			if (testClientInfo == null) {
				resultInfo.setResultFail(ErrorCode.C200001);
			} else {
				testClientInfo.setUpdateDate(new Date());
				resultInfo.setAffectedRows(this.testClientService.updateByClientNumber(testClientInfo));
			}

		} catch (Throwable e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【更新测试子账号】错误,testClientInfo=" + testClientInfo + ",resultInfo=" + resultInfo, e);

		}

		log.info("【更新测试子账号】结束,testClientInfo={},resultInfo={}", testClientInfo, resultInfo);
		log.info("【更新测试子账号】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());

		return resultInfo;
	}

	@Override
	public ResultInfo<TestClientInfo> insert(TestClientInfo testClientInfo) {
		ResultInfo<TestClientInfo> resultInfo = new ResultInfo<TestClientInfo>(ErrorCode.C100000);
		log.debug("【添加测试子账号】开始,testClientInfo={}", testClientInfo);

		try {
			if (testClientInfo == null) {
				resultInfo.setResultFail(ErrorCode.C200001);
			} else if (testClientInfo.getClientNumber() == null) {
				resultInfo.setResultFail(ErrorCode.C131001);
			} else if (StringUtils.isEmpty(testClientInfo.getClientSid())) {
				resultInfo.setResultFail(ErrorCode.C131005);
			} else {
				testClientInfo.setCreateDate(new Date());
				testClientInfo.setUpdateDate(new Date());
				resultInfo.setAffectedRows(this.testClientService.insert(testClientInfo));
			}

		} catch (Throwable e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【添加测试子账号】错误,testClientInfo=" + testClientInfo + ",resultInfo=" + resultInfo, e);

		}

		log.info("【添加测试子账号】结束,testClientInfo={},resultInfo={}", testClientInfo, resultInfo);
		log.info("【添加测试子账号】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
		return resultInfo;
	}

}
