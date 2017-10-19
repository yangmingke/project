package com.network.monitor.tcpserver;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.Socket;
import java.nio.CharBuffer;

import com.network.monitor.util.ByteUtil;

public class TCPClient extends Thread{

	private Socket clientSocket;
	
	public TCPClient(){
		try {
			clientSocket = new Socket("127.0.0.1", 2015);
			System.out.println("已经连上服务器");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		try {
//			while(1==2){
				
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		TCPClient tcpClient = new TCPClient();
		tcpClient.start();
	}
}
