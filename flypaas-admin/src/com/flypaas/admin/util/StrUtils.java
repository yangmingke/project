package com.flypaas.admin.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 
 * @author xiejiaan
 */
public class StrUtils {

	/**
	 * 替换字符串中的占位符，占位符的正则为\[@\w+@\]
	 * 
	 * @param data
	 *            处理的字符串
	 * @param params
	 *            替换的参数
	 * @return
	 */
	public static String replacePlaceholder(String data, Map<String, Object> params) {
		Pattern p = Pattern.compile("\\[@\\w+@\\]");
		Matcher m = p.matcher(data);
		String key;
		Object value;
		while (m.find()) {
			key = m.group();
			value = params.get(key.substring(2, key.length() - 2));
			if (value != null) {
				data = data.replace(key, value.toString());
			}
		}
		return data;
	}

	public static boolean isEmpty(Object o){
		if(o instanceof String){
			if(o==null || o.toString().trim().equals("")){
				return true;
			}
		}else{
			if(o==null){
				return true;
			}
		}
		return false;
	}

	//获取4位随机数
    public static int getRandom4(){
    	return (int) (Math.random()*9000+1000);
    }

    public static String getUUID() {  
        UUID uuid = UUID.randomUUID();  
        String str = uuid.toString();  
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);  
        return temp;  
    }

  //加密sid token
  	public static String toHMACSHA(String baseStr,String key){
  		String temp=null;
  		try {
  			temp = HMACSHAUtil.getSignature(baseStr,key);
  		} catch (InvalidKeyException e) {
  			e.printStackTrace();
  		} catch (NoSuchAlgorithmException e) {
  			e.printStackTrace();
  		}
  		return temp ;
  	} 
}
