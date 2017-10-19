package com.flypaas.service.system;

import java.util.List;

import com.flypaas.model.TbRsConfig;

public interface SystemService {

	public List<TbRsConfig> getSysConfigList();

	public String updateConfig(TbRsConfig tbRsConfig);

	public String addConfig(TbRsConfig tbRsConfig);

	public String delConfig(TbRsConfig tbRsConfig);

}
