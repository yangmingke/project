package com.network.monitor.tcpserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.network.monitor.util.Consts;

/**
 * 启动项目时开启TCP服务
 *
 */
@Component
public class TCPServer{

	private static final Logger LOGGER = LoggerFactory.getLogger(TCPServer.class);

	@Autowired
	private TCPServerHandler tCPServerHandler;
	
	/**
	 * 启动TCP服务
	 * 初始化
	 */
	@PostConstruct
	public void init() {
		LOGGER.debug("\n\n---------------------【开始启动TCP服务器】-------------------\n");

        try {
        	//创建一个非阻塞的Server端socket，用NIO  
            IoAcceptor acceptor = new NioSocketAcceptor();  
            //创建接受数据的过滤器  
            DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();  
            //设定这个过滤器将一行一行的读取数据 
            ServerProtocolDecoder decoder = new ServerProtocolDecoder();
            ServerProtocolEncoder encoder = new ServerProtocolEncoder();
            chain.addLast("codec",new ProtocolCodecFilter(new ServerProtocolCodecFactory(encoder,decoder)));// 指定编码过滤器 
            chain.addLast("executor",new ExecutorFilter());
            //设置session配置，10秒内无操作进入空闲状态
            acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,10);
            acceptor.getSessionConfig().setMaxReadBufferSize(2048);
//            ((NioSocketAcceptor)acceptor).setReuseAddress(true);//端口重用
            // 指定业务逻辑处理器  
            acceptor.setHandler((IoHandler) tCPServerHandler);  
            // 设置端口号  
            acceptor.setDefaultLocalAddress(new InetSocketAddress(Consts.TCPPORT));  
			acceptor.bind();//启动监听  
		} catch (IOException e) {
			e.printStackTrace();
		} 
        
		LOGGER.debug("\n\n---------------------【TCP服务器已经启动，在"+Consts.TCPPORT+"端口上侦听】---------------\n");
		
		//开启UDP来接收心跳信息，探测TCP连接状态
		LOGGER.debug("\n\n---------------------【开始启动UDP服务器】-------------------\n");
        try {
        	NioDatagramAcceptor udpacceptor = new NioDatagramAcceptor();//创建一个UDP的接收器  
        	udpacceptor.setHandler(new UDPServerHandler());//设置接收器的处理程序  
        	udpacceptor.getFilterChain().addLast("executor",new ExecutorFilter());
        	
        	Executor threadPool = Executors.newCachedThreadPool();  
            DefaultIoFilterChainBuilder chain = udpacceptor.getFilterChain(); 
            chain.addLast("threadPool", new ExecutorFilter(threadPool));
            
            DatagramSessionConfig dcfg = udpacceptor.getSessionConfig();//建立连接的配置文件  
            dcfg.setReadBufferSize(4096);//设置接收最大字节默认2048  
            dcfg.setReceiveBufferSize(1024);//设置输入缓冲区的大小  
            dcfg.setSendBufferSize(1024);//设置输出缓冲区的大小  
            dcfg.setReuseAddress(true);//设置每一个非主监听连接的端口可以重用 
            udpacceptor.bind(new InetSocketAddress(Consts.UDPPORT));//绑定端口 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
