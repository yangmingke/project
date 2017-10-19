/**
 * @autor (段元俊)(duanyj)
 * @project 
 * 
 */
package com.ucp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author (段元俊)(duanyj)
 */
public class DateUtil {
	private static final Logger log = Logger.getLogger(DateUtil.class);
	public static String getTimeStr(String dateFormat){
		SimpleDateFormat sdFormat = new SimpleDateFormat(dateFormat);
		return sdFormat.format(new Date());
	}
	public static boolean isRunning(Date startDate) throws ParseException {
		try {
			SimpleDateFormat sdFormat = new SimpleDateFormat(
					"yyyy-mm-dd HH:mm:ss");
			Date date2 = sdFormat.parse(sdFormat.format(new Date()));
			Calendar calendar = Calendar.getInstance();
			int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
			int hour = calendar.get(calendar.HOUR_OF_DAY);
			int mm = calendar.get(calendar.MINUTE);
			int second = calendar.get(calendar.SECOND);
			if (dayOfWeek == 1 && hour == 10 && mm == 21 && second == 0) {
				return true;
			}
			return false;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ParseException("get monday eight time error" + e, 0);
		}
	}
	public static boolean isRunning(long delay, String startDate,long PERIOD_SEC)throws ParseException {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			long start = sdFormat.parse(startDate).getTime();
			long now = sdFormat.parse(sdFormat.format(new Date())).getTime();
			if (start +((now-start)/PERIOD_SEC)*PERIOD_SEC+ delay*60*1000 <= now) {
				return false;
			} else {
				return true;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			log.info("数字格式错误"+e);
			e.printStackTrace();
			throw new NumberFormatException("numberformat exception" + e);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.info("日期格式转换错误"+e);
			e.printStackTrace();
			throw new ParseException("Parse exception", 1);
		}
	}
	public static String getTime(String time){
		if (StringUtils.isEmpty(time)) {
			return "";
		}
		long ms=Long.parseLong(time)*1000;
		Date dat=new Date(ms);  
        GregorianCalendar gc = new GregorianCalendar();   
        gc.setTime(dat);  
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
        String sb=format.format(gc.getTime());
        return sb;
	}
}
