package com.flypaas.annotation;    
   
import org.aspectj.lang.JoinPoint;    
import org.aspectj.lang.annotation.*;    
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;    
import org.springframework.web.context.request.ServletRequestAttributes;

import com.flypaas.model.TbRsOprateLog;
import com.flypaas.model.TbRsUserInfo;
import com.flypaas.service.LogService;

import javax.servlet.http.HttpServletRequest;    
import javax.servlet.http.HttpSession;    
import java.lang.reflect.Method;
import java.util.Date;    
    
/**
 * 切点类  暂且仅后置通知
 * 1.后置通知
 * 2.前置通知
 * 3.异常返回通知
 * @author win10
 *
 */
@Aspect    
@Component
public  class SystemLogAspect {  
	public static Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);
	
    //注入Service用于把日志保存数据库    
    @Autowired    
    private LogService logServiceImpl;    
   
    //Controller层切点    
    @Pointcut("@annotation(com.flypaas.annotation.Log)")    
     public  void LogAspect() { 
    	
    }    
    /**  
     * 后置通知 用于拦截service层记录日志  
     *  
     * @param joinPoint  
     * @param e  
     * @throws Exception 
     */    
     @Before("LogAspect()")
     public  void before(JoinPoint joinPoint) throws Exception {    
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
        HttpSession session = request.getSession();    
        //读取session中的用户    
        TbRsUserInfo user = (TbRsUserInfo) session.getAttribute("adminSession");    
        //获取请求ip    
        //String ip = request.getRemoteHost();    
        //获取用户请求方法的参数并序列化为JSON格式字符串    
        String params = "";    
         if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {    
             for ( int i = 0; i < joinPoint.getArgs().length; i++) {    
                params += joinPoint.getArgs()[i] + ";";    
            }   
         } 
         /*========控制台输出=========*/    
         logger.info("=====前置通知通知开始=====");    
         logger.info("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));    
         logger.info("方法描述:" + getServiceMthodDescription(joinPoint));    
         logger.info("请求人:" + user.getUsername());    
         logger.info("请求IP:" + getRemoteHost(request));    
         logger.info("请求参数:" + params);    
         /*==========数据库日志=========*/    
         TbRsOprateLog log = new TbRsOprateLog();    
         log.setSid(user.getSid());
         log.setIp(getRemoteHost(request));
         log.setCreateDate(new Date());
         log.setPageUrl(getServiceMthodUrl(joinPoint));
         log.setOpDesc(getServiceMthodDescription(joinPoint));
         log.setOpType(getServiceMthodType(joinPoint));
         log.setPageUrl(request.getRequestURL().toString());
         //保存数据库    
         logServiceImpl.insert(log);    
         logger.info(("=====前置通知结束====="));    
    }    
    
    
    /**  
     * 获取注解中对方法的描述信息 用于service层注解  
     *  
     * @param joinPoint 切点  
     * @return 方法描述  
     * @throws Exception  
     */    
     @SuppressWarnings("rawtypes")
     public  static String getServiceMthodDescription(JoinPoint joinPoint)     throws Exception {    
        String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();    
		Class targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();    
        String description = "";    
         for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class[] clazzs = method.getParameterTypes();    
                 if (clazzs.length == arguments.length) {    
                    description = method.getAnnotation(Log.class).name();    
                     break;    
                }    
            }    
        }    
     return description;    
    }  
    @SuppressWarnings("rawtypes")
    public  static String getServiceMthodType(JoinPoint joinPoint)     throws Exception {    
        String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();    
		Class targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();    
        String type = "";    
         for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class[] clazzs = method.getParameterTypes();    
                 if (clazzs.length == arguments.length) {    
                	 type = method.getAnnotation(Log.class).type();    
                     break;    
                }    
            }    
        }    
     return type;    
    }
    @SuppressWarnings("rawtypes")
    public  static String getServiceMthodUrl(JoinPoint joinPoint)     throws Exception {    
        String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();    
		Class targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();    
        String url = "";    
         for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class[] clazzs = method.getParameterTypes();    
                 if (clazzs.length == arguments.length) {    
                	 url = method.getAnnotation(Log.class).url();    
                     break;    
                }    
            }    
        }    
    return url;    
    }  
    
    public String getRemoteHost(javax.servlet.http.HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }
}    
