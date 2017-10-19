package com.flypaas.dao;


import com.flypaas.model.TbRsAccountBalance;
public interface TbRsAccountBalanceMapper {
    int insert(TbRsAccountBalance record);
    /**
     * 注册成功后添加资源方信息
     * @param record
     * @return
     */
    int insertSelective(TbRsAccountBalance record);
    
    /**
     * 按资源方netSid查询余额信息
     * @param netSid
     * @return
     */
    public TbRsAccountBalance queryAccountBalanceBynetSid(String netSid);
    /**
     * 按资源方netSid查询余额信息(悲观锁)
     * @param netSid
     * @return
     */
    public TbRsAccountBalance queryAccountBalance(String netSid);
    /**
     * 修改余额信息
     * @param tbRsAccountBalance
     * @return
     */
    public int updateByPrimaryKeySelective(TbRsAccountBalance tbRsAccountBalance);
}