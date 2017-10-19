package com.flypaas.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RestClient {
	private static final Logger logger = LoggerFactory.getLogger(RestClient.class);
	public static String createClient(){
		String result = null ;
		try {
			result = createClient();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		logger.info("client："+result);
		return result ;
	}
	
	
}
