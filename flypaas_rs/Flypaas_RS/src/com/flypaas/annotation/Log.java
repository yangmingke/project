package com.flypaas.annotation;

import java.lang.annotation.*;

/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2016年12月29日 下午3:21:12
* 类说明
*/
@Target({ElementType.PARAMETER, ElementType.METHOD})    
@Retention(RetentionPolicy.RUNTIME)    
@Documented
public @interface Log {
	String name()  default "";
	String type()  default "";
	String url() default "";
}
