package com.flypaas.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2016年12月22日 下午8:13:23
* 类说明
*/
public class DateConvert implements Converter<String, Date> {    
	@Override    
	public Date convert(String source) {    
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
	    dateFormat.setLenient(false);    
	    try {    
	        return dateFormat.parse(source);    
	    } catch (ParseException e) {    
	        e.printStackTrace();    
	    }           
	    return null;    
	}
}