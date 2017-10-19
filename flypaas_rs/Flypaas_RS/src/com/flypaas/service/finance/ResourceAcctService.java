package com.flypaas.service.finance;

import java.util.List;

import com.flypaas.model.TbRsBillAcctRTPP;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年1月11日 上午11:23:19
* 类说明
*/
public interface ResourceAcctService {
	/**
     * 查询资源节点日消耗流量账单
     * @param netSid
     * @param date
     * @return
     */
    public List<TbRsBillAcctRTPP> queryResourceTDC(String netSid,String date);
    
    /**
     * 查询资源节点月消耗流量账单
     * @param netSid
     * @param date
     * @return
     */
    public List<TbRsBillAcctRTPP> queryResourceTMC(String netSid,String date);
    
    /**
     * 查询资源节点日消耗流量账单总和
     * @param netSid
     * @param date
     * @return
     */
    public TbRsBillAcctRTPP queryResourceTDCTotal(String netSid, String date);
    
    /**
     * 查询资源节点月消耗流量账单总和
     * @param netSid
     * @param date
     * @return
     */
    public TbRsBillAcctRTPP queryResourceTMCTotal(String netSid, String month);
}
