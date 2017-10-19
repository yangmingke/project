package com.network.monitor.tcpserver;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.network.monitor.util.ByteUtil;

/**
 * UDP监听端代码
 * 处理客户端发来的心跳包，并返回应答包
 *
 */
public class UDPServerHandler extends IoHandlerAdapter{

	private static final Logger logger = LoggerFactory.getLogger(UDPServerHandler.class);
	
	/**
	 * 建立连接触发的事件  
	 */
	@Override  
	public void sessionCreated(IoSession session) throws Exception {  
//	   SocketAddress remoteAddress = session.getRemoteAddress();
//	   logger.info("接收数据包：" + remoteAddress);
	   super.sessionCreated(session);
	} 
	
	/**
	 * 打开连接触发的事件，它与sessionCreated的区别在于，一个连接地址（A）第一次请求Server会建立一个Session默认超时时间为1分钟，
	 * 此时若未达到超时时间这个连接地址（A）再一次向Server发送请求即是sessionOpened（连接地址（A）第一次向Server发送请求或者连接超时后向Server发送请求时会同时触发sessionCreated和sessionOpened两个事件）  
	 */
	  
	@Override  
	public void sessionOpened(IoSession session) throws Exception {
//	   SocketAddress remoteAddress = session.getRemoteAddress();  
//	   logger.info("接收数据包： " + remoteAddress);  
	   super.sessionOpened(session);
	}
	  
	/**
	 * Server接收到UDP请求触发的事件  
	 */
	@Override  
	public void messageReceived(IoSession session, Object message)  
	            throws Exception {
//	SocketAddress remoteAddress = session.getRemoteAddress(); 
//	   logger.info("接收到客户端("+remoteAddress+")的心跳信息：");  
	   if (message instanceof IoBuffer) {
		  IoBuffer buffer = (IoBuffer) message;  
		  byte [] sizeBytes = new byte[4]; 
		  buffer.mark();//标记当前位置，以便reset
	      //数据包的长度保存在第1-4字节中，
		  buffer.get(sizeBytes,0,4);//读取4字节 
	            
	      byte[] len = new byte[4]; //总长度
	      len[0] = sizeBytes[0];
	      len[1] = sizeBytes[1];
	      len[2] = sizeBytes[2];
	      len[3] = sizeBytes[3];
	      //获取数据包长度 
	      int size = ByteUtil.bytes2Int(len);
//	      System.out.println("数据包体大小size : "+size);
	            
	      HeartBeatPacket pack = new HeartBeatPacket();//定义协议包对象
          pack.setLen(size);
                
	      if (size == 12) {//数据包长度不对
	         byte[] bytes = new byte[size];
	         buffer.reset();
	         buffer.get(bytes, 0, size);
			 byte[] sn = new byte[4]; //报文序列号
	         sn[0] = bytes[4];
	         sn[1] = bytes[5];
	         sn[2] = bytes[6];
	         sn[3] = bytes[7];
//	         System.out.println( "报文序列号:" + sn[0]+","+sn[1]+","+sn[2]+","+sn[3]);
//	         System.out.println( "报文序列号:" + ByteUtil.bytes2Int(sn));
	         pack.setSn(ByteUtil.bytes2Int(sn));
	                
	         byte[] msgType = new byte[2]; //消息类型
	         msgType[0] = bytes[8];
	         msgType[1] = bytes[9];
//	         System.out.println( "消息类型:" + msgType[0]+","+msgType[1]);
//	         System.out.println( "消息类型:" + ByteUtil.byte2Short(msgType));
	         pack.setMsgType(ByteUtil.byte2Short(msgType));
	                
	         byte[] asn = new byte[2]; //应答码
	         asn[0] = bytes[10];
	         asn[1] = bytes[11];
//	         System.out.println( "应答码:" + asn[0] + "," + asn[1]);
//	         System.out.println( "应答码:" + ByteUtil.byte2Short(asn));
	         pack.setAsn(ByteUtil.byte2Short(asn));
	                
	         if (9 == pack.getMsgType()) {
				pack.setAsn((short)0);
			}else {
				pack.setAsn((short)1);
			}
	            	
		}else {
			pack.setAsn((short)1);
		}
	            
	    //返回信息给Clinet端 
	    int sn = pack.getSn();
	    short msgType = (short)(pack.getMsgType()+(short)1);
	    short asn = pack.getAsn(); //应答码，0：成功，1：失败
	    byte[] snArr = ByteUtil.int2Bytes(sn);
	    byte[] msgArr = ByteUtil.short2Byte(msgType);
	    byte[] asnArr = ByteUtil.short2Byte(asn);
	    //总长度
	    int length =  snArr.length + msgArr.length + asnArr.length+4;
	    byte[] lenArr = ByteUtil.int2Bytes(length);
	    		
	    		
	    //打包字节数组
	    byte[] result = ByteUtil.packByteArr(lenArr,snArr,msgArr,asnArr);
//	    for( int i = 0; i < result.length; i++ ){
//	    	System.out.print(result[i]+",");
//	    }
//	    System.out.println( "总长度:" +result.length );
	    IoBuffer sendBuffer = IoBuffer.allocate(12).setAutoExpand(true);;//返回信息给Clinet端 
	    sendBuffer.put(result);
	    sendBuffer.flip();
	    session.write(sendBuffer);
	  }
	 }  
	 
	/**
	 * messageSent是Server响应给Clinet成功后触发的事件  
	 */
	@Override  
	public void messageSent(IoSession session, Object message) throws Exception { 
		super.messageSent(session, message);
//		logger.info("UDP服务端发送消息成功");
	}  
	
	/**
	 * 抛出异常触发的事件  
	 */
	@Override  
	public void exceptionCaught(IoSession session, Throwable cause)  
	    throws Exception {  
	   cause.printStackTrace();  
//	   session.close(true);  
	}  
	
	/**
	 * 连接关闭触发的事件  
	 */
	@Override  
	public void sessionClosed(IoSession session) throws Exception { 
//		session.close(true);
	   logger.info("UDP关闭");  
	}
}
