package com.ucpaas.commonservice.facade.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.constant.ErrorCode;
import com.ucpaas.commonservice.facade.UserQueryFacade;
import com.ucpaas.commonservice.model.UserInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;
import com.ucpaas.commonservice.service.UserService;

@Service("userQueryFacade")
public class UserQueryFacadeImpl implements UserQueryFacade {
	private static final Logger log = LoggerFactory.getLogger(UserQueryFacadeImpl.class);

	@Resource(name = "userService")
	private UserService userService;

	@Override
	public ResultInfo<UserInfo> getBySidCache(String sid, Boolean isEqual, String status) {
		ResultInfo<UserInfo> resultInfo = new ResultInfo<UserInfo>(ErrorCode.C100000);
		log.debug("【查询用户】开始,sid={},isEqual={},status={}", sid, isEqual, status);
		try {
			if (StringUtils.isEmpty(sid)) {
				resultInfo.setResultFail(ErrorCode.C111001);
			} else {
				resultInfo.setData(this.userService.getBySidCache(sid, isEqual, status));
			}

		} catch (Throwable e) {
			log.error("【查询用户】错误,sid=" + sid + ",isEqual=" + isEqual + ",status=" + status + ",resultInfo=" + resultInfo,e);
			resultInfo.setResultFail(ErrorCode.C200099);
		}
		log.debug("【查询用户】结束,sid={},isEqual={},status={},resultInfo={}", sid, isEqual, status, resultInfo);
		log.info("【查询用户】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
		return resultInfo;
	}

	@Override
	public ResultInfo<UserInfo> getByPwdUserName(String password, String userName){
		ResultInfo<UserInfo> resultInfo = new ResultInfo<UserInfo>(ErrorCode.C100000);
		log.debug("【查询用户】开始,password={},userName={}", password,userName);
		try {
			if(StringUtils.isEmpty(password)){
				resultInfo.setResultFail(ErrorCode.C111002);
			}else if(StringUtils.isEmpty(userName)){
				//email或mobile存放在username中
				resultInfo.setResultFail(ErrorCode.C111003);
			}else {
				resultInfo.setListData(this.userService.getByPwdUserName(password,userName));
			}

		} catch (Throwable e) {
			log.error("【查询用户】错误,password=" + password +",userName="+userName + ",resultInfo=" + resultInfo,e);
			resultInfo.setResultFail(ErrorCode.C200099);
		}
		log.debug("【查询用户】结束,password={},userName={}", password,userName, resultInfo);
		log.info("【查询用户】结束,result={},errorCode={},timeCount={}",resultInfo.isActionResult(),resultInfo.getErrorCode(),resultInfo.getTimeCount());
		return resultInfo;
	}



}
