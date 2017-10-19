package com.network.monitor.tcpserver;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.network.monitor.util.ByteUtil;

/**
 * 实现解码器
 *
 */
public class ServerProtocolDecoder extends CumulativeProtocolDecoder {

	private static final Logger logger = LoggerFactory.getLogger(ServerProtocolDecoder.class);
	
	/**
	 * 1、当内容刚好时，返回false，告知父类接收下一批内容
     * 2、内容不够时需要下一批发过来的内容，此时返回false，这样父类 CumulativeProtocolDecoder会将内容放进IoSession中，等下次来数据后就自动拼装再交给本类的doDecode
     * 3、当内容多时，返回true，因为需要再将本批数据进行读取，父类会将剩余的数据再次推送本类的doDecode
	 */
	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
//		logger.info("接收到数据大小： "+in.remaining());
        if(in.remaining() >= 4){//有数据时，读取前4字节判断消息长度 
            byte [] sizeBytes = new byte[4]; 
            in.mark();//标记当前位置，以便reset
            //数据包的长度保存在第1-4字节中，
            in.get(sizeBytes,0,4);//读取4字节 
            
            byte[] len = new byte[4]; //总长度
            len[0] = sizeBytes[0];
            len[1] = sizeBytes[1];
            len[2] = sizeBytes[2];
            len[3] = sizeBytes[3];
//            System.out.println(len[0]+","+len[1]+","+len[2]+","+len[3]);
            //获取数据包长度 
            int size = ByteUtil.bytes2Int(len);
            
//            logger.info("数据包体大小size : "+size);
            in.reset();
            if(size > in.remaining()){//如果消息内容不够，则重置，相当于不读取size
                return false;//父类接收新数据，以拼凑成完整数据 
            } else{
                byte[] bytes = new byte[size];
                in.get(bytes, 0, size);
                
                TCPPack pack = new TCPPack();//定义协议包对象
                pack.setLen(size);
                
                byte[] sn = new byte[4]; //报文序列号
                sn[0] = bytes[4];
                sn[1] = bytes[5];
                sn[2] = bytes[6];
                sn[3] = bytes[7];
//                System.out.println( "报文序列号:" + sn[0]+","+sn[1]+","+sn[2]+","+sn[3]);
//                System.out.println( "报文序列号:" + ByteUtil.bytes2Int(sn));
                pack.setSn(ByteUtil.bytes2Int(sn));
                
                byte[] msgType = new byte[2]; //消息类型
                msgType[0] = bytes[8];
                msgType[1] = bytes[9];
//                System.out.println( "消息类型:" + msgType[0]+","+msgType[1]);
//                System.out.println( "消息类型:" + ByteUtil.byte2Short(msgType));
                pack.setMsgType(ByteUtil.byte2Short(msgType));
                
                byte[] asn = new byte[2]; //应答码
                asn[0] = bytes[10];
                asn[1] = bytes[11];
//                System.out.println( "应答码:" + asn[0] + "," + asn[1]);
//                System.out.println( "应答码:" + ByteUtil.byte2Short(asn));
                pack.setAsn(ByteUtil.byte2Short(asn));
                
                byte[] jsonArr = new byte[size-8]; //包体字节数组
                int counter = 0;
                for( int i = 12; i < size; i++){
                	jsonArr[counter++] = bytes[i];
                }
                 
                String jsonStr = new String(jsonArr,"UTF-8");  
//                System.out.println( "receive jsonStr=" + jsonStr );
                pack.setJsonStr(jsonStr);
                out.write(pack);
                if(in.remaining() > 0){//如果读取内容后还粘了包，就让父类再重读  一次，进行下一次解析 
                    return true; 
                } 
            } 
        } 
        return false;//处理成功，让父类进行接收下个包  
	}

}
