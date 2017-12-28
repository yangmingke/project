package com.flypaas.service.operation;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* @author 作者 yangmingke:
* @version 创建时间：2017年2月6日 下午12:30:33
* 类说明
*/
public interface OperationService {

	/**
	 * 查询节点的丢包情况
	 * @param ip
	 * @param date
	 * @return
	 */
	public Map queryPacketLoss(String ip, String date);

	public List queryAllRTPP();

	public Map queryPacketLossResource(String destIp, String date);

	public Map<String, Object> querySessionPacketLoss(String dateTime, String userSid, String appSid, String cookie);

	public Map<String, Object> queryNodeConcurrent(String date);

	/**
	 * 
	 * @param routeDoamin 路由域
	 * @param routePolicy	路由策略
	 * @param routeId	路由主备: 0主 1备
	 * @param type  流量类型： in、out
	 * @param sessionID 会话ID
	 * @return
	 */
	public Map<String, Object> querySessionSpeed(String routeDoamin, String routePolicy, String routeId,String routeType, String sessionID);

	public Set<String> getDomainList();
	
}
