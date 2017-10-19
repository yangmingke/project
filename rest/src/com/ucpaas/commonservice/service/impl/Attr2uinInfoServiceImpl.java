package com.ucpaas.commonservice.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ucpaas.commonservice.constant.Constants;
import com.ucpaas.commonservice.dao.Attr2uinInfoDao;
import com.ucpaas.commonservice.model.Attr2uinInfo;
import com.ucpaas.commonservice.service.Attr2uinInfoService;
import com.ucpaas.commonservice.service.base.RedisBaseService;
import com.ucpaas.commonservice.util.db.DBShardingUtil;

@Service("attr2uinInfoService")
public class Attr2uinInfoServiceImpl extends RedisBaseService<String, Attr2uinInfo> implements Attr2uinInfoService {
	private static final Logger log = LoggerFactory.getLogger(Attr2uinInfoServiceImpl.class);

	@Resource(name = "attr2uinInfoDao")
	private Attr2uinInfoDao attr2uinInfoDao;

	@Override
	public Attr2uinInfo getByAttr(String attr, int type) throws Exception {
		String[] dbNodeArr = DBShardingUtil.getDBNodeByKey(attr);
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("uin_mod", dbNodeArr[0]);
		parameterMap.put("attr", attr);
		parameterMap.put("type", type);
		log.debug("attr={},type={},parameterMap={}", attr, type, parameterMap);

		return this.attr2uinInfoDao.selectByMap(parameterMap, dbNodeArr[1]);

	}

	@Override
	public Attr2uinInfo getByAttrCache(String attr, int type) throws Exception {
		Attr2uinInfo attr2uinInfo = null;
		// 1.从缓存拿数据
		attr2uinInfo = this.getFromCache(Constants.REDIS_KEY_ATTR2UIN + type +":" + attr);
		if (attr2uinInfo == null) {
			// 2.如果缓存中没有数据，从数据库拿数据
			attr2uinInfo = this.getByAttr(attr, type);
			if (attr2uinInfo != null) {
				// 3.将数据放入缓存中,有效期n天
				this.setToCacheDays(Constants.REDIS_KEY_ATTR2UIN + type +":" + attr, attr2uinInfo, RandomUtils.nextInt(Constants.REDIS_DAY_MIN, Constants.REDIS_DAY_MAX));
			}
		}
		log.debug("attr={},type={},attr2uinInfo={}", attr, type, attr2uinInfo);
		return attr2uinInfo;
	}
	

	public Integer getUinCache(String attr, int type) throws Exception{
		Attr2uinInfo attr2uinInfo = this.getByAttrCache(attr, type);
		if(attr2uinInfo != null && attr2uinInfo.getUin() != null){
			return attr2uinInfo.getUin();
		}else{
			return null;
		}
	}

	@Override
	public int insert(Attr2uinInfo attr2uinInfo) throws Exception {
		Map<String, String> dbMap = DBShardingUtil.getMapDBNodeByKey(attr2uinInfo.getAttr());
		log.debug("【插入反向表信息】,attr2uinInfo={},dbMap={}",attr2uinInfo,dbMap);
		attr2uinInfo.setUin_mod(dbMap.get("uin_mod"));
		return this.attr2uinInfoDao.insert(attr2uinInfo, dbMap.get("db_node"));
	}
	
	public int insertCache(Attr2uinInfo attr2uinInfo) throws Exception {
		//1.插入数据库
		int rows = this.insert(attr2uinInfo);
		//2.将数据放入缓存中,有效期n天
		this.setToCacheDays(Constants.REDIS_KEY_ATTR2UIN + attr2uinInfo.getType() +":" + attr2uinInfo.getAttr(), attr2uinInfo, RandomUtils.nextInt(Constants.REDIS_DAY_MIN, Constants.REDIS_DAY_MAX));
		return rows;
	}

	@Override
	public int deleteByAttrType(Attr2uinInfo attr2uinInfo) throws Exception {
		Map<String, String> dbMap = DBShardingUtil.getMapDBNodeByKey(attr2uinInfo.getAttr());
		log.debug("【删除反向表信息】,attr2uinInfo={},dbMap={}",attr2uinInfo,dbMap);
		attr2uinInfo.setUin_mod(dbMap.get("uin_mod"));
		return this.attr2uinInfoDao.deleteByAttrType(attr2uinInfo, dbMap.get("db_node"));
	}
	
	
	
	
	public int deleteByAttrTypeCache(Attr2uinInfo attr2uinInfo) throws Exception {
		//1.删除数据库中数据
		int rows = this.deleteByAttrType(attr2uinInfo);
		//2.删除缓存
		this.deleteFromCache(Constants.REDIS_KEY_ATTR2UIN + attr2uinInfo.getType() +":" + attr2uinInfo.getAttr());
		return rows;
	}

	@Override
	public int deleteByUinType(Attr2uinInfo attr2uinInfo) throws Exception {
		Map<String, String> dbMap = DBShardingUtil.getMapDBNodeByKey(attr2uinInfo.getAttr());
		log.debug("【删除反向表信息】,attr2uinInfo={},dbMap={}",attr2uinInfo,dbMap);
		attr2uinInfo.setUin_mod(dbMap.get("uin_mod"));
		return this.attr2uinInfoDao.deleteByUinType(attr2uinInfo, dbMap.get("db_node"));
	}

	@Override
	public int deleteByUinTypeCache(Attr2uinInfo attr2uinInfo) throws Exception {
		//删除缓存
		this.deleteFromCache(Constants.REDIS_KEY_ATTR2UIN + attr2uinInfo.getType() +":" + attr2uinInfo.getAttr());
		return this.deleteByUinType(attr2uinInfo);
	}

}
