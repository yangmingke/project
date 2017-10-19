package com.ucpaas.commonservice.facade;

import com.ucpaas.commonservice.model.UserBalanceInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;

/**
 * 开发者用户余额服务接口
 * 
 * @author luke
 * 
 */
public interface UserBalanceFacade {

	/**
	 * 根据sid更新用户余额记录
	 * 
	 * @param userBalance
	 *            用户余额
	 * @return ResultInfo
	 * 
	 *         <p>
	 *         userBalance==null,返回actionResult=false;errorCode=ErrorCode.C200001 <br/>
	 *         操作成功,返回actionResult=true;errorCode=ErrorCode.C100000; 更新1条记录,affectedRows=1;
	 *         更新0条记录,affectedRows=0; <br/>
	 *         系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099 <br/>
	 */
	ResultInfo<UserBalanceInfo> updateBySid(UserBalanceInfo userBalance);

}
