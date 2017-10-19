package com.flypaas.service.user;

import java.util.List;
import java.util.Map;

import com.flypaas.model.TbRsUserMsg;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2016年12月22日 下午7:08:43
* 类说明
*/
public interface MessageService {
	int deleteByPrimaryKey(Long msgId);
	   
    int insert(TbRsUserMsg record);

    int insertSelective(TbRsUserMsg record);

    TbRsUserMsg selectByPrimaryKey(Long msgId);

    int updateByPrimaryKeySelective(TbRsUserMsg record);

    int updateByPrimaryKey(TbRsUserMsg record);
    
    public List<TbRsUserMsg> queryAllMsg(String sid);

    public int updateMsgStatus(int msgId);

	public int delMsg(Map para);
}
