package com.yzx.rest.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

import com.tools.StreamUtil;
public class BodyMsgUtil
{
    private static final Logger log = Logger.getLogger(BodyMsgUtil.class);

    /**
     * 从客户端获取数据，不包含解压解密
     * @param request
     * @return
     * @throws IOException
     */
    public static String commonProcess(InputStream is)
    {
        if (is == null)
        {
            return "";
        }
        String clientText = "";
        BufferedInputStream bis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try
        {
            bis = new BufferedInputStream(is);
            isr = new InputStreamReader(bis, "UTF-8");
            br = new BufferedReader(isr);
            String temp = "";
            StringBuffer sbBuffer = new StringBuffer();
            while ((temp = br.readLine()) != null)
            {
                sbBuffer.append(temp);
            }
            clientText = sbBuffer.toString();
        }
        catch (IOException e)
        {
            log.error("commonProcess IOException.", e);
        }
        catch (Exception e)
        {
            log.error("commonProcess Exception.", e);
        }
        finally
        {
            StreamUtil.close(br);
            StreamUtil.close(isr);
            StreamUtil.close(bis);
            StreamUtil.close(is);
        }
        return clientText;
    }

    /**
     * 从客户端获取数据，包含解压解密
     * @param uncompressText
     * @return
     * @throws Exception
     */
    public static String decryptProcess(InputStream is)
    {
        if (is == null)
        {
            return "";
        }
        int headerData = 0;
        boolean isGzipStream = false;
        String logContent = "";
        String uncompressText = "";
        BufferedInputStream bis = null;
        GZIPInputStream gzipis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try
        {
            bis = new BufferedInputStream(is);
            bis.mark(2);
            // 取前两个字节
            byte[] header = new byte[2];
            int result = bis.read(header);
            // reset输入流到开始位置
            bis.reset();
            // 判断是否是GZIP格式
            headerData = getShort(header);
            // Gzip 流 的前两个字节是 0x1f8b
            if (result != -1 && headerData == 0x1f8b)
            {
                isGzipStream = true;
            }
            // GZIP输入流
            gzipis = new GZIPInputStream(bis);
            isr = new InputStreamReader(gzipis, "UTF-8");
            br = new BufferedReader(isr);
            String temp = "";
            StringBuilder sb = new StringBuilder();
            while ((temp = br.readLine()) != null)
            {
                sb.append(temp);
            }
            // 解压后的日志内容
            uncompressText = sb.toString();
            if(log.isDebugEnabled()){
                log.debug("decryptProcess|isGzipStream=" + isGzipStream);
            }
            // 解密后的日志内容
            logContent = aesDecrypt(uncompressText);
        }
        catch (IOException e)
        {
            log.error("decryptProcess IOException|headerData=" + headerData
                + "|isGzipStream=" + isGzipStream, e);
        }
        catch (Exception e)
        {
            log.error("decryptProcess Exception.", e);
        }
        finally
        {
            StreamUtil.close(br);
            StreamUtil.close(isr);
            StreamUtil.close(gzipis);
            StreamUtil.close(bis);
            StreamUtil.close(is);
        }
        return logContent;
    }

    /**
     * aes解密
     * @param strKey
     * @param strIn
     * @return
     * @throws Exception
     */
    public static String aesDecrypt(String strIn)
    {
        String ret = "";
        try
        {
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            IvParameterSpec iv = new IvParameterSpec("0102030405060708"
//                .getBytes());
//            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
//            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(strIn);
//            byte[] original = cipher.doFinal(encrypted1);
//            ret = new String(original);
        }
        catch (Exception e)
        {
            log.error("aesDecrypt Exception.", e);
        }
        return ret;
    }

    /**
     * 
     * @param strKey
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unused")
	private static SecretKeySpec aesgetKey(String strKey)
    {
        byte[] arrBTmp = strKey.getBytes();
        // 创建一个空的16位字节数组（默认值为0）
        byte[] arrB = new byte[16];
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++)
        {
            arrB[i] = arrBTmp[i];
        }
        SecretKeySpec skeySpec = new SecretKeySpec(arrB, "AES");
        return skeySpec;
    }

    private static int getShort(byte[] data)
    {
        return (int) ((data[0] << 8) | data[1] & 0xFF);
    }
}
