package com.flypaas.service;

import java.util.List;
import java.util.Map;

import com.flypaas.model.TbRsOprateLog;
import com.flypaas.model.vo.LogUser;
import com.flypaas.util.PageContainer;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2016年12月29日 上午9:55:07
* 类说明
*/
public interface LogService {
	   int deleteByPrimaryKey(Long logId);

	   int insert(TbRsOprateLog record);

	   int insertSelective(TbRsOprateLog record);

	   TbRsOprateLog selectByPrimaryKey(Long logId);

	   int updateByPrimaryKeySelective(TbRsOprateLog record);

	   int updateByPrimaryKey(TbRsOprateLog record);
	    
	   public List<LogUser> queryLog(String netSid);
	   
	   public int queryCount(String netSid);

	   PageContainer queryLog(Map<String, Object> para, int page, String netSid);
}
