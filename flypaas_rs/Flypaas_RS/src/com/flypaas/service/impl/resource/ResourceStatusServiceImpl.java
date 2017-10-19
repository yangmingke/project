package com.flypaas.service.impl.resource;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.cdr.dao.TrafficNodeConsumMapper;
import com.flypaas.dao.TbRsRTPPRealtimeStatusMapper;
import com.flypaas.model.TbRsRTPPRealtimeStatus;
import com.flypaas.service.resource.ResourceStatusService;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2016年12月23日 上午9:40:58
* 类说明
*/
@Service("/resourceStatusServiceImpl")
public class ResourceStatusServiceImpl implements ResourceStatusService{
	@Autowired
	private TbRsRTPPRealtimeStatusMapper tbRsRTPPRealtimeStatusMapper;
	@Autowired
	private TrafficNodeConsumMapper trafficNodeConsumMapper;

	@Override
	public TbRsRTPPRealtimeStatus queryStatusByIp(String ip) {
		
		return tbRsRTPPRealtimeStatusMapper.queryStatusByIp(ip);
	}
	@Override
	public List<Map<String,String>> queryStatusByIpTime(String ip,String dateTime) {
		Map<String,String> para =new HashMap<String,String>();
		para.put("ip", ip);
		para.put("dateTime", dateTime);
		List<Map<String, String>> result = trafficNodeConsumMapper.queryNodeTodayTraffic(para);
		List<Map<String, String>> nodeTodayTrafficList = new ArrayList<Map<String, String>>(24);
		for(Integer hour = 0; hour < 24; hour ++){//按小时存放流量数据
			Map<String, String> temp = new HashMap<String, String>();
			temp.put("hour", hour.toString());
			temp.put("total", "0");
			temp.put("traffic_in", "0");
			temp.put("traffic_out", "0");
			nodeTodayTrafficList.add(hour, temp);
		}
		for(Map nodeTraffic : result){
			Integer hour = (Integer) nodeTraffic.get("hour");
			Map<String, String> temp = nodeTodayTrafficList.get(hour);
			temp.putAll(nodeTraffic);
		}
		return nodeTodayTrafficList;
	}
}
