package com.flypaas.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.dao.TbRsCityMapper;
import com.flypaas.dao.TbRsOprateLogMapper;
import com.flypaas.service.DemoService;
import com.flypaas.util.PageContainer;

@Service("/demoServiceImpl")
public class DemoServiceImpl implements DemoService{
	@Autowired
	private TbRsOprateLogMapper tbRsOprateLogMapper;
	@Autowired
	TbRsCityMapper tbRsCityMapper;

	@Override
	public PageContainer queryDemo(String netId,int page) {
		/*int total = tbRsOprateLogMapper.queryCount(netId);
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("netSid", netId);
		PageContainer pageContainer = new PageContainer();
		para = pageContainer.createParameter(para, page, total);
		List<Object> result = tbRsOprateLogMapper.queryLogPage(para);
		pageContainer.setResult(result);
		return pageContainer;*/
		return null;
	}
	

}
