package com.ucpaas.commonservice.quartz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ucpaas.commonservice.config.Properties;
import com.ucpaas.commonservice.dao.IpAuthenticationDao;
import com.ucpaas.commonservice.util.JsonObjectUtil;
import com.ucpaas.commonservice.util.RedisBaseUtil;

/**
 * 
 * @author xiangrui
 *
 *         2016年12月9日下午5:11:55 TODO 定时同步ip
 */
public class SynchronizeIpJob {
	private static Logger logger = LoggerFactory
			.getLogger(SynchronizeIpJob.class);
	@Autowired
	private IpAuthenticationDao ipAuthenticationDao;
	@Resource(name = "properties")
	private Properties properties;
	@Autowired
	private RedisBaseUtil<String, String> redisBaseUtil;

	public void execute() {
		String name=properties.getIp_redis_key();
		String keys=properties.getIp_redis_key()+"_"+"kyes";
		// 5秒扫描一次数据库 将ip插入redis
		// 获取数据库中所有ip
		List<Map<String, Object>> list = ipAuthenticationDao.selectList("ipAuthentication.listIps");
		// 获取所有key
		String keysJson=redisBaseUtil.getFromCache(keys);
		List<Map<String, Object>> listKyes = new ArrayList<Map<String,Object>>();
		if(keysJson!=null){
			listKyes=JsonObjectUtil.json2object(keysJson, List.class);			
		}
		
		// 遍历数据库ip
		for (Map<String, Object> m : list) {
			boolean flag = false;
			for (Map<String, Object> mapKey : listKyes) {
				// 如果redis中存在数据库中数据 则不处理
				if ( m.get("ip").equals(mapKey.get("ip"))) {
					flag = true;
					continue;
				}
			}
			// 如果redis中不存在 则插入到redis中
			if (flag == false) {
				logger.info("【向redis中插入ubs鉴权数据】,key={}",name + "_" + m.get("ip"));
				redisBaseUtil.setToCache(name + "_" + m.get("ip"),
						(String) m.get("ip"));
			}
		}
		
		//遍历redis  (如果redis中存在，数据库中不存在，则删除redis中数据)
		if(listKyes!=null){
			for (Map<String, Object> mapKey : listKyes) {
				boolean flag=false;
				for (Map<String, Object> m : list) {
					if(m.get("ip").equals(mapKey.get("ip"))){
						flag=true;
						continue;
					}
				}
				//如果redis中存在  数据库中不存在  删除redis中数据
				if(flag==false){
					logger.info("【删除掉redis中ubs鉴权的key】,key={}",mapKey.get("ip"));
					redisBaseUtil.deleteFromCache(name + "_"+(String)mapKey.get("ip"));
				}
			}
		}
		//将key更新到redis中
				redisBaseUtil.setToCache(keys,JsonObjectUtil.object2json(list));
	}

}
