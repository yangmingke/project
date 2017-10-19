package com.ucpaas.commonservice.facade;

import com.ucpaas.commonservice.model.TestClientInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;

/**
 * 测试子账号查询服务接口
 * 
 * @author luke
 * 
 */
public interface TestClientQueryFacade {

	/**
	 * 查询应用下的测试子账号
	 * 
	 * @param appSid
	 * @return ResultInfo
	 *         <p>
	 *         appSid为空,返回actionResult=false;errorCode=ErrorCode.C121001 <br/>
	 *         操作成功,返回actionResult=true;errorCode=ErrorCode.C100000 <br/>
	 *         系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099 <br/>
	 */
	ResultInfo<TestClientInfo> getListByAppSid(String appSid);

	/**
	 * 根据clientNumber查询测试子账号
	 * 
	 * @param clientNumber
	 * @return ResultInfo
	 * 
	 *         <p>
	 *         clientNumber为空,返回actionResult=false;errorCode=ErrorCode.C131001 <br/>
	 *         操作成功,返回actionResult=true;errorCode=ErrorCode.C100000 <br/>
	 *         系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099 <br/>
	 */
	ResultInfo<TestClientInfo> getByClientNumber(String clientNumber);

	/**
	 * 根开发者账号sid查询测试子账号集合 2015-09-18添加
	 * 
	 * @param sid
	 * @return ResultInfo
	 *         <p>
	 *         sid为空,返回actionResult=false;errorCode=ErrorCode.C111001 <br/>
	 *         操作成功,返回actionResult=true;errorCode=ErrorCode.C100000 <br/>
	 *         系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099 <br/>
	 */
	ResultInfo<TestClientInfo> getListBySid(String sid);

}
