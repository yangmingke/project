package com.flypaas.service.resource.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.cdr.dao.TrafficNodeConsumMapper;
import com.flypaas.constant.RouterConstant;
import com.flypaas.dao.TbRsCityMapper;
import com.flypaas.dao.TbRsRTPPMapper;
import com.flypaas.dao.TbRsRTPPNbMapper;
import com.flypaas.dao.TbRsRTPPRealtimeStatusMapper;
import com.flypaas.model.TbRsRTPP;
import com.flypaas.model.TbRsRTPPRealtimeStatus;
import com.flypaas.service.impl.RedisService;
import com.flypaas.service.resource.ResourceService;
import com.flypaas.util.PageContainer;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年2月6日 下午12:32:00
* 类说明
*/
@Service
public class ResourceServiceImpl implements ResourceService{
	@Autowired
	private TbRsRTPPMapper tbRsRTPPMapper;
	@Autowired
	private TbRsCityMapper tbRsCityMapper;
	@Autowired
	private TrafficNodeConsumMapper trafficNodeConsumMapper;
	@Autowired
	private TbRsRTPPNbMapper tbRsRTPPNbMapper;
	
	public PageContainer queryResourceFenYe(Map<String, Object> para, int page) {
		int total = tbRsRTPPMapper.queryResourceCount(para);//查询数据总数
		
		PageContainer pageContainer = new PageContainer();
		para = pageContainer.createParameter(para, page, total);
		List<Map<String,String>> result = tbRsRTPPMapper.queryResourceFenYe(para);
		pageContainer.setResultMap(result);
		return pageContainer;
	}

	@Override
	public int deleteByPrimaryKey(Integer rtppSid) {
		
		return tbRsRTPPMapper.deleteByPrimaryKey(rtppSid);
	}

	@Override
	public TbRsRTPP selectByPrimaryKey(Integer rtppSid) {
		
		return tbRsRTPPMapper.selectByPrimaryKey(rtppSid);
	}

	@Override
	public Map<String, String> queryResourceById(int rtppSid) {
		
		return tbRsRTPPMapper.queryResourceById(rtppSid);
	}

	@Override
	public int batchDelResource(String checkedId) {

		return tbRsRTPPMapper.batchDelResource(checkedId.split(","));
	}

	@Override
	public int updateByPrimaryKeySelective(TbRsRTPP record) {
		int continentId = tbRsCityMapper.queryContinentByCountry(record.getCountry());
		int areaId = tbRsCityMapper.queryAreaByCity(record.getZone());
		record.setContinent(continentId);
		record.setRegion(areaId);
		System.out.println(record.getContinent()+"  "+record.getCountry()+" "+record.getRegion()+"  "+record.getProvince()+"  "+record.getZone()+"  ");
		return tbRsRTPPMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateBlockPriceById(TbRsRTPP record) {
		
		return tbRsRTPPMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int insertSelective(TbRsRTPP record) {
		int continentId = tbRsCityMapper.queryContinentByCountry(record.getCountry());
		int areaId = tbRsCityMapper.queryAreaByCity(record.getZone());
		record.setContinent(continentId);
		record.setRegion(areaId);
		return tbRsRTPPMapper.insertSelective(record);
	}

	@Override
	public TbRsRTPP checkIp(String ip) {
		TbRsRTPP tbRsRTPP = null;
		List<TbRsRTPP> RTPPList = tbRsRTPPMapper.checkIp(ip);
		if(RTPPList != null && !RTPPList.isEmpty()){
			tbRsRTPP = RTPPList.get(0);
		}
		return tbRsRTPP;
	}
	
	@Override
	public List<TbRsRTPP> getRTPPListByIp(String ip) {
		List<TbRsRTPP> RTPPList = tbRsRTPPMapper.checkIp(ip);
		return RTPPList;
	}
	

	@Override
	public List<Map<String, String>> queryResourceRealStatus(String ip,String dateTime) {
		Map<String,String> para = new HashMap<String,String>();
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

	@Override
	public Map<String, Object> showNeighbor(String ip,String routeDomain) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ip", ip);
		map.put("area", routeDomain);
		List<String> nbList = tbRsRTPPNbMapper.getNbList(map);//查询已配置的邻居节点
		map.put("nbList", nbList);
		List<String> nnbList = tbRsRTPPNbMapper.getNnbList(map);//查询已配置的邻居节点
		map.put("nnbList", nnbList);
		Set<String> set = RedisService.getInstance().zrange(RouterConstant.PRE_ROUTE_KEY + routeDomain, 0, -1);
		List<String> otherIpList = new ArrayList<String>(set);
		otherIpList.removeAll(nbList);
		otherIpList.removeAll(nnbList);
		map.put("otherIpList", otherIpList);
		return map;
	}

	@Override
	public int updateNeighbor(String[] neighborArray, String[] notNeighborArray,String ip, String routeDomain){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("neighborArray", neighborArray);
		para.put("notNeighborArray", notNeighborArray);
		para.put("ip", ip);
		para.put("area", routeDomain);
		tbRsRTPPNbMapper.deleteNb(para);
		tbRsRTPPNbMapper.deleteNnb(para);
		if(neighborArray != null && neighborArray.length > 0){
			tbRsRTPPNbMapper.insertNb(para);
		}
		if(notNeighborArray != null && notNeighborArray.length > 0){
			tbRsRTPPNbMapper.insertNnb(para);
		}
		return 1;
	}

	@Override
	public List<TbRsRTPP> checkRTPP(TbRsRTPP tbRsRTPP) {
//		return tbRsRTPPMapper.checkRTPP(tbRsRTPP);
		return tbRsRTPPMapper.checkIp(tbRsRTPP.getIp());
	}
	
	
}
