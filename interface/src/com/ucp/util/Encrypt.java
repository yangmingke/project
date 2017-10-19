package com.ucp.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class Encrypt {
	public static String deEncrypt(String strSrc, String encName) {
		// parameter strSrc is a string will be encrypted,
		// parameter encName is the algorithm name will be used.
		// encName dafault to "MD5"
		MessageDigest md = null;
		String strDes = null;

		byte[] bt = strSrc.getBytes();
		try {
			if (encName == null || encName.equals("")) {
				encName = "MD5";
			}
			md = MessageDigest.getInstance(encName);
			md.update(bt);
			strDes = bytes2Hex(md.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Invalid algorithm.");
			return null;
		}
		return strDes;
	}

	public static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}
	public static byte[] sha1InternalEncode(String in) {  
	    byte[] out = null;  
	    try {  
	        MessageDigest messagedigest = MessageDigest.getInstance("SHA-1");  
	        messagedigest.update(in.getBytes("UTF-8"));  
	        out = messagedigest.digest();  
	    } catch (NoSuchAlgorithmException e) {  
	    } catch (UnsupportedEncodingException e) {  
	    }  
	    return out;  
	}

	/**
	 * @autor Administrator(duanyj)
	 * @date 2013-6-21 上午10:35:05
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String DOMAIN="xx.xx.cn";
		String strSrc=DOMAIN+":"+"xxx";
//		System.out.println("Use Def:" + TestEncrypt.Encrypt(strSrc, null));
//		System.out.println("Use MD5:" + TestEncrypt.Encrypt(strSrc, "MD5"));
//		System.out.println("Use SHA:" + Encrypt.deEncrypt(strSrc, "SHA-1"));
//		System.out.println("Use SHA-256:" + TestEncrypt.Encrypt(strSrc, "SHA-256"));
		String pwString=new BASE64Encoder().encode(Encrypt.sha1InternalEncode("sxit123"+"2013022713460393700004"));
		System.out.println(pwString);
	}

}
