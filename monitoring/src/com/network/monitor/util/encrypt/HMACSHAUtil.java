package com.network.monitor.util.encrypt;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * 生成签名数据，请使用EncryptUtils.getSignature(dataStr, keyStr)
 * 
 * @author xiejiaan
 */
public class HMACSHAUtil {

	private static final String HMAC_SHA1 = "HmacSHA1";

	/**
	 * 生成签名数据
	 * 
	 * @param dataStr
	 *            待加密的数据
	 * @param keyStr
	 *            加密使用的key
	 * @return 生成MD5编码的字符串
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 */
	static String getSignature(String dataStr, String keyStr) throws InvalidKeyException, NoSuchAlgorithmException {
		byte[] data = dataStr.getBytes();
		byte[] key = keyStr.getBytes();
		SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
		Mac mac = Mac.getInstance(HMAC_SHA1);
		mac.init(signingKey);
		byte[] rawHmac = mac.doFinal(data);
		return encode(rawHmac);
	}

	/**
	 * 获取MD5 结果字符串
	 * 
	 * @param source
	 * @return
	 */
	private static String encode(byte[] source) {
		String s = null;
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest();
			char str[] = new char[16 * 2];
			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			s = new String(str);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return s;
	}

	public static void main(String[] args) {
		try {
			String test = getSignature("荷花哈", "20140404");
			System.out.println(test + "-" + test.length());
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
