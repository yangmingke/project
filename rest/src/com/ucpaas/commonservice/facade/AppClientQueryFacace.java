package com.ucpaas.commonservice.facade;

import com.ucpaas.commonservice.model.AppClientInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;

/**
 * 应用子账号总表查询服务接口
 * @author luke
 *
 */
public interface AppClientQueryFacace {
	
	/**
	 * 根据应用id分页查询记录集合
	 * 默认查询非0状态的记录
	 * client状态  0:关闭，1:正常，2:充值未平账（正常）
	 * 
	 * @param appSid	应用id
	 * @param start		起始行
	 * @param pageSize	每页行数，最大值100
	 * @return			ResultInfo
	 * 
	 * <p>
	 *  appSid为空,返回actionResult=false;errorCode=ErrorCode.C121001	<br/>
	 *  start为空,返回actionResult=false;errorCode=ErrorCode.C200004		<br/>
	 *  pageSize为空,返回actionResult=false;errorCode=ErrorCode.C200005	<br/>
	 *  操作成功,返回actionResult=true;errorCode=ErrorCode.C100000		<br/>
	 *  系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099	<br/>
	 * 
	 */
	ResultInfo<AppClientInfo> getPageListByAppSid(String appSid, Integer start, Integer pageSize);
	
	/**
	 * 根据appSid查询统计应用下子账号总数
	 * 应用下子账号总数的数据有延时
	 * @param appSid
	 * @return	ResultInfo
	 * 
	 * <p>
	 *  appSid为空,返回errorCode=ErrorCode.C121001	<br/>
	 *  操作成功,返回errorCode=ErrorCode.C100000		<br/>
	 *  系统内部错误,返回errorCode=ErrorCode.C200099	<br/>
	 * 
	 */
	ResultInfo<Integer> getAppClientCountByAppSid(String appSid);

}
