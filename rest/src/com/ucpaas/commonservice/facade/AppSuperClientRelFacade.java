package com.ucpaas.commonservice.facade;

import com.ucpaas.commonservice.model.AppSuperClientRelInfo;
import com.ucpaas.commonservice.model.ClientInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;

/**
 * 应用超级子账号关系服务接口
 * <br/>
 * 说明：一个应用下只有一个子账号（该子账号为应用下的超级子账号）<br/>
 * 关系表：ucpaas.tb_ucpaas_app_superclient_rel<br/>
 * 创建时间：2016-03-25<br/>
 */
public interface AppSuperClientRelFacade {
	
	
	/**
	 * 根据appSid查询应用超级子账号关联关系
	 * @param appSid  应用id
	 * @return
	 * 
	 * 	      <p>
	 *         appSid为空,返回actionResult=false;errorCode=ErrorCode.C121001 <br/>
	 *         操作成功,返回actionResult=true;errorCode=ErrorCode.C100000 <br/>
	 *         系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099 <br/>
	 * 
	 */
	ResultInfo<AppSuperClientRelInfo> getByAppSid(String appSid);
	
	/**
	 * 新增应用和子账号关联关系
	 * 
	 * @param appSuperClientRelInfo
	 * @return
	 * 
	 *         <p>
	 *         appSid为空,返回actionResult=false;errorCode=ErrorCode.C121001 <br/>
	 *         clientNumber为空,返回actionResult=false;errorCode=ErrorCode.C131001	<br/>
	 *         操作成功,返回actionResult=true;errorCode=ErrorCode.C100000;
	 *         插入1条记录,affectedRows=1;		 <br/>
	 *         系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099 <br/>
	 */
	ResultInfo<AppSuperClientRelInfo> insert(AppSuperClientRelInfo appSuperClientRelInfo);
	
	
	/**
	 * 根据appSid查询超级子账号信息
	 * @param appSid	应用id
	 * @return	
	 * 
	 * 	     <p>
	 *         appSid为空,返回actionResult=false;errorCode=ErrorCode.C121001 <br/>
	 *         操作成功,返回actionResult=true;errorCode=ErrorCode.C100000 <br/>
	 *         系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099 <br/>
	 */
	ResultInfo<ClientInfo> getClientByAppSid(String appSid);
	
	
	

}
