package com.ucpaas.commonservice.facade;

import com.ucpaas.commonservice.model.UserInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;

/**
 * 开发者用户查询服务接口
 * 
 * @author luke
 * 
 */
public interface UserQueryFacade {

	/**
	 * 从缓存查询用户信息
	 * 
	 * @param sid
	 *            主键
	 * @param isEqual
	 *            后接查询状态,等于为true,不等于为false;不接查询状态,isEqual=null
	 * @param status
	 *            用户状态：0:注册未激活,1:邮箱已激活, 5:锁定 6:关闭
	 * @return ResultInfo
	 * 
	 *         <p>
	 *         sid为空,返回actionResult=false;errorCode=ErrorCode.C111001 <br/>
	 *         操作成功,返回actionResult=true;errorCode=ErrorCode.C100000 <br/>
	 *         系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099 <br/>
	 */
	ResultInfo<UserInfo> getBySidCache(String sid, Boolean isEqual, String status);
	
	/**
	 * 根据密码，email或mobile，查询开发者用户信息
	 * email或mobile存放在username中
	 * 
	 * 内部测试demo登录定制接口，sdk调用
	 * 2015-09-21添加
	 * @param password
	 * @param userName	存放email或mobile
	 * @return	ResultInfo
	 * 
	 *         <p>
	 *         password为空,返回actionResult=false;errorCode=ErrorCode.C111002 <br/>
	 *         userName为空,返回actionResult=false;errorCode=ErrorCode.C111003 <br/>
	 *         操作成功,返回actionResult=true;errorCode=ErrorCode.C100000 <br/>
	 *         系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099 <br/>
	 */
	ResultInfo<UserInfo> getByPwdUserName(String password, String userName);

}
