package com.ucpaas.commonservice.facade;

import com.ucpaas.commonservice.model.UserBalanceInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;

/**
 * 开发者用户余额查询服务接口
 * 
 * @author luke
 * 
 */

public interface UserBalanceQueryFacade {

	/**
	 * 根据用户sid查询用户余额记录
	 * 
	 * @param sid
	 *            主账号id
	 * @return ResultInfo
	 * 
	 *         <p>
	 *         sid为空,返回actionResult=false;errorCode=ErrorCode.C111001 <br/>
	 *         操作成功,返回actionResult=true;errorCode=ErrorCode.C100000 <br/>
	 *         系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099 <br/>
	 */
	ResultInfo<UserBalanceInfo> getBySid(String sid);

}
