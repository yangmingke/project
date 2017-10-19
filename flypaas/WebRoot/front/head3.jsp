<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@page import="com.flypaas.utils.SysConfig"%>
<%
	String aboutPath = SysConfig.getInstance().getProperty("about_host");
%>
<!--公共头部 ft_header bof-->
<script type="text/javascript" src="<%=path%>/js/cookie.js"></script>
	<div class="ft_header">
		<div class="ft_header_wp">
			<div class="ft_logo">
				<a href="<%=path%>/" title="快传 UNIFIED COMMUNICATION PLATFORM-AS-A-SERVICE">
					<img src="<%=path%>/front/images1/logo.png" alt="快传 UNIFIED COMMUNICATION PLATFORM-AS-A-SERVICE" />
					<%-- <img src="<%=path%>/front/images1/logo_mascot.png" alt="快传 UNIFIED COMMUNICATION PLATFORM-AS-A-SERVICE" class="logo_mascot" /> --%>
				</a>
			</div>
			<div class="ft_menu">
				<ul>
                    <li t_nav="about" id="m-01"><a href="javascript:void(0)" onclick="document.getElementById('item-01').scrollIntoView();" class="default">关于我们</a></li>
                    <li t_nav="video_radio" id="m-02"><a href="javascript:void(0)" onclick="document.getElementById('item-02').scrollIntoView();" class="default">音视频通话</a></li>
                    <li t_nav="can" id="m-03"><a href="javascript:void(0)" onclick="document.getElementById('item-03').scrollIntoView();" class="default">CAN</a></li>
                    <li t_nav="solution" id="m-04"><a href="javascript:void(0)" onclick="document.getElementById('item-04').scrollIntoView();" class="default">解决方案</a></li>
                    <li t_nav="sdk" id="m-05"><a href="javascript:void(0)" onclick="document.getElementById('item-05').scrollIntoView();" class="default">SDK</a></li>
                    <li t_nav="call_us" id="m-06"><a href="javascript:void(0)" onclick="document.getElementById('item-06').scrollIntoView();" class="default">联系我们</a></li>
                    <li t_nav="download" id="m-07"><a href="<%=path%>/file/downLoad" class="default">DEMO下载</a></li>
                    <%-- <li t_nav="home" id="m-01"><a href="<%=path%>/" class="default">首页</a></li> --%>
                    <!-- <li t_nav="voice" id="m-02"><a href="/product_service/voice" class="default">语音通话</a></li>
                    <li t_nav="telemarket" id="m-03"><a href="/freetrial/telemarket" class="default">视频通话</a></li> -->
                    <%-- <li t_nav="product" id="m-02">
                        <a href="javascript:void(0)" class="default">产品与服务</a>
                        <div class="submenu" id="product" t_nav="product">
                            <em>&nbsp;</em>
                            <div class="list1">
                                <strong>产品</strong>
                                <dl>
                                    <dt><a href="<%=path%>/message">短信</a></dt>
                                    <dd><a href="<%=path%>/message">短信通知</a><a href="<%=path%>/message">短信验证码</a></dd>
                                </dl>
                                <dl>
                                    <dt><a href="/product_service/voice">语音通话</a></dt>
                                    <dd><a href="/product_service/voice_1">语音通知</a><a href="/product_service/voice_2">语音验证码</a><a href="/product_service/voice_1">直拨通话</a><a href="/product_service/voice_1">回拨通话</a></dd>
                                    <dd><a href="/product_service/voice_1">互联网语音</a><a href="/product_service/voice_1">点击呼叫</a></dd>
                                    <dd><a href="/product_service/voice_2">语音群聊</a><a href="/product_service/voice_1">语音会议</a><a href="/product_service/voice_2">语音呼转</a><a href="/product_service/voice_2">通话录音</a></dd>
                                </dl>
                                <dl>
                                    <dt><a href="/product_service/im">即时消息IM</a></dt>
                                    <dd><a href="/product_service/im">多媒体消息</a><a href="/product_service/im">单聊</a><a href="/product_service/im">群聊</a></dd>
                                </dl>
                                <dl>
                                    <dt><a href="/freetrial/telemarket">视频</a></dt>
                                    <dd><a href="/freetrial/telemarket">一对一视频</a><a href="/freetrial/telemarket">多人视频</a></dd>
                                </dl>
                            </div>
                            <div class="list2">
                                <strong>体验&amp;下载</strong>
                                <div class="list2_a">
                                    <a href="/freetrial/VoiceVerificationCode_demo">Demo下载</a>
                                    <a href="/freetrial/VoiceVerificationCode">在线体验</a>
                                    <a href="/product_service/download">SDK下载</a>
                                </div>
                            </div>
                        </div>               
                    </li> --%>
                    <%-- <li t_nav="solution" id="m-03">
                        <a href="#" class="default">场景与方案</a>
                        <div class="submenu" id="solution" t_nav="solution">
                            <em>&nbsp;</em>
                            <div class="list1">
                                <strong>场景案例</strong>
                                <div class="list2_a">
                                    <a href="/case/social">社交应用</a>
                                    <a href="/case/o2o">O2O</a>
                                    <a href="/case/category">分类信息</a>
                                    <a href="/case/medical">远程医疗</a>
                                </div>
                            </div>
                            <div class="list2">
                                <strong>解决方案</strong>
                                <div class="list2_a">
                                    <a href="/solution/freeVoice">企业通讯</a>
                                    <a href="/solution/ip">IP客服</a>
                                    <a href="/solution/corporateCommt">安全通讯</a>
                                    <a href="/solution/interRoaming">国际通话</a>
                                </div>
                            </div>
                        </div> 
                    </li> --%>
                    <!-- <li t_nav="can" id="m-04"><a href="/freetrial/can" class="default">CAN</a></li>
                    <li t_nav="solution" id="m-05"><a href="/solution/freeVoice" class="default">解决方案</a></li>
                    <li t_nav="sdk" id="m-06"><a href="/freetrial/sdk" class="default">SDK</a></li> -->
                    <!-- <li t_nav="api" id="m-05"><a href="http://docs.flypaas.com/doku.php" target="_blank" class="default">API&amp;文档</a></li>
                    <li t_nav="support" id="m-06"><a target="_blank" href="http://bbs.flypaas.com/forum.php" class="default">技术支持</a></li> -->
                </ul>
			</div>
			<%-- <div class="ft_log">
				<s:if test="#session.user==null">
					<a href="javascript:void(0)" class="log" onclick="location.href='<%=path%>/login'">登&nbsp;&nbsp;录</a>
					<a href="javascript:void(0)" class="reg" onclick="location.href='<%=path%>/user/toSign'">注册</a>
				</s:if>
				<s:else>
					<a href="<%=path%>/user/account" title="管理中心" class="admin_center">开发者控制台</a>
				</s:else>
			</div> --%>
		</div>
	</div>
	<!--公共头部 ft_header eof-->
	<!--popupBox-->
<!--弹层（提交后）bof-->
  <div class="background_box" style="display:none;">&nbsp;</div>
  <div class="float_box" id="popup_box" style="display:none;">
    <div class="float_tit">
      <h3></h3>
      <h1></h1>
    </div>
    <div class="float_ctn">
    	<p class="float_field"></p>
        <div class="float_btn">
          <input type="button" value="取消" id="cancel" class="cancel_btn" />
          <input type="button" value="确定" id="popup_smt" class="confirm_btn" />
      </div>
    </div>
  </div>
  <!--弹层（提交后）eof-->

  <script type="text/javascript">
  $(function(){
    /* var bid = $("body").attr("id");
    if(bid==undefined){
    	return false;
    }
    var menus = bid.substring(2);
    $("#m-"+menus).addClass("active").siblings("li").removeClass("active"); */	  
    /* $("#m-02").addClass("active").siblings("li").removeClass("active"); */
	/* $("#m-01,#m-02,#m-03,#m-04,#m-05,#m-06,#m-07").click(function(){
		var bid = $(this).attr("id");
	    if(bid==undefined){
	    	return false;
	    }
	    var menus = bid.substring(2);
	    $("#m-"+menus).addClass("active").siblings("li").removeClass("active");
	}); */
    
	$("#item-01,#item-02,#item-03,#item-04,#item-05,#item-06,#item-07").hover(function(){
		var bid = $(this).attr("id");
	    if(bid==undefined){
	    	return false;
	    }
	    var menus = bid.substring(5);
	    $("#m-"+menus).addClass("active");
	},function(){
		var bid = $(this).attr("id");
	    if(bid==undefined){
	    	return false;
	    }
	    var menus = bid.substring(5);
	    $("#m-"+menus).removeClass("active");
	});
	
	//首页客户图标鼠标放上去状态变化
	$(".call_us ul li,.sdk .img").hover(function(){
		var img_src = $(this).find("img").attr("src");
		var img_name = img_src.substring(7).replace(".png","");
		//alert(img_name);
		$(this).find("img").attr("src","/front/"+img_name+"_hover.png");
	},function(){
		var img_src = $(this).find("img").attr("src");
		var img_name = img_src.substring(7).replace("_hover.png","");
		$(this).find("img").attr("src","/front/"+img_name+".png");
	})
    
  });
  
  
  </script>