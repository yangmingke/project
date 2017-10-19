package com.flypaas.dao;

import java.util.List;
import java.util.Map;

import com.flypaas.entity.UserPic;

public interface UserPicDao {

	public int isExist(String sid);
	public void add(UserPic userPic);
	public UserPic getUserPic(String sid);
	public List<UserPic> getUserPicList(String sid);
	public void update(UserPic userPic);
	public void delete(String sid);
	public int idNbrEnable(String idNbr);
	public int idNbrEnable(UserPic userPic);
	public UserPic getUserPicBySidAndType(Map<String, Object> map);
}
