package com.flypaas.dao;

import java.util.List;
import java.util.Map;

public interface TbRsRTPPNbMapper {

	public List<String> getNbList(Map<String, Object> map);

	public void deleteNb(Map para);

	public void insertNb(Map para);

	public List<String> getNnbList(Map<String, Object> map);

	public void deleteNnb(Map<String, Object> para);

	public void insertNnb(Map<String, Object> para);

}