package com.network.monitor.util;

/**
 * TCP监听常量定义
 *
 */
public class Consts {
	
	//TCP监听端口
    public static final int TCPPORT=2015;
    
    //UDP监听端口
    public static final int UDPPORT=2016;
    
    //消息类型常量
    public static final int ROUTE_REPORT=1;
    public static final int ROUTE_REPORT_ACK=2;
    public static final int LINK_REPORT=3;
    public static final int LINK_REPORT_ACK=4;
    public static final int TRANS_REPORT=5;
    public static final int TRANS_REPORT_ACK=6;
    public static final int NEIGHBOR_REPORT=7;
    public static final int NEIGHBOR_REPORT_ACK=8;
    
    //应答码定义
    public static final int SUCCESS=0;
    public static final int ERRO=1;
    
    /**
     * 将字节数组转换成int
     * @param b
     * @param length
     * @return
     */
    public static int byteArrayToInt(byte[] b, int length) {
	       int value= 0;
	       for (int i = 0; i < length; i++) {
	    	   System.out.println(b[i]);
	           int shift= (length-1-i);
	           value +=(b[i]) << shift;
	       }
	       return value;
	 }
}
