/*
 *  Copyright (c) 2013 The CCP project authors. All Rights Reserved.
 *
 *  Use of this source code is governed by a Beijing Speedtong Information Technology Co.,Ltd license
 *  that can be found in the LICENSE file in the root of the web site.
 *
 *   http://www.cloopen.com
 *
 *  An additional intellectual property rights grant can be found
 *  in the file PATENTS.  All contributing project authors may
 *  be found in the AUTHORS file in the root of the source tree.
 */
package com.flypaas.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {
	
    /**
     * 变量：日期格式化类型 - 格式:yyyy/MM/dd
     * 
     * @since 1.0
     */
    public static final int DEFAULT = 0;

    /**
     * 变量：日期格式化类型 - 格式:yyyy/MM
     * 
     * @since 1.0
     */
    public static final int YM = 1;

    /**
     * 变量：日期格式化类型 - 格式:yyyy-MM-dd
     * 
     * @since 1.0
     */
    public static final int YMR_SLASH = 11;

    /**
     * 变量：日期格式化类型 - 格式:yyyyMMdd
     * 
     * @since 1.0
     */
    public static final int NO_SLASH = 2;

    /**
     * 变量：日期格式化类型 - 格式:yyyyMM
     * 
     * @since 1.0
     */
    public static final int YM_NO_SLASH = 3;

    /**
     * 变量：日期格式化类型 - 格式:yyyy/MM/dd HH:mm:ss
     * 
     * @since 1.0
     */
    public static final int DATE_TIME = 4;

    /**
     * 变量：日期格式化类型 - 格式:yyyyMMddHHmmss
     * 
     * @since 1.0
     */
    public static final int DATE_TIME_NO_SLASH = 5;

    /**
     * 变量：日期格式化类型 - 格式:yyyy/MM/dd HH:mm
     * 
     * @since 1.0
     */
    public static final int DATE_HM = 6;

    /**
     * 变量：日期格式化类型 - 格式:HH:mm:ss
     * 
     * @since 1.0
     */
    public static final int TIME = 7;

    /**
     * 变量：日期格式化类型 - 格式:HH:mm
     * 
     * @since 1.0
     */
    public static final int HM = 8;
    
    /**
     * 变量：日期格式化类型 - 格式:HHmmss
     * 
     * @since 1.0
     */
    public static final int LONG_TIME = 9;
    /**
     * 变量：日期格式化类型 - 格式:HHmm
     * 
     * @since 1.0
     */
    
    public static final int SHORT_TIME = 10;

    /**
     * 变量：日期格式化类型 - 格式:yyyy-MM-dd HH:mm:ss
     * 
     * @since 1.0
     */
    public static final int DATE_TIME_LINE = 12;
    
    /**
     * 变量：日期格式化类型 - 格式:yyyy
     * 
     * @since 1.0
     */
    
    public static Date strToDateLong(String strDate) {
    	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	   ParsePosition pos = new ParsePosition(0);
    	   Date strtodate = formatter.parse(strDate, pos);
    	   return strtodate;
    }
    
    public static final int DATE_TIME_YEAR = 13;
    
    public static String dateToStr(Date date,String pattern) {
       if (date == null)
    	 return null;
       SimpleDateFormat formatter = new SimpleDateFormat(pattern);
       return formatter.format(date);
    } 

    public static String dateToStr(Date date) {
        return dateToStr(date, "yyyy/MM/dd");
    }
    public static long getTime(String time) throws ParseException{
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    	long times=sdf.parse(time).getTime();
    	return times;
    }
    public static String dateToStr(Date date, int type) {
        switch (type) {
        case DEFAULT:
            return dateToStr(date);
        case YM:
            return dateToStr(date, "yyyy/MM");
        case NO_SLASH:
            return dateToStr(date, "yyyyMMdd");
        case YMR_SLASH:
        	return dateToStr(date, "yyyy-MM-dd");
        case YM_NO_SLASH:
            return dateToStr(date, "yyyyMM");
        case DATE_TIME:
            return dateToStr(date, "yyyy/MM/dd HH:mm:ss");
        case DATE_TIME_NO_SLASH:
            return dateToStr(date, "yyyyMMddHHmmss");
        case DATE_HM:
            return dateToStr(date, "yyyy/MM/dd HH:mm");
        case TIME:
            return dateToStr(date, "HH:mm:ss");
        case HM:
            return dateToStr(date, "HH:mm");
        case LONG_TIME:
            return dateToStr(date, "HHmmss");
        case SHORT_TIME:
            return dateToStr(date, "HHmm");
        case DATE_TIME_LINE:
            return dateToStr(date, "yyyy-MM-dd HH:mm:ss");
        case DATE_TIME_YEAR:
        	return dateToStr(date, "yyyy");
        default:
            throw new IllegalArgumentException("Type undefined : " + type);
        }
    }
    public static Date strToDate(String date,String pattern){
        if (date == null)
        	return null;
        SimpleDateFormat ft = new SimpleDateFormat(pattern);
    	try {
			return ft.parse(date);
		} catch (ParseException e) {
			return null ;
		}
    } 

    public static Date strToDate(String date){
         return strToDate(date, "yyyy/MM/dd");
    }
    public static Date strToDate(String date, int type){
        switch (type) {
        case DEFAULT:
            return strToDate(date);
        case YM:
            return strToDate(date, "yyyy/MM");
        case NO_SLASH:
            return strToDate(date, "yyyyMMdd");
        case YMR_SLASH:
        	return strToDate(date, "yyyy-MM-dd");
        case YM_NO_SLASH:
            return strToDate(date, "yyyyMM");
        case DATE_TIME:
            return strToDate(date, "yyyy/MM/dd HH:mm:ss");
        case DATE_TIME_NO_SLASH:
            return strToDate(date, "yyyyMMddHHmmss");
        case DATE_HM:
            return strToDate(date, "yyyy/MM/dd HH:mm");
        case TIME:
            return strToDate(date, "HH:mm:ss");
        case HM:
            return strToDate(date, "HH:mm");
        case LONG_TIME:
            return strToDate(date, "HHmmss");
        case SHORT_TIME:
            return strToDate(date, "HHmm");
        case DATE_TIME_LINE:
            return strToDate(date, "yyyy-MM-dd HH:mm:ss");
        case DATE_TIME_YEAR:
        	return strToDate(date, "yyyy");
        default:
            throw new IllegalArgumentException("Type undefined : " + type);
        }
    }
    public static Date getCurrentDate() throws ParseException{
    	return new Date();
    	
    }
    @SuppressWarnings("static-access")
	public static String getYstdDate(){
    	Calendar cl = Calendar.getInstance();
    	cl.add(cl.DATE, -1);
    	return dateToStr(cl.getTime(),YMR_SLASH);
    }
    @SuppressWarnings("static-access")
	public static String getLastYear(){
    	Calendar cl = Calendar.getInstance();
    	cl.add(cl.YEAR, -1);
    	return dateToStr(cl.getTime(),DATE_TIME_YEAR);
    }
    public static long compare(Date date1,Date date2,long interval){
    	return date2.getTime()+interval-date1.getTime();
    }
    @SuppressWarnings("static-access")
   	public static String add(String date,int days){
	   	Calendar cl = Calendar.getInstance();
	   	cl.setTime(strToDate(date,YMR_SLASH));
	   	cl.add(cl.DAY_OF_YEAR, days);
	   	return dateToStr(cl.getTime(),YMR_SLASH);
   }
}
