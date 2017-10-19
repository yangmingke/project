package com.flypaas.service.finance;

import com.flypaas.model.TbRsAccountBalance;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2016年12月27日 下午7:22:38
* 类说明
*/
public interface AccountBalanceService {
	/**
     * 注册成功后添加资源方信息
     * @param record
     * @return
     */
    int insertSelective(TbRsAccountBalance record);
    
	
	/**
	 * 按netSid查询余额信息
	 * @param netSid
	 * @return
	 */
	public TbRsAccountBalance queryAccountBalanceBynetSid(String netSid);
	
	/**
     * 修改余额信息
     * @param tbRsAccountBalance
     * @return
     */
    public int updateByPrimaryKeySelective(TbRsAccountBalance tbRsAccountBalance);


    public TbRsAccountBalance queryAccountBalance(String netSid);
    
    

}
