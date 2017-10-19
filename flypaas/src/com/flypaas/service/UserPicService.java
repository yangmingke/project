package com.flypaas.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.flypaas.entity.TbFlypaasUser;
import com.flypaas.entity.UserPic;

public interface UserPicService {
	
	public int isExist(String sid);
	
	public void add(UserPic userPic);
	
	public UserPic getUserPic(String sid);
	
	public List<UserPic> getUserPicList(String sid);
	
	public void update(UserPic userPic);
	
	public void delete(String sid);
	
	public int idNbrEnable(String idNbr);
	
	public int idNbrEnable(String idNbr,String sid);
	
	public UserPic getUserPicBySidAndType(Map<String, Object> map);
	
	public void oauthPersonal(String sid,TbFlypaasUser user,File file,String type);
	
	public void oauthCompany(String sid, TbFlypaasUser user, File file1,
			File file2, File file3,String ittid,String bsnumid,String type1,String type2,String type3);
	
	public void updatePs(String sid,TbFlypaasUser user,File idCardFile,String type);
	
	public void updateCp(String sid, TbFlypaasUser user, File file1,
			File file2, File file3,String ittid,String bsnumid,String type1,String type2,String type3);
	
	public void qualificationUpdate(String sid, TbFlypaasUser user, File file1,
			File file2, File file3,String ittid,String bsnumid,String type1,String type2,String type3);
	
}
