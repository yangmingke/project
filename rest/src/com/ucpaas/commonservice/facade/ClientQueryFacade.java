package com.ucpaas.commonservice.facade;

import com.ucpaas.commonservice.model.ClientInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;

/**
 * 子账号查询服务接口
 * 
 * @author luke
 * 
 */
public interface ClientQueryFacade {

	/**
	 * 通过clientNumber获取client
	 * 
	 * 
	 * @param clientNumber
	 * @return ResultInfo
	 * 
	 *         <p>
	 *         clientNumber为空,返回actionResult=false;errorCode=ErrorCode.C131001 <br/>
	 *         操作成功,返回actionResult=true;errorCode=ErrorCode.C100000 <br/>
	 *         系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099 <br/>
	 */
	ResultInfo<ClientInfo> getByClientNumber(String clientNumber);

	/**
	 * 根据手机号,应用id查询client 先根据attr=mobile_appSid在缓存中查询反向表， 再从反向表根据uin查询client
	 * 
	 * @param mobile
	 *            手机号
	 * @param appSid
	 *            应用id
	 * @return ResultInfo
	 * 
	 *         <p>
	 *         mobile为空,返回actionResult=false;errorCode=ErrorCode.C131002 <br/>
	 *         appSid为空,返回actionResult=false;errorCode=ErrorCode.C121001 <br/>
	 *         操作成功,返回actionResult=true;errorCode=ErrorCode.C100000 <br/>
	 *         系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099 <br/>
	 */
	ResultInfo<ClientInfo> getByMobileAndAppSidCache(String mobile, String appSid);

	/**
	 * 根据子账号用户id,应用id查询client 先根据attr=userId_appSid在缓存中查询反向表， 再从反向表根据uin查询client
	 * 
	 * @param userId
	 *            client子账号用户id
	 * @param appSid
	 *            应用id
	 * @return ResultInfo
	 *         <p>
	 *         userId为空,返回actionResult=false;errorCode=ErrorCode.C131003 <br/>
	 *         appSid为空,返回actionResult=false;errorCode=ErrorCode.C121001 <br/>
	 *         操作成功,返回actionResult=true;errorCode=ErrorCode.C100000 <br/>
	 *         系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099 <br/>
	 */
	ResultInfo<ClientInfo> getByUserIdAndAppSidCache(String userId, String appSid);
	
	
	
	/**
	 * 测试用
	 * @param clientNumber
	 * @return
	 */
	ResultInfo<ClientInfo> getByClientNumberCacheTest(String clientNumber);
	
	
	

}
