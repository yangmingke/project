<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="u"  uri="/flypaas-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>快传音视频开放平台_提供音视频通讯加速,一对一视频通话，视频直播等通讯加速API及SDK</title>
	<meta name="keywords" content="一对一音视频通话,音视频通讯,音视频通讯加速,音视频通话,在线教育,视频通话,视频会议,互联网语音,通讯加速,快传,快传技术," >
	<meta name="description" content="快传音视频通讯平台让开发者轻松接入一对一视频通话,音视频通讯加速,视频直播等能力,快速搭建音视频通讯,视频通话/会议应用,通讯加速,音视频引擎让视频更流畅。" >
	<%@include file="/front/resource1.jsp"%>
	<script type="text/javascript">
	$(function(){
		var downloadType = "${downloadType}";
		if(downloadType=="a"){
			$(".download_tab_tit ul li:eq(1)").addClass("current").siblings("li").removeClass("current");
			$(".download_tab_tit ul li:eq(1)").find("i").show().end().siblings("li").find("i").hide();
			$(".download_tab_ctn").find("img").eq(1).show().siblings("img").hide();
			$(".download_tab_tit ul li:eq(1)").parents(".download_tab_tit").css("border-color","#c4f3d7");
			//对应各版本信息展示
			$(".download_box").find(".download_detail").eq(1).show().siblings(".download_detail").hide();
		}
		
		if(downloadType=="w"){
			$(".download_tab_tit ul li:eq(2)").addClass("current").siblings("li").removeClass("current");
			$(".download_tab_tit ul li:eq(2)").find("i").show().end().siblings("li").find("i").hide();
			$(".download_tab_ctn").find("img").eq(2).show().siblings("img").hide();
			$(".download_tab_tit ul li:eq(2)").parents(".download_tab_tit").css("border-color","#bcdef2");
			//对应各版本信息展示
			$(".download_box").find(".download_detail").eq(2).show().siblings(".download_detail").hide();
		}
		
		
		
		$("#li3_i").click(function(){
			location.href = "http://docs.flypaas.com/doku.php?id=ios_sdk%E4%BB%8B%E7%BB%8D";
		});
		$("#li3_w").click(function(){
			location.href = "http://docs.flypaas.com/doku.php?id=windows_sdk%E4%BB%8B%E7%BB%8D";
		});
		$("#li3_a").click(function(){
			location.href = "http://docs.flypaas.com/doku.php?id=android_sdk介绍";
		});
	});
	function updateDownloadCount(key,version){
		$.ajax({
			url:"/download/updateDownloadCount",
			type:"post",
			data:"key="+key+"&version="+version,
			dataType: "text",
			success: function (data) {
	        },
	        error: function (msg) {
	        }
		});
	}
	function showlog_a(key){
		$.ajax({
			url:"/download/versionDesc",
			type:"post",
			data:"key="+key,
			dataType: "text",
			success: function (data) {
				$("#a_r").html(data);
	        },
	        error: function (msg) {
	        }
		});
	}
	
	function showlog_i(key){
		$.ajax({
			url:"/download/versionDesc",
			type:"post",
			data:"key="+key,
			dataType: "text",
			success: function (data) {
				$("#i_r").html(data);
	        },
	        error: function (msg) {
	        }
		});
	}
	
	function showlog_w(key){
		$.ajax({
			url:"/download/versionDesc",
			type:"post",
			data:"key="+key,
			dataType: "text",
			success: function (data) {
				$("#w_r").html(data);
	        },
	        error: function (msg) {
	        }
		});
	}
</script>
</head>
<body id="b-02">
	<!--公共头部 ft_header bof-->
    <%@include file="/front/head1.jsp" %>

	<!--主体部分 ft_content bof-->
    <div class="ft_content">
    	<div class="ft_banner ft_banner7">
            <div class="ft_banner_wp">
                <div class="download_tab_tit">
                    <ul>
                        <li class="ios current"><i></i></li>
                        <li class="android"><i style="display:none;"></i></li>
                        <li class="windows"><i style="display:none;"></i></li>
                    </ul>
                </div>
                <div class="download_tab_ctn">
                    <img src="<%=path%>/front/images1/i_sdk.png" />
                    <img src="<%=path%>/front/images1/a_sdk.png" style="display:none;" />
                    <img src="<%=path%>/front/images1/w_sdk.png" style="display:none;" />
                </div>
            </div>   
        </div>
    	<div class="item_box download_box">
    		<div class="item_box_wp">

                <!--ios 下载 bof-->
                <div class="download_detail">
                    <div class="download_tit">
                        <ul>
                            <li class="li1 current">iOS SDK 简介</li>
                            <li class="li2">iOS 资源下载</li>
                            <li class="li3" id="li3_i">iOS SDK 使用教程</li>
                        </ul>
                    </div>                    
                    
                    <div class="dowload_info">

                        <!--ios sdk简介 bof-->
                        <div class="info">
                            <div class="desc">
                                <p>快传融合通讯开放平台iOS SDK是以C++静态库的方式提供给iOS开发人员，该SDK提供了与固定电话或者其他的快传融合通讯开放平台客户端设备（包含网络浏览器和其他的移动设备）进行语音通信。可以方便快速为您的iOS应用实现打电话和接听电话的功能，包括在游戏中实现实时语音聊天等功能。</p>
                                <div class="txt">
                                    <b>1. SDK 文件介绍</b>
                                    <p>UCSService.h能力类文件。</p>
                                    <p>UCSService类连接客户端到快传融合通讯开放平台服务器。这个类包含了注册服务器，电话，实时语音等能力接口。</p>
                                    <p>UCSEvent.h代理协议类文件。</p>
                                    <p>UCSCommonClass.h消息公共类文件，主要包含了接口中所需要的数据相关类。</p>
                                </div>
                            </div>                                
                        </div>
                        <!--ios sdk简介 eof-->

                        <!--ios 资源下载 bof-->
                        <div class="info" style="display:none;">
                            <div class="info_left">
                            
							<s:iterator value="sdkIList" id="a" status="index">
		                            <div class="info_list">
		                                 <strong><s:property value="#a.name"/>
		                                 	<a href="javascript:void(0)" onclick="showlog_i(${a.key})" id="i_l" class="update_log">更新日志:
			                                 	<s:if test="#index.index+1==1">
				                                 	<script type="text/javascript">
				                                 		showlog_i("${a.key}");
				                                 		$("#i_l").attr("class","update_log current");
				                                 	</script>
			                                 	</s:if>
		                                 	</a>
		                                 </strong>
		                                 <p class="mark"><span>正式版</span></p>
		                                 <dl>
		                                     <dt>版本号：</dt>
		                                     <dd>${a.version}</dd>
		                                 </dl>
		                                 <dl>
		                                     <dt>更新时间：</dt>
		                                     <dd><s:date name="#a.updateDate" format="yyyy-MM-dd"/></dd>
		                                 </dl>
		                                 <dl>
		                                     <dt>下载地址：</dt>
		                                     <dd><a href="<%=path%>/file/view?fileName=<u:des3 value='${a.path}' />" onclick="updateDownloadCount('<s:property value="#a.key" />','<s:property value="#a.version" />')">下载至本机</a></dd>
		                                 </dl>
		                             </div>
	                             </s:iterator>
                            	
                            	<div class="info_list">
                            		<s:iterator value="clientList" id="c">
                            			<s:if test="#c.key==2112">
		                                    <strong>演示应用iPA 、源码 包下载<a href="javascript:void(0);" class="update_log" onclick="showlog_i(${c.key})">更新日志</a></strong>
		                                    <dl>
		                                        <dt>版本号：</dt>
		                                        <dd>V<s:property value="#c.version"/></dd>
		                                    </dl>
		                                    <dl>
		                                        <dt>更新时间：</dt>
		                                        <dd><s:date name="#a.updateDate" format="yyyy-MM-dd"/></dd>
		                                    </dl>
		                                    <dl>
	                                        <dt>下载地址：</dt><dd>
	                                    </s:if>
	                                        
	                                        <s:if test="#c.key==2112">
	                                       		 <a href="<%=path%>/file/view?fileName=<u:des3 value="${c.path}" />" onclick="updateDownloadCount('<s:property value="#c.key" />','<s:property value="#c.version" />')">演示iPA安装包</a>
	                                       	</s:if>
	                                       	<s:if test="#c.key==2122">
	                                        	<a href="<%=path%>/file/view?fileName=<u:des3 value="${c.path}" />" onclick="updateDownloadCount('<s:property value="#c.key" />','<s:property value="#c.version" />')">源码</a>
			                                    </dd></dl>
	                                        </s:if>
	                                 </s:iterator>
                                </div>
                            </div>
                            
                            <div class="info_right">
                                <div class="right_list" id= "i_r"></div>
                            </div>

                        </div>
                        <!--ios 资源下载 eof-->    

                        <!--ios sdk使用教程 bof--> 
                        <div class="info" style="display:none;"></div>     
                        <!--ios sdk使用教程 eof-->               

                    </div>
                </div>	
                <!--ios 下载 eof-->

                <!--android 下载 bof-->
                <div class="download_detail" style="display:none;">
                    <div class="download_tit">
                        <ul>
                            <li class="li1 current">Android SDK 简介</li>
                            <li class="li2">Android 资源下载</li>
                           	<li class="li3" id="li3_a">
                            	Android SDK 使用教程
                           	</li>
                        </ul>
                    </div>                    
                    
                    <div class="dowload_info">

                        <!--android sdk简介 bof-->
                        <div class="info">
                            <div class="desc">
                                <p>快传融合通讯开放平台Android SDK是以Java libs的方式提供给Android开发人员，该SDK提供了与固定电话或者其他的快传融合通讯开放平台客户端设备（包含网络浏览器和其他的移动设备）进行语音通信。可以方便快速为您的Android应用实现打电话和接听电话的功能，包括在游戏中实现实时语音聊天等功能。</p>
                                <div class="txt">
                                    <b>1. SDK 通讯类介绍</b>
                                    <p>UCSService SDK核心通讯类,用于连接云服务平台。</p>
                                    <p>UCSMessage SDK消息管理类,用于消息发送/录音,添加自定义消息类型等操作。</p>
                                    <p>UCSCall SDK VoIP电话管理类,用于呼叫请求,挂断,静音等一系统操作。</p>
                                </div>
                                <div class="txt">
                                    <b>2. SDK 工具类介绍</b>
                                    <p>FileTools文件工具类。</p>
                                    <p>CustomLog日志工具类，用于打印日志。</p>
                                    <p>MapTools地图位置工具类。</p>
                                </div>
                            </div>                                
                        </div>
                        <!--android sdk简介 eof-->

                        <!--android 资源下载 bof-->
                        <div class="info" style="display:none;">
                            <div class="info_left">
                                <s:iterator value="sdkAList" id="a" status="index">
		                            <div class="info_list">
		                                 <strong><s:property value="#a.name"/>
		                                 	<a href="javascript:void(0)" onclick="showlog_a(${a.key},'a')" id="a_l" class="update_log">更新日志:
			                                 	<s:if test="#index.index+1==1">
				                                 	<script type="text/javascript">
				                                 		showlog_a("${a.key}");
				                                 		$("#a_l").attr("class","update_log current");
				                                 	</script>
			                                 	</s:if>
		                                 	</a>
		                                 </strong>
		                                 <p class="mark"><span>正式版</span></p>
		                                 <dl>
		                                     <dt>版本号：</dt>
		                                     <dd>${a.version}</dd>
		                                 </dl>
		                                 <dl>
		                                     <dt>更新时间：</dt>
		                                     <dd><s:date name="#a.updateDate" format="yyyy-MM-dd"/></dd>
		                                 </dl>
		                                 <dl>
		                                     <dt>下载地址：</dt>
		                                     <dd><a href="<%=path%>/file/view?fileName=<u:des3 value='${a.path}' />" onclick="updateDownloadCount('<s:property value="#a.key" />','<s:property value="#a.version" />')">下载至本机</a></dd>
		                                 </dl>
		                             </div>
	                             </s:iterator>
	                             <div class="info_list">
                            		<s:iterator value="clientList" id="c">
                            			<s:if test="#c.key==2111">
		                                    <strong>演示应用iPA 、源码 包下载<a href="javascript:void(0);" class="update_log" onclick="showlog_a(${c.key})">更新日志</a></strong>
		                                    <dl>
		                                        <dt>版本号：</dt>
		                                        <dd>V<s:property value="#c.version"/></dd>
		                                    </dl>
		                                    <dl>
		                                        <dt>更新时间：</dt>
		                                        <dd><s:date name="#a.updateDate" format="yyyy-MM-dd"/></dd>
		                                    </dl>
		                                    <dl>
	                                        <dt>下载地址：</dt><dd>
	                                    </s:if>
	                                        
	                                        <s:if test="#c.key==2111">
	                                       		 <a href="<%=path%>/file/view?fileName=<u:des3 value="${c.path}" />" onclick="updateDownloadCount('<s:property value="#c.key" />','<s:property value="#c.version" />')">演示iPA安装包</a>
	                                       	</s:if>
	                                       	<s:if test="#c.key==2121">
	                                        	<a href="<%=path%>/file/view?fileName=<u:des3 value="${c.path}" />" onclick="updateDownloadCount('<s:property value="#c.key" />','<s:property value="#c.version" />')">源码</a>
			                                    </dd></dl>
	                                        </s:if>
	                                 </s:iterator>
                                </div>
                            </div> 

                            <div class="info_right">
                                <div class="right_list" id= "a_r"></div>
                            </div>
                        </div>
                        <!--android 资源下载 eof-->    

                        <!--android sdk使用教程 bof--> 
                        <div class="info" style="display:none;"></div>     
                        <!--android sdk使用教程 eof-->               

                    </div>
                </div>  
                <!--android 下载 eof-->

                <!--windows 下载 bof-->
                <div class="download_detail" style="display:none;">
                    <div class="download_tit">
                        <ul>
                            <li class="li1 current">Windows SDK 简介</li>
                            <li class="li2">Windows 资源下载</li>
                            <li class="li3" id="li3_w">Windows SDK 使用教程</li>
                        </ul>
                    </div>                    
                    
                    <div class="dowload_info">

                        <!--windows sdk简介 bof-->
                        <div class="info">
                            <div class="desc">
                                <p>快传融合通讯开放平台Windows SDK是以C++动态库的方式提供给Windows开发人员，可以方便快速为您的Windows应用实现打电话和接听电话的功能，包括在游戏中实现实时语音聊天等功能。</p>
                                <div class="txt">
                                    <b>1. SDK 文件介绍</b>
                                    <p>UCSClient.h接口函数声明文件。 包含初始化、连接服务器、呼叫、应答、挂机、注销、回调函数、双向回拨、即时消息等能力接口函数。</p>
                                </div>
                                <div class="txt">
                                    <b>2. SDK 工具类介绍</b>
                                    <p>FileTools文件工具类。</p>
                                    <p>CustomLog日志工具类，用于打印日志。</p>
                                    <p>MapTools地图位置工具类。</p>
                                </div>
                            </div>                                
                        </div>
                        <!--windows sdk简介 eof-->

                        <!--windows 资源下载 bof-->
                        <div class="info" style="display:none;">
                            <div class="info_left">
                                <s:iterator value="sdkWList" id="a" status="index">
		                            <div class="info_list">
		                                 <strong><s:property value="#a.name"/>
		                                 	<a href="javascript:void(0)" onclick="showlog_w(${a.key})" id="w_l" class="update_log">更新日志:
			                                 	<s:if test="#index.index+1==1">
				                                 	<script type="text/javascript">
				                                 		showlog_w("${a.key}");
				                                 		$("#w_l").attr("class","update_log current");
				                                 	</script>
			                                 	</s:if>
		                                 	</a>
		                                 </strong>
		                                 <p class="mark"><span>正式版</span></p>
		                                 <dl>
		                                     <dt>版本号：</dt>
		                                     <dd>${a.version}</dd>
		                                 </dl>
		                                 <dl>
		                                     <dt>更新时间：</dt>
		                                     <dd><s:date name="#a.updateDate" format="yyyy-MM-dd"/></dd>
		                                 </dl>
		                                 <dl>
		                                     <dt>下载地址：</dt>
		                                     <dd><a href="<%=path%>/file/view?fileName=<u:des3 value='${a.path}' />" onclick="updateDownloadCount('<s:property value="#a.key" />','<s:property value="#a.version" />')">下载至本机</a></dd>
		                                 </dl>
		                             </div>
	                             </s:iterator>
	                             <div class="info_list">
                            		<s:iterator value="clientList" id="c">
                            			<s:if test="#c.key==2113">
		                                    <strong>演示应用iPA 、源码 包下载<a href="javascript:void(0);" class="update_log" onclick="showlog_w(${c.key})">更新日志</a></strong>
		                                    <dl>
		                                        <dt>版本号：</dt>
		                                        <dd>V<s:property value="#c.version"/></dd>
		                                    </dl>
		                                    <dl>
		                                        <dt>更新时间：</dt>
		                                        <dd><s:date name="#a.updateDate" format="yyyy-MM-dd"/></dd>
		                                    </dl>
		                                    <dl>
	                                        <dt>下载地址：</dt><dd>
	                                    </s:if>
	                                        
                                        <s:if test="#c.key==2113">
                                       		 <a href="<%=path%>/file/view?fileName=<u:des3 value="${c.path}" />" onclick="updateDownloadCount('<s:property value="#c.key" />','<s:property value="#c.version" />')">演示iPA安装包</a>
                                       	</s:if>
                                       	<s:if test="#c.key==2123">
                                        	<a href="<%=path%>/file/view?fileName=<u:des3 value="${c.path}" />" onclick="updateDownloadCount('<s:property value="#c.key" />','<s:property value="#c.version" />')">源码</a>
		                                    </dd></dl>
                                        </s:if>
	                                 </s:iterator>
                                </div>
                            </div>

                            <div class="info_right">
                                <div class="right_list" id= "w_r"></div>
                            </div>
                        </div>
                        <!--windows 资源下载 eof-->    

                        <!--windows sdk使用教程 bof--> 
                        <div class="info" style="display:none;"></div>     
                        <!--windows sdk使用教程 eof-->               

                    </div>
                </div>  
                <!--windows 下载 eof-->

    		</div>
    	</div>
	<!--主体部分 ft_content eof-->

	<%@include file="/front/foot1.jsp" %>
	
</body>
</html>