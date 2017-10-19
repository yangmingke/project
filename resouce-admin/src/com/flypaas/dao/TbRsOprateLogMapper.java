package com.flypaas.dao;

import java.util.List;
import java.util.Map;

import com.flypaas.model.TbRsOprateLog;
import com.flypaas.model.vo.LogUser;

public interface TbRsOprateLogMapper {
    int deleteByPrimaryKey(Long logId);

    int insert(TbRsOprateLog record);

    int insertSelective(TbRsOprateLog record);

    TbRsOprateLog selectByPrimaryKey(Long logId);

    int updateByPrimaryKeySelective(TbRsOprateLog record);

    int updateByPrimaryKey(TbRsOprateLog record);
    
    public List<LogUser> queryLog(String netSid);
    
    public int queryCount(String netSid);
    
    public List<Object> queryLogPage(Map para);

	int queryCount(Map<String, Object> para);
}