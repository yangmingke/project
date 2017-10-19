package com.flypaas.timer;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TmTask extends TimerTask {
	Logger logger = LoggerFactory.getLogger(TmTask.class);
	private Timer timer = null ;
	private HttpSession session;
	private String mobile = null ;
	private int count;
	public TmTask(Timer t,HttpSession session,String mobile,int count){
		this.timer = t ;
		this.session = session;
		this.mobile = mobile ;
		this.count = count;
	}
	@Override
	public synchronized void run() {
		if(count>0){
			int cc = --count;
			//System.out.println(session.getId()+":"+mobile+":"+cc);
			session.setAttribute(mobile, cc);
		}else{
			logger.info("验证码倒计时结束");
			session.setAttribute(mobile, null);
			cancel();
			timer.cancel();
		}
	}

	@Override
	public boolean cancel() {
		return super.cancel();
	}
}
