package com.flypaas.model.vo;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import org.springframework.core.env.Environment;
import org.springframework.web.context.support.StandardServletEnvironment;

import freemarker.log.Logger;


/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年1月20日 上午9:41:08
* 类说明
*/
public class RouterInfo {
	private   String url;
	
	private   String add;
	
	private   String del;
	
	private   String upd;
	
	private   int post;
	
	private   String ip = getLocalHost();
	
	
	public static String getLocalHost(){
		InetAddress addr;
		String ip = null;
		try {
			addr = InetAddress.getLocalHost();
			ip = addr.getHostAddress().toString();//获得本机IP
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ip;
	}
	
	
	public RouterInfo(){
		try {
			Properties pName = new Properties();
			Environment environment = new StandardServletEnvironment();
			String spring_profiles_active = environment.getProperty("flypaas_env");
	        InputStream iStr = this.getClass().getResourceAsStream("/config_"+spring_profiles_active+".properties");
	        pName.load(iStr);
	        this.setUrl(pName.getProperty("router_url"));
	        
	        this.setAdd(pName.getProperty("router_add"));
	        this.setDel(pName.getProperty("router_del"));
	        this.setUpd(pName.getProperty("router_upd"));
	        System.out.println(pName.getProperty("result_port"));
	        this.setPost(Integer.valueOf(pName.getProperty("result_port")));
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	


	


	public int getPost() {
		return post;
	}


	public void setPost(int post) {
		this.post = post;
	}


	public String getUrl() {
		return url;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}
	

	public String getAdd() {
		return add;
	}


	public void setAdd(String add) {
		this.add = add;
	}


	public String getDel() {
		return del;
	}


	public void setDel(String del) {
		this.del = del;
	}


	public String getUpd() {
		return upd;
	}


	public void setUpd(String upd) {
		this.upd = upd;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	
	
}
