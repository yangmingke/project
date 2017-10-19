package com.ucpaas.commonservice.facade.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.constant.ErrorCode;
import com.ucpaas.commonservice.facade.ClientBalanceFacade;
import com.ucpaas.commonservice.model.ClientBalanceInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;
import com.ucpaas.commonservice.service.ClientBalanceService;

@Service("clientBalanceFacade")
public class ClientBalanceFacadeImpl implements ClientBalanceFacade {
	private static final Logger log = LoggerFactory.getLogger(ClientBalanceFacadeImpl.class);

	@Resource(name = "clientBalanceService")
	private ClientBalanceService clientBalanceService;

	@Override
	public ResultInfo<ClientBalanceInfo> updateByClientNumber(ClientBalanceInfo clientBalanceInfo) {
		ResultInfo<ClientBalanceInfo> resultInfo = new ResultInfo<ClientBalanceInfo>(ErrorCode.C100000);
		log.debug("【更新子账号余额】开始,clientBalanceInfo={}", clientBalanceInfo);

		try {
			if (clientBalanceInfo == null) {
				resultInfo.setResultFail(ErrorCode.C200001);
			}else {
				clientBalanceInfo.setUpdateDate(new Date());
				resultInfo.setAffectedRows(this.clientBalanceService.updateByClientNumber(clientBalanceInfo));
			}

		} catch (Throwable e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【更新子账号余额】错误,clientBalanceInfo=" + clientBalanceInfo + ",resultInfo=" + resultInfo, e);

		}
		
		log.debug("【更新子账号余额】结束,clientBalanceInfo={},resultInfo={}", clientBalanceInfo, resultInfo);
		log.info("【更新子账号余额】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
		return resultInfo;
	}


}
