<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="baidu-site-verification" content="H655bLuKzM" />
	<title>快传音视频开放平台_提供音视频通讯加速,一对一视频通话，视频直播等通讯加速API及SDK</title>
	<meta name="keywords" content="一对一音视频通话,音视频通讯,音视频通讯加速,音视频通话,在线教育,视频通话,视频会议,互联网语音,通讯加速,快传,快传技术," >
	<meta name="description" content="快传音视频通讯平台让开发者轻松接入一对一视频通话,音视频通讯加速,视频直播等能力,快速搭建音视频通讯,视频通话/会议应用,通讯加速,音视频引擎让视频更流畅。" >
    <%@include file="/front/resource1.jsp"%>
</head>
<body id="b-01">
	<!--公共头部 ft_header bof-->
    <%@include file="/front/head1.jsp" %>
    <!--网页主体部分-->
	<div id="banner" class="banner">
		<div class="banner_first">
			<h1><span class="gold_word">精</span>于算法</h1>
			<h1><span class="gold_word">简</span>于接口</h1>
		</div>
		<div class="banner_second">
			<h2>快传<span class="gold_word">实时音视频通讯</span>加速平台</h2>
		</div>
	</div>
	<div id="item-01" class="about">
		<div class="about_content">
			<h1>关于我们</h1>
			<h3>深圳市快传技术有限公司成立于2015年12月，目标是为开发者提供端到端延迟低于300毫秒的实时音视频通讯能力。创始团队曾参与构建亿级用户的音视频通讯平台，拥有十余年音视频处理和通讯组网经验。快传技术通过flypaas开放平台，聚合海量网络节点资源，形成完善的实时音视频通讯网络，为开发者提供云加速SDK。</h3>
		</div>
	</div>
	<div id="item-02" class="video_audio">
		<div class="audio_content">
			<h1>音频通话&nbsp;&nbsp;<span class="blue">Audio call</span></h1>
			<p>自主研发音视频引擎，语音增强模块支持AEC、NS、AGC</p>
			<p>行业前端的AEC、抖动缓冲、前向纠错和丢帧补偿技术，<br/>快速适应弱信号和网络动态切换</p>
			<p>语音编解码全频带支持，码率动态切换，<br/>引擎实时保证延时、流畅和音质平衡</p>
			<p>适配各种终端、各种网络，低延迟、<br/>不卡顿、高音质、无回声、强降噪</p>
		</div>
		<div class="video_content">
			<h1>视频通话&nbsp;&nbsp;<span class="blue">Video call</span></h1>
			<p>支持H.264、自研H.265软编码技术，<br/>H.265在与同分辨率和码流上，可减少带宽30%以上</p>
			<p>支持android、ios硬件编解码，<br/>cpu及内存开销显著减少，<br/>手机不发烫，减少能量消耗</p>
			<p>支持网络状况探测，视频码率动态调整，<br/>深度优化的FEC和NACK抗丢包技术，<br/>保证3G/4G/wifi等各种网络状态下的视频画质与流畅度</p>
		</div>
	</div>
	<div id="item-03" class="can">
		<div class="can_head">
			<h1>CAN</h1>
		</div>
		<div class="can_head_introduce">
			<h3>Communication Accelerate  Network</h3>
			<h3>通讯加速网</h3>
		</div>
		<div class="can_introduce1">
			<h3 class="can_introduce1_title">多业务通讯加速能力</h3>
			<h3>通过海量流媒体边缘转发节点、全球分布式部署、智能化路由算法，可实现多种通讯业务的端到端延迟低于300毫秒</h3>
		</div>
		<div class="can_introduce2">
			<h3 class="can_introduce2_title">组网能力</h3>
			<h3>创始团队曾参与基于类似架构组网，承载过亿级用户并运营多年，用户体验及系统稳定性已被充分验证</h3>
		</div>
		<div class="can_introduce3">
			<h3 class="can_introduce3_title">扩容能力</h3>
			<h3>支持多业务集群部署，对接一线网络资源方，可管理上万通讯节点，支持无感知自动扩容</h3>
		</div>
		<div class="can_introduce4">
			<h3 class="can_introduce4_title">架构能力</h3>
			<h3>领先的高性能、高并发设计能力，核心组件单机单核并发处理能力超过1000路通讯</h3>
		</div>
		<div class="can_introduce5">
			<h3 class="can_introduce5_title">通讯安全:全方位安全保障能力</h3>
			<h3>HTTPS保障通讯链路安全，HS256+base64保障通讯数据安全</h3>
		</div>
	</div>
	<div id="item-04" class="solution">
		<div class="solution_head">
			<h1>解决方案&nbsp;<span class="blue">Solution</span></h1>
		</div>
		<div class="solution_introduce1">
			<h2 class="solution_introduce1_title">视频直播</h2>
			<h3><span class="black">主播上行加速:</span>聚合海量网络节点后，可使得边缘接入节点离主播更近；通讯组网为上行提供了智能选路算法、最优动态路由选择，可保证上行连接稳定、延迟低</h3>
		</div>
		<div class="solution_introduce2">
			<h2 class="solution_introduce2_title">视频监控</h2>
			<h3><span class="black">手机实时查看ipCam加速：</span>通过就近接入和动态路由技术，可实现RTSP数据的加速推送</h3>
		</div>
		<div class="solution_introduce3">
			<h2 class="solution_introduce3_title">音视频聊天加速</h2>
			<h3><span class="black">自研全部核心算法：</span>在WebRTC的媒体处理框架之上，实现了端到端全面QoS优化</h3>
		</div>
		<div class="solution_introduce4">
			<h2 class="solution_introduce4_title">其他互联网通讯需求</h2>
			<h3><span class="black">行业应用通讯加速：</span>视频会议、在线教育、智慧社区、车联网和物联网应用中，通过动态路由技术，可实现数据源节点和分发末端节点之间数据的加速推送</h3>
		</div>
	</div>
	<div id="item-05" class="sdk">
		<div class="sdk_content">
			<h1>SDK</h1>
			<h3 class="first">以PaaS开放平台的方式为开发者提供实时音视频通讯云加速SDK，</h3>
			<h3>开发者在flyPaaS平台上注册后，调用快传提供的云加速SDK，</h3>
			<h3>即可满足各种应用场景中的实时音视频通讯需求，</h3>
			<h3>开发者只需根据通讯产生的媒体转发总流量付费。</h3>
			<!-- <h3>下载：用github提供下载、使用指南等</h3> -->
		</div>
		<div class="sdk_download">
			<a class="img" href="https://github.com/flyRTC/flySDK" target="_blank" rel="NOfollow">
				<img src="<%=path%>/front/images1/sdk_download.png" class="leave_img" alt="前往github" title="前往github"/>
				<img src="<%=path%>/front/images1/sdk_download_hover.png" class="enter_img" style="display:none;" alt="前往github" title="前往github"/>
			</a>
		</div>
	</div>
	<div id="item-06" class="call_us">
		<div class="company">
			<h1>合作伙伴</h1>
			<div class="company_img">
				<img src="<%=path%>/front/images1/company_img.png" class="leave_img" alt="合作伙伴"/>
				<img src="<%=path%>/front/images1/company_img_hover.png" class="enter_img" style="display:none;" alt="合作伙伴"/>
			</div>
		</div>
		<div class="blog">
            <img src="<%=path%>/front/images1/sina_code.png" alt="快传微博二维码"/>
			<span><a href="http://weibo.com/u/6069886407" target="_blank" rel="NOfollow" title="前往快传微博">快传微博</a></span>
		</div>
		<div class="head">
			<h1>联系我们</h1>
		</div>
		<div class="linkman">
			<h3>联系人</h3>
			<h3>张小姐</h3>
		</div>
		<div class="detail">
			<h3>电&nbsp;&nbsp;话：13510563690&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮&nbsp;&nbsp;箱：sales@flypaas.com</h3>
			<h3>Q&nbsp;&nbsp;&nbsp;Q：2909770113&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Q&nbsp;Q群：533899415</h3>
			<h3>地&nbsp;&nbsp;址：深圳市南山区科技园高新南七道惠恒大厦2期205</h3>
		</div>
	</div>
	<div class="copyright">
        <div class="ft_footer_wp">
            <p>
            © 深圳市快传技术有限公司. All Rights Reserved<i class="space">|</i>服务协议<i class="space">|</i>隐私声明<i class="space">|</i><a href="http://www.miitbeian.gov.cn/" target="_blank" rel="NOfollow" title="前往工信部的备案网站" ><span class="icp" style="color: rgb(137, 138, 139);">粤ICP备16104934号</span></a> 
            </p>
        </div>
    </div>
</body>
</html>