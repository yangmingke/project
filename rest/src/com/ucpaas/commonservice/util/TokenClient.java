package com.ucpaas.commonservice.util;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import net.sf.json.JSONObject;

public class TokenClient {

	/**
	 * {"Alg":"HS256","Accid":"ijklmn","Appid":"abcdefg","Userid":"jacky"}
	 * 
	 * {"Accid":"ijklmn","Appid":"abcdefg","Userid":"jacky"}
	 * 
	 * @param Accid
	 * @param Cnumber
	 * @param Expiretime
	 * @return
	 */
	public static String formatHearder(String Accid, String appid, String userid) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Alg", "HS256");
		jsonObject.put("Accid", Accid);
		jsonObject.put("Appid", appid);
		jsonObject.put("Userid", userid);
		return jsonObject.toString();
	}
	public static String formatPayload(String Accid, String appid, String userid) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Accid", Accid);
		jsonObject.put("Appid", appid);
		jsonObject.put("Userid", userid);
		return jsonObject.toString();
	}
	public static byte[] encryptHMAC(byte[] data, String key) throws Exception {
		SecretKey secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);
		return mac.doFinal(data);
	}
	public static String getToken(String Accid, String AccToken, String appid, String userid) {
		try {
			String slcHread = Base64.encode(formatHearder(Accid, appid, userid).getBytes());
			String payload = formatPayload(Accid, appid, userid);
			byte[] sec = encryptHMAC(payload.getBytes(), AccToken);
			String slcDepayload = Base64.encode(sec);
			String result = slcHread + "." + slcDepayload;
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {

		String aa = getToken("eda6f19bf87fc21e10f5626e596e8f74", "2aaa531a5c882f6d253b5e35e94c6111",
				"74119c5b5b814910bee74a8e1f1fed85", "71570009616531");
		System.out.println(aa);
	}
}
