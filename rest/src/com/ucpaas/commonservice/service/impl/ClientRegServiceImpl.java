package com.ucpaas.commonservice.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.constant.Constants;
import com.ucpaas.commonservice.model.Attr2uinInfo;
import com.ucpaas.commonservice.model.ClientBalanceInfo;
import com.ucpaas.commonservice.model.ClientInfo;
import com.ucpaas.commonservice.service.Attr2uinInfoService;
import com.ucpaas.commonservice.service.ClientBalanceService;
import com.ucpaas.commonservice.service.ClientRegService;
import com.ucpaas.commonservice.service.ClientService;
import com.ucpaas.commonservice.service.TestClientService;

@Service("clientRegService")
public class ClientRegServiceImpl implements ClientRegService {
	private static final Logger log = LoggerFactory.getLogger(ClientRegServiceImpl.class);

	@Resource(name = "attr2uinInfoService")
	private Attr2uinInfoService attr2uinInfoService;

	@Resource(name = "clientService")
	private ClientService clientService;

	@Resource(name = "clientBalanceService")
	private ClientBalanceService clientBalanceService;

	@Resource(name = "testClientService")
	private TestClientService testClientService;

	/**
	 * 注册client，反向表，clientbalance信息
	 * 
	 * @param clientInfo
	 * @param clientBalanceInfo
	 * @return
	 * @throws Exception
	 */
	public int regClientAndAttrAndBalance(ClientInfo clientInfo, ClientBalanceInfo clientBalanceInfo) throws Exception {
		int i = 0;
		// 插入反向表mobile信息
		if (StringUtils.isNotEmpty(clientInfo.getMobile())) {
			// 如果手机号不为空，则插入type=102：attr={mobile}_{appId}
			Attr2uinInfo attr2uinInfo = new Attr2uinInfo();
			attr2uinInfo.setAttr(clientInfo.getMobile() + "_" + clientInfo.getAppSid());
			attr2uinInfo.setType(Constants.ATTR2UIN_TYPE_102);
			attr2uinInfo.setUin(clientInfo.getUin());
			i += this.attr2uinInfoService.insert(attr2uinInfo);
		}
		// 插入反向表userid信息
		if (StringUtils.isNotEmpty(clientInfo.getUserid())) {
			// 如果子账号的userid非空，则插入type=101：attr={userId}_{appId}
			Attr2uinInfo attr2uinInfo = new Attr2uinInfo();
			attr2uinInfo.setAttr(clientInfo.getUserid() + "_" + clientInfo.getAppSid());
			attr2uinInfo.setType(Constants.ATTR2UIN_TYPE_101);
			attr2uinInfo.setUin(clientInfo.getUin());
			i += this.attr2uinInfoService.insert(attr2uinInfo);
		} else {
			// 如果子账号的userid为空，则插入type=101：attr={clientNumber}_{appId}
			Attr2uinInfo attr2uinInfo = new Attr2uinInfo();
			attr2uinInfo.setAttr(clientInfo.getClientNumber() + "_" + clientInfo.getAppSid());
			attr2uinInfo.setType(Constants.ATTR2UIN_TYPE_101);
			attr2uinInfo.setUin(clientInfo.getUin());
			i += this.attr2uinInfoService.insert(attr2uinInfo);
		}

		// 插入client信息
		i += this.clientService.insert(clientInfo);
		// 插入clientbalance信息
		if (null != clientBalanceInfo) {
			i += this.clientBalanceService.insert(clientBalanceInfo);
		}
		log.debug("【注册子账号信息】service结束,插入数据条数i={}", i);
		return i;
	}

	public void rollbackClient(ClientInfo clientInfo, ClientBalanceInfo clientBalanceInfo) throws Exception {
		int i = 0;
		// 删除反向表uin_101
		if (StringUtils.isNotBlank(clientInfo.getUserid())) {
			Attr2uinInfo attr_userid = new Attr2uinInfo();
			attr_userid.setAttr(clientInfo.getUserid() + "_" + clientInfo.getAppSid());
			attr_userid.setType(Constants.ATTR2UIN_TYPE_101);
			attr_userid.setUin(clientInfo.getUin());

			i += this.attr2uinInfoService.deleteByUinTypeCache(attr_userid);
		}
		// 删除反向表uin_102
		if (StringUtils.isNotBlank(clientInfo.getMobile())) {
			Attr2uinInfo attr_mobile = new Attr2uinInfo();
			attr_mobile.setAttr(clientInfo.getMobile() + "_" + clientInfo.getAppSid());
			attr_mobile.setType(Constants.ATTR2UIN_TYPE_102);
			attr_mobile.setUin(clientInfo.getUin());

			i += this.attr2uinInfoService.deleteByUinTypeCache(attr_mobile);
		}
		// 删除client
		i += this.clientService.deleteByUin(clientInfo);
		// 删除clientbalance
		if (null != clientBalanceInfo) {
			i += this.clientBalanceService.deleteByUin(clientBalanceInfo);
		}
		// 删除测试子账号
		if ("0".equals(clientInfo.getClientType())) {
			testClientService.deleteTestClientByUin(clientInfo.getUin());
		}

		log.info("【回滚子账号注册信息】结束,i={},clientInfo={},clientBalanceInfo={}", i, clientInfo, clientBalanceInfo);
	}

}
