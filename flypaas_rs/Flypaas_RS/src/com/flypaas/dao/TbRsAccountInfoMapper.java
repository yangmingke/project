package com.flypaas.dao;

import com.flypaas.model.TbRsAccountInfo;

public interface TbRsAccountInfoMapper {
    int deleteByPrimaryKey(String netSid);

    int insert(TbRsAccountInfo record);

    int insertSelective(TbRsAccountInfo record);

    TbRsAccountInfo selectByPrimaryKey(String netSid);

    int updateByPrimaryKeySelective(TbRsAccountInfo record);

    int updateByPrimaryKey(TbRsAccountInfo record);
}