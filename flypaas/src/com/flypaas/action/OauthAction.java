package com.flypaas.action;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.constant.SysConstant;
import com.flypaas.constant.UserConstant;
import com.flypaas.entity.TbFlypaasUser;
import com.flypaas.entity.UserPic;
import com.flypaas.utils.FileUtil;
import com.flypaas.utils.StrUtil;

@Controller
@Scope("prototype")
@Results({
	@Result(name="dispComSuc",type="redirectAction",location="oAuthCompanyInfo"),
	@Result(name="dispPersSuc",type="redirectAction",location="oAuthPersonalInfo"),
	@Result(name="toOauthSuc",location="/page/user/oauth.jsp"),
	@Result(name="oauthPersonalSuc",type="redirectAction",location="oAuthPersonalInfo"),
	@Result(name="oauthCompanySuc",type="redirectAction",location="oAuthCompanyInfo"),
	@Result(name="personalInfoSuc",location="/page/user/oauth_info.jsp"),
	@Result(name="companyInfoSuc",location="/page/user/oauth_info.jsp"),
	@Result(name="updateSuc",type="redirectAction",location="oAuthCompanyInfo"),
	@Result(name="updatePage",location="/page/user/oauth.jsp")
})
public class OauthAction extends BaseAction {
	private TbFlypaasUser user = null;
	private UserPic userPic = null ;
	
	private File idCardFile = null;
	private String idCardFileFileName;
	
	private File taxEnrolCertificateFile = null ;
	private String taxEnrolCertificateFileFileName = null ;
	
	private File institutionalFile  = null ;
	private String institutionalFileFileName  = null ;
	
	private File bsnumFile = null ;
	private String bsnumFileFileName= null ;
	
	private String sid;
	private String ittid;
	private List<UserPic> userPicList = null ;
	private String idNum;
	private String bsnumid ;
	private String idNbr ;
	/*------------------------------------------------认证分发--------------------------------------*/
	@Action("/user/oAuthDispather")
	public String oAuthDispather(){
		user = userService.findUserById(getSessionUser().getSid());
		String result = null ;
		if(!StrUtil.isEmpty(user.getOauthStatus())){
			if(UserConstant.USER_TYPE_1.equals(user.getUserType())){
				result = "dispPersSuc" ;
			}else if(UserConstant.USER_TYPE_2.equals(user.getUserType())){
				result = "dispComSuc" ;
			}
		}else{
			result = "toOauthSuc" ;
		}
		return result;
	}
	/*------------------------------------------------个人认证--------------------------------------*/
	@Action("/user/oAuthPersonal")
	public String oAuthPersonal(){
		String name = user.getRealname();
		boolean boo = StrUtil.constainsSymbol(name);
		if(boo){
			StrUtil.writeMsg(response,"请输入合法的用户名",null);
			return null ;
		}
		UserPic u = userPicService.getUserPic(getSessionUser().getSid());
		sid = getSessionUser().getSid();
		if(StrUtil.isEmpty(u)){
			//新增
			int count = userPicService.idNbrEnable(user.getIdNbr());
			if(count>0){
				StrUtil.writeMsg(response,"证件号码已经被占用",null);
				return null ;
			}
			String result = FileUtil.checkFile(idCardFile,SysConstant.SYS_PIC_MAXSIZE);
			if(result!=null){
				StrUtil.writeMsg(response,result,null);
				return null ;
			}
			boolean  imgType = FileUtil.checkImgType(idCardFile);
			if(!imgType){
				StrUtil.writeMsg(response, "图片格式不正确", null);
				return null ;
			}
			String type = idCardFileFileName.substring(idCardFileFileName.lastIndexOf(".")+1);
			userPicService.oauthPersonal(sid, user, idCardFile,type);
			//刷新缓存
			getSessionUser().setUserType(UserConstant.USER_TYPE_1);
			getSessionUser().setOauthStatus(UserConstant.AUTH_STATUS_2);
		}else{
			//更新
			Map<String, Object> map = null ;
			map = new HashMap<String, Object>();
			map.put("sid", getSessionUser().getSid());
			if(UserConstant.OAUTH_TYPE_1.equals(user.getIdType())){
				map.put("type",UserConstant.OAUTH_TYPE_1);
			}else{
				map.put("type",UserConstant.OAUTH_TYPE_2);
			}
			UserPic up = userPicService.getUserPicBySidAndType(map);
			String IdTmep = up==null?null:up.getIdNbr();
			if(!StrUtil.isEmpty(IdTmep) && !IdTmep.equals(user.getIdNbr())){
				int count = userPicService.idNbrEnable(user.getIdNbr());
				if(count>0){
					StrUtil.writeMsg(response,"身份证号码已经被占用",null);
					return null ;
				}
			}
			String type = null ;
			if(idCardFile!=null){
				String result1 = FileUtil.checkFile(idCardFile,SysConstant.SYS_PIC_MAXSIZE);
				if(result1!=null){
					StrUtil.writeMsg(response,result1,null);
					return null ;
				}
				boolean  imgType = FileUtil.checkImgType(idCardFile);
				if(!imgType){
					StrUtil.writeMsg(response, "图片格式不正确", null);
					return null ;
				}
				type = idCardFileFileName.substring(idCardFileFileName.lastIndexOf(".")+1);
			}
			userPicService.updatePs(sid,user,idCardFile,type);
		}
		return "oauthPersonalSuc";
	}
	/*------------------------------------------------企业认证--------------------------------------*/
	@Action("/user/oAuthCompany")
	public String oAuthCompany(){
		boolean boo = checkComp(user);
		if(!boo){
			StrUtil.writeMsg(response, "认证信息不合法，请检查后重试.",null);
			return null;
		}
		String type1 = null;
		String type2 = null;
		String type3 = null;
		if(taxEnrolCertificateFile!=null){
			String result1 = FileUtil.checkFile(taxEnrolCertificateFile,SysConstant.SYS_PIC_MAXSIZE);
			if(result1!=null){
				StrUtil.writeMsg(response,result1,null);
				return null ;
			}
			boolean  imgType = FileUtil.checkImgType(taxEnrolCertificateFile);
			if(!imgType){
				StrUtil.writeMsg(response, "图片格式不正确", null);
				return null ;
			}
			type1 = taxEnrolCertificateFileFileName.substring(taxEnrolCertificateFileFileName.lastIndexOf(".")+1);
		}
		if(institutionalFile!=null){
			String result2 = FileUtil.checkFile(institutionalFile,SysConstant.SYS_PIC_MAXSIZE);
			if(result2!=null){
				StrUtil.writeMsg(response,result2,null);
				return null ;
			}
			boolean  imgType = FileUtil.checkImgType(institutionalFile);
			if(!imgType){
				StrUtil.writeMsg(response, "图片格式不正确", null);
				return null ;
			}
			type2 = institutionalFileFileName.substring(institutionalFileFileName.lastIndexOf(".")+1);
		}
		if(bsnumFile!=null){
			String result3 = FileUtil.checkFile(bsnumFile,SysConstant.SYS_PIC_MAXSIZE);
			if(result3!=null){
				StrUtil.writeMsg(response,result3,null);
				return null ;
			}
			boolean  imgType = FileUtil.checkImgType(bsnumFile);
			if(!imgType){
				StrUtil.writeMsg(response, "图片格式不正确", null);
				return null ;
			}
			type3 = bsnumFileFileName.substring(bsnumFileFileName.lastIndexOf(".")+1);
		}
		List<UserPic> p = userPicService.getUserPicList(getSessionUser().getSid()) ;
		sid = getSessionUser().getSid();
		if(StrUtil.isEmpty(p)||p.size()==0){
			int count = userPicService.idNbrEnable(user.getOrgId(),sid);
			if(count>0){
				StrUtil.writeMsg(response,"组织机构代码已经被占用",null);
				return null ;
			}
			int count1 = userPicService.idNbrEnable(ittid,sid);
			if(count1>0){
				StrUtil.writeMsg(response,"税务登记号已经被占用",null);
				return null ;
			}
			int count2 = userPicService.idNbrEnable(bsnumid,sid);
			if(count2>0){
				StrUtil.writeMsg(response,"营业执照号已经被占用",null);
				return null ;
			}
			userPicService.oauthCompany(sid, user, taxEnrolCertificateFile, institutionalFile, bsnumFile,ittid,bsnumid,type1,type2,type3);
			//刷新缓存
			getSessionUser().setUserType(UserConstant.USER_TYPE_2);
			getSessionUser().setOauthStatus(UserConstant.AUTH_STATUS_2);
		}else{
			Map<String, Object> map = null ;
			map = new HashMap<String, Object>();
			map.put("sid", getSessionUser().getSid());
			map.put("type",UserConstant.OAUTH_TYPE_3);
			UserPic up = userPicService.getUserPicBySidAndType(map);
			String orgIdTmep = up==null?null:up.getIdNbr();
			if(!StrUtil.isEmpty(orgIdTmep) && !orgIdTmep.equals(user.getOrgId()) ){
				int count = userPicService.idNbrEnable(user.getOrgId());
				if(count>0){
					StrUtil.writeMsg(response,"组织机构代码已经被占用",null);
					return null ;
				}
			}
			map = new HashMap<String, Object>();
			map.put("sid", sid);
			map.put("type",UserConstant.OAUTH_TYPE_4);
			up = userPicService.getUserPicBySidAndType(map);
			String ittidTmep = up==null?null:up.getIdNbr();
			if(!StrUtil.isEmpty(ittidTmep) && !ittidTmep.equals(ittid)){
				int count1 = userPicService.idNbrEnable(ittid);
				if(count1>0){
					StrUtil.writeMsg(response,"税务登记号已经被占用",null);
					return null ;
				}
			}
			map = new HashMap<String, Object>();
			map.put("sid", sid);
			map.put("type",UserConstant.OAUTH_TYPE_5);
			up = userPicService.getUserPicBySidAndType(map);
			String bsnumidTmep = up==null?null:up.getIdNbr();
			if(!StrUtil.isEmpty(bsnumidTmep) && !bsnumidTmep.equals(bsnumid)){
				int count2 = userPicService.idNbrEnable(bsnumid);
				if(count2>0){
					StrUtil.writeMsg(response,"营业执照号已经被占用",null);
					return null ;
				}
			}
			userPicService.updateCp(sid, user, taxEnrolCertificateFile,institutionalFile,bsnumFile,ittid,bsnumid,type1,type2,type3);
		}
		
		return "oauthCompanySuc";
	}
	private boolean checkComp(TbFlypaasUser user){
		boolean boo = true ;
		String name = user.getRealname();
		String legalPerson = user.getLegalPerson();
		String orgId = user.getOrgId();
		String ittId = ittid;
		String bsnumId = bsnumid ;
		if(StrUtil.constainsSymbol(name)||StrUtil.constainsSymbol(legalPerson)){
			boo = false;
		}
		if(!StrUtil.checkOrgId(orgId)){
			boo = false;
		}
		if(!StrUtil.checkIttsId(ittId)){
			boo = false;
		}
		if(!StrUtil.checkBsNum(bsnumId)){
			boo = false;
		}
		return boo;
	}
	
	/*------------------------------------------------查看个人信息--------------------------------------*/
	@Action("/user/oAuthPersonalInfo")
	public String oAuthPersonalInfo(){
		geUserInfo();
		getPersonalUserPic();
		return "personalInfoSuc" ;
	}
	/*------------------------------------------------查看企业信息--------------------------------------*/
	@Action("/user/oAuthCompanyInfo")
	public String oAuthCompanyInfo(){
		geUserInfo();
		getCompanyUserPic();
		return "companyInfoSuc" ;
	}
	/*------------------------------------------------跳转到更新个人信息页--------------------------------------*/
	@Action("/user/updatePerson")
	public String updatePsPage(){
		geUserInfo();
		getPersonalUserPic();
		return "updatePage";
	}
	/*------------------------------------------------跳转到更新企业信息页--------------------------------------*/
	@Action("/user/updateCompany")
	public String updateCpPage(){
		geUserInfo();
		getCompanyUserPic();
		return "updatePage";
	}
	/*-----------------------------------------------资质升级-----------	---------------------------*/
	@Action("/user/qlfcUpdate")
	public String qualificationUpdate(){
		boolean boo = checkComp(user);
		if(!boo){
			StrUtil.writeMsg(response, "认证信息不合法，请检查后重试.",null);
			return null;
		}
		String sid = getSessionUser().getSid();
		int count = userPicService.idNbrEnable(user.getOrgId(),sid);
		if(count>0){
			StrUtil.writeMsg(response,"组织机构代码已经被占用",null);
			return null ;
		}
		int count1 = userPicService.idNbrEnable(ittid,sid);
		if(count1>0){
			StrUtil.writeMsg(response,"税务登记号已经被占用",null);
			return null ;
		}
		int count2 = userPicService.idNbrEnable(bsnumid,sid);
		if(count2>0){
			StrUtil.writeMsg(response,"营业执照号已经被占用",null);
			return null ;
		}
		String result1 = FileUtil.checkFile(taxEnrolCertificateFile,SysConstant.SYS_PIC_MAXSIZE);
		if(result1!=null){
			StrUtil.writeMsg(response,result1,null);
			return null ;
		}
		String result2 = FileUtil.checkFile(institutionalFile,SysConstant.SYS_PIC_MAXSIZE);
		if(result2!=null){
			StrUtil.writeMsg(response,result2,null);
			return null ;
		}
		String result3 = FileUtil.checkFile(bsnumFile,SysConstant.SYS_PIC_MAXSIZE);
		if(result3!=null){
			StrUtil.writeMsg(response,result3,null);
			return null ;
		}
		sid = getSessionUser().getSid();
		String type1 = taxEnrolCertificateFileFileName.substring(taxEnrolCertificateFileFileName.lastIndexOf(".")+1);
		String type2 = institutionalFileFileName.substring(institutionalFileFileName.lastIndexOf(".")+1);
		String type3 = bsnumFileFileName.substring(bsnumFileFileName.lastIndexOf(".")+1);
		userPicService.qualificationUpdate(sid, user, taxEnrolCertificateFile, institutionalFile, bsnumFile, ittid, bsnumid,type1,type2,type3);
		//更新本地session信息
		user = userService.findUserById(sid);
		recordSession(user);
		return "updateSuc" ;
	}
	@Action("/user/ckIDNumEnable")
	public void ckIDNumEnable(){
		int count = userPicService.idNbrEnable(idNum);
		printMsg(count==0?"0":"1");
	}
	private void getCompanyUserPic(){
		userPicList = userPicService.getUserPicList(sid);
	}
	private void getPersonalUserPic(){
		userPic = userPicService.getUserPic(sid);
	}
	private void geUserInfo(){
		sid = getSessionUser().getSid();
		user = userService.findUserById(sid);
	}
	public TbFlypaasUser getUser() {
		return user;
	}
	public void setUser(TbFlypaasUser user) {
		this.user = user;
	}
	public UserPic getUserPic() {
		return userPic;
	}
	public void setUserPic(UserPic userPic) {
		this.userPic = userPic;
	}
	
	public File getIdCardFile() {
		return idCardFile;
	}
	public void setIdCardFile(File idCardFile) {
		this.idCardFile = idCardFile;
	}
	public File getTaxEnrolCertificateFile() {
		return taxEnrolCertificateFile;
	}
	public void setTaxEnrolCertificateFile(File taxEnrolCertificateFile) {
		this.taxEnrolCertificateFile = taxEnrolCertificateFile;
	}
	public File getInstitutionalFile() {
		return institutionalFile;
	}
	public void setInstitutionalFile(File institutionalFile) {
		this.institutionalFile = institutionalFile;
	}
	public String getIttid() {
		return ittid;
	}
	public void setIttid(String ittid) {
		this.ittid = ittid;
	}
	public List<UserPic> getUserPicList() {
		return userPicList;
	}
	public void setUserPicList(List<UserPic> userPicList) {
		this.userPicList = userPicList;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public File getBsnumFile() {
		return bsnumFile;
	}
	public void setBsnumFile(File bsnumFile) {
		this.bsnumFile = bsnumFile;
	}
	public String getBsnumid() {
		return bsnumid;
	}
	public void setBsnumid(String bsnumid) {
		this.bsnumid = bsnumid;
	}
	public String getIdNbr() {
		return idNbr;
	}
	public void setIdNbr(String idNbr) {
		this.idNbr = idNbr;
	}
	public String getIdCardFileFileName() {
		return idCardFileFileName;
	}
	public void setIdCardFileFileName(String idCardFileFileName) {
		this.idCardFileFileName = idCardFileFileName;
	}
	public String getTaxEnrolCertificateFileFileName() {
		return taxEnrolCertificateFileFileName;
	}
	public void setTaxEnrolCertificateFileFileName(
			String taxEnrolCertificateFileFileName) {
		this.taxEnrolCertificateFileFileName = taxEnrolCertificateFileFileName;
	}
	public String getInstitutionalFileFileName() {
		return institutionalFileFileName;
	}
	public void setInstitutionalFileFileName(String institutionalFileFileName) {
		this.institutionalFileFileName = institutionalFileFileName;
	}
	public String getBsnumFileFileName() {
		return bsnumFileFileName;
	}
	public void setBsnumFileFileName(String bsnumFileFileName) {
		this.bsnumFileFileName = bsnumFileFileName;
	}
	
	
}
