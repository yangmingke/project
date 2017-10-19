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
     * @param netSid
     * @return
     */
    public List<TbRsBillFlow> queryAllBillFlowBynetSid(String netSid);
    
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

    /**
     * 提现记录总数
     * @param para
     * @return
     */
	int queryCount(Map<String, Object> para);

	/**
	 * 查询所有提现记录
	 * @param para
	 * @return
	 */
	List<Map<String, String>> queryAllBillFlow(Map<String, Object> para);
    
	/**
	 * 根据id查询提现记录
	 * @param netSid
	 * @return
	 */
	Map<String, String> queryBillFlow(String id);
}