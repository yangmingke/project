package com.flypaas.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.flypaas.model.TbRsAccountInfo;

public interface TbRsAccountInfoMapper {
    int deleteByPrimaryKey(String netSid);

    int insert(TbRsAccountInfo record);

    int insertSelective(TbRsAccountInfo record);

    TbRsAccountInfo selectByPrimaryKey(String netSid);
    
    public List<Map<String, String>> findRsAccountBySids(List netSids);

    int updateByPrimaryKeySelective(TbRsAccountInfo record);

    int updateByPrimaryKey(TbRsAccountInfo record);
    
    public List<Object> queryAccount(Map para);

    public int queryCount(Map para);
    
    public List<TbRsAccountInfo> queryAllAccountName();
    
    public List<Map<String, String>> queryAccountByName(@Param(value="username")String username);

	public List<TbRsAccountInfo> queryAllAccountNameByName(@Param(value="username")String username);
}