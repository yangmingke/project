package com.network.monitor.util;

import java.text.ParseException;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期、时间工具类
 * 
 * @author xiejiaan
 */
public class UcpaasDateUtils {
	private static final Logger logger = LoggerFactory.getLogger(UcpaasDateUtils.class);

	/**
	 * 时间格式转换
	 * 
	 * @param str
	 * @param format
	 * @return
	 */
	public static DateTime parseDate(String str, String format) {
		DateTime dateTime = null;
		try {
			dateTime = new DateTime(DateUtils.parseDateStrictly(str, format));
		} catch (ParseException e) {
			logger.error("时间格式转换失败：str=" + str + ", format=" + format, e);
		}
		return dateTime;
	}

	/**
	 * 增加分钟
	 * 
	 * @param str
	 * @param format
	 *            时间格式
	 * @param plus
	 *            增量
	 * @return
	 */
	public static String plusMinutes(String str, String format, int plus) {
		return parseDate(str, format).plusMinutes(plus).toString(format);
	}

	/**
	 * 增加小时
	 * 
	 * @param str
	 * @param format
	 *            时间格式
	 * @param plus
	 *            增量
	 * @return
	 */
	public static String plusHours(String str, String format, int plus) {
		return parseDate(str, format).plusHours(plus).toString(format);
	}

	/**
	 * 增加天
	 * 
	 * @param str
	 * @param format
	 *            时间格式
	 * @param plus
	 *            增量
	 * @return
	 */
	public static String plusDays(String str, String format, int plus) {
		return parseDate(str, format).plusDays(plus).toString(format);
	}

	/**
	 * 增加周
	 * 
	 * @param str
	 * @param format
	 *            时间格式
	 * @param plus
	 *            增量
	 * @return
	 */
	public static String plusWeeks(String str, String format, int plus) {
		return parseDate(str, format).plusWeeks(plus).toString(format);
	}

	/**
	 * 增加月
	 * 
	 * @param str
	 * @param format
	 *            时间格式
	 * @param plus
	 *            增量
	 * @return
	 */
	public static String plusMonths(String str, String format, int plus) {
		return parseDate(str, format).plusMonths(plus).toString(format);
	}

	/**
	 * 增加年
	 * 
	 * @param str
	 * @param format
	 *            时间格式
	 * @param plus
	 *            增量
	 * @return
	 */
	public static String plusYears(String str, String format, int plus) {
		return parseDate(str, format).plusYears(plus).toString(format);
	}

	public static void main(String[] args) {
		System.out.println(UcpaasDateUtils.plusDays("20140901", "yyyyMMdd", 2));
		System.out.println(UcpaasDateUtils.plusWeeks("20140901", "yyyyMMdd", 2));
		System.out.println(UcpaasDateUtils.plusMonths("201409", "yyyyMM", 2));
	}

}
