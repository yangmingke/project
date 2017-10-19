package com.ucpaas.commonservice.model;

import com.ucpaas.commonservice.model.base.BaseInfo;

/**
 * 反向表实体类
 * @author luke
 *
 */
public class Attr2uinInfo extends BaseInfo {
	private static final long serialVersionUID = -3030590857260672005L;

	private String attr;

	/**类型，101：attr={userId}_{appId}；102：attr={mobile}_{appId}*/
	private Integer type;

	private Integer uin;

	private Integer ctime;
	
    //分表信息
    private String uin_mod;

	public Integer getUin() {
		return uin;
	}

	public void setUin(Integer uin) {
		this.uin = uin;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr == null ? null : attr.trim();
	}

	public Integer getCtime() {
		return ctime;
	}

	public void setCtime(Integer ctime) {
		this.ctime = ctime;
	}

	public String getUin_mod() {
		return uin_mod;
	}

	public void setUin_mod(String uin_mod) {
		this.uin_mod = uin_mod;
	}
	
	
}