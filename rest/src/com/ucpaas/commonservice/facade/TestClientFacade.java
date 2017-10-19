package com.ucpaas.commonservice.facade;

import com.ucpaas.commonservice.model.TestClientInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;

/**
 * 测试子账号服务接口
 * 
 * @author luke
 * 
 */
public interface TestClientFacade {

	/**
	 * 根据clientNumber更新测试子账号
	 * 
	 * @param testClientInfo
	 * @return ResultInfo
	 * 
	 *         <p>
	 *         testClientInfo==null,返回actionResult=false;errorCode=ErrorCode.
	 *         C200001 <br/>
	 *         操作成功,返回actionResult=true;errorCode=ErrorCode.C100000;
	 *         更新1条记录,affectedRows=1; 更新0条记录,affectedRows=0; <br/>
	 *         系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099 <br/>
	 */
	ResultInfo<TestClientInfo> updateByClientNumber(TestClientInfo testClientInfo);

	/**
	 * 插入测试子账号
	 * 
	 * @param testClientInfo
	 * @return ResultInfo
	 * 
	 *         <p>
	 *         testClientInfo==null,返回actionResult=false;errorCode=ErrorCode.
	 *         C200001 <br/>
	 *         clientNumber为空,返回actionResult=false;errorCode=ErrorCode.C131001 <br/>
	 *         clientSid为空,返回actionResult=false;errorCode=ErrorCode.C131005 <br/>
	 *         操作成功,返回actionResult=true;errorCode=ErrorCode.C100000;affectedRows
	 *         =1;<br/>
	 *         系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099 <br/>
	 */
	ResultInfo<TestClientInfo> insert(TestClientInfo testClientInfo);

}
