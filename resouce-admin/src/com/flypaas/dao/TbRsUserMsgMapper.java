package com.flypaas.dao;

import java.util.List;

import com.flypaas.model.TbRsUserMsg;

public interface TbRsUserMsgMapper {
    int deleteByPrimaryKey(Long msgId);
   
    int insert(TbRsUserMsg record);

    int insertSelective(TbRsUserMsg record);

    TbRsUserMsg selectByPrimaryKey(Long msgId);

    int updateByPrimaryKeySelective(TbRsUserMsg record);

    int updateByPrimaryKey(TbRsUserMsg record);
    
    public List<TbRsUserMsg> queryAllMsg(String sid);
    
    public int updateMsgStatus(int msgId);
}