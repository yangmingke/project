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
import com.ucpaas.commonservice.model.UserInfo;
import com.ucpaas.commonservice.model.base.RespInfo;
import com.ucpaas.commonservice.service.UserService;

@RestController
@RequestMapping("/ucms")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger("http_log");
	@Autowired
	private UserService userService;

	@RequestMapping(value = "user/{sid}", method = RequestMethod.GET)
	@ResponseBody
	public RespInfo<UserInfo> getUserBySid(@PathVariable String sid) {
		logger.info("【查询主账号信息】开始 sid={}",sid);
		RespInfo<UserInfo> resp = new RespInfo<UserInfo>(ErrorCode.C100000);
		UserInfo userInfo = null;
		try {
			userInfo = userService.getBySidCache(sid, null, null);
			if (userInfo != null) {
				resp.setData(userInfo);
			} else {
				resp.setErrorCode(ErrorCode.C111004);
			}
		} catch (Exception e) {
			resp.setErrorCode(ErrorCode.C200099);
			logger.error("【查询主账号信息】错误 sid={}, e={}",sid,e);
		}
		logger.info("【查询主账号信息】结束 resp={}",resp);
		return resp;

	}

}
