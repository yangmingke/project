package com.yzx.rest.service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.log4j.Logger;

import com.yzx.rest.util.Consts;
import com.yzx.rest.util.SqlCode;
public class ConKeyService {
	private static final Logger logger = Logger.getLogger(ConKeyService.class);
	private static ConKeyService conKeyService=new ConKeyService();
	public static ConKeyService getInstance(){
		return conKeyService;
	}
	private long curNum;
	private int cacheSize;
	public int getCacheSize() {
		return cacheSize;
	}
	public void setCacheSize(int cacheSize) {
		this.cacheSize = cacheSize;
	}
	public synchronized long getCurNum() {
		return curNum;
	}
	public synchronized void setCurNum(long curNum) {
		this.curNum = curNum;
	}
	private Queue<Map<String, String>> queue=new LinkedList<Map<String,String>>();
	public synchronized Map<String, String> getNextNbr(){
		Map<String, String> nextMap=null;
		if ((nextMap=queue.poll())!=null&&nextMap.size()>0) {
			setCurNum(Long.parseLong(nextMap.get("curNum")));
		}else {
			//nextMap=getNbrMap();
			setNumbers();
			nextMap=queue.poll();
			setCurNum(Long.parseLong(nextMap.get("curNum")));
		}
		String curNum=nextMap.get("curNum");
		//RedisService.getInstance().set(Consts.KEY_CURNUM,curNum);
		List<Object[]> px = new ArrayList<Object[]>();
		px.add(new Object[]{"number",curNum});
		DBCommService.getInstance().update(SqlCode.UPDATE_NUM,px);
		return nextMap;
	}
	/*private synchronized Map<String, String> getNbrMap(){
		String voipAccount="";
		long start=0;
		if (getCurNum()==0) {
			try {
				String curnum=RedisService.getInstance().get(Consts.KEY_CURNUM);
				if (curnum!=null) {
					start=Long.parseLong(curnum)+1;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else {
			start=getCurNum()+1;
		}
		String temp=String.valueOf(start);
		int length=temp.length();
		if (length<Consts.NBR_LEN) {
			for (int j = 0; j < Consts.NBR_LEN-length; j++) {
				temp="0"+temp;
			}
		}
		String nvoipAccount=voipAccount+temp;
		Map<String, String> map=new HashMap<String, String>();
		map.put("curNum", String.valueOf((start)));
		map.put("nbr", nvoipAccount);
		return map;
	}*/
	public synchronized void setNumbers(){
		long start=1;
		int limit=cacheSize;
		String voipAccount="";//Consts.VOIP_START
		if (queue.size()<=0) {
			if (getCurNum()==0) {
				try {
					long curNum=DBCommService.getInstance().queryForLong(SqlCode.NEXT_VALUE_NUM,null,Consts.CON_MASTER);//RedisService.getInstance().get(Consts.KEY_CURNUM);
					if (curNum!=0) {
						start=curNum+1;
					}
					/*if (curNum!=null) {
						start=Long.parseLong(curNum)+1;
					}*/
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				setCurNum(start-1);
			}else {
				start=getCurNum()+1;
			}
		}else {
			Map<String, String> queueMap=queue.peek();
			start=Long.parseLong(queueMap.get("curNum"))+queue.size();
			limit=cacheSize-queue.size();
		}
		if (limit>0) {
			for (int i = 0; i < limit; i++) {
				String temp=String.valueOf(start+i);
				int length=temp.length();
				if (length<Consts.NBR_LEN) {
					for (int j = 0; j < Consts.NBR_LEN-length; j++) {
						temp="0"+temp;
					}
				}
				String nvoipAccount=voipAccount+temp;
				Map<String, String> map=new HashMap<String, String>();
				map.put("curNum", String.valueOf((start+i)));
				map.put("nbr", nvoipAccount);
				queue.offer(map);
			}
			logger.info("从"+start+"到"+(start+limit)+"共计生成号码"+limit+"个");
		}
	}
	public boolean writeFile(String fileName,String content){
		FileWriter fw = null;
		try {
//			String url=this.getClass().getClassLoader().getResource("").getPath();
			fw = new FileWriter(fileName.replace(" ", ""));
			fw.append(content);
			fw.flush();
			fw.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("写记录文件异常," + e.getMessage());
			return false;
		}
	}
	public long readFile(String fileName){
		BufferedReader rec_reader = null;
		FileReader rec_fr = null;
		long nowVoip=0;
		try {
			rec_fr = new FileReader(fileName.replace(" ", ""));
			rec_reader = new BufferedReader(rec_fr);
			nowVoip=Long.parseLong(rec_reader.readLine())+1;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}finally{
			try {
				if (rec_fr!=null) {
					rec_fr.close();
				}
				if (rec_reader!=null) {
					rec_reader.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return nowVoip;
	}
}
