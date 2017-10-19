package com.flypaas.dao;

import java.util.List;
import java.util.Map;

import com.flypaas.model.TbRsAccountBalance;

public interface TbRsAccountBalanceMapper {
    int insert(TbRsAccountBalance record);

    int insertSelective(TbRsAccountBalance record);
    
    public TbRsAccountBalance queryAccountBalanceBynetSid(String netSid);
    
    public TbRsAccountBalance queryAccountBalance(String netSid);
    
    /**
     * 对资源方余额账户进行操作    冻结/解冻
     * @param para
     * @return
     */
    public int editAccountStatus(TbRsAccountBalance balance);
    /**
     * 账号管理数量
     * @param para
     * @return
     */
	int queryCount(Map<String, Object> para);
	/**
     * 账号管理数据
     * @param para
     * @return
     */
	List<Map<String, String>> queryAccount(Map<String, Object> para);
	/**
	 * 提款人资料
	 * @param netSid
	 * @return
	 */
	Map getEnchashment(String netSid);

	int updateByPrimaryKeySelective(TbRsAccountBalance tbRsAccountBalance);

	int updateBalanceAdd(TbRsAccountBalance tbRsAccountBalance);

	int updateBalanceSub(TbRsAccountBalance tbRsAccountBalance);
    
}