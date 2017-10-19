package com.flypaas.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.constant.UserConstant;
import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.TbFlypaasUser;
import com.flypaas.entity.UserPic;
import com.flypaas.service.UserPicService;
import com.flypaas.service.UserService;
import com.flypaas.utils.DateUtil;
import com.flypaas.utils.FileUtil;
import com.flypaas.utils.StrUtil;
import com.flypaas.utils.SysConfig;
@Service
@Transactional
public class UserPicServiceImpl extends DaoCenter implements UserPicService {
	private UserPic userPic ;
	
	@Autowired
	private UserService userService;
	
	public int isExist(String sid) {
		return userPicDao.isExist(sid);
	}
	public void add(UserPic userPic) {
		userPicDao.add(userPic);
	}
	public UserPic getUserPic(String sid) {
		return userPicDao.getUserPic(sid);
	}
	public List<UserPic> getUserPicList(String sid) {
		return userPicDao.getUserPicList(sid);
	}
	public void update(UserPic userPic) {
		userPicDao.update(userPic);
	}
	public void delete(String sid) {
		userPicDao.delete(sid);
	}
	public int idNbrEnable(String idNbr){
		return userPicDao.idNbrEnable(idNbr);
	}
	
	public int idNbrEnable(String idNbr,String sid){
		UserPic userPic = new UserPic();
		userPic.setSid(sid);
		userPic.setIdNbr(idNbr);
		return userPicDao.idNbrEnable(userPic);
	}
	
	public UserPic getUserPicBySidAndType(Map<String, Object> map) {
		return userPicDao.getUserPicBySidAndType(map);
	}
	public void oauthPersonal(String sid,TbFlypaasUser user, File idCardFile,String type) {
		updateUser(sid,UserConstant.USER_TYPE_1,user);
		String url = FileUtil.upload(idCardFile,sid,SysConfig.getInstance().getProperty("oauth_pic"),type);
		addUserPicForPerson(sid,user,url);
	}
	public void oauthCompany(String sid, TbFlypaasUser user, File file1,
			File file2, File file3,String ittid,String bsnumid,String type1,String type2,String type3) {
		String url = FileUtil.upload(file1,sid,SysConfig.getInstance().getProperty("oauth_pic"),type1);
		String url1 = FileUtil.upload(file2,sid,SysConfig.getInstance().getProperty("oauth_pic"),type2);
		String url3 = FileUtil.upload(file3,sid,SysConfig.getInstance().getProperty("oauth_pic"),type3);
		updateUser(sid,UserConstant.USER_TYPE_2,user);
		//添加组织机构号
		addUserPicForCompany(sid,url,user.getOrgId(),UserConstant.OAUTH_TYPE_3);
		//添加税务登记号
		addUserPicForCompany(sid,url1,ittid,UserConstant.OAUTH_TYPE_4);
		//营业执照
		addUserPicForCompany(sid,url3,bsnumid, UserConstant.OAUTH_TYPE_5);
	}
	public void updatePs(String sid,TbFlypaasUser user,File idCardFile,String type){
		String imgUrl = null ;
		if(!StrUtil.isEmpty(idCardFile)){
			imgUrl = FileUtil.upload(idCardFile,sid,SysConfig.getInstance().getProperty("oauth_pic"),type);
		}
		updateUserProcess(sid, user);
		updateUserPicProcess(imgUrl, user,sid);
	}
	public void updateCp(String sid, TbFlypaasUser user, File file1,
			File file2, File file3,String ittid,String bsnumid,String type1,String type2,String type3){
		String imgUrl1 = null ;
		String imgUrl2 = null ;
		String imgUrl3 = null ;
		if(!StrUtil.isEmpty(file1)){
			imgUrl1 = FileUtil.upload(file1,sid,SysConfig.getInstance().getProperty("oauth_pic"),type1);
		}
		if(!StrUtil.isEmpty(file2)){
			imgUrl2 = FileUtil.upload(file2,sid,SysConfig.getInstance().getProperty("oauth_pic"),type2);
		}
		if(!StrUtil.isEmpty(file3)){
			imgUrl3 = FileUtil.upload(file3,sid,SysConfig.getInstance().getProperty("oauth_pic"),type3);
		}
		
		updateUserProcess(sid, user);
		updateUserPicProcessForCp(imgUrl1,UserConstant.OAUTH_TYPE_3,user.getOrgId()+"",sid);
		updateUserPicProcessForCp(imgUrl2, UserConstant.OAUTH_TYPE_4,ittid,sid);
		updateUserPicProcessForCp(imgUrl3, UserConstant.OAUTH_TYPE_5,bsnumid,sid);
	}
	public void qualificationUpdate(String sid, TbFlypaasUser user, File file1,
			File file2, File file3,String ittid,String bsnumid,String type1,String type2,String type3){
		//升级用户基础信息
		qualificationUpdateProcess(user,sid);
		//删除老的资质
		delete(sid);
		//添加新的资质信息
		String url = FileUtil.upload(file1,sid,SysConfig.getInstance().getProperty("oauth_pic"),type1);
		String url1 = FileUtil.upload(file2,sid,SysConfig.getInstance().getProperty("oauth_pic"),type2);
		String url2 = FileUtil.upload(file3,sid,SysConfig.getInstance().getProperty("oauth_pic"),type3);
		
		//添加组织机构号
		addUserPicForCompany(sid,url,user.getOrgId()+"",UserConstant.OAUTH_TYPE_3);
		//添加税务登记号
		addUserPicForCompany(sid,url1,ittid,UserConstant.OAUTH_TYPE_4);
		//添加营业执照号
		addUserPicForCompany(sid,url2,bsnumid,UserConstant.OAUTH_TYPE_5);
	}
	private void qualificationUpdateProcess(TbFlypaasUser user,String sid){
		TbFlypaasUser u = new TbFlypaasUser();
		u.setUserType("2");
		u.setRealname(user.getRealname());
		u.setAddress(user.getAddress());
		u.setIdType("");
		u.setIdNbr("");
		u.setOrgId(user.getOrgId());
		u.setUpdateDate(DateUtil.getCurrentDate());
		u.setSid(sid);
		u.setOauthStatus(UserConstant.AUTH_STATUS_2);
		u.setLegalPerson(user.getLegalPerson());
		u.setCompanyNbr(user.getCompanyNbr());
		u.setWebSite(user.getWebSite());
		u.setOauthDate(DateUtil.getCurrentDate());
		userService.updateUser(u);
	}
	private void updateUserPicProcessForCp(String imgUrl,String idType,String idNum,String sid){
		UserPic userPic = new UserPic();
		userPic.setSid(sid);
		userPic.setIdType(idType);
		userPic.setIdNbr(idNum);
		if(imgUrl!=null){
			userPic.setImgUrl(imgUrl);
		}
		if(idType.equals(UserConstant.OAUTH_TYPE_1)){
			userPic.setIdDesc("身份证");
		}else if(idType.equals(UserConstant.OAUTH_TYPE_2)){
			userPic.setIdDesc("护照");
		}else if(idType.equals(UserConstant.OAUTH_TYPE_3)){
			userPic.setIdDesc("组织机构证");
		}else if(idType.equals(UserConstant.OAUTH_TYPE_4)){
			userPic.setIdDesc("税务登记证");
		}else if(idType.equals(UserConstant.OAUTH_TYPE_5)){
			userPic.setIdDesc("营业执照");
		}
		userPic.setUpdateDate(DateUtil.getCurrentDate());
		update(userPic);
}
	private void updateUserPicProcess(String imgUrl,TbFlypaasUser user,String sid){
		
		UserPic userPic = new UserPic();
		userPic.setSid(sid);
		userPic.setIdType(user.getIdType());
		userPic.setIdNbr(user.getIdNbr());
		if(imgUrl!=null){
			userPic.setImgUrl(imgUrl);
		}
		if(user.getIdType().equals(UserConstant.OAUTH_TYPE_1)){
			userPic.setIdDesc("身份证");
		}else if(user.getIdType().equals(UserConstant.OAUTH_TYPE_2)){
			userPic.setIdDesc("护照");
		}else if(user.getIdType().equals(UserConstant.OAUTH_TYPE_3)){
			userPic.setIdDesc("组织机构证");
		}else if(user.getIdType().equals(UserConstant.OAUTH_TYPE_4)){
			userPic.setIdDesc("税务登记证");
		}else if(user.getIdType().equals(UserConstant.OAUTH_TYPE_5)){
			userPic.setIdDesc("营业执照");
		}
		userPic.setUpdateDate(DateUtil.getCurrentDate());
		update(userPic);
}
	private void updateUserProcess(String sid,TbFlypaasUser user){
		TbFlypaasUser u = new TbFlypaasUser();
		u.setRealname(user.getRealname());
		u.setIdType(user.getIdType());
		u.setIdNbr(user.getIdNbr());
		u.setOauthStatus(UserConstant.AUTH_STATUS_2);
		u.setUpdateDate(DateUtil.getCurrentDate());
		u.setSid(sid);
		u.setAddress(user.getAddress());
		u.setOrgId(user.getOrgId());
		u.setRevisability(UserConstant.OUTH_MODIFY_0);
		u.setLegalPerson(user.getLegalPerson());
		u.setCompanyNbr(user.getCompanyNbr());
		u.setWebSite(user.getWebSite());
		u.setOauthDate(DateUtil.getCurrentDate());
		userService.updateUser(u);
	}
	private void addUserPicForCompany(String sid,String url,String idNum,String idType){
		userPic = new UserPic();
		userPic.setCreateDate(DateUtil.getCurrentDate());
		userPic.setUpdateDate(DateUtil.getCurrentDate());
		userPic.setSid(sid);
		userPic.setIdNbr(idNum);
		userPic.setIdType(idType);
		userPic.setImgUrl(url);
		if(idType.equals(UserConstant.OAUTH_TYPE_1)){
			userPic.setIdDesc("身份证");
		}else if(idType.equals(UserConstant.OAUTH_TYPE_2)){
			userPic.setIdDesc("护照");
		}else if(idType.equals(UserConstant.OAUTH_TYPE_3)){
			userPic.setIdDesc("组织机构证");
		}else if(idType.equals(UserConstant.OAUTH_TYPE_4)){
			userPic.setIdDesc("税务登记证");
		}else if(idType.equals(UserConstant.OAUTH_TYPE_5)){
			userPic.setIdDesc("营业执照");
		}
		add(userPic);
	}
	private void addUserPicForPerson(String sid,TbFlypaasUser user,String url){
		userPic = new UserPic();
		userPic.setCreateDate(DateUtil.getCurrentDate());
		userPic.setUpdateDate(DateUtil.getCurrentDate());
		userPic.setSid(sid);
		userPic.setIdNbr(user.getIdNbr());
		userPic.setIdType(user.getIdType());
		userPic.setImgUrl(url);
		if(user.getIdType().equals(UserConstant.OAUTH_TYPE_1)){
			userPic.setIdDesc("身份证");
		}else if(user.getIdType().equals(UserConstant.OAUTH_TYPE_2)){
			userPic.setIdDesc("护照");
		}else if(user.getIdType().equals(UserConstant.OAUTH_TYPE_3)){
			userPic.setIdDesc("组织机构证");
		}else if(user.getIdType().equals(UserConstant.OAUTH_TYPE_4)){
			userPic.setIdDesc("税务登记证");
		}else if(user.getIdType().equals(UserConstant.OAUTH_TYPE_5)){
			userPic.setIdDesc("营业执照");
		}
		add(userPic);
	}
	private void updateUser(String sid,String type,TbFlypaasUser user){
		user.setSid(sid);
		user.setUpdateDate(DateUtil.getCurrentDate());
		user.setOauthStatus(UserConstant.AUTH_STATUS_2);
		user.setUserType(type);
		user.setOauthDate(DateUtil.getCurrentDate());
		userService.updateUser(user);
	}
}
