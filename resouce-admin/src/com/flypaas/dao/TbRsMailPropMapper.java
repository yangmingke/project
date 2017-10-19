package com.flypaas.dao;

import com.flypaas.model.TbRsMailProp;

public interface TbRsMailPropMapper {
    int insert(TbRsMailProp record);

    int insertSelective(TbRsMailProp record);
    

	/**
	 * TODO 根据模板类型获取邮件模板
	 * @author 29p9g02
	 * 2014-4-8
	 */
	public TbRsMailProp getMailByType(String type);
}