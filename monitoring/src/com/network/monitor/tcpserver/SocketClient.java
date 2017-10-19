package com.network.monitor.tcpserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.CharBuffer;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.lang3.SystemUtils;

import com.network.monitor.util.ByteUtil;

/*{  user:jiangwh }*/

public class SocketClient {

	public static final Object locked = new Object();
	public static final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1024 * 100);

	class SendThread extends Thread{
		private Socket socket;
		public SendThread(Socket socket) {
			this.socket = socket;
		}
		@Override
		public void run() {
			try {
			OutputStream os = socket.getOutputStream();
			InputStream in = socket.getInputStream();
			while(true){				
					System.out.println("开始向服务器发送消息");
//					String send = "{'links':{'sr_id':'172.19.1.1','item':[{'dst_sr_id':'10.10.1.1','pings':100,'lost':5,'delay':20},{'dst_sr_id':'10.10.1.5','pings':100,'lost':1,'delay':30}]}}"; 
					String send = "{'routes':{'sr_id':'172.16.2.1','items':[{'dest':'10.10.1.1','mask':'255.255.0.0','next_hop':'10.10.1.14','iface':'eth0','metric':0},{'dest':'10.10.6.2','mask':'255.255.0.0','next_hop':'10.10.1.15','iface':'eth0','metric':0}]}}";
					System.out.println(send.length());
					int sn = 123456789;
					short msgType = 1;
					short asn = 0;
					byte[] snArr = ByteUtil.int2Bytes(sn);
					byte[] msgArr = ByteUtil.short2Byte(msgType);
					byte[] asnArr = ByteUtil.short2Byte(asn);
					byte[] strArr = send.getBytes("UTF-8");
					//总长度
					int len =  snArr.length + msgArr.length + asnArr.length+ strArr.length+4;
					byte[] lenArr = ByteUtil.int2Bytes(len);
					System.out.println( "总长度:" +len );
					
					//打包字节数组
					byte[] result = ByteUtil.packByteArr(lenArr,snArr,msgArr,asnArr,strArr);
					int counter = 0;
//					for( byte b : result ){
//						System.out.println( counter + ":" +b );
//						counter ++;
//					}
					os.write(result); //发送字节数组
					os.flush();
//					os.close();
					
					 byte[] bytes = new byte[12];   
			         in.read(bytes);
			         System.out.println("received bytes len=" + bytes.length);
			         for( byte b : bytes ){
			        	 System.out.println( "b=" +b );
			         }
			        System.out.println( new Date().toLocaleString()+"receive byte=" + result.length );
					
					Thread.sleep(100000);
				
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public String getSend() throws InterruptedException{
			Thread.sleep(1000);
			return "<SOAP-ENV:Envelope>"+System.currentTimeMillis()+"</SOAP-ENV:Envelope>";
		}
	}

	class ReceiveThread extends Thread{
		private Socket socket;
		
		public ReceiveThread(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			while(true){
				try {		
					InputStream in = socket.getInputStream();
					byte[] temp = new byte[in.available()];  
			        byte[] result = new byte[12];  
			        int size = 0;  
			        while ((size = in.read(result)) != -1) {  
			            byte[] readBytes = new byte[size];  
			            System.out.println( "read bytes="+readBytes.length );
			            
			            result = ByteUtil.packByteArr(result,readBytes);  
			        }  
			        System.out.println( "receive byte=" + result.length );
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void start() throws UnknownHostException, IOException{
		Socket socket = new Socket("127.0.0.1",2015);
		new SendThread(socket).start();
//		new ReceiveThread(socket).start();
	}
	public static void main(String[] args) throws UnknownHostException, IOException {
		new SocketClient().start();
	}
}

