package com.flypaas.dao;

import com.flypaas.model.TbRsPic;

public interface TbRsPicMapper {
    int insert(TbRsPic record);

    int insertSelective(TbRsPic record);
}