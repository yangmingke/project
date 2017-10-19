package com.flypaas.dao;

import java.util.List;

import com.flypaas.model.TbRsArea;

public interface TbRsAreaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbRsArea record);

    int insertSelective(TbRsArea record);

    TbRsArea selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbRsArea record);

    int updateByPrimaryKey(TbRsArea record);
    /**
	 * 查询所有的片区
	 * @return
	 */
	public List<TbRsArea> queryAllArea();
	
	
}