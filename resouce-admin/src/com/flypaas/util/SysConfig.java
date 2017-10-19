package com.flypaas.util;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class SysConfig {
	private static Properties props = null;
	private static Logger log = LoggerFactory.getLogger(SysConfig.class);
	private static volatile SysConfig conf;
	private static String springPrefixActive = null ;
	private static Map<Integer,String> payTypeMap;
	public static String config_file_path;
	
	static{
		props = new Properties();
	}
	
	public static SysConfig getInstance() {
		if (conf == null) {
			synchronized (SysConfig.class) {
				if (conf == null) {
					conf = new SysConfig();
				}
			}
		}
		return conf;
	}

	public void loadConfigProps() {
		InputStream is = null;
		try {
			String configName ="config_"+springPrefixActive+".properties";
			
			is = getClass().getResourceAsStream(
					"/WEB-INF/classes/config/"+configName);
			if (is == null) {
				is = getClass().getResourceAsStream("/config/"+configName);
			}
			InputStreamReader reader = new InputStreamReader(is, "UTF-8");
			props.load(reader);
			Iterator<String> iter = props.stringPropertyNames().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				props.setProperty(key, props.getProperty(key));
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.error("load config.properties error!please check the file!", e);
		} finally {
			if (is != null) {
				try {
					is.close();
					is = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void loadPaytypeProps() {
		InputStream is = null;
		try {
			is = getClass().getResourceAsStream(
					"/WEB-INF/classes/paytype.properties");
			if (is == null) {
				is = getClass().getResourceAsStream("/paytype.properties");
			}
			InputStreamReader reader = new InputStreamReader(is, "UTF-8");
			props.load(reader);
			payTypeMap = new HashMap<Integer, String>();
	        String paytypeJSON = props.getProperty("paytype_json");
	        JSONObject json = new JSONObject(paytypeJSON);
	        JSONArray array = new JSONArray(json.get("data").toString());
	        
	        for (int i = 0; i < array.length(); i++) {
	            JSONObject item =(JSONObject) array.get(i);
	            payTypeMap.put(item.getInt("paytype_no"),
	                    item.getString("paytype_code"));
	        }
		} catch (IOException e) {
			e.printStackTrace();
			log.error("加载paytype.properties失败 请检查文件！", e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("解析paytype.properties成json数据错误 请检查文件!", e);
		} finally {
			if (is != null) {
				try {
					is.close();
					is = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getProperty(String key) {
		String tmp = props.getProperty(key);
		if (StringUtils.isNotEmpty(tmp)) {
			return tmp.trim();
		}
		return tmp;
	}

	public String getProperty(String key, String defaultValue) {
		String tmp = props.getProperty(key, defaultValue);
		if (StringUtils.isNotEmpty(tmp)) {
			return tmp.trim();
		}
		return tmp;
	}

	public int getPropertyInt(String key) {
		String tmp = props.getProperty(key);
		if (StringUtils.isNotEmpty(tmp)) {
			return Integer.parseInt(tmp.trim());
		}
		return 0;

	}

	public int getPropertyInt(String key, int defaultValue) {
		String tmp = props.getProperty(key);
		if (StringUtils.isNotEmpty(tmp)) {
			return Integer.parseInt(tmp.trim());
		}
		return defaultValue;
	}

	public long getPropertyLong(String key, long defaultValue) {
		String tmp = props.getProperty(key);
		if (StringUtils.isNotEmpty(tmp)) {
			return Integer.parseInt(tmp.trim());
		}
		return defaultValue;
	}
	public Map<Integer, String> getPayTypeMap(){
		return payTypeMap;
	}
	@Value("${spring.profiles.active}")
	public void setSpringPrefixActive(String springPrefix) {
		springPrefixActive = springPrefix;
		String path = SysConfig.class.getClassLoader().getResource("").getPath() + "config/";
		config_file_path = path + "config_" + springPrefix + ".properties";
		loadConfigProps();
		loadPaytypeProps();
	}
	public  String getSpringPrefixActive(){
		return springPrefixActive;
	}
	
}
