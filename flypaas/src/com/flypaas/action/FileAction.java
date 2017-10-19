package com.flypaas.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.struts2.convention.annotation.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.utils.Des3Utils;
import com.flypaas.utils.FileContentTypes;
import com.flypaas.utils.StrUtil;
import com.flypaas.utils.SysConfig;

@Controller
@Scope("prototype")
public class FileAction extends BaseAction {
	private String fileName;
	private Logger logger = LoggerFactory.getLogger(FileAction.class);
	
	@Action("/file/downLoad")
	public void downLoad() throws UnsupportedEncodingException{
		// 下载本地文件
        fileName = StrUtil.isEmpty(fileName) ? "flyRTC.apk" : fileName;// 暂时//TODO
        String apkPath = SysConfig.getInstance().getProperty("apk_path");
        // 读到流中
        InputStream inStream = null;
        File f = null;
		try {
			f= new File(apkPath+fileName);  
			inStream = new FileInputStream(f);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0){
                response.getOutputStream().write(b, 0, len);
            }
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@Action("/file/useragreement")
	public void useragreement() throws UnsupportedEncodingException{
		// 下载本地文件
		fileName = SysConfig.getInstance().getProperty("useragreement_file");
        String filePath = SysConfig.getInstance().getProperty("useragreement_path");
        // 读到流中
        InputStream inStream = null;
        File f = null;
		try {
			f= new File(filePath+fileName);  
			inStream = new FileInputStream(f);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentLength((int) f.length());
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0){
                response.getOutputStream().write(b, 0, len);
            }
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	@Action("/file/view")
	public void file(){
		FileInputStream fis = null;
		try {
			fileName = Des3Utils.decodeDes3(fileName);
			if(StrUtil.isEmpty(fileName)){
				logger.info("文件名称为null，退出打印文件");
				return;
			}
			File file = new File(fileName);
			String fileName = file.getName();
			fis = new FileInputStream(file);
			response.reset();
			response.setCharacterEncoding("GBK");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes("GBK"), "ISO-8859-1"));
			response.setContentType(FileContentTypes.getContentType(fileName));
			response.setContentLength((int)file.length());
			OutputStream os=new BufferedOutputStream(response.getOutputStream());  
			byte[] b=new byte[100];  
			int len=0;  
			while((len=fis.read(b))>0){  
				os.write(b,0,len);  
			}
			os.flush();  
			os.close(); 
		} catch (FileNotFoundException e) {
			logger.info("找不到文件:"+fileName+"，异常：", e);
		} catch (IOException e) {
			logger.info("打印文件流"+fileName+"失败", e);
		} 
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
