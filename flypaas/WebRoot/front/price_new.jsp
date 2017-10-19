<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
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
			findPrice("001","USA - 48 STATES","美国","United-States");
			//点击输入框，隐藏显示的国家信息
			$(".search_data").click(function(e){
				e.stopPropagation();
				$(".div_code_cur").hide();
				$(".search_data").find("input").focus();
			});
			$(document).click(function(){
				$("#search_list").find("ul").hide();
				$(".div_code_cur").show();
				$(".search_data").find("input").val("");
				});
			
		});
	
		function findCountry(){
			var a = $("#prefixorname").val();
			a = a.trim();
			var prefix = null;
			var namePrefix = null;
			var num = /^[0-9]*$/;
			var en = /^[a-zA-Z ]*$/;
			if(num.test(a)){
				prefix = a;
			}else if(en.test(a)){
				namePrefix = a;
			}
			$.ajax({
	  			url:"<%=path%>/ctList",
	   			type:"post",
	   			data:"namePrefix="+namePrefix+"&prefix="+prefix,
	   			dataType: "text",
	   			success: function (data) {
	   				$("#search_list").find("ul").show();
	   				$("#search_list").find("ul").html(data);
	             },
	             error: function (msg) {
	             }
	   		});
		}
		
		function findPrice(prefix,namePrefix,ch_name,picName){
			$("#prefixorname").val("");
			var picPath = "/front/images1/flag/"+picName+".png";
			$(".div_code_cur").show();
			$(".div_code_cur").find(".code_cur").html(prefix);
			$(".div_code_cur").find(".country_cur").html(ch_name+picPath);
			$.ajax({
	  			url:"<%=path%>/findPrice",
	   			type:"post",
	   			data:"prefix="+prefix + "&namePrefix=" + namePrefix+"&ch_name="+ch_name,
	   			dataType: "text",
	   			success: function (data) {
	   				$("#search_list").find("ul").hide();
	   				$("#global_price").html(data);
	   				var ht = "<span class='code code_cur'>"+prefix+"</span><span class='country country_cur'>"+ch_name+"<img src='"+picPath+"'/></span>";
	   				$(".div_code_cur").html(ht);
	   				$(".div_code_cur").show();
	             },
	             error: function (msg) {
	             }
	   		});
		}
		
		function jump(){
			location.hash="#item_box box42";
		}
	</script>
</head>
<body id="b-06">
	<!--公共头部 ft_header bof-->
    <%@include file="/front/head1.jsp" %>

	<!--主体部分 ft_content bof-->
    <div class="ft_content">
    	<div class="ft_banner ft_banner16"></div>
    	<div class="item_box box41">
    		<div class="item_box_wp">
                <h4>平台资源价格<span>(无技术授权费)</span><a href="javascript:void(0)" onclick="jump();">国际线路资源费用>></a></h4>
                <div class="price_1">
                    <div class="price_table">
                        <table cellpadding="0" cellspacing="0" border="0">
                            <tbody>
                                <tr>
                                    <td class="tit"><b>互联网语音通话</b>通过互联网线路传递语音通话</td>
                                    <td class="ctn"><span><em>免费</em></span></td>
                                </tr>
                                <tr>
                                    <td class="tit"><b>国内(运营商)单向呼叫</b>通过云平台单向呼出通话</td>
                                    <td class="ctn"><span><em>￥0.0300</em>元 / 分钟</span></td>
                                </tr>
                                <tr>
                                    <td class="tit"><b>国内(运营商)双向呼叫</b>通过云平台桥接两方进行通话</td>
                                    <td class="ctn"><span><em>￥0.0600</em>元 / 分钟</span></td>
                                </tr>
                                <tr>
                                    <td class="tit"><b>平台通话录音</b>客服通话过程录音服务</td>
                                    <td class="ctn"><span><em>￥0.0600</em>元 / 分钟（录制）</span><span><em>￥1</em>元 / 月·G（存储）</span></td>
                                </tr>
                                <tr class="last">
                                    <td class="tit"><b>多方通话</b>多方会议通话模式</td>
                                    <td class="ctn"><span><em>免费（IP） </em>元 / 分钟·方</span><span><em>￥0.0900（落地）</em>元 / 分钟·方</span></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <div class="price_table">
                        <table cellpadding="0" cellspacing="0" border="0">
                            <tbody>
                                <tr>
                                    <td class="tit"><b>点对点视频</b>通过远程在线视频的方式进行实时通话</td>
                                    <td class="ctn"><span><em>免费</em></span></td>
                                </tr>
                                <tr class="last">
                                    <td class="tit"><b>即时消息IM</b>通过网络发送接收多媒体文本、语音等信息</td>
                                    <td class="ctn"><span><em>免费</em></span></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <div class="price_table">
                        <table cellpadding="0" cellspacing="0" border="0">
                            <tbody>
                                <tr>
                                    <td class="tit"><b>短信验证</b>通过短信SMS下发身份验证码</td>
                                    <td class="ctn"><span><em>￥0.0500</em>元 / 条</span></td>
                                </tr>
                                <tr>
                                    <td class="tit"><b>语音验证</b>通过语音播报验证码进行身份校验</td>
                                    <td class="ctn"><span><em>￥0.0300</em>元 / 条</span></td>
                                </tr>
                                <tr>
                                    <td class="tit"><b>智能验证</b>通过语音播报验证码进行身份校验</td>
                                    <td class="ctn"><span><em>￥0.0300</em>元 / 条</span></td>
                                </tr>
                                <tr class="last">
                                    <td class="tit"><b>语音通知</b>通过语音播报通知类固定信息</td>
                                    <td class="ctn"><span><em>￥0.0500</em>元 / 条</span></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                </div>    			
    		</div>
    	</div>

    	<div class="item_box box42" id="item_box box42">
    		<div class="item_box_wp">
    			<div class="price_2">
                    <div class="price_search">
                        <label>查询国际资费</label>
                        <div class="search_data">
                        	<div class="div_code_cur" style="display:none;">
                            <span class="code code_cur"></span>
                            <span class="country country_cur"></span></div>
                        	<input type="text" id="prefixorname" onkeyup="findCountry();"/>
                            <div class="search_list" id="search_list">
                            	<ul></ul>
                            </div>
                        </div>
                        <p class="tips">输入数字 或者 英文字母 进行查询</p>
                    </div>       
                    
                    
                    <div class="global_price">
                    <div  id="global_price"></div>
                    </div>
                </div>
    		</div>
    	</div>

	<!--主体部分 ft_content eof-->

	<%@include file="/front/foot1.jsp" %>
	
</body>
</html>