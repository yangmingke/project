package com.ucpaas.commonservice.facade;

import com.ucpaas.commonservice.model.AppInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;

/**
 * 应用服务接口
 * @author luke
 *
 */
public interface AppFacade {
	
	
	/**
	 * 更新应用信息,同时删除缓存中的数据
	 * @param appInfo
	 * @return	ResultInfo
	 * 
	 * <p>
	 * appInfo==null,返回actionResult=false;errorCode=ErrorCode.C200001	<br/>
	 * 操作成功,返回actionResult=true;errorCode=ErrorCode.C100000;				
	 * 更新1条记录,affectedRows=1;								
	 * 更新0条记录,affectedRows=0;						<br/>
	 * 系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099		<br/>
	 * 
	 */
	ResultInfo<AppInfo> updateByAppSidCache(AppInfo appInfo);
	
	/**
	 * 根据appSid更新应用下的子账号总数
	 * 异步调用
	 * @param appSid		应用主键
	 * @param isAdd			增加为true，减少为false
	 * @param clientCount	增加或减少的子账号数量
	 * @return	ResultInfo
	 * 
	 * <p>
	 * appSid为空,返回actionResult=false;errorCode=ErrorCode.C121001	<br/>
	 * clientCount为空,返回actionResult=false;errorCode=ErrorCode.C121002	<br/>
	 * 操作成功,返回actionResult=true;errorCode=ErrorCode.C100000;				
	 * 更新1条记录,affectedRows=1;								
	 * 更新0条记录,affectedRows=0;						<br/>
	 * 系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099		<br/>
	 */
	void updateClientCount(String appSid,boolean isAdd,Integer clientCount);
	
	
	

}
