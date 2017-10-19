package com.ucpaas.commonservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ucpaas.commonservice.constant.ErrorCode;
import com.ucpaas.commonservice.model.ClientInfo;
import com.ucpaas.commonservice.model.base.RespInfo;
import com.ucpaas.commonservice.service.ClientService;

@RestController
@RequestMapping("/ucms")
public class ClientController {

	private static final Logger logger = LoggerFactory.getLogger("http_log");
	@Autowired
	private ClientService clientService;

	@RequestMapping(value = "client/clientByUin/{uin}", method = RequestMethod.GET)
	@ResponseBody
	public RespInfo<ClientInfo> getClientByUin(@PathVariable Integer uin) {
		logger.info("【查询子账号信息uin】开始 uin={}", uin);
		RespInfo<ClientInfo> resp = new RespInfo<ClientInfo>(ErrorCode.C100000);
		ClientInfo clientInfo = null;
		try {
			clientInfo = clientService.getByUin(uin);
			if (clientInfo != null) {
				resp.setData(clientInfo);
			} else {
				resp.setErrorCode(ErrorCode.C132005);
			}
		} catch (Exception e) {
			resp.setErrorCode(ErrorCode.C200099);
			logger.error("【查询子账号信息uin】错误 uin={}, e={}", uin, e);
		}
		logger.info("【查询子账号信息uin】结束 resp={}", resp);
		return resp;

	}

	@RequestMapping(value = "client/clientByClientNumber/{client_number}", method = RequestMethod.GET)
	@ResponseBody
	public RespInfo<ClientInfo> getClientByClientNumber(@PathVariable String client_number) {
		logger.info("【查询子账号信息client_number】开始 client_number={}", client_number);
		RespInfo<ClientInfo> resp = new RespInfo<ClientInfo>(ErrorCode.C100000);
		ClientInfo clientInfo = null;
		try {
			clientInfo = clientService.getByClientNumberCache(client_number);
			if (clientInfo != null) {
				resp.setData(clientInfo);
			} else {
				resp.setErrorCode(ErrorCode.C132005);
			}
		} catch (Exception e) {
			resp.setErrorCode(ErrorCode.C200099);
			logger.error("【查询子账号信息client_number】错误 client_number={}, e={}", client_number, e);
		}
		logger.info("【查询子账号信息client_number】结束 resp={}", resp);
		return resp;

	}

	@RequestMapping(value = "client/clientByMobile/{appSid}/{mobile}", method = RequestMethod.GET)
	@ResponseBody
	public RespInfo<ClientInfo> getClientByMobile(@PathVariable String appSid, @PathVariable String mobile) {
		logger.info("【查询子账号信息mobile】开始 appSid={},mobile = {}", appSid, mobile);
		RespInfo<ClientInfo> resp = new RespInfo<ClientInfo>(ErrorCode.C100000);
		ClientInfo clientInfo = null;
		try {
			clientInfo = clientService.getByMobileAndAppSidCache(mobile, appSid);
			if (clientInfo != null) {
				resp.setData(clientInfo);
			} else {
				resp.setErrorCode(ErrorCode.C132005);
			}
		} catch (Exception e) {
			resp.setErrorCode(ErrorCode.C200099);
			logger.error("【查询子账号信息mobile】错误 appSid={},mobile = {}, e={}", appSid, mobile, e);
		}
		logger.info("【查询子账号信息mobile】结束 resp={}", resp);
		return resp;

	}
	
	@RequestMapping(value = "client/clientByAppSid/{appSid}/{tablie_index}", method = RequestMethod.GET)
	@ResponseBody
	public RespInfo<ClientInfo> getClientByAppSid(@PathVariable String appSid, @PathVariable int tablie_index) {
		logger.info("【查询子账号信息AppSid】开始 appSid={},mobile = {}", appSid, tablie_index);
		RespInfo<ClientInfo> resp = new RespInfo<ClientInfo>(ErrorCode.C100000);
		List<ClientInfo> clientInfos = null;
		try {
			clientInfos = clientService.getByAppSid(appSid,tablie_index);
			if (clientInfos != null&& clientInfos.size()>0) {
				resp.setListData(clientInfos);
			} else {
				resp.setErrorCode(ErrorCode.C132005);
			}
		} catch (Exception e) {
			resp.setErrorCode(ErrorCode.C200099);
			logger.error("【查询子账号信息AppSid】错误 appSid={},tablie_index = {}, e={}", appSid, tablie_index, e);
		}
		logger.info("【查询子账号信息AppSid】结束 resp={}", resp.getListData().size());
		return resp;

	}
	
	

}
