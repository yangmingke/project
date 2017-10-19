package com.network.monitor.util;

/**
 * byte与int、short类型互转
 * @author 39p9g02
 *
 */
public class ByteUtil {
	
	public static byte[] int2Bytes(int num) {
		byte[] byteNum = new byte[4];
		for (int ix = 0; ix < 4; ++ix) {
			int offset = 32 - (ix + 1) * 8;
			byteNum[ix] = (byte) ((num >> offset) & 0xff);
		}
		return byteNum;
	}

	public static int bytes2Int(byte[] byteNum) {
		int num = 0;
		for (int ix = 0; ix < 4; ++ix) {
			num <<= 8;
			num |= (byteNum[ix] & 0xff);
		}
		return num;
	}

	public static byte int2OneByte(int num) {
		return (byte) (num & 0x000000ff);
	}
	
	/** 
     * 将short转成byte[2] 
     * @param a 
     * @return 
     */  
    public static byte[] short2Byte(short a){  
        byte[] b = new byte[2];  
          
        b[0] = (byte) (a >> 8);  
        b[1] = (byte) (a);  
          
        return b;  
    }  
    
    /** 
     * 将byte[2]转换成short 
     * @param b 
     * @return 
     */  
    public static short byte2Short(byte[] b){  
        return (short) (((b[0] & 0xff) << 8) | (b[1] & 0xff));  
    }  
	
	/**
	 * byte数组组合
	 * @param aArr
	 * @param bArr
	 * @return
	 */
	public static byte[] packByteArr(byte[]... arr){
		
		int counter=0;
		int length = 0;
		for(byte[] bArr : arr){
			for(byte b : bArr){
				length ++;
			}
		}
		
		byte[] result = new byte[length];
		
		for(byte[] bArr : arr){
			for(byte b : bArr){
				result[counter++] = b;
			}
		}
		return result;
	}

	/**  
	   * 将高字节数组转换为int  
	   * @param b byte[]  
	   * @return int  
	   */   
	public   static   int  hBytesToInt( byte [] b) {  
	   int  s =  0 ;  
	   for  ( int  i =  0 ; i <  3 ; i++) {  
	     if  (b[i] >=  0 ) {  
	     s = s + b[i];  
	     } else  {  
	     s = s + 256  + b[i];  
	     }  
	     s = s * 256 ;  
	   }  
	   if  (b[ 3 ] >=  0 ) {  
	     s = s + b[3 ];  
	   } else  {  
	     s = s + 256  + b[ 3 ];  
	   }  
	   return  s;  
	}

	public static void main(String args[]){
		byte[] arr = ByteUtil.int2Bytes(13567);
		for(byte b : arr){
			System.out.println( b );
		}
		byte[] bArr = new byte[4];
		bArr[0] = 12;
		bArr[1] = 0;
		bArr[2] = 0;
		bArr[3] = 0;
		System.out.println( "字节转整型："+ByteUtil.bytes2Int(bArr));
		System.out.println( "高位字节："+ByteUtil.hBytesToInt(bArr));
		
		byte[] shortArr = ByteUtil.short2Byte((short)2);
		for(byte b : shortArr){
			System.out.println( "short :"+b );
		}
		shortArr[0] = 0;
		shortArr[1] = 1;
		System.out.println( ByteUtil.byte2Short(shortArr) );
	}
}