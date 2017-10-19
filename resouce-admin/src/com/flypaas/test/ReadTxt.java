
package com.flypaas.test;
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
 
/**
 * @author 码农小江
 * H20121012.java
 * 2012-10-12下午11:40:21
 */
public class ReadTxt {
    /**
     * 功能：Java读取txt文件的内容
     * 步骤：1：先获得文件句柄
     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流
     * 4：一行一行的输出。readline()。
     * 备注：需要考虑的是异常情况
     * @param filePath
     */
    public static void readTxtFile(String filePath){
        try {
                String encoding="GBK";
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    while((lineTxt = bufferedReader.readLine()) != null){
                        System.out.println(lineTxt);
                    }
                    read.close();
        }else{
            System.out.println("找不到指定的文件");
        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }
    
    public static void contentToTxt(String filePath,String outputPath) throws IOException {  
    	String encoding="GBK";
        String str = new String(); //原有txt内容  
        InputStreamReader input = null;
        BufferedWriter output = null;
        try {  
            File f = new File(filePath);
            File outputFlie = new File(outputPath);  
            if (f.exists()) {  
            } else {  
                System.out.print("文件不存在");  
                f.createNewFile();// 不存在则创建  
            }  
            input = new InputStreamReader( new FileInputStream(f),encoding);//考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(input);
            output = new BufferedWriter(new FileWriter(outputFlie));  
  
            while ((str = bufferedReader.readLine()) != null) {  
            	String[] strList = str.split("\\|");
            	String city = null;
            	String area = null;
            	if(strList.length > 7){
            		city = strList[7];
            	}
            	if(strList.length > 7){
            		area = strList[8];
            	}
            	System.out.println(city  +" : "+ area);
            	
            	
                output.write(str + "\r\n");
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
  
        } finally{
        	input.close();  
        	output.close();  
        	
        }
    }  
     
    public static void main(String argv[]) throws IOException{
        String filePath = "C:\\Users\\dell\\Desktop\\aaa.txt";
        String outputPath = "C:\\Users\\dell\\Desktop\\ccc.txt";
//      "res/";
        /*readTxtFile(filePath);*/
        contentToTxt(filePath,outputPath);
    }
     
     
 
}