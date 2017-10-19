package com.ucpaas.commonservice.constant;

/**
 * sqlId常量类
 */
public class SqlConstant {
	
	
	/**用户sqlMapper*/
	public static final class UserInfoMapper{
		public static final String selectByMap = "com.ucpaas.commonservice.dao.UserInfoMapper.selectByMap";
		public static final String updateBySid = "com.ucpaas.commonservice.dao.UserInfoMapper.updateBySid";
		public static final String selectByPwdUserName = "com.ucpaas.commonservice.dao.UserInfoMapper.selectByPwdUserName";
	}
	
	/**用户余额sqlMapper*/
	public static final class UserBalanceInfoMapper{
		public static final String updateBySid = "com.ucpaas.commonservice.dao.UserBalanceInfoMapper.updateBySid";
		public static final String selectBySid = "com.ucpaas.commonservice.dao.UserBalanceInfoMapper.selectBySid";
	}
	
	/**应用sqlMapper*/
	public static final class AppInfoMapper{
		public static final String selectByMap = "com.ucpaas.commonservice.dao.AppInfoMapper.selectByMap";
		public static final String selectBySid = "com.ucpaas.commonservice.dao.AppInfoMapper.selectBySid";
		//说明：selectByMapCache不包含clientcount子账号总数的值
		public static final String selectPartByMap = "com.ucpaas.commonservice.dao.AppInfoMapper.selectPartByMap";
		public static final String updateByAppSid = "com.ucpaas.commonservice.dao.AppInfoMapper.updateByAppSid";
		public static final String updateClientCountByMap = "com.ucpaas.commonservice.dao.AppInfoMapper.updateClientCountByMap";
	}
	
	/**应用余额sqlMapper*/
	public static final class AppBalanceInfoMapper{
		public static final String updateByAppSid = "com.ucpaas.commonservice.dao.AppBalanceInfoMapper.updateByAppSid";
		public static final String selectByAppSid = "com.ucpaas.commonservice.dao.AppBalanceInfoMapper.selectByAppSid";
		
	}
	
	/**子账号client sqlMapper*/
	public static final class ClientInfoMapper{
		public static final String updateByClientNumber = "com.ucpaas.commonservice.dao.ClientInfoMapper.updateByClientNumber";
		public static final String selectByUin = "com.ucpaas.commonservice.dao.ClientInfoMapper.selectByUin";
		public static final String selectByClientNumber = "com.ucpaas.commonservice.dao.ClientInfoMapper.selectByClientNumber";
		public static final String updateByUin = "com.ucpaas.commonservice.dao.ClientInfoMapper.updateByUin";
		public static final String insert = "com.ucpaas.commonservice.dao.ClientInfoMapper.insert";
		public static final String deleteByUin = "com.ucpaas.commonservice.dao.ClientInfoMapper.deleteByUin";
		public static final String selectByAppSidAndIndex = "com.ucpaas.commonservice.dao.ClientInfoMapper.selectByAppSidAndIndex";
	}
	
	/**子账号余额clientBalance sqlMapper*/
	public static final class ClientBalanceInfoMapper{
		public static final String updateByClientNumber = "com.ucpaas.commonservice.dao.ClientBalanceInfoMapper.updateByClientNumber";    
		public static final String selectByClientNumber = "com.ucpaas.commonservice.dao.ClientBalanceInfoMapper.selectByClientNumber";  
		public static final String updateByUin = "com.ucpaas.commonservice.dao.ClientBalanceInfoMapper.updateByUin";  
		public static final String insert = "com.ucpaas.commonservice.dao.ClientBalanceInfoMapper.insert";  
		public static final String chargeClientBalanceByClientNumber = "com.ucpaas.commonservice.dao.ClientBalanceInfoMapper.chargeClientBalanceByClientNumber";  
		public static final String deleteByUin = "com.ucpaas.commonservice.dao.ClientBalanceInfoMapper.deleteByUin";  
		
	}
	
	/**反向表  sqlMapper*/
	public static final class Attr2uinInfoMapper{
		public static final String selectByMap = "com.ucpaas.commonservice.dao.Attr2uinInfoMapper.selectByMap";           
		public static final String insert = "com.ucpaas.commonservice.dao.Attr2uinInfoMapper.insert";           
		public static final String deleteByAttrType = "com.ucpaas.commonservice.dao.Attr2uinInfoMapper.deleteByAttrType";           
		public static final String deleteByUinType = "com.ucpaas.commonservice.dao.Attr2uinInfoMapper.deleteByUinType";           
		
	}
	
	/**应用子账号总表  sqlMapper*/
	public static final class AppClientInfoMapper{
		public static final String selectByMap = "com.ucpaas.commonservice.dao.AppClientInfoMapper.selectByMap";   
		public static final String selectCountAppSid = "com.ucpaas.commonservice.dao.AppClientInfoMapper.selectCountAppSid"; 
		
	}
	
	/**测试子账号总表  sqlMapper*/
	public static final class TestClientInfoMapper{
		public static final String updateByClientNumber = "com.ucpaas.commonservice.dao.TestClientInfoMapper.updateByClientNumber";  
		public static final String selectListByMap = "com.ucpaas.commonservice.dao.TestClientInfoMapper.selectListByMap";  
		public static final String selectByClientNumber = "com.ucpaas.commonservice.dao.TestClientInfoMapper.selectByClientNumber";  
		public static final String insert = "com.ucpaas.commonservice.dao.TestClientInfoMapper.insert"; 
		public static final String delete = "com.ucpaas.commonservice.dao.TestClientInfoMapper.deleteByPrimaryKey"; 
		
	}
	
	/**应用超级子账号关系表  sqlMapper*/
	public static final class AppSuperClientRelMapper{
		public static final String insert = "com.ucpaas.commonservice.dao.AppSuperClientRelInfoMapper.insert"; 
		public static final String selectByAppSid = "com.ucpaas.commonservice.dao.AppSuperClientRelInfoMapper.selectByAppSid"; 
		
	}
	
	
	
	

}
