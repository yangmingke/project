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
	
	/**
	 * 秒数转换成  XX天XX时XX分XX秒
	 * @param time秒
	 * @return
	 */
	public static String secToDateTime(int time) {
		String timeStr = null;
		int day = 0;
		int hour = 0;
		int minute = 0;
		int second = 0;
		if (time <= 0)
			return "0天00时00分00秒";
		else {
			minute = time / 60;
			if (minute < 60) {
				second = time % 60;
				timeStr = "0天00时" + unitFormat(minute) + "分" + unitFormat(second) + "秒";
			} else {
				hour = minute / 60;
				if(hour < 24){
					minute = minute % 60;
					second = time - hour * 3600 - minute * 60;
					timeStr = "0天" + unitFormat(hour) + "时" + unitFormat(minute) + "分" + unitFormat(second)+ "秒";
				}else{
					day = hour / 24;
					hour = hour % 24; 
					minute = minute % 60;
					second = time - day*60*60*24 - hour * 3600 - minute * 60;
					timeStr = day + "天" + unitFormat(hour) + "时" + unitFormat(minute) + "分" + unitFormat(second)+ "秒";
				}
			}
		}
		return timeStr;
	}

	public static String unitFormat(int i) {
		String retStr = null;
		if (i >= 0 && i < 10)
			retStr = "0" + Integer.toString(i);
		else
			retStr = "" + i;
		return retStr;
	}

}
