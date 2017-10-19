package com.ucpaas.commonservice.facade.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.constant.ErrorCode;
import com.ucpaas.commonservice.facade.ClientFacade;
import com.ucpaas.commonservice.model.ClientBalanceInfo;
import com.ucpaas.commonservice.model.ClientInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;
import com.ucpaas.commonservice.service.ClientService;


@Service("clientFacade")
public class ClientFacadeImpl implements ClientFacade {
	private static final Logger log = LoggerFactory.getLogger(ClientFacadeImpl.class);

	@Resource(name = "clientService")
	private ClientService clientService;

	@Override
	public ResultInfo<ClientInfo> updateByClientNumberCache(ClientInfo clientInfo) {
		ResultInfo<ClientInfo> resultInfo = new ResultInfo<ClientInfo>(ErrorCode.C100000);
		log.debug("【更新Client】开始,clientInfo={}", clientInfo);

		try {
			if (clientInfo == null) {
				resultInfo.setResultFail(ErrorCode.C200001);
			}else if(StringUtils.isEmpty(clientInfo.getClientNumber())){
				resultInfo.setResultFail(ErrorCode.C131001);
			}else {
				clientInfo.setUpdateDate(new Date());
				resultInfo.setAffectedRows(this.clientService.updateByClientNumberCache(clientInfo));
			}
		} catch (Throwable e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【更新Client】错误,clientInfo=" + clientInfo + ",resultInfo=" + resultInfo, e);
		}
		
		log.info("【更新Client】结束,clientInfo={}, resultInfo={},", clientInfo, resultInfo);
		log.info("【更新Client】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
		return resultInfo;
	}


	@Override
	public ResultInfo<ClientInfo> updateByUserIdAndAppSidCache(ClientInfo clientInfo) {
		ResultInfo<ClientInfo> resultInfo = new ResultInfo<ClientInfo>(ErrorCode.C100000);
		log.debug("【更新Client】开始, clientInfo={}", clientInfo);

		try {
			if(clientInfo == null){
				resultInfo.setResultFail(ErrorCode.C200001);
			}else if (StringUtils.isEmpty(clientInfo.getUserid())) {
				resultInfo.setResultFail(ErrorCode.C131003);
			} else if (StringUtils.isEmpty(clientInfo.getAppSid())) {
				resultInfo.setResultFail(ErrorCode.C121001);
			} else {
				clientInfo.setUpdateDate(new Date());
				resultInfo.setAffectedRows(this.clientService.updateByUserIdAndAppSidCache(clientInfo));
			}

		} catch (Exception e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【更新Client】错误,clientInfo=" + clientInfo + ",resultInfo=" + resultInfo, e);
		}
		log.info("【更新Client】结束,clientInfo={},resultInfo={}", clientInfo, resultInfo);
		log.info("【更新Client】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
		return resultInfo;
	}


	@Override
	public ResultInfo<ClientInfo> updateMobileCache(ClientInfo clientInfo, String newMobile) {
		ResultInfo<ClientInfo> resultInfo = new ResultInfo<ClientInfo>(ErrorCode.C100000);
		log.debug("【更新Client的mobile】开始, clientInfo={},newMobile={}", clientInfo,newMobile);

		try {
			if(clientInfo == null){
				resultInfo.setResultFail(ErrorCode.C200001);
			}else if (StringUtils.isEmpty(clientInfo.getAppSid())) {
				resultInfo.setResultFail(ErrorCode.C121001);
			}else if(clientInfo.getUin() == null){
				resultInfo.setResultFail(ErrorCode.C131004);
			}else {
				clientInfo.setUpdateDate(new Date());
				resultInfo.setAffectedRows(this.clientService.updateMobileCache(clientInfo, newMobile));
			}

		} catch (Exception e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【更新Client的mobile】错误,clientInfo=" + clientInfo +",newMobile=" + newMobile + ",resultInfo=" + resultInfo, e);
		}
		log.info("【更新Client的mobile】结束,clientInfo={},newMobile={},resultInfo={}", clientInfo,newMobile, resultInfo);
		log.info("【更新Client的mobile】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
		return resultInfo;
	}


	@Override
	public ResultInfo<ClientInfo> closeClient(ClientInfo clientInfo,ClientBalanceInfo clientBalanceInfo) {
		ResultInfo<ClientInfo> resultInfo = new ResultInfo<ClientInfo>(ErrorCode.C100000);
		log.debug("【关闭client】开始, clientInfo={}", clientInfo);

		try {
			if(clientInfo == null){
				resultInfo.setResultFail(ErrorCode.C200001);
			}else if (StringUtils.isEmpty(clientInfo.getAppSid())) {
				resultInfo.setResultFail(ErrorCode.C121001);
			}else if(clientInfo.getUin() == null){
				resultInfo.setResultFail(ErrorCode.C131004);
			}else if(clientBalanceInfo.getUin() == null){
				resultInfo.setResultFail(ErrorCode.C132004);
			}else if(!clientInfo.getUin().equals(clientBalanceInfo.getUin())){
				resultInfo.setResultFail(ErrorCode.C131008);
			}else {
				clientInfo.setUpdateDate(new Date());
				clientBalanceInfo.setUpdateDate(new Date());
				resultInfo.setAffectedRows(this.clientService.closeClient(clientInfo, clientBalanceInfo));				
			}

		} catch (Exception e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【关闭client】错误,clientInfo=" + clientInfo +",clientBalanceInfo="+clientBalanceInfo + ",resultInfo=" + resultInfo, e);
		}
		log.info("【关闭client】结束,clientInfo={},clientBalanceInfo={},resultInfo={}", clientInfo,clientBalanceInfo, resultInfo);
		log.info("【关闭client】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
		return resultInfo;
	}



}
