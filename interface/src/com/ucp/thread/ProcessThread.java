package com.ucp.thread;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
public class ProcessThread implements Callable<Integer> {
	private static final Logger logger = Logger.getLogger(ProcessThread.class);
	private String sql;
	private String keyPrefix;
	private String column1,column2;
	private int curPage;
	public ProcessThread(String sql,String keyPrefix,String column1,String column2,int curPage){
		this.sql=sql;
		this.keyPrefix=keyPrefix;
		this.column1=column1;
		this.column2=column2;
		this.curPage=curPage;
	}
	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
