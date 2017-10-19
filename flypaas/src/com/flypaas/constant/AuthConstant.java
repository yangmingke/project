package com.flypaas.constant;

import com.flypaas.utils.SysConfig;

/**
 * 第三方账号登录，相关常数
 * 
 * @author xiejiaan
 */
public class AuthConstant {

	/**
	 * 第三方账号登录的类型
	 * 
	 * @author xiejiaan
	 */
	public enum AuthType {
		mingdao("1", "明道");

		private String typeId;
		private String typeName;

		private AuthType(String typeId, String typeName) {
			this.typeId = typeId;
			this.typeName = typeName;
		}

		public String getTypeId() {
			return typeId;
		}

		public String getTypeName() {
			return typeName;
		}
	}

	/**
	 * <pre>
	 * 明道开放平台：app_key
	 * 网址：http://open.mingdao.com/
	 * 账号：xiejiaan@flypaas.com
	 * </pre>
	 */
	public static final String mingdao_app_key = "C905E0785E73";
	/**
	 * 明道开放平台：app_secret
	 */
	public static final String mingdao_app_secret = "993E9F4CE2725A824F465A97B1367";
	/**
	 * 明道开放平台：授权页面
	 */
	public static String mingdao_authorize_url = "https://api.mingdao.com/oauth2/authorize?format=json&app_key=%s&redirect_uri=%s";
	/**
	 * 明道开放平台：获取令牌
	 */
	public static String mingdao_access_token_url = "https://api.mingdao.com/oauth2/access_token?format=json&app_key=%s&app_secret=%s&grant_type=authorization_code&redirect_uri=%s&code=";
	/**
	 * 明道开放平台：获取用户信息
	 */
	public static final String mingdao_user_info_url = "https://api.mingdao.com/passport/detail?format=json&access_token=";
	static {
		String mingdaoCallback = "http://" + SysConfig.getInstance().getProperty("host") + "/auth/mingdaoCallback";
		mingdao_authorize_url = String.format(mingdao_authorize_url, mingdao_app_key, mingdaoCallback);
		mingdao_access_token_url = String.format(mingdao_access_token_url, mingdao_app_key, mingdao_app_secret,
				mingdaoCallback);
	}

}
