package com.flypaas.util;

import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.flypaas.rest.HttpRequest;

@SuppressWarnings("rawtypes")
public class BaiduMapUtil {
	public static Map getrtpplist(String ip) throws Exception{//未完成TODO
        String baiduMapAk = StrUtil.getConfigVal("baidu_map_ak");
		HttpRequest req = new HttpRequest();
		HttpResponse response;
		Map resultMap = null;
		//获取源IP信息
		response = req.get("http://api.map.baidu.com/location/ip?coor=bd09ll&ak=" + baiduMapAk + "&ip=" + ip);
		//获取响应实体信息
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			String result = EntityUtils.toString(entity, "UTF-8");
			System.out.println("获取结果:"+result);
			Map map = JSON.parseObject(result, Map.class);
			resultMap = (Map) map.get("content");
			// 确保HTTP响应内容全部被读出或者内容流被关闭
			EntityUtils.consume(entity);
			System.out.println("获取IP"+ip+"定位信息成功");
		}
		return resultMap;
	}
  
}  
