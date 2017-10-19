package com.flypaas.service.impl;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.Ring;
import com.flypaas.entity.vo.PageContainer;
import com.flypaas.service.RingService;
import com.flypaas.utils.FileUtil;
import com.flypaas.utils.StrUtil;
import com.flypaas.utils.SysConfig;
@Service
@Transactional
public class RingServiceImpl extends DaoCenter implements RingService {
	Logger logger = LoggerFactory.getLogger(RingServiceImpl.class);
	public PageContainer query(PageContainer page) {
		return myBatisDao.getSearchPage("ringServiceImpl_query", "ringServiceImpl_count", page);
	}

	public void add(Ring ring,File file,String type,String fileType,String fileId) {
		if(!StrUtil.isEmpty(file)){
			logger.debug("上传的铃音开始,路径："+SysConfig.getInstance().getProperty("oauth_ring")+ring.getId().toString()+"_"+type+"."+fileType);
			String fileName = FileUtil.uploadRing(file,fileId,SysConfig.getInstance().getProperty("oauth_ring"),type,fileType);
			logger.debug("上传的铃音完成.上传的铃音名称的:"+fileName);
			ring.setFileName(fileName);
		}
		ringDao.add(ring);
	}

	public Ring get(Ring ring) {
		return ringDao.get(ring);
	}

	public void update(Ring ring, File file, String type,String fileType,String fileId) {
		if(!StrUtil.isEmpty(file)){
			String fileName = FileUtil.uploadRing(file,fileId,SysConfig.getInstance().getProperty("oauth_ring"),type,fileType);
			ring.setFileName(fileName);
		}
		ringDao.update(ring);
	}

	public void delete(Ring ring) {
		ringDao.delete(ring);
	}

	public Ring getRingByIdSid(Ring ring) {
		return ringDao.getRingByIdSid(ring);
	}

}
