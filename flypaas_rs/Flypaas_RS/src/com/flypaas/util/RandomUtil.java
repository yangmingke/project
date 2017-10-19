package com.flypaas.util;

import java.util.Random;
import java.util.UUID;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年1月14日 下午5:35:31
* 类说明
*/
public class RandomUtil {
	/**
	 * 生成uuid String型32位
	 * @return
	 */
	public static String randomToStr(){
		
		return MD5Util.encode2hex(UUID.randomUUID().toString());
	}
	
	/**
	 * 生成uuid int型9位
	 * @return
	 */
	public static int randomToInt(){
		int max = 999999999;
        int min = 100000000;
        Random random = new Random();
        int id = random.nextInt(max)%(max-min+1) + min;
		return id;
	}
	
}
