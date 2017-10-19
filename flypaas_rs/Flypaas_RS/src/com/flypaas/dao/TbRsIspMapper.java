package com.flypaas.dao;

import java.util.List;

import com.flypaas.model.TbRsIsp;

public interface TbRsIspMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbRsIsp record);

    int insertSelective(TbRsIsp record);

    TbRsIsp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbRsIsp record);

    int updateByPrimaryKey(TbRsIsp record);
    
    /**
     * 查询所有的Isp动态显示在页面
     * @return
     */
    public List<TbRsIsp> queryAllIsp();
    /**
     * 根据operatorId查询运营商名称
     * @return
     */
    public String queryById(String operator);
}