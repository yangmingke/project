package com.flypaas.entity;

import org.apache.ibatis.type.Alias;

@Alias(value="tb_flypaas_id")
public class FlypaasId {

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
