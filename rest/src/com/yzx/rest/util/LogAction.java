package com.yzx.rest.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.yzx.rest.models.BaseInfo;
import com.yzx.rest.models.ErrorLog;
import com.yzx.rest.models.Ulog;


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
					if (ulog.getLogEvent().equals(Consts.evt_conc)) {
						ulogServer+="?event=conc&interface=1";
						SimpleDateFormat sdfFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						JSONObject jsonObject=new JSONObject();
						jsonObject.put("appId",ulog.getAppId());
						jsonObject.put("logTime", sdfFormat.format(new Date()));
						jsonObject.put("concurrency", 1);
						jsonObject.put("ifName",ulog.getIfName());
						HttpHelper.httpConnectionPostJson(ulogServer, jsonObject.toString());
					}else if (ulog.getLogEvent().equals(Consts.evt_errorCode)){
						ulogServer+="?event=errorCode";
						ErrorLog errorLog=new ErrorLog();
						errorLog.setIfType("2");
						errorLog.setIfName(ulog.getIfName());
						errorLog.setErrorCode(ulog.getErrorCode());
						errorLog.setAppId(ulog.getAppId());
						errorLog.setClientNumber(ulog.getClientNumber());
						SimpleDateFormat sdfFormat=new SimpleDateFormat("yyyyMMddHHmmss");
						errorLog.setLogDate(sdfFormat.format(new Date()));
						String json=JsonUtil.toJsonString(errorLog);
						HttpHelper.httpConnectionPostJson(ulogServer,"["+json+"]");
					}
				} catch (Exception e) {
					// TODO: handle exception
					logger.error("队列入缓存失败",e);
				}
			}
		}
    }
}
