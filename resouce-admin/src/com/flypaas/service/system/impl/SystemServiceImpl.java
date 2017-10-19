package com.flypaas.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.flypaas.dao.TbRsConfigMapper;
import com.flypaas.model.TbRsConfig;
import com.flypaas.service.system.SystemService;

@Service
public class SystemServiceImpl implements SystemService {
	@Autowired
	TbRsConfigMapper tbRsConfigMapper;

	@Override
	public List<TbRsConfig> getSysConfigList() {
		List<TbRsConfig> sysConfigList = tbRsConfigMapper.getSysConfigList();
		return sysConfigList;
	}

	@Override
	public String updateConfig(TbRsConfig tbRsConfig) {
		try {
			int count = tbRsConfigMapper.updateConfig(tbRsConfig);
			if(count > 0){
				return "success";
			}
		} catch (DuplicateKeyException duplicateKeyException) {
			System.out.println(duplicateKeyException);
			return "duplicateKey";
		} catch(Exception e){
			System.out.println(e);
			return "false";
		}
		return "false";
	}

	@Override
	public String addConfig(TbRsConfig tbRsConfig) {
		try {
			int count = tbRsConfigMapper.addConfig(tbRsConfig);
			if(count > 0){
				return "success";
			}
		} catch (DuplicateKeyException duplicateKeyException) {
			System.out.println(duplicateKeyException);
			return "duplicateKey";
		} catch(Exception e){
			System.out.println(e);
			return "false";
		}
		return "false";
	}

	@Override
	public String delConfig(TbRsConfig tbRsConfig) {
		try {
			int count = tbRsConfigMapper.delConfigByKey(tbRsConfig);
			if(count > 0){
				return "success";
			}
		} catch(Exception e){
			System.out.println(e);
			return "false";
		}
		return "false";
	}

}
