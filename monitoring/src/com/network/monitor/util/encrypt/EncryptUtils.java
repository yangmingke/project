package com.network.monitor.util.encrypt;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 加密工具类
 * 
 * @author xiejiaan
 */
public class EncryptUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(EncryptUtils.class);

	// 系统生成sid秘钥
	private static final String KEY = "8989621";
	private static final String SID_BASE_STRING = "123321";

	/**
	 * 生成签名数据
	 * 
	 * @param dataStr
	 *            待加密的数据
	 * @param keyStr
	 *            加密使用的key
	 * @return
	 */
	public static String getSignature(String dataStr, String keyStr) {
		String result = null;
		try {
			result = HMACSHAUtil.getSignature(dataStr, keyStr);
		} catch (Throwable e) {
			LOGGER.error("生成签名数据【失败】", e);
		}
		return result;
	}

	/**
	 * 生成sid
	 * 
	 * @return
	 */
	public static String generateSid() {
		return getSignature(SID_BASE_STRING + System.currentTimeMillis(), KEY);
	}

	/**
	 * md5加密
	 * 
	 * @param str
	 * @return
	 */
	public static String encodeMd5(String str) {
		return MD5Util.getMD5Code(str);
	}

	/**
	 * Base64加密
	 * 
	 * @param str
	 * @return
	 */
	public static String encodeBase64(String str) {
		String result = null;
		BASE64Encoder encoder = new BASE64Encoder();
		try {
			result = encoder.encode(str.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Base64加密失败：str=" + str, e);
		}
		return result;
	}

	/**
	 * Base64解密
	 * 
	 * @param str
	 * @return
	 */
	public static String decodeBase64(String str) {
		String result = null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			result = new String(decoder.decodeBuffer(str), "utf-8");
		} catch (IOException e) {
			LOGGER.error("Base64解密失败：str=" + str, e);
		}
		return result;
	}

	/**
	 * des3加密
	 * 
	 * @param str
	 *            需要加密的字符串
	 * @return
	 */
	public static String encodeDes3(String str) {
		return Des3Utils.encodeDes3(str);
	}

	/**
	 * des3解密
	 * 
	 * @param str
	 *            需要解密的字符串
	 * @return
	 */
	public static String decodeDes3(String str) {
		return Des3Utils.decodeDes3(str);
	}

	public static void main(String[] args) {
		String str = "xiejiaan7777";
		str = encodeDes3(str);
		System.out.println("encodeDes3：" + str);
		str = decodeDes3(str);
		System.out.println("decodeDes3：" + str);
	}

}
