package com.ucpaas.commonservice.facade;

import java.util.Map;

import com.ucpaas.commonservice.model.ClientBalanceInfo;
import com.ucpaas.commonservice.model.ClientInfo;

/**
 * rest2014注册子账号服务接口全部接管
 * <br/>
 * 说明：调存ucpaas库下储过程注册client相关信息:<br/>
 * 134库
 * 1.子账号表：tb_ucpaas_client<br/>
 * 2.子账号余额表：tb_bill_client_balance<br/>
 * 3.应用手机号码池表：tb_ucpaas_client_pool(保证应用下手机号码唯一)<br/>
 * 110分库
 * 1、子账号分库：tb_ucpaas_client_XXX <br/>
 * 2、测试账号汇总表 tb_ucpaas_test_client <br/>
 * 3、带手机号码的反向表 mobile是存在时 （mobile存在时使用）<br/>
 * 4、带client_number的方向表  （必填）<br/>
 * 创建时间：2016-03-22<br/>
 * 
 * @author liuLu
 * 
 */
public interface ClientDoubleRegFacade {
	/**
	 * rest2014注册子账号
	 * <p>
	 * 2016-03-22<p>
	 * 2016-05-11对接计费中间件client充值接口<p>
	 * 2016年6月29日 接口同时写入134 和110同时成功才成功
	 * 1.clientbalance>0<p>
	 *  先注册client，clientbalance，其中balance=0,<p>
	 *  通过计费中间件的client充值接口给clientbalance充值。<p>
	 * 2.clientbalance=0<p>
	 * 	注册client，clientbalance，其中balance=0,<p>
	 * 	不需要调用计费中间件的client充值接口<p>
	 * 3.134入库成功后，写110数据库
	 * 4.110入库成功 返回成功，110入库失败 ，回滚134数据库数据
	 * 
	 * @param clientInfo
	 * @param clientBalanceInfo
	 * @return	Map<String, Object>
	 * 
	 *  <p>
	 *  返回map示例：
	 *  注册client成功：{po_code=0, po_client_number=22345050000031}
	 *	应用下手机号已存在：{po_code=1, po_client_number=null}或者 {po_code=null, po_client_number=null}
	 *	应用下friendly_name已存在：{po_code=2, po_client_number=null}
	 *	系统内部错误：{po_code=200099, po_desc=系统内部错误}
	 * 
	 */
	Map<String, Object> clientReg(ClientInfo clientInfo,ClientBalanceInfo clientBalanceInfo);

}
