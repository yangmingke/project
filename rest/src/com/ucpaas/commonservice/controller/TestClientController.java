package com.ucpaas.commonservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ucpaas.commonservice.constant.ErrorCode;
import com.ucpaas.commonservice.model.TestClientInfo;
import com.ucpaas.commonservice.model.base.RespInfo;
import com.ucpaas.commonservice.service.TestClientService;

@RestController
@RequestMapping("/ucms")
public class TestClientController {

	private static final Logger logger = LoggerFactory.getLogger("http_log");
	@Autowired
	private TestClientService testClientService;

	@RequestMapping(value = "testclient/clientByClientNumber/{client_number}", method = RequestMethod.GET)
	@ResponseBody
	public RespInfo<TestClientInfo> getTestClientByClientNumber(@PathVariable String client_number) {
		logger.info("【查询测试子账号信息client_number】开始 client_number={}", client_number);
		RespInfo<TestClientInfo> resp = new RespInfo<TestClientInfo>(ErrorCode.C100000);
		TestClientInfo clientInfo = null;
		try {
			clientInfo = testClientService.getByClientNumber(client_number);
			if (clientInfo != null) {
				resp.setData(clientInfo);
			} else {
				resp.setErrorCode(ErrorCode.C132005);
			}
		} catch (Exception e) {
			resp.setErrorCode(ErrorCode.C200099);
			logger.error("【查询测试子账号信息client_number】错误 client_number={}, e={}", client_number, e);
		}
		logger.info("【查询测试子账号信息client_number】结束 resp={}", resp);
		return resp;

	}

}
