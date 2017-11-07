package com.flypaas.cdr.dao;

import java.util.List;
import java.util.Map;

import com.flypaas.model.TbRsNodeConcurrence;

public interface NodeConcurrenceMapper {
	public int insert(TbRsNodeConcurrence tbRsNodeConcurrence);
	public void createTable();
	public List<TbRsNodeConcurrence> queryNodeConcurrent(Map<String, String> para);
}
