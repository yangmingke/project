package com.ucpaas.commonservice.facade;

import com.ucpaas.commonservice.model.ClientBalanceInfo;
import com.ucpaas.commonservice.model.ClientInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;

/**
 * rest2015注册子账号服务接口
 * 
 * <br/>
 * 说明：在client分表创建子账号等信息:<br/>
 * 1.反向表：userid_appid;mobile_appid<br/>
 * 2.子账号分表：tb_ucpaas_client_(0-999)<br/>
 * 3.子账号余额分表：tb_bill_client_balance_(0-999)<br/>
 * 创建时间：2016-03-22<br/>
 * @author luke
 * 
 */
public interface ClientRegFacade {
	
	/**
	 * rest2015注册子账号<br/>
	 * 2016-03-22
	 * 
	 * @param clientInfo，开发者token(userToken)需要赋值，供生成新clientToken
	 * @param clientBalanceInfo，值为null，则不生成clientbalance信息
	 * @return
	 * 
	 * <p>
	 *         appSid为空,返回actionResult=false;errorCode=ErrorCode.C121001 <br/>
	 *         ClnPrefix为空,返回actionResult=false;errorCode=ErrorCode.C131010 <br/>
	 *         应用下mobile已存在,返回actionResult=false;errorCode=ErrorCode.C121003 <br/>
	 *         应用下userid已存在,返回actionResult=false;errorCode=ErrorCode.C121004 <br/>
	 *         注册子账号错误,返回actionResult=false;errorCode=ErrorCode.C131009 <br/>
	 *         
	 *         操作成功,返回actionResult=true;errorCode=ErrorCode.C100000 <br/>
	 *         系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099 <br/>
	 *         userid为空，会默认赋上clientNumber的值
	 *         
	 *         
	 * 
	 * 
	 */
	ResultInfo<ClientInfo> clientReg(ClientInfo clientInfo,ClientBalanceInfo clientBalanceInfo);
	
	


}
