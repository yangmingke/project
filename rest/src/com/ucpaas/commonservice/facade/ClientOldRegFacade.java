package com.ucpaas.commonservice.facade;

import java.util.Map;

import com.ucpaas.commonservice.model.ClientBalanceInfo;
import com.ucpaas.commonservice.model.ClientInfo;

/**
 * rest2014注册子账号服务接口
 * <br/>
 * 说明：调存ucpaas库下储过程注册client相关信息:<br/>
 * 1.子账号表：tb_ucpaas_client<br/>
 * 2.子账号余额表：tb_bill_client_balance<br/>
 * 3.应用手机号码池表：tb_ucpaas_client_pool(保证应用下手机号码唯一)<br/>
 * 创建时间：2016-03-22<br/>
 * 
 * @author luke
 * 
 */
public interface ClientOldRegFacade {
	/**
	 * rest2014注册子账号
	 * <p>
	 * 2016-03-22<p>
	 * 2016-05-11对接计费中间件client充值接口<p>
	 * 1.clientbalance>0<p>
	 *  先注册client，clientbalance，其中balance=0,<p>
	 *  通过计费中间件的client充值接口给clientbalance充值。<p>
	 * 2.clientbalance=0<p>
	 * 	注册client，clientbalance，其中balance=0,<p>
	 * 	不需要调用计费中间件的client充值接口<p>
	 * 
	 * 
	 * @param clientInfo
	 * @param clientBalanceInfo
	 * @return	Map<String, Object>
	 * 
	 *  <p>
	 *  返回map示例：
	 *  注册client成功：{po_code=0, po_client_number=22345050000031}
	 *	应用下手机号已存在：{po_code=1, po_client_number=null}
	 *	应用下friendly_name已存在：{po_code=2, po_client_number=null}
	 *	系统内部错误：{po_code=200099, po_desc=系统内部错误}
	 * 
	 */
	Map<String, Object> clientReg(ClientInfo clientInfo,ClientBalanceInfo clientBalanceInfo);

}
