package com.flypaas.service.scheduled.impl;

import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.flypaas.cdr.dao.NodeConcurrenceMapper;
import com.flypaas.dao.TbRsConfigMapper;
import com.flypaas.model.TbRsNodeConcurrence;
import com.flypaas.rest.HttpRequest;
import com.flypaas.service.scheduled.ScheduledService;
import com.flypaas.util.DateUtil;
import com.flypaas.util.SysConstant;

@Service
public class ScheduledServiceImpl implements ScheduledService{
	@Autowired
	NodeConcurrenceMapper nodeConcurrenceMapper;
	@Autowired
	TbRsConfigMapper tbRsConfigMapper;

	// 每60秒执行一次
	@Scheduled(cron = "*/60 * * * * ?")
    public void concurrenceMonitoring() {
        try{
        	String urlsStr = tbRsConfigMapper.getConfigByKey(SysConstant.RTPP_RTPC_IP);
        	String[] urls = urlsStr.split("/");
        	for(String url : urls){
				HttpRequest req = new HttpRequest();
				HttpResponse response;
				response = req.get("http://" + url + ":" + SysConstant.RTPP_RTPC_PORT);
				//获取响应实体信息
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String result = EntityUtils.toString(entity, "UTF-8");
					TbRsNodeConcurrence tbRsNodeConcurrence = new TbRsNodeConcurrence();
					tbRsNodeConcurrence.setConcurrence(Integer.parseInt(result.replace("\r\n", "")));
					tbRsNodeConcurrence.setIp(url.split(":")[0]);
					tbRsNodeConcurrence.setDatetime(new Date());
					tbRsNodeConcurrence.setMonth(DateUtil.dateToStr(new Date(),DateUtil.YM_NO_SLASH));
					insert(tbRsNodeConcurrence);
					// 确保HTTP响应内容全部被读出或者内容流被关闭
					EntityUtils.consume(entity);
				}
        	}
		}catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	private void insert(TbRsNodeConcurrence tbRsNodeConcurrence){
		try {
			nodeConcurrenceMapper.insert(tbRsNodeConcurrence);
		} catch (BadSqlGrammarException e) {//如果表不存在则建立表，重新插入数据
			nodeConcurrenceMapper.createTable();
			nodeConcurrenceMapper.insert(tbRsNodeConcurrence);
		}
	}
}
