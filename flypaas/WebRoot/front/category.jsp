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
</head>
<body id="b-03">
	<!--公共头部 ft_header bof-->
    <%@include file="/front/head1.jsp" %>
	<!--主体部分 ft_content bof-->
    <div class="ft_content">
    	<div class="ft_banner ft_banner10"></div>
    	<div class="item_box box25">
    		<div class="item_box_wp">                
                <div class="category_1">
                    <div class="txt">
                        <h6>点击呼叫</h6>
                        <p>在传统呼叫中，用户需要反复记忆需要沟通的长达8位甚至11位的数字，并在拨号盘内逐个输入才完成一次沟通的过程。摒弃传统的沟通方式，通过应用内按钮直接点击呼叫完成沟通。提升用户体验，而又能确保用户停留在应用内，真实留住用户。</p>
                        <p class="doc_link"><a href="http://docs.flypaas.com/doku.php?id=双向回拨">查看回拨接口</a></p>
                        <p class="img"><img src="<%=path%>/front/images1/img33.png" /></p>
                    </div>
                </div>    			
    		</div>
    	</div>

    	<div class="item_box box25 box26">
            <div class="item_box_wp">                
                <div class="category_1 category_2">
                    <div class="txt">
                        <h6>即时消息</h6>
                        <p>为应用提供可扩展的即时客服服务，在应用内即时解答客户疑问，反馈客户需求。精细化服务客户既能提升客户服务满意度，又能满足应用内运营产品需求。</p>
                        <p class="doc_link"><a href="http://docs.flypaas.com/doku.php?id=即时消息接口_android">查看即时消息接口</a></p>
                        <p class="img"><img src="<%=path%>/front/images1/img34.png" /></p>
                    </div>
                </div>              
            </div>
        </div>

        <div class="item_box box27">
            <div class="item_box_wp">                
                <div class="category_3">
                    <ul>
                        <li class="li1">
                            <dl>
                                <dt><img src="<%=path%>/front/images1/img35.png" /></dt>
                                <dd><b>按需付费</b></dd>
                                <dd>功能使用接入免费，按资源消耗计费，拥有行业最具性价比的成本，每路线路消耗按时长收费，不使用不计费原则，降低企业部署通讯服务成本。</dd>
                            </dl>
                        </li>
                        <li class="li2">
                            <dl>
                                <dt><img src="<%=path%>/front/images1/img36.png" /></dt>
                                <dd><b>跨平台SDK</b></dd>
                                <dd>提供iOS、Android、Web常用平台SDK开发包，可快速无缝集成至已有系统中，降低63%的开发成本</dd>
                            </dl>
                        </li>
                        </ul>
                </div>              
            </div>
        </div>

	</div>

	<!--主体部分 ft_content eof-->
	

	<%@include file="/front/foot1.jsp" %>
	
</body>
</html>