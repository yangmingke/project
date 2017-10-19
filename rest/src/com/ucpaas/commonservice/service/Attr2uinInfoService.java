package com.ucpaas.commonservice.service;

import com.ucpaas.commonservice.model.Attr2uinInfo;

/**
 * 反向表service
 * @author luke
 *
 */
public interface Attr2uinInfoService {
	
	/**
	 * 查询反向表信息
	 * @param attr
	 * @param type 反向表类型;	type=101：attr={userId}_{appId};type=102：attr={mobile}_{appId}
	 * @return
	 * @throws Exception
	 */
	Attr2uinInfo getByAttr(String attr, int type) throws Exception;
	
	/**
	 * 从缓存查询反向表信息
	 * @param attr
	 * @param type 反向表类型;	type=101：attr={userId}_{appId};type=102：attr={mobile}_{appId}
	 * @return
	 * @throws Exception
	 */
	Attr2uinInfo getByAttrCache(String attr, int type) throws Exception;
	
	/**
	 * 获取反向表uin
	 * @param attr
	 * @param type
	 * @return
	 * @throws Exception
	 */
	Integer getUinCache(String attr, int type) throws Exception;
	
	/**
	 * 插入反向表信息
	 * @param attr2uinInfo
	 * @return
	 * @throws Exception
	 */
	int insert(Attr2uinInfo attr2uinInfo) throws Exception;
	
	
	/**
	 * 插入数据到反向表
	 * 同时将反向表记录写入缓存
	 * @param attr2uinInfo
	 * @return
	 * @throws Exception
	 */
	int insertCache(Attr2uinInfo attr2uinInfo) throws Exception;
	
	/**
	 * 根据attr和type删除反向表信息
	 * type=101：attr={userId}_{appId};type=102：attr={mobile}_{appId}
	 * @param attr2uinInfo
	 * @return
	 * @throws Exception
	 */
	int deleteByAttrType(Attr2uinInfo attr2uinInfo) throws Exception;
	
	
	
	/**
	 * 删除反向表记录
	 * 同时删除反向表缓存
	 * @param attr2uinInfo
	 * @return
	 * @throws Exception
	 */
	int deleteByAttrTypeCache(Attr2uinInfo attr2uinInfo) throws Exception;
	
	/**
	 * 根据uin，type删除反向表记录
	 * 2016-03-21新增
	 * @param attr2uinInfo
	 * @return
	 * @throws Exception
	 */
	int deleteByUinType(Attr2uinInfo attr2uinInfo) throws Exception;
	
	/**
	 * 删除反向表记录
	 * 同时删除反向表缓存
	 * 2016-03-21
	 * @param attr2uinInfo
	 * @return
	 * @throws Exception
	 */
	int deleteByUinTypeCache(Attr2uinInfo attr2uinInfo) throws Exception;
	
	
	
	
	
	
	
	
	
	
	
	

}
