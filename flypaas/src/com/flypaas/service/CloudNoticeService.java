package com.flypaas.service;

import java.io.File;

import com.flypaas.entity.NotifyCallFile;
import com.flypaas.entity.vo.PageContainer;

public interface CloudNoticeService {

	public PageContainer getCloudNtc(PageContainer page);
	
	public void add(File voice,NotifyCallFile ncf);
	
	public void delete(NotifyCallFile ncf);

}
