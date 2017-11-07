package com.flypaas.service.operation;

import java.util.List;
import java.util.Map;

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
	
}
