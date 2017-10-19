package com.flypaas.dao;

import com.flypaas.entity.NotifyCallFile;
import com.flypaas.entity.vo.PageContainer;

public interface CloudNoticeDao {

	public PageContainer getCloudNtc(PageContainer page);
	
	public void add(NotifyCallFile ncf);
	
	public void delete(NotifyCallFile ncf);
}
