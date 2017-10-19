package com.ucpaas.commonservice.facade;

import com.ucpaas.commonservice.model.AppBalanceInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;

/**
 * 应用余额服务接口
 * @author luke
 *
 */

public interface AppBalanceFacade {
	/**
	 * 根据appSid更新应用余额信息
	 * @param appBalanceInfo
	 * @return	ResultInfo
	 * 
	 * <p>
	 * appBalanceInfo==null,返回actionResult=false;errorCode=ErrorCode.C200001	<br/>
	 * 操作成功,返回actionResult=true;errorCode=ErrorCode.C100000;				
	 * 更新1条记录,affectedRows=1;								
	 * 更新0条记录,affectedRows=0;								<br/>
	 * 系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099				<br/>
	 */
	ResultInfo<AppBalanceInfo> updateByAppSid(AppBalanceInfo appBalanceInfo);

}
