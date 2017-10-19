package com.ucpaas.commonservice.facade;

import com.ucpaas.commonservice.model.AppInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;

/**
 * 应用查询服务接口
 * 
 * @author luke
 * 
 */
public interface AppQueryFacade {

	/**
	 * 从缓存查询应用信息 说明：查询的应用信息不包含clientCount的值,查询clientCount调用getByAppSid
	 * 
	 * @param appSid
	 *            应用主键
	 * @param isEqual
	 *            后接查询状态,等于为true,不等于为false;不接查询状态,isEqual=null
	 * @param status
	 *            应用状态：0:未上线 1：审核通过 2：审核不通过 3:已删除 4 待审核 5强制下线，6重新提交
	 * @return ResultInfo
	 * 
	 *         <p>
	 *         appSid为空,返回actionResult=false;errorCode=ErrorCode.C121001 <br/>
	 *         操作成功,返回actionResult=true;errorCode=ErrorCode.C100000 <br/>
	 *         系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099 <br/>
	 */
	ResultInfo<AppInfo> getByAppSidCache(String appSid, Boolean isEqual, String status);

	/**
	 * 查询应用信息
	 * 
	 * @param appSid
	 *            应用主键
	 * @param isEqual
	 *            后接查询状态,等于为true,不等于为false;不接查询状态,isEqual=null
	 * @param status
	 *            应用状态：0:未上线 1：审核通过 2：审核不通过 3:已删除 4 待审核 5强制下线，6重新提交
	 * @return ResultInfo
	 *         <p>
	 *         appSid为空,返回actionResult=false;errorCode=ErrorCode.C121001 <br/>
	 *         操作成功,返回actionResult=true;errorCode=ErrorCode.C100000 <br/>
	 *         系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099 <br/>
	 */
	ResultInfo<AppInfo> getByAppSid(String appSid, Boolean isEqual, String status);

}
