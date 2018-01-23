package com.flypaas.admin.service.account;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;

/**
 * 计费类型
 * 
 * @author yangmingke
 */
@Service
@Transactional
public class FeeTypeServiceImpl implements FeeTypeService {
	@Autowired
	private MasterDao dao;

	@Override
	public PageContainer query(Map<String, String> params) {
		return dao.getSearchPage("feeType.query", "feeType.queryCount", params);
	}

	@Override
	public Map<String, Object> addFeeTypeItem(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		int count = dao.insert("feeType.insert", params);
		if(count == 1){
			data.put("result", "success");
			data.put("msg", "收费项目添加成功");
			return data;
		}
		data.put("result", "fail");
		data.put("msg", "收费项目添加失败");
		return data;
	}

	@Override
	public Map<String, Object> updateFeeTypeItem(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		int count = dao.update("feeType.update", params);
		if(count == 1){
			data.put("result", "success");
			data.put("msg", "收费项目添加成功");
			return data;
		}
		data.put("result", "fail");
		data.put("msg", "收费项目添加失败");
		return data;
	}

	@Override
	public Map<String, Object> delFeeTypeItem(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		int count = dao.update("feeType.delete", params);
		if(count == 1){
			data.put("result", "success");
			data.put("msg", "收费项目删除成功");
			return data;
		}
		data.put("result", "fail");
		data.put("msg", "收费项目删除失败");
		return data;
	}

}
