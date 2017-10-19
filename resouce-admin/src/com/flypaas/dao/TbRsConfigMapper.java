package com.flypaas.dao;

import java.util.List;

import com.flypaas.model.TbRsConfig;

public interface TbRsConfigMapper {

	public List<TbRsConfig> getSysConfigList();

	public int updateConfig(TbRsConfig tbRsConfig);

	public int addConfig(TbRsConfig tbRsConfig);

	public int delConfigByKey(TbRsConfig tbRsConfig);
}