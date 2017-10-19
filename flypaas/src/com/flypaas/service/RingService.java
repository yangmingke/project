package com.flypaas.service;

import java.io.File;

import com.flypaas.entity.Ring;
import com.flypaas.entity.vo.PageContainer;

public interface RingService {
	
	public PageContainer query(PageContainer page);
	
	public Ring get(Ring ring);
	
	public Ring getRingByIdSid(Ring ring);
	
	public void add(Ring ring,File file,String type,String fileType,String fileId);
	
	public void update(Ring ring,File file,String type,String fileType,String fileId);
	
	public void delete(Ring ring);

	
}
