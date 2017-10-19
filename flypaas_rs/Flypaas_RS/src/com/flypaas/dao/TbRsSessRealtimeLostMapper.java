package com.flypaas.dao;

import com.flypaas.model.TbRsSessRealtimeLost;

public interface TbRsSessRealtimeLostMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbRsSessRealtimeLost record);

    int insertSelective(TbRsSessRealtimeLost record);

    TbRsSessRealtimeLost selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbRsSessRealtimeLost record);

    int updateByPrimaryKey(TbRsSessRealtimeLost record);
}