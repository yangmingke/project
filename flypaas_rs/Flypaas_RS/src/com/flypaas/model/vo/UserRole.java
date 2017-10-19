package com.flypaas.model.vo;

import com.flypaas.model.TbRsRole;
import com.flypaas.model.TbRsUserInfo;

public class UserRole {
	
	private TbRsUserInfo user;
	private TbRsRole role;
	
	public TbRsUserInfo getUser() {
		return user;
	}
	public void setUser(TbRsUserInfo user) {
		this.user = user;
	}
	public TbRsRole getRole() {
		return role;
	}
	public void setRole(TbRsRole role) {
		this.role = role;
	}
	
	
}
