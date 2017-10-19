package com.ucpaas.commonservice.model;

import java.util.Date;

import com.ucpaas.commonservice.model.base.BaseInfo;

/**
 * 应用下超级子账号关系表实体类
 * @author 7yk7p02
 *
 */
public class AppSuperClientRelInfo extends BaseInfo {

	private static final long serialVersionUID = -6041565339545253513L;

	private String appSid;

	private String clientNumber;

    private Date createTime;

    public String getAppSid() {
        return appSid;
    }

    public void setAppSid(String appSid) {
        this.appSid = appSid == null ? null : appSid.trim();
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber == null ? null : clientNumber.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}