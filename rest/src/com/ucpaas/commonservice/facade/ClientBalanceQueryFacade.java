package com.ucpaas.commonservice.facade;

import com.ucpaas.commonservice.model.ClientBalanceInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;

/**
 * 子账号余额查询服务接口
 * 
 * @author luke
 * 
 */

public interface ClientBalanceQueryFacade {

	/**
	 * 根据clientNumber查询子账号余额信息
	 * 
	 * @param clientNumber
	 * @return ResultInfo
	 *         <p>
	 *         clientNumber为空,返回actionResult=false;errorCode=ErrorCode.C131001 <br/>
	 *         操作成功,返回actionResult=true;errorCode=ErrorCode.C100000 <br/>
	 *         系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099 <br/>
	 */
	ResultInfo<ClientBalanceInfo> getByClientNumber(String clientNumber);

}
