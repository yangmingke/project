package com.network.monitor.tcpserver;


import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.network.monitor.util.ByteUtil;

public class ServerProtocolEncoder extends ProtocolEncoderAdapter{

	/**
	 * 编码器
	 */
	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput output)
			throws Exception {
//		System.out.println(message);

		IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);
		TCPPack pack = (TCPPack)message;
//		System.out.println( "返回client byte:" + message);
		int sn = pack.getSn();
		short msgType = (short)(pack.getMsgType()+(short)1);
		short asn = pack.getAsn(); //应答码，0：成功，1：失败
		byte[] snArr = ByteUtil.int2Bytes(sn);
		byte[] msgArr = ByteUtil.short2Byte(msgType);
		byte[] asnArr = ByteUtil.short2Byte(asn);
		//总长度
		int len =  snArr.length + msgArr.length + asnArr.length+4;
		byte[] lenArr = ByteUtil.int2Bytes(len);
		
		
		//打包字节数组
		byte[] result = ByteUtil.packByteArr(lenArr,snArr,msgArr,asnArr);
//		for( int i = 0; i < result.length; i++ ){
//			System.out.print(result[i]+",");
//		}
//		System.out.println( "总长度:" +result.length );
		buffer.put(result);
		buffer.flip();
		output.write(buffer);
	}

}
