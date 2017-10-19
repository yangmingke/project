package com.ucpaas.commonservice.facade;

import com.ucpaas.commonservice.model.ClientBalanceInfo;
import com.ucpaas.commonservice.model.ClientInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;

/**
 * 子账号服务接口
 * 
 * @author luke
 * 
 */
public interface ClientFacade {
	/**
	 * 根据ClientNumber更新ClientInfo
	 * (注意，不包括更新手机号,更新手机号调用updateMobileCache接口)
	 * 同时调用第三方接口，删除第三方缓存中的client信息
	 * @param clientInfo
	 * @return ResultInfo
	 *         <p>
	 *         clientInfo==null,返回actionResult=false;errorCode=ErrorCode.C200001
	 *         <br/>
	 *         clientNumber为空,返回actionResult=false;errorCode=ErrorCode.C131001	<br/>
	 *         操作成功,返回actionResult=true;errorCode=ErrorCode.C100000;
	 *         更新1条记录,affectedRows=1; 更新0条记录,affectedRows=0; <br/>
	 *         系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099 <br/>
	 */
	ResultInfo<ClientInfo> updateByClientNumberCache(ClientInfo clientInfo);

	/**
	 * 根据userId和appSid更新子账号信息
	 * (注意，不包括更新手机号,更新手机号调用updateMobileCache接口)
	 * 同时调用第三方接口，删除第三方缓存中的client信息
	 * @param clientInfo
	 * @return ResultInfo
	 * 
	 *         <p>
	 *         clientInfo==null,返回actionResult=false;errorCode=ErrorCode.C200001
	 *         <br/>
	 *         userId为空,返回actionResult=false;errorCode=ErrorCode.C131003 <br/>
	 *         appSid为空,返回actionResult=false;errorCode=ErrorCode.C121001 <br/>
	 *         操作成功,返回actionResult=true;errorCode=ErrorCode.C100000;
	 *         affectedRows=1; <br/>
	 *         系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099 <br/>
	 */
	ResultInfo<ClientInfo> updateByUserIdAndAppSidCache(ClientInfo clientInfo);
	

	/**
	 * 更新client子账号的手机号码；
	 * 如果手机号为空，则删除反向表的旧数据；
	 * 如果手机号不为空，则删除反向表的旧数据，插入新数据。
	 * 同时调用第三方接口，删除第三方缓存中的client信息
	 * @param clientInfo	存放oldMobile
	 * @param newMobile
	 * @return
	 * 
	 *         <p>
	 *         clientInfo==null,返回actionResult=false;errorCode=ErrorCode.C200001
	 *         <br/>
	 *         appSid为空,返回actionResult=false;errorCode=ErrorCode.C121001 <br/>
	 *         uin为空,返回actionResult=false;errorCode=ErrorCode.C131004 <br/>
	 *         操作成功,返回actionResult=true;errorCode=ErrorCode.C100000;
	 *         affectedRows=n; <br/>
	 *         系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099 <br/>
	 */
	ResultInfo<ClientInfo> updateMobileCache(ClientInfo clientInfo,String newMobile);
	
	
	
	
	/**
	 * 根据userId关闭client
	 * 1.将mobile和userId设为空
	 * 2.删除反向表中数据，删除缓存中数据
	 * 
	 * 3.调用第三方接口，删除第三方缓存中的client信息
	 * 
	 * 2015-11-02添加
	 * @param clientInfo
	 * @return
	 */
	/**
	 * 关闭client
	 * 
	 * 1.删除反向表中mobile、userId信息。
	 * 2.更新client状态为已关闭，mobile、userId设为空字符串。client状态  0:关闭，1:正常，2:充值未平账
	 * 3.更新clientbalance状态。0：冻结 1 正常
	 * 4.调用第三方接口，删除第三方缓存中的client信息(刘晓健)
	 * 
	 * 2015-11-02添加
	 * @param clientInfo
	 * @param clientBalanceInfo
	 * @return
	 * 
	 *         <p>
	 *         clientInfo==null,返回actionResult=false;errorCode=ErrorCode.C200001
	 *         <br/>
	 *         appSid为空,返回actionResult=false;errorCode=ErrorCode.C121001 <br/>
	 *         uin为空,返回actionResult=false;errorCode=ErrorCode.C131004 <br/>
	 *         balance的uin为空,返回actionResult=false;errorCode=ErrorCode.C132004 <br/>
	 *         client和balance的uin不一致,返回actionResult=false;errorCode=ErrorCode.C131008 <br/>
	 *         
	 *         操作成功,返回actionResult=true;errorCode=ErrorCode.C100000;
	 *         affectedRows=n; <br/>
	 *         系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099 <br/>
	 */
	ResultInfo<ClientInfo> closeClient(ClientInfo clientInfo,ClientBalanceInfo clientBalanceInfo);
	
	
	
	
	
	
	



}
