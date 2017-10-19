package com.flypaas.service.finance;

import java.util.List;

import com.flypaas.model.TbRsAccountBalance;
import com.flypaas.model.TbRsAccountInfo;
import com.flypaas.model.TbRsBillFlow;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年1月11日 下午7:08:33
* 类说明
*/
public interface BillFlowService {
	/**
     * 根据资源方netSid查询该资源方所有的提款记录
     * @param netSid
	 * @param dateMax 
	 * @param dateMin 
     * @return
     */
    public List<TbRsBillFlow> queryAllBillFlowBynetSid(String netSid, String dateMin, String dateMax);
    
    /**
     * 生成账户记录表
     * @param tbRsBillFlow
     * @param money 
     * @param balance 
     * @param tbRsUser 
     * @return
     */
    public int insertSelective(TbRsBillFlow tbRsBillFlow,TbRsAccountInfo tbRsUser, double balance, long money);
    
    /**
     * 通过Id查询 账单
     * @param id
     * @return
     */
    public TbRsBillFlow selectByPrimaryKey(Long id);
    
    /**
     * 修改订单记录
     * @param tbRsBillFlow
     * @return
     */
    public int updateByPrimaryKeySelective(TbRsBillFlow tbRsBillFlow,TbRsAccountBalance tbRsAccountBalance);
}
