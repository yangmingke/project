package com.flypaas.dao;

import com.flypaas.model.TbRsBillAcctRTPPBlock;

public interface TbRsBillAcctRTPPBlockMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbRsBillAcctRTPPBlock record);

    int insertSelective(TbRsBillAcctRTPPBlock record);

    TbRsBillAcctRTPPBlock selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbRsBillAcctRTPPBlock record);

    int updateByPrimaryKey(TbRsBillAcctRTPPBlock record);
}