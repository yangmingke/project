package com.ucpaas.commonservice.constant;

/**
 * 常量类
 * 
 * @author luke
 * 
 */
public class Constants {
	/** 操作成功 */
	public static final boolean RESULT_TRUE = true;

	/** 操作失败 */
	public static final boolean RESULT_FALSE = false;

	/** redis缓存-主账号key前缀=ucpaas:main:sid: */
	public static final String REDIS_KEY_USER = "ucpaas:main:";

	/** redis缓存-应用key前缀=ucpaas:app:appSid: */
	public static final String REDIS_KEY_APP = "ucpaas:app:";
	

	/** redis缓存-子账号key前缀=ucpaas:client:clientNumber */
	//public static final String REDIS_KEY_CLIENT = "ucpaas:client:";
	
	/** redis缓存-反向表key前缀=ucpaas:attr2uin:attr: */
	public static final String REDIS_KEY_ATTR2UIN = "ucpaas:attr2uin:";
	
	/**反向表类型:type=101,表示attr={userId}_{appId}*/
	public static final int ATTR2UIN_TYPE_101 = 101;
	
	/**反向表类型:type=102,表示attr={mobile}_{appId}*/
	public static final int ATTR2UIN_TYPE_102 = 102;
	
	/**
	 * client状态  0:关闭，1:正常，2:充值未平账
	 * 
	 */
	public static final String CLIENT_STATUS_CLOSE = "0";
	
	/**
	 * clientbalance状态 0：冻结 ，1 正常
	 */
	public static final String CLIENTBALANCE_STATUS_CLOSE = "0";
	

	/**
	 * 第三方删除client缓存接口地址
	 */
	public static final String CLIENT_DELETE_CACHE = "client.delete.cache";
	
	/**
	 * redis缓存最小有效天数
	 */
	public static final int REDIS_DAY_MIN = 1;
	
	/**
	 * redis缓存最大有效天数
	 */
	public static final int REDIS_DAY_MAX = 3;
	
	/**
	 * redis缓存有效时间n秒
	 */
	public static String REDIS_EFFECTIVE_SECONDS = "redis.effective.seconds";
	
	
	
	
	

}
