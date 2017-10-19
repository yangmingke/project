package com.flypaas.service.finance;

import java.util.List;

import com.flypaas.model.TbRsBillAcct;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年1月11日 上午11:23:04
* 类说明
*/
public interface ResourceSideAcctService{
	/**
     * 查询资源方日消耗流量账单
     * @param netSid
     * @param date
     * @return
     */
    public List<TbRsBillAcct> queryResourceSideTDC(String netSid,String date);
    
    /**
     * 查询资源方月消耗流量账单
     * @param netSid
     * @param date
     * @return
     */
    public List<TbRsBillAcct> queryResourceSideTMC(String netSid,String date);
}
