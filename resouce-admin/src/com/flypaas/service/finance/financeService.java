package com.flypaas.service.finance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.flypaas.model.TbRsBillFlow;
import com.flypaas.util.PageContainer;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年2月13日 下午6:49:48
* 类说明
*/
public interface financeService{
	/*-------------------------------资源节点账单表-----------------------------------*/
	/**
	 * 查询资源节点账单表
	 * 		日流量消耗   (netSid,ip,dateTime)  默认当天
	 * @param para
	 * @return
	 */
	public PageContainer queryResourceFlowDay(String keyWord,String dateTime,int page);
	
	/**
	 * 统计流量日消耗账单
	 * @param para
	 * @return
	 */
	public Map<String,String> statisticsFlowDay(String dateTime);
	
	/**
	 * 查询资源节点账单表
	 * 		月流量消耗   (netSid,ip,monthTime) 默认当月
	 * @param para
	 * @return
	 */
	public PageContainer queryResourceFlowMonth(String keyWord,String dateTime,int page);
	
	/**
	 * 查询某月的账单数量
	 * @param monthTime
	 * @return
	 */
	public int queryCount(String monthTime);
	
	
	/*-------------------------------------------------------资源方账单表------------------------------------------------*/
	/**
	 * 查询资源方账单表
	 * 		日流量消耗   (netSid,ip,dateTime)  默认当天
	 * @param para
	 * @return
	 */
	public PageContainer queryResourceSideFlowDay(List<String> sidList,String dateTime,int page);
	
	
	/**
	 * 查询资源方账单表
	 * 		月流量消耗   (netSid,ip,monthTime) 默认当月
	 * @param para
	 * @return
	 */
	public PageContainer queryResourceSideFlowMonth(List<String> sidList,String monthTime,int page);
	
	
	/*----------------------------账号管理---------------------------*/
	
    /**
     * 查询账号数量
     * @param para
     * @return
     */

	public PageContainer queryAccount(Map<String, Object> para, int page);

	public int unLockorLock(String netSid, String status);

	public Map enchashment(String netSid);

	/**
	 * 提款申请
	 * @param tbRsBillFlow
	 * @return 
	 */
	public int createApply(TbRsBillFlow tbRsBillFlow);

	/**
	 * 全部提款记录
	 * @param para 
	 * @return
	 */
	public PageContainer queryAllBillFlow(Map<String, Object> para);

	/**
	 * 提款记录
	 * @param para 
	 * @return
	 */
	public Map<String, String> queryBillFlow(String netSid);

	/**
	 * 提现审批
	 * @param netSid
	 * @param id
	 * @param state
	 * @param actualFee 
	 * @param demo 
	 * @return 
	 */
	public int updateFlow(String netSid, String id, String state, long actualFee, String demo);

	public List<Map<String, String>> queryAccountByName(String netName);

	public List<Map<String, String>> findRsAccountBySids(ArrayList arrayList);
	
}
