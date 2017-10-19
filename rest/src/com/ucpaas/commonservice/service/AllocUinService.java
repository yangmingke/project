package com.ucpaas.commonservice.service;

/**
 * uin分配器service
 *
 */
public interface AllocUinService {
	
	/**
	 * 根据分片id调存储过程，获取分配的uin
	 * 2016-03-21
	 * @param sectionId
	 * @return
	 * @throws Exception
	 */
	Integer selectUinBySectionId(int sectionId) throws Exception;

}
