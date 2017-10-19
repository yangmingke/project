package com.flypaas.util;

import java.math.BigDecimal;

/**
 * @author yangmingke
 */
public class TransformUtil {
	
	/**
	 * 
	 * @param d 需要截取的数
	 * @param scale 截取保留scale位小数
	 * @return
	 */
	public static double doubleDown(double d,int scale){
		BigDecimal bg = new BigDecimal(d);
        double f = bg.setScale(scale, BigDecimal.ROUND_DOWN).doubleValue();
        return f;
	}
	/**
	 * 
	 * @param d 需要截取的数
	 * @param scale 截取保留scale位小数
	 * @return
	 */
	public static String doubleDown2Str(double d,int scale){
		BigDecimal bg = new BigDecimal(d);
        double f = bg.setScale(scale, BigDecimal.ROUND_DOWN).doubleValue();
        return String.format("%." + scale + "f", f);
	}
	/**
	 * 
	 * @param d 需要转换的数
	 * @param scale 转换保留scale位小数
	 * @param roundingMode BigDecimal转换策略
	 * @return
	 */
	public static double doubleTransform(double d,int scale, int roundingMode ){
		BigDecimal bg = new BigDecimal(d);
        double f = bg.setScale(scale, roundingMode).doubleValue();
        return f;
	}
	/**
	 * 
	 * @param d 需要转换的数
	 * @param scale 转换保留scale位小数
	 * @param roundingMode BigDecimal转换策略
	 * @return
	 */
	public static String doubleTransform2Str(double d,int scale, int roundingMode ){
		BigDecimal bg = new BigDecimal(d);
        double f = bg.setScale(scale, roundingMode).doubleValue();
        return String.format("%." + scale + "f", f);
	}
	
}
