package com.flypaas.admin.util.file;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FileUtil {
	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	//创建文件并写入内容
	public static void createFile(String dirName, String fileName,
			String content) throws IOException {
		File file = createEmptyFile(dirName, fileName);
		setFileContent(file, content);
	}
	public static void setFileContent(File file,String content) throws IOException{
		OutputStream out = new FileOutputStream(file);
		out.write(content.getBytes());
		out.close();
	}
	//创建文件夹
	public static void mkDir(String dirName){
		if (!isExist(dirName)) {
			new File(dirName).mkdirs();
		}
	}
	//根据文件夹名称和文件名创建文件
	public static File createEmptyFile(String dirName,String fileName) throws IOException{
		mkDir(dirName);
		File file1 = new File(dirName+fileName);
		if(!file1.exists()){
			file1.createNewFile();
		}
		return file1;
	}
	//获取目录下面的txt文件的内容
	@SuppressWarnings("unused")
	public static String getFileConToStr(String pathName) throws IOException {
		File file = new File(pathName);
		if(file==null){
			return null ;
		}
		BufferedReader  input = new BufferedReader(new FileReader(file));
		String ct = input.readLine();
		input.close();
		return ct;
	}
	//删除本地文件
	public static void deleteFile(String pathName){
		File file = new File(pathName);
		file.delete();
	}
	//判读文件是否存在
	public static boolean isExist(String fileName){
		File file = new File(fileName);
		if(file.exists()){
			return true ;
		}
		return false;
	}
	//修改文件内容
	public static void updateFileContent(String fileName,String content) throws IOException{
		File file = new File(fileName);
		setFileContent(file, content);
	}
	/**
	 * 下载文件
	 * 
	 * @param path
	 *            文件路径
	 */
	public static void download(String path,HttpServletResponse response) {
		String fileName = path.substring(path.lastIndexOf("/") + 1);
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(path); // 文件流
			// 设置response的Header
			response.reset();
			response.setCharacterEncoding("GBK");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes("GBK"), "ISO-8859-1"));
			response.setContentType(FileContentTypes.getContentType(fileName));
			out = new BufferedOutputStream(response.getOutputStream());
			byte[] buffer = new byte[16 * 1024];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			out.flush();
		} catch (Throwable e) {
			logger.error("下载文件失败：path=" + path, e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				logger.error("关闭文件失败：path=" + path, e);
			}
		}
		logger.debug("下载文件成功：path=" + path);
	}
	public static String upload(File file,String path,String fileName){
		if(file==null){
			return null ;
		}
		//基于myFile创建一个文件输入流  
        InputStream is = null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			logger.error("------------------------上传图片 创建文件流失败------------------------");
			logger.error(e1.getMessage(),e1);
		}  
          
        File toFile = null;
		try {
			toFile = FileUtil.createEmptyFile(path, fileName);
		} catch (IOException e1) {
			logger.error("------------------------上传图片 生成指定目录空文件失败------------------------");
			logger.error(e1.getMessage(),e1);
		}
		
        // 创建一个输出流  
        OutputStream os = null;
		try {
			os = new FileOutputStream(toFile);
		} catch (FileNotFoundException e) {
			logger.error("------------------------上传图片 创建文件输出流失败------------------------");
			logger.error(e.getMessage(),e);
		}  
  
        //设置缓存  
        byte[] buffer = new byte[1024];  
  
        int length = 0;  
  
        //读取myFile文件输出到toFile文件中  
        try {
        	if(null != is && null != os){
        		while ((length = is.read(buffer)) > 0) {  
    			    os.write(buffer, 0, length);  
    			}
        	}
		} catch (IOException e) {
			logger.error("------------------------上传图片 读文件流失败------------------------");
			logger.error(e.getMessage(),e);
		}  
        //关闭入流  
        try {
        	ColserUtils.closeEx(is);
        	ColserUtils.closeEx(os);
		} catch (IOException e) {
			logger.error("------------------------上传图片  关闭输入输出流失败------------------------");
			logger.error(e.getMessage(),e);
		}  
        return toFile.getAbsolutePath(); 
	}
	public static String checkFile(File file,long size){
		int rest = checkFileSize(file,size);
		String msg = null ;
		//文件大小超过限制
		if(rest==1){
			msg = "文件大小超过限制" ;
		}else if(rest==2){
			msg = "文件不能为空" ;
		}
		return msg ;
	}
	private static int checkFileSize(File file,long size){
		final long MAX_SIZE = size;
		if(file!=null){
			if(file.length()>MAX_SIZE){
				return 1 ;
			}else{
				return 0 ;
			}
		}else{
			return 2 ;
		}
	}
}
