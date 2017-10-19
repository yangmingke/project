package com.ucpaas.commonservice.util.date;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static final String FORMAT_DAFAULT = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_DATE = "yyyy-MM-dd";
	public static final String FORMAT_TIME = "HH:mm:ss";

	public static final String FORMAT_yyyyMMdd = "yyyyMMdd";
	public static final String FORMAT_yyyyMMddHH = "yyyyMMddHH";
	

	/**
	 * 格式化时间 , yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return String
	 */
	public static String format(Date date) {
		return format(FORMAT_DAFAULT, date);
	}

	/**
	 * 格式化时间 , yyyy-MM-dd HH:mm:ss
	 * 
	 * @param timestamp
	 * @return String
	 */
	public static String format(Timestamp timestamp) {
		return format(FORMAT_DAFAULT, timestamp);
	}

	/**
	 * 格式化时间
	 * 
	 * @param formater
	 *            用户期望的时间格式
	 * @param date
	 * @return
	 */
	public static String format(String formater, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(formater);
		return sdf.format(date);
	}

	/**
	 * 格式化时间
	 * 
	 * @param formater
	 *            用户期望的时间格式
	 * @param timestamp
	 * @return
	 */
	public static String format(String formater, Timestamp timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat(formater);
		return sdf.format(timestamp);
	}

	/**
	 * 获取今天的起始时间 eg：2013-05-18 00:00:00.000
	 * 
	 * @return Timestamp
	 */
	public static Timestamp getTodayBeginTimestamp() {

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * 获取今天的结束时间 eg：2013-05-18 23:59:59.999
	 * 
	 * @return Timestamp
	 */
	public static Timestamp getTodayEndTimestamp() {

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.MILLISECOND, 999);

		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * 获取当前时间
	 * 
	 * @param formater
	 *            期望转换的格式
	 * @return String
	 */
	public static String getCurrentDateTime(String formater) {

		SimpleDateFormat sdf = new SimpleDateFormat(formater);
		return sdf.format(new Date());
	}

	/**
	 * 获取当前时间戳
	 * 
	 * @return Timestamp
	 */
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * 将"yyyy-MM-dd HH:mm:ss"日期字符串转成Date
	 * @param dateStr
	 * @return
	 */
	public static Date strToDate(String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DAFAULT);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 将当期日期转成yyyyMMdd的整形数字
	 * @return
	 */
	public static int getCurrentDate(){
		String dateStr = DateUtils.getCurrentDateTime(FORMAT_yyyyMMdd);
		return Integer.parseInt(dateStr);
	}
	
	
	
	public static void main(String[] args) {
		String str = "2014-08-01 09:01:01";
		Date date = DateUtils.strToDate(str);
		System.out.println(date);
		
		
		System.err.println(DateUtils.getCurrentDate());
		
		
		
		
		
		
	}

}
