package com.flypaas.rest;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.flypaas.constant.RouterConstant;
import com.flypaas.model.vo.Rtpps;

//获取响应实体信息
public class JsonReqClient{
	public static Logger logger = Logger.getLogger(JsonReqClient.class);
	public void  createClient() throws Exception{
		Rtpps rtpps = new Rtpps();
		List list =new ArrayList();
		list.add("192.168.0.0.14");
		rtpps.setAction(RouterConstant.add);
		
		rtpps.setRtpps(list);
		HttpRequest req = new HttpRequest();
		HttpResponse response = req.post("application/json",RouterConstant.url,rtpps);
		//获取响应实体信息
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			String result = EntityUtils.toString(entity, "UTF-8");
			logger.info("---------------------------返回实体信息-------------------------");
			logger.info(result);
			// 确保HTTP响应内容全部被读出或者内容流被关闭
			EntityUtils.consume(entity);
		}
	}
	
}			