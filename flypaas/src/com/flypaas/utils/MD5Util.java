package com.flypaas.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
  * TODO MD5 算法
  * @author chenxijun
  * @version
 */
public class MD5Util {
	    // 全局数组
	    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
	            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	    // 返回形式为数字跟字符串
	    private static String byteToArrayString(byte bByte) {
	        int iRet = bByte;
	        if (iRet < 0) {
	            iRet += 256;
	        }
	        int iD1 = iRet / 16;
	        int iD2 = iRet % 16;
	        return strDigits[iD1] + strDigits[iD2];
	    }

	    // 返回形式只为数字
	    @SuppressWarnings("unused")
		private static String byteToNum(byte bByte) {
	        int iRet = bByte;
	        System.out.println("iRet1=" + iRet);
	        if (iRet < 0) {
	            iRet += 256;
	        }
	        return String.valueOf(iRet);
	    }

	    // 转换字节数组为16进制字串
	    public static String byteToString(byte[] bByte) {
	        StringBuffer sBuffer = new StringBuffer();
	        for (int i = 0; i < bByte.length; i++) {
	            sBuffer.append(byteToArrayString(bByte[i]));
	        }
	        return sBuffer.toString();
	    }

	    public static String GetMD5Code(String strObj) {
	        String resultString = null;
	        try {
	            resultString = new String(strObj);
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            // md.digest() 该函数返回值为存放哈希值结果的byte数组
	            resultString = byteToString(md.digest(strObj.getBytes()));
	        } catch (NoSuchAlgorithmException ex) {
	            ex.printStackTrace();
	        }
	        return resultString;
	    }

	    @SuppressWarnings("static-access")
		public static void main(String[] args) {
	        MD5Util getMD5 = new MD5Util();
	        System.out.println(getMD5.GetMD5Code("fbe59ffe9cd599dec9dcf927ce2dcdf7"));
	    }
}
