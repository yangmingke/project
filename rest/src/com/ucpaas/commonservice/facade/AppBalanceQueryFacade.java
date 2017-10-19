package com.ucpaas.commonservice.facade;

import com.ucpaas.commonservice.model.AppBalanceInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;


/**
 * 应用余额查询服务接口
 * @author luke
 *
 */
public interface AppBalanceQueryFacade {
	
	/**
	 * 根据appSid获取应用余额信息
	 * @param appSid
	 * @return ResultInfo
	 * 
	 * <p>
	 * appSid为空,返回actionResult=false;errorCode=ErrorCode.C121001	<br/>
	 * 操作成功,返回actionResult=true;errorCode=ErrorCode.C100000	<br/>
	 * 系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099	<br/>
	 *
	 */
	ResultInfo<AppBalanceInfo> getByAppSid(String appSid);

}
