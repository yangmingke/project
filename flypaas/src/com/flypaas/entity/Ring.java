package com.flypaas.entity;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import com.flypaas.utils.Des3Utils;
import com.flypaas.utils.SysConfig;

@Alias(value="tb_flypaas_call_ring")
public class Ring {
	private Long id;
	private String sid;
	private String appSid;
	private Integer type;
	private String fileName;
	private Date createDate;
	private Date updateDate;
	private Integer auditStatus;
	private Integer uploadStatus;
	private Integer contentType;
	private String content;
	private String userFileName;
	
	private String appName;
	private String fileFullPath;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getAppSid() {
		return appSid;
	}
	public void setAppSid(String appSid) {
		this.appSid = appSid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		String temp = Des3Utils.encodeDes3(SysConfig.getInstance().getProperty("oauth_ring")+fileName);
		setFileFullPath(temp);
		this.fileName = fileName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public Integer getUploadStatus() {
		return uploadStatus;
	}
	public void setUploadStatus(Integer uploadStatus) {
		this.uploadStatus = uploadStatus;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getFileFullPath() {
		return fileFullPath;
	}
	public void setFileFullPath(String fileFullPath) {
		this.fileFullPath = fileFullPath;
	}
	public Integer getContentType() {
		return contentType;
	}
	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserFileName() {
		return userFileName;
	}
	public void setUserFileName(String userFileName) {
		this.userFileName = userFileName;
	}
	
	
}
