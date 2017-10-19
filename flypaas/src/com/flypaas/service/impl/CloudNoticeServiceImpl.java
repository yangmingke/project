package com.flypaas.service.impl;

import java.io.File;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.constant.AppConstant;
import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.NotifyCallFile;
import com.flypaas.entity.vo.PageContainer;
import com.flypaas.service.CloudNoticeService;
import com.flypaas.utils.DateUtil;
import com.flypaas.utils.FileUtil;
import com.flypaas.utils.SysConfig;
@Service
@Transactional
public class CloudNoticeServiceImpl extends DaoCenter implements CloudNoticeService {
	
	public PageContainer getCloudNtc(PageContainer page){
		return cloudNoticeDao.getCloudNtc(page);
	}

	public void add(File voice,NotifyCallFile ncf) {
		String filePath = SysConfig.getInstance().getProperty("oauth_ring")+AppConstant.APP_CLOUD_VOICE_PATH+"/";
		FileUtil.baseUpload(voice,filePath,ncf.getFileName());
		ncf.setFilePath(AppConstant.APP_CLOUD_VOICE_PATH);
		Date date = DateUtil.getCurrentDate();
		ncf.setCreateDate(date);
		ncf.setUpdateDate(date);
		ncf.setAuditStatus(AppConstant.APP_CLOUD_VOICE_STATUS);
		ncf.setType(AppConstant.APP_CLOUD_VOICE_TYPE);
		cloudNoticeDao.add(ncf);
	}

	public void delete(NotifyCallFile ncf) {
		cloudNoticeDao.delete(ncf);
	}
}
