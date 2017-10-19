package com.network.monitor.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.network.monitor.action.LoginAction;

public final class DateKit {
	private static final Logger logger = LoggerFactory.getLogger(LoginAction.class);
	private DateKit(){
	}

	public static String format(Date date, String dateStyle) {
		if(null != date){
			return new SimpleDateFormat(dateStyle).format(date);
		}
		return null;
	}

	public static Date addDay(Date date, int i) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, i);
		return cal.getTime();
	}
	public static Date parseyyyyMMdd(String date_str){
		return parse(date_str, "yyyyMMdd");
	}
	public static Date parse(String date_str, String dateStyle) {
		if(null != date_str){
			try {
				return new SimpleDateFormat(dateStyle).parse(date_str);
			} catch (ParseException e) {
				logger.error(e.getMessage(),e);
			}
		}
		return null;
	}

	public static Date addHour(Date date, int i) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, i);
		return cal.getTime();
	}

	public static Date addMinute(Date date, int i) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, i);
		return cal.getTime();
	}

	public static Date addMonth(Date date, int i) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, i);
		return cal.getTime();
	}
	
}
