package com.network.monitor.tcpserver;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.network.monitor.service.node.TopologyService;
import com.network.monitor.service.route.RouteService;
import com.network.monitor.service.srinterface.SRInterfaceService;
import com.network.monitor.service.statistics.QualityStatisticsService;
import com.network.monitor.service.statistics.ThroughputStatisticsService;

@Component
public class TCPServerHandler extends IoHandlerAdapter{
	private static final Logger logger = LoggerFactory.getLogger(TCPServerHandler.class);
	
	@Autowired
	private RouteService routeService;//路由表信息Service
	
	@Autowired
	private TopologyService topologyService;//邻居节点Service
	
	@Autowired
	private QualityStatisticsService qualityStatisticsService;//链路质量Service
	
	@Autowired
	private ThroughputStatisticsService throughputStatisticsService;//SR流量Service
	
	@Autowired
	private SRInterfaceService srInterfaceService;//SR接口Servcie
	
	/**
	 * 当客户端连接进入时执行
	 */
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		//请求后直接关闭连接
		SocketSessionConfig cfg = (SocketSessionConfig) session.getConfig();   
        cfg.setKeepAlive(true);   
        cfg.setSoLinger(0);
        
        super.sessionCreated(session);
//		String ip = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
//		logger.info("客户端:"+ip+" 进入连接");
	}
	
	/**
	 * 当连接开始时触发
	 */
	@Override
	public void sessionOpened(IoSession session) throws Exception {
//		InetSocketAddress remoteAddress = (InetSocketAddress)session.getRemoteAddress();
//		String clientIp = remoteAddress.getAddress().getHostAddress();
//		logger.info("LongConnect Server opened Session ID ="+String.valueOf(session.getId()));
//		logger.info("接收来自客户端 :" + clientIp + "的连接.");
		super.sessionOpened(session);
	}
	
	/**
	 * 接收到消息后被触发
	 */
	@Override
    public void messageReceived (IoSession session, Object message) throws Exception{
		TCPPack pack = (TCPPack)message;
//		InetSocketAddress remoteAddress = (InetSocketAddress)session.getRemoteAddress();
//		String clientIp = remoteAddress.getAddress().getHostAddress();
//       logger.info("接收到客户端("+clientIp+")消息：报文序列号是" + pack.getSn()+",消息类型是"+pack.getMsgType()+",应答码是"+pack.getAsn()+",报文内容是"+pack.getJsonStr());
       
       //对接收到的报文进行业务处理（入库）
       if (9 == pack.getMsgType()) {//心跳包直接返回头部作为应答
    	   pack.setAsn((short)0);
       }else {
    	   analysis(pack);
       }
       
       session.write(pack);//发送消息
    }
	
	/**
	 * messageSent是Server响应给Clinet成功后触发的事件  
	 */
	@Override  
	public void messageSent(IoSession session, Object message) throws Exception { 
		super.messageSent(session, message);
//		logger.info("TCP服务端发送消息成功");
	}
	
	/**
	 * 当连接空闲时被触发 
	 * @throws Exception 
	 */
	@Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		super.sessionIdle(session, status);
//		logger.info("连接空闲");
    }
	
	/**
	 * 当接口中其他方法抛出异常未被捕获时触发此方法 
	 */
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		logger.info("其他方法异常,远程主机关闭连接session id="+session.getId());
		session.close(true);
	}
	
	/**
	 * 连接关闭触发的事件  
	 */
	@Override  
	public void sessionClosed(IoSession session) throws Exception {  
		CloseFuture closeFuture = session.close(true);
		closeFuture.addListener(new IoFutureListener<IoFuture>() {
            public void operationComplete(IoFuture future) {
                if (future instanceof CloseFuture) {
                    ((CloseFuture) future).setClosed();
                }
            }
        });
		logger.info("TCP连接关闭"); 
	}
	
	/**
	 * 解析客户端发来的报文
	 * @param message
	 */
	public void analysis(TCPPack pack){
//		logger.info("开始解析报文......");
		
		//解析报文体
		JSONObject bodyJson = null;
		List<Map<String, Object>> paramList = new ArrayList<Map<String,Object>>();//保存批量更新的参数数据
		Map<String, Object> params = null;//保存每条数据
		
		JSONObject datagram = JSONObject.fromObject(pack.getJsonStr());//将报文内容转换成json
		short type = pack.getMsgType();//报文类型
		
		if (1 == type) {//路由表包体信息
//			logger.info("开始解析路由表报文......");
			bodyJson = JSONObject.fromObject(datagram.get("routes"));//获取包体内容
			//清空变量 list
			if (null != paramList || !paramList.isEmpty()) {
				paramList.clear();
			}
			
			String sr_id = bodyJson.getString("sr_id").toString();//SR源节点
			JSONArray route_list = JSONArray.fromObject(bodyJson.get("items"));//临界点的链路质量信息
			//封装路由表的信息
			for (int i = 0; i < route_list.size(); i++) {
				JSONObject route = JSONObject.fromObject(route_list.get(i));
				params = new HashMap<String, Object>();
				params.put("sr_id", sr_id);//SR节点ID
				params.put("dest", route.get("dest"));//目的网络地址
				params.put("mask", route.get("mask"));//目的网络掩码
				params.put("next_hop", route.get("next_hop"));//下一跳IP地址
				params.put("iface", route.get("iface"));//出口接口名
				params.put("metric", route.get("metric"));//链路消耗值
				
				paramList.add(params);//加入List变量
			}
		}else if (3 == type) {//链路质量包体
//			logger.info("开始解析SR链路质量报文......");
			bodyJson = JSONObject.fromObject(datagram.get("links"));//获取包体内容
			
			//清空变量 list
			if (null != paramList || !paramList.isEmpty()) {
				paramList.clear();
			}
			
			String sr_id = bodyJson.getString("sr_id").toString();//SR源节点
			JSONArray dst_sr_list = JSONArray.fromObject(bodyJson.getString("items").toString());//临界点的链路质量信息
			
			//封装SR链路质量的信息
			for (int i = 0; i < dst_sr_list.size(); i++) {
				JSONObject item = JSONObject.fromObject(dst_sr_list.get(i));
				params = new HashMap<String, Object>();
				params.put("src_sr_id", sr_id);//SR源节点
				params.put("dst_ip", item.get("dst_ip"));
				params.put("ping_num", item.get("pings"));//ping包个数
				params.put("lost_num", item.get("lost"));//丢包个数
				params.put("average_delay", item.get("delay"));//平均延时
				params.put("metric", item.get("metric"));//链路成本值
				
				paramList.add(params);//加入List变量
			}
			
		}else if(5 == type){//流量统计包体
//			logger.info("开始解析SR流量统计报文......");
			bodyJson = JSONObject.fromObject(datagram.get("trans"));//获取包体内容
			
			//清空变量 list
			if (null != paramList || !paramList.isEmpty()) {
				paramList.clear();
			}
			
			String sr_id = bodyJson.getString("sr_id").toString();//SR源节点
			JSONArray dst_sr_list = JSONArray.fromObject(bodyJson.getString("items").toString());//SR流量报文信息
			
			//封装SR流量的信息
			for (int i = 0; i < dst_sr_list.size(); i++) {
				JSONObject item = JSONObject.fromObject(dst_sr_list.get(i));
				params = new HashMap<String, Object>();
				params.put("src_sr_id", sr_id);//SR源节点
				params.put("ifname", item.get("ifname"));
				params.put("in_pkts", item.get("in_pkts"));//最近5秒钟进入的包个数
				params.put("in_bytes", item.get("in_bytes"));//最近5秒进入的字节数
				params.put("out_pkts", item.get("out_pkts"));//最近5秒钟发出的包个数
				params.put("out_bytes", item.get("out_bytes"));//最近5秒钟发出的字节数
				params.put("timespan", item.get("timespan"));//最近5秒钟发出的字节数
				
				paramList.add(params);//加入List变量
			}
		}else if(7 == type){//邻居包体
//			logger.info("开始解析SR邻居报文......");
			bodyJson = JSONObject.fromObject(datagram.get("neighbors"));//获取包体内容
			
			//清空变量 list
			if (null != paramList || !paramList.isEmpty()) {
				paramList.clear();
			}
			
			String sr_id = bodyJson.getString("sr_id").toString();//SR源节点
			JSONArray dst_sr_list = JSONArray.fromObject(bodyJson.getString("items").toString());//SR流量报文信息
			
			if (dst_sr_list.size() >= 1) {
				for (int i = 0; i < dst_sr_list.size(); i++) {
					JSONObject item = JSONObject.fromObject(dst_sr_list.get(i));
					if (null == item.get("nbrid").toString() || "".equals(item.get("nbrid").toString())
							|| null == item.get("nbrip").toString() || "".equals(item.get("nbrip").toString())) {
						continue;
					}
					params = new HashMap<String, Object>();
					params.put("sr_id", sr_id);//源节点
					params.put("ifname", item.get("ifname"));//本地接口名称
					params.put("nbrid", item.get("nbrid"));//邻居SR节点ID
					params.put("nbrip", item.get("nbrip"));//邻居SR节点IP
					
					paramList.add(params);//加入List变量
				}
			}
		}else if(11 == type){//SR接口包体
//			logger.info("开始解析SR接口IP报文......");
			bodyJson = JSONObject.fromObject(datagram.get("interfaces"));//获取包体内容
			
			//清空变量 list
			if (null != paramList || !paramList.isEmpty()) {
				paramList.clear();
			}
			
			String sr_id = bodyJson.getString("sr_id").toString();//SR源节点
			JSONArray dst_sr_list = JSONArray.fromObject(bodyJson.getString("items").toString());//SR流量报文信息
			
			if (dst_sr_list.size() >= 1) {
				for (int i = 0; i < dst_sr_list.size(); i++) {
					JSONObject item = JSONObject.fromObject(dst_sr_list.get(i));
					if (null == item.get("ifip").toString() || "".equals(item.get("ifip").toString())
							|| null == item.get("ifname").toString() || "".equals(item.get("ifname").toString())) {
						continue;
					}
					params = new HashMap<String, Object>();
					params.put("sr_id", sr_id);//源节点
					params.put("ifip", item.get("ifip"));//本地接口IP
					params.put("ifname", item.get("ifname"));//本地接口名称
					
					paramList.add(params);//加入List变量
				}
			}
		}else {
			pack.setAsn((short)1);//报文错误
		}
		
		
		//将组装好的数据保存到数据库
		boolean isExist = saveMessageInfo(type,paramList);
		
		if (isExist) {
			pack.setAsn((short)0);//接收成功
		}else {
			pack.setAsn((short)1);//接收失败
		}
	}
	
	/**
	 * 数据入库
	 * @param type
	 * @param paramList
	 */
	private boolean saveMessageInfo(int type,List<Map<String, Object>> paramList){
		Map<String, Object> map = new HashMap<String, Object>();
		String opDateTmp = new SimpleDateFormat("yyyyMM").format(new Date());
		map.put("opDate", opDateTmp);//时间
		map.put("list", paramList);
		if (null != paramList && !paramList.isEmpty()) {
			if(1 == type){//路由表信息
				routeService.saveRoutes(paramList);
			}else if (3 == type) {//SR链路质量
				qualityStatisticsService.insertBatch(map);
			}else if(5 == type){//SR流量统计
				throughputStatisticsService.insertBatch(map);
			}else if(7 == type){//SR邻居节点
				topologyService.saveFromTCP(paramList);
			}else if(11 == type){//SR接口信息
				srInterfaceService.saveFromTCP(paramList);
			}else {//空操作
				
			}
			
			return true;
		}else {
			return false;
		}
	}
}
