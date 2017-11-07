package com.flypaas.service.operation.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.cdr.dao.LostRateMapper;
import com.flypaas.cdr.dao.NodeConcurrenceMapper;
import com.flypaas.dao.TbRsRTPPMapper;
import com.flypaas.model.TbRsNodeConcurrence;
import com.flypaas.model.TbRsSessRealtimeLost;
import com.flypaas.service.operation.OperationService;
import com.flypaas.util.JsonUtil;
import com.flypaas.util.StrUtil;

/**
* @author 作者 yangmingke:
* @version 创建时间：2017年2月6日 下午12:32:00
* 类说明
*/
@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class OperationServiceImpl implements OperationService{

	@Autowired
	LostRateMapper lostRateMapper;
	@Autowired
	TbRsRTPPMapper tbRsRTPPMapper;
	@Autowired
	NodeConcurrenceMapper nodeConcurrenceMapper;
	
	@Override
	public List<String> queryAllRTPP(){
		LinkedList<String> ipAddressList = new LinkedList<String>();
		List<String> tempList = tbRsRTPPMapper.queryAllRTPP();
		for(String ip : tempList){//升序去重
			int i = 0;
			for(String ipAddress : ipAddressList){
				if(StrUtil.firstIpAddressIsLess(ip, ipAddress)){
					ipAddressList.add(i,ip);
					break;
				}
				i++;
			}
			if(ipAddressList.size() == i){
				ipAddressList.addLast(ip);
			}
		}
		return ipAddressList;
	}
	
	@Override
	public Map queryPacketLoss(String ip, String date) {
		date = date.replace("-", "");
		Map<String, String> para = new HashMap<String, String> ();
		para.put("ip", ip);
		para.put("date", date);
		List<TbRsSessRealtimeLost> paketLossList = lostRateMapper.queryPaketLoss(para);
		float[][] rateList = new float[2][240];
		for(TbRsSessRealtimeLost tbRsSessRealtimeLost : paketLossList){//计算丢包率
			int hours = tbRsSessRealtimeLost.getTime().getHours();
			int minutes = tbRsSessRealtimeLost.getTime().getMinutes();
			float rate = tbRsSessRealtimeLost.getLostrate() / 100;
			rateList[0][hours*10+minutes/6] += rate;//丢包率
			rateList[1][hours*10+minutes/6] ++;//丢包次数
		}
		String[] timeList = new String[240];
		for(int i= 0; i < timeList.length; i++){
			timeList[i] = i/10+"时" + i%10*6+"分" ;
		}

		float rateMax = 0;
		float countMax = 0; 
		for(int i=0; i < timeList.length; i++){
			if(rateList[0][i] > 0){
				float rate = (float)(Math.round(rateList[0][i]/rateList[1][i]*100))/100;
				rateList[0][i] = rate;
				if(rate > rateMax){
					rateMax = rate;
				}
				if(rateList[1][i] > countMax){
					countMax =  rateList[1][i];
				}
			}
		}
		Map result = new HashMap();
		result.put("rate", rateList);
		result.put("time", timeList);
		result.put("rateMax", rateMax);
		result.put("countMax", countMax);
		result.putAll(para);
		
		return result;
	}

	@Override
	public Map queryPacketLossResource(String destIp, String date) {
		date = date.replace("-", "");
		Map para = new HashMap();
		para.put("destIp", destIp);
		para.put("date", date);
		int count = lostRateMapper.queryPaketLossSourceCount(para);
		List resultList = lostRateMapper.queryPaketLossSource(para);
		
		Map result = new HashMap();
		result.put("resultList", resultList);
		result.put("count", count);
		return result;
	}

	@Override
	public Map<String, Object> querySessionPacketLoss(String dateTime, String userSid, String appSid, String sessionID) {
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("dateTime", dateTime.replaceAll("-", ""));
		model.put("userSid", StringUtils.isEmpty(userSid) ?  null : userSid);
		model.put("appSid", StringUtils.isEmpty(appSid) ?  null : appSid);
		model.put("cookie", StringUtils.isEmpty(sessionID) ?  null : sessionID);
		List<Map<String,Object>> analysisResultList = lostRateMapper.querySessionPaketLoss(model);
		LinkedList<String> packetLossTime = new LinkedList<String>(Arrays.asList("00:00:00", "01:00:00", "02:00:00", "03:00:00", "04:00:00",
				"05:00:00", "06:00:00", "07:00:00", "08:00:00", "09:00:00", "10:00:00", "11:00:00",
				"12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00",
				"19:00:00", "20:00:00", "21:00:00", "22:00:00", "23:00:00", "24:00:00"));
		for (Map<String, Object> analysisResult : analysisResultList) {
			String snapshoot = analysisResult.get("snapshoot").toString();
			String packetLossString = snapshoot.replace("[", "").replace("]", "").trim();
			String[] packetLossArray = packetLossString.split("  ");
			for (int i = 0; StringUtils.isNotEmpty(packetLossString) && i < packetLossArray.length; i++) {
				for (int j = 0; j < packetLossTime.size(); j++) {
					String time = packetLossArray[i].split(" ")[0];
					if (time.compareTo(packetLossTime.get(j)) <= 0) {
						if (!time.equals(packetLossTime.get(j))) {
							packetLossTime.add(j, time);
						}
						break;
					}
				}
			}
		}
		model.put("packetLossTime", JsonUtil.toJsonStr(packetLossTime));//时间轴
		model.put("resultList", analysisResultList);//用于列表显示
		model.put("resultJson", JsonUtil.toJsonStr(analysisResultList));//用于画曲线图
		return model;
	}

	@Override
	public Map<String, Object> queryNodeConcurrent(String date) {
		date = date.replaceAll("-", "");
		Map<String, String> para = new HashMap<String, String> ();
		para.put("date", date);
		para.put("month", date.substring(0, 6));
		List<TbRsNodeConcurrence> nodeConcurrenceList = nodeConcurrenceMapper.queryNodeConcurrent(para);
		Map<String,List> nodesData = new HashMap<String,List>();
		List<String> timeList = new ArrayList<String>();
		for(TbRsNodeConcurrence tbRsNodeConcurrence : nodeConcurrenceList){
			String time = tbRsNodeConcurrence.getDatetimeStr();
			if(!timeList.contains(time)){
				timeList.add(time);
			}
			String ip = tbRsNodeConcurrence.getIp();
			if(nodesData.containsKey(ip)){
				nodesData.get(ip).add(tbRsNodeConcurrence);
			}else{
				List NodeConcurrence = new ArrayList();
				NodeConcurrence.add(tbRsNodeConcurrence);
				nodesData.put(ip, NodeConcurrence);
			}
		}
		Map<String, Object> result = new HashMap();
		result.put("nodeData", nodesData);
		result.put("time", timeList);
		return result;
	}
	
	
}
