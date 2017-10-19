package com.flypaas.entity;

import java.util.Date;
import org.apache.ibatis.type.Alias;
import com.flypaas.utils.Des3Utils;

@Alias(value="tb_srv_sms_template")
public class SmsTemplate {

	private Long templateId ;
	private String appSid ;
	private String name ;
	private String content;
	private String status;
	private Integer type;
	private Integer rule;
	private Integer len;
	private String sign;
	private Date createDate;
	private Date updateDate;
	private Application app ;
	private String templateIdStr;
	private String contentStr;
	
	public Long getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Long templateId) {
		this.templateIdStr = Des3Utils.encodeDes3(templateId+"");
		this.templateId = templateId;
	}
	public String getAppSid() {
		return appSid;
	}
	public void setAppSid(String appSid) {
		this.appSid = appSid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public Application getApp() {
		return app;
	}
	public void setApp(Application app) {
		this.app = app;
	}
	public String getTemplateIdStr() {
		return templateIdStr;
	}
	public void setTemplateIdStr(String templateIdStr) {
		this.templateIdStr = templateIdStr;
	}
	public String getContentStr() {
		return contentStr;
	}
	public void setContentStr(String contentStr) {
		this.contentStr = contentStr;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getRule() {
		return rule;
	}
	public void setRule(Integer rule) {
		this.rule = rule;
	}
	public Integer getLen() {
		return len;
	}
	public void setLen(Integer len) {
		this.len = len;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
}
