package com.ucp.thread;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.ucp.util.SysConfig;
public class MemInitThread extends TimerTask {
	private static final Logger logger = Logger.getLogger(MemInitThread.class);
	private int type;
	public MemInitThread(int type){
		this.type=type;
	}
	private static final String savePath=SysConfig.getInstance().getProperty("rec_file_path");
	private static boolean start=false;
	@Override
	public void run() {
		// TODO Auto-generated method stub
	}
	
}
