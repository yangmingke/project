package com.flypaas.service.impl.finance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.cdr.dao.TrafficNodeConsumMapper;
import com.flypaas.model.TbRsBillAcctRTPP;
import com.flypaas.service.finance.ResourceAcctService;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年1月11日 上午11:24:28
* 类说明
*/
@Service("/resourceAcctServiceImpl")
public class ResourceAcctServiceImpl implements ResourceAcctService {
	@Autowired TrafficNodeConsumMapper trafficNodeConsumMapper;
	
	@Override
	public List<TbRsBillAcctRTPP> queryResourceTDC(String netSid, String date) {
		String month = date.replace("-", "").substring(0, 6);
		Map<String,String> para =  new HashMap<String,String>();
		para.put("dateTime", date);
		para.put("netSid", netSid);
		para.put("month",month);
		return trafficNodeConsumMapper.queryResourceFlowDay(para);
	}
	
	@Override
	public TbRsBillAcctRTPP queryResourceTDCTotal(String netSid, String date) {
		String month = date.replace("-", "").substring(0, 6);
		Map<String,String> para =  new HashMap<String,String>();
		para.put("dateTime", date);
		para.put("netSid", netSid);
		para.put("month",month);
		List<TbRsBillAcctRTPP> list = trafficNodeConsumMapper.queryResourceSideFlowDay(para);
		if(list == null || list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<TbRsBillAcctRTPP> queryResourceTMC(String netSid, String month) {
		month = month.replace("-", "");
		Map<String,String> para =  new HashMap<String,String>();
		para.put("netSid", netSid);
		para.put("month",month);
		return trafficNodeConsumMapper.queryResourceFlowMonth(para);
	}
	
	@Override
	public TbRsBillAcctRTPP queryResourceTMCTotal(String netSid, String month) {
		month = month.replace("-", "");
		Map<String,String> para =  new HashMap<String,String>();
		para.put("netSid", netSid);
		para.put("month",month);
		List<TbRsBillAcctRTPP> list = trafficNodeConsumMapper.queryResourceSideFlowMonth(para);
		if(list == null || list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

}
