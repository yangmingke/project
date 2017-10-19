package com.yzx.rest.util;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.log4j.Logger;

/**
 * 公共工具类
 * 
 * @author ：yzd
 * @date ：2015年9月7日
 */
public class CommonUtil {

	private static final Logger logger = Logger.getLogger(CommonUtil.class);
	public static String ip = "";

	// linux/unix下面获取ip地址通过网卡绑定获取，否则只会获取host文件的ip地址
	// 如果同一网卡绑定了多个ip地址，程序会不识别使用哪个ip，则最好直接读取配置文件的真实ip的方式
	// 此处默认获取第一张网卡的第一个ip地址
	public static String getUnixIP() {
		if (ip.equals("")) {
			try {
				Enumeration<?> e1 = (Enumeration<?>) NetworkInterface.getNetworkInterfaces();
				while (e1.hasMoreElements()) {
					NetworkInterface ni = (NetworkInterface) e1.nextElement();
					Enumeration<?> e2 = ni.getInetAddresses();
					while (e2.hasMoreElements()) {
						InetAddress ia = (InetAddress) e2.nextElement();
						if (ia instanceof Inet6Address)
							continue;
						ip = ia.getHostAddress();
						break;
					}
					break;
				}
			} catch (SocketException e) {
				logger.info("获取ip地址错误：" + e);
			}
		}
		return ip;
	}
}
