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
				</a>
			</div>
			<div class="ft_menu">
				<ul>
                    <li t_nav="home" id="m-01"><a href="/" class="default">关于我们</a></li>
                    <li t_nav="voice" id="m-02"><a  href="/" class="default">语音通话</a></li>
                    <li t_nav="telemarket" id="m-03"><a href="/" class="default">视频通话</a></li>
                    <li t_nav="can" id="m-04"><a href="/" class="default">CAN</a></li>
                    <li t_nav="solution" id="m-05"><a href="/" class="default">解决方案</a></li>
                    <li t_nav="sdk" id="m-06"><a href="/" class="default">SDK</a></li>
                    <li t_nav="callus" id="m-07"><a href="/" class="default">联系我们</a></li>
                </ul>
			</div>
			<div class="ft_log">
				<s:if test="#session.user==null">
					<a href="javascript:void(0)" class="log" onclick="location.href='<%=path%>/login'">登&nbsp;&nbsp;录</a>
					<a href="javascript:void(0)" class="reg" onclick="location.href='<%=path%>/user/toSign'">注册</a>
				</s:if>
				<s:else>
					<a href="<%=path%>/user/account" title="管理中心" class="admin_center">开发者控制台</a>
				</s:else>
			</div>
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
	})
    
  });
  </script>