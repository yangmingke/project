package com.ucpaas.commonservice.facade.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.constant.ErrorCode;
import com.ucpaas.commonservice.facade.UserBalanceFacade;
import com.ucpaas.commonservice.model.UserBalanceInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;
import com.ucpaas.commonservice.service.UserBalanceService;

@Service("userBalanceFacade")
public class UserBalanceFacadeImpl implements UserBalanceFacade {
	private static final Logger log = LoggerFactory.getLogger(UserBalanceFacadeImpl.class);
	
	@Resource(name="userBalanceService")
	private UserBalanceService userBalanceService;

	@Override
	public ResultInfo<UserBalanceInfo> updateBySid(UserBalanceInfo userBalance) {
		ResultInfo<UserBalanceInfo> resultInfo = new ResultInfo<UserBalanceInfo>(ErrorCode.C100000);
		log.debug("【更新用户余额】开始,userBalance={}", userBalance);

		try {
			if (userBalance == null) {
				resultInfo.setResultFail(ErrorCode.C200001);
			} else {
				resultInfo.setAffectedRows(this.userBalanceService.updateBySid(userBalance));
			}
		} catch (Throwable e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【更新用户余额】错误,userBalance=" + userBalance + ",resultInfo=" + resultInfo, e);
		}
		log.debug("【更新用户余额】结束,userBalance={}, resultInfo={}", userBalance, resultInfo);
		log.info("【更新用户余额】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
		return resultInfo;
	}

}
