package com.flypaas.dao;

import java.util.List;
import java.util.Map;

import com.flypaas.model.TbRsBillFlow;
public interface TbRsBillFlowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbRsBillFlow record);
    /**
     * 生成账户记录表
     * @param tbRsBillFlow
     * @return
     */
    int insertSelective(TbRsBillFlow record);

    /**
     * 通过Id查询 账单
     * @param id
     * @return
     */
    public TbRsBillFlow selectByPrimaryKey(Long id);
    
    /**
     * 根据资源方netSid查询该资源方所有的提款记录
     * @param para
     * @return
     */
    public List<TbRsBillFlow> queryAllBillFlowBynetSid(Map<String, String> para);
    
    /**
    * 生成订单记录
    * @param tbRsBillFlow
    * @return
    */
    public int addBillFlow(TbRsBillFlow tbRsBillFlow);
    
    /**
     * 修改订单记录
     * @param tbRsBillFlow
     * @return
     */
    public int updateByPrimaryKeySelective(TbRsBillFlow tbRsBillFlow);
    
}