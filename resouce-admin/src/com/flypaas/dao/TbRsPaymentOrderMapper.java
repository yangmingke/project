package com.flypaas.dao;

import com.flypaas.model.TbRsPaymentOrder;

public interface TbRsPaymentOrderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(TbRsPaymentOrder record);

    int insertSelective(TbRsPaymentOrder record);

    TbRsPaymentOrder selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(TbRsPaymentOrder record);

    int updateByPrimaryKey(TbRsPaymentOrder record);
}