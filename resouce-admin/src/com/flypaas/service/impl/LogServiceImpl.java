package com.flypaas.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.dao.TbRsOprateLogMapper;
import com.flypaas.model.TbRsOprateLog;
import com.flypaas.model.vo.LogUser;
import com.flypaas.service.LogService;
import com.flypaas.util.PageContainer;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2016年12月29日 上午9:55:35
* 类说明
*/
@Service("logServiceImpl")
public class LogServiceImpl implements LogService {
	@Autowired
	private TbRsOprateLogMapper tbRsOprateLogMapper;
	
	@Override
	public int deleteByPrimaryKey(Long logId) {

		return 0;
	}

	@Override
	public int insert(TbRsOprateLog record) {

		return tbRsOprateLogMapper.insert(record);
	}

	@Override
	public int insertSelective(TbRsOprateLog record) {
		
		return 0;
	}

	@Override
	public TbRsOprateLog selectByPrimaryKey(Long logId) {
		
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(TbRsOprateLog record) {
		
		return 0;
	}

	@Override
	public int updateByPrimaryKey(TbRsOprateLog record) {
		
		return 0;
	}

	@Override
	public List<LogUser> queryLog(String netSid) {
		
		return tbRsOprateLogMapper.queryLog(netSid);
	}

	@Override
	public int queryCount(String netSid) {
		
		return tbRsOprateLogMapper.queryCount(netSid);
	}
	
	@Override
	public PageContainer queryLog(Map<String, Object> para, int page, String netSid){
		int total = tbRsOprateLogMapper.queryCount(para);//计算总页数
		PageContainer pageContainer = new PageContainer();
		para = pageContainer.createParameter(para, page, total);
		List<Object> result = tbRsOprateLogMapper.queryLogPage(para);
		pageContainer.setResult(result);
		return pageContainer;
	}
	

	
}
