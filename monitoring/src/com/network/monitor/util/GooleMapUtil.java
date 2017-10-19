package com.network.monitor.util;


import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.UnsupportedEncodingException;  
import java.net.MalformedURLException;  
import java.net.URL;  
import java.net.URLConnection;  
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  

import com.google.gson.stream.JsonReader;  

/** 
* 获取经纬度通过google 
*  
* @author jueyue 返回格式：Map<String,Object> map map.put("status", 
*         reader.nextString());//状态 map.put("result", list);//查询结果 
*         list<map<String,String>> 
*/  
public class GooleMapUtil {  
  /** 
   * @param isProxyip 
   *            是否使用代理 
   * @param addr 
   *            查询的地址 
   * @return 
   */  
  public static  Map<String, Object> getCoordinate(String addr) {  
      String address = null;  
      Map<String, Object> map = new HashMap<String, Object>();  
      try {  
          address = java.net.URLEncoder.encode(addr, "UTF-8");  
      } catch (UnsupportedEncodingException e1) {  
          e1.printStackTrace();  
      }  
      int ipPort = (int) Math.round((Math.random() * 190));  
//      PostPortConst.getInstance();  
//      InetSocketAddress addrss = new InetSocketAddress(  
////            PostPortConst.getProxyip()[ipPort][0],  
////            Integer.valueOf(PostPortConst.getProxyip()[ipPort][1]));  
//              "118.97.103.82",8080);  
//      Proxy proxy = new Proxy(Proxy.Type.HTTP, addrss);  
      String key = "zh-CN";  
      String url = String  
              .format("http://ditu.google.cn/maps/api/geocode/json?address=%s&sensor=false&language=%s",  
                      address, key);  
      URL myURL = null;  
      URLConnection httpsConn = null;  
      try {  
          myURL = new URL(url);  
      } catch (MalformedURLException e) {  
          e.printStackTrace();  
      }  
      try {  
        httpsConn = (URLConnection) myURL.openConnection();// 不使用代理  
          if (httpsConn != null) {  
              InputStreamReader insr = new InputStreamReader(  
                      httpsConn.getInputStream(), "UTF-8");  
              JsonReader reader = new JsonReader(insr);  
              reader.beginObject();  
              while (reader.hasNext()) {  
                  String tagName = reader.nextName();  
                  if (tagName.equals("results")) {  
                      reader.beginArray();  
                      List<Map<String, String>> list = new ArrayList<Map<String, String>>();  
                      while (reader.hasNext()) {  
                          reader.beginObject();  
                          Map<String, String> map_temp = new HashMap<String, String>();  
                          while (reader.hasNext()) {  
                              tagName = reader.nextName();  
                              if (tagName.equals("address_components")) {  
                                  reader.skipValue();  
                              } else if (tagName.equals("formatted_address")) {  
                                  map_temp.put("address", reader.nextString());  
                              } else if (tagName.equals("geometry")) {  
                                  reader.beginObject();  
                                  while (reader.hasNext()) {  
                                      tagName = reader.nextName();  
                                      if (tagName.equals("location")) {  
                                          reader.beginObject();  
                                          while (reader.hasNext()) {  
                                              map_temp.put(reader.nextName(),  
                                                      reader.nextString());  
                                          }  
                                          reader.endObject();  
                                      } else {  
                                          reader.skipValue();  
                                      }  
                                  }  
                                  reader.endObject();  
                              } else {  
                                  reader.skipValue();  
                              }  
                          }  
                          list.add(map_temp);  
                          reader.endObject();  
                      }  
                      map.put("result", list);  
                      reader.endArray();  
                  } else if (tagName.equals("status")) {  
                      map.put("status", reader.nextString());  
                  }  
              }  
              insr.close();  
          }  
      } catch (IOException e) {  
          e.printStackTrace();  
      }  
      return map;  
  }  
  public static void main(String[] args) {
	  getCoordinate("洛杉矶");
}
}  
