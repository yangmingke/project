package com.flypaas.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flypaas.dao.TbRsCityMapper;
import com.flypaas.dao.TbRsOprateLogMapper;
import com.flypaas.model.TbRsCity;
import com.flypaas.service.DemoService;
import com.flypaas.util.PageContainer;

@Service("/menuServiceImpl")
public class DemoServiceImpl implements DemoService{
	@Autowired
	private TbRsOprateLogMapper tbRsOprateLogMapper;
	@Autowired
	TbRsCityMapper tbRsCityMapper;

	@Override
	public PageContainer queryDemo(String netId,int page) {
		Map<String,String> data = new HashMap<String,String>();
		data.put("netSid", netId);
		int total = tbRsOprateLogMapper.queryCount(netId);
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("netSid", netId);
		PageContainer pageContainer = new PageContainer();
		para = pageContainer.createParameter(para, page, total);
		List<Object> result = tbRsOprateLogMapper.queryLogPage(para);
		pageContainer.setResult(result);
		return pageContainer;
	}
	
	public void contentToTxt(String filePath,String outputPath) throws IOException {  
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
            	if(strList.length > 7 && !strList.equals("")){
            		city = strList[7];
            		if(!city.equals("")){
	            		TbRsCity tbRsCity = tbRsCityMapper.queryCityByname(city);
	            		if( tbRsCity != null){
	            			strList[7] = tbRsCity.getCityid().toString();
	            		}
            		}
            	}
            	if(strList.length > 7){
            		area = strList[8];
            		strList[8] = "";
            	}
            	System.out.println(city  +" : "+ area);
            	String outputStr = "";
            	for(int i=0;i < strList.length; i++){
            		outputStr += strList[i];
            		if(i != strList.length - 1){
            			outputStr += "|";
            		}
            	}
            	output.write(outputStr + "\r\n");
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
  
        } finally{
        	input.close();  
        	output.close();  
        	
        }
    }  

	

}
