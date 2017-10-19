package com.ucp.util;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.ucp.bean.BaseInfo;
import com.ucp.bean.Ulog;


public class LogAction extends Thread{
	private static Logger logger = Logger.getLogger(LogAction.class);
	private static volatile LogAction instance;
	private final ConcurrentLinkedQueue<BaseInfo> memInfosList = new ConcurrentLinkedQueue<BaseInfo>();
	private ThreadPoolExecutor pool;
    private LogAction(){
    	pool = new ThreadPoolExecutor(12, 20, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(512),
                new ThreadPoolExecutor.CallerRunsPolicy());
    	pool.execute(new MemActionHandler());
    }
    public static LogAction getInstance(){
    	if (instance == null)
        {
            synchronized (LogAction.class)
            {
                if (instance == null)
                {
                    instance = new LogAction();
                }
            }
        }
        return instance;
    }
    public void addQuene(BaseInfo info){
    	memInfosList.add(info);
    }
    public ConcurrentLinkedQueue<BaseInfo> getForceLists(){
    	return memInfosList;
    }
    public int getActiveCount(){
    	return pool.getActiveCount();
    }
    public int getPoolSize()
    {
        return pool.getPoolSize();
    }

    // 返回核心线程数
    public int getCorePoolSize()
    {
        return pool.getCorePoolSize();
    }

    public int getLargestPoolSize()
    {
        return pool.getLargestPoolSize();
    }

    public long getCompletedTaskCount()
    {
        return pool.getCompletedTaskCount();
    }

    public long getTaskCount()
    {
        return pool.getTaskCount();
    }
    /**
     * 
     * @author (段元俊)(duanyj)
     */
    private class MemActionHandler implements Runnable{
        private final int WAITTIME = 1000;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true){
				try {
					BaseInfo info=memInfosList.poll();
					if (info==null) {
						try {
							Thread.sleep(WAITTIME);
						} catch (Exception e) {
							// TODO: handle exception
						}
						continue;
					}
					Ulog ulog=(Ulog)info;
					String ulogServer=SysConfig.getInstance().getProperty("ulog_server");
					if (ulog.getLogEvent().equals("smsp")) {
						String us=ulogServer+"/errcode?type=0";
						JSONObject jsonObject=new JSONObject();
						jsonObject.put("sid", ulog.getSid());
						jsonObject.put("appId",ulog.getAppId());
						jsonObject.put("smsId",ulog.getSmsId());
						jsonObject.put("createTime", new Date().getTime());
						jsonObject.put("templateId", ulog.getTemplateId());
						jsonObject.put("errCode",ulog.getErrorCode());
						us+="&data="+URLEncoder.encode(jsonObject.toString());
						logger.info("sms requrl["+us+"]");
						HttpHelper.httpConnectionGet(us);
					}else if (ulog.getLogEvent().equals("voice")) {
						String us=ulogServer+"/errcode?type=2";
						JSONObject jsonObject=new JSONObject();
						jsonObject.put("sid", ulog.getSid());
						jsonObject.put("appId",ulog.getAppId());
						jsonObject.put("callId",ulog.getCallId());
						jsonObject.put("createTime", new Date().getTime());
						jsonObject.put("callee", ulog.getTo()==null?"":ulog.getTo());
						jsonObject.put("errCode",ulog.getErrorCode()==null?"":ulog.getErrorCode());
						jsonObject.put("display",ulog.getDisplay()==null?"":ulog.getDisplay());
						jsonObject.put("displayNum",ulog.getDisplayNum()==null?"":ulog.getDisplayNum());
						us+="&data="+URLEncoder.encode(jsonObject.toString());
						logger.info("voice requrl["+us+"]");
						HttpHelper.httpConnectionGet(us);
					}else if (ulog.getLogEvent().equals("notify")) {
						String us=ulogServer+"/errcode?type=1";
						JSONObject jsonObject=new JSONObject();
						jsonObject.put("sid", ulog.getSid());
						jsonObject.put("appId",ulog.getAppId());
						jsonObject.put("callId",ulog.getCallId());
						jsonObject.put("createTime", new Date().getTime());
						jsonObject.put("callee", ulog.getTo()==null?"":ulog.getTo());
						jsonObject.put("errCode",ulog.getErrorCode()==null?"":ulog.getErrorCode());
						jsonObject.put("display",ulog.getDisplay()==null?"":ulog.getDisplay());
						jsonObject.put("displayNum",ulog.getDisplayNum()==null?"":ulog.getDisplayNum());
						us+="&data="+URLEncoder.encode(jsonObject.toString());
						logger.info("notify requrl["+us+"]");
						HttpHelper.httpConnectionGet(us);
					}
				} catch (Exception e) {
					// TODO: handle exception
					logger.error("队列入缓存失败",e);
				}
			}
		}
    }
}
