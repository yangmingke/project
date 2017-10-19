package com.ucpaas.commonservice.facade.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.constant.ErrorCode;
import com.ucpaas.commonservice.facade.ClientBalanceQueryFacade;
import com.ucpaas.commonservice.model.ClientBalanceInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;
import com.ucpaas.commonservice.service.ClientBalanceService;

@Service("clientBalanceQueryFacade")
public class ClientBalanceQueryFacadeImpl implements ClientBalanceQueryFacade {
	private static final Logger log = LoggerFactory.getLogger(ClientBalanceQueryFacadeImpl.class);

	@Resource(name = "clientBalanceService")
	private ClientBalanceService clientBalanceService;

	@Override
	public ResultInfo<ClientBalanceInfo> getByClientNumber(String clientNumber) {
		ResultInfo<ClientBalanceInfo> resultInfo = new ResultInfo<ClientBalanceInfo>(ErrorCode.C100000);
		log.debug("【查询应用】开始,clientNumber={}", clientNumber);

		try {
			if (StringUtils.isEmpty(clientNumber)) {
				resultInfo.setResultFail(ErrorCode.C131001);
			} else {
				resultInfo.setData(this.clientBalanceService.getByClientNumber(clientNumber));
			}

		} catch (Throwable e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【查询应用】错误,clientNumber=" + clientNumber + ",resultInfo=" + resultInfo, e);

		}
		log.debug("【查询应用】结束,clientNumber={},resultInfo={},", clientNumber, resultInfo);
		log.info("【查询应用】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
		return resultInfo;
	}

}
