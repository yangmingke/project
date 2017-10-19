package com.ucpaas.commonservice.facade.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.constant.ErrorCode;
import com.ucpaas.commonservice.facade.UserFacade;
import com.ucpaas.commonservice.model.UserInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;
import com.ucpaas.commonservice.service.UserService;

@Service("userFacade")
public class UserFacadeImpl implements UserFacade {
	private static final Logger log = LoggerFactory.getLogger(UserFacadeImpl.class);

	@Resource(name = "userService")
	private UserService userService;

	@Override
	public ResultInfo<UserInfo> updateBySidCache(UserInfo userInfo) {
		ResultInfo<UserInfo> resultInfo = new ResultInfo<UserInfo>(ErrorCode.C100000);
		log.debug("【更新用户】开始,userInfo={}", userInfo);

		try {
			if (userInfo == null) {
				resultInfo.setResultFail(ErrorCode.C200001);
			} else {
				userInfo.setUpdateDate(new Date());
				resultInfo.setAffectedRows(this.userService.updateBySidCache(userInfo));
			}
		} catch (Throwable e) {
			resultInfo.setResultFail(ErrorCode.C200099);
			log.error("【更新用户】错误,userInfo=" + userInfo + ",resultInfo=" + resultInfo, e);
		}
		log.debug("【更新用户】结束,userInfo={}, resultInfo={}", userInfo, resultInfo);
		log.info("【更新用户】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
		return resultInfo;
	}

}
