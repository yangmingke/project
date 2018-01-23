package com.flypaas.dao;

import java.util.List;
import java.util.Map;

import com.flypaas.entity.vo.PageContainer;


public interface NewConsumeDao {

	public List<Map<String, Object>> getCurrentCsm(Map<String, Object> map);

	public PageContainer getApp45Csm(PageContainer page);

	public List<Map<String, Object>> getBillMonth(Map<String, Object> param);

	public String getBillCsmSum(Map<String, Object> param);

	public List<Map<String, Object>> getClientMonthBill(Map<String, Object> param);

	public int isExistsTable(String tableName);

	public List<Map<String, Object>> getFastDFS(String sql);
	
	public Map<String, Object> getYstCsm(Map<String, String> map);
	
	public List<Map<String, Object>> appCsmDataByMonth(Map<String, String> map);
	
	public PageContainer lyDetail(PageContainer page);

	public List<Map<String, Object>> getCurrentTrafficCsm(Map<String, Object> map);

	public PageContainer getAppFee(PageContainer page);

	public String getDayTotalFee(Map<String, Object> param);
}
