package com.ucpaas.commonservice.facade.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.constant.ErrorCode;
import com.ucpaas.commonservice.facade.UserBalanceQueryFacade;
import com.ucpaas.commonservice.model.UserBalanceInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;
import com.ucpaas.commonservice.service.UserBalanceService;

@Service("userBalanceQueryFacade")
public class UserBalanceQueryFacadeImpl implements UserBalanceQueryFacade {
	private static final Logger log = LoggerFactory.getLogger(UserBalanceQueryFacadeImpl.class);
	
	@Resource(name="userBalanceService")
	private UserBalanceService userBalanceService;

	@Override
	public ResultInfo<UserBalanceInfo> getBySid(String sid){
		ResultInfo<UserBalanceInfo> resultInfo = new ResultInfo<UserBalanceInfo>(ErrorCode.C100000);
		log.debug("【查询用户余额】开始,sid={}", sid);
		try {
			if (StringUtils.isEmpty(sid)) {
				resultInfo.setResultFail(ErrorCode.C111001);
			} else {
				resultInfo.setData(this.userBalanceService.getBySid(sid));
			}

		} catch (Throwable e) {
			log.error("【查询用户余额】错误,sid=" + sid + ",resultInfo=" + resultInfo,e);
			resultInfo.setResultFail(ErrorCode.C200099);
		}
		log.debug("【查询用户余额】结束,sid={},resultInfo={}", sid, resultInfo);
		log.info("【查询用户余额】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
		return resultInfo;
	}

}
